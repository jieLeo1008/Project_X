package com.jieleo.projecta.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.GiftPageFragmentAdapter;
import com.jieleo.projecta.bean.gift.GiftTitleBean;
import com.jieleo.projecta.inter.CallBack;
import com.jieleo.projecta.tool.NetTool;
import com.jieleo.projecta.website.WebsiteInter;

/**
 * Created by yuyongjie on 17/2/10.
 */


public class GiftPageFragmnet extends BaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private GiftPageFragmentAdapter giftPageFragmentAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gift_page;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout_gift_page_fragment);
        viewPager = (ViewPager) view.findViewById(R.id.vp_gift_page_fragment);
        giftPageFragmentAdapter =new GiftPageFragmentAdapter(getChildFragmentManager());
    }

    @Override
    protected void initData() {
        NetTool.getInstance().startRequest(WebsiteInter.GIFT, GiftTitleBean.class, new CallBack<GiftTitleBean>() {
            @Override
            public void onSuccess(GiftTitleBean response) {
                GiftTitleBean giftTitleBean= response;
                giftPageFragmentAdapter.setGiftTitleBean(giftTitleBean);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
        viewPager.setAdapter(giftPageFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
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
