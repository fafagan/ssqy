package com.medicine.ssqy.ssqy.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.sj.mylibrary.net.NetCallback;
import com.example.sj.mylibrary.net.NetForJson;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.common.URLConstant;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.entity.course.CourseVedioEntity;
import com.medicine.ssqy.ssqy.ui.adapter.ItemLvVedioCourseAdapter;
import com.medicine.ssqy.ssqy.ui.views.ProgressWheel;

import java.util.List;

public class VedioCourseListActivity extends KBaseActivity implements OnLoadMoreListener, OnRefreshListener {
    private ListView mSwipeTarget;
    
    private SwipeToLoadLayout mLayoutRefresh;
    
    private static final int TYPE_ALL = 111222;
    private static final int TYPE_TODAY = 222111;
    private static int type;
    private ItemLvVedioCourseAdapter mItemLvVedioCourseAdapter;
    //    private List<CourseVedioListEntity> mCourseVedioListEntities;
    private List<CourseVedioEntity.VideoCourseDataEntity> mVideoCourseData;
    private ProgressWheel mPbLoadingCourseList;
    private boolean mIsFirstRequest = true;
    private NetForJson mNetForJson;
    public static final int TYPE_REFRESH = 110;
    public static final int TYPE_LOADMORE = 120;
    private int typeNet = TYPE_REFRESH;
    private int mPosStart;
    private int mCourseCount;
    private boolean mIsFirst;
    public static void showAll(Context context) {
        type = TYPE_ALL;
        Intent intent = new Intent(context, VedioCourseListActivity.class);
        context.startActivity(intent);
    }
    
    public static void showToday(Context context) {
        type = TYPE_TODAY;
        Intent intent = new Intent(context, VedioCourseListActivity.class);
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
        }, 100);
        mSwipeTarget = (ListView) findViewById(R.id.swipe_target);
        mLayoutRefresh = (SwipeToLoadLayout) findViewById(R.id.layout_refresh);
        mLayoutRefresh.setOnRefreshListener(this);
        mLayoutRefresh.setOnLoadMoreListener(this);
        mLayoutRefresh.setSwipeStyle(SwipeToLoadLayout.STYLE.ABOVE);
        if (type == TYPE_ALL) {
            setTitleCenter("全部养生视频");
        } else {
            setTitleCenter("今日养生视频");
        }
        
        mSwipeTarget.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CourseVedioEntity.VideoCourseDataEntity videoCourseDataEntity = mVideoCourseData.get(position);
                Intent intent = new Intent(mSelf, VideoPlayActivity.class);
                intent.putExtra("vd", videoCourseDataEntity);
                mSelf.startActivity(intent);
            }
        });
    }
    
    @Override
    public void initDatas() {
        
        mNetForJson = new NetForJson(URLConstant.VIDEO_LIST_URL, new NetCallback<CourseVedioEntity>() {
            @Override
            public void onSuccess(CourseVedioEntity entity) {
                
                mVideoCourseData = entity.getVideoCourseData();
                if (mVideoCourseData == null ||mVideoCourseData.size()==0) {
                    Toast.makeText(mSelf, "您今天没有养生视频任务！", Toast.LENGTH_SHORT).show();
                    return;
                }
                mCourseCount = entity.getCourseCount();
                if (typeNet == TYPE_REFRESH) {
                    if (mItemLvVedioCourseAdapter == null) {
                        mItemLvVedioCourseAdapter = new ItemLvVedioCourseAdapter(mSelf, mVideoCourseData);
                        mSwipeTarget.setAdapter(mItemLvVedioCourseAdapter);
                        mIsFirstRequest = false;
                        mPbLoadingCourseList.setVisibility(View.GONE);
                        mPbLoadingCourseList.stopSpinning();
                    } else {
                        mItemLvVedioCourseAdapter.setEntities(mVideoCourseData);
                    }
                    
                }else {
    
                    if (mItemLvVedioCourseAdapter == null) {
                        mItemLvVedioCourseAdapter = new ItemLvVedioCourseAdapter(mSelf, mVideoCourseData);
                        mSwipeTarget.setAdapter(mItemLvVedioCourseAdapter);
                        mIsFirstRequest = false;
                        mPbLoadingCourseList.setVisibility(View.GONE);
                        mPbLoadingCourseList.stopSpinning();
                    } else {
                        mItemLvVedioCourseAdapter.addEntities(mVideoCourseData);
                    }
                }
               
            }
            
            @Override
            public void onError() {
                Toast.makeText(mSelf, "加载失败，请检查您的网络状态！", Toast.LENGTH_SHORT).show();
                
                
            }
            
            @Override
            public void onFinish() {
                typeNet = TYPE_REFRESH;
                mLayoutRefresh.setLoadingMore(false);
                mPbLoadingCourseList.setVisibility(View.GONE);
                mLayoutRefresh.setRefreshing(false);
                mPbLoadingCourseList.stopSpinning();
            }
        }, true);
        mLayoutRefresh.setRefreshing(true);
        
        
    }
    
    @Override
    protected void onRestart() {
        super.onRestart();
        mLayoutRefresh.setRefreshing(true);
    }
    //    private void tempData() {
