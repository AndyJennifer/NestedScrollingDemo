package com.jennifer.andy.nestedscrollingdemo.ui.cdl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jennifer.andy.nestedscrollingdemo.R;

/**
 * Author:  andy.xwt
 * Date:    2018/8/8 13:56
 * Description:不重写layoutDependsOn方法，而是在布局使用xml中使用layout_anchor来确定依赖关系
 */

public class CoordinatorLayoutDemo2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cdl_demo2);
    }
}
