package com.jieleo.projecta.tool;

import com.jieleo.projecta.MyApp;
import com.jieleo.projecta.bean.greendao.CollectSingleDao;

/**
 * Created by jie on 2017/3/2.
 */
public class CollectionSingleTool {
    private static CollectionSingleTool ourInstance;

    private CollectSingleDao mCollectionSingleDao;

    public static CollectionSingleTool getInstance() {
        if (ourInstance==null){
            synchronized (CollectionSingleTool.class){
                if (ourInstance==null){
                    ourInstance=new CollectionSingleTool();
                }
            }
        }
        return ourInstance;
    }

    private CollectionSingleTool() {
        mCollectionSingleDao= MyApp.getmDaoSession().getCollectSingleDao();
    }
    //TODO 完成数据库方法
}
