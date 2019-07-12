package com.jennifer.andy.nestedscrollingdemo.ui.nested.normal_form;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingChild2;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;

import static android.support.v4.view.ViewCompat.TYPE_NON_TOUCH;

/**
 * Author:  andy.xwt
 * Date:    2019-06-28 00:12
 * Description: NestedScrollingChild2与NestedScrollingChild接口的最大差异就是处理fling,
 * 所以我们直接查看fling效果的处理
 */

public class NestedScrollingChild2View extends View implements NestedScrollingChild2 {

    private NestedScrollingChildHelper mScrollingChildHelper = new NestedScrollingChildHelper(this);
    private final int mMinFlingVelocity;
    private final int mMaxFlingVelocity;
    private OverScroller mScroller;

    public NestedScrollingChild2View(Context context) {
        super(context);
        ViewConfiguration vc = ViewConfiguration.get(context);
        mMinFlingVelocity = vc.getScaledMinimumFlingVelocity();
        mMaxFlingVelocity = vc.getScaledMaximumFlingVelocity();
        mScroller = new OverScroller(context);
    }


    @Override
    public boolean startNestedScroll(int axes, int type) {
        return mScrollingChildHelper.startNestedScroll(axes);
    }


    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow, int type) {
        return mScrollingChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type);
    }


    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow, int type) {
        return mScrollingChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type);
    }

    @Override
    public void stopNestedScroll(int type) {
        mScrollingChildHelper.stopNestedScroll(type);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return mScrollingChildHelper.dispatchNestedPreFling(velocityX, velocityY);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return mScrollingChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }


    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        mScrollingChildHelper.setNestedScrollingEnabled(enabled);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return mScrollingChildHelper.isNestedScrollingEnabled();
    }

    @Override
    public boolean hasNestedScrollingParent(int type) {
        return mScrollingChildHelper.hasNestedScrollingParent(type);
    }


    private VelocityTracker mVelocityTracker;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();

        int y = (int) event.getY();
        int x = (int) event.getX();

        //添加速度检测器，用于处理fling效果
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);

        switch (action) {
            case MotionEvent.ACTION_UP: {//当手指抬起的时，结束嵌套滑动传递,并判断是否产生了fling效果
                mVelocityTracker.computeCurrentVelocity(1000, mMaxFlingVelocity);
                int xvel = (int) mVelocityTracker.getXVelocity();
                int yvel = (int) mVelocityTracker.getYVelocity();
                fling(xvel, yvel);
                mVelocityTracker.clear();
                stopNestedScroll(ViewCompat.TYPE_TOUCH);
                break;
            }

        }

        return super.onTouchEvent(event);
    }

    private boolean fling(int velocityX, int velocityY) {
        //判断速度是否足够大。如果够大才执行fling
        if (Math.abs(velocityX) < mMinFlingVelocity) {
            velocityX = 0;
        }
        if (Math.abs(velocityY) < mMinFlingVelocity) {
            velocityY = 0;
        }
        if (velocityX == 0 && velocityY == 0) {
            return false;
        }
        if (dispatchNestedPreFling(velocityX, velocityY)) {
            boolean canScroll = canScroll();
            //将fling效果传递给父控件
            dispatchNestedFling(velocityX, velocityY, canScroll);

            //子控件在处理fling效果
            if (canScroll) {
                //通知父控件开始fling事件，注意这里默认传递的是竖直方向，具体方向由子控件决定
                startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL, ViewCompat.TYPE_NON_TOUCH);
                velocityX = Math.max(-mMaxFlingVelocity, Math.min(velocityX, mMaxFlingVelocity));
                velocityY = Math.max(-mMaxFlingVelocity, Math.min(velocityY, mMaxFlingVelocity));
                doFling(velocityX, velocityY);
                return true;
            }

        }
        return false;

    }

    private int mLastFlingX;
    private int mLastFlingY;
    private final int[] mScrollConsumed = new int[2];

    /**
     * 实际的fling处理效果
     */
    private void doFling(int velocityX, int velocityY) {
        mScroller.fling(0, 0, velocityX, velocityY, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        postInvalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            int x = mScroller.getCurrX();
            int y = mScroller.getCurrY();
            int dx = x - mLastFlingX;
            int dy = y - mLastFlingY;

            mLastFlingX = x;
            mLastFlingY = y;
            //在子控件处理fling之前，先判断父控件是否消耗
            if (dispatchNestedPreScroll(dx, dy, mScrollConsumed, null, TYPE_NON_TOUCH)) {
                //计算父控件消耗后，剩下的距离
                dx -= mScrollConsumed[0];
                dy -= mScrollConsumed[1];

                //因为之前默认向父控件传递的竖直方向，所以这里子控件也消耗剩下的竖直方向
                int hResult = 0;
                int vResult = 0;
                int leaveDx = 0;//子控件水平fling 消耗的距离
                int leaveDy = 0;//父控件竖直fling 消耗的距离

                if (dx != 0) {
                    leaveDx = childFlingX(dx);
                    hResult = dx - leaveDx;//得到子控件消耗后剩下的水平距离
                }
                if (dy != 0) {
                    leaveDy = childFlingY(dy);//得到子控件消耗后剩下的竖直距离
                    vResult = dy - leaveDy;
                }

                dispatchNestedScroll(leaveDx, leaveDy, hResult, vResult, null, TYPE_NON_TOUCH);

            }
        } else {
            stopNestedScroll(TYPE_NON_TOUCH);

        }

    }

    /**
     * 判断子子控件是否能够滑动，只有能滑动才能处理fling
     */
    private boolean canScroll() {
        //具体逻辑自己实现
        return false;
    }

    /**
     * 子控件消耗多少竖直方向上的fling,由子控件自己决定
     *
     * @param dy 父控件消耗部分竖直fling后,剩余的距离
     * @return 子控件竖直fling，消耗的距离
     */
    private int childFlingY(int dy) {

        return 0;
    }

    /**
     * 子控件消耗多少竖直方向上的fling,由子控件自己决定
     *
     * @param dx 父控件消耗部分水平fling后,剩余的距离
     * @return 子控件水平fling，消耗的距离
     */
    private int childFlingX(int dx) {
        return 0;
    }
}