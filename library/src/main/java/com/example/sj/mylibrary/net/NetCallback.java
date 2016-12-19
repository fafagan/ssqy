package com.example.sj.mylibrary.net;

/**
 * Created by Administrator on 2016/9/1.
 */
public abstract class NetCallback<T> {
    
    public abstract void onSuccess(T entity);
    public abstract void onError();
    public abstract void onFinish();
}
