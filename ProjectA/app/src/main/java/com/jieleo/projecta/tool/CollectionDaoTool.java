package com.jieleo.projecta.tool;

import com.jieleo.projecta.MyApp;
import com.jieleo.projecta.bean.greendao.Collection;
import com.jieleo.projecta.bean.greendao.CollectionDao;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by yuyongjie on 17/3/1.
 */

public class CollectionDaoTool {
    private static CollectionDaoTool ourInstance ;

    private CollectionDao mCollectionDao;

    public static CollectionDaoTool getInstance() {
        if (ourInstance==null){
            synchronized (CollectionDaoTool.class){
                if (ourInstance==null){
                    ourInstance=new CollectionDaoTool();
                }
            }
        }
        return ourInstance;
    }

    private CollectionDaoTool() {
        mCollectionDao= MyApp.getmDaoSession().getCollectionDao();
    }

    //增加一个收藏对象
    public void insertCollection(Collection collection){
        mCollectionDao.insert(collection);
    }
    //增加收藏的集合
    public void insertAllCollection(List<Collection> collections){
        mCollectionDao.insertInTx(collections);
    }
    //删除一个收藏对象
    public void deleteCollection(Collection collection){
        mCollectionDao.delete(collection);
    }
    //删除所有
    public  void deleteAll(){
        mCollectionDao.deleteAll();
    }
    //通过name字段删除
    public void deleteByName(String name){
        DeleteQuery<Collection> deleteQuery=mCollectionDao.queryBuilder().where(CollectionDao.Properties.Name.eq(name)).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
    }

    public List<Collection> QueryAll(){
        List<Collection> collections=mCollectionDao.loadAll();
        return collections;
    }

    public  boolean isrepeate(Collection collection){
        QueryBuilder<Collection> queryBuilder=mCollectionDao.queryBuilder().where(CollectionDao.Properties.Name.eq(collection.getName()));
        Long count=queryBuilder.buildCount().count();
        return count>0?true:false;
    }




}
