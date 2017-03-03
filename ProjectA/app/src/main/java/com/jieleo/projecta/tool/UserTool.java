package com.jieleo.projecta.tool;

import com.jieleo.projecta.MyApp;
import com.jieleo.projecta.bean.greendao.Collection;
import com.jieleo.projecta.bean.greendao.User;
import com.jieleo.projecta.bean.greendao.UserDao;

import java.util.List;

/**
 * Created by yuyongjie on 17/3/3.
 */

public class UserTool {
    private static UserTool ourInstance;
    private UserDao mUserDao;

    public static UserTool getInstance() {
        if (ourInstance==null){
            synchronized (UserTool.class){
                if (ourInstance==null){
                    ourInstance=new UserTool();
                }
            }
        }
        return ourInstance;
    }

    private UserTool() {
        mUserDao= MyApp.getmDaoSession().getUserDao();
    }

    public void insertUser(User user){
        mUserDao.insert(user);
    }

    public void deleteAll(){
        mUserDao.deleteAll();
    }

    public List<User> QueryAll(){
        List<User> users=mUserDao.loadAll();
        return users;
    }

}
