package com.jieleo.projecta.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by yuyongjie on 17/2/10.
 */


public class MainFragmentPageAdapter extends FragmentPagerAdapter {
    private List<Fragment> mfragments;

    public MainFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setMfragments(List<Fragment> mfragments) {
        this.mfragments = mfragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mfragments.get(position);
    }

    @Override
    public int getCount() {
        return mfragments.size();
    }
}
