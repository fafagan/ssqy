package com.medicine.ssqy.ssqy.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.medicine.ssqy.ssqy.R;

/**
 * Created by Amy on 2016/12/16.
 */
public class AccoutDig extends Dialog implements View.OnClickListener {
    private EditText mEdtOldPwdDigAccount;
    private EditText mEdtNewPwd1DigAccount;
    private EditText mEdtNewPwd2DigAccount;
    private Button mBtnCancelDigAccount;
    private Button mBtnConfirmDigAccount;
    public AccoutDig(Context context) {
        super(context);
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
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel_dig_account:
                this.dismiss();
            
                break;
            case R.id.btn_confirm_dig_account:
                this.dismiss();
            
                break;
        
        }
    }
}
