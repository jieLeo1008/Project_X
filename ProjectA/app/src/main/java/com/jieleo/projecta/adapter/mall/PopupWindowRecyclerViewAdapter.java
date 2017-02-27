package com.jieleo.projecta.adapter.mall;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.BaseViewHolder;
import com.jieleo.projecta.bean.gift.GiftDetailsBean;

import java.util.List;

/**
 * Created by yuyongjie on 17/2/27.
 */


public class PopupWindowRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private GiftDetailsBean.DataBean.ItemsBean.SpecsDomainsBean specsDomainsBean;

    public PopupWindowRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setSpecsDomainsBean(GiftDetailsBean.DataBean.ItemsBean.SpecsDomainsBean specsDomainsBean) {
        this.specsDomainsBean = specsDomainsBean;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder myHolder=null;
        View view= LayoutInflater.from(context).inflate(R.layout.item_popup_window_normal,parent,false);
        myHolder=new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder= (MyHolder) holder;
        myHolder.radioButton.setText(specsDomainsBean.getDomains().get(position));
    }

    @Override
    public int getItemCount() {
        return specsDomainsBean!=null?specsDomainsBean.getDomains().size():0;
    }

//    public void setSpecsDomainsBean(GiftDetailsBean.DataBean.ItemsBean.SpecsDomainsBean specsDomainsBean) {
//        this.specsDomainsBean = specsDomainsBean;
//        notifyDataSetChanged();
//    }
//
//    public PopupWindowRecyclerViewAdapter(Context context) {
//        this.context = context;
//    }
//
//    @Override
//    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return BaseViewHolder.createViewHolder(context,parent, R.layout.item_popup_window_normal);
//    }
//
//    @Override
//    public void onBindViewHolder(BaseViewHolder holder, int position) {
//        holder.setText(R.id.tv_name_details_popup_window,specsDomainsBean.getDomains().get(position));
//    }
//
//    @Override
//    public int getItemCount() {
//        return specsDomainsBean!=null?specsDomainsBean.getDomains().size():0;
//    }

    class MyHolder extends RecyclerView.ViewHolder{
        private RadioButton radioButton;
        public MyHolder(View itemView) {
            super(itemView);
            radioButton = (RadioButton) itemView.findViewById(R.id.radio_btn_name_details_popup_window);
        }
    }
}
