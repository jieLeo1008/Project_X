package com.jieleo.projecta.bean.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by yuyongjie on 17/3/4.
 */

@Entity
public class Search {
    @Id
    private Long id;

    private String name;

    private Long time;

    @Generated(hash = 457943435)
    public Search(Long id, String name, Long time) {
        this.id = id;
        this.name = name;
        this.time = time;
    }

    @Generated(hash = 1644193961)
    public Search() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTime() {
        return this.time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
