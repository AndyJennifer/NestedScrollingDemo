package com.jennifer.andy.nestedscrollingdemo.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.util.SparseArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedHashSet;

/**
 * Author:  andy.xwt
 * Date:    2017/8/8 11:55
 * Description:
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;
    private final LinkedHashSet<Integer> childClickViewIds = new LinkedHashSet();
    private BaseRecyclerViewAdapter adapter;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        this.mViews = new SparseArray<>();
    }


    public <V extends View> V getView(@IdRes int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (V) view;
    }

    /**
     * 设置textView值
     *
     * @param viewId
     * @param text   内容
     * @return
     */
    public RecyclerViewHolder setText(@IdRes int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }


    /**
     * 设置textView值
     */
    public RecyclerViewHolder setText(@IdRes int viewId, SpannableStringBuilder text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    /**
     * 设置textVie颜色
     */
    public RecyclerViewHolder setTextColor(@IdRes int viewId, @ColorInt int color) {
        TextView tv = getView(viewId);
        tv.setTextColor(color);
        return this;
    }



    public RecyclerViewHolder setImage(@IdRes int viewId, Drawable drawable) {
        ImageView iv = getView(viewId);
        iv.setImageDrawable(drawable);
        return this;
    }


    public RecyclerViewHolder setImage(@IdRes int viewId, @DrawableRes int resId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(resId);
        return this;
    }


    public RecyclerViewHolder setCompoundDrawables(@IdRes int viewId,
                                                   @Nullable Drawable left,
                                                   @Nullable Drawable top,
                                                   @Nullable Drawable right,
                                                   @Nullable Drawable bottom) {
        TextView tv = getView(viewId);
        tv.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        return this;
    }

    /**
     * 设置背景
     *
     * @param viewId
     * @param resId
     * @return
     */
    public RecyclerViewHolder setBackgroundResource(@IdRes int viewId, @DrawableRes int resId) {
        View view = getView(viewId);
        view.setBackgroundResource(resId);
        return this;
    }

    /**
     * 设置image 为null,解决viewHolder复用问题
     *
     * @param viewId
     */
    public RecyclerViewHolder setImageBitMapNull(@IdRes int viewId) {
        ImageView iv = getView(viewId);
        iv.setImageBitmap(null);
        return this;
    }

    /**
     * 设置是否可见
     *
     * @param viewId
     * @param isVisible 是否可见
     * @return
     */
    public RecyclerViewHolder setVisible(@IdRes int viewId, boolean isVisible) {
        View tv = getView(viewId);
        tv.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        return this;
    }


    /**
     * 设置是否选中
     *
     * @param viewId
     * @param isChecked 是否选中
     * @return
     */
    public RecyclerViewHolder setChecked(@IdRes int viewId, boolean isChecked) {
        CheckBox tv = getView(viewId);
        if (tv != null) {
            tv.setChecked(isChecked);
        }
        return this;
    }


    /**
     * 设置是否可用
     */
    public RecyclerViewHolder setEnabled(@IdRes int viewId, boolean isEnabled) {
        View view = getView(viewId);
        if (view != null) {
            view.setEnabled(isEnabled);
        }
        return this;
    }

    /**
     * 设置点击事件
     */
    public RecyclerViewHolder setOnClickListener(@IdRes int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * 添加点击事件 注意添加点击事件的时候必须要设置adapter
     *
     * @see #setAdapter(BaseRecyclerViewAdapter)
     */
    public RecyclerViewHolder addOnClickListener(@IdRes int viewId) {
        this.childClickViewIds.add(viewId);
        View view = this.getView(viewId);
        if (view != null) {
            if (!view.isClickable()) {
                view.setClickable(true);
            }
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (RecyclerViewHolder.this.adapter.getOnItemChildClickListener() != null) {
                        RecyclerViewHolder.this.adapter.getOnItemChildClickListener().onItemChildClick(v, RecyclerViewHolder.this.getLayoutPosition());
                    }
                }
            });
        }
        return this;
    }

    /**
     * 设置适配器
     */
    public BaseRecyclerViewAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(BaseRecyclerViewAdapter adapter) {
        this.adapter = adapter;
    }
}