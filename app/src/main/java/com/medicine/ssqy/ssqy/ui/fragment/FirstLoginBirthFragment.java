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

public class FirstLoginBirthFragment extends KBaseFragment {
    
    private TextView mTvBirthFirstLogin;
    private WheelView mWvYearFirstLogin;
    private WheelView mWvMonthFirstLogin;
    private WheelView mWvDayFirstLogin;
    private Button mBtNext;
    private List<Integer> mDatasYear = new ArrayList<>();
    private List<Integer> mDatasMonth = new ArrayList<>();
    private List<Integer> mDatasDay31 = new ArrayList<>();
    private List<Integer> mDatasDay30 = new ArrayList<>();
    private List<Integer> mDatasDay29 = new ArrayList<>();
    private List<Integer> mDatasDay28 = new ArrayList<>();
    
    private int mYear = 1980, mMonth = 1, mDay = 1;
    private StringBuilder stringBuilder = new StringBuilder();
    
    @Override
    public boolean isUseTitle() {
        return false;
    }
    
    @Override
    public int setRootView() {
        return R.layout.fragment_first_login_birth;
    }
    
    @Override
    public void initViews() {
        mTvBirthFirstLogin = (TextView) findViewById(R.id.tv_birth_first_login);
        mWvYearFirstLogin = (WheelView) findViewById(R.id.wv_year_first_login);
        mWvMonthFirstLogin = (WheelView) findViewById(R.id.wv_month_first_login);
        mWvDayFirstLogin = (WheelView) findViewById(R.id.wv_day_first_login);
        mBtNext = (Button) findViewById(R.id.bt_next);
        mBtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                FirstLoginMsg firstLoginMsg = new FirstLoginMsg();
                firstLoginMsg.action = FirstLoginMsg.ACTION_DONE_2;
                firstLoginMsg.birthDay = mTvBirthFirstLogin.getText().toString().trim();
                EventBus.getDefault().post(firstLoginMsg);
            }
        });
        
    }
    
    @Override
    public void initDatas() {
        initWVDatas();
        
        mWvYearFirstLogin.setWheelAdapter(new TVWheelAdapter(mActivity)); // 文本数据源
        WheelView.WheelViewStyle wheelViewStyle = new WheelView.WheelViewStyle();
        wheelViewStyle.backgroundColor = 0x00ffffff;
        mWvYearFirstLogin.setStyle(wheelViewStyle);
        mWvYearFirstLogin.setSkin(WheelView.Skin.None); // common皮肤
        mWvYearFirstLogin.setWheelData(mDatasYear);  // 数据集合
        mWvYearFirstLogin.setLoop(true);
        mWvYearFirstLogin.setWheelSize(3);
        mWvYearFirstLogin.setSelection(34);
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
        
        mWvMonthFirstLogin.setWheelAdapter(new TVWheelAdapter(mActivity)); // 文本数据源
        mWvMonthFirstLogin.setStyle(wheelViewStyle);
        mWvMonthFirstLogin.setSkin(WheelView.Skin.None); // common皮肤
        mWvMonthFirstLogin.setWheelData(mDatasMonth);  // 数据集合
        mWvMonthFirstLogin.setLoop(true);
        mWvMonthFirstLogin.setWheelSize(3);
        mWvMonthFirstLogin.setSelection(0);
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
            }
        });
        
        
        mWvDayFirstLogin.setWheelAdapter(new TVWheelAdapter(mActivity)); // 文本数据源
        mWvDayFirstLogin.setStyle(wheelViewStyle);
        mWvDayFirstLogin.setSkin(WheelView.Skin.None); // common皮肤
        mWvDayFirstLogin.setWheelData(mDatasDay31);  // 数据集合
        mWvDayFirstLogin.setLoop(true);
        mWvDayFirstLogin.setWheelSize(3);
        mWvDayFirstLogin.setSelection(0);
        mWvDayFirstLogin.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener() {
            @Override
            public void onItemSelected(int i, Object o) {
                mDay = (int) o;
                setTvText();
            }
        });
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
    
}
