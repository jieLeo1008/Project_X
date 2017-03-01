package com.jieleo.projecta.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.jieleo.projecta.R;
import com.jieleo.projecta.activity.GiftDetailsActivity;
import com.jieleo.projecta.adapter.MallPageHeadRecyclerAdapter;
import com.jieleo.projecta.adapter.mall.MallPageRecyclerViewAdapter;
import com.jieleo.projecta.bean.mall.MallBodyBean;
import com.jieleo.projecta.bean.mall.MallHeadBean;
import com.jieleo.projecta.inter.CallBack;
import com.jieleo.projecta.inter.OnClickListenerInter;
import com.jieleo.projecta.tool.NetTool;
import com.jieleo.projecta.website.WebsiteInter;

import java.util.List;

/**
 * Created by yuyongjie on 17/2/10.
 */


public class MallPageFragment extends BaseFragment implements OnClickListenerInter {
    private LRecyclerView mLRecyclerView;
    private MallPageRecyclerViewAdapter mallPageRecyclerViewAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private List<MallBodyBean.DataBean.ItemsBean> itemsBeen;
    private String nextUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mall_page;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mLRecyclerView = (LRecyclerView) view.findViewById(R.id.l_recycler_view_mall_page_fragment);
        mallPageRecyclerViewAdapter = new MallPageRecyclerViewAdapter(getContext());
        lRecyclerViewAdapter = new LRecyclerViewAdapter(mallPageRecyclerViewAdapter);
        mallPageRecyclerViewAdapter.notifyDataSetChanged();
        mLRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mLRecyclerView.setAdapter(lRecyclerViewAdapter);
    }

    @Override
    protected void initData() {
        NetTool.getInstance().startRequest(WebsiteInter.MALLDOWN, MallBodyBean.class, new CallBack<MallBodyBean>() {
            @Override
            public void onSuccess(MallBodyBean response) {
                itemsBeen = response.getData().getItems();
                mallPageRecyclerViewAdapter.setItemsBeen(itemsBeen);
                nextUrl = response.getData().getPaging().getNext_url();
            }

            @Override
            public void onError(Throwable e) {

            }
        });


        View headView = LayoutInflater.from(getContext()).inflate(R.layout.item_head_first_mall_page, mLRecyclerView, false);
        RecyclerView recyclerView = (RecyclerView) headView.findViewById(R.id.recycler_view_head_mall_page);
        final MallPageHeadRecyclerAdapter mallPageHeadRecyclerAdapter = new MallPageHeadRecyclerAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mallPageHeadRecyclerAdapter);
        NetTool.getInstance().startRequest(WebsiteInter.MallUP, MallHeadBean.class, new CallBack<MallHeadBean>() {
            @Override
            public void onSuccess(MallHeadBean response) {
                MallHeadBean mallHeadBean = response;
                mallPageHeadRecyclerAdapter.setMallHeadBean(mallHeadBean);
            }

            @Override
            public void onError(Throwable e) {

            }
        });

        lRecyclerViewAdapter.addHeaderView(headView);


        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_head_bottom_mall_page, mLRecyclerView, false);
        lRecyclerViewAdapter.addHeaderView(view);


        mLRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (nextUrl != null) {

                    NetTool.getInstance().startRequest(nextUrl, MallBodyBean.class, new CallBack<MallBodyBean>() {
                        @Override
                        public void onSuccess(MallBodyBean response) {
                            itemsBeen.addAll(response.getData().getItems());
                            mallPageRecyclerViewAdapter.notifyDataSetChanged();
                            nextUrl = response.getData().getPaging().getNext_url();
                            mLRecyclerView.setNoMore(false);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
                } else {
                    mLRecyclerView.setNoMore(true);
                }
            }
        });

        mallPageRecyclerViewAdapter.setOnClickListenerInter(this);
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClickListener(int position) {
//        Intent intent =new Intent(getActivity(), GiftDetailsActivity.class);
//        Bundle bundle=new Bundle();
//        bundle.putParcelable("itemsBean",itemsBeen.get(position));
//        intent.putExtra("bundle",bundle);
//        startActivity(intent);

    }
}
