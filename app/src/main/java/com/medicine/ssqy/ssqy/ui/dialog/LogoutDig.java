package com.medicine.ssqy.ssqy.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.sj.mylibrary.Control.ActivityController;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.db.TempUser;
import com.medicine.ssqy.ssqy.ui.activity.HomeActivity;
import com.medicine.ssqy.ssqy.ui.activity.LoginActivity;
import com.medicine.ssqy.ssqy.ui.activity.SettingActivity;
import com.orhanobut.logger.Logger;

/**
 * Created by Amy on 2016/12/16.
 */
public class LogoutDig extends Dialog implements View.OnClickListener {
    private Button mBtnCancelDigLogout;
    private Button mBtnConfirmDigModifyLogout;
    private Context mContext;
    
    
    public LogoutDig(Context context) {
        super(context);
        mContext = context;
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setGravity(Gravity.CENTER);
        this.setContentView(R.layout.dig_modify_logout);
        initViews();
        initDatas();
    }
    
    private void initViews() {
        mBtnCancelDigLogout = (Button) findViewById(R.id.btn_cancel_dig_logout);
        mBtnConfirmDigModifyLogout = (Button) findViewById(R.id.btn_confirm_dig_modify_logout);
        
        mBtnCancelDigLogout.setOnClickListener(this);
        mBtnConfirmDigModifyLogout.setOnClickListener(this);
    }
    
    private void initDatas() {
    }
    
    public void showSelf() {
        this.getWindow().setWindowAnimations(R.style.diganim);
        this.show();
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel_dig_logout:
                this.dismiss();
                break;
            case R.id.btn_confirm_dig_modify_logout:
                //确定
                
                doLogOut();
                this.dismiss();
                
                break;
            
        }
    }
    
    private void doLogOut() {
        SharePLogin.saveIsFree(false);
        Logger.e("xx"+SharePLogin.isFree());
        TempUser.reset();
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
        SettingActivity settingActivity = ActivityController.getActivity(SettingActivity.class);
        settingActivity.finish();
        HomeActivity homeActivity = ActivityController.getActivity(HomeActivity.class);
        homeActivity.finish();
    }
}
