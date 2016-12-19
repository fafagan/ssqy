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
import com.medicine.ssqy.ssqy.entity.course.CoursePicEntity;
import com.medicine.ssqy.ssqy.entity.course.CoursePicListEntity;
import com.medicine.ssqy.ssqy.ui.adapter.ItemLvPicCourseAdapter;
import com.medicine.ssqy.ssqy.ui.listener.ItemTWClickListener;
import com.medicine.ssqy.ssqy.ui.views.ProgressWheel;
import com.medicine.ssqy.ssqy.util.UtilCourseDay;

import java.util.ArrayList;
import java.util.List;

public class PicCourseListActivity extends KBaseActivity implements OnLoadMoreListener, OnRefreshListener {
    private ListView mSwipeTarget;
    
    private SwipeToLoadLayout mLayoutRefresh;
 
    private static final int TYPE_ALL=111222;
    private static final int TYPE_TODAY=222111;
    private static  int type;
    private List<CoursePicEntity.PicCourseDataEntity> mPicCourseDataEntities;
    private ItemLvPicCourseAdapter mItemLvPicCourseAdapter;
    private List<CoursePicListEntity> mCoursePicListEntities;
    
    private ProgressWheel mPbLoadingCourseList;
    private boolean mIsFirstRequest=true;

    
    public static  void showAll(Context context){
        type=TYPE_ALL;
        Intent intent=new Intent(context,PicCourseListActivity.class);
        context.startActivity(intent);
    }
    public static  void showToday(Context context){
        type=TYPE_TODAY;
        Intent intent=new Intent(context,PicCourseListActivity.class);
        context.startActivity(intent);
    }
    @Override
    public int setRootView() {
        return R.layout.activity_pic_course_list;
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
        setTitleCenter("今日图文课程");
    
        mSwipeTarget.setOnItemClickListener(new ItemTWClickListener());
    }
    
    @Override
    public void initDatas() { 
        mLayoutRefresh.setRefreshing(true);
        
       
    }
    
    private void tempData() {
      
        mPicCourseDataEntities=new ArrayList<>();
        if (type==TYPE_TODAY){
            for (int i = 1; i <=22; i++) {
                CoursePicEntity.PicCourseDataEntity picCourseDataEntity
                        = new CoursePicEntity.PicCourseDataEntity();
                picCourseDataEntity.setCourseID(i);
                picCourseDataEntity.setCourseDay(UtilCourseDay.getToday());
                picCourseDataEntity.setCourseLearned(i%2==0);
                picCourseDataEntity.setCourseTitle("四时七养养生系列课程"+i);
                picCourseDataEntity.setCourseType("pic");
                mPicCourseDataEntities.add(picCourseDataEntity);
            }
        }else {
            for (int i = 1; i <=3; i++) {
                CoursePicEntity.PicCourseDataEntity picCourseDataEntity
                        = new CoursePicEntity.PicCourseDataEntity();
                picCourseDataEntity.setCourseID(i);
                picCourseDataEntity.setCourseDay(UtilCourseDay.getToday());
                picCourseDataEntity.setCourseLearned(i%2==0);
                picCourseDataEntity.setCourseTitle("四时七养养生系列课程"+i);
                picCourseDataEntity.setCourseType("pic");
                mPicCourseDataEntities.add(picCourseDataEntity);
            }
            for (int i = 4; i <=6; i++) {
                CoursePicEntity.PicCourseDataEntity picCourseDataEntity
                        = new CoursePicEntity.PicCourseDataEntity();
                picCourseDataEntity.setCourseID(i);
                picCourseDataEntity.setCourseDay(UtilCourseDay.getYesterday());
                picCourseDataEntity.setCourseLearned(i%2==0);
                picCourseDataEntity.setCourseTitle("四时七养养生系列课程"+i);
                picCourseDataEntity.setCourseType("pic");
                mPicCourseDataEntities.add(picCourseDataEntity);
            }
    
            for (int i = 7; i <=15; i++) {
                CoursePicEntity.PicCourseDataEntity picCourseDataEntity
                        = new CoursePicEntity.PicCourseDataEntity();
                picCourseDataEntity.setCourseID(i);
                picCourseDataEntity.setCourseDay(UtilCourseDay.getBeforeYesterday());
                picCourseDataEntity.setCourseLearned(i%2==0);
                picCourseDataEntity.setCourseTitle("四时七养养生系列课程"+i);
                picCourseDataEntity.setCourseType("pic");
                mPicCourseDataEntities.add(picCourseDataEntity);
            }
        }
   
    
        reSortCourseList();
        mIsFirstRequest=false;
        mPbLoadingCourseList.setVisibility(View.GONE);
        mPbLoadingCourseList.stopSpinning();
        if (mItemLvPicCourseAdapter==null) {
            mItemLvPicCourseAdapter = new ItemLvPicCourseAdapter(mSelf,mCoursePicListEntities);
            mSwipeTarget.setAdapter(mItemLvPicCourseAdapter);
        }
        
     
    }
    
    private void reSortCourseList(){
        if (mCoursePicListEntities==null) {
            mCoursePicListEntities=new ArrayList<>();
        }
        String timeSYG="";
        for (CoursePicEntity.PicCourseDataEntity picCourseDataEntity : mPicCourseDataEntities) {
            String timeZYG=picCourseDataEntity.getCourseDay();
            
            if (!timeSYG.equals(timeZYG)){
                CoursePicListEntity coursePicListEntityTime=new CoursePicListEntity();
                coursePicListEntityTime.setTime(timeZYG);
                mCoursePicListEntities.add(coursePicListEntityTime);
                timeSYG=timeZYG;
            }
    
            CoursePicListEntity coursePicListEntity=new CoursePicListEntity();
            coursePicListEntity.setCoursePicEntity(picCourseDataEntity);
            mCoursePicListEntities.add(coursePicListEntity);
            
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
