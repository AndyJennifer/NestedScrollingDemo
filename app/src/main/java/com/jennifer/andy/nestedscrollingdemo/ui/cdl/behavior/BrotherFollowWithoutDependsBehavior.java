package com.jennifer.andy.nestedscrollingdemo.ui.cdl.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author:  andy.xwt
 * Date:    2019-07-11 10:53
 * Description: 不重写layoutDependsOn方法，而是在布局使用xml中使用layout_anchor来确定依赖关系
 */

public class BrotherFollowWithoutDependsBehavior extends CoordinatorLayout.Behavior<View> {

    public BrotherFollowWithoutDependsBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        child.setY(dependency.getTop() - 50);//始终在依赖控件上面50个像素
        child.setX(dependency.getX());
        return true;
    }
}
