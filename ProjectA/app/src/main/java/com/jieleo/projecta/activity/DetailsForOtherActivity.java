package com.jieleo.projecta.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.jieleo.projecta.MyApp;
import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.gift.OtherFragmentAdapter;
import com.jieleo.projecta.bean.gift.GiftDetailsBean;
import com.jieleo.projecta.bean.greendao.Enshrine;
import com.jieleo.projecta.fragment.OtherCommentsFragment;
import com.jieleo.projecta.fragment.OtherDetailsFragment;
import com.jieleo.projecta.fragment.OtherSingleFragment;
import com.jieleo.projecta.inter.MoveToFive;
import com.jieleo.projecta.tool.EnshirneTool;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class DetailsForOtherActivity extends BaseActivity implements OtherSingleFragment.MoveToSecond, View.OnClickListener {
    private GiftDetailsBean.DataBean.ItemsBean itemsBean;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragments;
    private OtherFragmentAdapter adapter;
    private MoveToFive moveToFive;
    private CheckBox likeCheck;
    private TextView shareTv;

    @Override
    public int setLayout() {
        return R.layout.activity_details_for_other;
    }

    @Override
    protected void initView() {
        tabLayout=bindView(R.id.tab_layout_details_others);
        viewPager=bindView(R.id.vp_details_for_others);
        likeCheck = (CheckBox) findViewById(R.id.checkbox_heart_other_single);
        shareTv = (TextView) findViewById(R.id.tv_share_other_single);
    }

    @Override
    protected void initData() {
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("details");
        itemsBean=bundle.getParcelable("itemsDetails");
        fragments=new ArrayList<>();

        OtherSingleFragment otherSingleFragment=new OtherSingleFragment();
        OtherDetailsFragment otherDetailsFragment=new OtherDetailsFragment();
        OtherCommentsFragment otherCommentsFragment=new OtherCommentsFragment();
        fragments.add(otherSingleFragment);
        fragments.add(otherDetailsFragment);
        fragments.add(otherCommentsFragment);
        adapter=new OtherFragmentAdapter(getSupportFragmentManager());
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(adapter);
        adapter.setFragments(fragments);

        if (EnshirneTool.getInstance().queryByName(itemsBean.getName())){
            likeCheck.setChecked(true);
        }else {
            likeCheck.setChecked(false);
        }


        otherSingleFragment.setArguments(bundle);
        otherDetailsFragment.setArguments(bundle);

        shareTv.setOnClickListener(this);
        otherSingleFragment.setMoveToSecond(this);
        moveToFive=otherSingleFragment;
    }

    @Override
    protected void bindEvent() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition()==0){
                    moveToFive.moveToFive();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        likeCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Enshrine enshrine=new Enshrine();
                    enshrine.setEnshireName(itemsBean.getName());
                    enshrine.setEnshrineCoverImage(itemsBean.getCover_image_url());
                    enshrine.setDetailsUrl(itemsBean.getUrl());
                    enshrine.setType(2);
                    EnshirneTool.getInstance().insert(enshrine);
                }else {
                    EnshirneTool.getInstance().deletByName(itemsBean.getName());
                }
            }
        });
    }


    @Override
    public void moveToSecond() {
        viewPager.setCurrentItem(1);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_share_other_single:
                showShare();
                break;
        }
    }


    private void showShare(){
        ShareSDK.initSDK(MyApp.getmContext());
        OnekeyShare oks=new OnekeyShare();

        oks.disableSSOWhenAuthorize();
        oks.setSite(getString(R.string.app_name));
        oks.setTitleUrl(itemsBean.getCover_image_url());
        oks.setText(itemsBean.getShort_description());
        oks.setTitle(itemsBean.getName());
        oks.show(MyApp.getmContext());
    }

}
