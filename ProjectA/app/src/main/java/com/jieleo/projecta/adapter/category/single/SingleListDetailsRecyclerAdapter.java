package com.jieleo.projecta.adapter.category.single;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.BaseViewHolder;
import com.jieleo.projecta.bean.category.SingleBean;
import com.jieleo.projecta.bean.category.SingleListDetailsBean;

import java.util.List;

/**
 * Created by yuyongjie on 17/2/24.
 */


public class SingleListDetailsRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private List<SingleListDetailsBean.DataBean.ItemsBean> itemsBeen;
    private Context context;

    public SingleListDetailsRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setItemsBeen(List<SingleListDetailsBean.DataBean.ItemsBean> itemsBeen) {
        this.itemsBeen = itemsBeen;
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHolder.createViewHolder(context,parent, R.layout.item_body_details_gift_page);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setCustromImage(R.id.iv_details_gift_page,itemsBeen.get(position).getCover_image_url());
        holder.setText(R.id.tv_short_description_details_gift_page,itemsBeen.get(position).getShort_description());
        holder.setText(R.id.tv_name_details_gift_page,itemsBeen.get(position).getName());
        holder.setText(R.id.tv_price_details_gift_page,itemsBeen.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return itemsBeen!=null?itemsBeen.size():0;
    }
}
