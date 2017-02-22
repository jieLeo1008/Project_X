package com.jieleo.projecta.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jieleo.projecta.R;
import com.jieleo.projecta.bean.gift.GiftDetailsBean;

import java.util.List;

public class GiftDetailsActivity extends BaseActivity{
    private static final String TAG = "GiftDetailsActivity";
    private GiftDetailsBean.DataBean.ItemsBean itemsBean;
    private ScrollView scrollView;
    private ImageView bgImageView;
    private TextView titleTv,descriptionTv;

    @Override
    public int setLayout() {
        return R.layout.activity_gift_details;
    }

    @Override
    protected void initView() {
        scrollView = (ScrollView) findViewById(R.id.scrollView_gift_details);
        bgImageView = (ImageView) findViewById(R.id.iv_bg_gift_details);
        titleTv = (TextView) findViewById(R.id.tv_title_gift_details);
        descriptionTv = (TextView) findViewById(R.id.tv_short_description_gift_details);
    }

    @Override
    protected void initData() {
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("details");
        itemsBean=bundle.getParcelable("itemdetails");
        Log.e(TAG, "initView: "+itemsBean.getName());
    }

    @Override
    protected void bindEvent() {

    }
}
