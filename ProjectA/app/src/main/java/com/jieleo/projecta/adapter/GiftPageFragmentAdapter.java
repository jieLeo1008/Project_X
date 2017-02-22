package com.jieleo.projecta.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.jieleo.projecta.bean.gift.GiftTitleBean;
import com.jieleo.projecta.fragment.GiftDetailsPageFragment;

/**
 * Created by yuyongjie on 17/2/15.
 */


public class GiftPageFragmentAdapter extends FragmentPagerAdapter {
    private static final String TAG = "GiftPageFragmentAdapter";
    private GiftTitleBean giftTitleBean;
    public GiftPageFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setGiftTitleBean(GiftTitleBean giftTitleBean) {
        this.giftTitleBean = giftTitleBean;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return GiftDetailsPageFragment.newInstance(giftTitleBean.getData().getRanks().get(position));
    }

    @Override
    public int getCount() {
        return giftTitleBean!=null?giftTitleBean.getData().getRanks().size():0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return giftTitleBean.getData().getRanks().get(position).getName();
    }
}
