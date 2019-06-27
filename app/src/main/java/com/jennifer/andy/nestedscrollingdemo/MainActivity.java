package com.jennifer.andy.nestedscrollingdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jennifer.andy.nestedscrollingdemo.ui.CoorWithAppBarActivity;
import com.jennifer.andy.nestedscrollingdemo.ui.CoorWithAppBarWithCollActivity;
import com.jennifer.andy.nestedscrollingdemo.ui.CoordinatorLayoutActivity;
import com.jennifer.andy.nestedscrollingdemo.ui.NestedScrollingActivity;
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
        Button btnCoordinatorLayout = findViewById(R.id.btn_coordinator_layout);
        Button btnCdlWithAppbar = findViewById(R.id.btn_coor_with_appbar);
        Button btnCdlWithAppbarWithColl = findViewById(R.id.btn_coor_with_appbar_with_coll);

        btnNestedTradition.setOnClickListener(this);
        btnNestedScrolling.setOnClickListener(this);
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
            case R.id.btn_nested_scrolling://nested_scrolling机制的嵌套滑动
                startActivity(new Intent(this, NestedScrollingActivity.class));
                break;
            case R.id.btn_coordinator_layout://CoordinatorLayout的使用
                startActivity(new Intent(this, CoordinatorLayoutActivity.class));
                break;
            case R.id.btn_coor_with_appbar://CoordinatorLayout与AppBarLayout结合使用
                startActivity(new Intent(this, CoorWithAppBarActivity.class));
                break;
            case R.id.btn_coor_with_appbar_with_coll://CoordinatorLayout与AppBarLayout与CollapsingToolbarLayout 三者结合使用
                startActivity(new Intent(this, CoorWithAppBarWithCollActivity.class));
                break;
        }
    }
}
