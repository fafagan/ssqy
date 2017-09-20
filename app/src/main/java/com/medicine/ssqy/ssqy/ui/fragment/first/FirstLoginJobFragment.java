package com.medicine.ssqy.ssqy.ui.fragment.first;

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

public class FirstLoginJobFragment extends KBaseFragment {
    private TextView mTvJobFirstLogin;
    private WheelView mWvJobFirstLogin;
    private Button mBtNext;
    private List<String> mJobs=new ArrayList<>();
  
    
    
    @Override
    public boolean isUseTitle() {
        return false;
    }
    
    @Override
    public int setRootView() {
        return R.layout.fragment_first_login_job;
    }
    
    @Override
    public void initViews() {
        mTvJobFirstLogin = (TextView) findViewById(R.id.tv_job_first_login);
        mWvJobFirstLogin = (WheelView) findViewById(R.id.wv_job_first_login);
        mBtNext = (Button) findViewById(R.id.bt_next);
        mBtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            
                FirstLoginMsg firstLoginMsg = new FirstLoginMsg();
                firstLoginMsg.action = FirstLoginMsg.ACTION_DONE_3;
                firstLoginMsg.job = mTvJobFirstLogin.getText().toString().trim();
                EventBus.getDefault().post(firstLoginMsg);
            }
        });
    }
    
    @Override
    public void initDatas() {
        initWVDatas();
    
        mWvJobFirstLogin.setWheelAdapter(new TVWheelAdapter(mActivity)); // 文本数据源
        WheelView.WheelViewStyle wheelViewStyle = new WheelView.WheelViewStyle();
        wheelViewStyle.backgroundColor = 0x00ffffff;
        mWvJobFirstLogin.setStyle(wheelViewStyle);
        mWvJobFirstLogin.setSkin(WheelView.Skin.None); // common皮肤
        mWvJobFirstLogin.setWheelData(mJobs);  // 数据集合
        mWvJobFirstLogin.setLoop(true);
        mWvJobFirstLogin.setWheelSize(3);
        mWvJobFirstLogin.setSelection(3);
        mWvJobFirstLogin.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener() {
            @Override
            public void onItemSelected(int i, Object o) {
                mTvJobFirstLogin.setText(o.toString());
            }
        });
        
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
    
}
