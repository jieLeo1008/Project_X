package com.jieleo.projecta.inter;

/**
 * Created by jie on 2017/2/12.
 */

public interface NetInter {
    void startRequest(String url,CallBack<String> callBack);

    <T> void startRequest(String url,Class<T> tClass,CallBack<T> callBack);
}
