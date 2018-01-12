package com.medicine.ssqy.ssqy.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sj.mylibrary.Control.ActivityController;
import com.example.sj.mylibrary.net.NetCallback;
import com.example.sj.mylibrary.net.NetForJson;
import com.example.sj.mylibrary.util.StringEmptyUtil;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.common.URLConstant;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.db.TempUser;
import com.medicine.ssqy.ssqy.entity.UserPwdEntity;
import com.medicine.ssqy.ssqy.ui.activity.HomeActivity;
import com.medicine.ssqy.ssqy.ui.activity.IndexActivity;
import com.medicine.ssqy.ssqy.ui.activity.LoginActivity;
import com.medicine.ssqy.ssqy.ui.activity.SettingActivity;

/**
 * Created by Amy on 2016/12/16.
 */
public class AccoutDig extends Dialog implements View.OnClickListener {
    private EditText mEdtOldPwdDigAccount;
    private EditText mEdtNewPwd1DigAccount;
    private EditText mEdtNewPwd2DigAccount;
    private Button mBtnCancelDigAccount;
    private Button mBtnConfirmDigAccount;
    private NetForJson mNetForJson;
    private String mPwd1;
    private String mPwd2;
    private Context mContext;
    public AccoutDig(Context context) {
        super(context);
        mContext=context;
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setGravity(Gravity.CENTER);
        this.setContentView(R.layout.dig_account);
        initViews();
        initDatas();
    }
    
    private void initViews() {
        mEdtOldPwdDigAccount = (EditText) findViewById(R.id.edt_old_pwd_dig_account);
        mEdtNewPwd1DigAccount = (EditText) findViewById(R.id.edt_new_pwd1_dig_account);
        mEdtNewPwd2DigAccount = (EditText) findViewById(R.id.edt_new_pwd2_dig_account);
        mBtnCancelDigAccount = (Button) findViewById(R.id.btn_cancel_dig_account);
        mBtnConfirmDigAccount = (Button) findViewById(R.id.btn_confirm_dig_account);
    
    }
    
    private void initDatas() {
        mBtnCancelDigAccount.setOnClickListener(this);
        mBtnConfirmDigAccount.setOnClickListener(this);
    }
    public void showSelf() {
        this.getWindow().setWindowAnimations(R.style.diganim);
        this.show();
    }
    
    private void doNet() {
        mPwd1=mEdtNewPwd1DigAccount.getText().toString();
        mPwd2=mEdtNewPwd2DigAccount.getText().toString();
        
        if (StringEmptyUtil.isEmpty(mPwd1)||StringEmptyUtil.isEmpty(mPwd2)){
            Toast.makeText(mContext, "请输入密码！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!mPwd1.equals(mPwd2)){
            Toast.makeText(mContext, "请确认两次密码填写一致！", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(mContext, "正在保存您的新密码，请稍后", Toast.LENGTH_SHORT).show();
        mBtnConfirmDigAccount.setOnClickListener(null);
        if (mNetForJson==null) {
            mNetForJson=new NetForJson(URLConstant.USERPWD_URL, new NetCallback<UserPwdEntity>() {
                @Override
                public void onSuccess(UserPwdEntity entity) {
                    
                    if (entity != null&&entity.isState()) {
                        
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(mContext, "密码已重置，请牢记您的密码呦！", Toast.LENGTH_SHORT).show();
                                AccoutDig.this.dismiss();
                                doLogOut();
                            }
                        },1000);
                      
                    }else {
                        
                        Toast.makeText(mContext, "设置新密码失败，请重试！", Toast.LENGTH_SHORT).show();
                    }
                }
                
                @Override
                public void onError() {
                    Toast.makeText(mContext, "网络错误，请检查网络状态！", Toast.LENGTH_SHORT).show();
                    mBtnConfirmDigAccount.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            
                            doNet();
                        }
                    });
                }
                
                @Override
                public void onFinish() {
                    
                }
            });
        }
        mNetForJson.addParam("phone", SharePLogin.getLastPhone());
        mNetForJson.addParam("userpwd",mPwd1);
        mNetForJson.excute();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel_dig_account:
                this.dismiss();
            
                break;
            case R.id.btn_confirm_dig_account:
         
                doNet();
                break;
        
        }
    }
    
    private void doLogOut() {
        SharePLogin.saveIsFree(false);
//        Logger.e("xx"+SharePLogin.isFree());
        SharePLogin.removeUid();
        TempUser.reset();
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
        try {
            SettingActivity settingActivity = ActivityController.getActivity(SettingActivity.class);
            settingActivity.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            HomeActivity homeActivity = ActivityController.getActivity(HomeActivity.class);
            homeActivity.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            IndexActivity indexActivity = ActivityController.getActivity(IndexActivity.class);
            indexActivity.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
