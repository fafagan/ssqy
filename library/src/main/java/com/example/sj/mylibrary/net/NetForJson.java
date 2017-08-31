package com.example.sj.mylibrary.net;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.xutils.x.http;

/**
 * Created by Administrator on 2016/9/1.
 * 
 * 实现对于Json格式数据的网络访问
 */
public class NetForJson implements Callback.CommonCallback<String> {
    
    private String mUrl;
    private Type clazz;
    private NetCallback mNetCallback;
    private boolean isPost;
    private RequestParams requestParams;
    private Cancelable mCancelable;
    private Map<String,Object> mCookies;
    public NetForJson(String url, NetCallback netCallback, boolean isPost) {
        this.mUrl = url;
        mNetCallback = netCallback;
        this.isPost = isPost;
        requestParams=new RequestParams(url);
    
        //根据泛型获取Type类型，从而省略传入解析类型参数
        if (netCallback!=null){
            ParameterizedType parameterizedType = (ParameterizedType) netCallback
                    .getClass().getGenericSuperclass();
            clazz = parameterizedType.getActualTypeArguments()[0];
        }
    }
    
    public NetForJson(String url, NetCallback netCallback) {
        this(url,netCallback,false);
    }
    
    //执行访问
    public void excute(){
        if (mCookies!=null&&mCookies.size()!=0){
            
            StringBuilder stringBuilder=new StringBuilder();
            Set<String> keys = mCookies.keySet();
            for (String key:keys){
                stringBuilder.append(key+"="+mCookies.get(key)+"; ");
            }
            requestParams.setHeader("Cookie", stringBuilder.substring(0,stringBuilder.length()-2));
        }
        if (isPost){
            doPost();
        }else {
            doGet();
        }
    }
    //撤销访问
    public void cancel(){
        if (mCancelable!=null){
            mCancelable.cancel();
        }
    }
    
    
    private void doGet() {
        mCancelable= x.http().get(requestParams,this);
    }
    
    private void doPost() {
        mCancelable = http().post(requestParams, this);
    }
    
    //加参数
    public void addParam(String key,Object value){
        requestParams.removeParameter(key);
        if (isPost){
           
            requestParams.addBodyParameter(key,value+"");
            
        }else {
            requestParams.addParameter(key,value);
        }
        
    }
    
    //换参数
    public void replaceParam(String oldkey,Object newValue){
        requestParams.removeParameter(oldkey);
        addParam(oldkey,newValue);
    }
    //移除参数
    public void removeParam(String key){
        requestParams.removeParameter(key);
        
    }
    
    //添加Cookie
    public void addCookie(String key,Object value){
        if (mCookies==null){
            mCookies=new HashMap<>();
        }
        
        mCookies.put(key,value);
    }
    
    @Override
    public void onSuccess(String result) {
        Gson gson=new Gson();
        Logger.e(result);
        mNetCallback.onSuccess(gson.fromJson(result,clazz));
    }
    
    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        ex.printStackTrace();
        mNetCallback.onError();
    }
    
    @Override
    public void onCancelled(CancelledException cex) {
        
    }
    
    @Override
    public void onFinished() {
        mNetCallback.onFinish();
    }
}
