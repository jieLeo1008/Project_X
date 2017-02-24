package com.jieleo.projecta.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.jieleo.projecta.R;

/**
 * Created by jie on 2017/2/19.
 */

public class FirstStartActivity extends  BaseActivity {
    private Handler handler;
    @Override
    public int setLayout() {
        return R.layout.activity_first_start;
    }

    @Override
    protected void initView() {
        startActivity(new Intent(FirstStartActivity.this,MainActivity.class));
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what==100){
                finish();
                }
                return false;
            }
        });
        handler.sendEmptyMessageDelayed(100,3000);
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void bindEvent() {

    }
}
