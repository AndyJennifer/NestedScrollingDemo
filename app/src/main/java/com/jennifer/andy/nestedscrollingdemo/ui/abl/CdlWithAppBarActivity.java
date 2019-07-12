package com.jennifer.andy.nestedscrollingdemo.ui.abl;

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
 * Description:coordinatorLayout与AppBarLayout的使用
 */

public class CdlWithAppBarActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cdl_with_appbar);
        initView();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new SimpleStringAdapter(initStrings(), this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private List<String> initStrings() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("简单文本" + i);
        }
        return list;
    }
}
