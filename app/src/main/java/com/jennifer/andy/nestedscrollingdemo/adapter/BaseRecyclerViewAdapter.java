package com.jennifer.andy.nestedscrollingdemo.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.IntRange;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:  andy.xwt
 * Date:    2017/5/17 17:14
 * Description: 基础 RecyclerView适配器
 */

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {

    protected int mLayoutId;
    protected List<T> mData;
    protected Context mContext;
    protected View mRootView;//
    protected List<RecyclerViewHolder> mHolders = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private onItemClickListener mOnItemClickListener;
    private onItemChildClickListener mOnItemChildClickListener;

    public BaseRecyclerViewAdapter(int layoutId, List<T> data, Context context) {
        mLayoutId = layoutId;
        mData = data;
        mContext = context;
    }

    public BaseRecyclerViewAdapter(int layoutId, Context context) {
        mLayoutId = layoutId;
        mContext = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mRootView = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
        final RecyclerViewHolder holder = new RecyclerViewHolder(mRootView);
        mHolders.add(holder);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onClick(v, holder.getLayoutPosition());
                }
            }
        });
        holder.setAdapter(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        bindData(holder, mData.get(position), position);


    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    /**
     * 绑定数据
     *
     * @param holder
     * @param t        数据源
     * @param position 位置
     */
    protected abstract void bindData(RecyclerViewHolder holder, T t, int position);


    public List<T> getData() {
        return mData;
    }

    public void setData(List<T> data) {
        mData = data;
    }

    /**
     * 添加新数据(集合)
     */
    public void addData(List<T> data) {
        if (data != null && data.size() > 0) {
            mData.addAll(data);
            notifyItemRangeInserted(mData.size(), data.size());
        }
    }

    /**
     * 添加一个数据
     */
    public void addData(T data) {
        if (data != null) {
            mData.add(data);
            notifyItemInserted(mData.size());
        }
    }


    /**
     * 设置新数据
     */
    public void setNewData(List<T> data) {
        if (data != null && data.size() > 0) {
            mData = data;
            notifyDataSetChanged();
        }
    }

    /**
     * 清空数据
     */
    public void clearData() {
        mData.clear();
        notifyDataSetChanged();
    }

    /**
     * 根据位置删除数据
     */
    public void remove(@IntRange(from = 0) int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    private void checkNotNull() {
        if (getRecyclerView() == null) {
            throw new RuntimeException("please bind recyclerView first!");
        }
    }

    /**
     * 根据当前位置获取相应item中的view
     */
    public View getViewByPosition(int position, @IdRes int viewId) {
        checkNotNull();
        return getViewByPosition(getRecyclerView(), position, viewId);
    }

    /**
     * 获取当前
     */
    public <V extends View> V getViewByPosition(RecyclerView recyclerView, int position, @IdRes int viewId) {
        if (recyclerView == null) {
            return null;
        }
        RecyclerViewHolder viewHolder = (RecyclerViewHolder) recyclerView.findViewHolderForLayoutPosition(position);
        if (viewHolder == null) {
            return null;
        }
        return viewHolder.getView(viewId);
    }

    public View getRootView() {
        return mRootView;
    }


    protected RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    private void setRecyclerView(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
    }

    /**
     * item 点击事件
     */
    public interface onItemClickListener {
        void onClick(View view, int position);
    }

    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }


    /**
     * item 中子布局点击事件
     */
    public interface onItemChildClickListener {
        void onItemChildClick(View view, int position);
    }


    public onItemChildClickListener getOnItemChildClickListener() {
        return mOnItemChildClickListener;
    }

    public void setOnItemChildClickListener(onItemChildClickListener onItemChildClickListener) {
        mOnItemChildClickListener = onItemChildClickListener;
    }
}
