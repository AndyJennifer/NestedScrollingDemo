package com.jennifer.andy.nestedscrollingdemo.normal_form;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.view.View;

/**
 * Author:  andy.xwt
 * Date:    2019-06-28 00:12
 * Description:
 */

public class NestedScrollingChildView extends View implements NestedScrollingChild {

    private NestedScrollingChildHelper mScrollingChildHelper = new NestedScrollingChildHelper(this);

    public NestedScrollingChildView(Context context) {
        super(context);
    }


    /**
     * 开启一个嵌套滑动
     *
     * @param axes 支持的嵌套滑动方法，分为水平方向，竖直方向，或不指定
     * @return 如果返回true, 表示当前子view已经找了一起嵌套滑动的view
     */
    @Override
    public boolean startNestedScroll(int axes) {
        return mScrollingChildHelper.startNestedScroll(axes);
    }


    /**
     * 在子view滑动前，将事件分发给父view，由父view判断消耗多少
     *
     * @param dx             水平方向嵌套滑动的子View想要变化的距离 dx<0 向右滑动 dx>0 向左滑动
     * @param dy             垂直方向嵌套滑动的子View想要变化的距离 dy<0 向下滑动 dy>0 向上滑动
     * @param consumed       子view传给父view数组，用于存储父view水平与竖直方向上消耗的距离，consumed[0] 水平消耗的距离，consumed[1] 垂直消耗的距离
     * @param offsetInWindow 子view在当前window的偏移量
     * @return 如果返回true, 表示父view已经消耗了
     */
    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow) {
        return mScrollingChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }


    /**
     * 当父view消耗事件后，子view处理后，又继续将事件分发给父view,由父view判断是否消耗剩下的距离。
     *
     * @param dxConsumed     水平方向嵌套滑动的子View滑动的距离(消耗的距离)
     * @param dyConsumed     垂直方向嵌套滑动的子View滑动的距离(消耗的距离)
     * @param dxUnconsumed   水平方向嵌套滑动的子View未滑动的距离(未消耗的距离)
     * @param dyUnconsumed   垂直方向嵌套滑动的子View未滑动的距离(未消耗的距离)
     * @param offsetInWindow 子view在当前window的偏移量
     * @return 如果返回true, 表示父view又继续消耗了
     */
    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow) {
        return mScrollingChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    /**
     * 子view停止嵌套滑动
     */
    @Override
    public void stopNestedScroll() {
        mScrollingChildHelper.stopNestedScroll();
    }


    /**
     * 当子view产生fling滑动时，判断父view是否处拦截fling，如果父View处理了fling，那子view就没有办法处理fling了。
     *
     * @param velocityX 水平方向上的速度 velocityX > 0  向左滑动，反之向右滑动
     * @param velocityY 竖直方向上的速度 velocityY > 0  向上滑动，反之向下滑动
     * @return 如果返回true, 表示父view拦截了fling
     */
    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return mScrollingChildHelper.dispatchNestedPreFling(velocityX, velocityY);
    }

    /**
     * 当父view不拦截子view的fling,那么子view会调用该方法将fling，传给父view进行处理
     *
     * @param velocityX 水平方向上的速度 velocityX > 0  向左滑动，反之向右滑动
     * @param velocityY 竖直方向上的速度 velocityY > 0  向上滑动，反之向下滑动
     * @param consumed  子view是否可以消耗该fling，也可以说是子view是否消耗掉了该fling
     * @return 父view是否消耗了该fling
     */
    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return mScrollingChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    /**
     * 设置当前子view是否支持嵌套滑动，如果不支持，那么父view是不能够响应嵌套滑动的
     *
     * @param enabled true 支持
     */
    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        mScrollingChildHelper.setNestedScrollingEnabled(enabled);
    }

    /**
     * 当前子view是否支持嵌套滑动
     */
    @Override
    public boolean isNestedScrollingEnabled() {
        return mScrollingChildHelper.isNestedScrollingEnabled();
    }

    /**
     * 判断当前子view是否拥有嵌套滑动的父view
     */
    @Override
    public boolean hasNestedScrollingParent() {
        return mScrollingChildHelper.hasNestedScrollingParent();
    }

}