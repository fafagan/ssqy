package com.medicine.ssqy.ssqy.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sj.mylibrary.net.NetCallback;
import com.example.sj.mylibrary.net.NetForJson;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.common.URLConstant;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.db.TempUser;
import com.medicine.ssqy.ssqy.entity.UserEntity;
import com.medicine.ssqy.ssqy.entity.UsersetEntity;
import com.medicine.ssqy.ssqy.eventBus.ModifyInformation;
import com.medicine.ssqy.ssqy.ui.pop.LoadingPopWindow;
import com.medicine.ssqy.ssqy.ui.views.TVWheelAdapter;
import com.orhanobut.logger.Logger;
import com.wx.wheelview.widget.WheelView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amy on 2016/12/16.
 */
public class ModifyJobDig extends Dialog implements View.OnClickListener {
    
    private Button mBtnCancelDigModifyNickname;
    private Button mBtnConfirmDigModifyNickname;
    private TextView mTvJobFirstLogin;
    private WheelView mWvJobFirstLogin;
    
    private boolean mIsFirst=true;
    private List<String> mJobs=new ArrayList<>();
    
    UserEntity nowUser;
    private int mIndex;
    
    public ModifyJobDig(Context context) {
        super(context);
        mContext=context;
        loadingPopWindow = new LoadingPopWindow(context);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setGravity(Gravity.CENTER);
        this.setContentView(R.layout.dig_modify_job);
        initViews();
        initDatas();
    }
    
    private void initViews() {
        mBtnCancelDigModifyNickname = (Button) findViewById(R.id.btn_cancel_dig_modify_nickname);
        mBtnConfirmDigModifyNickname = (Button) findViewById(R.id.btn_confirm_dig_modify_nickname);
        mTvJobFirstLogin = (TextView) findViewById(R.id.tv_job_first_login);
        mWvJobFirstLogin = (WheelView) findViewById(R.id.wv_job_first_login);
    }
    
    private void initDatas() {
        nowUser=  TempUser.getNowUser(SharePLogin.getUid());
        initWVDatas();
        if (nowUser != null) {
            mTvJobFirstLogin.setText(nowUser.getJob());
            mIndex = mJobs.indexOf(nowUser.getJob());
        }
        mWvJobFirstLogin.setWheelAdapter(new TVWheelAdapter(mContext)); // 文本数据源
        WheelView.WheelViewStyle wheelViewStyle = new WheelView.WheelViewStyle();
        wheelViewStyle.backgroundColor = 0x00ffffff;
        mWvJobFirstLogin.setStyle(wheelViewStyle);
        mWvJobFirstLogin.setSkin(WheelView.Skin.None); // common皮肤
        mWvJobFirstLogin.setWheelData(mJobs);  // 数据集合
        mWvJobFirstLogin.setLoop(true);
        mWvJobFirstLogin.setWheelSize(3);
        mWvJobFirstLogin.setSelection(mIndex);
        mWvJobFirstLogin.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener() {
            @Override
            public void onItemSelected(int i, Object o) {
                mTvJobFirstLogin.setText(o.toString());
                
            }
        });
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
    
    private void initWVDatas() {
        
        mJobs.add("工人");
        mJobs.add("农民");
        mJobs.add("教师");
        mJobs.add("商人");
        mJobs.add("学者");
        mJobs.add("公务员");
        mJobs.add("退休职工");
        mJobs.add("家庭妇女");
        mJobs.add("学生");
        mJobs.add("白领");
        mJobs.add("建筑工");
        mJobs.add("医生");
        mJobs.add("厨师");
        mJobs.add("其他职业");
    }
    public void showSelf() {
        this.getWindow().setWindowAnimations(R.style.diganim);
        this.show();
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel_dig_modify_nickname:
                this.dismiss();
                break;
            case R.id.btn_confirm_dig_modify_nickname:
                synchServer();
                //确定
//                this.dismiss();
                synchServer();
                
                break;
            
        }
    }
    
    NetForJson mNetForJson;
    private LoadingPopWindow loadingPopWindow;
    private Context mContext;
    private void synchServer() {
        loadingPopWindow.show();
  
        
        Logger.e(nowUser.toString());
        mNetForJson.addParam("uid", SharePLogin.getUid());
        mNetForJson.addParam("nickName",nowUser.getNickName());
        mNetForJson.addParam("sex", nowUser.getSex());
        mNetForJson.addParam("marry", nowUser.getIsMarried());
        mNetForJson.addParam("birth", nowUser.getBirthDay());
        mNetForJson.addParam("phone", nowUser.getUseraccount());
        mNetForJson.addParam("job", mTvJobFirstLogin.getText().toString());
        mNetForJson.addParam("studylevel", nowUser.getStudylevel());
        mNetForJson.addParam("isFirstLogin", nowUser.isIsFisrtLogin());
        mNetForJson.addParam("level", 1);
        mNetForJson.excute();
        
        
    }
    
    private void onSaveOk() {
//        TempUser.saveOrUpdateUserFirst(sex,isMarried,birthDay,job,studyLevel);
        Toast.makeText(mContext, "更改工作成功！", Toast.LENGTH_SHORT).show();
        
        nowUser.setJob( mTvJobFirstLogin.getText().toString());
        TempUser.saveOrUpdateUser(nowUser);
        EventBus.getDefault().post(new ModifyInformation());
        cancel();
    }
}
