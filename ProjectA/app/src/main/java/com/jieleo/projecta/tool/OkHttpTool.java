package com.jieleo.projecta.tool;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.jieleo.projecta.inter.CallBack;
import com.jieleo.projecta.inter.NetInter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jie on 2017/2/12.
 */

public class OkHttpTool implements NetInter{
    //声明OkHttpClient对象
    private OkHttpClient mOkHttpClient;
    //声明一个Handler对象 并使其一直处于主线程中
    private Handler mHandler=new Handler(Looper.getMainLooper());

    private Gson mGson;

    public OkHttpTool() {
        mGson=new Gson();
        //设置OkHttpClient对象
        mOkHttpClient =new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS).retryOnConnectionFailure(true).cache(new Cache(Environment.getExternalStorageDirectory(),10*1024*1024)).build();
    }

    @Override
    public void startRequest(String url, final CallBack<String> callBack) {
        Request request =new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onError(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String str =response.body().string();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onsuccess(str);
                    }
                });
            }
        });
    }

    @Override
    public <T> void startRequest(String url, Class<T> tClass, CallBack<T> callBack) {

    }
}
