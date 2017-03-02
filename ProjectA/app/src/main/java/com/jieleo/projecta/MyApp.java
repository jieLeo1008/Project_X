package com.jieleo.projecta;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.jieleo.projecta.bean.DaoMaster;
import com.jieleo.projecta.bean.DaoSession;

/**
 * Created by yuyongjie on 17/2/23.
 */


public class MyApp extends Application {
    public static Context mContext;
    public static DaoMaster mDaoMaster;
    public static DaoSession mDaoSession;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();
    }

    public static Context getmContext() {
        return mContext;
    }

    public static DaoMaster getmDaoMaster(){
        //初始化helper对象
        DaoMaster.DevOpenHelper helper =new DaoMaster.DevOpenHelper(getmContext(),"MyCollection.db",null);
        mDaoMaster=new DaoMaster(helper.getWritableDb());
        return mDaoMaster;
    }

    public static DaoSession getmDaoSession(){
        if (mDaoSession==null){
            if (mDaoMaster==null){
                mDaoMaster=getmDaoMaster();
            }
        }
        mDaoSession=mDaoMaster.newSession();
        return mDaoSession;
    }


}
