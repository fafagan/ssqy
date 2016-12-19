package com.medicine.ssqy.ssqy.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.ui.dialog.AccoutDig;

public class SettingActivity extends KBaseActivity implements View.OnClickListener {
    private TextView mTvAccountActivitySetting;
    private TextView mTvAboutusActivitySetting;
    private TextView mTvExitActivitySetting;
    
    @Override
    public int setRootView() {
        return R.layout.activity_setting;
    }
    
    @Override
    public void initViews() {
        setTitleCenter("设置");
        mTvAccountActivitySetting = (TextView) findViewById(R.id.tv_account_activity_setting);
        mTvAboutusActivitySetting = (TextView) findViewById(R.id.tv_aboutus_activity_setting);
        mTvExitActivitySetting = (TextView) findViewById(R.id.tv_exit_activity_setting);
        
    }
    
    @Override
    public void initDatas() {
        mTvAccountActivitySetting.setOnClickListener(this);
        mTvAboutusActivitySetting.setOnClickListener(this);
        mTvExitActivitySetting.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_account_activity_setting:
                AccoutDig accountDig=new AccoutDig(this);
                accountDig.showSelf();
                break;
            case R.id.tv_aboutus_activity_setting:
                
                break;
            case R.id.tv_exit_activity_setting:
                
                break;
        }
    }
}
