package com.jieleo.projecta.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by yuyongjie on 17/3/2.
 */


public class ProfileFragmentAdapter extends FragmentPagerAdapter   {
    private List<Fragment> fragments;

    private String[] title=new String[]{"单品","攻略"};

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    public ProfileFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments!=null?fragments.size():0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
