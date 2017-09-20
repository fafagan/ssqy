package com.medicine.ssqy.ssqy.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sj.mylibrary.net.NetCallback;
import com.example.sj.mylibrary.net.NetForJson;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.common.URLConstant;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.db.TempUser;
import com.medicine.ssqy.ssqy.entity.UserEntity;
import com.medicine.ssqy.ssqy.entity.UsersetEntity;
import com.medicine.ssqy.ssqy.eventBus.ModifyInformation;
import com.medicine.ssqy.ssqy.ui.pop.LoadingPopWindow;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Amy on 2016/12/16.
 */
public class ModifySexDig extends Dialog implements View.OnClickListener {
    private TextView mTvManDigModifySex;
    private TextView mTvWomanDigModifySex;
    private int mSex=1;
    public ModifySexDig(Context context) {
        super(context);
        mContext=context;
        loadingPopWindow = new LoadingPopWindow(context);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setGravity(Gravity.CENTER);
        this.setContentView(R.layout.dig_modify_sex);
        initViews();
        initDatas();
      
    }
    
    private void initViews() {
        mTvManDigModifySex = (TextView) findViewById(R.id.tv_man_dig_modify_sex);
        mTvWomanDigModifySex = (TextView) findViewById(R.id.tv_woman_dig_modify_sex);
    
    }
    
    private void initDatas() {
        mTvManDigModifySex.setOnClickListener(this);
        mTvWomanDigModifySex.setOnClickListener(this);
    
        mNetForJson = new NetForJson(URLConstant.USERSET_URL, new NetCallback<UsersetEntity>() {
            @Override
            public void onSuccess(UsersetEntity entity) {
                onSaveOk();
            }
        
            @Override
            public void onError() {
                Toast.makeText(mContext, "保存失败，请重试！", Toast.LENGTH_SHORT).show();
            }
        
            @Override
            public void onFinish() {
                if (loadingPopWindow.isShowing()){
                    loadingPopWindow.dismiss();
                
                }
            
            
            }
        }, true);
    }
    public void showSelf() {
        this.getWindow().setWindowAnimations(R.style.diganim);
        this.show();
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_man_dig_modify_sex:
                mSex=1;
                
                break;
            case R.id.tv_woman_dig_modify_sex:
                mSex=2;
                
                break;
        
        }
        synchServer();
    }
    
    NetForJson mNetForJson;
    private LoadingPopWindow loadingPopWindow;
    private Context mContext;
    private void synchServer() {
        loadingPopWindow.show();
        UserEntity nowUser = TempUser.getNowUser(SharePLogin.getUid());
        mNetForJson.addParam("uid", SharePLogin.getUid());
        mNetForJson.addParam("nickName", nowUser.getNickName());
        mNetForJson.addParam("sex", mSex);
        mNetForJson.addParam("marry", nowUser.getIsMarried());
        mNetForJson.addParam("birth", nowUser.getBirthDay());
        mNetForJson.addParam("phone", nowUser.getUseraccount());
        mNetForJson.addParam("job", nowUser.getJob());
        mNetForJson.addParam("studylevel", nowUser.getStudylevel());
        mNetForJson.addParam("isFirstLogin", nowUser.isIsFisrtLogin());
        mNetForJson.addParam("level", 1);
        mNetForJson.excute();
        
        
    }
    
    private void onSaveOk() {
//        TempUser.saveOrUpdateUserFirst(sex,isMarried,birthDay,job,studyLevel);
        Toast.makeText(mContext, "更改性别成功！", Toast.LENGTH_SHORT).show();
        UserEntity nowUser = TempUser.getNowUser(SharePLogin.getUid());
        nowUser.setSex(mSex+"");
        TempUser.saveOrUpdateUser(nowUser);
        EventBus.getDefault().post(new ModifyInformation());
        cancel();
    }
}
