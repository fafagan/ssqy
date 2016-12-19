package com.medicine.ssqy.ssqy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sj.mylibrary.util.EdtJava;
import com.example.sj.mylibrary.util.EdtUtil;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.ui.listener.LoginBtClick;
import com.medicine.ssqy.ssqy.ui.listener.LoginPlatClick;
import com.umeng.socialize.UMShareAPI;

public class LoginActivity extends KBaseActivity {
    public EditText mEdtPhoneLogin;
    public View mDividerPhone;
    public EditText mEdtPwdLogin;
    public ImageView mImgvShowPwd;
    public View mDividerPwd;
    public Button mBtLogin;
    public TextView mTvRegLogin;
    public TextView mTvFindPwdLogin;
    public LinearLayout mLayoutLoginQq;
    public LinearLayout mLayoutLoginWx;
    public LinearLayout mLayoutLoginWb;
    public OnEdtFocusChange mOnEdtFocusChange;
    private LoginBtClick mLoginBtClick;
    private LoginPlatClick mLoginPlatClick;
    private boolean mIsLoging;
    public UMShareAPI mShareAPI ;
    
    
    @Override
    public int setRootView() {
        return R.layout.activity_login;
    }
    
    @Override
    public void initViews() {
        setTitleCenter("登 录");
        mEdtPhoneLogin = (EditText) findViewById(R.id.edt_phone_login);
        mDividerPhone = (View) findViewById(R.id.divider_phone);
        mEdtPwdLogin = (EditText) findViewById(R.id.edt_pwd_login);
        mImgvShowPwd = (ImageView) findViewById(R.id.imgv_show_pwd);
        mDividerPwd = (View) findViewById(R.id.divider_pwd);
        mBtLogin = (Button) findViewById(R.id.bt_login);
        mTvRegLogin = (TextView) findViewById(R.id.tv_reg_login);
        mTvFindPwdLogin = (TextView) findViewById(R.id.tv_find_pwd_login);
        mLayoutLoginQq = (LinearLayout) findViewById(R.id.layout_login_qq);
        mLayoutLoginWx = (LinearLayout) findViewById(R.id.layout_login_wx);
        mLayoutLoginWb = (LinearLayout) findViewById(R.id.layout_login_wb);
        mTitleCenter.setFocusable(true);
        mTitleCenter.setFocusableInTouchMode(true);
        mTitleCenter.requestFocus();
        
        mEdtPwdLogin.setInputType(EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
        EdtJava.makeEdtJava(mEdtPwdLogin);
        
        setEdtFoucsChanged();
        setOnclick();

    }
    
    private void setOnclick() {
        if (mLoginBtClick == null) {
            mLoginBtClick = new LoginBtClick(this);
        }
        
        mBtLogin.setOnClickListener(mLoginBtClick);
        
        mImgvShowPwd.setTag(true);
        mImgvShowPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isHide = (boolean) mImgvShowPwd.getTag();
                mImgvShowPwd.setTag(!isHide);
                if (isHide) {
                    mEdtPwdLogin.setInputType(EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    EdtJava.makeEdtJava(mEdtPwdLogin);
                    mImgvShowPwd.setImageResource(R.drawable.pwd_state_true);
                } else {
                    mEdtPwdLogin.setInputType(EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
                    EdtJava.makeEdtJava(mEdtPwdLogin);
                    mImgvShowPwd.setImageResource(R.drawable.pwd_state_false);
                }
                mEdtPwdLogin.setSelection(EdtUtil.getEdtText(mEdtPwdLogin).length());
            }
        });
        
        mTvFindPwdLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("phone", EdtUtil.getEdtText(mEdtPhoneLogin));
                goToActivity(PWDFindActivity.class, bundle);
            }
        });
        mTvRegLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(RegActivity.class);
            }
        });
        
        if (mLoginPlatClick == null) {
            mLoginPlatClick = new LoginPlatClick(this);
        }
        mLayoutLoginQq.setOnClickListener(mLoginPlatClick);
        mLayoutLoginWx.setOnClickListener(mLoginPlatClick);
        mLayoutLoginWb.setOnClickListener(mLoginPlatClick);
    }
    
    private void setEdtFoucsChanged() {
        if (mOnEdtFocusChange == null) {
            mOnEdtFocusChange = new OnEdtFocusChange();
        }
        
        mEdtPhoneLogin.setOnFocusChangeListener(mOnEdtFocusChange);
        mEdtPwdLogin.setOnFocusChangeListener(mOnEdtFocusChange);
    }
    
    @Override
    public void initDatas() {
        mShareAPI=UMShareAPI.get(this);
    }
    public boolean isLoging() {
        return mIsLoging;
    }
    
    public void setLoging(boolean loging) {
        mIsLoging = loging;
    }
    private class OnEdtFocusChange implements View.OnFocusChangeListener {
        
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (v == mEdtPhoneLogin) {
                if (hasFocus) {
                    mDividerPhone.setBackgroundColor(getResources().getColor(R.color.background_color_dark));
                } else {
                    mDividerPhone.setBackgroundColor(getResources().getColor(R.color.login_divider_color_normal));
                }
                
            }
            if (v == mEdtPwdLogin) {
                if (hasFocus) {
                    mDividerPwd.setBackgroundColor(getResources().getColor(R.color.background_color_dark));
                } else {
                    mDividerPwd.setBackgroundColor(getResources().getColor(R.color.login_divider_color_normal));
                }
            }
        }
    }
    
    @Override
    public void onBackPressed() {
        
        if (mIsLoging){
            //cancelLogin();
            mIsLoging=false;
            return;
        }
        super.onBackPressed();
    }
    
    private void cancelLogin() {
        
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        
    }
}
