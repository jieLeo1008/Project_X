package com.jieleo.projecta.tool;

import com.jieleo.projecta.MyApp;
import com.jieleo.projecta.bean.greendao.Collect;
import com.jieleo.projecta.bean.greendao.CollectDao;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by jie on 2017/3/2.
 */
public class CollectTool {
    private static CollectTool ourInstance;

    private CollectDao mCollectDao;

    public static CollectTool getInstance() {
        if (ourInstance==null){
            synchronized (CollectTool.class){
                if (ourInstance==null){
                    ourInstance=new CollectTool();
                }
            }
        }
        return ourInstance;
    }

    private CollectTool() {
        mCollectDao= MyApp.getmDaoSession().getCollectDao();
    }
    //TODO 完成数据库方法

    //增加的方法
    public void insert(Collect collect){
        mCollectDao.insert(collect);
    }
    //通过那么字段删除
    public void deletByName(String name){
        DeleteQuery<Collect>  deleteQuery=mCollectDao.queryBuilder().where(CollectDao.Properties.CollectName.eq(name)).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
    }
    //查询是否存在
    public boolean queryByName(String name){
        QueryBuilder<Collect> queryBuilder=mCollectDao.queryBuilder().where(CollectDao.Properties.CollectName.eq(name));
        Long count=queryBuilder.buildCount().count();
        return count>0?true:false;
    }

    public List<Collect> queryAll(){
        List<Collect> collects=mCollectDao.loadAll();
        return  collects;
    }

}
