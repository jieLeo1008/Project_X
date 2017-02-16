package com.jieleo.projecta.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.category.StrategyRecyclerViewAdapter;
import com.jieleo.projecta.bean.category.StrategyDownBean;
import com.jieleo.projecta.inter.CallBack;
import com.jieleo.projecta.tool.NetTool;
import com.jieleo.projecta.website.WebsiteInter;

/**
 * Created by jie on 2017/2/16.
 */

public class StrategyFragment extends BaseFragment {
    private static final String TAG = "StrategyFragment";
    private RecyclerView mRecyclerView;
    private StrategyRecyclerViewAdapter strategyRecyclerViewAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragmet_strategy_page;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mRecyclerView= (RecyclerView) view.findViewById(R.id.recycler_view_strategy_page);
        strategyRecyclerViewAdapter=new StrategyRecyclerViewAdapter(getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(strategyRecyclerViewAdapter);
    }

    @Override
    protected void initData() {

        NetTool.getInstance().startRequest(WebsiteInter.STRATEGY, StrategyDownBean.class, new CallBack<StrategyDownBean>() {
            @Override
            public void onsuccess(StrategyDownBean responce) {
                StrategyDownBean strategyDownBean =responce;
                strategyRecyclerViewAdapter.setStrategyDownBean(strategyDownBean);

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    public void onClick(View v) {

    }
}
