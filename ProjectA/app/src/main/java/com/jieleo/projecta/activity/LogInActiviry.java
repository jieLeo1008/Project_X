package com.jieleo.projecta.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jieleo.projecta.MyApp;
import com.jieleo.projecta.R;
import com.jieleo.projecta.bean.greendao.User;
import com.jieleo.projecta.tool.SPUtils;
import com.jieleo.projecta.tool.UserTool;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

public class LogInActiviry extends BaseActivity implements View.OnClickListener {

    private EditText useName, password;
    private Button button;
    private ImageView sinaShareIv, weChatShareIv, qqShareIv;
    private TextView toHomeTv, toGiftTv, toMallTv, toCategoryTv;
    private User user;

    @Override
    public int setLayout() {
        return R.layout.activity_log_in_activiry;
    }

    @Override
    protected void initView() {
        useName = (EditText) findViewById(R.id.user_name_et);
        password = (EditText) findViewById(R.id.password_et);
        button = (Button) findViewById(R.id.login_btn);
        sinaShareIv = (ImageView) findViewById(R.id.iv_sina_share_login);
        weChatShareIv = (ImageView) findViewById(R.id.iv_wechat_share_login);
        qqShareIv = (ImageView) findViewById(R.id.iv_qq_share_login);
        toHomeTv = (TextView) findViewById(R.id.tv_move_to_home_page);
        toGiftTv = (TextView) findViewById(R.id.tv_move_to_gift_page);
        toMallTv = (TextView) findViewById(R.id.tv_move_to_mall_page);
        toCategoryTv = (TextView) findViewById(R.id.tv_move_to_category_page);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void bindEvent() {
        button.setOnClickListener(this);
        sinaShareIv.setOnClickListener(this);
        weChatShareIv.setOnClickListener(this);
        qqShareIv.setOnClickListener(this);
        toHomeTv.setOnClickListener(this);
        toGiftTv.setOnClickListener(this);
        toMallTv.setOnClickListener(this);
        toCategoryTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                if (useName.getText().toString().equals("12345") && password.getText().toString().equals("12345")) {
                    SPUtils.put(MyApp.getmContext(), "LOGIN", true);
                    finish();
                }
                break;
            case R.id.iv_sina_share_login:
                Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
//回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
                weibo.setPlatformActionListener(new PlatformActionListener() {

                    @Override
                    public void onError(Platform arg0, int arg1, Throwable arg2) {
                        // TODO Auto-generated method stub
                        arg2.printStackTrace();

                    }

                    @Override
                    public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                        // TODO Auto-generated method stub
                        //输出所有授权信息
                        arg0.getDb().exportData();
                        //成功授权的回调
                        if (arg1 == arg0.ACTION_USER_INFOR) {
                            PlatformDb platformDb = arg0.getDb();
                            user = new User();
                            user.setUserId(platformDb.getUserId());
                            user.setUserName(platformDb.getUserName());
                            user.setUserIcon(platformDb.getUserIcon());

                        }
                        SPUtils.put(MyApp.getmContext(), "LOGIN", true);
                        if (UserTool.getInstance().QueryAll().size() == 0) {
                            UserTool.getInstance().insertUser(user);
                        } else {
                            UserTool.getInstance().deleteAll();
                            UserTool.getInstance().insertUser(user);
                        }
                        sendBroadcast(new Intent("refresh"));
                    }


                    @Override
                    public void onCancel(Platform arg0, int arg1) {
                        // TODO Auto-generated method stub

                    }
                });
//authorize与showUser单独调用一个即可
                weibo.showUser(null);//授权并获取用户信息
                finish();
                break;
            case R.id.iv_wechat_share_login:
                break;
            case R.id.iv_qq_share_login:
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
//回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
                qq.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onError(Platform arg0, int arg1, Throwable arg2) {
                        // TODO Auto-generated method stub
                        arg2.printStackTrace();
                    }

                    @Override
                    public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                        // TODO Auto-generated method stub
                        //输出所有授权信息
                        arg0.getDb().exportData();
                        if (arg1 == arg0.ACTION_USER_INFOR) {
                            PlatformDb platformDb = arg0.getDb();
                            user = new User();
                            user.setUserId(platformDb.getUserId());
                            user.setUserName(platformDb.getUserName());
                            user.setUserIcon(platformDb.getUserIcon());
                        }

                        SPUtils.put(MyApp.getmContext(), "LOGIN", true);
                        if (UserTool.getInstance().QueryAll().size() == 0) {
                            UserTool.getInstance().insertUser(user);
                        } else {
                            UserTool.getInstance().deleteAll();
                            UserTool.getInstance().insertUser(user);
                        }
                        sendBroadcast(new Intent("refresh"));
                    }


                    @Override
                    public void onCancel(Platform arg0, int arg1) {
                        // TODO Auto-generated method stub

                    }
                });


//authorize与showUser单独调用一个即可
                qq.showUser(null);
                finish();
                break;
            case R.id.tv_move_to_home_page:
                sendBroadcast(new Intent("moveToHome"));
                finish();
                break;
            case R.id.tv_move_to_gift_page:
                sendBroadcast(new Intent("moveToGift"));
                finish();
                break;
            case R.id.tv_move_to_mall_page:
                sendBroadcast(new Intent("moveToMall"));
                finish();
                break;
            case R.id.tv_move_to_category_page:
                sendBroadcast(new Intent("moveToCategory"));
                finish();
                break;
        }
    }
}
