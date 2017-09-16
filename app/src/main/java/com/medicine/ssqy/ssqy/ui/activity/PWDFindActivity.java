package com.medicine.ssqy.ssqy.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;

public class PWDFindActivity extends KBaseActivity {
    private EditText mEdtPhoneDigModifyPhonenumber;
    private EditText mEdtVerifycodeDigModifyPhonenumber;
    private Button mBtnGetVerifycodeDigModifyPhonenumber;
    private Button mBtnCancelDigModifyPhonenumber;
    private Button mBtnConfirmDigModifyPhonenumber;
    

    
    @Override
    public int setRootView() {
        
        return R.layout.activity_pwdfind;
    }
    
    @Override
    public void initViews() {
        setTitleCenter("找回密码");
        mEdtPhoneDigModifyPhonenumber = (EditText) findViewById(R.id.edt_phone_dig_modify_phonenumber);
        mEdtVerifycodeDigModifyPhonenumber = (EditText) findViewById(R.id.edt_verifycode_dig_modify_phonenumber);
        mBtnGetVerifycodeDigModifyPhonenumber = (Button) findViewById(R.id.btn_get_verifycode_dig_modify_phonenumber);
        mBtnCancelDigModifyPhonenumber = (Button) findViewById(R.id.btn_cancel_dig_modify_phonenumber);
        mBtnConfirmDigModifyPhonenumber = (Button) findViewById(R.id.btn_confirm_dig_modify_phonenumber);
    
        mBtnConfirmDigModifyPhonenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(PWDFind2Activity.class);
                finish();
            }
        });
    }
    
    @Override
    public void initDatas() {
        
    }
}
