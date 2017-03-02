package com.jieleo.projecta.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.jieleo.projecta.MyApp;
import com.jieleo.projecta.R;
import com.jieleo.projecta.tool.SPUtils;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

public class LogInActiviry extends BaseActivity implements View.OnClickListener {

    private EditText useName,password;
    private Button button;
    private ImageView sinaShareIv,weChatShareIv,qqShareIv;
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                if (useName.getText().toString().equals("12345")&&password.getText().toString().equals("12345")){
                    SPUtils.put(MyApp.getmContext(),"LOGIN",true);
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
                    }

                    @Override
                    public void onCancel(Platform arg0, int arg1) {
                        // TODO Auto-generated method stub

                    }
                });
//authorize与showUser单独调用一个即可
                weibo.authorize();//单独授权,OnComplete返回的hashmap是空的
                weibo.showUser(null);//授权并获取用户信息
                Log.d("ProfilePageFragment", weibo.getDb().getUserName());
                Log.d("ProfilePageFragment", weibo.getDb().getUserId());
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
                    }

                    @Override
                    public void onCancel(Platform arg0, int arg1) {
                        // TODO Auto-generated method stub

                    }
                });
//authorize与showUser单独调用一个即可
                qq.authorize();//单独授权,OnComplete返回的hashmap是空的
                qq.showUser(null);//授权并获取用户信息
                break;
        }
    }
}
