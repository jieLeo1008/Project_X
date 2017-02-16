package com.jieleo.projecta.adapter.mall;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.BaseViewHolder;
import com.jieleo.projecta.bean.mall.MallBodyBean;

/**
 * Created by yuyongjie on 17/2/16.
 */


public class MallPageRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private MallBodyBean mallBodyBean;
    private Context context;

    public void setMallBodyBean(MallBodyBean mallBodyBean) {
        this.mallBodyBean = mallBodyBean;
        notifyDataSetChanged();
    }

    public MallPageRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder baseViewHolder = BaseViewHolder.createViewHolder(context,parent, R.layout.item_body_details_mall_page);
       // baseViewHolder.setIsRecyclable(true);
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
//        holder.setIsRecyclable(true);
        MallBodyBean.DataBean.ItemsBean itemsBean =mallBodyBean.getData().getItems().get(position);
        holder.setCustromImage(R.id.iv_body_mall_page,itemsBean.getCover_image_url());
        holder.setText(R.id.tv_body_short_description_mall_page,itemsBean.getShort_description());
        holder.setText(R.id.tv_body_title_mall_page,itemsBean.getTitle());
        if (itemsBean.getSkus().get(0).getPrice()!=null){
            holder.setText(R.id.tv_body_price_mall_page,itemsBean.getSkus().get(0).getPrice());
        }else if (itemsBean.getSkus().get(0).getFixed_price()!=null){
            holder.setText(R.id.tv_body_price_mall_page,itemsBean.getSkus().get(0).getFixed_price());
        }else {
        }
    }

    @Override
    public int getItemCount() {
        return mallBodyBean!=null?mallBodyBean.getData().getItems().size():0;
    }
}
