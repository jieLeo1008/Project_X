package com.jieleo.projecta.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.category.single.LeftListViewAdapter;
import com.jieleo.projecta.adapter.category.single.RightListViewAdapter;
import com.jieleo.projecta.bean.category.SingleBean;
import com.jieleo.projecta.inter.CallBack;
import com.jieleo.projecta.tool.NetTool;
import com.jieleo.projecta.website.WebsiteInter;

/**
 * Created by jie on 2017/2/16.
 */

public class SingleFragment extends BaseFragment {
    private ListView leftListView ,rightListView;
    private LeftListViewAdapter leftAdapter;
    private RightListViewAdapter rightAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_single_page;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        leftListView = (ListView) view.findViewById(R.id.list_view_left);
        rightListView = (ListView) view.findViewById(R.id.list_view_right);
        leftAdapter=new LeftListViewAdapter(getContext());
        rightAdapter=new RightListViewAdapter(getContext());
        leftListView.setAdapter(leftAdapter);
        rightListView.setAdapter(rightAdapter);
    }

    @Override
    protected void initData() {
        NetTool.getInstance().startRequest(WebsiteInter.SINGLE, SingleBean.class, new CallBack<SingleBean>() {
            @Override
            public void onSuccess(SingleBean response) {
                    SingleBean singleBean=response;
                rightAdapter.setSingleBean(singleBean);
                leftAdapter.setSingleBean(singleBean);
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
