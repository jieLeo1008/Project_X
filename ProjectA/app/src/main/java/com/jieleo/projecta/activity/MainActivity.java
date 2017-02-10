package com.jieleo.projecta.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.MainFragmentPageAdapter;
import com.jieleo.projecta.fragment.ClassifyPageFragment;
import com.jieleo.projecta.fragment.HomePageFragment;
import com.jieleo.projecta.fragment.ListPageFragmnet;
import com.jieleo.projecta.fragment.MinePageFragment;
import com.jieleo.projecta.fragment.StorePageFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    private List<Fragment> fragments;


    private MainFragmentPageAdapter madapter;

    private ViewPager vp;
    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        vp = (ViewPager) findViewById(R.id.vp_activity_main);
        HomePageFragment homePageFragment=new HomePageFragment();
        ListPageFragmnet listPageFragmnet=new ListPageFragmnet();
        StorePageFragment storePageFragment=new StorePageFragment();
        ClassifyPageFragment classifyPageFragment=new ClassifyPageFragment();
        MinePageFragment minePageFragment=new MinePageFragment();
        fragments=new ArrayList<>();
        fragments.add(homePageFragment);
        fragments.add(listPageFragmnet);
        fragments.add(storePageFragment);
        fragments.add(classifyPageFragment);
        fragments.add(minePageFragment);
        madapter=new MainFragmentPageAdapter(getSupportFragmentManager());
        madapter.setMfragments(fragments);
        Log.e(TAG, "initView: "+fragments.size() );
        vp.setAdapter(madapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void bindEvent() {

    }
}
