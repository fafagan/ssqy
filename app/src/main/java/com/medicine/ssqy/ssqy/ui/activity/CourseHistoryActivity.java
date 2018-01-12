package com.medicine.ssqy.ssqy.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sj.mylibrary.net.NetCallback;
import com.example.sj.mylibrary.net.NetForJson;
import com.example.sj.mylibrary.util.StringEmptyUtil;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.common.URLConstant;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.entity.CourseHistory;
import com.medicine.ssqy.ssqy.ui.adapter.ItemLvHistoryDayAdapter;
import com.medicine.ssqy.ssqy.ui.views.calendar.CalendarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CourseHistoryActivity extends KBaseActivity implements AdapterView.OnItemClickListener {
    
    private CalendarView calendarView;
    private TextView mTvByysrj;
    private TextView mTvDate;
    private ListView mLvCourseHistory;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
    private SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
    List<Date> markDates = new ArrayList<>();
    private NetForJson mNetForJson;
    private List<CourseHistory.CourseDataEntity> mCourseData;
    private ItemLvHistoryDayAdapter mItemLvHistoryDayAdapter;
    private TextView mTvNoneHistory;
    private Date mDateNow;
//数据库里身份证号，每个5年一组  5年里0岁  1岁  
    //
    
    @Override
    public int setRootView() {
        return R.layout.activity_course_history;
    }
    
    @Override
    public void initViews() {
        setTitleCenter("我的养生记录");
        mTvNoneHistory = (TextView) findViewById(R.id.tv_none_history);
        calendarView = (CalendarView) findViewById(R.id.calender);
        mTvByysrj = (TextView) findViewById(R.id.tv_byysrj);
        mTvDate = (TextView) findViewById(R.id.tv_date);
        mTvDate.setText(format.format(new Date()));
        mDateNow=new Date();
        mLvCourseHistory = (ListView) findViewById(R.id.lv_course_history);
        //设置点击操作
        calendarView.setOnCalendarViewListener(new CalendarView.OnCalendarViewListener() {
        
            @Override
            public void onCalendarItemClick(CalendarView view, Date date) {
                mNetForJson.cancel();
    
         
                if (date.getTime()>System.currentTimeMillis()){
                    Toast.makeText(CourseHistoryActivity.this, "只能查看今天及以前的记录呦！", Toast.LENGTH_SHORT).show();
                    return;
                }
    
           
                markDates.clear();
                markDates.add(date);
                calendarView.setMarkDates(markDates);
//                Toast.makeText(MainActivity.this, format.format(date), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(CourseHistoryActivity.this, RecordDateActivity.class);
//                intent.putExtra("date", format.format(date));
//                intent.putExtra("date2", format2.format(date));
//                startActivity(intent);
                mDateNow=date;
                mTvDate.setText(format.format(date));
    
                mNetForJson.addParam("uid", SharePLogin.getUid());
                mNetForJson.addParam("date", format2.format(date));
//                mNetForJson.addParam("startpos", "0");
//                mNetForJson.addParam("count", "100");
//                mNetForJson.addParam("order", "asc");
//                mNetForJson.addParam("type", "day");
                mNetForJson.excute();
            }
        });
        
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        initNet();
    }
    
    @Override
    public void initDatas() {
    

    }
    private  void  initNet(){
        mNetForJson=new NetForJson(URLConstant.COURSE_HISTORY_URL_ONEDAY, new NetCallback<CourseHistory>() {
            
            @Override
            public void onSuccess(CourseHistory entity) {
                mCourseData = entity.getCourseData();
                if (mCourseData != null&&mCourseData.size()>0) {
                    mTvNoneHistory.setVisibility(View.GONE);
                    mLvCourseHistory.setVisibility(View.VISIBLE);
//                    for (CourseHistory.CourseDataEntity courseDataEntity : mCourseData) {
//                        calculateTotalTime(courseDataEntity);
//                    }
                    if (mItemLvHistoryDayAdapter==null) {
                        mItemLvHistoryDayAdapter = new ItemLvHistoryDayAdapter(mSelf, mCourseData);
                        mLvCourseHistory.setAdapter(mItemLvHistoryDayAdapter);
                        mLvCourseHistory.setOnItemClickListener(CourseHistoryActivity.this);
                    }else {
                        mItemLvHistoryDayAdapter.setEntities(mCourseData);
                    }
                }else {
                    mTvNoneHistory.setVisibility(View.VISIBLE);
                    mLvCourseHistory.setVisibility(View.GONE);
                }
            }
            
            @Override   
            public void onError() {
                Toast.makeText(mSelf, "网络异常！", Toast.LENGTH_SHORT).show();
                mTvNoneHistory.setVisibility(View.VISIBLE);
                mLvCourseHistory.setVisibility(View.GONE);
            }
            
            @Override
            public void onFinish() {
//                mSwipeToLoadLayout.setRefreshing(false);
//                mSwipeToLoadLayout.setLoadingMore(false);
            }
        });
        
        mNetForJson.addParam("uid", SharePLogin.getUid());
        mNetForJson.addParam("date", format2.format(mDateNow));
//        mNetForJson.addParam("startpos", "0");
//        mNetForJson.addParam("count", "100");
//        mNetForJson.addParam("order", "asc");
//        mNetForJson.addParam("type", "day");
//        mSwipeToLoadLayout.setRefreshing(true);
        mNetForJson.excute();
    }
    
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CourseHistory.CourseDataEntity courseDataEntity = mCourseData.get(position);
        if (courseDataEntity != null) {
            if (!StringEmptyUtil.isEmpty(courseDataEntity.getCourseType())) {
                if (!StringEmptyUtil.isEmpty(courseDataEntity.getCourseID())){
                    String courseType = courseDataEntity.getCourseType();
                    String courseID = courseDataEntity.getCourseID();
    
                    switch (courseType) {
                        case "video":
                            Intent intent1 = new Intent(mSelf, VideoPlayActivity.class);
                            intent1.putExtra("courseid",courseID);
                            intent1.putExtra("date",format2.format(mDateNow));
                            mSelf.startActivity(intent1);
                            break;
                        case "audio":
    
                            Intent intent2 = new Intent(mSelf, AudioPlayActivity.class);
                            intent2.putExtra("courseid",courseID);
                            intent2.putExtra("date",format2.format(mDateNow));
                            mSelf.startActivity(intent2);
                            break;
                        case "pic":
                            Intent intent3 = new Intent(mSelf, CourseDetailPicActivity.class);
                            intent3.putExtra("courseid",courseID);
                            intent3.putExtra("date",format2.format(mDateNow));
                            mSelf.startActivity(intent3);
                            break;
                    }
    
    
                }
            }
        }
        
    }
}
