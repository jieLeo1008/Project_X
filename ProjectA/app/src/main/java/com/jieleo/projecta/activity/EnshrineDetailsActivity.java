package com.jieleo.projecta.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jieleo.projecta.MyApp;
import com.jieleo.projecta.R;
import com.jieleo.projecta.bean.greendao.Enshrine;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class EnshrineDetailsActivity extends BaseActivity{
    private ImageView coverImageIv;
    private WebView detailsWebView;
    private Enshrine enshrine;
    private WebSettings webSettings;

    @Override
    public int setLayout() {
        return R.layout.activity_enshrine_details;
    }

    @Override
    protected void initView() {
        coverImageIv = (ImageView) findViewById(R.id.iv_enshrine_details);
        detailsWebView = (WebView) findViewById(R.id.web_view_enshrine_details);

    }

    @Override
    protected void initData() {
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("data");
        enshrine=bundle.getParcelable("enshrine");
        Glide.with(MyApp.getmContext()).load(enshrine.getEnshrineCoverImage()).into(coverImageIv);
        webSettings=detailsWebView.getSettings();
        if (enshrine.getType()==1){
            webSettings.setJavaScriptEnabled(true);
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            webSettings.setSupportZoom(true);
            detailsWebView.loadDataWithBaseURL(null, getNewContent(enshrine.getDetailsUrl()), "text/html", "utf-8", null);
        }else {
            webSettings.setJavaScriptEnabled(false);
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            coverImageIv.setVisibility(View.GONE);
            webSettings.setSupportZoom(true);
            detailsWebView.setWebViewClient(new WebViewClient());
            detailsWebView.loadUrl(enshrine.getDetailsUrl());
        }
    }
    private String getNewContent(String htmltext) {

        Document doc = Jsoup.parse(htmltext);
        Elements elements = doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width", "100%").attr("height", "auto");
        }

        return doc.toString();
    }

    @Override
    protected void bindEvent() {

    }
}
