package com.medicine.ssqy.ssqy.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sj.mylibrary.net.NetCallback;
import com.example.sj.mylibrary.net.NetForJson;
import com.example.sj.mylibrary.util.EdtUtil;
import com.example.sj.mylibrary.util.PhoneCheckUtil;
import com.example.sj.mylibrary.util.StringEmptyUtil;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.common.URLConstant;
import com.medicine.ssqy.ssqy.entity.UserExistsEntity;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class PWDFindActivity extends KBaseActivity {
    private EditText mEdtPhoneDigModifyPhonenumber;
    private EditText mEdtVerifycodeDigModifyPhonenumber;
    private Button mBtnGetVerifycodeDigModifyPhonenumber;
    private Button mBtnCancelDigModifyPhonenumber;
    private Button mBtnConfirmDigModifyPhonenumber;
    private String phoneNum;
    
    
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
//                Toast.makeText(mSelf, "注册成功！", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(mSelf,PWDFind2Activity.class);
                intent.putExtra("phone",phoneNum);
                startActivity(intent);
                finish();
                
            }else {
                Toast.makeText(mSelf, "验证码错误！！", Toast.LENGTH_SHORT).show();
                
                
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
    
    @Override
    public int setRootView() {
        
        return R.layout.activity_pwdfind;
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh);
    }
    
    @Override
    public void initViews() {
        setTitleCenter("找回密码");
        mEdtPhoneDigModifyPhonenumber = (EditText) findViewById(R.id.edt_phone_dig_modify_phonenumber);
        mEdtVerifycodeDigModifyPhonenumber = (EditText) findViewById(R.id.edt_verifycode_dig_modify_phonenumber);
        mBtnGetVerifycodeDigModifyPhonenumber = (Button) findViewById(R.id.btn_get_verifycode_dig_modify_phonenumber);
        mBtnCancelDigModifyPhonenumber = (Button) findViewById(R.id.btn_cancel_dig_modify_phonenumber);
        mBtnConfirmDigModifyPhonenumber = (Button) findViewById(R.id.btn_confirm_dig_modify_phonenumber);
        mBtnGetVerifycodeDigModifyPhonenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCode();
            }
        });
        SMSSDK.registerEventHandler(eh); //注册短信回调
        mBtnConfirmDigModifyPhonenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                goToActivity(PWDFind2Activity.class);
//                finish();
                Toast.makeText(mSelf, "正在验证您的信息，请稍等", Toast.LENGTH_SHORT).show();
                String code = mEdtVerifycodeDigModifyPhonenumber.getText().toString();
                if (StringEmptyUtil.isEmpty(code)){
                    Toast.makeText(mSelf, "验证码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                SMSSDK. submitVerificationCode("86",phoneNum,mEdtVerifycodeDigModifyPhonenumber.getText().toString());
            }
        });
    }
    
    @Override
    public void initDatas() {
        
    }
    
    public void getCode() {
        phoneNum= EdtUtil.getEdtText(mEdtPhoneDigModifyPhonenumber);
        if (StringEmptyUtil.isEmpty(phoneNum)){
            Toast.makeText(mSelf, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!PhoneCheckUtil.isRight(phoneNum)){
            Toast.makeText(mSelf, "请检查手机号格式", Toast.LENGTH_SHORT).show();
            return;
        }
    
        checkExists();
    }
    
    private void checkExists() {
        
        NetForJson netForJson=new NetForJson(URLConstant.USECHECK_URL, new NetCallback<UserExistsEntity>() {
            @Override
            public void onSuccess(UserExistsEntity entity) {
                if (!entity.isUserexists()) {
                    Toast.makeText(mSelf, "抱歉，该用户并不存在！", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(mSelf, "正在获取验证码，请稍等。。", Toast.LENGTH_SHORT).show();
                    SMSSDK.getVerificationCode("86",phoneNum);
                    mBtnGetVerifycodeDigModifyPhonenumber.setOnClickListener(null);
                    
                }
            }
            
            @Override
            public void onError() {
                Toast.makeText(mSelf, "网络错误！", Toast.LENGTH_SHORT).show();
            }
            
            @Override
            public void onFinish() {
                
            }
        },true);
        netForJson.addParam("phone",mEdtPhoneDigModifyPhonenumber.getText().toString());
        netForJson.excute();
    }
}
