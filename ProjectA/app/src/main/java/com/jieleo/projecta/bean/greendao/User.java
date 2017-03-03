package com.jieleo.projecta.bean.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by yuyongjie on 17/3/3.
 */

@Entity
public class User {
    @Id
    private Long  id;
    private String userName;
    private String userId;
    private String userIcon;
    @Generated(hash = 1635852392)
    public User(Long id, String userName, String userId, String userIcon) {
        this.id = id;
        this.userName = userName;
        this.userId = userId;
        this.userIcon = userIcon;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserIcon() {
        return this.userIcon;
    }
    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }
}
