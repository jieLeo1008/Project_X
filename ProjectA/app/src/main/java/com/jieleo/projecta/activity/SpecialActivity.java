package com.jieleo.projecta.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.SpecialRecyclerViewAdapter;
import com.jieleo.projecta.bean.mall.MallHeadBean;

public class SpecialActivity extends BaseActivity {
    private ImageView backIv,shareIv;
    private LRecyclerView lRecyclerView;
    private MallHeadBean.DataBean.ItemsBeanX itemsBeanX;
    private SpecialRecyclerViewAdapter specialRecyclerViewAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;

    @Override
    public int setLayout() {
        return R.layout.activity_special;
    }

    @Override
    protected void initView() {
        backIv = (ImageView) findViewById(R.id.iv_back_special);
        shareIv = (ImageView) findViewById(R.id.iv_share_special);
        lRecyclerView = (LRecyclerView) findViewById(R.id.lrecycler_view_special);
    }

    @Override
    protected void initData() {
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("bundle");
        itemsBeanX=bundle.getParcelable("itemsBean");
        specialRecyclerViewAdapter =new SpecialRecyclerViewAdapter(this);
        lRecyclerViewAdapter =new LRecyclerViewAdapter(specialRecyclerViewAdapter);
        lRecyclerView.setLayoutManager(new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL,false));
        lRecyclerView.setAdapter(lRecyclerViewAdapter);
        specialRecyclerViewAdapter.setItemsBeen(itemsBeanX.getItems());
    }

    @Override
    protected void bindEvent() {
        lRecyclerView.setPullRefreshEnabled(false);
        View view=LayoutInflater.from(this).inflate(R.layout.item_head_view_special,lRecyclerView,false);
        ImageView coverIv= (ImageView) view.findViewById(R.id.iv_head_special);
        TextView titleTv= (TextView) view.findViewById(R.id.tv_title_head_special);
        TextView introductionTv= (TextView) view.findViewById(R.id.tv_introduction_head_special);
        Glide.with(this).load(itemsBeanX.getCover_image_url()).into(coverIv);
        titleTv.setText(itemsBeanX.getTitle());
        introductionTv.setText(itemsBeanX.getIntroduction());
        lRecyclerViewAdapter.addHeaderView(view);

    }
}
