package com.jennifer.andy.nestedscrollingdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jennifer.andy.nestedscrollingdemo.ui.abl.CdlWithAppBarActivity;
import com.jennifer.andy.nestedscrollingdemo.ui.abl.CdlWithAppBarWithCollActivity;
import com.jennifer.andy.nestedscrollingdemo.ui.cdl.CoordinatorLayoutActivity;
import com.jennifer.andy.nestedscrollingdemo.ui.nested.NestedScrolling2DemoActivity;
import com.jennifer.andy.nestedscrollingdemo.ui.nested.NestedScrollingParent2Activity;
import com.jennifer.andy.nestedscrollingdemo.ui.nested.NestedScrollingParentActivity;
import com.jennifer.andy.nestedscrollingdemo.ui.nested.NestedTraditionActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewAndSetListener();
    }

    private void findViewAndSetListener() {
        findViewById(R.id.btn_nested_scrolling_tradition).setOnClickListener(this);
        findViewById(R.id.btn_nested_scrolling).setOnClickListener(this);
        findViewById(R.id.btn_nested_scrolling2).setOnClickListener(this);
        findViewById(R.id.btn_nested_scrolling2Demo).setOnClickListener(this);
        findViewById(R.id.btn_coordinator_layout).setOnClickListener(this);
        findViewById(R.id.btn_coor_with_appbar).setOnClickListener(this);
        findViewById(R.id.btn_coor_with_appbar_with_coll).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_nested_scrolling_tradition://传统嵌套滑动
                startActivity(new Intent(this, NestedTraditionActivity.class));
                break;
            case R.id.btn_nested_scrolling://实现NestedScrollingParent机制的嵌套滑动
                startActivity(new Intent(this, NestedScrollingParentActivity.class));
                break;
            case R.id.btn_nested_scrolling2://实现NestedScrollingParent2机制的嵌套滑动
                startActivity(new Intent(this, NestedScrollingParent2Activity.class));
                break;
            case R.id.btn_nested_scrolling2Demo://嵌套滑动实际使用例子
                startActivity(new Intent(this, NestedScrolling2DemoActivity.class));
                break;
            case R.id.btn_coordinator_layout://CoordinatorLayout效果展示
                startActivity(new Intent(this, CoordinatorLayoutActivity.class));
                break;
            case R.id.btn_coor_with_appbar://CoordinatorLayout与AppBarLayout结合使用
                startActivity(new Intent(this, CdlWithAppBarActivity.class));
                break;
            case R.id.btn_coor_with_appbar_with_coll://CoordinatorLayout与AppBarLayout与CollapsingToolbarLayout 三者结合使用
                startActivity(new Intent(this, CdlWithAppBarWithCollActivity.class));
                break;
        }
    }
}
