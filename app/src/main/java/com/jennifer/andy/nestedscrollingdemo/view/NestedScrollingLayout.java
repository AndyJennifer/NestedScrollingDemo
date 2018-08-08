package com.jennifer.andy.nestedscrollingdemo.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.jennifer.andy.nestedscrollingdemo.R;

/**
 * Author:  andy.xwt
 * Date:    2018/8/8 14:28
 * Description:
 */

public class NestedScrollingLayout extends LinearLayout {

    private View mHeadView;
    private int mHeadTopHeight;
    private ValueAnimator mValueAnimator;

    public static final String TAG = "NestedScrollingLayout";
    private static final int MAX_OFFSET_ANIMATION_DURATION = 600;

    public NestedScrollingLayout(Context context) {
        super(context);
    }

    public NestedScrollingLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NestedScrollingLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 判断是否进行嵌套滑动，意思就是当内部子view滑动时，外部ViewGroup是否能接受到内部子view的滑动参数
     *
     * @param child            内部滑动的子view
     * @param target           内部滑动的子view
     * @param nestedScrollAxes 当前内部滑动的子view的滑动模式，横向或者是竖直方向
     * @return 是否接受
     */
    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;//只有竖直滑动的时候才接受参数
    }

    /**
     * 在目标视图消耗一部分滚动之前，对正在进行的嵌套滚动进行反应。
     *
     * @param target   内部滑动的子view
     * @param dx       当前内部滑动的子view,移动的横向距离  dx>0向左滑
     * @param dy       当前内部滑动的子view,移动的竖直方向距离 dy>0向上滑
     * @param consumed 当前父view消耗的水平与垂直距离
     */
    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        if (dy > 0) {//向上滑
            if (getScrollY() + dy <= mHeadTopHeight) {//一直向上滑，直到隐藏headView为止，
                scrollBy(0, dy);
                consumed[1] = dy;
            }
        } else {//向下滑
            if (getScrollY() + dy > 0 && !target.canScrollVertically(-1)) {//当内部的子view不能再滑动，且headView是隐藏的时候，
                scrollBy(0, dy);
                consumed[1] = dy;
            }
        }
    }


    /**
     * 当前父view执行快速滑动
     *
     * @param target    当前将要响应快速滑动的子view
     * @param velocityX 横向速度 大于0向左滑
     * @param velocityY 竖直速度 大于0向上滑
     * @param consumed  当前子view是否能消费掉快速滑动，（因为有可能,当前子view以及滑动到头了）
     * @return 返回true, 表示当前父view，执行了快速滑动
     */
    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        Log.i(TAG, "onNestedFling: velocityY" + velocityY);
        // 还要处理快速滑动的情况 可能快速滑动，导致headView没有完全显示，或者没有完全隐藏
        if (velocityY > 0 && getScrollY() > 0 && getScrollY() < mHeadTopHeight) {//向上滑
            startAnimation(200, getScrollY(), mHeadTopHeight);
        } else {
            startAnimation(300, getScrollY(), 0);
        }
        return true;
    }


    /**
     * 在内部子view快速滑动之前，当前父View是否消耗
     *
     * @param target    当前将要响应快速滑动的子view
     * @param velocityX 横向速度 大于0向左滑
     * @param velocityY 竖直速度 大于0向上滑
     * @return 返回false 则父view不消耗，返回true则消耗，那么子View就不能进行快速滑动
     */
    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        Log.i(TAG, "onNestedPreFling:getScrollY--> " + getScrollY());
        return false;
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


    private void startAnimation(long duration, int startY, int endY) {
        if (mValueAnimator == null) {
            mValueAnimator = new ValueAnimator();
            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int animatedValue = (int) animation.getAnimatedValue();
                    scrollTo(0, animatedValue);
                }
            });
        } else {
            mValueAnimator.cancel();
        }
        mValueAnimator.setIntValues(startY, endY);
        mValueAnimator.setDuration(duration);
        mValueAnimator.start();
    }


}
