package com.jieleo.projecta.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.jieleo.projecta.R;

/**
 * Created by jie on 2017/2/20.
 */

public class TestAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private Context context;

    public TestAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHolder.createViewHolder(context,parent,R.layout.item_popupwindow);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
