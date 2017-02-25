package com.jieleo.projecta.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jieleo.projecta.R;
import com.jieleo.projecta.activity.SpecialActivity;
import com.jieleo.projecta.bean.mall.MallHeadBean;

/**
 * Created by yuyongjie on 17/2/21.
 */


public class MallPageHeadRecyclerAdapter extends RecyclerView.Adapter<MallPageHeadRecyclerAdapter.MyHolder> {
    private Context context;
    private MallHeadBean mallHeadBean;

    public void setMallHeadBean(MallHeadBean mallHeadBean) {
        this.mallHeadBean = mallHeadBean;
        notifyDataSetChanged();
    }



    public MallPageHeadRecyclerAdapter(Context context) {
        this.context = context;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder holder=null;
        View view = LayoutInflater.from(context).inflate(R.layout.item_head_details_mall_page,parent,false);
        holder=new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        if (position>=0&&position<=3){
            final MallHeadBean.DataBean.ItemsBeanX itemsBeanX=mallHeadBean.getData().getItems().get(position);
        Glide.with(context).load(itemsBeanX.getCover_image_url()).into(holder.imageView);
        holder.textView.setText(itemsBeanX.getTitle());
        MallPageHeadDetailsRecyclerAdapter mallPageHeadDetailsRecyclerAdapter=new MallPageHeadDetailsRecyclerAdapter(context);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        holder.recyclerView.setAdapter(mallPageHeadDetailsRecyclerAdapter);
        mallPageHeadDetailsRecyclerAdapter.setItemsBeanX(itemsBeanX);
        mallPageHeadDetailsRecyclerAdapter.setId(position);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, SpecialActivity.class);
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
        return mallHeadBean!=null?mallHeadBean.getData().getItems().size():0;
    }



    class  MyHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        RecyclerView recyclerView;
        public MyHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.iv_head_first_mall_page);
            textView = (TextView) itemView.findViewById(R.id.tv_head_first_mall_page);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_view_head_first_mall_page);
        }
    }
}
