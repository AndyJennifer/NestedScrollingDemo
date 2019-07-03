package com.jennifer.andy.nestedscrollingdemo.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent2;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jennifer.andy.nestedscrollingdemo.R;

/**
 * Author:  andy.xwt
 * Date:    2018/8/8 14:28
 * Description:NestedScrolling2机制下的嵌套滑动，实现NestedScrollingParent2接口下，处理fling效果的区别
 */

public class NestedScrolling2Layout extends LinearLayout implements NestedScrollingParent2 {

    private View mTopView;
    private View mNavView;
    private View mViewPager;
    private int mTopViewHeight;


    private NestedScrollingParentHelper mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);

    public NestedScrolling2Layout(Context context) {
        this(context, null);
    }

    public NestedScrolling2Layout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NestedScrolling2Layout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
    }


    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        return true;
    }


    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {
        mNestedScrollingParentHelper.onNestedScrollAccepted(child, target, axes, type);
    }

    /**
     * 在嵌套滑动的子View未滑动之前，判断父view是否优先与子view处理(也就是父view可以先消耗，然后给子view消耗）
     *
     * @param target   具体嵌套滑动的那个子类
     * @param dx       水平方向嵌套滑动的子View想要变化的距离
     * @param dy       垂直方向嵌套滑动的子View想要变化的距离 dy<0向下滑动 dy>0 向上滑动
     * @param consumed 这个参数要我们在实现这个函数的时候指定，回头告诉子View当前父View消耗的距离
     *                 consumed[0] 水平消耗的距离，consumed[1] 垂直消耗的距离 好让子view做出相应的调整
     * @param type     滑动类型，ViewCompat.TYPE_NON_TOUCH fling效果,ViewCompat.TYPE_TOUCH 手势滑动
     */
    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        //这里不管手势滚动还是fling都处理
        boolean hideTop = dy > 0 && getScrollY() < mTopViewHeight;
        boolean showTop = dy < 0 && getScrollY() >= 0 && !target.canScrollVertically(-1);
        if (hideTop || showTop) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }


    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        //当子控件处理完后，交给父控件进行处理。
        if (dyUnconsumed < 0 && type == ViewCompat.TYPE_NON_TOUCH) {//表示已经向下滑动到头，且为fling
            scrollBy(0, dyUnconsumed);
        }

    }

    @Override
    public boolean onNestedPreFling(@NonNull View target, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {
        if (type == ViewCompat.TYPE_NON_TOUCH) {
            System.out.println("onStopNestedScroll");
        }

        mNestedScrollingParentHelper.onStopNestedScroll(target, type);
    }


    @Override
    public boolean onNestedFling(@NonNull View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
        return mNestedScrollingParentHelper.getNestedScrollAxes();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //ViewPager修改后的高度= 总高度-导航栏高度
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        ViewGroup.LayoutParams layoutParams = mViewPager.getLayoutParams();
        layoutParams.height = getMeasuredHeight() - mNavView.getMeasuredHeight();
        mViewPager.setLayoutParams(layoutParams);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTopView = findViewById(R.id.iv_head_image);
        mNavView = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
        if (!(mViewPager instanceof ViewPager)) {
            throw new RuntimeException("id view_pager should be viewpager!");
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTopViewHeight = mTopView.getMeasuredHeight();
    }

    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        }
        if (y > mTopViewHeight) {
            y = mTopViewHeight;
        }
        super.scrollTo(x, y);
    }
}
