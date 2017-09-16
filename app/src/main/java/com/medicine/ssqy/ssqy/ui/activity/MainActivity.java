package com.medicine.ssqy.ssqy.ui.activity;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import com.example.sj.mylibrary.util.StringEmptyUtil;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePFirst;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.db.TempUser;
import com.medicine.ssqy.ssqy.entity.UserEntity;
import com.medicine.ssqy.ssqy.util.PermissionUtils;

public class MainActivity extends KBaseActivity implements View.OnClickListener {
    private Button mBtLoginActivityWelcome;
    private Button mBtRegActivityWelcome;
    
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
             
                case 1:
                    if (!SharePFirst.isFirst()){
    
                        UserEntity nowUser = TempUser.getNowUser(SharePLogin.getUid());
                        if (nowUser==null) {
                            goToActivity(LoginActivity.class);
                        }else {
                            if (SharePLogin.isFree()){
                               if (!StringEmptyUtil.isEmpty(nowUser.isIsFisrtLogin())&&"false".equals(nowUser.isIsFisrtLogin())){
                                   goToActivity(IndexActivity.class);
                               }else {
//                                   goToActivity(IndexActivity.class);
                                   goToActivity(FirstLoginActivity.class);
                               }
                               
                            }else {
                                goToActivity(LoginActivity.class);
                            }
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
    
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//    
//        super.onCreate(savedInstanceState);
//    }
    
    @Override
    public void initViews() {
        if (SharePFirst.isFirst()){
            goToActivity(FirstWelcomeActivity.class);
            finish();
            return;
        }
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
                    goToActivity(IndexActivity.class);
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
