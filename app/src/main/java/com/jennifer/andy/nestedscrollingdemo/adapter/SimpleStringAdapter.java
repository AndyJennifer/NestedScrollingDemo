package com.jennifer.andy.nestedscrollingdemo.adapter;

import android.content.Context;

import com.jennifer.andy.nestedscrollingdemo.R;

import java.util.List;

/**
 * Author:  andy.xwt
 * Date:    2018/8/8 15:55
 * Description:简单的一行文本适配器
 */

public class SimpleStringAdapter extends BaseRecyclerViewAdapter<String> {

    public SimpleStringAdapter(List<String> strs, Context context) {
        super(R.layout.item_single_text, strs, context);
    }

    @Override
    protected void bindData(RecyclerViewHolder holder, String text, int position) {
        holder.setText(R.id.tv_text, text);
    }
}
