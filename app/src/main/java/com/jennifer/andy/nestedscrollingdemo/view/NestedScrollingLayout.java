package com.jennifer.andy.nestedscrollingdemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
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
    public static final String TAG = "NestedScrollingLayout";

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


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //计算headView高度
        measureChild(mHeadView, widthMeasureSpec, heightMeasureSpec);
        mHeadTopHeight = mHeadView.getHeight();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mHeadView = findViewById(R.id.iv_head_image);
    }


}
