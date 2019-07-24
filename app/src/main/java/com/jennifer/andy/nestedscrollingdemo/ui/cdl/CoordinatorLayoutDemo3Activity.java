package com.jennifer.andy.nestedscrollingdemo.ui.cdl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jennifer.andy.nestedscrollingdemo.R;
import com.jennifer.andy.nestedscrollingdemo.adapter.SimpleStringAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:  andy.xwt
 * Date:    2018/8/8 13:56
 * Description:Behavior嵌套滑动效果。
 * 因为要重新设置Recycler的位置，所以说需要自定义Behavior{@link com.jennifer.andy.nestedscrollingdemo.ui.cdl.behavior.ScrollingViewBehavior}
 */

public class CoordinatorLayoutDemo3Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cdl_demo3);
        initView();
    }


    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new SimpleStringAdapter(initStrings(), this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private List<String> initStrings() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i + "--->Behavior的嵌套滑动");
        }
        return list;
    }


}
