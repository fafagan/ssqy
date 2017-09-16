package com.medicine.ssqy.ssqy.js;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.example.sj.mylibrary.net.NetCallback;
import com.example.sj.mylibrary.net.NetForJson;
import com.medicine.ssqy.ssqy.common.URLConstant;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.db.TempUser;
import com.medicine.ssqy.ssqy.entity.UserEntity;
import com.medicine.ssqy.ssqy.entity.UsersetEntity;
import com.medicine.ssqy.ssqy.ui.activity.IndexActivity;

import static com.umeng.socialize.utils.DeviceConfig.context;

/**
 * Created by Administrator on 2017-09-10.
 */

public class QuestionInterface {
    
    private Context mContext;
    private NetForJson mNetForJson;
    private   UserEntity mNowUser;
    public QuestionInterface(Context context) {
        mContext = context;
        mNetForJson=new NetForJson(URLConstant.USERSET_URL, new NetCallback<UsersetEntity>() {
            @Override
            public void onSuccess(UsersetEntity entity) {
                onSaveOk();
            }
        
            @Override
            public void onError() {
                Toast.makeText(mContext, "提交失败，请重试！", Toast.LENGTH_SHORT).show();
            }
        
            @Override
            public void onFinish() {
                
            }
        },true);
    }
    
    private void onSaveOk(){
        mNowUser.setIsFisrtLogin("false");
        TempUser.saveOrUpdateUser(mNowUser);
        mContext.startActivity(new Intent(mContext,IndexActivity.class));
        Activity activity= (Activity) context;
        activity.finish();
    }
    @JavascriptInterface
    public void goToIndexActivity(){
        mNowUser = TempUser.getNowUser(SharePLogin.getUid());
       
        Toast.makeText(mContext, "正在保存您的问卷，请稍等", Toast.LENGTH_SHORT).show();
        mNetForJson.addParam("nickName",mNowUser.getNickName());
        mNetForJson.addParam("sex","man".equals(mNowUser.getSex())?1:2);
        mNetForJson.addParam("marry",mNowUser.getIsMarried());
        mNetForJson.addParam("birth",mNowUser.getBirthDay());
        mNetForJson.addParam("phone",mNowUser.getUseraccount());
        mNetForJson.addParam("job",mNowUser.getJob());
        mNetForJson.addParam("studylevel",mNowUser.getStudyLevel());
        mNetForJson.addParam("isFirstLogin","false");
        mNetForJson.addParam("level",1);
        mNetForJson.excute();
        
    }
}
