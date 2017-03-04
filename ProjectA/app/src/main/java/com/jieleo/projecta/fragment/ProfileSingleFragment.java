package com.jieleo.projecta.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jieleo.projecta.MyApp;
import com.jieleo.projecta.R;
import com.jieleo.projecta.activity.EnshrineDetailsActivity;
import com.jieleo.projecta.adapter.ProfileSingleRecyclerViewAdapter;
import com.jieleo.projecta.bean.greendao.Enshrine;
import com.jieleo.projecta.inter.OnClickListenerInter;
import com.jieleo.projecta.tool.EnshirneTool;

import java.util.List;

/**
 * Created by yuyongjie on 17/3/2.
 */


public class ProfileSingleFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private ProfileSingleRecyclerViewAdapter adapter;
    private List<Enshrine> enshrines;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile_single_page;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_profile_single_page);
    }

    @Override
    protected void initData() {
        enshrines= EnshirneTool.getInstance().queryAll();
        adapter = new ProfileSingleRecyclerViewAdapter(MyApp.getmContext());
        recyclerView.setLayoutManager(new GridLayoutManager(MyApp.getmContext(), 4));
        recyclerView.setAdapter(adapter);
        adapter.setEnshrines(enshrines);

    }

    @Override
    protected void bindEvent() {
        adapter.setOnClickListenerInter(new OnClickListenerInter() {
            @Override
            public void onItemClickListener(int position) {
                Intent intent =new Intent(getActivity(), EnshrineDetailsActivity.class);
                Bundle bundle=new Bundle();
                bundle.putParcelable("enshrine",enshrines.get(position));
                intent.putExtra("data",bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
