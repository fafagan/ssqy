package com.medicine.ssqy.ssqy.ui.activity;

import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.base.KBaseFragment;
import com.medicine.ssqy.ssqy.ui.fragment.courseHistory.HistoryAllFragment;
import com.medicine.ssqy.ssqy.ui.fragment.courseHistory.HistoryDayFragment;
import com.medicine.ssqy.ssqy.ui.fragment.courseHistory.HistoryMonthFragment;
import com.medicine.ssqy.ssqy.ui.fragment.courseHistory.HistoryWeekFragment;

import java.util.ArrayList;
import java.util.List;

public class CourseHistoryActivity extends KBaseActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup mRgCourseHistory;
    private RadioButton mRbCourseHistoryToday;
    private RadioButton mRbCourseHistoryWeek;
    private RadioButton mRbCourseHistoryMonth;
    private RadioButton mRbCourseHistoryAll;
    private FrameLayout mLayoutCourseHistoryFrags;
    
    private List<RadioButton> mRadioButtons=new ArrayList<>();
    private HistoryDayFragment mHistoryDayFragment=new HistoryDayFragment();
    private HistoryWeekFragment mHistoryWeekFragment=new HistoryWeekFragment();
    private HistoryMonthFragment mHistoryMonthFragment=new HistoryMonthFragment();
    private HistoryAllFragment mHistoryAllFragment=new HistoryAllFragment();
    
    private KBaseFragment[] mKBaseFragments={mHistoryDayFragment,mHistoryWeekFragment,mHistoryMonthFragment,mHistoryAllFragment};
    
    
    @Override
    public int setRootView() {
        return R.layout.activity_course_history;
    }
    
    @Override
    public void initViews() {
        setTitleCenter("我的学习记录");
        mRgCourseHistory = (RadioGroup) findViewById(R.id.rg_course_history);
        mRbCourseHistoryToday = (RadioButton) findViewById(R.id.rb_course_history_today);
        mRbCourseHistoryWeek = (RadioButton) findViewById(R.id.rb_course_history_week);
        mRbCourseHistoryMonth = (RadioButton) findViewById(R.id.rb_course_history_month);
        mRbCourseHistoryAll = (RadioButton) findViewById(R.id.rb_course_history_all);
        mLayoutCourseHistoryFrags = (FrameLayout) findViewById(R.id.layout_course_history_frags);
        mRadioButtons.add(mRbCourseHistoryToday);
        mRadioButtons.add(mRbCourseHistoryWeek);
        mRadioButtons.add(mRbCourseHistoryMonth);
        mRadioButtons.add(mRbCourseHistoryAll);
        mRgCourseHistory.setOnCheckedChangeListener(this);
        mRbCourseHistoryToday.setChecked(true);
        
    }
    
    @Override
    public void initDatas() {
        
    }
    
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton radioButton=null;
        KBaseFragment nowFrag=null;
        switch (checkedId){
            case R.id.rb_course_history_today:
                radioButton=mRbCourseHistoryToday;
                nowFrag=mHistoryDayFragment;
                break;
            case R.id.rb_course_history_week:
                radioButton=mRbCourseHistoryWeek;
                nowFrag=mHistoryWeekFragment;
                break;
            case R.id.rb_course_history_month:
                radioButton=mRbCourseHistoryMonth;
                nowFrag=mHistoryMonthFragment;
                break;
            case R.id.rb_course_history_all:
                radioButton=mRbCourseHistoryAll;
                nowFrag=mHistoryAllFragment;
                break;
        }
        if (radioButton.isChecked()) {
            radioButton.setTextColor(0xffffffff);
        }
    
        for (RadioButton button : mRadioButtons) {
            if (button!=radioButton){
                button.setTextColor(this.getResources().getColor(R.color.text_common_default)); 
            }
        } 
        changeHideAndShow(nowFrag);
    }
    
    private void changeHideAndShow(KBaseFragment nowFrag) {
        for (KBaseFragment kBaseFragment : mKBaseFragments) {
            if (nowFrag==kBaseFragment){
                if (!nowFrag.isAdded()){
                    addFrag(R.id.layout_course_history_frags,nowFrag);
                }
                showFrag(nowFrag);
                
            }else {
                if (nowFrag.isAdded()){
                    hideFrag(kBaseFragment);
                }
            }
        }
    }
}
