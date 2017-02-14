package com.jieleo.projecta.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jieleo.projecta.bean.homepage.TitleBean;
import com.jieleo.projecta.fragment.DetialHomePageFragment;

import java.util.List;

/**
 * Created by yuyongjie on 17/2/11.
 */


public class HomePageFragmentPageAdapter extends FragmentPagerAdapter{
    private List<TitleBean.DataBean.ChannelsBean> channelsBeen;
    public HomePageFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setChannelsBeen(List<TitleBean.DataBean.ChannelsBean> channelsBeen) {
        this.channelsBeen = channelsBeen;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return DetialHomePageFragment.getInstance(channelsBeen.get(position).getId());
    }

    @Override
    public int getCount() {
        return channelsBeen!=null? channelsBeen.size():0;
    }

    public  String getPageTitle(int position){
        return channelsBeen.get(position).getName();
    }

}
