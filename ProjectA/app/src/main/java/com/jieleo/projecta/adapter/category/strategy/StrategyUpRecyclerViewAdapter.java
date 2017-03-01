package com.jieleo.projecta.adapter.category.strategy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.BaseViewHolder;
import com.jieleo.projecta.bean.category.StrategyUpBean;
import com.jieleo.projecta.inter.OnClickListenerInter;

/**
 * Created by jie on 2017/2/16.
 */

public class StrategyUpRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final String TAG = "StrategyUpRecyclerViewA";
    private Context context;
    private StrategyUpBean strategyUpBean;

    private OnClickListenerInter onClickListenerInter;

    public void setStrategyUpBean(StrategyUpBean strategyUpBean) {
        this.strategyUpBean = strategyUpBean;
        notifyDataSetChanged();
    }

    public void setOnClickListenerInter(OnClickListenerInter onClickListenerInter) {
        this.onClickListenerInter = onClickListenerInter;
    }

    public StrategyUpRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHolder.createViewHolder(context, parent, R.layout.item_strategy_details_up_page);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        if (position==11){
            holder.setLocalImage(R.id.iv_item_strategy_details_up_page,R.mipmap.click_to_show_more);
        }else {
        holder.setCustromImage(R.id.iv_item_strategy_details_up_page, strategyUpBean.getData().getColumns().get(position).getCover_image_url());
        holder.setText(R.id.tv_title_item_strategy_up_page, strategyUpBean.getData().getColumns().get(position).getTitle());
        holder.setText(R.id.tv_author_item_strategy_up_page,strategyUpBean.getData().getColumns().get(position).getAuthor());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListenerInter.onItemClickListener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return strategyUpBean != null ? 12 : 0;
    }
}
