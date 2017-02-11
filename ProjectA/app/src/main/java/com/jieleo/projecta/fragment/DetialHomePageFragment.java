package com.jieleo.projecta.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.jieleo.projecta.R;
import com.jieleo.projecta.bean.homepage.DetailsBean;

/**
 * Created by yuyongjie on 17/2/11.
 */


public class DetialHomePageFragment extends BaseFragment {
    private DetailsBean datas;

    private TextView textView;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_page_details;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        textView = (TextView) view.findViewById(R.id.tv_home_page_details);
    }

    @Override
    protected void initData() {
            Bundle bundle=getArguments();
            datas=bundle.getParcelable("data");
    }

    @Override
    protected void bindEvent() {
        textView.setText(datas.getTitle());
    }

    @Override
    public void onClick(View v) {

    }

    public static Fragment getInstance(DetailsBean detailsBean){
        DetialHomePageFragment detialHomePageFragment=new DetialHomePageFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelable("data", detailsBean);
        detialHomePageFragment.setArguments(bundle);
        return detialHomePageFragment;
    }

}
