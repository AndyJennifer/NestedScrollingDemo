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
 * Date:    2019-07-08 22:11
 * Description:
 */

public class StickyNavLayout extends LinearLayout implements NestedScrollingParent2 {

    private NestedScrollingParentHelper mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
    private View mTopView;//头部view
    private View mNavView;//导航view
    private ViewPager mViewPager;//Viewpager
    private ScrollChangeListener mScrollChangeListener;
    /**
     * 父控件可以滚动的距离
     */
    private float mCanScrollDistance = 0f;

    public StickyNavLayout(Context context) {
        this(context, null);
    }

    public StickyNavLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StickyNavLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(LinearLayout.VERTICAL);
    }

    /**
     * 父控件接受嵌套滑动，不管是手势滑动还是fling 父控件都接受
     */
    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        return true;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {
        mNestedScrollingParentHelper.onNestedScrollAccepted(child, target, axes, type);
    }


    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        //如果子view欲向上滑动，则先交给父view滑动
        boolean hideTop = dy > 0 && getScrollY() < mCanScrollDistance;
        //如果子view欲向下滑动，必须要子view不能向下滑动后，才能交给父view滑动
        boolean showTop = dy < 0 && getScrollY() >= 0 && !target.canScrollVertically(-1);
        if (hideTop || showTop) {
            scrollBy(0, dy);
            consumed[1] = dy;// consumed[0] 水平消耗的距离，consumed[1] 垂直消耗的距离
        }
    }


    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        if (dyUnconsumed < 0 && type == ViewCompat.TYPE_NON_TOUCH) {//表示已经向下滑动到头，且为fling
            scrollBy(0, dyUnconsumed);
        }
    }

    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {
        mNestedScrollingParentHelper.onStopNestedScroll(target, type);
    }

    /**
     * 嵌套滑动时，如果父View处理了fling,那子view就没有办法处理fling了，所以这里要返回为false
     */
    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return false;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTopView = findViewById(R.id.sl_top_view);
        mNavView = findViewById(R.id.sl_tab);
        mViewPager = findViewById(R.id.sl_viewpager);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //先测量一次
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //ViewPager修改后的高度= 总高度-导航栏高度
        ViewGroup.LayoutParams lp = mViewPager.getLayoutParams();
        lp.height = getMeasuredHeight() - mNavView.getMeasuredHeight();
        mViewPager.setLayoutParams(lp);
        //因为ViewPager修改了高度，所以需要重写测量
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mCanScrollDistance = mTopView.getMeasuredHeight() - getResources().getDimension(R.dimen.normal_title_height);
    }

    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        }
        if (y > mCanScrollDistance) {
            y = (int) mCanScrollDistance;
        }
        if (mScrollChangeListener != null) {
            mScrollChangeListener.onScroll(y / mCanScrollDistance);
        }
        if (getScrollY() != y) super.scrollTo(x, y);
    }


    @Override
    public int getNestedScrollAxes() {
        return mNestedScrollingParentHelper.getNestedScrollAxes();
    }


    public interface ScrollChangeListener {
        /**
         * 移动监听
         *
         * @param moveRatio 移动比例
         */
        void onScroll(float moveRatio);
    }

    public void setScrollChangeListener(ScrollChangeListener scrollChangeListener) {
        mScrollChangeListener = scrollChangeListener;
    }
}
