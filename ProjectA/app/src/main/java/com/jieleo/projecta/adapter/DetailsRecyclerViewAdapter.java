package com.jieleo.projecta.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.jieleo.projecta.R;
import com.jieleo.projecta.bean.homepage.BannerBean;
import com.jieleo.projecta.bean.homepage.DetialsBean;
import com.jieleo.projecta.bean.homepage.SecondBannerBean;
import com.jieleo.projecta.fragment.DetialHomePageFragment;
import com.jieleo.projecta.inter.CallBack;
import com.jieleo.projecta.tool.NetTool;
import com.jieleo.projecta.website.WebsiteInter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuyongjie on 17/2/13.
 */


public class DetailsRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private DetialsBean detialsBean;
    private Context context;
    private int tabId;
    private SecondBannerBean secondBannerBean;
    public static final int HEAD_VIEW = 0;
    public static final int BODY_VIEW = 1;

    public DetailsRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setDetialsBean(DetialsBean detialsBean) {
        this.detialsBean = detialsBean;
        notifyDataSetChanged();
    }

    public void setTabId(int tabId) {
        this.tabId = tabId;
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType==HEAD_VIEW&&tabId==102){
            return BaseViewHolder.createViewHolder(context, parent, R.layout.item_head_body_details_home_page);
        }else if (viewType==BODY_VIEW&&tabId==102){
            return BaseViewHolder.createViewHolder(context, parent, R.layout.item_body_details_home_page);
        }else if (viewType==HEAD_VIEW&&tabId!=102){
            return null;
        }else {
            return BaseViewHolder.createViewHolder(context, parent, R.layout.item_body_details_home_page);
        }

    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType==HEAD_VIEW&&tabId==102){
            holder.setBanner(R.id.banner_home_page, WebsiteInter.BANNER);
            NetTool.getInstance().startRequest(WebsiteInter.MODULE, SecondBannerBean.class, new CallBack<SecondBannerBean>() {
                @Override
                public void onsuccess(SecondBannerBean responce) {
                    List<String> bannerRes = new ArrayList<>();
                    secondBannerBean = responce;
                    for (int i = 0; i < 6; i++) {
                        bannerRes.add(secondBannerBean.getData().getSecondary_banners().get(i).getImage_url());
                    }
                    holder.setCustromImage(R.id.iv_first_head_body_details_home_page, bannerRes.get(0));
                    holder.setCustromImage(R.id.iv_second_head_body_details_home_page, bannerRes.get(1));
                    holder.setCustromImage(R.id.iv_third_head_body_details_home_page, bannerRes.get(2));
                    holder.setCustromImage(R.id.iv_fourth_head_body_details_home_page, bannerRes.get(3));
                    holder.setCustromImage(R.id.iv_fifth_head_body_details_home_page, bannerRes.get(4));
                    holder.setCustromImage(R.id.iv_sixth_head_body_details_home_page, bannerRes.get(5));
                }

                @Override
                public void onError(Throwable e) {

                }
            });
        }else if (itemViewType==BODY_VIEW&&tabId==102){
            DetialsBean.DataBean.ItemsBean itemsBean = detialsBean.getData().getItems().get(position - 1);
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
            holder.setText(R.id.tv_introduction_item_details_home_page, itemsBean.getIntroduction());
            holder.setCustromImage(R.id.iv_cover_image_item_details_home_page, itemsBean.getCover_image_url());
            holder.setCircleImage(R.id.iv_author_head_item_details_home_page, itemsBean.getAuthor().getAvatar_url());

        }else if (itemViewType==BODY_VIEW&&tabId!=102){
            DetialsBean.DataBean.ItemsBean itemsBean = detialsBean.getData().getItems().get(position );
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
            holder.setText(R.id.tv_introduction_item_details_home_page, itemsBean.getIntroduction());
            holder.setCustromImage(R.id.iv_cover_image_item_details_home_page, itemsBean.getCover_image_url());
            holder.setCircleImage(R.id.iv_author_head_item_details_home_page, itemsBean.getAuthor().getAvatar_url());
        }
    }

    @Override
    public int getItemCount() {
        return detialsBean != null ? detialsBean.getData().getItems().size() + 1 : 0;
    }


    @Override
    public int getItemViewType(int position) {
        if(tabId==102){
            if (position == 0) {
                return HEAD_VIEW;
            } else {
                return BODY_VIEW;
            }
        }else{
            return BODY_VIEW;
        }

    }


}
