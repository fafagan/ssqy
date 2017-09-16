package com.medicine.ssqy.ssqy.ui.activity;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.db.TempUser;
import com.medicine.ssqy.ssqy.entity.UserEntity;
import com.medicine.ssqy.ssqy.ui.dialog.ModifyMarriageDig;
import com.medicine.ssqy.ssqy.ui.dialog.ModifyNicknameDig;
import com.medicine.ssqy.ssqy.ui.dialog.ModifyPhonenumberDig;
import com.medicine.ssqy.ssqy.ui.dialog.ModifySexDig;

public class MyInformationActivity extends KBaseActivity implements View.OnClickListener {
    private RelativeLayout mRlUsernameActivityInformation;
    private TextView mTvUsernameActivityInformation;
    private RelativeLayout mRlNicknameActivityInformation;
    private TextView mTvNicknameActivityInformation;
    private RelativeLayout mRlUsersexActivityInformation;
    private TextView mTvUsersexActivityInformation;
    private RelativeLayout mRlMarriageActivityInformation;
    private TextView mTvUsermarriageActivityInformation;
    private RelativeLayout mRlBirthdayActivityInformation;
    private TextView mTvBirthdayActivityInformation;
    private RelativeLayout mRlPhonenumberActivityInformation;
    private TextView mTvPhonenumberActivityInformation;
    private RelativeLayout mRlJobActivityInformation;
    private TextView mTvJobActivityInformation;
    private RelativeLayout mRlStudylevelActivityInformation;
    private TextView mTvStudylevelActivityInformation;
    private RelativeLayout mRlUserlevelActivityInformation;
    private TextView mTvUserlevelActivityInformation;
    private RelativeLayout mRlRegistedtimeActivityInformation;
    private TextView mTvRegistedtimeActivityInformation;
    private UserEntity mNowUser;
    
    @Override
    public int setRootView() {
        return R.layout.activity_my_information;
    }
    
    @Override
    public void initViews() {
        setTitleCenter("我的基本资料");
        mTvUsernameActivityInformation = (TextView) findViewById(R.id.tv_username_activity_information);
        mRlNicknameActivityInformation = (RelativeLayout) findViewById(R.id.rl_nickname_activity_information);
        mTvNicknameActivityInformation = (TextView) findViewById(R.id.tv_nickname_activity_information);
        mRlUsersexActivityInformation = (RelativeLayout) findViewById(R.id.rl_usersex_activity_information);
        mTvUsersexActivityInformation = (TextView) findViewById(R.id.tv_usersex_activity_information);
        mRlMarriageActivityInformation = (RelativeLayout) findViewById(R.id.rl_marriage_activity_information);
        mTvUsermarriageActivityInformation = (TextView) findViewById(R.id.tv_usermarriage_activity_information);
        mRlBirthdayActivityInformation = (RelativeLayout) findViewById(R.id.rl_birthday_activity_information);
        mTvBirthdayActivityInformation = (TextView) findViewById(R.id.tv_birthday_activity_information);
        mRlPhonenumberActivityInformation = (RelativeLayout) findViewById(R.id.rl_phonenumber_activity_information);
        mTvPhonenumberActivityInformation = (TextView) findViewById(R.id.tv_phonenumber_activity_information);
        mRlJobActivityInformation = (RelativeLayout) findViewById(R.id.rl_job_activity_information);
        mTvJobActivityInformation = (TextView) findViewById(R.id.tv_job_activity_information);
        mRlStudylevelActivityInformation = (RelativeLayout) findViewById(R.id.rl_studylevel_activity_information);
        mTvStudylevelActivityInformation = (TextView) findViewById(R.id.tv_studylevel_activity_information);
        mRlUserlevelActivityInformation = (RelativeLayout) findViewById(R.id.rl_userlevel_activity_information);
        mTvUserlevelActivityInformation = (TextView) findViewById(R.id.tv_userlevel_activity_information);
        mRlRegistedtimeActivityInformation = (RelativeLayout) findViewById(R.id.rl_registedtime_activity_information);
        mTvRegistedtimeActivityInformation = (TextView) findViewById(R.id.tv_registedtime_activity_information);
        
    }
    
    @Override
    public void initDatas() {
        mRlNicknameActivityInformation.setOnClickListener(this);
        mRlUsersexActivityInformation.setOnClickListener(this);
        mRlMarriageActivityInformation.setOnClickListener(this);
        mRlBirthdayActivityInformation.setOnClickListener(this);
        mRlPhonenumberActivityInformation.setOnClickListener(this);
        mRlJobActivityInformation.setOnClickListener(this);
        mRlStudylevelActivityInformation.setOnClickListener(this);
    
        mNowUser = TempUser.getNowUser(SharePLogin.getUid());
    
        mTvUsernameActivityInformation.setText(mNowUser.getUseraccount());
        mTvNicknameActivityInformation.setText(mNowUser.getNickName());
        if ("1".equals(mNowUser.getSex())) {
            mTvUsersexActivityInformation.setText("女");
        }else {
          
            mTvUsersexActivityInformation.setText("男");
        }
    
    
        if ("0".equals(mNowUser.getSex())) {
            mTvUsermarriageActivityInformation.setText("未婚");
        }else {
            mTvUsermarriageActivityInformation.setText("已婚");
        }
     
        mTvBirthdayActivityInformation.setText(mNowUser.getBirthDay());
        mTvPhonenumberActivityInformation.setText(mNowUser.getUseraccount());
        if ("0".equals(mNowUser.getStudyLevel())){
            mTvStudylevelActivityInformation.setText("高中或以下");
        }else if ("1".equals(mNowUser.getStudyLevel())){
            mTvStudylevelActivityInformation.setText("大专及中专");
        }else {
          
            mTvStudylevelActivityInformation.setText("本科及以上");
        }
       
        mTvUserlevelActivityInformation.setText("等级 "+mNowUser.getLevel());
        mTvRegistedtimeActivityInformation.setText(mNowUser.getCreateDate());
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_nickname_activity_information:
                String oldNickName=mTvNicknameActivityInformation.getText().toString();
                ModifyNicknameDig nameDig=new ModifyNicknameDig(this,oldNickName);
                nameDig.showSelf();
                
                break;
            case R.id.rl_usersex_activity_information:
                ModifySexDig sexDig=new ModifySexDig(this);
                sexDig.showSelf();
                
                break;
            case R.id.rl_marriage_activity_information:
                ModifyMarriageDig marriageDig=new ModifyMarriageDig(this);
                marriageDig.showSelf();
                break;
            case R.id.rl_birthday_activity_information:
        
                break;
            case R.id.rl_phonenumber_activity_information:
                ModifyPhonenumberDig phoneDig=new ModifyPhonenumberDig(this);
                phoneDig.showSelf();
                break;
            case R.id.rl_job_activity_information:
        
                break;
            case R.id.rl_studylevel_activity_information:
        
                break;
            
        }
    }
}
