package com.jieleo.projecta.fragment;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jieleo.projecta.R;
import com.jieleo.projecta.bean.gift.GiftDetailsBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by jie on 2017/2/28.
 */

public class OtherDetailsFragment extends BaseFragment {

    private GiftDetailsBean.DataBean.ItemsBean itemsBean;
    private WebView webView;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_other_details;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        webView = (WebView) view.findViewById(R.id.web_view_details_single);

    }

    @Override
    protected void initData() {
        Bundle bundle=getArguments();
        itemsBean=bundle.getParcelable("itemsDetails");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setSupportZoom(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(itemsBean.getUrl());
    }




    @Override
    protected void bindEvent() {

    }

    @Override
    public void onClick(View v) {

    }
}
