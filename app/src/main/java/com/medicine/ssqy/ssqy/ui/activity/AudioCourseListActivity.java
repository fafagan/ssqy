package com.medicine.ssqy.ssqy.ui.activity;

import android.content.Context;
import android.content.Intent;
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
import com.medicine.ssqy.ssqy.entity.course.CourseAudioListEntity;
import com.medicine.ssqy.ssqy.ui.adapter.ItemLvAudioCourseAdapter;
import com.medicine.ssqy.ssqy.ui.views.ProgressWheel;

import java.util.List;

public class AudioCourseListActivity extends KBaseActivity implements OnLoadMoreListener, OnRefreshListener {
    private ListView mSwipeTarget;
    
    private SwipeToLoadLayout mLayoutRefresh;
    
    private static final int TYPE_ALL = 111222;
    private static final int TYPE_TODAY = 222111;
    private static int type;
    //    private List<CourseAudioEntity.AudioCourseDataEntity> mAudioCourseDataEntities;
    private List<CourseAudioListEntity.AudioCourseDataEntity> mAudioCourseData;
    private ItemLvAudioCourseAdapter mItemLvAudioCourseAdapter;
    private ProgressWheel mPbLoadingCourseList;
    private boolean mIsFirstRequest = true;
    private NetForJson mNetForJson;
    private int mPosStart = 0;
    public static final int TYPE_REFRESH = 110;
    public static final int TYPE_LOADMORE = 120;
    private int typeNet = TYPE_REFRESH;
    private int mCourseCount;
    
    public static void showAll(Context context) {
        type = TYPE_ALL;
        Intent intent = new Intent(context, AudioCourseListActivity.class);
        context.startActivity(intent);
    }
    
