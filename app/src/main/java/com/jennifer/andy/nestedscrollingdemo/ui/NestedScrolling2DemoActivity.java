package com.jennifer.andy.nestedscrollingdemo.ui;

import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jennifer.andy.nestedscrollingdemo.R;
import com.jennifer.andy.nestedscrollingdemo.adapter.BaseFragmentItemAdapter;
import com.jennifer.andy.nestedscrollingdemo.view.StickyNavLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:  andy.xwt
 * Date:    2019-07-08 22:11
 * Description:
 */

public class NestedScrolling2DemoActivity extends AppCompatActivity {


    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private StickyNavLayout mStickyNavLayout;
    private ImageView mBackImageView;
    private TextView mTitleView;

    public static final int FRAGMENT_COUNT = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_scrolling2_demo);
        findView();
        initData();
    }

    private void findView() {
        mTabLayout = findViewById(R.id.sl_tab);
        mViewPager = findViewById(R.id.sl_viewpager);
        mStickyNavLayout = findViewById(R.id.sick_layout);
        mBackImageView = findViewById(R.id.iv_back);
        mTitleView = findViewById(R.id.tv_title);

        initToolBar(R.drawable.ic_action_back_black, 0);
    }

    private void initData() {
        mViewPager.setAdapter(new BaseFragmentItemAdapter(getSupportFragmentManager(), initFragments(), initTitles()));
        mTabLayout.setupWithViewPager(mViewPager);
        mStickyNavLayout.setScrollChangeListener(new StickyNavLayout.ScrollChangeListener() {
            @Override
            public void onScroll(float moveRatio) {
                initToolBar(R.drawable.ic_action_back_white, moveRatio);
            }
        });
    }

    private void initToolBar(@DrawableRes int backResId, float moveRatio) {
        mBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ArgbEvaluator argbEvaluator = new ArgbEvaluator();
        int color = (int) argbEvaluator.evaluate(moveRatio, Color.WHITE, Color.BLACK);
        Drawable wrapDrawable = DrawableCompat.wrap(getResources().getDrawable(backResId));
        DrawableCompat.setTint(wrapDrawable, color);

        mBackImageView.setImageDrawable(wrapDrawable);
        mTitleView.setAlpha(moveRatio);


    }

    private List<Fragment> initFragments() {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < FRAGMENT_COUNT; i++) {
            fragments.add(TabFragment.newInstance("NestedScrolling2Demo"));
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
