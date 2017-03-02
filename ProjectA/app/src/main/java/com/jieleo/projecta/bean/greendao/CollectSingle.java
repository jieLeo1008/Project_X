package com.jieleo.projecta.bean.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by jie on 2017/3/2.
 */
@Entity
public class CollectSingle {
    @Id
    private Long id;
    private String coverImageUrl;
    private String detailsUrl;
    @Generated(hash = 1694773699)
    public CollectSingle(Long id, String coverImageUrl, String detailsUrl) {
        this.id = id;
        this.coverImageUrl = coverImageUrl;
        this.detailsUrl = detailsUrl;
    }
    @Generated(hash = 1651765869)
    public CollectSingle() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCoverImageUrl() {
        return this.coverImageUrl;
    }
    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }
    public String getDetailsUrl() {
        return this.detailsUrl;
    }
    public void setDetailsUrl(String detailsUrl) {
        this.detailsUrl = detailsUrl;
    }
}
