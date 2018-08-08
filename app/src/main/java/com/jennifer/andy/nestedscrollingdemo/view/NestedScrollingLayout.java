package com.jennifer.andy.nestedscrollingdemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Author:  andy.xwt
 * Date:    2018/8/8 14:28
 * Description:
 */

public class NestedScrollingLayout extends LinearLayout  {

    public NestedScrollingLayout(Context context) {
        super(context);
    }

    public NestedScrollingLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NestedScrollingLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return super.onStartNestedScroll(child, target, nestedScrollAxes);
    }
}