//      
//        mVedioCourseDataEntities=new ArrayList<>();
//        if (type==TYPE_TODAY){
//            for (int i = 2; i <=12; i++) {
//                CourseVedioEntity.VedioCourseDataEntity vedioCourseDataEntity
//                        = new CourseVedioEntity.VedioCourseDataEntity();
//                vedioCourseDataEntity.setCourseID(i);
//                vedioCourseDataEntity.setCourseDay(UtilCourseDay.getToday());
//                vedioCourseDataEntity.setCourseLength(i*60*10*2);
//                vedioCourseDataEntity.setCourseStudy(i*60*10);
//                vedioCourseDataEntity.setCourseTitle("测试视频课程"+i);
//                vedioCourseDataEntity.setCourseType("vedio");
//                mVedioCourseDataEntities.add(vedioCourseDataEntity);
//            }
//        }else {
//            for (int i = 2; i <=4; i++) {
//                CourseVedioEntity.VedioCourseDataEntity vedioCourseDataEntity
//                        = new CourseVedioEntity.VedioCourseDataEntity();
//                vedioCourseDataEntity.setCourseID(i);
//                vedioCourseDataEntity.setCourseDay(UtilCourseDay.getToday());
//                vedioCourseDataEntity.setCourseLength(i*60*10*2);
//                vedioCourseDataEntity.setCourseStudy(i*60*10);
//                vedioCourseDataEntity.setCourseTitle("测试视频课程"+i);
//                vedioCourseDataEntity.setCourseType("vedio");
//                mVedioCourseDataEntities.add(vedioCourseDataEntity);
//            }
//    
//            for (int i = 5; i <=8; i++) {
//                CourseVedioEntity.VedioCourseDataEntity vedioCourseDataEntity
//                        = new CourseVedioEntity.VedioCourseDataEntity();
//                vedioCourseDataEntity.setCourseID(i);
//                vedioCourseDataEntity.setCourseDay(UtilCourseDay.getYesterday());
//                vedioCourseDataEntity.setCourseLength(i*60*10*2);
//                vedioCourseDataEntity.setCourseStudy(i*60*10);
//                vedioCourseDataEntity.setCourseTitle("测试视频课程"+i);
//                vedioCourseDataEntity.setCourseType("vedio");
//                mVedioCourseDataEntities.add(vedioCourseDataEntity);
//            }
//    
//            for (int i = 9; i <=11; i++) {
//                CourseVedioEntity.VedioCourseDataEntity vedioCourseDataEntity
//                        = new CourseVedioEntity.VedioCourseDataEntity();
//                vedioCourseDataEntity.setCourseID(i);
//                vedioCourseDataEntity.setCourseDay(UtilCourseDay.getBeforeYesterday());
//                vedioCourseDataEntity.setCourseLength(i*60*10*2);
//                vedioCourseDataEntity.setCourseStudy(i*60*10);
//                vedioCourseDataEntity.setCourseTitle("测试视频课程"+i);
//                vedioCourseDataEntity.setCourseType("vedio");
//                mVedioCourseDataEntities.add(vedioCourseDataEntity);
//            }
//        }
//    
//    
//        reSortCourseList();
//        mIsFirstRequest=false;
//        mPbLoadingCourseList.setVisibility(View.GONE);
//        mPbLoadingCourseList.stopSpinning();
//        if (mItemLvVedioCourseAdapter==null) {
//            mItemLvVedioCourseAdapter = new ItemLvVedioCourseAdapter(mSelf,mCourseVedioListEntities);
//            mSwipeTarget.setAdapter(mItemLvVedioCourseAdapter);
//        }
//     
//    }

//    private void reSortCourseList(){
//        if (mCourseVedioListEntities==null) {
//            mCourseVedioListEntities=new ArrayList<>();
//        }
//        String timeSYG="";
//        for (CourseVedioEntity.VedioCourseDataEntity vedioCourseDataEntity : mVedioCourseDataEntities) {
//            String timeZYG=vedioCourseDataEntity.getCourseDay();
//            
//            if (!timeSYG.equals(timeZYG)){
//                CourseVedioListEntity courseVedioListEntityTime=new CourseVedioListEntity();
//                courseVedioListEntityTime.setTime(timeZYG);
//                mCourseVedioListEntities.add(courseVedioListEntityTime);
//                timeSYG=timeZYG;
//            }
//    
//            CourseVedioListEntity courseVedioListEntity=new CourseVedioListEntity();
//            courseVedioListEntity.setCourseVedioEntity(vedioCourseDataEntity);
//            mCourseVedioListEntities.add(courseVedioListEntity);
//            
//        }
//    }
    
    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doNetLoadMore();
            }
        }, 2500);
        
    }
    
    
    @Override
    public void onRefresh() {

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
        
        doNetRfresh();

//                
//            }
//        },1000);
        
    }
    
    private void doNetRfresh() {
//        Toast.makeText(mSelf, "正在加载，请稍等", Toast.LENGTH_SHORT).show();
//        tempData();
        typeNet = TYPE_REFRESH;
        mNetForJson.addParam("uid", SharePLogin.getUid());
        mNetForJson.addParam("startpos", "0");
        mNetForJson.addParam("count", "10");
        mNetForJson.excute();
    }
    
    private void doNetLoadMore() {
        if (mSwipeTarget.getCount() >= mCourseCount) {
            Toast.makeText(mSelf, "已全部加载完毕", Toast.LENGTH_SHORT).show();
            mLayoutRefresh.setLoadingMore(false);
            return;
        }
    
        mPosStart = mSwipeTarget.getCount();
        typeNet = TYPE_LOADMORE;
        mLayoutRefresh.setLoadingMore(false);
        mNetForJson.addParam("uid", SharePLogin.getUid());
        mNetForJson.addParam("startpos", mPosStart);
        mNetForJson.addParam("count", 10);
        mNetForJson.excute();
        
    }
    
    @Override
    public void onBackPressed() {
        if (mIsFirstRequest) {
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
