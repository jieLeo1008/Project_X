package com.jieleo.projecta.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jieleo.projecta.MyApp;
import com.jieleo.projecta.R;
import com.jieleo.projecta.activity.LogInActiviry;
import com.jieleo.projecta.adapter.ProfileFragmentAdapter;
import com.jieleo.projecta.bean.greendao.User;
import com.jieleo.projecta.tool.SPUtils;
import com.jieleo.projecta.tool.UserTool;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by yuyongjie on 17/2/10.
 */


public class ProfilePageFragment extends BaseFragment {
    private Button logOutBtn;
    private ImageView headIv;
    private TextView userNameTv;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private ProfileFragmentAdapter profileFragmentAdapter;
    private RefreshBroadCastReceiver refreshBroadCastReceiver;
    private RelativeLayout relativeLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile_page;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        logOutBtn = (Button) view.findViewById(R.id.logout_btn);
        headIv = (ImageView) view.findViewById(R.id.iv_head_profile_page);
        userNameTv = (TextView) view.findViewById(R.id.tv_user_name_profile_page);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout_profile_page);
        viewPager = (ViewPager) view.findViewById(R.id.vp_profile_page);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.relative_layout_main_profile_page);


        refreshBroadCastReceiver = new RefreshBroadCastReceiver();
        IntentFilter intentFilter = new IntentFilter("refresh");
        getActivity().registerReceiver(refreshBroadCastReceiver, intentFilter);
    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        fragments.add(new ProfileSingleFragment());
        fragments.add(new ProfileStrategyFragment());
        profileFragmentAdapter = new ProfileFragmentAdapter(getChildFragmentManager());
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(profileFragmentAdapter);
        profileFragmentAdapter.setFragments(fragments);


    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            boolean needLogin = (boolean) SPUtils.get(MyApp.getmContext(), "LOGIN", false);
            if (!needLogin) {
                relativeLayout.setVisibility(View.INVISIBLE);
                startActivity(new Intent(getActivity(), LogInActiviry.class));
            } else {
                //如果已经登录过 去数据库获取用户资料设置到登录页上
                User user = UserTool.getInstance().QueryAll().get(0);
                Glide.with(MyApp.getmContext()).load(user.getUserIcon()).into(headIv);
                userNameTv.setText(user.getUserName());
                relativeLayout.setVisibility(View.VISIBLE);
                getContext().sendBroadcast(new Intent("shuaxinjiemian"));
            }
        }
    }


    @Override
    protected void bindEvent() {
        logOutBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout_btn:
                SPUtils.put(MyApp.getmContext(), "LOGIN", false);
                relativeLayout.setVisibility(View.INVISIBLE);
                Platform weibo =ShareSDK.getPlatform(SinaWeibo.NAME);
                Platform qq=ShareSDK.getPlatform(QQ.NAME);
                weibo.removeAccount(true);
                qq.removeAccount(true);
                startActivity(new Intent(getActivity(), LogInActiviry.class));
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(refreshBroadCastReceiver);
    }

    class RefreshBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            if ((boolean) SPUtils.get(MyApp.getmContext(), "LOGIN", false)) {
                User user = UserTool.getInstance().QueryAll().get(0);
                Glide.with(MyApp.getmContext()).load(user.getUserIcon()).into(headIv);
                userNameTv.setText(user.getUserName());
                relativeLayout.setVisibility(View.VISIBLE);
            }
        }
    }


    //    private Button loginBtn;
//    private ImageView qqshareIv;
//    private EditText userNameEt,passWordEt;
//    @Override
//    protected int getLayoutId() {
//        return R.layout.fragment_profile_page;
//    }
//
//    @Override
//    protected void initView(View view, Bundle savedInstanceState) {
//        loginBtn = (Button) view.findViewById(R.id.btn_password_profile_page);
//        qqshareIv = (ImageView) view.findViewById(R.id.iv_qq_share);
//        userNameEt = (EditText) view.findViewById(R.id.et_uesr_name_profile_page);
//        passWordEt = (EditText) view.findViewById(R.id.et_password_profile_page);
//    }
//
//    @Override
//    protected void initData() {
//
//    }
//
//    @Override
//    protected void bindEvent() {
//        loginBtn.setOnClickListener(this);
//        qqshareIv.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_password_profile_page:
//
//                if (userNameEt.getText().toString().equals("12345")&&passWordEt.getText().toString().equals("12345")){
//                    SPUtils.put(MyApp.getmContext(),"LOGIN",true);
//                }
//                break;
//            case R.id.iv_qq_share:
//                Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
////回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
//                weibo.setPlatformActionListener(new PlatformActionListener() {
//
//                    @Override
//                    public void onError(Platform arg0, int arg1, Throwable arg2) {
//                        // TODO Auto-generated method stub
//                        arg2.printStackTrace();
//                    }
//
//                    @Override
//                    public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
//                        // TODO Auto-generated method stub
//                        //输出所有授权信息
//                        arg0.getDb().exportData();
//                    }
//
//                    @Override
//                    public void onCancel(Platform arg0, int arg1) {
//                        // TODO Auto-generated method stub
//
//                    }
//                });
////authorize与showUser单独调用一个即可
//                weibo.authorize();//单独授权,OnComplete返回的hashmap是空的
//                weibo.showUser(null);//授权并获取用户信息
//                Log.d("ProfilePageFragment", weibo.getDb().getUserName());
//                Log.d("ProfilePageFragment", weibo.getDb().getUserId());
//
////移除授权
////weibo.removeAccount(true);
//                break;
//        }
//    }
//
    private void showShare(){
        ShareSDK.initSDK(MyApp.getmContext());
        OnekeyShare oks=new OnekeyShare();

        oks.disableSSOWhenAuthorize();
        oks.setSite(getString(R.string.app_name));
        oks.setTitleUrl("www.baidu.com");
        oks.setText("哈哈哈哈哈哈哈哈");
        oks.setTitle("我是刘浩");
        oks.show(MyApp.getmContext());
    }
}
