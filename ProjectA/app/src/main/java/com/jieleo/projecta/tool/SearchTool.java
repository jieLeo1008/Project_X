package com.jieleo.projecta.tool;

import com.jieleo.projecta.MyApp;
import com.jieleo.projecta.bean.greendao.Search;
import com.jieleo.projecta.bean.greendao.SearchDao;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by yuyongjie on 17/3/4.
 */


public class SearchTool {
    private static  SearchTool ourInstance ;

    private SearchDao mSearchDao;

    public static SearchTool getInstance() {
        if (ourInstance==null){
            synchronized (SearchTool.class){
                if (ourInstance==null){
                    ourInstance=new SearchTool();
                }
            }
        }
        return ourInstance;
    }

    private SearchTool() {
        mSearchDao= MyApp.getmDaoSession().getSearchDao();
    }

    //增加数据
    public void insert(Search search){
        mSearchDao.insert(search);
    }
    //删除全部
    public void deleteAll(){
        mSearchDao.deleteAll();
    }
    //按name删除
    public void deleteByName(String name){
        DeleteQuery<Search> queryBuilder=mSearchDao.queryBuilder().where(SearchDao.Properties.Name.eq(name)).buildDelete();
        queryBuilder.executeDeleteWithoutDetachingEntities();
    }

    public List<Search> queryAll(){
        List<Search> searches=mSearchDao.loadAll();
        return searches;
    }
    //查重
    public  boolean isrepeate(String name){
        QueryBuilder<Search> queryBuilder=mSearchDao.queryBuilder().where(SearchDao.Properties.Name.eq(name));
        Long count=queryBuilder.buildCount().count();
        return count>0?true:false;
    }




}
