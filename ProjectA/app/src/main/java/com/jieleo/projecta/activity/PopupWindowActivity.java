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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jieleo.projecta.MyApp;
import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.mall.PopupWindowRecyclerViewAdapter;
import com.jieleo.projecta.bean.gift.GiftDetailsBean;

public class PopupWindowActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "PopupWindowActivity";
    private ImageView destroyIv,coverIv;
    private LinearLayout linearLayout;
    private TextView priceTv,stockTv,propertyTv,addToShopTv,buyNowTv;
    private GiftDetailsBean.DataBean.ItemsBean itemsBean;
    private RadioGroup radioGroup;
    private int lastId = 0;
    private RadioButton radioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        Intent intent = getIntent();//接受传过来的数据
        Bundle bundle = intent.getBundleExtra("bundle");
        itemsBean = bundle.getParcelable("itemsBean");


        destroyIv = (ImageView) findViewById(R.id.iv_destroy_activity_popup_window);
        linearLayout = (LinearLayout) findViewById(R.id.liner_layout_popup_window);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group_popup_window);
        coverIv= (ImageView) findViewById(R.id.iv_cover_image_popup_window);
        priceTv= (TextView) findViewById(R.id.tv_price_popup_window);
        stockTv= (TextView) findViewById(R.id.tv_stock_popup_window);
        propertyTv= (TextView) findViewById(R.id.tv_property_popup_window);
        addToShopTv= (TextView) findViewById(R.id.tv_add_to_shop_popup_window);
        buyNowTv= (TextView) findViewById(R.id.tv_buy_now_popup_window);
        destroyIv.setOnClickListener(this);
        linearLayout.setOnClickListener(this);




        //动态添加RadioButton
        for (int i = 0; i < itemsBean.getSpecs_domains().get(0).getDomains().size(); i++) {
            RadioButton radioButton = new RadioButton(MyApp.getmContext());
            radioButton.setBackgroundResource(R.drawable.popup_window_details_secector);
            radioButton.setTextColor(Color.BLACK);
            radioButton.setId(i + 1);
            radioButton.setPadding(10,5,5,10);
            radioButton.setOnClickListener(this);
            radioButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            Log.d("PopupWindowActivity", itemsBean.getSpecs_domains().get(0).getDomains().get(i));
            radioButton.setText(itemsBean.getSpecs_domains().get(0).getDomains().get(i));
            radioButton.setButtonDrawable(getResources().getDrawable(android.R.drawable.screen_background_light_transparent));
            radioGroup.addView(radioButton);
        }


        for (int i = 0; i < itemsBean.getSpecs_domains().get(0).getDomains().size(); i++) {
            findViewById(i + 1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (lastId == 0) {//如果是第一次点击
                        ((RadioButton) v).setChecked(true);
                        ((RadioButton) v).setTextColor(Color.RED);
                        lastId = v.getId();
                    } else {
                        if (v.getId() == lastId) {//如果点击了两次
                            ((RadioButton) v).setChecked(false);
                            ((RadioButton) v).setTextColor(Color.BLACK);
                            lastId = 0;
                        } else {//如果两次点击了不同的按钮
                            ((RadioButton) findViewById(lastId)).setTextColor(Color.BLACK);

                            ((RadioButton) v).setChecked(true);
                            ((RadioButton) v).setTextColor(Color.RED);
                        }
                    }
                    lastId = v.getId();
                }
            });
        }


    }


    @Override
    protected void onStart() {
        super.onStart();
        //设置布局文字
        priceTv.setText(itemsBean.getSkus().get(0).getPrice());
        Log.e(TAG, "onStart: "+itemsBean.getSkus().get(0).getStock() );
//        Log.e(TAG, "onStart: "+itemsBean.getSkus().get(0).getSpecs().get(0).getProperty() );
        stockTv.setText("库存"+itemsBean.getSkus().get(0).getStock()+"件");
//        propertyTv.setText(itemsBean.getSkus().get(0).getSpecs().get(0).getProperty());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_destroy_activity_popup_window://点击关闭按钮
                finish();
                break;
            case R.id.liner_layout_popup_window://点击弹出以外的界面
                break;




        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

}
