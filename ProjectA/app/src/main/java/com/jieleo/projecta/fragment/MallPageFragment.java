package com.jieleo.projecta.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.mall.MallHeadFirstAdapter;
import com.jieleo.projecta.adapter.mall.MallHeadFourthAdapter;
import com.jieleo.projecta.adapter.mall.MallHeadSecondAdapter;
import com.jieleo.projecta.adapter.mall.MallHeadThirdAdapter;
import com.jieleo.projecta.adapter.mall.MallPageRecyclerViewAdapter;
import com.jieleo.projecta.bean.mall.MallBodyBean;
import com.jieleo.projecta.bean.mall.MallHeadBean;
import com.jieleo.projecta.inter.CallBack;
import com.jieleo.projecta.tool.NetTool;
import com.jieleo.projecta.website.WebsiteInter;

/**
 * Created by yuyongjie on 17/2/10.
 */


public class MallPageFragment extends BaseFragment {
    private LRecyclerView mLRecyclerView;
    private MallPageRecyclerViewAdapter mallPageRecyclerViewAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mall_page;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mLRecyclerView = (LRecyclerView) view.findViewById(R.id.l_recycler_view_mall_page_fragment);
        mallPageRecyclerViewAdapter=new MallPageRecyclerViewAdapter(getContext());
        lRecyclerViewAdapter = new LRecyclerViewAdapter(mallPageRecyclerViewAdapter);
        mLRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        mLRecyclerView.setAdapter(lRecyclerViewAdapter);
    }

    @Override
    protected void initData() {
        NetTool.getInstance().startRequest(WebsiteInter.MALLDOWN, MallBodyBean.class, new CallBack<MallBodyBean>() {
            @Override
            public void onsuccess(MallBodyBean responce) {
                MallBodyBean mallBodyBean=responce;
                mallPageRecyclerViewAdapter.setMallBodyBean(mallBodyBean);
            }

            @Override
            public void onError(Throwable e) {

            }
        });

        NetTool.getInstance().startRequest(WebsiteInter.MallUP, MallHeadBean.class, new CallBack<MallHeadBean>() {
            @Override
            public void onsuccess(MallHeadBean responce) {
                MallHeadBean mallHeadBean=responce;


                View headViewFIrst= LayoutInflater.from(getContext()).inflate(R.layout.item_head_first_mall_page,mLRecyclerView,false);
                View headViewSecond=LayoutInflater.from(getContext()).inflate(R.layout.item_head_second_mall_page,mLRecyclerView,false);
                View headViewThird=LayoutInflater.from(getContext()).inflate(R.layout.item_head_third_mall_page,mLRecyclerView,false);
                View headViewFourth=LayoutInflater.from(getContext()).inflate(R.layout.item_head_fourth_mall_page,mLRecyclerView,false);

                ImageView ivHeadFirst= (ImageView) headViewFIrst.findViewById(R.id.iv_head_first_mall_page);
                TextView tvHeadFirst= (TextView) headViewFIrst.findViewById(R.id.tv_head_first_mall_page);
                RecyclerView rvHeadFirst= (RecyclerView) headViewFIrst.findViewById(R.id.recycler_view_head_first_mall_page);

                Glide.with(getContext()).load(mallHeadBean.getData().getItems().get(0).getCover_image_url()).into(ivHeadFirst);
                tvHeadFirst.setText(mallHeadBean.getData().getItems().get(0).getTitle());
                MallHeadFirstAdapter mallHeadFirstAdapter=new MallHeadFirstAdapter(getContext());
                rvHeadFirst.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
                rvHeadFirst.setAdapter(mallHeadFirstAdapter);
                mallHeadFirstAdapter.setItemsBean(mallHeadBean.getData().getItems().get(0));





                ImageView ivHeadSecond= (ImageView) headViewSecond.findViewById(R.id.iv_head_second_mall_page);
                TextView tvHeadSecond= (TextView) headViewSecond.findViewById(R.id.tv_head_second_mall_page);
                RecyclerView rvHeadSecond= (RecyclerView) headViewSecond.findViewById(R.id.recycler_view_head_second_mall_page);

                Glide.with(getContext()).load(mallHeadBean.getData().getItems().get(1).getCover_image_url()).into(ivHeadSecond);
                tvHeadSecond.setText(mallHeadBean.getData().getItems().get(1).getTitle());
                MallHeadSecondAdapter mallHeadSecondAdapter=new MallHeadSecondAdapter(getContext());
                rvHeadSecond.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
                rvHeadSecond.setAdapter(mallHeadSecondAdapter);
                mallHeadSecondAdapter.setItemsBean(mallHeadBean.getData().getItems().get(1));



                ImageView ivHeadThird= (ImageView) headViewThird.findViewById(R.id.iv_head_third_mall_page);
                TextView tvHeadThird= (TextView) headViewThird.findViewById(R.id.tv_head_third_mall_page);
                RecyclerView rvHeadThird= (RecyclerView) headViewThird.findViewById(R.id.recycler_view_head_third_mall_page);

                Glide.with(getContext()).load(mallHeadBean.getData().getItems().get(2).getCover_image_url()).into(ivHeadThird);
                tvHeadThird.setText(mallHeadBean.getData().getItems().get(2).getTitle());
                MallHeadThirdAdapter mallHeadThirdAdapter=new MallHeadThirdAdapter(getContext());
                rvHeadThird.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
                rvHeadThird.setAdapter(mallHeadThirdAdapter);
                mallHeadThirdAdapter.setItemsBean(mallHeadBean.getData().getItems().get(2));



                ImageView ivHeadFourth= (ImageView) headViewFourth.findViewById(R.id.iv_head_fourth_mall_page);
                TextView tvHeadFourth= (TextView) headViewFourth.findViewById(R.id.tv_head_fourth_mall_page);
                RecyclerView rvHeadFourth= (RecyclerView) headViewFourth.findViewById(R.id.recycler_view_head_fourth_mall_page);

                Glide.with(getContext()).load(mallHeadBean.getData().getItems().get(3).getCover_image_url()).into(ivHeadFourth);
                tvHeadFourth.setText(mallHeadBean.getData().getItems().get(3).getTitle());
                MallHeadFourthAdapter mallHeadFourthAdapter=new MallHeadFourthAdapter(getContext());
                rvHeadFourth.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
                rvHeadFourth.setAdapter(mallHeadFourthAdapter);
                mallHeadFourthAdapter.setItemsBean(mallHeadBean.getData().getItems().get(3));



                lRecyclerViewAdapter.addHeaderView(headViewFIrst);
                lRecyclerViewAdapter.addHeaderView(headViewSecond);
                lRecyclerViewAdapter.addHeaderView(headViewThird);
                lRecyclerViewAdapter.addHeaderView(headViewFourth);
                lRecyclerViewAdapter.notifyItemRangeInserted(0,4);


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
