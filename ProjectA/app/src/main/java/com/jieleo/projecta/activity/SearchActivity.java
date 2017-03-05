package com.jieleo.projecta.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.SearchBodyAdapter;
import com.jieleo.projecta.adapter.SearchHeadRecyclerViewAdapter;
import com.jieleo.projecta.bean.HotSearch;
import com.jieleo.projecta.bean.greendao.Search;
import com.jieleo.projecta.inter.CallBack;
import com.jieleo.projecta.tool.NetTool;
import com.jieleo.projecta.tool.SearchTool;
import com.jieleo.projecta.website.WebsiteInter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class SearchActivity extends BaseActivity {
    private EditText etSearch;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private LRecyclerView lRecyclerView;
    private SearchBodyAdapter adapter;
    private List<Search> searches;
    private RecyclerView recyclerView;
    private ImageView imageView;
    private SearchHeadRecyclerViewAdapter headRecyclerViewAdapter;
    private HotSearch hotSearch;
    private Search search;
    private LinearLayout linearLayout;
    private NotifyDataBroadCastRecriver notifyDataBroadCastRecriver;
    private View headView;

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
        searches = new ArrayList<>();

        notifyDataBroadCastRecriver = new NotifyDataBroadCastRecriver();
        IntentFilter intentFilter = new IntentFilter("notifyData");
        registerReceiver(notifyDataBroadCastRecriver, intentFilter);

        adapter = new SearchBodyAdapter(this);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        lRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        lRecyclerView.setAdapter(lRecyclerViewAdapter);


        headView = LayoutInflater.from(this).inflate(R.layout.item_head_search, null);
        recyclerView = (RecyclerView) headView.findViewById(R.id.recycler_view_search_head);
        imageView = (ImageView) headView.findViewById(R.id.iv_delete_all_search);
        linearLayout = (LinearLayout) headView.findViewById(R.id.liner_layout_search);

        headRecyclerViewAdapter = new SearchHeadRecyclerViewAdapter(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 6));
        recyclerView.setAdapter(headRecyclerViewAdapter);
        NetTool.getInstance().startRequest(WebsiteInter.SEARCH, HotSearch.class, new CallBack<HotSearch>() {
            @Override
            public void onSuccess(HotSearch response) {
                headRecyclerViewAdapter.setHotSearche(response);

            }

            @Override
            public void onError(Throwable e) {

            }
        });
        lRecyclerViewAdapter.addHeaderView(headView);
        if (SearchTool.getInstance().queryAll().size() != 0) {
            searches = SearchTool.getInstance().queryAll();
            Collections.reverse(searches);
            adapter.setSearchs(searches);
            //TODO 设置布局显示
            linearLayout.setVisibility(View.VISIBLE);
        } else {
            //TODO 设置布局隐藏
            linearLayout.setVisibility(View.INVISIBLE);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.INVISIBLE);
                SearchTool.getInstance().deleteAll();
                adapter.setSearchs(null);
            }
        });
    }

    @Override
    protected void bindEvent() {

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (etSearch.getText().toString().isEmpty()) {
                        Toast.makeText(SearchActivity.this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
                    } else {
                        search = new Search();
                        String name=etSearch.getText().toString();
                        search.setName(name);
                        search.setTime(new Date().getTime());
                        etSearch.setText("");
                        if (!SearchTool.getInstance().isrepeate(name)){
                        SearchTool.getInstance().insert(search);
                        }
                        if (searches.size() > 0) {
                            searches.clear();
                        }
                        searches = SearchTool.getInstance().queryAll();
                        Collections.reverse(searches);
                        adapter.setSearchs(searches);
                        adapter.notifyDataSetChanged();
                        linearLayout.setVisibility(View.VISIBLE);
                    }
                }
                return false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(notifyDataBroadCastRecriver);
    }

    class NotifyDataBroadCastRecriver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (SearchTool.getInstance().queryAll().size() > 0) {
                searches.clear();
                searches = SearchTool.getInstance().queryAll();
                Collections.reverse(searches);
                adapter.setSearchs(searches);
                linearLayout.setVisibility(View.VISIBLE);

            } else {
                adapter.setSearchs(null);
                linearLayout.setVisibility(View.INVISIBLE);
            }
        }
    }
}
