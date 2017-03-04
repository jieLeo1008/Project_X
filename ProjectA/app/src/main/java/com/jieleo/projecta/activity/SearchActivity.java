package com.jieleo.projecta.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.SearchBodyAdapter;
import com.jieleo.projecta.bean.greendao.Search;
import com.jieleo.projecta.tool.SearchTool;

import java.util.List;

public class SearchActivity extends BaseActivity {
    private EditText etSearch;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private LRecyclerView lRecyclerView;
    private SearchBodyAdapter adapter;
    private List<Search> searches;

    @Override
    public int setLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        etSearch = (EditText) findViewById(R.id.et_search);
        lRecyclerView = (LRecyclerView) findViewById(R.id.recycler_view_hot_search);
    }

    @Override
    protected void initData() {
        if (SearchTool.getInstance().queryAll().size()!=0){
            searches=SearchTool.getInstance().queryAll();
            //TODO 完成处理数据库
        }
        adapter=new SearchBodyAdapter(this);
        lRecyclerViewAdapter=new LRecyclerViewAdapter(adapter);
        lRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        lRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void bindEvent() {
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (event.getAction()) {
                }
                return false;
            }
        });
    }
}
