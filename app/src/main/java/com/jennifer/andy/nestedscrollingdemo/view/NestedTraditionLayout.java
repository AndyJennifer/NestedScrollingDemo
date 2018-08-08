package com.jennifer.andy.nestedscrollingdemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Author:  andy.xwt
 * Date:    2018/8/8 14:27
 * Description:传统处理嵌套滑动的方式
 */

public class NestedTraditionLayout extends LinearLayout {

    public NestedTraditionLayout(Context context) {
        super(context);
    }

    public NestedTraditionLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NestedTraditionLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
