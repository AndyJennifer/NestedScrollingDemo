package com.jennifer.andy.nestedscrollingdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jennifer.andy.nestedscrollingdemo.ui.CdlWithAppBarActivity;
import com.jennifer.andy.nestedscrollingdemo.ui.CdlWithAppBarWithCollActivity;
import com.jennifer.andy.nestedscrollingdemo.ui.CoordinatorLayoutActivity;
import com.jennifer.andy.nestedscrollingdemo.ui.NestedScrollingParent2Activity;
import com.jennifer.andy.nestedscrollingdemo.ui.NestedScrolling2DemoActivity;
import com.jennifer.andy.nestedscrollingdemo.ui.NestedScrollingParentActivity;
import com.jennifer.andy.nestedscrollingdemo.ui.NestedTraditionActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewAndSetListener();
    }

    private void findViewAndSetListener() {
        Button btnNestedTradition = findViewById(R.id.btn_nested_scrolling_tradition);
        Button btnNestedScrolling = findViewById(R.id.btn_nested_scrolling);
        Button btnNestedScrolling2 = findViewById(R.id.btn_nested_scrolling2);
        Button btnNestedScrolling2Demo = findViewById(R.id.btn_nested_scrolling2Demo);
        Button btnCoordinatorLayout = findViewById(R.id.btn_coordinator_layout);
        Button btnCoordinatorDemo = findViewById(R.id.btn_coordinator_demo);
        Button btnCdlWithAppbar = findViewById(R.id.btn_coor_with_appbar);
        Button btnCdlWithAppbarWithColl = findViewById(R.id.btn_coor_with_appbar_with_coll);

        btnNestedTradition.setOnClickListener(this);
        btnNestedScrolling.setOnClickListener(this);
        btnNestedScrolling2.setOnClickListener(this);
        btnNestedScrolling2Demo.setOnClickListener(this);
        btnCoordinatorDemo.setOnClickListener(this);
        btnCoordinatorLayout.setOnClickListener(this);
        btnCdlWithAppbar.setOnClickListener(this);
        btnCdlWithAppbarWithColl.setOnClickListener(this);
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
            case R.id.btn_coordinator_demo://CoordinatorLayout的Demo

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
