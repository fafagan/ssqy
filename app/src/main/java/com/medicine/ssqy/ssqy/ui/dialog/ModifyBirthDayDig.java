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
public class ModifyBirthDayDig extends Dialog implements View.OnClickListener {
    private TextView mTvBirthFirstLogin;
    private WheelView mWvYearFirstLogin;
    private WheelView mWvMonthFirstLogin;
    private WheelView mWvDayFirstLogin;
    private Button mBtnCancelDigModifyNickname;
    private Button mBtnConfirmDigModifyNickname;
    
    private List<Integer> mDatasYear = new ArrayList<>();
    private List<Integer> mDatasMonth = new ArrayList<>();
    private List<Integer> mDatasDay31 = new ArrayList<>();
    private List<Integer> mDatasDay30 = new ArrayList<>();
    private List<Integer> mDatasDay29 = new ArrayList<>();
    private List<Integer> mDatasDay28 = new ArrayList<>();
    
    private int mYear = 1980, mMonth = 1, mDay = 1;
    private StringBuilder stringBuilder = new StringBuilder();
    UserEntity nowUser ;
    public ModifyBirthDayDig(Context context) {
        super(context);
        mContext=context;
        loadingPopWindow = new LoadingPopWindow(context);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setGravity(Gravity.CENTER);
        this.setContentView(R.layout.dig_modify_birthday);
        initViews();
        initDatas();
    }
    
    private void initViews() {
        mTvBirthFirstLogin = (TextView) findViewById(R.id.tv_birth_first_login);
        mWvYearFirstLogin = (WheelView) findViewById(R.id.wv_year_first_login);
        mWvMonthFirstLogin = (WheelView) findViewById(R.id.wv_month_first_login);
        mWvDayFirstLogin = (WheelView) findViewById(R.id.wv_day_first_login);
        mBtnCancelDigModifyNickname = (Button) findViewById(R.id.btn_cancel_dig_modify_nickname);
        mBtnConfirmDigModifyNickname = (Button) findViewById(R.id.btn_confirm_dig_modify_nickname);
    }
    
    private void initDatas() {
        nowUser = TempUser.getNowUser(SharePLogin.getUid());
        mBtnConfirmDigModifyNickname.setOnClickListener(this);
        mBtnCancelDigModifyNickname.setOnClickListener(this);
        if (nowUser!=null) {
            mTvBirthFirstLogin.setText(nowUser.getBirthDay());
        }
    
        initWVDatas();
        initWVViews();
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
            case R.id.btn_delete_dig_modify_nickname:
                break;
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
        if (nowUser==null) {
            Toast.makeText(mContext, "登录状态异常，请重新登录！", Toast.LENGTH_SHORT).show();
            return;
        }
        loadingPopWindow.show();
        Logger.e(nowUser.toString());
        mNetForJson.addParam("uid", SharePLogin.getUid());
        mNetForJson.addParam("nickName", nowUser.getNickName());
        mNetForJson.addParam("sex", nowUser.getSex());
        mNetForJson.addParam("marry", nowUser.getIsMarried());
        mNetForJson.addParam("birth", mTvBirthFirstLogin.getText().toString());
        mNetForJson.addParam("phone", nowUser.getUseraccount());
        mNetForJson.addParam("job", nowUser.getJob());
        mNetForJson.addParam("studylevel", nowUser.getStudylevel());
        mNetForJson.addParam("isFirstLogin", nowUser.isIsFisrtLogin());
        mNetForJson.addParam("level", 1);
        mNetForJson.excute();
        
        
    }
    
