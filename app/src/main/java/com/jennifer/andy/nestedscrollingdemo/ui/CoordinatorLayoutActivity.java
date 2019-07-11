package com.jennifer.andy.nestedscrollingdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jennifer.andy.nestedscrollingdemo.R;

/**
 * Author:  andy.xwt
 * Date:    2018/8/8 13:56
 * Description:CoordinatorLayout的效果展示，也就是子控件可以通知多个控件来处理相关效果
 */

public class CoordinatorLayoutActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);
    }
}
