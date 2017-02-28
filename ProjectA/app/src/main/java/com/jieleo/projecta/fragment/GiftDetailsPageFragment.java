package com.jieleo.projecta.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.jieleo.projecta.R;
import com.jieleo.projecta.activity.DetailsForOtherActivity;
import com.jieleo.projecta.activity.GiftDetailsActivity;
import com.jieleo.projecta.adapter.gift.GiftPageRecyclerViewAdapter;
import com.jieleo.projecta.bean.gift.GiftDetailsBean;
import com.jieleo.projecta.bean.gift.GiftTitleBean;
import com.jieleo.projecta.inter.CallBack;
import com.jieleo.projecta.inter.OnClickListenerInter;
import com.jieleo.projecta.tool.NetTool;
import com.jieleo.projecta.website.WebsiteInter;

import java.util.List;

/**
 * Created by yuyongjie on 17/2/15.
 */


public class GiftDetailsPageFragment extends BaseFragment implements OnClickListenerInter {
    private GiftPageRecyclerViewAdapter giftPageRecyclerViewAdapter;
    private static final String TAG = "GiftDetailsPageFragment";
    private LRecyclerView mLRecyclerView;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private List<GiftDetailsBean.DataBean.ItemsBean> itemsBeen;
    private String nextUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gift_page_details;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mLRecyclerView = (LRecyclerView) view.findViewById(R.id.recycler_l_view_item);
        giftPageRecyclerViewAdapter = new GiftPageRecyclerViewAdapter(getContext());
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(giftPageRecyclerViewAdapter);
        mLRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false));
        mLRecyclerView.setAdapter(mLRecyclerViewAdapter);
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        int id = bundle.getInt("id", 1);
        String url = WebsiteInter.getGiftDetailsUrl(id);


        NetTool.getInstance().startRequest(url, GiftDetailsBean.class, new CallBack<GiftDetailsBean>() {
            @Override
            public void onSuccess(GiftDetailsBean response) {
                View head = LayoutInflater.from(getContext()).inflate(R.layout.item_head_gift_page, mLRecyclerView, false);
                ImageView imageView = (ImageView) head.findViewById(R.id.iv_head_gift_page);
                Glide.with(getContext()).load(response.getData().getCover_image()).into(imageView);
                mLRecyclerViewAdapter.addHeaderView(head);
                itemsBeen = response.getData().getItems();
                giftPageRecyclerViewAdapter.setItemsBeen(itemsBeen);
                nextUrl = response.getData().getPaging().getNext_url();
            }

            @Override
            public void onError(Throwable e) {
            }
        });

        mLRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (nextUrl != null) {
                    NetTool.getInstance().startRequest(nextUrl, GiftDetailsBean.class, new CallBack<GiftDetailsBean>() {
                        @Override
                        public void onSuccess(GiftDetailsBean response) {
                            if (response.getData().getPaging().getNext_url() != null) {
                                nextUrl = response.getData().getPaging().getNext_url();
                            } else {
                                nextUrl = null;
                            }
                            itemsBeen.addAll(response.getData().getItems());
                            giftPageRecyclerViewAdapter.notifyDataSetChanged();
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


        giftPageRecyclerViewAdapter.setOnClickListenerInter(this);
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    public void onClick(View v) {

    }

    public static GiftDetailsPageFragment newInstance(GiftTitleBean.DataBean.RanksBean ranksBean) {
        Bundle args = new Bundle();
        args.putInt("id", ranksBean.getId());
        GiftDetailsPageFragment fragment = new GiftDetailsPageFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onItemClickListener(int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("itemsDetails", itemsBeen.get(position));
        if (itemsBeen.get(position).getFavorites_count() == 0) {
            Intent intent = new Intent(getActivity(), GiftDetailsActivity.class);
            intent.putExtra("details", bundle);
            startActivity(intent);
        }else {
            Intent intent=new Intent(getActivity(), DetailsForOtherActivity.class);
            intent.putExtra("details",bundle);
            startActivity(intent);
        }
    }
}
