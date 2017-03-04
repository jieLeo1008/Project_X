package com.jieleo.projecta.tool;

import com.jieleo.projecta.MyApp;
import com.jieleo.projecta.bean.greendao.Enshrine;
import com.jieleo.projecta.bean.greendao.EnshrineDao;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by yuyongjie on 17/3/4.
 */


public class EnshirneTool {
    private static  EnshirneTool ourInstance ;

    private EnshrineDao mEnshrineDao;

    public static EnshirneTool getInstance() {
        if (ourInstance==null){
            synchronized (EnshirneTool.class){
                if (ourInstance==null){
                    ourInstance=new EnshirneTool();
                }
            }
        }
        return ourInstance;
    }

    private EnshirneTool() {
        mEnshrineDao = MyApp.getmDaoSession().getEnshrineDao();
    }

    //增加的方法
    public void insert(Enshrine enshrine){
        mEnshrineDao.insert(enshrine);
    }
    //通过那么字段删除
    public void deletByName(String name){
        DeleteQuery<Enshrine> deleteQuery= mEnshrineDao.queryBuilder().where(EnshrineDao.Properties.EnshireName.eq(name)).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
    }
    //查询是否存在
    public boolean queryByName(String name){
        QueryBuilder<Enshrine> queryBuilder= mEnshrineDao.queryBuilder().where(EnshrineDao.Properties.EnshireName.eq(name));
        Long count=queryBuilder.buildCount().count();
        return count>0?true:false;
    }

    public List<Enshrine> queryAll(){
        List<Enshrine> collects= mEnshrineDao.loadAll();
        return  collects;
    }



}
