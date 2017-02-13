package com.jieleo.projecta.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.DetailsRecyclerViewAdapter;
import com.jieleo.projecta.bean.homepage.DetialsBean;
import com.jieleo.projecta.inter.CallBack;
import com.jieleo.projecta.tool.NetTool;

/**
 * Created by yuyongjie on 17/2/11.
 */


public class DetialHomePageFragment extends BaseFragment {
    private static final String TAG = "DetialHomePageFragment";
    private RecyclerView mRecyclerView;


    public static String TEST = "http://api.liwushuo.com/v2/channels/108/items_v2?gender=1&generation=2&limit=20&offset=0";

    private DetialsBean detialsBean;


    private DetailsRecyclerViewAdapter mDetialsRecyclerViewAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_page_details;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_details_home_page);
         }

    @Override
    protected void initData() {
        mDetialsRecyclerViewAdapter = new DetailsRecyclerViewAdapter(getContext());
        NetTool.getInstance().startRequest(TEST, DetialsBean.class, new CallBack<DetialsBean>() {
            @Override
            public void onsuccess(DetialsBean responce) {
                detialsBean = responce;
                for (int i = 0; i < 10; i++) {

//                Log.e(TAG, "onsuccess: " +detialsBean.getData().getItems().get(i).getColumn().getTitle());
                }
                mDetialsRecyclerViewAdapter.setDetialsBean(detialsBean);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mDetialsRecyclerViewAdapter);

    }

    @Override
    protected void bindEvent() {
    }

    @Override
    public void onClick(View v) {

    }

    public static Fragment getInstance(String title) {
        DetialHomePageFragment detialHomePageFragment = new DetialHomePageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("data", title);
        detialHomePageFragment.setArguments(bundle);
        return detialHomePageFragment;
    }

}
