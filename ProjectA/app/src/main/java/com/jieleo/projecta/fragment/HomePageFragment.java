package com.jieleo.projecta.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.HomePageFragmentPageAdapter;
import com.jieleo.projecta.bean.homepage.TitleBean;
import com.jieleo.projecta.inter.CallBack;
import com.jieleo.projecta.tool.NetTool;
import com.jieleo.projecta.tool.OkHttpTool;
import com.jieleo.projecta.website.WebsiteInter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuyongjie on 17/2/10.
 */


public class HomePageFragment extends BaseFragment {

    private ViewPager vp; private TabLayout tabLayout;


    private HomePageFragmentPageAdapter homePageFragmentPageAdapter;

    private List<TitleBean.DataBean.ChannelsBean> channelsBeen;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_page;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout_home_page_fragment);
        vp = (ViewPager) view.findViewById(R.id.vp_home_page_fragment);
        homePageFragmentPageAdapter=new HomePageFragmentPageAdapter(getChildFragmentManager());
    }

    @Override
    protected void initData() {
        NetTool.getInstance().startRequest(WebsiteInter.CHANNELS_URL, TitleBean.class, new CallBack<TitleBean>() {
            @Override
            public void onsuccess(TitleBean responce) {
                channelsBeen=responce.getData().getChannels();
                homePageFragmentPageAdapter.setChannelsBeen(channelsBeen);
            }

            @Override
            public void onError(Throwable e) {

            }
        });

        vp.setAdapter(homePageFragmentPageAdapter);
        tabLayout.setupWithViewPager(vp);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setSelectedTabIndicatorHeight(3);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF2D47"));

    }

    @Override
    protected void bindEvent() {

    }

    @Override
    public void onClick(View v) {

    }
}
