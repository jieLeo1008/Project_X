package com.jieleo.projecta.adapter.category.strategy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.BaseViewHolder;
import com.jieleo.projecta.bean.category.StrategyDownBean;
import com.jieleo.projecta.inter.OnClickListenerInter;

/**
 * Created by yuyongjie on 17/2/17.
 */


public class StrategyDownRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final String TAG = "StrategyDownRecyclerVie";
    private StrategyDownBean.DataBean.ChannelGroupsBean channelGroupsBean;
    private Context context;
    private OnClickListenerInter onClickListenerInter;

    public void setOnClickListenerInter(OnClickListenerInter onClickListenerInter) {
        this.onClickListenerInter = onClickListenerInter;
    }

    public StrategyDownRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setChannelGroupsBean(StrategyDownBean.DataBean.ChannelGroupsBean channelGroupsBean) {
        this.channelGroupsBean = channelGroupsBean;
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHolder.createViewHolder(context,parent, R.layout.item_strategy_details_down_page);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
            holder.setCustromImage(R.id.iv_cover_image_item_details_down_strategy_page,channelGroupsBean.getChannels().get(position).getCover_image_url());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListenerInter.onItemClickListener(position);
                }
            });
    }

    @Override
    public int getItemCount() {
        if (channelGroupsBean.getChannels().size()>6){
            return 6;
        }else if (channelGroupsBean==null){
            return 0;
        }else {
            return channelGroupsBean.getChannels().size();

        }
    }
}
