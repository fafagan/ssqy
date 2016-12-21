package com.medicine.ssqy.ssqy.ui.activity;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePFirst;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.util.PermissionUtils;
import com.orhanobut.logger.Logger;

public class MainActivity extends KBaseActivity implements View.OnClickListener {
    private Button mBtLoginActivityWelcome;
    private Button mBtRegActivityWelcome;
    
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Logger.e("xx"+SharePLogin.isFree());
            switch (msg.what) {
             
                case 1:
                    if (!SharePFirst.isFirst()){
                        if (SharePLogin.isFree()){
                          //  goToActivity(FirstLoginActivity.class);
                       goToActivity(HomeActivity.class);
                      //    goToActivity(LoginActivity.class);
                        }else {
                            goToActivity(LoginActivity.class);
                        }
                        killSelf();
                        return;
                    }
                    showBT();
                    break;
                default:
                    break;
            }
        }
    };
    private PermissionUtils.PermissionGrant mPermissionGrant=new PermissionUtils.PermissionGrant() {
        @Override
        public void onPermissionGranted(int requestCode) {
        
        }
    };
    
    @Override
    public int setRootView() {
        return R.layout.activity_main;
    }
    
    @Override
    public void initViews() {
        mHandler.sendEmptyMessageDelayed(1,1000);
        initBT();
      
        
    }
    
    private void initBT() {
        
        mBtLoginActivityWelcome = (Button) findViewById(R.id.bt_login_activity_welcome);
        mBtRegActivityWelcome = (Button) findViewById(R.id.bt_reg_activity_welcome);
        
        
        mBtLoginActivityWelcome.setOnClickListener(this);
        mBtRegActivityWelcome.setOnClickListener(this);
    }
    
    private void showBT() {
        if (mBtLoginActivityWelcome.getVisibility() != View.GONE) {
            return;
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1f);
        alphaAnimation.setDuration(1000);
        
        mBtLoginActivityWelcome.startAnimation(alphaAnimation);
        mBtRegActivityWelcome.startAnimation(alphaAnimation);
        mBtLoginActivityWelcome.setVisibility(View.VISIBLE);
        mBtRegActivityWelcome.setVisibility(View.VISIBLE);
        
    }
    
    @Override
    public void initDatas() {
    PermissionUtils.requestMultiPermissions(this,mPermissionGrant);
    }
    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionUtils.requestPermissionsResult(this, requestCode, permissions, grantResults, mPermissionGrant);
    }
    @Override
    public boolean isUseTitle() {
        return false;
    }
    
    @Override
    public void onClick(View v) {
        int what = -1;
        switch (v.getId()) {
            
            case R.id.bt_login_activity_welcome:
                what = 1;
                break;
            case R.id.bt_reg_activity_welcome:
                what = 2;
                break;
        }
        
        doChangeScene(what);
        killSelf();
    }
    
    private void doChangeScene(int what) {
        switch (what) {
            case 1:
                if (!SharePFirst.isFirst()&&SharePLogin.isFree()) {
                    goToActivity(HomeActivity.class);
                } else {
                    //123
                    
                    goToActivity(LoginActivity.class);
                }
                break;
            case 2:
//                if (SharePLogin.isFree()) {
//                    goToActivity(HomeActivity.class);
//                } else {
//                    goToActivity(LoginActivity.class);
//                }
                goToActivity(RegActivity.class);
                break;
            default:
                break;
        }
    }
    
    
}
