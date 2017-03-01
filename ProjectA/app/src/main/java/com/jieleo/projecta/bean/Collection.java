package com.jieleo.projecta.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by yuyongjie on 17/3/1.
 */

@Entity
public class Collection {
    @Id
    private Long id;
    private String imageUrl;
    private String name;
    private String shortDescribion;
    private int price;
    private String contentUrl;
    @Generated(hash = 1615003438)
    public Collection(Long id, String imageUrl, String name, String shortDescribion,
            int price, String contentUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.shortDescribion = shortDescribion;
        this.price = price;
        this.contentUrl = contentUrl;
    }
    @Generated(hash = 1149123052)
    public Collection() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getImageUrl() {
        return this.imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getShortDescribion() {
        return this.shortDescribion;
    }
    public void setShortDescribion(String shortDescribion) {
        this.shortDescribion = shortDescribion;
    }
    public int getPrice() {
        return this.price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getContentUrl() {
        return this.contentUrl;
    }
    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }
    

}
