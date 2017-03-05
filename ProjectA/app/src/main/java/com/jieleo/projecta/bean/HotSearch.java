package com.jieleo.projecta.bean;

import java.util.List;

/**
 * Created by jie on 2017/3/5.
 */

public class HotSearch
{
    /**
     * code : 200
     * data : {"hot_words":["情侣","生日","项链","手机壳","手表","杯子","双肩包","钱包"],"placeholder":"选份走心好礼送给Ta"}
     * message : OK
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * hot_words : ["情侣","生日","项链","手机壳","手表","杯子","双肩包","钱包"]
         * placeholder : 选份走心好礼送给Ta
         */

        private String placeholder;
        private List<String> hot_words;

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public List<String> getHot_words() {
            return hot_words;
        }

        public void setHot_words(List<String> hot_words) {
            this.hot_words = hot_words;
        }
    }
}
