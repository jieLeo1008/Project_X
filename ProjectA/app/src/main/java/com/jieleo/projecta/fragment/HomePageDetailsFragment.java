package com.jieleo.projecta.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.HomePageDetailsRecyclerViewAdapter;
import com.jieleo.projecta.bean.homepage.DetailsBean;
import com.jieleo.projecta.bean.homepage.TitleBean;
import com.jieleo.projecta.inter.CallBack;
import com.jieleo.projecta.tool.NetTool;
import com.jieleo.projecta.website.WebsiteInter;

/**
 * Created by yuyongjie on 17/2/11.
 */


public class HomePageDetailsFragment extends BaseFragment {
    private static final String TAG = "HomePageDetailsFragment";
    private RecyclerView mRecyclerView;

    private int id,position;



    private DetailsBean detailsBean;


    private HomePageDetailsRecyclerViewAdapter mDetialsRecyclerViewAdapter;

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
        position=bundle.getInt("position",0);
        String url=WebsiteInter.getHomePageDetailsUrl(id);
        mDetialsRecyclerViewAdapter = new HomePageDetailsRecyclerViewAdapter(getContext());
        NetTool.getInstance().startRequest(url, DetailsBean.class, new CallBack<DetailsBean>() {
            @Override
            public void onSuccess(DetailsBean response) {
                detailsBean = response;
                mDetialsRecyclerViewAdapter.setDetailsBean(detailsBean);
                mDetialsRecyclerViewAdapter.setId(position);

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

    public static Fragment getInstance(int position, TitleBean.DataBean.ChannelsBean channelsBean) {
        HomePageDetailsFragment homePageDetailsFragment = new HomePageDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id",channelsBean.getId());
        bundle.putInt("position",position);
        homePageDetailsFragment.setArguments(bundle);
        return homePageDetailsFragment;
    }


}
