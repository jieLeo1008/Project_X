package com.jieleo.projecta.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.jieleo.projecta.R;
import com.jieleo.projecta.bean.greendao.Search;
import com.jieleo.projecta.tool.SearchTool;

import java.util.List;

/**
 * Created by yuyongjie on 17/3/4.
 */


public class SearchBodyAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private Context context;
    private List<Search> searchs;

    public SearchBodyAdapter(Context context) {
        this.context = context;
    }

    public void setSearchs(List<Search> searchs) {
        this.searchs = searchs;
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHolder.createViewHolder(context,parent, R.layout.item_search_body);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        holder.setText(R.id.tv_name_search,searchs.get(position).getName());
        holder.getView(R.id.iv_delete_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchTool.getInstance().deleteByName(searchs.get(position).getName());
                context.sendBroadcast(new Intent("notifyData"));
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchs!=null?searchs.size():0;
    }
}
