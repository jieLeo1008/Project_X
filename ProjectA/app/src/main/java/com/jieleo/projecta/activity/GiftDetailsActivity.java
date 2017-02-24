package com.jieleo.projecta.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jieleo.projecta.R;
import com.jieleo.projecta.bean.gift.GiftDetailsBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class GiftDetailsActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "GiftDetailsActivity";
    private GiftDetailsBean.DataBean.ItemsBean itemsBean;
    private ScrollView scrollView;
    private Banner banner;
    private TextView shortDescriotionTv, nameTv, priceTv, likesTv, topTv;
    private ImageView backWhiteIv, backDarkIv, shopWhiteIv,shopDarkIv;
    private WebView webView;
    private List<String> bannerUrl;
    private CheckBox checkBox;
    private RelativeLayout relativeWhiteLayout, relativeDarkLayout;

    @Override
    public int setLayout() {
        return R.layout.activity_gift_details;
    }

    @Override
    protected void initView() {
        scrollView = (ScrollView) findViewById(R.id.scrollView_gift_details);
        banner = (Banner) findViewById(R.id.banner_gift_details);
        shortDescriotionTv = (TextView) findViewById(R.id.tv_short_description_gift_details);
        nameTv = (TextView) findViewById(R.id.tv_name_gift_details);
        priceTv = (TextView) findViewById(R.id.tv_price_gift_details);
        likesTv = (TextView) findViewById(R.id.tv_likes_gift_details);
        webView = (WebView) findViewById(R.id.web_view_gift_details);
        checkBox = (CheckBox) findViewById(R.id.checkbox_heart_gift_details);
        topTv = (TextView) findViewById(R.id.tv_top_gift_details);
        backWhiteIv = (ImageView) findViewById(R.id.iv_back_white_gift_page);
        shopWhiteIv = (ImageView) findViewById(R.id.iv_shop_white_gift_details);
        shopDarkIv = (ImageView) findViewById(R.id.iv_shop_dark_gift_details);
        backDarkIv = (ImageView) findViewById(R.id.iv_back_dark_gift_page);
        relativeWhiteLayout = (RelativeLayout) findViewById(R.id.relative_top_white_gift_details);
        relativeDarkLayout = (RelativeLayout) findViewById(R.id.relative_top_dark_gift_details);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("details");
        itemsBean = bundle.getParcelable("itemdetails");
        setBanner();
        shortDescriotionTv.setText(itemsBean.getShort_description());
        Log.d(TAG, itemsBean.getShort_description());
        Log.d(TAG, itemsBean.getName());
        nameTv.setText(itemsBean.getName());
        priceTv.setText("¥" + itemsBean.getSkus().get(0).getFixed_price());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setSupportZoom(true);
        webView.loadDataWithBaseURL(null, getNewContent(itemsBean.getDetail_html()), "text/html", "utf-8", null);

    }


    @Override
    protected void bindEvent() {
        relativeWhiteLayout.setVisibility(View.INVISIBLE);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(GiftDetailsActivity.this, "选中", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(GiftDetailsActivity.this, "未选中", Toast.LENGTH_SHORT).show();
                }
            }
        });


        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY - oldScrollY > 0 && scrollY > 350) {
                    relativeWhiteLayout.setVisibility(View.VISIBLE);
                    relativeDarkLayout.setVisibility(View.INVISIBLE);

                } else if (scrollY - oldScrollY < 0 && scrollY < 350) {
                    relativeWhiteLayout.setVisibility(View.INVISIBLE);
                    relativeDarkLayout.setVisibility(View.VISIBLE);

                }
            }
        });

        backWhiteIv.setOnClickListener(this);
        shopWhiteIv.setOnClickListener(this);
        shopDarkIv.setOnClickListener(this);
        backDarkIv.setOnClickListener(this);
    }



    private void setBanner() {
        bannerUrl = new ArrayList<>();
        for (int i = 0; i < itemsBean.getImage_urls().size(); i++) {
            bannerUrl.add(itemsBean.getImage_urls().get(i));
        }
        banner.setImageLoader(new ImageLoader());
        banner.setImages(bannerUrl);
        banner.isAutoPlay(false);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.start();
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_dark_gift_page:
                finish();
                break;
            case R.id.iv_back_white_gift_page:
                finish();
                break;
            case R.id.iv_shop_dark_gift_details:

                break;
            case R.id.iv_shop_white_gift_details:

                break;

            //TODO 完成popipWindow

        }
    }

    class ImageLoader extends com.youth.banner.loader.ImageLoader {


        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }

    }

}
