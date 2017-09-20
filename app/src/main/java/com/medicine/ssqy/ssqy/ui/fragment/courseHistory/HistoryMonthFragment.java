package com.medicine.ssqy.ssqy.ui.fragment.courseHistory;

import android.os.Handler;
import android.widget.ListView;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseFragment;
import com.medicine.ssqy.ssqy.test.CourseData;
import com.medicine.ssqy.ssqy.ui.adapter.ItemLvHistoryDayAdapter;

public class HistoryMonthFragment extends KBaseFragment implements OnRefreshListener, OnLoadMoreListener {
    private TextView mTvDaysMonth;
    private SwipeToLoadLayout mSwipeToLoadLayout;
    private ListView mSwipeTarget;
    
    
    @Override
    public int setRootView() {
        return R.layout.fragment_history_month;
    }
    
    @Override
    public boolean isUseTitle() {
        return false;
    }
    
    @Override
    public void initViews() {
    
        mTvDaysMonth = (TextView) findViewById(R.id.tv_days_month);
        mSwipeToLoadLayout = (SwipeToLoadLayout) findViewById(R.id.swipeToLoadLayout);
        mSwipeTarget = (ListView) findViewById(R.id.swipe_target);
    
        mSwipeToLoadLayout.setOnRefreshListener(this);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);
        mSwipeTarget.setAdapter(new ItemLvHistoryDayAdapter(mActivity, CourseData.getDatasToday()));
    }
    
    @Override
    public void initDatas() {
        
    }
    
    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeToLoadLayout.setRefreshing(false);
                
            }
        },2500);
    }
    
    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeToLoadLayout.setLoadingMore(false);
                
            }
        },2500);
    }
}
