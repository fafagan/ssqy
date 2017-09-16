package com.medicine.ssqy.ssqy.base;

import android.content.Context;

import com.example.sj.mylibrary.base.BaseApplication;
import com.medicine.ssqy.ssqy.R;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.PlatformConfig;

import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;
import cn.smssdk.SMSSDK;

//import com.hyphenate.chat.EMOptions;

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
        //三方登录
        PlatformConfig.setQQZone("1105746827", "akQQqkK7SdDHqnox");
        PlatformConfig.setSinaWeibo("3597239576", "5413f61575a155dc7075b65d00b903db");
        SMSSDK.initSDK(this, "19f417b016350", "edfccec959e6034d6656c41e50cd2ea7");
//        UMShareAPI.get(this);
        
        
        //分享与短信验证
        ShareSDK.initSDK(this);
        JPushInterface.setDebugMode(false);
        JPushInterface.init(this);
    
        //友盟统计
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType. E_UM_NORMAL);
    }
//    private EMOptions initChatOptions(){
//        
//        EMOptions options = new EMOptions();
//        // set if accept the invitation automatically
//        options.setAcceptInvitationAlways(false);
//        // set if you need read ack
//        options.setRequireAck(true);
//        // set if you need delivery ack
//        options.setRequireDeliveryAck(false);
//        
//        //you need apply & set your own id if you want to use google cloud messaging.
//        options.setGCMNumber("324169311137");
//        //you need apply & set your own id if you want to use Mi push notification
//        options.setMipushConfig("2882303761517426801", "5381742660801");
//        //you need apply & set your own id if you want to use Huawei push notification
//        options.setHuaweiPushAppId("10492024");
//        
//        
//        options.allowChatroomOwnerLeave(true);
//        options.setDeleteMessagesAsExitGroup(true);
//        options.setAutoAcceptGroupInvitation(true);
//        
//        return options;
//    }
    @Override
    protected boolean isDebug() {
        return true;
    }
    
    @Override
    protected int initTitle() {
        return R.layout.title_bar;
    }
}
