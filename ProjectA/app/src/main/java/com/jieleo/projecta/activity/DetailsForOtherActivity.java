package com.jieleo.projecta.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.gift.OtherFragmentAdapter;
import com.jieleo.projecta.bean.gift.GiftDetailsBean;
import com.jieleo.projecta.fragment.OtherCommentsFragment;
import com.jieleo.projecta.fragment.OtherDetailsFragment;
import com.jieleo.projecta.fragment.OtherSingleFragment;

import java.util.ArrayList;
import java.util.List;

public class DetailsForOtherActivity extends BaseActivity {
    private GiftDetailsBean.DataBean.ItemsBean itemsBean;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragments;
    private OtherFragmentAdapter adapter;

    @Override
    public int setLayout() {
        return R.layout.activity_details_for_other;
    }

    @Override
    protected void initView() {
        tabLayout=bindView(R.id.tab_layout_details_others);
        viewPager=bindView(R.id.vp_details_for_others);
    }

    @Override
    protected void initData() {
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("details");
        itemsBean=bundle.getParcelable("itemsDetails");
        fragments=new ArrayList<>();
        fragments.add(new OtherSingleFragment());
        fragments.add(new OtherDetailsFragment());
        fragments.add(new OtherCommentsFragment());
        adapter=new OtherFragmentAdapter(getSupportFragmentManager());
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(adapter);
        adapter.setFragments(fragments);

    }

    @Override
    protected void bindEvent() {

    }
}
