package com.jennifer.andy.nestedscrollingdemo.ui.cdl.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

/**
 * Author:  andy.xwt
 * Date:    2019-07-22 23:24
 * Description: 处理嵌套滑动的Behavior,仿照{@link android.support.design.widget.BottomSheetBehavior}
 * 对嵌套滑动相关方法不熟悉的的小伙伴可以查看{@link com.jennifer.andy.nestedscrollingdemo.ui.nested.normal_form.NestedScrollingParent2View}
 * 其实这里可以使用android.support.design.widget.ViewOffsetHelper,熟悉的小伙伴可以自己改造。
 */

public class NestedHeaderBehavior extends CoordinatorLayout.Behavior<View> {


    private WeakReference<View> mNestedScrollingChildRef;

    public static final String TAG = "NestedHeaderBehavior";

    private int mOffset;//记录当前布局的偏移量

    public NestedHeaderBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        mNestedScrollingChildRef = new WeakReference<>(findScrollingChild(parent));
        return super.onLayoutChild(parent, child, layoutDirection);
    }


    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        //只要竖直方向上就拦截
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }


    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        View scrollingChild = mNestedScrollingChildRef.get();
        if (target != scrollingChild) {
            return;
        }
        int currentTop = child.getTop();
        int newTop = currentTop - dy;
        if (dy > 0) {//向上滑动
            //处理在范围内的滚动与fling
            if (newTop >= -child.getHeight()) {
                Log.i(TAG, "onNestedPreScroll:向上移动" + "currentTop--->" + currentTop + " newTop--->" + newTop);
                consumed[1] = dy;
                mOffset = -dy;
                ViewCompat.offsetTopAndBottom(child, -dy);
                coordinatorLayout.dispatchDependentViewsChanged(child);
            } else { //当超过后，单独处理
                consumed[1] = child.getHeight() + currentTop;
                mOffset = -consumed[1];
                ViewCompat.offsetTopAndBottom(child, -consumed[1]);
                coordinatorLayout.dispatchDependentViewsChanged(child);
            }
        }
        if (dy < 0) {//向下滑动
            if (newTop <= 0 && !target.canScrollVertically(-1)) {
                Log.i(TAG, "onNestedPreScroll:向下移动" + "currentTop--->" + currentTop + " newTop--->" + newTop);
                consumed[1] = dy;
                mOffset = -dy;
                ViewCompat.offsetTopAndBottom(child, -dy);
                coordinatorLayout.dispatchDependentViewsChanged(child);
            }
        }

    }


    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        if (dyUnconsumed < 0) {//表示已经向下滑动到头。
            int currentTop = child.getTop();
            int newTop = currentTop - dyUnconsumed;
            if (newTop <= 0) {
                Log.i(TAG, "onNestedScroll: " + "dyUnconsumed--> " + dyUnconsumed + " currentTop--->" + currentTop + " newTop--->" + newTop);
                ViewCompat.offsetTopAndBottom(child, -dyUnconsumed);
                mOffset = -dyUnconsumed;
            } else {//如果当前的值大于最大的偏移量，那么就直接滚动到-currentTop就行了
                ViewCompat.offsetTopAndBottom(child, -currentTop);
                mOffset = -currentTop;
            }
            coordinatorLayout.dispatchDependentViewsChanged(child);
        }

    }

    /**
     * 这里是为了解决在CoordinatorLayout下，RecyclerView调用smoothScrollToPosition导致嵌套滑动效果失效的问题
     * 如有需要可将注释打开
     */

//    @Override
//    public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int type) {
//        super.onStopNestedScroll(coordinatorLayout, child, target, type);
//        String message = type == ViewCompat.TYPE_NON_TOUCH ? "TYPE_NON_TOUCH" : "TYPE_TOUCH";
//        Log.i(TAG, "onStopNestedScroll: " + message);
//        if (type == ViewCompat.TYPE_NON_TOUCH) {
//            if (!target.canScrollVertically(-1)) {
//                ViewCompat.offsetTopAndBottom(child, -child.getTop());
//                coordinatorLayout.dispatchDependentViewsChanged(child);
//            }
//        }
//
//    }

    /**
     * 获取实现了NestedScrollingChild或NestedScrollingChild2接口的View。
     */
    private View findScrollingChild(View view) {
        if (ViewCompat.isNestedScrollingEnabled(view)) {
            return view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int i = 0, count = group.getChildCount(); i < count; i++) {
                View scrollingChild = findScrollingChild(group.getChildAt(i));
                if (scrollingChild != null) {
                    return scrollingChild;
                }
            }
        }
        return null;
    }

    /**
     * 获取当前控件的偏移量
     */
    public int getOffset() {
        return mOffset;
    }

}
