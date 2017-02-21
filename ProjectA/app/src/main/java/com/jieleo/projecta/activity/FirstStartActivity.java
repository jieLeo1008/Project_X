package com.jieleo.projecta.activity;

import android.content.Intent;

import com.jieleo.projecta.R;

/**
 * Created by jie on 2017/2/19.
 */

public class FirstStartActivity extends  BaseActivity {

    @Override
    public int setLayout() {
        return R.layout.activity_first_start;
    }

    @Override
    protected void initView() {
        startActivity(new Intent(FirstStartActivity.this,MainActivity.class));
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void bindEvent() {

    }
}
