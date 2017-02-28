package com.jieleo.projecta.adapter.gift;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.BaseViewHolder;
import com.jieleo.projecta.bean.gift.GiftDetailsBean;
import com.jieleo.projecta.inter.OnClickListenerInter;

import java.util.List;


/**
 * Created by yuyongjie on 17/2/15.
 */


public class GiftPageRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final String TAG = "GiftPageRecyclerViewAda";
    private List<GiftDetailsBean.DataBean.ItemsBean> itemsBeen;
    private Context context;
    private OnClickListenerInter onClickListenerInter;

    public GiftPageRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setItemsBeen(List<GiftDetailsBean.DataBean.ItemsBean> itemsBeen) {
        this.itemsBeen = itemsBeen;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return 2333;
    }

    public void setOnClickListenerInter(OnClickListenerInter onClickListenerInter) {
        this.onClickListenerInter = onClickListenerInter;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHolder.createViewHolder(context,parent, R.layout.item_body_details_gift_page);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        holder.setCustromImage(R.id.iv_details_gift_page,itemsBeen.get(position).getCover_image_url());
        holder.setText(R.id.tv_short_description_details_gift_page,itemsBeen.get(position).getShort_description());
        holder.setText(R.id.tv_name_details_gift_page,itemsBeen.get(position).getName());
        if (itemsBeen.get(position).getSkus()!=null){
            holder.setText(R.id.tv_price_details_gift_page,"¥"+itemsBeen.get(position).getSkus().get(0).getFixed_price());
        }else {
            holder.setText(R.id.tv_price_details_gift_page,"¥"+itemsBeen.get(position).getPrice());
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
        return itemsBeen!=null?itemsBeen.size():0;
    }
}
