package com.jieleo.projecta.bean.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by yuyongjie on 17/3/3.
 */

@Entity
public class Collect {
    @Id
    private Long id;

    private String collectName;
    private String collectCoverIconUrl;
    private String collectContentUrl;
    @Generated(hash = 256505826)
    public Collect(Long id, String collectName, String collectCoverIconUrl,
            String collectContentUrl) {
        this.id = id;
        this.collectName = collectName;
        this.collectCoverIconUrl = collectCoverIconUrl;
        this.collectContentUrl = collectContentUrl;
    }
    @Generated(hash = 1726975718)
    public Collect() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCollectName() {
        return this.collectName;
    }
    public void setCollectName(String collectName) {
        this.collectName = collectName;
    }
    public String getCollectCoverIconUrl() {
        return this.collectCoverIconUrl;
    }
    public void setCollectCoverIconUrl(String collectCoverIconUrl) {
        this.collectCoverIconUrl = collectCoverIconUrl;
    }
    public String getCollectContentUrl() {
        return this.collectContentUrl;
    }
    public void setCollectContentUrl(String collectContentUrl) {
        this.collectContentUrl = collectContentUrl;
    }
}
