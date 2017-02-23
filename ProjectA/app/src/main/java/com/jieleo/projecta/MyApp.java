package com.jieleo.projecta;

import android.app.Application;
import android.content.Context;

/**
 * Created by yuyongjie on 17/2/23.
 */


public class MyApp extends Application {
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();
    }

    public static Context getmContext() {
        return mContext;
    }
}
