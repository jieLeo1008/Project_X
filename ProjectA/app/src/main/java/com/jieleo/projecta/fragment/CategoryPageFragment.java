package com.jieleo.projecta.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.category.CategoryPageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuyongjie on 17/2/10.
 */


public class CategoryPageFragment extends BaseFragment {
    private TabLayout tabLayout;
    private TextView searchTv;
    private ViewPager mViewPager;
    private List<Fragment> fragments;
    private CategoryPageAdapter mAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_category_page;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        tabLayout= (TabLayout) view.findViewById(R.id.tab_layout_category_page);
        searchTv= (TextView) view.findViewById(R.id.tv_search_category_page);
        mViewPager= (ViewPager) view.findViewById(R.id.vp_category_page);
    }

    @Override
    protected void initData() {
        fragments=new ArrayList<>();
        StrategyFragment strategyFragment=new StrategyFragment();
        SingleFragment singleFragment=new SingleFragment();
        fragments.add(strategyFragment);
        fragments.add(singleFragment);
        mAdapter=new CategoryPageAdapter(getChildFragmentManager());
        tabLayout.setupWithViewPager(mViewPager);
        mViewPager.setAdapter(mAdapter);
        mAdapter.setFragments(fragments);
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    public void onClick(View v) {

    }


}
