package com.medicine.ssqy.ssqy.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.entity.course.CourseAudioEntity;
import com.medicine.ssqy.ssqy.entity.course.CourseAudioListEntity;
import com.medicine.ssqy.ssqy.ui.adapter.ItemLvAudioCourseAdapter;
import com.medicine.ssqy.ssqy.ui.listener.ItemAudioClickListener;
import com.medicine.ssqy.ssqy.ui.views.ProgressWheel;
import com.medicine.ssqy.ssqy.util.UtilCourseDay;

import java.util.ArrayList;
import java.util.List;

public class AudioCourseListActivity extends KBaseActivity implements OnLoadMoreListener, OnRefreshListener {
    private ListView mSwipeTarget;
    
    private SwipeToLoadLayout mLayoutRefresh;
 
    private static final int TYPE_ALL=111222;
    private static final int TYPE_TODAY=222111;
    private static  int type;
    private List<CourseAudioEntity.AudioCourseDataEntity> mAudioCourseDataEntities;
    private ItemLvAudioCourseAdapter mItemLvAudioCourseAdapter;
    private List<CourseAudioListEntity> mCourseAudioListEntities;
    private ProgressWheel mPbLoadingCourseList;
    private boolean mIsFirstRequest=true;
    public static  void showAll(Context context){
        type=TYPE_ALL;
        Intent intent=new Intent(context,AudioCourseListActivity.class);
        context.startActivity(intent);
    }
    public static  void showToday(Context context){
        type=TYPE_TODAY;
        Intent intent=new Intent(context,AudioCourseListActivity.class);
        context.startActivity(intent);
    }
    @Override
    public int setRootView() {
        return R.layout.activity_audio_course_list;
    }
    
