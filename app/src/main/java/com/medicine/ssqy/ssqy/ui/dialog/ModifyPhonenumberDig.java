package com.medicine.ssqy.ssqy.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sj.mylibrary.util.StringEmptyUtil;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.db.TempUser;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by Amy on 2016/12/16.
 */
public class ModifyPhonenumberDig extends Dialog implements View.OnClickListener {
    private EditText mEdtPhoneDigModifyPhonenumber;
    private EditText mEdtVerifycodeDigModifyPhonenumber;
    private Button mBtnGetVerifycodeDigModifyPhonenumber;
    private Button mBtnCancelDigModifyPhonenumber;
    private Button mBtnConfirmDigModifyPhonenumber;
    private Context mContext;
    private OnPhoneOKCallback mOnPhoneOKCallback;
    private String phone;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                Toast.makeText(mContext, "绑定成功！", Toast.LENGTH_SHORT).show();
                TempUser.saveOrUpdatePhone(phone);
                if (mOnPhoneOKCallback != null) {
                    mOnPhoneOKCallback.onOK();
                }
                SMSSDK.unregisterEventHandler(eh);
                dismiss();
            }else {
                Toast.makeText(mContext, "验证异常，请确认信息并重试！", Toast.LENGTH_SHORT).show();
               
            
            }
        }
    };
    EventHandler eh = new EventHandler() {
        
        @Override
        public void afterEvent(int event, int result, Object data) {
            
            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    mHandler.sendEmptyMessage(1);
                    //提交验证码成功
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //获取验证码成功
                    
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //返回支持发送验证码的国家列表
                }
            } else {
                mHandler.sendEmptyMessage(2);
                ((Throwable) data).printStackTrace();
            }
        }
    };
    
    public  interface  OnPhoneOKCallback{
        public void onOK();
    }
    
    public void setOnPhoneOKCallback(OnPhoneOKCallback onPhoneOKCallback) {
        mOnPhoneOKCallback = onPhoneOKCallback;
    }
    
    public ModifyPhonenumberDig(Context context) {
        super(context);
        mContext=context;
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setGravity(Gravity.CENTER);
        this.setContentView(R.layout.dig_modify_phonenumber);
        initViews();
        initDatas();
    }
    
    private void initViews() {
        mEdtPhoneDigModifyPhonenumber = (EditText) findViewById(R.id.edt_phone_dig_modify_phonenumber);
        mEdtVerifycodeDigModifyPhonenumber = (EditText) findViewById(R.id.edt_verifycode_dig_modify_phonenumber);
        mBtnGetVerifycodeDigModifyPhonenumber = (Button) findViewById(R.id.btn_get_verifycode_dig_modify_phonenumber);
        mBtnCancelDigModifyPhonenumber = (Button) findViewById(R.id.btn_cancel_dig_modify_phonenumber);
        mBtnConfirmDigModifyPhonenumber = (Button) findViewById(R.id.btn_confirm_dig_modify_phonenumber);
    }
    
    private void initDatas() {
        mBtnGetVerifycodeDigModifyPhonenumber.setOnClickListener(this);
        mBtnCancelDigModifyPhonenumber.setOnClickListener(this);
        mBtnConfirmDigModifyPhonenumber.setOnClickListener(this);
        SMSSDK.registerEventHandler(eh); //注册短信回调
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                SMSSDK.unregisterEventHandler(eh);
            }
        });
    }
    
    public void showSelf() {
        this.getWindow().setWindowAnimations(R.style.diganim);
        this.show();
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_verifycode_dig_modify_phonenumber:
                Toast.makeText(mContext, "正在获取，请稍等", Toast.LENGTH_SHORT).show();
                 phone=mEdtPhoneDigModifyPhonenumber.getText().toString().trim();
                
                if (!StringEmptyUtil.isEmpty(phone)){
                    SMSSDK.getVerificationCode("86",phone); 
                }
                break;
            case R.id.btn_cancel_dig_modify_phonenumber:
                this.dismiss();
                SMSSDK.unregisterEventHandler(eh);
                break;
            case R.id.btn_confirm_dig_modify_phonenumber:
                //确定
                Toast.makeText(mContext, "验证中，请稍等", Toast.LENGTH_SHORT).show();
                String phone1=mEdtPhoneDigModifyPhonenumber.getText().toString().trim();
                SMSSDK. submitVerificationCode("86",phone1,mEdtVerifycodeDigModifyPhonenumber.getText().toString());
      
                break;
            
        }
    }
    
}