    private void onSaveOk() {
//        TempUser.saveOrUpdateUserFirst(sex,isMarried,birthDay,job,studyLevel);
        Toast.makeText(mContext, "更改生日成功！", Toast.LENGTH_SHORT).show();
        
        nowUser.setBirthDay( mTvBirthFirstLogin.getText().toString());
        TempUser.saveOrUpdateUser(nowUser);
        EventBus.getDefault().post(new ModifyInformation());
        cancel();
    }
    int yearSelection=0;
    int monthSelection=0;
    int daySelection=0;
    boolean firstOpen=true;
    private void initWVViews() {
        
    
        if (nowUser != null) {
            if (nowUser.getBirthDay() != null) {
                String[] split = nowUser.getBirthDay().split("-");
                Logger.e(nowUser.getBirthDay());
                Logger.e(split[2]);
                Logger.e(mDatasDay31.indexOf(Integer.parseInt(split[2]))+"");
                yearSelection=mDatasYear.indexOf(Integer.parseInt(split[0]));
                monthSelection=mDatasMonth.indexOf(Integer.parseInt(split[1]));
                daySelection=mDatasDay31.indexOf(Integer.parseInt(split[2]));
            }
        }
        
        mWvYearFirstLogin.setWheelAdapter(new TVWheelAdapter(mContext)); // 文本数据源
        WheelView.WheelViewStyle wheelViewStyle = new WheelView.WheelViewStyle();
        wheelViewStyle.backgroundColor = 0x00ffffff;
        mWvYearFirstLogin.setStyle(wheelViewStyle);
        mWvYearFirstLogin.setSkin(WheelView.Skin.None); // common皮肤
        mWvYearFirstLogin.setWheelData(mDatasYear);  // 数据集合
        mWvYearFirstLogin.setLoop(true);
        mWvYearFirstLogin.setWheelSize(3);
        mWvYearFirstLogin.setSelection(yearSelection);
        mWvYearFirstLogin.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener() {
            @Override
            public void onItemSelected(int i, Object o) {
                mYear = (int) o;
                if (mYear % 4 == 0) {
                    mWvDayFirstLogin.setWheelData(mDatasDay29);
                } else {
                    mWvDayFirstLogin.setWheelData(mDatasDay28);
                }
                setTvText();
            }
        });
        
        mWvMonthFirstLogin.setWheelAdapter(new TVWheelAdapter(mContext)); // 文本数据源
        mWvMonthFirstLogin.setStyle(wheelViewStyle);
        mWvMonthFirstLogin.setSkin(WheelView.Skin.None); // common皮肤
        mWvMonthFirstLogin.setWheelData(mDatasMonth);  // 数据集合
        mWvMonthFirstLogin.setLoop(true);
        mWvMonthFirstLogin.setWheelSize(3);
        mWvMonthFirstLogin.setSelection(monthSelection);
        mWvMonthFirstLogin.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener() {
            @Override
            public void onItemSelected(int i, Object o) {
                mMonth = (int) o;
                switch (mMonth) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        mWvDayFirstLogin.setWheelData(mDatasDay31);
                        break;
                    case 2:
//                        if (mYear%100==0){
//                            if (mYear%400==0){
//                                
//                            }
//                        }else if (mYear%4==0){
//                            
//                        }
                        if (mYear % 4 == 0) {
                            mWvDayFirstLogin.setWheelData(mDatasDay29);
                        } else {
                            mWvDayFirstLogin.setWheelData(mDatasDay28);
                        }
                        break;
                    
                    default:
                        mWvDayFirstLogin.setWheelData(mDatasDay30);
                }
                setTvText();
                if (firstOpen) {
                    firstOpen=false;
                    mWvDayFirstLogin.setSelection(daySelection);
                }
                
            }
        });
        
        
        mWvDayFirstLogin.setWheelAdapter(new TVWheelAdapter(mContext)); // 文本数据源
        mWvDayFirstLogin.setStyle(wheelViewStyle);
        mWvDayFirstLogin.setSkin(WheelView.Skin.None); // common皮肤
        mWvDayFirstLogin.setWheelData(mDatasDay31);  // 数据集合
        mWvDayFirstLogin.setLoop(true);
        mWvDayFirstLogin.setWheelSize(3);
        mWvDayFirstLogin.setSelection(daySelection);
        mWvDayFirstLogin.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener() {
            @Override
            public void onItemSelected(int i, Object o) {
                mDay = (int) o;
                setTvText();
            }
        });
    }
    private void initWVDatas() {
        for (int i = 1946; i <= 2016; i++) {
            mDatasYear.add(i);
        }
        
        for (int i = 1; i <= 12; i++) {
            mDatasMonth.add(i);
        }
        
        
        for (int i = 1; i <= 31; i++) {
            mDatasDay31.add(i);
        }
        for (int i = 1; i <= 30; i++) {
            mDatasDay30.add(i);
        }
        for (int i = 1; i <= 29; i++) {
            mDatasDay29.add(i);
        }
        for (int i = 1; i <= 28; i++) {
            mDatasDay28.add(i);
        }
        
    }
    
    public void setTvText() {
        stringBuilder.delete(0, stringBuilder.length());
        stringBuilder.append(mYear);
        stringBuilder.append("-");
        stringBuilder.append(mMonth);
        stringBuilder.append("-");
        stringBuilder.append(mDay);
        mTvBirthFirstLogin.setText(stringBuilder.toString());
    }
    
}
