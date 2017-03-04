package com.jieleo.projecta.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jieleo.projecta.R;
import com.jieleo.projecta.activity.SpecialActivity;
import com.jieleo.projecta.bean.mall.MallHeadBean;

/**
 * Created by yuyongjie on 17/2/21.
 */


public class MallPageHeadDetailsRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private MallHeadBean.DataBean.ItemsBeanX itemsBeanX;
    private Context context;
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public static final  int NORMAL_TYPE=0;
    public static final  int MORE_TYPE=1;




    public void setItemsBeanX(MallHeadBean.DataBean.ItemsBeanX itemsBeanX) {
        this.itemsBeanX = itemsBeanX;
        notifyDataSetChanged();
    }

    public MallPageHeadDetailsRecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case NORMAL_TYPE:
               return BaseViewHolder.createViewHolder(context,parent,R.layout.item_head_first_details_mall_page);

            case MORE_TYPE:
               return BaseViewHolder.createViewHolder(context,parent,R.layout.item_more_strategy_page);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        if (position!=6){
        holder.setCustromImage(R.id.iv_head_first_details_mall_page,itemsBeanX.getItems().get(position).getCover_image_url());
        holder.setText(R.id.tv_head_first_short_description_details_mall_page,itemsBeanX.getItems().get(position).getShort_description());
        holder.setText(R.id.tv_head_first_price_details_mall_page,"¥"+itemsBeanX.getItems().get(position).getSkus().get(0).getPrice());
        holder.getView(R.id.iv_head_first_details_mall_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        }else {
            holder.getView(R.id.iv_more_strategy_page).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, SpecialActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putParcelable("itemsBean",itemsBeanX);
                    intent.putExtra("bundle",bundle);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (itemsBeanX!=null){
            if (itemsBeanX.getItems().size()<7){
                return itemsBeanX.getItems().size();
            }else {
                return 7;
            }
        }else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position==6){
            return MORE_TYPE;
        }else {
            return NORMAL_TYPE;
        }

    }
}
