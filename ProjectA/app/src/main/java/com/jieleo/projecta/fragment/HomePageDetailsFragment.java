package com.jieleo.projecta.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.jieleo.projecta.R;
import com.jieleo.projecta.activity.WebActivity;
import com.jieleo.projecta.adapter.HomePageDetailsRecyclerViewAdapter;
import com.jieleo.projecta.bean.homepage.BannerBean;
import com.jieleo.projecta.bean.homepage.DetailsBean;
import com.jieleo.projecta.bean.homepage.SecondBannerBean;
import com.jieleo.projecta.bean.homepage.TitleBean;
import com.jieleo.projecta.inter.CallBack;
import com.jieleo.projecta.tool.NetTool;
import com.jieleo.projecta.website.WebsiteInter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuyongjie on 17/2/11.
 */


public class HomePageDetailsFragment extends BaseFragment {
    private static final String TAG = "HomePageDetailsFragment";
    private LRecyclerView mLRecyclerView;
    private LRecyclerViewAdapter lRecyclerViewAdapter;

    private int id, position;

    private List<DetailsBean.DataBean.ItemsBean> itemsBeen;


    private HomePageDetailsRecyclerViewAdapter mDetialsRecyclerViewAdapter;
    private String nextUrl;
    private List<String> bannerRes;
    private BannerBean bannerBean;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_page_details;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        mLRecyclerView = (LRecyclerView) view.findViewById(R.id.recycler_view_details_home_page);

    }

    @Override
    protected void initData() {

        Bundle bundle = getArguments();
        id = bundle.getInt("id", 108);
        position = bundle.getInt("position", 0);
        String url = WebsiteInter.getHomePageDetailsUrl(id);
        mDetialsRecyclerViewAdapter = new HomePageDetailsRecyclerViewAdapter(getContext());
        NetTool.getInstance().startRequest(url, DetailsBean.class, new CallBack<DetailsBean>() {
            @Override
            public void onSuccess(DetailsBean response) {
                itemsBeen = response.getData().getItems();
                mDetialsRecyclerViewAdapter.setItemsBeen(itemsBeen);
                nextUrl = response.getData().getPaging().getNext_url();

            }


            @Override
            public void onError(Throwable e) {

            }
        });
        lRecyclerViewAdapter = new LRecyclerViewAdapter(mDetialsRecyclerViewAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mLRecyclerView.setLayoutManager(layoutManager);
        mLRecyclerView.setAdapter(lRecyclerViewAdapter);


        if (position == 0) {
            View itemHeadView = LayoutInflater.from(getContext()).inflate(R.layout.item_head_details_home_page, mLRecyclerView, false);
            final Banner banner = (Banner) itemHeadView.findViewById(R.id.banner_home_page);
            NetTool.getInstance().startRequest(WebsiteInter.BANNER, BannerBean.class, new CallBack<BannerBean>() {
                @Override
                public void onSuccess(BannerBean response) {
                    bannerBean = response;
                    bannerRes = new ArrayList<>();
                    for (int i = 0; i < bannerBean.getData().getBanners().size(); i++) {
                        bannerRes.add(bannerBean.getData().getBanners().get(i).getImage_url());
                    }
                    banner.setImageLoader(new ImageLoader());
                    banner.setImages(bannerRes);
                    banner.isAutoPlay(true);
                    banner.setDelayTime(3000);
                    banner.setIndicatorGravity(BannerConfig.CENTER);
                    banner.start();
                }

                @Override
                public void onError(Throwable e) {

                }
            });

            banner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    Intent intent = new Intent(getActivity(), WebActivity.class);
                    intent.putExtra("url", bannerRes.get(position-1));
                    startActivity(intent);
                }
            });
            final ImageView firstIV, secondIv, thirdIv, fourthIv, fifthIv, sixthIv;
            firstIV = (ImageView) itemHeadView.findViewById(R.id.iv_first_head_body_details_home_page);
            secondIv = (ImageView) itemHeadView.findViewById(R.id.iv_second_head_body_details_home_page);
            thirdIv = (ImageView) itemHeadView.findViewById(R.id.iv_third_head_body_details_home_page);
            fourthIv = (ImageView) itemHeadView.findViewById(R.id.iv_fourth_head_body_details_home_page);
            fifthIv = (ImageView) itemHeadView.findViewById(R.id.iv_fifth_head_body_details_home_page);
            sixthIv = (ImageView) itemHeadView.findViewById(R.id.iv_sixth_head_body_details_home_page);
            NetTool.getInstance().startRequest(WebsiteInter.MODULE, SecondBannerBean.class, new CallBack<SecondBannerBean>() {
                @Override
                public void onSuccess(SecondBannerBean response) {
                    SecondBannerBean secondBannerBean = response;
                    Glide.with(getContext()).load(secondBannerBean.getData().getSecondary_banners().get(0).getImage_url()).into(firstIV);
                    Glide.with(getContext()).load(secondBannerBean.getData().getSecondary_banners().get(1).getImage_url()).into(secondIv);
                    Glide.with(getContext()).load(secondBannerBean.getData().getSecondary_banners().get(2).getImage_url()).into(thirdIv);
                    Glide.with(getContext()).load(secondBannerBean.getData().getSecondary_banners().get(3).getImage_url()).into(fourthIv);
                    Glide.with(getContext()).load(secondBannerBean.getData().getSecondary_banners().get(4).getImage_url()).into(fifthIv);
                    Glide.with(getContext()).load(secondBannerBean.getData().getSecondary_banners().get(5).getImage_url()).into(sixthIv);

                }

                @Override
                public void onError(Throwable e) {

                }
            });
            firstIV.setOnClickListener(this);
            secondIv.setOnClickListener(this);
            thirdIv.setOnClickListener(this);
            fourthIv.setOnClickListener(this);
            fifthIv.setOnClickListener(this);
            sixthIv.setOnClickListener(this);

            lRecyclerViewAdapter.addHeaderView(itemHeadView);


        }
        mLRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (nextUrl != null) {
                    NetTool.getInstance().startRequest(nextUrl, DetailsBean.class, new CallBack<DetailsBean>() {
                        @Override
                        public void onSuccess(DetailsBean response) {
                            nextUrl = response.getData().getPaging().getNext_url();

                            itemsBeen.addAll(response.getData().getItems());
                            mDetialsRecyclerViewAdapter.notifyDataSetChanged();
                            mLRecyclerView.setNoMore(false);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
                } else {
                    mLRecyclerView.setNoMore(true);
                }
            }
        });
    }

    class ImageLoader extends com.youth.banner.loader.ImageLoader {


        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }

    }

    @Override
    protected void bindEvent() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_first_head_body_details_home_page:
                break;
            case R.id.iv_second_head_body_details_home_page:
                break;
            case R.id.iv_third_head_body_details_home_page:
                break;
            case R.id.iv_fourth_head_body_details_home_page:
                break;
            case R.id.iv_fifth_head_body_details_home_page:
                break;
            case R.id.iv_sixth_head_body_details_home_page:
                break;

        }
    }

    public static Fragment getInstance(int position, TitleBean.DataBean.ChannelsBean channelsBean) {
        HomePageDetailsFragment homePageDetailsFragment = new HomePageDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", channelsBean.getId());
        bundle.putInt("position", position);
        homePageDetailsFragment.setArguments(bundle);
        return homePageDetailsFragment;
    }


}
