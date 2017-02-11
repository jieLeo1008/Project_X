package com.jieleo.projecta.bean.homepage;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yuyongjie on 17/2/11.
 */


public class DetailsBean implements Parcelable{
    private String title;

    public DetailsBean() {
    }

    protected DetailsBean(Parcel in) {
        title = in.readString();
    }

    public static final Creator<DetailsBean> CREATOR = new Creator<DetailsBean>() {
        @Override
        public DetailsBean createFromParcel(Parcel in) {
            return new DetailsBean(in);
        }

        @Override
        public DetailsBean[] newArray(int size) {
            return new DetailsBean[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
    }
}
