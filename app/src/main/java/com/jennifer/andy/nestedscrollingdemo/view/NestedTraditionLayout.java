package com.jennifer.andy.nestedscrollingdemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jennifer.andy.nestedscrollingdemo.R;

/**
 * Author:  andy.xwt
 * Date:    2018/8/8 14:27
 * Description:
 * 传统处理嵌套滑动的方式，如果父控件拦截，根据传统事件分发机制，如果父控件确定拦截事件，那么在同一事件序列中，子控件是没有办法获取到事件，
 * 在下面的例子中，如果是同一事件序列中滑动导致headView隐藏，那么除非手指抬起，不然子控件是不能响应事件的。
 */

public class NestedTraditionLayout extends LinearLayout {

    private View mHeadView;
    private View mNavView;
    private ViewPager mViewPager;

    private int mHeadTopHeight;
    private int mLastY;
    private boolean isHeadHide;

    private final String TAG = "NestedTraditionLayout";

    public NestedTraditionLayout(Context context) {
        this(context, null);
    }

    public NestedTraditionLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NestedTraditionLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        int action = event.getAction() & MotionEvent.ACTION_MASK;
        int y = (int) event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int dy = mLastY - y;
                //如果父控件拦截，根据传统事件传递机制，如果父控件确定拦截事件，那么在同一事件序列中，子控件是没有办法获取到事件的。
                if (Math.abs(dy) > ViewConfiguration.getTouchSlop()) {
                    if (dy > 0 && !isHeadHide) { //如果是向上滑，且当前headView没有隐藏，那么就拦截
                        Log.d(TAG, "onInterceptTouchEvent: 开始向上拦截");
                        return true;
                    } else if (dy < 0 && isHeadHide) {//如果是向下, 且将headView已经隐藏，那么就拦截
                        Log.d(TAG, "onInterceptTouchEvent: 开始向下拦截");
                        return true;
                    }
                }
                break;
        }
        return super.onInterceptTouchEvent(event);//不拦截事件，把事件让给子控件。

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction() & MotionEvent.ACTION_MASK;
        int y = (int) event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int dy = mLastY - y;
                if (Math.abs(dy) > ViewConfiguration.getTouchSlop()) {
                    scrollBy(0, dy);
                }
                mLastY = y;
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 重新scrollTo方法，因为scrollBy最终会调用，scrollTo方法
     */
    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        }
        if (y > mHeadTopHeight) {
            y = mHeadTopHeight;
        }
        super.scrollTo(x, y);
        isHeadHide = getScrollY() == mHeadTopHeight;//判断当前head是否已经隐藏了
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //ViewPager修改后的高度= 总高度-导航栏高度
        ViewGroup.LayoutParams layoutParams = mViewPager.getLayoutParams();
        layoutParams.height = getMeasuredHeight() - mNavView.getMeasuredHeight();
        mViewPager.setLayoutParams(layoutParams);
        //当ViewPager修改高度后，重新开始测量
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeadTopHeight = mHeadView.getMeasuredHeight();//获取headView高度
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mHeadView = findViewById(R.id.iv_head_image);
        mNavView = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
        if (!(mViewPager instanceof ViewPager)) {
            throw new RuntimeException("id view_pager should be viewpager!");
        }
    }


}
