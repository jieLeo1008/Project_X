package com.jieleo.projecta.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.DetailsRecyclerViewAdapter;
import com.jieleo.projecta.bean.homepage.BannerBean;
import com.jieleo.projecta.bean.homepage.DetialsBean;
import com.jieleo.projecta.inter.CallBack;
import com.jieleo.projecta.tool.NetTool;
import com.jieleo.projecta.website.WebsiteInter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuyongjie on 17/2/11.
 */


public class DetialHomePageFragment extends BaseFragment {
    private static final String TAG = "DetialHomePageFragment";
    private RecyclerView mRecyclerView;

    private int id;



    private DetialsBean detialsBean;


    private DetailsRecyclerViewAdapter mDetialsRecyclerViewAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_page_details;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_details_home_page);

    }

    @Override
    protected void initData() {

        Bundle bundle=getArguments();
        id=bundle.getInt("id",108);
        String url=WebsiteInter.getUrl(id);
        mDetialsRecyclerViewAdapter = new DetailsRecyclerViewAdapter(getContext());
        NetTool.getInstance().startRequest(url, DetialsBean.class, new CallBack<DetialsBean>() {
            @Override
            public void onsuccess(DetialsBean responce) {
                detialsBean = responce;
                mDetialsRecyclerViewAdapter.setDetialsBean(detialsBean);
                mDetialsRecyclerViewAdapter.setTabId(id);
            }


            @Override
            public void onError(Throwable e) {

            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mDetialsRecyclerViewAdapter);

    }

    @Override
    protected void bindEvent() {
    }

    @Override
    public void onClick(View v) {

    }

    public static Fragment getInstance(int id) {
        DetialHomePageFragment detialHomePageFragment = new DetialHomePageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);
        detialHomePageFragment.setArguments(bundle);
        return detialHomePageFragment;
    }


}
