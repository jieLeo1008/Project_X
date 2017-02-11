package com.jieleo.projecta.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jieleo.projecta.bean.homepage.DetailsBean;
import com.jieleo.projecta.fragment.DetialHomePageFragment;

import java.util.List;

/**
 * Created by yuyongjie on 17/2/11.
 */


public class HomePageFragmentPageAdapter extends FragmentPagerAdapter{
    private List<DetailsBean>  detialsBeen;
    public HomePageFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setDetialsBeen(List<DetailsBean> detialsBeen) {
        this.detialsBeen = detialsBeen;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return DetialHomePageFragment.getInstance(detialsBeen.get(position));
    }

    @Override
    public int getCount() {
        return detialsBeen!=null?detialsBeen.size():0;
    }

    public  String getPageTitle(int position){
        return detialsBeen.get(position).getTitle();
    }

}
