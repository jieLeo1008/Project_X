package com.jieleo.projecta.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.HomePageFragmentPageAdapter;
import com.jieleo.projecta.bean.homepage.DetailsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuyongjie on 17/2/10.
 */


public class HomePageFragment extends BaseFragment {

    private ViewPager vp; private TabLayout tabLayout;

    private HomePageFragmentPageAdapter homePageFragmentPageAdapter;

    private List<DetailsBean> detailsBeen;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_page;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout_home_page_fragment);
        vp = (ViewPager) view.findViewById(R.id.vp_home_page_fragment);
        homePageFragmentPageAdapter=new HomePageFragmentPageAdapter(getFragmentManager());
    }

    @Override
    protected void initData() {
        detailsBeen=new ArrayList<>();
        String[] data=new String[]{"精选","关注","送女票","精选","关注","送女票","精选","关注","送女票"};
        for (int i = 0; i < data.length; i++) {
            DetailsBean detailsBean =new DetailsBean();
            detailsBean.setTitle(data[i]);
            detailsBeen.add(detailsBean);
        }
        homePageFragmentPageAdapter.setDetialsBeen(detailsBeen);
        vp.setAdapter(homePageFragmentPageAdapter);
        tabLayout.setupWithViewPager(vp);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setSelectedTabIndicatorHeight(2);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF2D47"));

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab=tabLayout.getTabAt(i);
            tab.setText(homePageFragmentPageAdapter.getPageTitle(i));

        }
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    public void onClick(View v) {

    }
}
