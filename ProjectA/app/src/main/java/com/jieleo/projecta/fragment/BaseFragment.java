package com.jieleo.projecta.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yuyongjie on 17/2/10.
 */


public abstract class BaseFragment extends Fragment implements View.OnClickListener{


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(),container,false );
        initView(view,savedInstanceState);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        bindEvent();
    }

    protected abstract int getLayoutId() ;

    protected abstract  void initView(View view, Bundle savedInstanceState) ;

    protected abstract void initData();

    protected abstract void bindEvent();

}
