package com.jennifer.andy.nestedscrollingdemo.ui.cdl.behavior;

import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.view.View;

/**
 * Author:  andy.xwt
 * Date:    2019-07-22 23:24
 * Description: 处理嵌套滑动的Behavior(仿照AppBarLayout)
 * 对嵌套滑动相关方法不熟悉的的小伙伴可以查看{@link com.jennifer.andy.nestedscrollingdemo.ui.nested.normal_form.NestedScrollingParent2View}
 */

public class NestedHeaderBehavior extends HeaderBehavior<View> {


    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        //只要竖直方向上就拦截
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        if (dy != 0) {
            int min, max;
            if (dy < 0) {
                //下滑
                min = 0;
                max = -child.getMeasuredHeight();
            } else {
                //上滑
                min = -child.getMeasuredHeight();
                max = 0;
            }
            if (min != max) {
                consumed[1] = scroll(coordinatorLayout, child, dy, min, max);
            }
        }
    }


    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        if (dyUnconsumed < 0) {////表示已经向下滑动到头，且为fling
            scroll(coordinatorLayout, child, dyUnconsumed, -child.getMeasuredHeight(), 0);
        }
    }

    @Override
    public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int type) {
        if (type == ViewCompat.TYPE_TOUCH) {

        }
    }
}
