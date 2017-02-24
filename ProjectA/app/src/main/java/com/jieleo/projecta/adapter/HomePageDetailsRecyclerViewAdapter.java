package com.jieleo.projecta.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jieleo.projecta.R;
import com.jieleo.projecta.activity.StrategyDetailsActivity;
import com.jieleo.projecta.bean.homepage.DetailsBean;
import com.jieleo.projecta.bean.homepage.SecondBannerBean;
import com.jieleo.projecta.inter.CallBack;
import com.jieleo.projecta.tool.NetTool;
import com.jieleo.projecta.website.WebsiteInter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuyongjie on 17/2/13.
 */


public class HomePageDetailsRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> implements View.OnClickListener {
    private static final String TAG = "DetailsRecyclerViewAdap";
    private DetailsBean detailsBean;
    private List<DetailsBean.DataBean.ItemsBean> itemsBeen;
    private Context context;
    private DetailsBean.DataBean.ItemsBean itemsBean;


    public void setItemsBeen(List<DetailsBean.DataBean.ItemsBean> itemsBeen) {
        this.itemsBeen = itemsBeen;
        notifyDataSetChanged();
    }

    public HomePageDetailsRecyclerViewAdapter(Context context) {
        this.context = context;
    }





    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return BaseViewHolder.createViewHolder(context, parent, R.layout.item_body_details_home_page);


    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, int position) {

        itemsBean = itemsBeen.get(position);
            holder.setText(R.id.tv_author_nickname_item_details_home_page, itemsBean.getAuthor().getNickname());
            holder.setText(R.id.tv_author_introduction_item_details_home_page, itemsBean.getAuthor().getIntroduction());
            if (itemsBean.getColumn() != null) {
                holder.setText(R.id.tv_column_title_details_home_page, itemsBean.getColumn().getTitle());
                holder.setText(R.id.tv_column_details_home_page, "栏目");
            } else {
                holder.setText(R.id.tv_column_title_details_home_page, "");
                holder.setText(R.id.tv_column_details_home_page, "");
            }
            holder.setText(R.id.tv_title_item_details_home_page, itemsBean.getTitle());
            holder.setText(R.id.tv_likes_count_details_home_page, itemsBean.getLikes_count()+"");
            holder.setText(R.id.tv_introduction_item_details_home_page, itemsBean.getIntroduction());
            holder.setCustromImage(R.id.iv_cover_image_item_details_home_page, itemsBean.getCover_image_url());
            holder.setCircleImage(R.id.iv_author_head_item_details_home_page, itemsBean.getAuthor().getAvatar_url());

            holder.getView(R.id.tv_author_nickname_item_details_home_page).setOnClickListener(this);
            holder.getView(R.id.tv_column_title_details_home_page).setOnClickListener(this);
            holder.getView(R.id.tv_column_details_home_page).setOnClickListener(this);
            holder.getView(R.id.tv_title_item_details_home_page).setOnClickListener(this);
            holder.getView(R.id.tv_likes_count_details_home_page).setOnClickListener(this);
            holder.getView(R.id.tv_introduction_item_details_home_page).setOnClickListener(this);
            holder.getView(R.id.iv_cover_image_item_details_home_page).setOnClickListener(this);
            holder.getView(R.id.iv_author_head_item_details_home_page).setOnClickListener(this);

    }

    @Override
    public int getItemCount() {
        return itemsBeen != null ? itemsBeen.size() : 0;
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        Bundle bundle;
        switch (v.getId()) {
            case R.id.tv_author_nickname_item_details_home_page:
                break;
            case R.id.iv_author_head_item_details_home_page:
                break;
            case R.id.tv_column_title_details_home_page:
                break;
            case R.id.tv_column_details_home_page:
                break;
            case R.id.tv_likes_count_details_home_page:
                break;
            case R.id.tv_title_item_details_home_page:
                intent=new Intent(context, StrategyDetailsActivity.class);
                bundle=new Bundle();
                bundle.putParcelable("itemsBean",itemsBean);
                intent.putExtra("bundle",bundle);
                context.startActivity(intent);
                break;
            case R.id.tv_introduction_item_details_home_page:
                intent=new Intent(context, StrategyDetailsActivity.class);
                bundle=new Bundle();
                bundle.putParcelable("itemsBean",itemsBean);
                intent.putExtra("bundle",bundle);
                context.startActivity(intent);
                break;
            case R.id.iv_cover_image_item_details_home_page:
                intent=new Intent(context, StrategyDetailsActivity.class);
                bundle=new Bundle();
                bundle.putParcelable("itemsBean",itemsBean);
                intent.putExtra("bundle",bundle);
                context.startActivity(intent);
                break;
        }
    }
}
