package com.medicine.ssqy.ssqy.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;

public class PWDFind2Activity extends KBaseActivity {
    private RelativeLayout mActivityPwdfind2;
    private EditText mEdtOldPwdDigAccount;
    private EditText mEdtNewPwd1DigAccount;
    private EditText mEdtNewPwd2DigAccount;
    private Button mBtnCancelDigAccount;
    private Button mBtnConfirmDigAccount;
    
    
    @Override
    public int setRootView() {
        return R.layout.activity_pwdfind2;
    }
    
    @Override
    public void initViews() {
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
                Toast.makeText(mSelf, "密码已重置，请牢记您的密码呦！", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    
    @Override
    public void initDatas() {
        
    }
}
