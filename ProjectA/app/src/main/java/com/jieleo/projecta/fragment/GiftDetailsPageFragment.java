package com.jieleo.projecta.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.GiftPageRecyclerViewAdapter;
import com.jieleo.projecta.bean.gift.GiftDetailsBean;
import com.jieleo.projecta.bean.gift.GiftTitleBean;
import com.jieleo.projecta.inter.CallBack;
import com.jieleo.projecta.tool.NetTool;
import com.jieleo.projecta.website.WebsiteInter;

import java.util.List;

/**
 * Created by yuyongjie on 17/2/15.
 */


public class GiftDetailsPageFragment extends BaseFragment {
    private GiftPageRecyclerViewAdapter giftPageRecyclerViewAdapter;
    private static final String TAG = "GiftDetailsPageFragment";
    private LRecyclerView mLRecyclerView;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private GiftDetailsBean.DataBean dataBean;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gift_page_details;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mLRecyclerView = (LRecyclerView) view.findViewById(R.id.recycler_l_view_item);
        giftPageRecyclerViewAdapter=new GiftPageRecyclerViewAdapter(getContext());
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(giftPageRecyclerViewAdapter);
        mLRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2,LinearLayoutManager.VERTICAL,false));
        mLRecyclerView.setAdapter(mLRecyclerViewAdapter);
    }

    @Override
    protected void initData() {
            Bundle bundle=getArguments();
            int id=bundle.getInt("id",1);
            String url =WebsiteInter.getGiftDetailsUrl(id);


            NetTool.getInstance().startRequest(url, GiftDetailsBean.class, new CallBack<GiftDetailsBean>() {
                @Override
                public void onSuccess(GiftDetailsBean response) {
                    dataBean = response.getData();
                    View head=LayoutInflater.from(getContext()).inflate(R.layout.item_head_gift_page,mLRecyclerView,false);
                    ImageView imageView= (ImageView) head.findViewById(R.id.iv_head_gift_page);
                    Glide.with(getContext()).load(dataBean.getCover_image()).into(imageView);
                    mLRecyclerViewAdapter.addHeaderView(head);
                    giftPageRecyclerViewAdapter.setDataBean(dataBean);
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

    public static GiftDetailsPageFragment newInstance(GiftTitleBean.DataBean.RanksBean ranksBean) {
        Bundle args = new Bundle();
        args.putInt("id",ranksBean.getId());
        GiftDetailsPageFragment fragment = new GiftDetailsPageFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
