package com.medicine.ssqy.ssqy.ui.fragment.courseHistory;

import android.os.Handler;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.sj.mylibrary.net.NetCallback;
import com.example.sj.mylibrary.net.NetForJson;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseFragment;
import com.medicine.ssqy.ssqy.common.URLConstant;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.entity.CourseHistory;
import com.medicine.ssqy.ssqy.ui.adapter.ItemLvHistoryDayAdapter;
import com.medicine.ssqy.ssqy.util.UtilTimeConvertS;

import java.util.List;

public class HistoryAllFragment extends KBaseFragment implements OnRefreshListener, OnLoadMoreListener {
    private SwipeToLoadLayout mSwipeToLoadLayout;
    private ListView mSwipeTarget;
    
    //-----------------------
    private TextView mTvDataHour;
    private TextView mTvDataMinutes;
    private NetForJson mNetForJson;
    private int mTotalTime=0;
    private ItemLvHistoryDayAdapter mItemLvHistoryDayAdapter;
    private List<CourseHistory.CourseDataEntity> mCourseData;
    private  void  initNet(){
        mNetForJson=new NetForJson(URLConstant.COURSE_HISTORY_URL, new NetCallback<CourseHistory>() {
            
            
            @Override
            public void onSuccess(CourseHistory entity) {
                mCourseData = entity.getCourseData();
                if (mCourseData != null&&mCourseData.size()>0) {
                    for (CourseHistory.CourseDataEntity courseDataEntity : mCourseData) {
                        calculateTotalTime(courseDataEntity);
                    }
                    if (mItemLvHistoryDayAdapter==null) {
                        mItemLvHistoryDayAdapter = new ItemLvHistoryDayAdapter(mActivity, mCourseData);
                        mSwipeTarget.setAdapter(mItemLvHistoryDayAdapter);
                    }else {
                        mItemLvHistoryDayAdapter.setEntities(mCourseData);
                    }
                    
                    
                    
                }
            }
            
            @Override
            public void onError() {
                Toast.makeText(mActivity, "网络异常！", Toast.LENGTH_SHORT).show();
                
            }
            
            @Override
            public void onFinish() {
                mSwipeToLoadLayout.setRefreshing(false);
                mSwipeToLoadLayout.setLoadingMore(false);
            }
        });
        
        mNetForJson.addParam("uid", SharePLogin.getUid());
        mNetForJson.addParam("startpos", "0");
        mNetForJson.addParam("count", "100");
        mNetForJson.addParam("order", "asc");
        mNetForJson.addParam("type", "all");
        mSwipeToLoadLayout.setRefreshing(true);
    }
    
    private void calculateTotalTime(CourseHistory.CourseDataEntity courseDataEntity) {
        if ("pic".equalsIgnoreCase(courseDataEntity.getCourseType())) {
            mTotalTime+=1000*60*3;
        }else {
            mTotalTime+=courseDataEntity.getCourseStudy();
        }
        mTvDataHour.setText(UtilTimeConvertS.getHour(mTotalTime));
        mTvDataMinutes.setText(UtilTimeConvertS.getMins(mTotalTime));
        
    }
    @Override
    public void onRefresh() {
        mTotalTime=0;
        mNetForJson.excute();
    }
    
    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(mActivity, "已全部加载完毕！", Toast.LENGTH_SHORT).show();
                mSwipeToLoadLayout.setLoadingMore(false);
                
            }
        },2500);
    }
    
    @Override
    public void initDatas() {
        initNet();
    }
    //-------------------------------------
    @Override
    public int setRootView() {
        return R.layout.fragment_history_all;
    }
    
    @Override
    public boolean isUseTitle() {
        return false;
    }
    
    @Override
    public void initViews() {
        mTvDataHour = (TextView) findViewById(R.id.tv_data_hour);
        mTvDataMinutes = (TextView) findViewById(R.id.tv_data_minutes);
        mSwipeToLoadLayout = (SwipeToLoadLayout) findViewById(R.id.swipeToLoadLayout);
        mSwipeTarget = (ListView) findViewById(R.id.swipe_target);
        mSwipeToLoadLayout.setOnRefreshListener(this);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);
//        mSwipeTarget.setAdapter(new ItemLvHistoryDayAdapter(mActivity, CourseData.getDatasToday()));
    }
    
}


