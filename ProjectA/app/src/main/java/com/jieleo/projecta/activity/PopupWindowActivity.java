package com.jieleo.projecta.activity;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jieleo.projecta.MyApp;
import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.mall.PopupWindowRecyclerViewAdapter;
import com.jieleo.projecta.bean.gift.GiftDetailsBean;

public class PopupWindowActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "PopupWindowActivity";
    private ImageView destroyIv;
    private LinearLayout linearLayout;
    private GiftDetailsBean.DataBean.ItemsBean itemsBean;
    private RadioGroup radioGroup;
    private int lastId = 0;
    private RadioButton radioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        itemsBean = bundle.getParcelable("itemsBean");
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        destroyIv = (ImageView) findViewById(R.id.iv_destroy_activity_popup_window);
        linearLayout = (LinearLayout) findViewById(R.id.liner_layout_popup_window);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group_popup_window);
        destroyIv.setOnClickListener(this);
        linearLayout.setOnClickListener(this);
        //动态添加RadioButton
        for (int i = 0; i < itemsBean.getSpecs_domains().get(0).getDomains().size(); i++) {
            RadioButton radioButton = new RadioButton(MyApp.getmContext());
            radioButton.setBackgroundResource(R.drawable.popup_window_details_secector);
            radioButton.setTextColor(Color.BLACK);
            radioButton.setId(i+1);
            radioButton.setOnClickListener(this);
            radioButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            Log.d("PopupWindowActivity", itemsBean.getSpecs_domains().get(0).getDomains().get(i));
            radioButton.setText(itemsBean.getSpecs_domains().get(0).getDomains().get(i));
            radioButton.setButtonDrawable(getResources().getDrawable(android.R.drawable.screen_background_light_transparent));
            radioGroup.addView(radioButton);
        }

//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, final int checkedId) {
//                radioButton = null;
//                if (checkedId != lastId) {
//                    radioButton = (RadioButton) findViewById(checkedId);
//                    radioButton.setTextColor(Color.RED);
//                    if (lastId != 0) {
//                        radioButton = (RadioButton) findViewById(lastId);
//                        radioButton.setTextColor(Color.BLACK);
//                    }
//                    lastId = checkedId;
//                }
//
//            }
//        });

//        radioGroup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e(TAG, "onClick: " + lastId + ",id---"+v.getId());
//                if (lastId == 0) {
//                    Log.e(TAG, "onClick: first in");
//                    ((RadioButton) v).setChecked(true);
//                    lastId = v.getId();
//                } else {
//                    Log.e(TAG, "onClick: repeat click");
//                    if (lastId == v.getId()) {
//                        ((RadioButton) v).setChecked(false);
//                        lastId = 0;
//                    } else {
//                        Log.e(TAG, "onClick: change click");
//                        ((RadioButton) v).setChecked(true);
//                        ((RadioButton)findViewById(lastId)).setChecked(false);
//                        lastId = v.getId();
//                    }
//                }
//
//            }
//        });

        for (int i = 0; i < itemsBean.getSpecs_domains().get(0).getDomains().size(); i++) {
            findViewById(i+1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(lastId == 0){
                        ((RadioButton)v).setChecked(true);
                        ((RadioButton)v).setTextColor(Color.RED);
                        lastId = v.getId();
                    }else {
                        if (v.getId() == lastId) {
                            ((RadioButton) v).setChecked(false);
                            ((RadioButton) v).setTextColor(Color.BLACK);
                            lastId=0;
                        } else {
                            ((RadioButton) findViewById(lastId)).setTextColor(Color.BLACK);

                            ((RadioButton) v).setChecked(true);
                            ((RadioButton) v).setTextColor(Color.RED);
                        }
                    }
                    lastId=v.getId();
                }
            });
        }


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_destroy_activity_popup_window:
                finish();
                break;
            case R.id.liner_layout_popup_window:
                break;

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    //    @Override
//    public int setLayout() {
//        return R.layout.activity_popup_window;
//    }
//
//    @Override
//    protected void initView() {
//        destroyIv=bindView(R.id.iv_destroy_activity_popup_window);
//        linearLayout=bindView(R.id.liner_layout_popup_window);
//    }
//
//    @Override
//    protected void initData() {
//
//    }
//
//    @Override
//    protected void bindEvent() {
//        destroyIv.setOnClickListener(this);
//        linearLayout.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.iv_destroy_activity_popup_window:
//                break;
//            case R.id.liner_layout_popup_window:
//                break;
//        }
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        finish();
//        return true;
//    }
}
