package com.jennifer.andy.nestedscrollingdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button mBtnNested;
    private Button mBtnCoorWithAppbar;
    private Button mBtnCoorWithAppbarWithColl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewAndSetListener();
    }

    private void findViewAndSetListener() {
        mBtnNested = findViewById(R.id.btn_nested_scrolling);
        mBtnCoorWithAppbar = findViewById(R.id.btn_coor_with_appbar);
        mBtnCoorWithAppbarWithColl = findViewById(R.id.btn_coor_with_appbar_with_coll);
        mBtnNested.setOnClickListener(this);
        mBtnCoorWithAppbar.setOnClickListener(this);
        mBtnCoorWithAppbarWithColl.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_nested_scrolling:
                break;
            case R.id.btn_coor_with_appbar:
                break;
            case R.id.btn_coor_with_appbar_with_coll:
                break;
        }
    }
}
