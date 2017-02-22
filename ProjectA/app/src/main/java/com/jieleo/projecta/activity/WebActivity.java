package com.jieleo.projecta.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jieleo.projecta.R;

public class WebActivity extends BaseActivity {
    private WebView webView;
    private String url;
    @Override
    public int setLayout() {
        return R.layout.activity_web;
    }

    @Override
    protected void initView() {
        webView= (WebView) findViewById(R.id.web_view);
    }

    @Override
    protected void initData() {
        Intent intent=getIntent();
        url=intent.getStringExtra("url");
        webView.loadUrl(url);
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDisplayZoomControls(true);
    }

    @Override
    protected void bindEvent() {

    }
}
