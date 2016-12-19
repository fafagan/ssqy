package com.medicine.ssqy.ssqy.base;

import android.content.Context;

import com.example.sj.mylibrary.base.BaseApplication;
import com.medicine.ssqy.ssqy.R;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import cn.smssdk.SMSSDK;

/**
 * Created by Administrator on 2016/11/9.
 */
public class KBaseApp extends BaseApplication {
    
    public static Context mContextGlobal;
    @Override
    public void initOthers() {
        mContextGlobal=this;
//        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
//        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
//        Config.REDIRECT_URL = "您新浪后台的回调地址"
        PlatformConfig.setQQZone("1105746827", "akQQqkK7SdDHqnox");
        PlatformConfig.setSinaWeibo("3597239576", "5413f61575a155dc7075b65d00b903db");
        SMSSDK.initSDK(this, "19f417b016350", "edfccec959e6034d6656c41e50cd2ea7");
        UMShareAPI.get(this);
    }
    
    @Override
    protected boolean isDebug() {
        return true;
    }
    
    @Override
    protected int initTitle() {
        return R.layout.title_bar;
    }
}
