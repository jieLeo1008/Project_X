package com.jieleo.projecta.tool;

import com.jieleo.projecta.inter.CallBack;
import com.jieleo.projecta.inter.NetInter;

/**
 * Created by jie on 2017/2/12.
 */
public class NetTool implements NetInter{
    private static NetTool ourInstance ;

    private NetInter netInter;
    public static NetTool getInstance() {
        if (ourInstance==null){
            synchronized (NetTool.class){
                if (ourInstance==null){
                    ourInstance=new NetTool();
                }
            }
        }

        return ourInstance;
    }

    private NetTool() {
        netInter=new OkHttpTool();
    }

    @Override
    public void startRequest(String url, CallBack<String> callBack) {
        netInter.startRequest(url,callBack);
    }

    @Override
    public <T> void startRequest(String url, Class<T> tClass, CallBack<T> callBack) {

    }
}
