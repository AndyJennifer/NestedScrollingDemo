package com.jennifer.andy.nestedscrollingdemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.OverScroller;

import com.jennifer.andy.nestedscrollingdemo.R;

/**
 * Author:  andy.xwt
 * Date:    2018/8/8 14:27
 * Description:传统处理嵌套滑动的方式
 */

public class NestedTraditionLayout extends LinearLayout {

    private View mHeadView;

    private int mHeadTopHeight;
    private int mLastY;
    private boolean isHideHead;

    private OverScroller mScroller;


    public NestedTraditionLayout(Context context) {
        this(context, null);
    }

    public NestedTraditionLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NestedTraditionLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new OverScroller(context);//这里使用scroller，是因为需要控制滚动
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        int action = event.getAction() & MotionEvent.ACTION_MASK;
        int y = (int) event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int dy = mLastY - y;
                //如果大于最小滑动距离
                if (Math.abs(dy) > ViewConfiguration.getTouchSlop()) {
                    if (dy > 0) { //如果是向上滑，且当前headView没有隐藏
                        if (!isHideHead) {
                            return true;
                        }
                    } else {//如果是向下, 且将headView隐藏后就不能滑动了
                        if (getScrollY() + dy > 0 && getScrollY() + dy <= mHeadTopHeight) {
                            return true;
                        }
                    }
                }
                break;
        }
        return super.onInterceptTouchEvent(event);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();

        int action = event.getAction() & MotionEvent.ACTION_MASK;
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_DOWN) {
            if (!mScroller.isFinished()) {
                mScroller.abortAnimation();
            }
        }
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int dy = mLastY - y;
                //其实做到这里大家应该明白了，因为就算我们控制了，滑动的范围，但是接下来的事件都是被父控件获取了，所以在同一事件序列中，子类是没有办法去响应事件的
                if (Math.abs(dy) > ViewConfiguration.getTouchSlop()) {
                    scrollBy(0, dy);
                }
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 重新scrollTo方法，因为scrollBy最终会调用，scrollTo方法
     */
    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        }
        if (y > mHeadTopHeight) {
            y = mHeadTopHeight;
        }
        super.scrollTo(x, y);
        isHideHead = getScrollY() == mHeadTopHeight;//判断当前head是否已经隐藏了
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //计算headView高度
        measureChild(mHeadView, widthMeasureSpec, heightMeasureSpec);
        mHeadTopHeight = mHeadView.getHeight();

        //重新计算高度，不然隐藏了headView后下面会空一截headView的高度
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int newMeasureSpec = MeasureSpec.makeMeasureSpec(height + mHeadTopHeight, heightMode);
        super.onMeasure(widthMeasureSpec, newMeasureSpec);

    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mHeadView = findViewById(R.id.iv_head_image);
    }


}
