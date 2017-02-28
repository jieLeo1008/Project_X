package com.jieleo.projecta.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.jieleo.projecta.R;
import com.jieleo.projecta.bean.mall.MallHeadBean;

import java.util.List;

/**
 * Created by yuyongjie on 17/2/25.
 */


public class SpecialRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private Context context;
    private List<MallHeadBean.DataBean.ItemsBeanX.ItemsBean> itemsBeen;

    public void setItemsBeen(List<MallHeadBean.DataBean.ItemsBeanX.ItemsBean> itemsBeen) {
        this.itemsBeen = itemsBeen;
        notifyDataSetChanged();
    }

    public SpecialRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHolder.createViewHolder(context,parent, R.layout.item_special_details);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setCustromImage(R.id.iv_head_special_details_page,itemsBeen.get(position).getCover_image_url());
        holder.setText(R.id.tv_short_description_special_page,itemsBeen.get(position).getShort_description());
        holder.setText(R.id.tv_price_special_page,"¥"+itemsBeen.get(position).getSkus().get(0).getPrice());

    }

    @Override
    public int getItemCount() {
        return itemsBeen!=null?itemsBeen.size():0;
    }
}
