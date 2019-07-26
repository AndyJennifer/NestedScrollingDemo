package com.jennifer.andy.nestedscrollingdemo.ui.cdl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jennifer.andy.nestedscrollingdemo.R;

/**
 * Author:  andy.xwt
 * Date:    2018/8/8 13:56
 * Description:
 * 不重写layoutDependsOn方法，而是在布局使用xml中使用layout_anchor来确定依赖关系
 * 具体为什么可以这样使用，请查看{@link android.support.design.widget.CoordinatorLayout#onChildViewsChanged(int)}方法中
 *
 *              for (int j = 0; j < i; j++) {
 *                 final View checkChild = mDependencySortedChildren.get(j);
 *
 *                 if (lp.mAnchorDirectChild == checkChild) {//这里
 *                     offsetChildToAnchor(child, layoutDirection);//与这里
 *                 }
 *             }
 */

public class CoordinatorLayoutDemo2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cdl_demo2);
    }
}
