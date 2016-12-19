package com.medicine.ssqy.ssqy.ui.listener;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sj.mylibrary.util.AvoidDouble;
import com.example.sj.mylibrary.util.EdtUtil;
import com.example.sj.mylibrary.util.PhoneCheckUtil;
import com.example.sj.mylibrary.util.StringEmptyUtil;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.db.TempUser;
import com.medicine.ssqy.ssqy.entity.LoginEntity;
import com.medicine.ssqy.ssqy.entity.UserEntity;
import com.medicine.ssqy.ssqy.ui.activity.FirstLoginActivity;
import com.medicine.ssqy.ssqy.ui.activity.HomeActivity;
import com.medicine.ssqy.ssqy.ui.activity.LoginActivity;

/**
 * Created by Administrator on 2016/11/14.
 */
public class LoginBtClick extends BaseOnClick<LoginActivity> {
    public LoginBtClick(LoginActivity loginActivity) {
        super(loginActivity);
    }
    Button mButton;
    private String[] mTitles={" 登录中   ","  登录中。","      登录中。。","          登录中。。。"};
    private int nowPos=0;
    private String pwd;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    startChangeText();
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    public void onClick(View v) {
        AvoidDouble.avoid(v,this);
   
        String phoneNum= EdtUtil.getEdtText(mParent.mEdtPhoneLogin);
        pwd=EdtUtil.getEdtText(mParent.mEdtPwdLogin);
        if (StringEmptyUtil.isEmpty(phoneNum)){
            Toast.makeText(mContext, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (StringEmptyUtil.isEmpty(pwd)){
            Toast.makeText(mContext, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        
        if (!PhoneCheckUtil.isRight(phoneNum)){
            Toast.makeText(mContext, "请检查手机号格式", Toast.LENGTH_SHORT).show();
            return;
        }
    
        if (pwd.length()<6){
            Toast.makeText(mContext, "请检查密码格式", Toast.LENGTH_SHORT).show();
            return;
        }
        mButton= (Button) v;
        
        doNetCheck();
        doLoginOK();
    }
    
    private void doLoginOK() {
        LoginEntity loginEntity=new LoginEntity();
        loginEntity.setFisrtLogin(true);
        loginEntity.setState(true);
        loginEntity.setIsformally(true);
        loginEntity.setUid(123456);
        loginEntity.setType("phone");
        loginEntity.setUseraccount("13718454853");
        loginEntity.setNickName("小飞侠");
        loginEntity.setHeadPicUrl("http://pic74.nipic.com/file/20150802/21532318_173943681365_2.jpg");
        loginEntity.setSex("woman");
        loginEntity.setLevel(1);
        loginEntity.setFisrtLogin(true);
        
        SharePLogin.saveIsFree(true);
        SharePLogin.saveUsername(loginEntity.getUseraccount());
        SharePLogin.saveUserpwd(pwd);
        SharePLogin.saveUid(loginEntity.getUid());
    
        UserEntity userEntity=new UserEntity();
        userEntity.setState(loginEntity.isState());
        userEntity.setUid(loginEntity.getUid());
        userEntity.setUseraccount(loginEntity.getUseraccount());
        userEntity.setNickName(loginEntity.getNickName());
        userEntity.setHeadPicUrl(loginEntity.getHeadPicUrl());
        userEntity.setSex(loginEntity.getSex());
        userEntity.setLevel(loginEntity.getLevel());
        userEntity.setFirstLogin(loginEntity.isFisrtLogin());
        TempUser.saveOrUpdateUser(userEntity);
    
        Bundle bundle=new Bundle();
        bundle.putSerializable("entity",loginEntity);
    
      
        if (loginEntity.isFisrtLogin()){
            mParent.goToActivity(FirstLoginActivity.class);
        }else {
            mParent.goToActivity(HomeActivity.class);
        }
        mParent.finish();
    }
    
    private void startChangeText() {
        mButton.setText(mTitles[nowPos++%4]);
        mHandler.sendEmptyMessageDelayed(1,600);
    }
    
    private void doNetCheck() {
        if (!mParent.isLoging()){
            startChangeText();
            mParent.setLoging(true);
//            xxx
        }else {
            Toast.makeText(mContext, "正在努力登录中，请稍等。。", Toast.LENGTH_SHORT).show();
        }
    }
}

