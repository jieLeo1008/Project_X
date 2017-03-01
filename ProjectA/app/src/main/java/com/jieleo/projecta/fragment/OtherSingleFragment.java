package com.jieleo.projecta.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.details.OtherDetailsSingleVpAdapter;
import com.jieleo.projecta.bean.eventbus.EventBusBean;
import com.jieleo.projecta.bean.gift.GiftDetailsBean;
import com.jieleo.projecta.inter.MoveToFive;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by yuyongjie on 17/2/28.
 */


public class OtherSingleFragment extends BaseFragment implements MoveToFive{
    private ViewPager viewPager;
    private ScrollView scrollView;
    private OtherDetailsSingleVpAdapter adapter;
    private GiftDetailsBean.DataBean.ItemsBean itemsBean;
    private MoveToSecond moveToSecond;
    private TextView shortDescriptionTv,nameTv,priceTv,likeTv;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_other_single;
    }

    public void setMoveToSecond(MoveToSecond moveToSecond) {
        this.moveToSecond = moveToSecond;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        viewPager = (ViewPager) view.findViewById(R.id.vp_other_single);
        scrollView = (ScrollView) view.findViewById(R.id.scrollView_other_single);
        shortDescriptionTv = (TextView) view.findViewById(R.id.tv_short_description_other_single);
        nameTv = (TextView) view.findViewById(R.id.tv_name_other_single);
        priceTv = (TextView) view.findViewById(R.id.tv_price_other_single);
        likeTv = (TextView) view.findViewById(R.id.tv_likes_other_single);
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        itemsBean = bundle.getParcelable("itemsDetails");
        adapter = new OtherDetailsSingleVpAdapter();
        viewPager.setAdapter(adapter);
        adapter.setImageUrls(itemsBean.getImage_urls());
        shortDescriptionTv.setText(itemsBean.getShort_description());
        nameTv.setText(itemsBean.getName());
        priceTv.setText(itemsBean.getPrice());
    }

    @Override
    protected void bindEvent() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d("OtherSingleFragment", "position:" + position);
                if (position == itemsBean.getImage_urls().size()) {
                    moveToSecond.moveToSecond();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void moveToFive() {
        viewPager.setCurrentItem(itemsBean.getImage_urls().size()-1);
    }


    public interface MoveToSecond {
        void moveToSecond();
    }




}
