package com.jennifer.andy.nestedscrollingdemo.normal_form;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingChild2;
import android.support.v4.view.NestedScrollingChildHelper;
import android.view.View;

/**
 * Author:  andy.xwt
 * Date:    2019-06-28 00:12
 * Description:
 */

public class NestedScrollingChild2View extends View implements NestedScrollingChild2 {

    private NestedScrollingChildHelper mScrollingChildHelper = new NestedScrollingChildHelper(this);

    public NestedScrollingChild2View(Context context) {
        super(context);
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

}