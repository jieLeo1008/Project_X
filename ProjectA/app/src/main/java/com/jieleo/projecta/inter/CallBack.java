package com.jieleo.projecta.inter;

/**
 * Created by jie on 2017/2/12.
 */

public interface CallBack<T> {
    void onsuccess(T responce);

    void  onError(Throwable e );

}
