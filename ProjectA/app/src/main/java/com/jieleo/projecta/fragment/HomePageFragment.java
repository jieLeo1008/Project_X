package com.jieleo.projecta.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jieleo.projecta.R;
import com.jieleo.projecta.activity.GiftDetailsActivity;
import com.jieleo.projecta.activity.SearchActivity;
import com.jieleo.projecta.activity.WebActivity;
import com.jieleo.projecta.adapter.HomePageFragmentPageAdapter;
import com.jieleo.projecta.adapter.TestAdapter;
import com.jieleo.projecta.bean.homepage.TitleBean;
import com.jieleo.projecta.custromview.MyGridView;
import com.jieleo.projecta.inter.CallBack;
import com.jieleo.projecta.tool.NetTool;
import com.jieleo.projecta.website.WebsiteInter;

import java.util.List;

/**
 * Created by yuyongjie on 17/2/10.
 */


public class HomePageFragment extends BaseFragment {

    private ImageView popIv;

    private ViewPager vp; private TabLayout tabLayout;


    private HomePageFragmentPageAdapter homePageFragmentPageAdapter;

    private List<TitleBean.DataBean.ChannelsBean> channelsBeen;
    private PopupWindow popupWindow;

    private TextView searchTv;

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
        searchTv = (TextView) view.findViewById(R.id.tv_search_home_page);

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
                popupWindow.showAsDropDown(v,0,-55);
            }
        });
        searchTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_search_home_page:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
        }
    }

    private void showPopuWindow(){
        View popView= LayoutInflater.from(getContext()).inflate(R.layout.item_popu_window,null);
        popupWindow = new PopupWindow(getContext());
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(popView);
        popupWindow.setFocusable(true);
        //设置popupWindow没有黑边
        popupWindow.setAnimationStyle(R.anim.popup_window);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(),(Bitmap) null));
        popupWindow.setOutsideTouchable(true);
        MyGridView gridView= (MyGridView) popView.findViewById(R.id.grid_view_popup_window);
        ImageView closeIv= (ImageView) popView.findViewById(R.id.iv_close_popup_window);
        TestAdapter testAdapter=new TestAdapter(getContext());
        gridView.setAdapter(testAdapter);
        testAdapter.setChannelsBeen(channelsBeen);
        closeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                vp.setCurrentItem(position);
                popupWindow.dismiss();
            }
        });

    }
}
