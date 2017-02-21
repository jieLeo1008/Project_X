package com.jieleo.projecta.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.jieleo.projecta.R;
import com.jieleo.projecta.bean.mall.MallHeadBean;

/**
 * Created by yuyongjie on 17/2/21.
 */


public class MallPageHeadDetailsRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private MallHeadBean.DataBean.ItemsBeanX itemsBeanX;
    private Context context;

    public void setItemsBeanX(MallHeadBean.DataBean.ItemsBeanX itemsBeanX) {
        this.itemsBeanX = itemsBeanX;
        notifyDataSetChanged();
    }

    public MallPageHeadDetailsRecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHolder.createViewHolder(context,parent,R.layout.item_head_first_details_mall_page);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setCustromImage(R.id.iv_head_first_details_mall_page,itemsBeanX.getItems().get(position).getCover_image_url());
        holder.setText(R.id.tv_head_first_short_description_details_mall_page,itemsBeanX.getItems().get(position).getShort_description());
        holder.setText(R.id.tv_head_first_price_details_mall_page,"¥"+itemsBeanX.getItems().get(position).getSkus().get(0).getPrice());
    }

    @Override
    public int getItemCount() {
        return itemsBeanX!=null?itemsBeanX.getItems().size():0;
    }
}
