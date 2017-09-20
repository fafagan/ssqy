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
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Amy on 2016/12/16.
 */
public class ModifyMarriageDig extends Dialog implements View.OnClickListener {
    
    private TextView mTvMarriedDigModifySex;
    private TextView mTvUnmarriedDigModifySex;
    private int mIsMarried=0;
    public ModifyMarriageDig(Context context) {
        super(context);
        mContext=context;
        loadingPopWindow = new LoadingPopWindow(context);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setGravity(Gravity.CENTER);
        this.setContentView(R.layout.dig_modify_marriage);
        initViews();
        initDatas();
    }
    
    private void initViews() {
        mTvMarriedDigModifySex = (TextView) findViewById(R.id.tv_married_dig_modify_sex);
        mTvUnmarriedDigModifySex = (TextView) findViewById(R.id.tv_unmarried_dig_modify_sex);
    }
    
    private void initDatas() {
        mTvMarriedDigModifySex.setOnClickListener(this);
        mTvUnmarriedDigModifySex.setOnClickListener(this);
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
            case R.id.tv_married_dig_modify_sex:
                mIsMarried=1;
                break;
            case R.id.tv_unmarried_dig_modify_sex:
                mIsMarried=0;
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
        Logger.e(nowUser.toString());
        mNetForJson.addParam("nickName",nowUser.getNickName());
        mNetForJson.addParam("sex", nowUser.getSex());
        mNetForJson.addParam("marry", mIsMarried+"");
        mNetForJson.addParam("birth", nowUser.getBirthDay());
        mNetForJson.addParam("phone", nowUser.getUseraccount());
        mNetForJson.addParam("job", nowUser.getJob());
        mNetForJson.addParam("studylevel", nowUser.getStudylevel());
        mNetForJson.addParam("isFirstLogin", nowUser.isIsFisrtLogin());
        mNetForJson.addParam("level", 1);
        mNetForJson.addParam("uid", SharePLogin.getUid());
        mNetForJson.excute();
        
        
    }
    
    private void onSaveOk() {
//        TempUser.saveOrUpdateUserFirst(sex,isMarried,birthDay,job,studyLevel);
        Toast.makeText(mContext, "更改婚姻状况成功！", Toast.LENGTH_SHORT).show();
        UserEntity nowUser = TempUser.getNowUser(SharePLogin.getUid());
        nowUser.setIsMarried(mIsMarried+"");
        TempUser.saveOrUpdateUser(nowUser);
        EventBus.getDefault().post(new ModifyInformation());
        cancel();
    }
}
