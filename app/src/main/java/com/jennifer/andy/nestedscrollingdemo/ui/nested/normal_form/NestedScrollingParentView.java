package com.jennifer.andy.nestedscrollingdemo.ui.nested.normal_form;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Author:  andy.xwt
 * Date:    2019-06-28 00:13
 * Description:
 */

public class NestedScrollingParentView extends LinearLayout implements NestedScrollingParent {

    private NestedScrollingParentHelper mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);

    public NestedScrollingParentView(Context context) {
        super(context);
    }

    /**
     * 有嵌套滑动到来了，判断父view是否接受嵌套滑动
     *
     * @param child            嵌套滑动对应的父类的子类(因为嵌套滑动对于的父View不一定是一级就能找到的，可能挑了两级父View的父View，child的辈分>=target)
     * @param target           具体嵌套滑动的那个子类
     * @param nestedScrollAxes 支持嵌套滚动轴。水平方向，垂直方向，或者不指定
     * @return 父view是否接受嵌套滑动， 只有接受了才会执行剩下的嵌套滑动方法
     */
    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int nestedScrollAxes) {
        return super.onStartNestedScroll(child, target, nestedScrollAxes);
    }

    /**
     * 当onStartNestedScroll返回为true时，也就是父view接受嵌套滑动时，该方法才会调用
     */
    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes) {
        mNestedScrollingParentHelper.onNestedScrollAccepted(child, target, axes);
    }

    /**
     * 在嵌套滑动的子View未滑动之前，判断父view是否优先与子view处理(也就是父view可以先消耗，然后给子view消耗）
     *
     * @param target   具体嵌套滑动的那个子类
     * @param dx       水平方向嵌套滑动的子View想要变化的距离
     * @param dy       垂直方向嵌套滑动的子View想要变化的距离 dy<0向下滑动 dy>0 向上滑动
     * @param consumed 这个参数要我们在实现这个函数的时候指定，回头告诉子View当前父View消耗的距离
     *                 consumed[0] 水平消耗的距离，consumed[1] 垂直消耗的距离 好让子view做出相应的调整
     */
    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed) {
        //自己实现
    }

    /**
     * 嵌套滑动的子View在滑动之后，判断父view是否继续处理（也就是父消耗一定距离后，子再消耗，最后判断父消耗不）
     *
     * @param target       具体嵌套滑动的那个子类
     * @param dxConsumed   水平方向嵌套滑动的子View滑动的距离(消耗的距离)
     * @param dyConsumed   垂直方向嵌套滑动的子View滑动的距离(消耗的距离)
     * @param dxUnconsumed 水平方向嵌套滑动的子View未滑动的距离(未消耗的距离)
     * @param dyUnconsumed 垂直方向嵌套滑动的子View未滑动的距离(未消耗的距离)
     */
    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    /**
     * 嵌套滑动结束
     */
    @Override
    public void onStopNestedScroll(@NonNull View child) {
        mNestedScrollingParentHelper.onStopNestedScroll(child);
    }

    /**
     * 当子view产生fling滑动时，判断父view是否处拦截fling，如果父View处理了fling，那子view就没有办法处理fling了。
     *
     * @param target    具体嵌套滑动的那个子类
     * @param velocityX 水平方向上的速度 velocityX > 0  向左滑动，反之向右滑动
     * @param velocityY 竖直方向上的速度 velocityY > 0  向上滑动，反之向下滑动
     * @return 父view是否拦截该fling
     */
    @Override
    public boolean onNestedPreFling(@NonNull View target, float velocityX, float velocityY) {
        return super.onNestedPreFling(target, velocityX, velocityY);
    }


    /**
     * 当父view不拦截该fling,那么子view会将fling传入父view
     *
     * @param target    具体嵌套滑动的那个子类
     * @param velocityX 水平方向上的速度 velocityX > 0  向左滑动，反之向右滑动
     * @param velocityY 竖直方向上的速度 velocityY > 0  向上滑动，反之向下滑动
     * @param consumed  子view是否可以消耗该fling，也可以说是子view是否消耗掉了该fling
     * @return 父view是否消耗了该fling
     */
    @Override
    public boolean onNestedFling(@NonNull View target, float velocityX, float velocityY, boolean consumed) {
        return super.onNestedFling(target, velocityX, velocityY, consumed);
    }

    /**
     * 返回当前父view嵌套滑动的方向，分为水平方向与，垂直方法，或者不变
     */
    @Override
    public int getNestedScrollAxes() {
        return mNestedScrollingParentHelper.getNestedScrollAxes();
    }
}