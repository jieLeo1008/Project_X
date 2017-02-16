package com.jieleo.projecta.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.jieleo.projecta.R;
import com.jieleo.projecta.bean.gift.GiftDetailsBean;


/**
 * Created by yuyongjie on 17/2/15.
 */


public class GiftPageRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private GiftDetailsBean.DataBean dataBean;
    private Context context;

    public GiftPageRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setDataBean(GiftDetailsBean.DataBean dataBean) {
        this.dataBean = dataBean;
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHolder.createViewHolder(context,parent, R.layout.item_body_details_gift_page);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setCustromImage(R.id.iv_details_gift_page,dataBean.getItems().get(position).getCover_image_url());
        holder.setText(R.id.tv_short_description_details_gift_page,dataBean.getItems().get(position).getShort_description());
        holder.setText(R.id.tv_name_details_gift_page,dataBean.getItems().get(position).getName());
        holder.setText(R.id.tv_price_details_gift_page,dataBean.getItems().get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return dataBean!=null?dataBean.getItems().size():0;
    }
}
