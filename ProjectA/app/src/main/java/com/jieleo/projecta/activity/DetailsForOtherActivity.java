package com.jieleo.projecta.activity;

import android.content.Intent;
import android.os.Bundle;

import com.jieleo.projecta.R;
import com.jieleo.projecta.bean.gift.GiftDetailsBean;

public class DetailsForOtherActivity extends BaseActivity {
    private GiftDetailsBean.DataBean.ItemsBean itemsBean;

    @Override
    public int setLayout() {
        return R.layout.activity_details_for_other;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("details");
        itemsBean=bundle.getParcelable("itemsDetails");
    }

    @Override
    protected void bindEvent() {

    }
}
