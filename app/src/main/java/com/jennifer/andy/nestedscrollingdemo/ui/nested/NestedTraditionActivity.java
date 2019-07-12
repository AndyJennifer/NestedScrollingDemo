package com.jennifer.andy.nestedscrollingdemo.ui.nested;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.jennifer.andy.nestedscrollingdemo.R;
import com.jennifer.andy.nestedscrollingdemo.adapter.BaseFragmentItemAdapter;
import com.jennifer.andy.nestedscrollingdemo.ui.TabFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:  andy.xwt
 * Date:    2018/8/8 13:56
 * Description:使用传统机制来实现嵌套滑动
 */

public class NestedTraditionActivity extends AppCompatActivity {


    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    public static final int FRAGMENT_COUNT = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_tradition);
        findView();
        initData();
    }

    private void findView() {
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
    }

    private void initData() {
        mViewPager.setAdapter(new BaseFragmentItemAdapter(getSupportFragmentManager(), initFragments(), initTitles()));
        mViewPager.setOffscreenPageLimit(FRAGMENT_COUNT);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private List<Fragment> initFragments() {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < FRAGMENT_COUNT; i++) {
            fragments.add(TabFragment.newInstance("传统事件分发机制嵌套滑动"));
        }
        return fragments;
    }

    private List<String> initTitles() {
        List<String> titles = new ArrayList<>();
        titles.add("首页");
        titles.add("全部");
        titles.add("作者");
        titles.add("专辑");
        return titles;
    }
}
