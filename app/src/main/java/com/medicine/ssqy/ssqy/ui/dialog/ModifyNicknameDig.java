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
public class ModifyNicknameDig extends Dialog implements View.OnClickListener {
    
    private Button mBtnDeleteDigModifyNickname;
    private Button mBtnCancelDigModifyNickname;
    private Button mBtnConfirmDigModifyNickname;
    private EditText mEdtNicknameDigModifyNickname;
    private String oldNickName;
    public ModifyNicknameDig(Context context,String oldNickName) {
        super(context);
        this.oldNickName=oldNickName;
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setGravity(Gravity.CENTER);
        this.setContentView(R.layout.dig_modify_nickname);
        initViews();
        initDatas();
    }
    
    private void initViews() {
        mEdtNicknameDigModifyNickname = (EditText) findViewById(R.id.edt_nickname_dig_modify_nickname);
        mBtnDeleteDigModifyNickname = (Button) findViewById(R.id.btn_delete_dig_modify_nickname);
        mBtnCancelDigModifyNickname = (Button) findViewById(R.id.btn_cancel_dig_modify_nickname);
        mBtnConfirmDigModifyNickname = (Button) findViewById(R.id.btn_confirm_dig_modify_nickname);
        
    }
    
    private void initDatas() {
        mBtnDeleteDigModifyNickname.setOnClickListener(this);
        mBtnCancelDigModifyNickname.setOnClickListener(this);
        mBtnConfirmDigModifyNickname.setOnClickListener(this);
    }
    
    public void showSelf() {
        mEdtNicknameDigModifyNickname.setText(oldNickName);
        this.getWindow().setWindowAnimations(R.style.diganim);
        this.show();
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_delete_dig_modify_nickname:
                mEdtNicknameDigModifyNickname.setText("");
                break;
            case R.id.btn_cancel_dig_modify_nickname:
                this.dismiss();
                break;
            case R.id.btn_confirm_dig_modify_nickname:
                //确定
                this.dismiss();
                
                break;
            
        }
    }
}
