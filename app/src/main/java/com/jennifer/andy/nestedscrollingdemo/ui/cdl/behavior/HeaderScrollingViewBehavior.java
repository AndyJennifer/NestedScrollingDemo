package com.jennifer.andy.nestedscrollingdemo.ui.cdl.behavior;

import android.content.Context;
import android.graphics.Rect;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Author:  andy.xwt
 * Date:    2019-07-12 15:39
 * Description:
 * 用于测量对应控件的高度和布局 需要配合{@link NestedHeaderBehavior}使用
 */
public class HeaderScrollingViewBehavior extends CoordinatorLayout.Behavior<View> {

    final Rect mTempRect1 = new Rect();
    final Rect mTempRect2 = new Rect();

    public static final String TAG = "HeaderScrolling";

    public HeaderScrollingViewBehavior() {
    }

    public HeaderScrollingViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onMeasureChild(CoordinatorLayout parent, View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {

        //获取当前滚动控件的测量模式
        final int childLpHeight = child.getLayoutParams().height;

        //只有当前滚动控件为match_parent/wrap_content时才重新测量其高度，因为固定高度不会出现底部空白的情况
        if (childLpHeight == ViewGroup.LayoutParams.MATCH_PARENT
                || childLpHeight == ViewGroup.LayoutParams.WRAP_CONTENT) {

            //获取当前child依赖的对象集合
            final List<View> dependencies = parent.getDependencies(child);

            final View header = findFirstDependency(dependencies);
            if (header != null) {
                if (ViewCompat.getFitsSystemWindows(header)
                        && !ViewCompat.getFitsSystemWindows(child)) {
                    // If the header is fitting system windows then we need to also,
                    // otherwise we'll get CoL's compatible measuring
                    ViewCompat.setFitsSystemWindows(child, true);

                    if (ViewCompat.getFitsSystemWindows(child)) {
                        // If the set succeeded, trigger a new layout and return true
                        child.requestLayout();
                        return true;
                    }
                }
                //获取当前父控件中可用的距离，
                int availableHeight = View.MeasureSpec.getSize(parentHeightMeasureSpec);
                if (availableHeight == 0) {

                    // If the measure spec doesn't specify a size, use the current height
                    availableHeight = parent.getHeight();
                }
                //计算当前滚动控件的高度。
                final int height = availableHeight - header.getMeasuredHeight() + getScrollRange(header);
                final int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(height,
                        childLpHeight == ViewGroup.LayoutParams.MATCH_PARENT
                                ? View.MeasureSpec.EXACTLY
                                : View.MeasureSpec.AT_MOST);

                //测量当前滚动的View的正确高度
                parent.onMeasureChild(child, parentWidthMeasureSpec,
                        widthUsed, heightMeasureSpec, heightUsed);

                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        final List<View> dependencies = parent.getDependencies(child);
        final View header = findFirstDependency(dependencies);

        if (header != null) {
            final CoordinatorLayout.LayoutParams lp =
                    (CoordinatorLayout.LayoutParams) child.getLayoutParams();
            final Rect available = mTempRect1;

            //得到依赖控件下方的坐标。
            available.set(parent.getPaddingLeft() + lp.leftMargin,
                    header.getBottom() + lp.topMargin,
                    parent.getWidth() - parent.getPaddingRight() - lp.rightMargin,
                    parent.getHeight() + header.getBottom()
                            - parent.getPaddingBottom() - lp.bottomMargin);

            //拿到上面计算的坐标后，根据当前控件在父控件中设置的gravity,重新计算并得到控件在父控件中的坐标
            final Rect out = mTempRect2;
            GravityCompat.apply(resolveGravity(lp.gravity), child.getMeasuredWidth(),
                    child.getMeasuredHeight(), available, out, layoutDirection);

            //拿到坐标后重新布局
            child.layout(out.left, out.top, out.right, out.bottom);

        } else {
            //如果没有依赖，则调用父控件来处理布局
            parent.onLayoutChild(child, layoutDirection);
        }
        return true;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        offsetChildAsNeeded(parent, child, dependency);
        return true;
    }

    private void offsetChildAsNeeded(CoordinatorLayout parent, View child, View dependency) {
        final CoordinatorLayout.Behavior behavior =
                ((CoordinatorLayout.LayoutParams) dependency.getLayoutParams()).getBehavior();
        if (behavior instanceof NestedHeaderBehavior) {
            Log.i(TAG, "offsetChildAsNeeded: " + dependency.getBottom() + "--->" + child.getTop() + "---->" + ((NestedHeaderBehavior) behavior).getOffset());
            ViewCompat.offsetTopAndBottom(child, dependency.getBottom() - child.getTop() + ((NestedHeaderBehavior) behavior).getOffset());
        }
    }

    /**
     * 从依赖集合中获取第一个
     */
    View findFirstDependency(List<View> views) {
        if (views != null && !views.isEmpty()) {
            return views.get(0);
        }
        return null;
    }

    /**
     * 矫正当前Gravity
     */
    private static int resolveGravity(int gravity) {
        return gravity == Gravity.NO_GRAVITY ? GravityCompat.START | Gravity.TOP : gravity;
    }


    /**
     * 获取当前View的滑动范围，一般情况下，为view的高度。
     * 特殊情况下，滚动范围会小于View的高度。这种一般都是折叠布局
     *
     * @param v
     * @return
     */
    int getScrollRange(View v) {
        return v.getMeasuredHeight();
    }


}
