package com.jieleo.projecta.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.jieleo.projecta.R;
import com.jieleo.projecta.bean.HotSearch;
import com.jieleo.projecta.bean.greendao.Search;
import com.jieleo.projecta.tool.SearchTool;

import java.util.Date;
import java.util.List;

/**
 * Created by jie on 2017/3/5.
 */

public class SearchHeadRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder>{
    private Context context;
    private HotSearch hotSearche;

    public SearchHeadRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setHotSearche(HotSearch hotSearche) {
        this.hotSearche = hotSearche;
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHolder.createViewHolder(context,parent, R.layout.item_hot_search);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        holder.setText(R.id.tv_item_hot_search,hotSearche.getData().getHot_words().get(position));
        holder.getView(R.id.tv_item_hot_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=hotSearche.getData().getHot_words().get(position);
                //TODO 完成热搜字的点击时间
                Search search=new Search();
                search.setName(name);
                search.setTime(new Date().getTime());
                if (!SearchTool.getInstance().isrepeate(name)){
                    SearchTool.getInstance().insert(search);
                    context.sendBroadcast(new Intent("notifyData"));
                }else {
                    SearchTool.getInstance().deleteByName(name);
                    SearchTool.getInstance().insert(search);
                    context.sendBroadcast(new Intent("notifyData"));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotSearche!=null?hotSearche.getData().getHot_words().size():0;
    }
}
