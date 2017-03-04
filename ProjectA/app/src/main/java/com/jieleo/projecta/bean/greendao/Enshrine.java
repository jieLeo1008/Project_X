package com.jieleo.projecta.bean.greendao;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by yuyongjie on 17/3/4.
 */

@Entity
public class Enshrine implements Parcelable{
    @Id
    private Long id;
    private String enshireName;
    private String enshrineCoverImage;
    private String detailsUrl;
    private int type;
    @Generated(hash = 751700971)
    public Enshrine(Long id, String enshireName, String enshrineCoverImage,
            String detailsUrl, int type) {
        this.id = id;
        this.enshireName = enshireName;
        this.enshrineCoverImage = enshrineCoverImage;
        this.detailsUrl = detailsUrl;
        this.type = type;
    }
    @Generated(hash = 1638193009)
    public Enshrine() {
    }

    protected Enshrine(Parcel in) {
        enshireName = in.readString();
        enshrineCoverImage = in.readString();
        detailsUrl = in.readString();
        type = in.readInt();
    }

    public static final Creator<Enshrine> CREATOR = new Creator<Enshrine>() {
        @Override
        public Enshrine createFromParcel(Parcel in) {
            return new Enshrine(in);
        }

        @Override
        public Enshrine[] newArray(int size) {
            return new Enshrine[size];
        }
    };

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEnshireName() {
        return this.enshireName;
    }
    public void setEnshireName(String enshireName) {
        this.enshireName = enshireName;
    }
    public String getEnshrineCoverImage() {
        return this.enshrineCoverImage;
    }
    public void setEnshrineCoverImage(String enshrineCoverImage) {
        this.enshrineCoverImage = enshrineCoverImage;
    }
    public String getDetailsUrl() {
        return this.detailsUrl;
    }
    public void setDetailsUrl(String detailsUrl) {
        this.detailsUrl = detailsUrl;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(enshireName);
        dest.writeString(enshrineCoverImage);
        dest.writeString(detailsUrl);
        dest.writeInt(type);
    }
}
