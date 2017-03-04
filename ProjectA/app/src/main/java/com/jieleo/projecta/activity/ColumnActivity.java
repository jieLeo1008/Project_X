package com.jieleo.projecta.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.mall.ColumnRecyclerViewAdapter;
import com.jieleo.projecta.bean.category.StrategyDownBean;
import com.jieleo.projecta.bean.category.StrategyUpBean;

public class ColumnActivity extends BaseActivity {
    private StrategyUpBean strategyUpBean;
    private LRecyclerView lRecyclerView;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private ColumnRecyclerViewAdapter columnRecyclerViewAdapter;

    @Override
    public int setLayout() {
        return R.layout.activity_column;
    }

    @Override
    protected void initView() {
        lRecyclerView = (LRecyclerView) findViewById(R.id.l_recycler_view_column);

    }

    @Override
    protected void initData() {
        strategyUpBean=getIntent().getBundleExtra("bundle").getParcelable("strategy");
        columnRecyclerViewAdapter=new ColumnRecyclerViewAdapter(this);
        lRecyclerViewAdapter=new LRecyclerViewAdapter(columnRecyclerViewAdapter);
        lRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        lRecyclerView.setAdapter(lRecyclerViewAdapter);
        columnRecyclerViewAdapter.setStrategyUpBean(strategyUpBean);
    }

    @Override
    protected void bindEvent() {

    }
}
