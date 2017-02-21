package com.jieleo.projecta.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.jieleo.projecta.R;
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


public class HomePageDetailsRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final String TAG = "DetailsRecyclerViewAdap";
    private DetailsBean detailsBean;
    private List<DetailsBean.DataBean.ItemsBean> itemsBeen;
    private Context context;

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

            DetailsBean.DataBean.ItemsBean itemsBean = itemsBeen.get(position);
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
            holder.setText(R.id.tv_likes_count_details_home_page,itemsBean.getLikes_count()+"");
            holder.setText(R.id.tv_introduction_item_details_home_page, itemsBean.getIntroduction());
            holder.setCustromImage(R.id.iv_cover_image_item_details_home_page, itemsBean.getCover_image_url());
            holder.setCircleImage(R.id.iv_author_head_item_details_home_page, itemsBean.getAuthor().getAvatar_url());
    }

    @Override
    public int getItemCount() {
        return itemsBeen != null ? itemsBeen.size() : 0;
    }


}
