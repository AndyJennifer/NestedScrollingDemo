package com.jennifer.andy.nestedscrollingdemo.ui.cdl.behavior;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import com.jennifer.andy.nestedscrollingdemo.view.DependedView;

/**
 * Author:  andy.xwt
 * Date:    2019-07-11 10:53
 * Description:变色行为
 */

public class BrotherChameleonBehavior extends CoordinatorLayout.Behavior<View> {

    private ArgbEvaluator mArgbEvaluator = new ArgbEvaluator();

    public BrotherChameleonBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof DependedView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        int color = (int) mArgbEvaluator.evaluate(dependency.getY() / parent.getHeight(), Color.WHITE, Color.BLACK);
        child.setBackgroundColor(color);
        return false;
    }
}
