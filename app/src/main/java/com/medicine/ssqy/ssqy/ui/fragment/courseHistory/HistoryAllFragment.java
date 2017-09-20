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

public class HistoryAllFragment extends KBaseFragment implements OnRefreshListener, OnLoadMoreListener {
    private TextView mTvDaysAll;
    private SwipeToLoadLayout mSwipeToLoadLayout;
    private ListView mSwipeTarget;
    
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
        mTvDaysAll = (TextView) findViewById(R.id.tv_days_all);
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


