package com.medicine.ssqy.ssqy.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseFragment;
import com.medicine.ssqy.ssqy.eventBus.FirstLoginMsg;
import com.medicine.ssqy.ssqy.ui.views.TVWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class FirstLoginStudyLevelFragment extends KBaseFragment {
    private TextView mTvStudyFirstLogin;
    private WheelView mWvStudyFirstLogin;
    private Button mBtNext;
    
    
    private List<String> mStudys=new ArrayList<>();
  
    
    
    @Override
    public boolean isUseTitle() {
        return false;
    }
    
    @Override
    public int setRootView() {
        return R.layout.fragment_first_login_study;
    }
    
    @Override
    public void initViews() {
    
        mTvStudyFirstLogin = (TextView) findViewById(R.id.tv_study_first_login);
        mWvStudyFirstLogin = (WheelView) findViewById(R.id.wv_study_first_login);
        mBtNext = (Button) findViewById(R.id.bt_next);
        mBtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            
                FirstLoginMsg firstLoginMsg = new FirstLoginMsg();
                firstLoginMsg.action = FirstLoginMsg.ACTION_DONE_ALL;
                firstLoginMsg.studyLevel = mTvStudyFirstLogin.getText().toString().trim();
                EventBus.getDefault().post(firstLoginMsg);
            }
        });
    }
    
    @Override
    public void initDatas() {
        initWVDatas();
    
        mWvStudyFirstLogin.setWheelAdapter(new TVWheelAdapter(mActivity)); // 文本数据源
        WheelView.WheelViewStyle wheelViewStyle = new WheelView.WheelViewStyle();
        wheelViewStyle.backgroundColor = 0x00ffffff;
        mWvStudyFirstLogin.setStyle(wheelViewStyle);
        mWvStudyFirstLogin.setSkin(WheelView.Skin.None); // common皮肤
        mWvStudyFirstLogin.setWheelData(mStudys);  // 数据集合
        mWvStudyFirstLogin.setLoop(true);
        mWvStudyFirstLogin.setWheelSize(3);
        mWvStudyFirstLogin.setSelection(3);
        mWvStudyFirstLogin.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener() {
            @Override
            public void onItemSelected(int i, Object o) {
                mTvStudyFirstLogin.setText(o.toString());
            }
        });
        
    }
    
    
    private void initWVDatas() {
    
        mStudys.add("小学");
        mStudys.add("初中");
        mStudys.add("高中");
        mStudys.add("大学本科");
        mStudys.add("研究生");
        mStudys.add("博士");
        mStudys.add("专科");
        mStudys.add("其他学历");
    }
    
}