    @Override
    public void initViews() {
    
        mPbLoadingCourseList = (ProgressWheel) findViewById(R.id.pb_loading_course_list);
        mPbLoadingCourseList.spin();
        mPbLoadingCourseList.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPbLoadingCourseList.setVisibility(View.VISIBLE);
            }
        },100);
        mSwipeTarget = (ListView) findViewById(R.id.swipe_target);
        mLayoutRefresh = (SwipeToLoadLayout) findViewById(R.id.layout_refresh);
        mLayoutRefresh.setOnRefreshListener(this);
        mLayoutRefresh.setOnLoadMoreListener(this);
        mLayoutRefresh.setSwipeStyle(SwipeToLoadLayout.STYLE.ABOVE);
        setTitleCenter("今日音频课程");
        mSwipeTarget.setOnItemClickListener(new ItemAudioClickListener());
       
    }
    
    @Override
    public void initDatas() { 
    
        mLayoutRefresh.setRefreshing(true);
    
    }
    
    private void tempData() {

        mAudioCourseDataEntities=new ArrayList<>();
        if (type==TYPE_TODAY){
            for (int i = 2; i <=12; i++) {
                CourseAudioEntity.AudioCourseDataEntity audioCourseDataEntity
                        = new CourseAudioEntity.AudioCourseDataEntity();
                audioCourseDataEntity.setCourseID(i);
                audioCourseDataEntity.setCourseDay(UtilCourseDay.getToday());
                audioCourseDataEntity.setCourseLength(i*60*10*2);
                audioCourseDataEntity.setCourseStudy(i*60*10);
                audioCourseDataEntity.setCourseTitle("一首美妙的音乐"+i);
                audioCourseDataEntity.setCourseType("audio");
                mAudioCourseDataEntities.add(audioCourseDataEntity);
            }
        }else {
            for (int i = 2; i <=4; i++) {
                CourseAudioEntity.AudioCourseDataEntity audioCourseDataEntity
                        = new CourseAudioEntity.AudioCourseDataEntity();
                audioCourseDataEntity.setCourseID(i);
                audioCourseDataEntity.setCourseDay(UtilCourseDay.getToday());
                audioCourseDataEntity.setCourseLength(i*60*10*2);
                audioCourseDataEntity.setCourseStudy(i*60*10);
                audioCourseDataEntity.setCourseTitle("一首美妙的音乐"+i);
                audioCourseDataEntity.setCourseType("audio");
                mAudioCourseDataEntities.add(audioCourseDataEntity);
            }
            for (int i = 5; i <=8; i++) {
                CourseAudioEntity.AudioCourseDataEntity audioCourseDataEntity
                        = new CourseAudioEntity.AudioCourseDataEntity();
                audioCourseDataEntity.setCourseID(i);
                audioCourseDataEntity.setCourseDay(UtilCourseDay.getYesterday());
                audioCourseDataEntity.setCourseLength(i*60*10*2);
                audioCourseDataEntity.setCourseStudy(i*60*10);
                audioCourseDataEntity.setCourseTitle("一首美妙的音乐"+i);
                audioCourseDataEntity.setCourseType("audio");
                mAudioCourseDataEntities.add(audioCourseDataEntity);
            }
            for (int i = 9; i <=12; i++) {
                CourseAudioEntity.AudioCourseDataEntity audioCourseDataEntity
                        = new CourseAudioEntity.AudioCourseDataEntity();
                audioCourseDataEntity.setCourseID(i);
                audioCourseDataEntity.setCourseDay(UtilCourseDay.getBeforeYesterday());
                audioCourseDataEntity.setCourseLength(i*60*10*2);
                audioCourseDataEntity.setCourseStudy(i*60*10);
                audioCourseDataEntity.setCourseTitle("一首美妙的音乐"+i);
                audioCourseDataEntity.setCourseType("audio");
                mAudioCourseDataEntities.add(audioCourseDataEntity);
            }
        }
     
    
        reSortCourseList();
        mIsFirstRequest=false;
        mPbLoadingCourseList.setVisibility(View.GONE);
        mPbLoadingCourseList.stopSpinning();
        if (mItemLvAudioCourseAdapter==null) {
            mItemLvAudioCourseAdapter = new ItemLvAudioCourseAdapter(mSelf,mCourseAudioListEntities);
            mSwipeTarget.setAdapter(mItemLvAudioCourseAdapter);
        }
     
    }
    
    private void reSortCourseList(){
        if (mCourseAudioListEntities==null) {
            mCourseAudioListEntities=new ArrayList<>();
        }
        String timeSYG="";
        for (CourseAudioEntity.AudioCourseDataEntity audioCourseDataEntity : mAudioCourseDataEntities) {
            String timeZYG=audioCourseDataEntity.getCourseDay();
            
            if (!timeSYG.equals(timeZYG)){
                CourseAudioListEntity courseAudioListEntityTime=new CourseAudioListEntity();
                courseAudioListEntityTime.setTime(timeZYG);
                mCourseAudioListEntities.add(courseAudioListEntityTime);
                timeSYG=timeZYG;
            }
    
            CourseAudioListEntity courseAudioListEntity=new CourseAudioListEntity();
            courseAudioListEntity.setCourseAudioEntity(audioCourseDataEntity);
            mCourseAudioListEntities.add(courseAudioListEntity);
            
        }
    }
    
    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doNetLoadMore();
            }
        },2500);
   
    }
    
    
    
    @Override
    public void onRefresh() {
    
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doNetRfresh();

                
            }
        },1500);
      
    }
    
    private void doNetRfresh() {
        Toast.makeText(mSelf, "已加载最新课程", Toast.LENGTH_SHORT).show();
        tempData();
        mLayoutRefresh.setRefreshing(false);
    }
    
    private void doNetLoadMore() {
        mLayoutRefresh.setLoadingMore(false);
        
    }
    
    @Override
    public void onBackPressed() {
        if (mIsFirstRequest){
            mPbLoadingCourseList.stopSpinning();
            mLayoutRefresh.setRefreshing(false);
            super.onBackPressed();
            return;
        }
        if (mLayoutRefresh.isRefreshing()) {
            mLayoutRefresh.setRefreshing(false);
            return;
        }
        
        if (mLayoutRefresh.isLoadingMore()) {
            mLayoutRefresh.setLoadingMore(false);
            return;
        }
        super.onBackPressed();
    }
}
