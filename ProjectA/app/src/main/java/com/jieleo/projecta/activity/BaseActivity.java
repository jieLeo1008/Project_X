package com.jieleo.projecta.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by jie on 2017/2/10.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        initView();
        initData();
        bindEvent();
    }

    /**
     * 绑定布局
     * @return 布局的Id
     */
    public abstract int setLayout();

    /**
     * 初始化组件
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 绑定事件
     */
    protected abstract void bindEvent();

    /**
     * 绑定布局
     * @param resId 组件的resId
     * @param <T>   组件的泛型
     * @return      绑定布局
     */
    protected <T extends View> T bindView(int resId) {
        return (T) findViewById(resId);
    }

}
