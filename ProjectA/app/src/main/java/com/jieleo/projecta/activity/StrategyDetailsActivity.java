package com.jieleo.projecta.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jieleo.projecta.R;
import com.jieleo.projecta.bean.homepage.DetailsBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class StrategyDetailsActivity extends BaseActivity implements View.OnClickListener {
    private ImageView backTv,coverIv;
    private TextView lookAllTv,shareDetailsTv;
    private WebView webView;
    private DetailsBean.DataBean.ItemsBean itemsBean;

    @Override
    public int setLayout() {
        return R.layout.activity_strategy_details;
    }

    @Override
    protected void initView() {
        backTv = (ImageView) findViewById(R.id.iv_back_strategy_details);
        coverIv = (ImageView) findViewById(R.id.iv_cover_image_strategy_details);
        lookAllTv = (TextView) findViewById(R.id.tv_look_all_strategy_details);
        shareDetailsTv = (TextView) findViewById(R.id.tv_share_msg_strategy_details);
        webView = (WebView) findViewById(R.id.web_view_strategy_details);
    }

    @Override
    protected void initData() {
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("bundle");
        itemsBean=bundle.getParcelable("itemsBean");
        Glide.with(this).load(itemsBean.getCover_image_url()).into(coverIv);
        shareDetailsTv.setText(itemsBean.getShare_msg());
        WebSettings webSettings=webView.getSettings();
        //设置webView
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setJavaScriptEnabled(false);
        webSettings.setBlockNetworkImage(false);
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        webSettings.setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(itemsBean.getContent_url());
                return true;
            }
        });
        webView.loadUrl(itemsBean.getContent_url());
    }


    @Override
    protected void bindEvent() {
        backTv.setOnClickListener(this);
        lookAllTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_strategy_details:
                finish();
                break;
            case R.id.iv_cover_image_strategy_details:
                break;
        }
    }
}
