package com.jennifer.andy.nestedscrollingdemo.ui.cdl.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.jennifer.andy.nestedscrollingdemo.ui.cdl.CoordinatorLayoutDemo4Activity;

/**
 * Author:  andy.xwt
 * Date:    2019-07-24 22:22
 * Description: 在{@link CoordinatorLayoutDemo4Activity}中RecyclerView重新布局中需要的Behavior
 */

public class ScrollingViewBehavior extends HeaderScrollingViewBehavior {


    public ScrollingViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 依赖TextView
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof TextView;
    }

}
