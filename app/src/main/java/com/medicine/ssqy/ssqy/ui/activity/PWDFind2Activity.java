package com.medicine.ssqy.ssqy.ui.activity;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.sj.mylibrary.net.NetCallback;
import com.example.sj.mylibrary.net.NetForJson;
import com.example.sj.mylibrary.util.StringEmptyUtil;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.common.URLConstant;
import com.medicine.ssqy.ssqy.entity.UserPwdEntity;

public class PWDFind2Activity extends KBaseActivity {
    private RelativeLayout mActivityPwdfind2;
    private EditText mEdtOldPwdDigAccount;
    private EditText mEdtNewPwd1DigAccount;
    private EditText mEdtNewPwd2DigAccount;
    private Button mBtnCancelDigAccount;
    private Button mBtnConfirmDigAccount;
    
    private String mPwd1,mPwd2;
    private NetForJson mNetForJson;
    private String mPhone;
    @Override
    public int setRootView() {
        return R.layout.activity_pwdfind2;
    }
    
    @Override
    public void initViews() {
        mPhone=this.getIntent().getStringExtra("phone");
        mActivityPwdfind2 = (RelativeLayout) findViewById(R.id.activity_pwdfind2);
        mEdtOldPwdDigAccount = (EditText) findViewById(R.id.edt_old_pwd_dig_account);
        mEdtNewPwd1DigAccount = (EditText) findViewById(R.id.edt_new_pwd1_dig_account);
        mEdtNewPwd2DigAccount = (EditText) findViewById(R.id.edt_new_pwd2_dig_account);
        mBtnCancelDigAccount = (Button) findViewById(R.id.btn_cancel_dig_account);
        mBtnConfirmDigAccount = (Button) findViewById(R.id.btn_confirm_dig_account);
        setTitleCenter("重置密码");
        mBtnConfirmDigAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doNet();
            }
        });
        mBtnCancelDigAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
        
            }
        });
    }
    
    private void doNet() {
        mPwd1=mEdtNewPwd1DigAccount.getText().toString();
        mPwd2=mEdtNewPwd2DigAccount.getText().toString();
        
        if (StringEmptyUtil.isEmpty(mPwd1)||StringEmptyUtil.isEmpty(mPwd2)){
            Toast.makeText(mSelf, "请输入密码！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!mPwd1.equals(mPwd2)){
            Toast.makeText(mSelf, "请确认两次密码填写一致！", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(mSelf, "正在保存您的新密码，请稍后", Toast.LENGTH_SHORT).show();
        mBtnConfirmDigAccount.setOnClickListener(null);
        if (mNetForJson==null) {
            mNetForJson=new NetForJson(URLConstant.USERPWD_URL, new NetCallback<UserPwdEntity>() {
                @Override
                public void onSuccess(UserPwdEntity entity) {
                    if (entity != null&&entity.isState()) {
                       new Handler().postDelayed(new Runnable() {
                           @Override
                           public void run() {
                               Toast.makeText(mSelf, "密码已重置，请牢记您的密码呦！", Toast.LENGTH_SHORT).show();
                               finish();
                           }
                       },1000);
                    }else {
                        Toast.makeText(mSelf, "设置新密码失败，请重试！", Toast.LENGTH_SHORT).show();
                    }
                }
    
                @Override
                public void onError() {
                    Toast.makeText(mSelf, "网络错误，请检查网络状态！", Toast.LENGTH_SHORT).show();
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
        mNetForJson.addParam("phone",mPhone);
        mNetForJson.addParam("userpwd",mPwd1);
        mNetForJson.excute();
    }
    
    @Override
    public void initDatas() {
        
    }
}
