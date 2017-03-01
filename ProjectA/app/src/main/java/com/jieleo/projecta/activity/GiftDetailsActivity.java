package com.jieleo.projecta.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jieleo.projecta.R;
import com.jieleo.projecta.bean.Collection;
import com.jieleo.projecta.bean.eventbus.PopupWindowBean;
import com.jieleo.projecta.bean.gift.GiftDetailsBean;
import com.jieleo.projecta.bean.mall.MallBodyBean;
import com.jieleo.projecta.inter.MoveToFive;
import com.jieleo.projecta.tool.DaoTool;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
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
    private TextView shortDescriotionTv, nameTv, priceTv, likesTv, topTv, addShopTv, chooseTv, choosedTv, bugNowTv;
    private ImageView backWhiteIv, backDarkIv, shopWhiteIv, shopDarkIv;
    private WebView webView;
    private CheckBox checkBox;
    private RelativeLayout relativeWhiteLayout, relativeDarkLayout;
    private LinearLayout chooseLinerLayout;
    private Intent intent;
    private Bundle bundle;
    private FloatingActionButton floatingActionButton;
    private MallBodyBean.DataBean.ItemsBean itemBean;

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
        chooseTv = bindView(R.id.tv_choose_gift_details);
        choosedTv = bindView(R.id.tv_choosed_gift_details);
        bugNowTv = (TextView) findViewById(R.id.tv_buy_now_gift_details);
        addShopTv = (TextView) findViewById(R.id.tv_add_to_shop_gift_details);
        chooseLinerLayout = bindView(R.id.liner_layout_choose_gift_details);
        floatingActionButton = bindView(R.id.fa_btn_gift_details);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent.getBundleExtra("details") != null) {
            Bundle bundle = intent.getBundleExtra("details");
            itemsBean = bundle.getParcelable("itemsDetails");
        }else {
            Bundle bundle=intent.getBundleExtra("bundle");
            itemBean=bundle.getParcelable("itemsBean");
        }
        setBanner();
        shortDescriotionTv.setText(itemsBean.getShort_description());
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
        floatingActionButton.setVisibility(View.INVISIBLE);
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
                if (scrollY - oldScrollY > 0) {
                    if (scrollY > 350) {
                        relativeWhiteLayout.setVisibility(View.VISIBLE);
                        relativeDarkLayout.setVisibility(View.INVISIBLE);
                    }
                    floatingActionButton.setVisibility(View.INVISIBLE);

                } else if (scrollY - oldScrollY < 0) {
                    if (scrollY < 350) {
                        relativeWhiteLayout.setVisibility(View.INVISIBLE);
                        relativeDarkLayout.setVisibility(View.VISIBLE);
                    }
                    if (scrollY > 500) {
                        floatingActionButton.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        backWhiteIv.setOnClickListener(this);
        shopWhiteIv.setOnClickListener(this);
        shopDarkIv.setOnClickListener(this);
        backDarkIv.setOnClickListener(this);
        addShopTv.setOnClickListener(this);
        chooseLinerLayout.setOnClickListener(this);
        bugNowTv.setOnClickListener(this);
        EventBus.getDefault().register(this);
        floatingActionButton.setOnClickListener(this);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Collection collection=new Collection();
                collection.setName(itemsBean.getName());
                collection.setContentUrl(itemsBean.getDetail_html());
                if (isChecked){
                    Log.d(TAG, "选中");
                    //TODO 完成数据库操作
                }else {
                    Log.d(TAG, "未选中");
                }
            }
        });
    }


    private void setBanner() {
        List<String> bannerUrl = new ArrayList<>();
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
            case R.id.tv_add_to_shop_gift_details://点击添加到购物车
                bundle = new Bundle();
                bundle.putParcelable("itemsBean", itemsBean);
                intent = new Intent(this, PopupWindowActivity.class);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
                break;
            case R.id.liner_layout_choose_gift_details:
                bundle = new Bundle();
                bundle.putParcelable("itemsBean", itemsBean);
                intent = new Intent(this, PopupWindowActivity.class);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
                break;
            case R.id.tv_buy_now_gift_details://弹出菜单
                bundle = new Bundle();
                bundle.putParcelable("itemsBean", itemsBean);
                intent = new Intent(this, PopupWindowActivity.class);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
                break;
            case R.id.fa_btn_gift_details://返回顶部
                scrollView.scrollTo(0, 0);
                break;

        }
    }

    //EventBus接收的方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setChoose(PopupWindowBean popupWindowBean) {
        chooseTv.setText(popupWindowBean.getText() + "     " + popupWindowBean.getCount() + "件");
        choosedTv.setVisibility(View.GONE);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    class ImageLoader extends com.youth.banner.loader.ImageLoader {


        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }

    }

}
