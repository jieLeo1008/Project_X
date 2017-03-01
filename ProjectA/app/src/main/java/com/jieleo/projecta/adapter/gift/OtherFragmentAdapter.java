package com.jieleo.projecta.adapter.gift;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by jie on 2017/2/28.
 */

public class OtherFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private int pos;
    private String[] title=new String[]{"单品","详情","评论"};

    public OtherFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        this.pos=position;
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
