package com.jieleo.projecta.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jieleo.projecta.R;

/**
 * Created by yuyongjie on 17/3/2.
 */


public class ProfileStrategyFragment extends BaseFragment {
    private RecyclerView recyclerView;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile_strategy_page;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_profile_strategy_page);
    }

    @Override
    protected void initData() {
        //TODO 完成recyclerview
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    public void onClick(View v) {

    }
}
