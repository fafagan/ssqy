package com.medicine.ssqy.ssqy.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sj.mylibrary.net.NetCallback;
import com.example.sj.mylibrary.net.NetForJson;
import com.example.sj.mylibrary.util.StringEmptyUtil;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.common.URLConstant;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.db.TempUser;
import com.medicine.ssqy.ssqy.entity.UserEntity;
import com.medicine.ssqy.ssqy.entity.UsersetEntity;
import com.medicine.ssqy.ssqy.eventBus.ModifyInformation;
import com.medicine.ssqy.ssqy.ui.pop.LoadingPopWindow;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Amy on 2016/12/16.
 */
public class ModifyNicknameDig extends Dialog implements View.OnClickListener {
    
    private Button mBtnDeleteDigModifyNickname;
    private Button mBtnCancelDigModifyNickname;
    private Button mBtnConfirmDigModifyNickname;
    private EditText mEdtNicknameDigModifyNickname;
    private String oldNickName;

    public ModifyNicknameDig(Context context, String oldNickName) {
        super(context);
        mContext=context;
        loadingPopWindow = new LoadingPopWindow(context);
        this.oldNickName = oldNickName;
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
        mNetForJson = new NetForJson(URLConstant.USERSET_URL, new NetCallback<UsersetEntity>() {
            @Override
            public void onSuccess(UsersetEntity entity) {
                onSaveOk();
            }
            
            @Override
            public void onError() {
                Toast.makeText(mContext, "保存失败，请重试！", Toast.LENGTH_SHORT).show();
            }
            
            @Override
            public void onFinish() {
                if (loadingPopWindow.isShowing()){
                    loadingPopWindow.dismiss();
                    
                }
                
                
            }
        }, true);
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
                if (StringEmptyUtil.isEmpty(mEdtNicknameDigModifyNickname.getText().toString())){
                    Toast.makeText(mContext, "昵称不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                synchServer();
                //确定
//                this.dismiss();
                
                break;
            
        }
    }
    
    NetForJson mNetForJson;
    private LoadingPopWindow loadingPopWindow;
    private Context mContext;
    private void synchServer() {
        loadingPopWindow.show();
        UserEntity nowUser = TempUser.getNowUser(SharePLogin.getUid());
        Logger.e(nowUser.toString());
        mNetForJson.addParam("uid", SharePLogin.getUid());
        mNetForJson.addParam("nickName", mEdtNicknameDigModifyNickname.getText().toString());
        mNetForJson.addParam("sex", nowUser.getSex());
        mNetForJson.addParam("marry", nowUser.getIsMarried());
        mNetForJson.addParam("birth", nowUser.getBirthDay());
        mNetForJson.addParam("phone", nowUser.getUseraccount());
        mNetForJson.addParam("job", nowUser.getJob());
        mNetForJson.addParam("studylevel", nowUser.getStudylevel());
        mNetForJson.addParam("isFirstLogin", nowUser.isIsFisrtLogin());
        mNetForJson.addParam("level", 1);
        mNetForJson.excute();
        
        
    }
    
    private void onSaveOk() {
//        TempUser.saveOrUpdateUserFirst(sex,isMarried,birthDay,job,studyLevel);
        Toast.makeText(mContext, "更改昵称成功！", Toast.LENGTH_SHORT).show();
        UserEntity nowUser = TempUser.getNowUser(SharePLogin.getUid());
        nowUser.setNickName(mEdtNicknameDigModifyNickname.getText().toString());
        TempUser.saveOrUpdateUser(nowUser);
        EventBus.getDefault().post(new ModifyInformation());
        cancel();
    }
}
