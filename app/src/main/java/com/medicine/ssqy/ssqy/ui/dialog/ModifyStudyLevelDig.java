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
public class ModifyStudyLevelDig extends Dialog implements View.OnClickListener {
    
    private Button mBtnCancelDigModifyNickname;
    private Button mBtnConfirmDigModifyNickname;
    private TextView mTvStudyFirstLogin;
    private WheelView mWvStudyFirstLogin;
    
    private List<String> mStudys=new ArrayList<>();
    private boolean misFirst=true;
    private UserEntity nowUser;
    public ModifyStudyLevelDig(Context context) {
        super(context);
        mContext=context;
        nowUser= TempUser.getNowUser(SharePLogin.getUid());
        loadingPopWindow = new LoadingPopWindow(context);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setGravity(Gravity.CENTER);
        this.setContentView(R.layout.dig_modify_studylevel);
        initViews();
        initDatas();
    }
    
    private void initViews() {
        mBtnCancelDigModifyNickname = (Button) findViewById(R.id.btn_cancel_dig_modify_nickname);
        mBtnConfirmDigModifyNickname = (Button) findViewById(R.id.btn_confirm_dig_modify_nickname);
    
        mTvStudyFirstLogin = (TextView) findViewById(R.id.tv_study_first_login);
        mWvStudyFirstLogin = (WheelView) findViewById(R.id.wv_study_first_login);
        initWVDatas();
    
        mWvStudyFirstLogin.setWheelAdapter(new TVWheelAdapter(mContext)); // 文本数据源
        WheelView.WheelViewStyle wheelViewStyle = new WheelView.WheelViewStyle();
        wheelViewStyle.backgroundColor = 0x00ffffff;
        mWvStudyFirstLogin.setStyle(wheelViewStyle);
        mWvStudyFirstLogin.setSkin(WheelView.Skin.None); // common皮肤
        mWvStudyFirstLogin.setWheelData(mStudys);  // 数据集合
        mWvStudyFirstLogin.setLoop(true);
        mWvStudyFirstLogin.setWheelSize(3);
        try {
            mWvStudyFirstLogin.setSelection(Integer.parseInt(nowUser.getStudylevel()));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        mWvStudyFirstLogin.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener() {
            @Override
            public void onItemSelected(int i, Object o) {
                mTvStudyFirstLogin.setText(o.toString());
                mTvStudyFirstLogin.setTag(i);
                Logger.e(i+"");
                if (misFirst) {
                    misFirst=false;
                    if ("0".equals(nowUser.getStudylevel())){
                        mTvStudyFirstLogin.setText("本科及以上");
                    }else if ("1".equals(nowUser.getStudylevel())){
                        mTvStudyFirstLogin.setText("大专及中专");
                    }else {
                        mTvStudyFirstLogin.setText("高中及以下");
                    }
                }
            }
        });
    }
    
    private void initWVDatas() {
        mStudys.add("本科及以上");
        mStudys.add("大专及中专");
        mStudys.add("高中及以下");
    }
    private void initDatas() {
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
                break;
            
        }
    }
    
    NetForJson mNetForJson;
    private LoadingPopWindow loadingPopWindow;
    private Context mContext;
    private void synchServer() {
        loadingPopWindow.show();
   
        Logger.e(nowUser.toString());
        mNetForJson.addParam("nickName",nowUser.getNickName());
        mNetForJson.addParam("sex", nowUser.getSex());
        mNetForJson.addParam("marry", nowUser.getIsMarried());
        mNetForJson.addParam("birth", nowUser.getBirthDay());
        mNetForJson.addParam("phone", nowUser.getUseraccount());
        mNetForJson.addParam("job", nowUser.getJob());
        mNetForJson.addParam("uid", SharePLogin.getUid());
    
        switch (mTvStudyFirstLogin.getText().toString().trim()){
            case "大专及中专":
                mNetForJson.addParam("studylevel", "1");
                break;
            case "本科及以上":
                mNetForJson.addParam("studylevel", "0");
                break;
            case "高中及以下":
                mNetForJson.addParam("studylevel", "2");
                break;
        }
   
        mNetForJson.addParam("isFirstLogin", nowUser.isIsFisrtLogin());
        mNetForJson.addParam("level", 1);
        mNetForJson.excute();
        
    }
    
    private void onSaveOk() {
//        TempUser.saveOrUpdateUserFirst(sex,isMarried,birthDay,job,studyLevel);
        Toast.makeText(mContext, "更改文化程度成功！", Toast.LENGTH_SHORT).show();
        switch (mTvStudyFirstLogin.getText().toString()){
            case "大专及中专":
                nowUser.setStudylevel("1");
                break;
            case "本科及以上":
                nowUser.setStudylevel("0");
                break;
            case "高中及以下":
                nowUser.setStudylevel("2");
                break;
        }
    
        Logger.e(mTvStudyFirstLogin.getText().toString().trim()+"  "+nowUser.getStudylevel());
        TempUser.saveOrUpdateUser(nowUser);
        EventBus.getDefault().post(new ModifyInformation());
        cancel();
    }
}
