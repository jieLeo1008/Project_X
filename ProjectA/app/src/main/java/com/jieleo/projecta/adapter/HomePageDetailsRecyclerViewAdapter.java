package com.jieleo.projecta.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
    private Context context;
    private int id;
    private SecondBannerBean secondBannerBean;
    public static final int HEAD_VIEW = 0;
    public static final int BODY_VIEW = 1;

    public HomePageDetailsRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setDetailsBean(DetailsBean detailsBean) {
        this.detailsBean = detailsBean;
        notifyDataSetChanged();
    }



    public void setId(int id) {
        this.id = id;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType==HEAD_VIEW&&id==0){
            return BaseViewHolder.createViewHolder(context, parent, R.layout.item_head_details_home_page);
        }else if (viewType==BODY_VIEW&&id==0){
            return BaseViewHolder.createViewHolder(context, parent, R.layout.item_body_details_home_page);
        }else if (viewType==HEAD_VIEW&&id!=0){
            return null;
        }else {
            return BaseViewHolder.createViewHolder(context, parent, R.layout.item_body_details_home_page);
        }

    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType==HEAD_VIEW&&id==0){
            holder.setBanner(R.id.banner_home_page, WebsiteInter.BANNER);
            NetTool.getInstance().startRequest(WebsiteInter.MODULE, SecondBannerBean.class, new CallBack<SecondBannerBean>() {
                @Override
                public void onSuccess(SecondBannerBean response) {
                    List<String> bannerRes = new ArrayList<>();
                    secondBannerBean = response;
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

        }else if (itemViewType==BODY_VIEW&&id==0){
            DetailsBean.DataBean.ItemsBean itemsBean = detailsBean.getData().getItems().get(position-1);
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

        }else if (itemViewType==BODY_VIEW&&id!=0){
            DetailsBean.DataBean.ItemsBean itemsBean = detailsBean.getData().getItems().get(position );
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
        return detailsBean != null ? detailsBean.getData().getItems().size() + 1 : 0;
    }


    @Override
    public int getItemViewType(int position) {
        if(id==0){
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
