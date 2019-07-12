package com.jennifer.andy.nestedscrollingdemo.ui.cdl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jennifer.andy.nestedscrollingdemo.R;

/**
 * Author:  andy.xwt
 * Date:    2018/8/8 13:56
 * Description:CoordinatorLayout的效果展示，也就是子控件可以通知多个控件来处理相关效果
 */

public class CoordinatorLayoutActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnDemo1;
    private Button mBtnDemo2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coord_main);
        initView();
    }

    private void initView() {
        mBtnDemo1 = findViewById(R.id.btn_demo1);
        mBtnDemo2 = findViewById(R.id.btn_demo2);
        mBtnDemo1.setOnClickListener(this);
        mBtnDemo2.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_demo1://没有实现NestedScrollingChild接口下，多个view的交互效果
                startActivity(new Intent(this, CoordinatorLayoutDemo1Activity.class));
                break;
            case R.id.btn_demo2://实现了NestedScrollingChild接口下，多个view的交互效果
                startActivity(new Intent(this, CoordinatorLayoutDemo2Activity.class));
                break;
        }
    }
}
