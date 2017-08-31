package com.medicine.ssqy.ssqy.ui.activity;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sj.mylibrary.net.NetCallback;
import com.example.sj.mylibrary.net.NetForJson;
import com.example.sj.mylibrary.util.EdtUtil;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.common.URLConstant;
import com.medicine.ssqy.ssqy.entity.UserEntity;
import com.orhanobut.logger.Logger;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegActivity extends KBaseActivity implements View.OnClickListener {
    private EditText mEdtPwdReg;
    private TextView mTvTemp1Reg;
    private TextView mTvXyReg;
    private EditText mEdtNickNameReg;
    private EditText mEdtPhoneReg;
//    private EditText mEdtVerifycodeReg;
//    private Button mBtGetVerifycodeReg;
    private Button mBtConfirmReg;
    private NetForJson mNetForJson;

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
//                Toast.makeText(mSelf, "注册成功！", Toast.LENGTH_SHORT).show();
              
            
            }else {
                Toast.makeText(mSelf, "验证异常，请确认信息并重试！", Toast.LENGTH_SHORT).show();
                
                
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
        return R.layout.activity_reg;
    }
    
    @Override
    public void initViews() {
        setTitleCenter("注 册");
        mEdtPwdReg = (EditText) findViewById(R.id.edt_pwd_reg);
        mTvTemp1Reg = (TextView) findViewById(R.id.tv_temp1_reg);
        mTvXyReg = (TextView) findViewById(R.id.tv_xy_reg);
        mEdtNickNameReg = (EditText) findViewById(R.id.edt_nick_name_reg);
        mEdtPhoneReg = (EditText) findViewById(R.id.edt_phone_reg);
//        mEdtVerifycodeReg = (EditText) findViewById(R.id.edt_verifycode_reg);
//        mBtGetVerifycodeReg = (Button) findViewById(R.id.bt_get_verifycode_reg);
        mBtConfirmReg = (Button) findViewById(R.id.bt_confirm_reg);
    
        SMSSDK.registerEventHandler(eh); //注册短信回调
        mBtConfirmReg.setOnClickListener(this);
//        mBtGetVerifycodeReg.setOnClickListener(this);
    }
    
    @Override
    public void initDatas() {
        mNetForJson=new NetForJson(URLConstant.REG_URL, new NetCallback<UserEntity>() {
            @Override
            public void onSuccess(UserEntity entity) {
                System.out.println(entity);
                Toast.makeText(mSelf, entity.getUseraccount(), Toast.LENGTH_SHORT).show();
                SMSSDK.unregisterEventHandler(eh);
                goToActivity(LoginActivity.class);
                finish();
            }
    
            @Override
            public void onError() {
                Toast.makeText(mSelf,"注册失败，请检查您的网络状态!", Toast.LENGTH_SHORT).show();
            }
    
            @Override
            public void onFinish() {
        
            }
        },true);
        
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.bt_get_verifycode_reg:
//                Toast.makeText(mSelf, "正在获取，请稍等", Toast.LENGTH_SHORT).show();
//                String phone=mEdtPhoneReg.getText().toString().trim();
//                if (!StringEmptyUtil.isEmpty(phone)){
//                    SMSSDK.getVerificationCode("86",phone);
//                }
//                break;
            case R.id.bt_confirm_reg:
                //确定
                Toast.makeText(mSelf, "验证中，请稍等", Toast.LENGTH_SHORT).show();
                String phone1=mEdtPhoneReg.getText().toString().trim();
                //SMSSDK. submitVerificationCode("86",phone1,mEdtVerifycodeReg.getText().toString());
                doNet(); 
                
                break;
        
        }
    }
    
    private void doNet() {
        mNetForJson.addParam("nickName", EdtUtil.getEdtText(mEdtNickNameReg));
        mNetForJson.addParam("phone", EdtUtil.getEdtText(mEdtPhoneReg));
        Logger.e(EdtUtil.getEdtText(mEdtPhoneReg)+"   "+EdtUtil.getEdtText(mEdtPwdReg));
        mNetForJson.addParam("userpwd", EdtUtil.getEdtText(mEdtPwdReg));
        mNetForJson.excute();
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh);
    }
 
}
