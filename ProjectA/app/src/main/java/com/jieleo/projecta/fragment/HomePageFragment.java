package com.jieleo.projecta.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.HomePageFragmentPageAdapter;
import com.jieleo.projecta.adapter.TestAdapter;
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

    private ImageView popIv;

    private ViewPager vp; private TabLayout tabLayout;


    private HomePageFragmentPageAdapter homePageFragmentPageAdapter;

    private List<TitleBean.DataBean.ChannelsBean> channelsBeen;
    private PopupWindow popupWindo;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_page;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout_home_page_fragment);
        vp = (ViewPager) view.findViewById(R.id.vp_home_page_fragment);
        homePageFragmentPageAdapter=new HomePageFragmentPageAdapter(getChildFragmentManager());
        popIv= (ImageView) view.findViewById(R.id.iv_pop_home_page);
        View popView= LayoutInflater.from(getContext()).inflate(R.layout.item_popu_window,null);

    }

    @Override
    protected void initData() {
        NetTool.getInstance().startRequest(WebsiteInter.CHANNELS_URL, TitleBean.class, new CallBack<TitleBean>() {
            @Override
            public void onSuccess(TitleBean response) {
                channelsBeen= response.getData().getChannels();
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
        popIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    showPopuWindow();
                popupWindo.showAsDropDown(v,0,20);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    private void showPopuWindow(){
        View popView= LayoutInflater.from(getContext()).inflate(R.layout.item_popu_window,null);
        popupWindo = new PopupWindow(getContext());
        popupWindo.setContentView(popView);
        popupWindo.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindo.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        RecyclerView recyclerView= (RecyclerView) popView.findViewById(R.id.recycler_view_popupwindow);
        TestAdapter testAdapter=new TestAdapter(getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
        recyclerView.setAdapter(testAdapter);

    }
}