    public static void showToday(Context context) {
        type = TYPE_TODAY;
        Intent intent = new Intent(context, AudioCourseListActivity.class);
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
        }, 100);
        mSwipeTarget = (ListView) findViewById(R.id.swipe_target);
        mLayoutRefresh = (SwipeToLoadLayout) findViewById(R.id.layout_refresh);
        mLayoutRefresh.setOnRefreshListener(this);
        mLayoutRefresh.setOnLoadMoreListener(this);
        mLayoutRefresh.setSwipeStyle(SwipeToLoadLayout.STYLE.ABOVE);
        
        if (type == TYPE_ALL) {
            setTitleCenter("全部养生音频");
        } else {
            setTitleCenter("今日养生音频");
        }
        
        mSwipeTarget.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = view.getContext();
                
                Intent intent = new Intent(context, AudioPlayActivity.class);
                intent.putExtra("course",mAudioCourseData.get(position));
                context.startActivity(intent);
                
            }
        });
        
    }
    
    @Override
    public void initDatas() {
        
        mLayoutRefresh.setRefreshing(true);
        mNetForJson = new NetForJson(URLConstant.AUDIO_LIST_URL, new NetCallback<CourseAudioListEntity>() {
            
            
            @Override
            public void onSuccess(CourseAudioListEntity entity) {
                
                if (entity.isState()) {
                    
                    mCourseCount = entity.getCourseCount();
                
                    if (typeNet == TYPE_REFRESH) {
                        mAudioCourseData = entity.getAudioCourseData();
    
                        if (mAudioCourseData == null ||mAudioCourseData.size()==0) {
                            Toast.makeText(mSelf, "您今天没有养生音频任务！", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (mItemLvAudioCourseAdapter == null) {
                            mItemLvAudioCourseAdapter = new ItemLvAudioCourseAdapter(mSelf, entity.getAudioCourseData());
                            mSwipeTarget.setAdapter(mItemLvAudioCourseAdapter);
                        } else {
                            mItemLvAudioCourseAdapter.setEntities(entity.getAudioCourseData());
                        }
                        
                        
                    } else if (typeNet == TYPE_LOADMORE) {
                        mAudioCourseData.addAll(entity.getAudioCourseData());
                        if (mItemLvAudioCourseAdapter == null) {
                            mItemLvAudioCourseAdapter = new ItemLvAudioCourseAdapter(mSelf, entity.getAudioCourseData());
                            mSwipeTarget.setAdapter(mItemLvAudioCourseAdapter);
                        } else {
                            mItemLvAudioCourseAdapter.addEntities(entity.getAudioCourseData());
                        }
                    }
                } else {
                    Toast.makeText(mSelf, "加载错误，请检查您的网络状态！", Toast.LENGTH_SHORT).show();
                }
                
            }
            
            @Override
            public void onError() {
                Toast.makeText(mSelf, "加载错误，请检查您的网络状态！", Toast.LENGTH_SHORT).show();
                
            }
            
            @Override
            public void onFinish() {
                typeNet = TYPE_REFRESH;
                mLayoutRefresh.setLoadingMore(false);
                mPbLoadingCourseList.setVisibility(View.GONE);
                mLayoutRefresh.setRefreshing(false);
                mPbLoadingCourseList.stopSpinning();
            }
        },true);
        
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        mLayoutRefresh.setRefreshing(true);
    }
//    private void tempData() {
//
//        mAudioCourseDataEntities=new ArrayList<>();
//        if (type==TYPE_TODAY){
//            for (int i = 2; i <=12; i++) {
//                CourseAudioEntity.AudioCourseDataEntity audioCourseDataEntity
//                        = new CourseAudioEntity.AudioCourseDataEntity();
//                audioCourseDataEntity.setCourseID(i);
//                audioCourseDataEntity.setCourseDay(UtilCourseDay.getToday());
//                audioCourseDataEntity.setCourseLength(i*60*10*2);
//                audioCourseDataEntity.setCourseStudy(i*60*10);
//                audioCourseDataEntity.setCourseTitle("一首美妙的音乐"+i);
//                audioCourseDataEntity.setCourseType("audio");
//                mAudioCourseDataEntities.add(audioCourseDataEntity);
//            }
//        }else {
//            for (int i = 2; i <=4; i++) {
//                CourseAudioEntity.AudioCourseDataEntity audioCourseDataEntity
//                        = new CourseAudioEntity.AudioCourseDataEntity();
//                audioCourseDataEntity.setCourseID(i);
//                audioCourseDataEntity.setCourseDay(UtilCourseDay.getToday());
//                audioCourseDataEntity.setCourseLength(i*60*10*2);
//                audioCourseDataEntity.setCourseStudy(i*60*10);
//                audioCourseDataEntity.setCourseTitle("一首美妙的音乐"+i);
//                audioCourseDataEntity.setCourseType("audio");
//                mAudioCourseDataEntities.add(audioCourseDataEntity);
//            }
//            for (int i = 5; i <=8; i++) {
//                CourseAudioEntity.AudioCourseDataEntity audioCourseDataEntity
//                        = new CourseAudioEntity.AudioCourseDataEntity();
//                audioCourseDataEntity.setCourseID(i);
//                audioCourseDataEntity.setCourseDay(UtilCourseDay.getYesterday());
//                audioCourseDataEntity.setCourseLength(i*60*10*2);
//                audioCourseDataEntity.setCourseStudy(i*60*10);
//                audioCourseDataEntity.setCourseTitle("一首美妙的音乐"+i);
//                audioCourseDataEntity.setCourseType("audio");
//                mAudioCourseDataEntities.add(audioCourseDataEntity);
//            }
//            for (int i = 9; i <=12; i++) {
//                CourseAudioEntity.AudioCourseDataEntity audioCourseDataEntity
//                        = new CourseAudioEntity.AudioCourseDataEntity();
//                audioCourseDataEntity.setCourseID(i);
//                audioCourseDataEntity.setCourseDay(UtilCourseDay.getBeforeYesterday());
//                audioCourseDataEntity.setCourseLength(i*60*10*2);
//                audioCourseDataEntity.setCourseStudy(i*60*10);
//                audioCourseDataEntity.setCourseTitle("一首美妙的音乐"+i);
//                audioCourseDataEntity.setCourseType("audio");
//                mAudioCourseDataEntities.add(audioCourseDataEntity);
//            }
//        }
//     
//    
//        reSortCourseList();
//        mIsFirstRequest=false;
//        mPbLoadingCourseList.setVisibility(View.GONE);
//        mPbLoadingCourseList.stopSpinning();
//        if (mItemLvAudioCourseAdapter==null) {
//            mItemLvAudioCourseAdapter = new ItemLvAudioCourseAdapter(mSelf,mCourseAudioListEntities);
//            mSwipeTarget.setAdapter(mItemLvAudioCourseAdapter);
//        }
//     
//    }

//    private void reSortCourseList(){
//        if (mCourseAudioListEntities==null) {
//            mCourseAudioListEntities=new ArrayList<>();
//        }
//        String timeSYG="";
//        for (CourseAudioEntity.AudioCourseDataEntity audioCourseDataEntity : mAudioCourseDataEntities) {
//            String timeZYG=audioCourseDataEntity.getCourseDay();
//            
//            if (!timeSYG.equals(timeZYG)){
//                CourseAudioListEntity courseAudioListEntityTime=new CourseAudioListEntity();
//                courseAudioListEntityTime.setTime(timeZYG);
//                mCourseAudioListEntities.add(courseAudioListEntityTime);
//                timeSYG=timeZYG;
//            }
//    
//            CourseAudioListEntity courseAudioListEntity=new CourseAudioListEntity();
//            courseAudioListEntity.setCourseAudioEntity(audioCourseDataEntity);
//            mCourseAudioListEntities.add(courseAudioListEntity);
//            
//        }
//    }
    
    @Override
    public void onLoadMore() {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                doNetLoadMore();
//            }
//        },2500);
        if (mSwipeTarget.getCount() >= mCourseCount) {
            Toast.makeText(mSelf, "已全部加载完毕", Toast.LENGTH_SHORT).show();
            mLayoutRefresh.setLoadingMore(false);
            return;
        }
        doNetLoadMore();
    }
    
    
    @Override
    public void onRefresh() {
        typeNet = TYPE_REFRESH;
        mNetForJson.addParam("uid", SharePLogin.getUid());
        mNetForJson.addParam("startpos", 0);
        mNetForJson.addParam("count", 10);
        mNetForJson.excute();
    }
    
    private void doNetRfresh() {
//        tempData();
        mLayoutRefresh.setRefreshing(false);
        
    }
    
    private void doNetLoadMore() {
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
