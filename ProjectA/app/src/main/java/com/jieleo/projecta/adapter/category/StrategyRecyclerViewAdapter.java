package com.jieleo.projecta.adapter.category;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.BaseViewHolder;
import com.jieleo.projecta.bean.category.StrategyDownBean;
import com.jieleo.projecta.bean.category.StrategyUpBean;
import com.jieleo.projecta.inter.CallBack;
import com.jieleo.projecta.tool.NetTool;
import com.jieleo.projecta.website.WebsiteInter;

/**
 * Created by jie on 2017/2/16.
 */

public class StrategyRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private StrategyDownBean strategyDownBean;
    private Context context;

    public static final int UP_VIEW=0;
    public static final int DOWN_VIEW=1;


    public StrategyRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setStrategyDownBean(StrategyDownBean strategyDownBean) {
        this.strategyDownBean = strategyDownBean;
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case UP_VIEW:
                return BaseViewHolder.createViewHolder(context,parent, R.layout.item_strategy_up_page);
            case DOWN_VIEW:
                return BaseViewHolder.createViewHolder(context,parent,R.layout.item_strategy_down_page);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        int viewType=getItemViewType(position);
        switch (viewType){
            case UP_VIEW:
                View view= LayoutInflater.from(context).inflate(R.layout.item_strategy_up_page,null);
                RecyclerView recyclerView= (RecyclerView) view.findViewById(R.id.recycler_view_item_up_strategy_page);
                final StrategyUpRecyclerViewAdapter strategyUpRecyclerViewAdapter=new StrategyUpRecyclerViewAdapter(context);
                recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
                recyclerView.setAdapter(strategyUpRecyclerViewAdapter);
                NetTool.getInstance().startRequest(WebsiteInter.STRATEGY_UP_TITLE, StrategyUpBean.class, new CallBack<StrategyUpBean>() {
                    @Override
                    public void onsuccess(StrategyUpBean responce) {
                        StrategyUpBean strategyUpBean=responce;
                        strategyUpRecyclerViewAdapter.setStrategyUpBean(strategyUpBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
            case DOWN_VIEW:

        }
    }

    @Override
    public int getItemCount() {
        return strategyDownBean!=null?strategyDownBean.getData().getChannel_groups().size()+1:0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return UP_VIEW;
        }else {
            return DOWN_VIEW;
        }
    }
}
