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
import com.medicine.ssqy.ssqy.entity.course.CourseVedioEntity;
import com.medicine.ssqy.ssqy.entity.course.CourseVedioListEntity;
import com.medicine.ssqy.ssqy.ui.adapter.ItemLvVedioCourseAdapter;
import com.medicine.ssqy.ssqy.ui.listener.ItemVedioClickListener;
import com.medicine.ssqy.ssqy.ui.views.ProgressWheel;
import com.medicine.ssqy.ssqy.util.UtilCourseDay;

import java.util.ArrayList;
import java.util.List;

public class VedioCourseListActivity extends KBaseActivity implements OnLoadMoreListener, OnRefreshListener {
    private ListView mSwipeTarget;
    
    private SwipeToLoadLayout mLayoutRefresh;
 
    private static final int TYPE_ALL=111222;
    private static final int TYPE_TODAY=222111;
    private static  int type;
    private List<CourseVedioEntity.VedioCourseDataEntity> mVedioCourseDataEntities;
    private ItemLvVedioCourseAdapter mItemLvVedioCourseAdapter;
    private List<CourseVedioListEntity> mCourseVedioListEntities;
    
    private ProgressWheel mPbLoadingCourseList;
    private boolean mIsFirstRequest=true;
    
    public static  void showAll(Context context){
        type=TYPE_ALL;
        Intent intent=new Intent(context,VedioCourseListActivity.class);
        context.startActivity(intent);
    }
    public static  void showToday(Context context){
        type=TYPE_TODAY;
        Intent intent=new Intent(context,VedioCourseListActivity.class);
        context.startActivity(intent);
    }
    @Override
    public int setRootView() {
        return R.layout.activity_vedio_course_list;
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
        setTitleCenter("今日视频课程");
    
        mSwipeTarget.setOnItemClickListener(new ItemVedioClickListener());
    }
    
    @Override
    public void initDatas() { 
        mLayoutRefresh.setRefreshing(true);
        
       
    }
    
    private void tempData() {
      
        mVedioCourseDataEntities=new ArrayList<>();
        if (type==TYPE_TODAY){
            for (int i = 2; i <=12; i++) {
                CourseVedioEntity.VedioCourseDataEntity vedioCourseDataEntity
                        = new CourseVedioEntity.VedioCourseDataEntity();
                vedioCourseDataEntity.setCourseID(i);
                vedioCourseDataEntity.setCourseDay(UtilCourseDay.getToday());
                vedioCourseDataEntity.setCourseLength(i*60*10*2);
                vedioCourseDataEntity.setCourseStudy(i*60*10);
                vedioCourseDataEntity.setCourseTitle("测试视频课程"+i);
                vedioCourseDataEntity.setCourseType("vedio");
                mVedioCourseDataEntities.add(vedioCourseDataEntity);
            }
        }else {
            for (int i = 2; i <=4; i++) {
                CourseVedioEntity.VedioCourseDataEntity vedioCourseDataEntity
                        = new CourseVedioEntity.VedioCourseDataEntity();
                vedioCourseDataEntity.setCourseID(i);
                vedioCourseDataEntity.setCourseDay(UtilCourseDay.getToday());
                vedioCourseDataEntity.setCourseLength(i*60*10*2);
                vedioCourseDataEntity.setCourseStudy(i*60*10);
                vedioCourseDataEntity.setCourseTitle("测试视频课程"+i);
                vedioCourseDataEntity.setCourseType("vedio");
                mVedioCourseDataEntities.add(vedioCourseDataEntity);
            }
    
            for (int i = 5; i <=8; i++) {
                CourseVedioEntity.VedioCourseDataEntity vedioCourseDataEntity
                        = new CourseVedioEntity.VedioCourseDataEntity();
                vedioCourseDataEntity.setCourseID(i);
                vedioCourseDataEntity.setCourseDay(UtilCourseDay.getYesterday());
                vedioCourseDataEntity.setCourseLength(i*60*10*2);
                vedioCourseDataEntity.setCourseStudy(i*60*10);
                vedioCourseDataEntity.setCourseTitle("测试视频课程"+i);
                vedioCourseDataEntity.setCourseType("vedio");
                mVedioCourseDataEntities.add(vedioCourseDataEntity);
            }
    
            for (int i = 9; i <=11; i++) {
                CourseVedioEntity.VedioCourseDataEntity vedioCourseDataEntity
                        = new CourseVedioEntity.VedioCourseDataEntity();
                vedioCourseDataEntity.setCourseID(i);
                vedioCourseDataEntity.setCourseDay(UtilCourseDay.getBeforeYesterday());
                vedioCourseDataEntity.setCourseLength(i*60*10*2);
                vedioCourseDataEntity.setCourseStudy(i*60*10);
                vedioCourseDataEntity.setCourseTitle("测试视频课程"+i);
                vedioCourseDataEntity.setCourseType("vedio");
                mVedioCourseDataEntities.add(vedioCourseDataEntity);
            }
        }
    
    
        reSortCourseList();
        mIsFirstRequest=false;
        mPbLoadingCourseList.setVisibility(View.GONE);
        mPbLoadingCourseList.stopSpinning();
        if (mItemLvVedioCourseAdapter==null) {
            mItemLvVedioCourseAdapter = new ItemLvVedioCourseAdapter(mSelf,mCourseVedioListEntities);
            mSwipeTarget.setAdapter(mItemLvVedioCourseAdapter);
        }
     
    }
    
    private void reSortCourseList(){
        if (mCourseVedioListEntities==null) {
            mCourseVedioListEntities=new ArrayList<>();
        }
        String timeSYG="";
        for (CourseVedioEntity.VedioCourseDataEntity vedioCourseDataEntity : mVedioCourseDataEntities) {
            String timeZYG=vedioCourseDataEntity.getCourseDay();
            
            if (!timeSYG.equals(timeZYG)){
                CourseVedioListEntity courseVedioListEntityTime=new CourseVedioListEntity();
                courseVedioListEntityTime.setTime(timeZYG);
                mCourseVedioListEntities.add(courseVedioListEntityTime);
                timeSYG=timeZYG;
            }
    
            CourseVedioListEntity courseVedioListEntity=new CourseVedioListEntity();
            courseVedioListEntity.setCourseVedioEntity(vedioCourseDataEntity);
            mCourseVedioListEntities.add(courseVedioListEntity);
            
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
        },2500);
      
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
