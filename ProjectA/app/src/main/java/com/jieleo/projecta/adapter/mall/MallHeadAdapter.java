package com.jieleo.projecta.adapter.mall;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.BaseViewHolder;
import com.jieleo.projecta.bean.mall.MallHeadBean;

/**
 * Created by yuyongjie on 17/2/16.
 */


public class MallHeadAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private MallHeadBean.DataBean.ItemsBeanX itemsBean;
    private Context context;
    private static final String TAG = "MallHeadAdapter";




    public void setItemsBean(MallHeadBean.DataBean.ItemsBeanX itemsBean) {
        this.itemsBean = itemsBean;
        notifyDataSetChanged();
    }

    public MallHeadAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHolder.createViewHolder(context,parent, R.layout.item_head_first_details_mall_page);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {


        if (position==6){
            holder.setLocalImage(R.id.iv_head_first_details_mall_page,R.mipmap.icon_commodity_more);
        }else {
            holder.setCustromImage(R.id.iv_head_first_details_mall_page,itemsBean.getItems().get(position).getCover_image_url());
            holder.setText(R.id.tv_head_first_short_description_details_mall_page,itemsBean.getItems().get(position).getShort_description());
            if (itemsBean.getItems().get(position).getSkus()!=null){
                holder.setText(R.id.tv_head_first_price_details_mall_page,"¥"+itemsBean.getItems().get(position).getSkus().get(0).getFixed_price());
            }else {
                holder.setText(R.id.tv_head_first_price_details_mall_page,"");
            }
        }
    }

    @Override
    public int getItemCount() {
        return itemsBean!=null?7:0;
    }


}
