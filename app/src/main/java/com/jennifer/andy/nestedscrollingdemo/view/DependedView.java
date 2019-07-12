package com.jennifer.andy.nestedscrollingdemo.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Author:  andy.xwt
 * Date:    2019-07-11 10:42
 * Description: 被依赖的TextView,当该view的位置发生改变的时候，那么其他依赖DependedView都会发生改变
 */

public class DependedView extends View {

    private float mLastX;
    private float mLastY;
    private final int mDragSlop;//最小的滑动距离


    public DependedView(Context context) {
        this(context, null);
    }

    public DependedView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DependedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDragSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastX = event.getX();
                mLastY = event.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                int dx = (int) (event.getX() - mLastX);
                int dy = (int) (event.getY() - mLastY);
                if (Math.abs(dx) > mDragSlop || Math.abs(dy) > mDragSlop) {
                    System.out.println("dx--->" + dx + "dy--->" + dy + "--->" + mDragSlop);
                    ViewCompat.offsetTopAndBottom(this, dy);
                    ViewCompat.offsetLeftAndRight(this, dx);
                }
                mLastX = event.getX();
                mLastY = event.getY();
                break;

            default:
                break;

        }

        return true;
    }
}
