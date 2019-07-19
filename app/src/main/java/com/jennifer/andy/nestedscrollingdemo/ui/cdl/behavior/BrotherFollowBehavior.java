package com.jennifer.andy.nestedscrollingdemo.ui.cdl.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import com.jennifer.andy.nestedscrollingdemo.view.DependedView;

/**
 * Author:  andy.xwt
 * Date:    2019-07-11 10:53
 * Description:跟随行为
 */

public class BrotherFollowBehavior extends CoordinatorLayout.Behavior<View> {

    public BrotherFollowBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof DependedView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        child.setY(dependency.getBottom() + 50);
        child.setX(dependency.getX());
        return true;
    }
}
