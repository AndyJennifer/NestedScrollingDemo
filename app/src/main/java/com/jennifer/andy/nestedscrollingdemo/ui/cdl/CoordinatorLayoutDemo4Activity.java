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

public class CoordinatorLayoutDemo4Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cdl_demo4);
        initView();
    }


    private void initView() {
        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new SimpleStringAdapter(initStrings(), this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        /**
         * 如果你在CoordinatorLayout结合RecyclerView使用了嵌套滑动效果。那么你会发现，当我们使用recyclerView.smoothScrollToPosition(0)时，
         * 之前设置的嵌套滑动效果会失效。也就是RecyclerView只会滚动到顶部。而RecyclerView
         * 所依赖的控件，它的嵌套滑动是没有出来的。如果你想看一下我的解决方法。可以将R.layout.activity_cdl_demo4布局中的注释解开。
         * 并查看NestedHeaderBehavior类中的onStopNestedScroll方法的处理。
         */
//        findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                recyclerView.startNestedScroll(View.SCROLL_AXIS_VERTICAL, ViewCompat.TYPE_NON_TOUCH);
//                recyclerView.smoothScrollToPosition(0);
//            }
//        });
    }

    private List<String> initStrings() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i + "--->Behavior的嵌套滑动");
        }
        return list;
    }


}
