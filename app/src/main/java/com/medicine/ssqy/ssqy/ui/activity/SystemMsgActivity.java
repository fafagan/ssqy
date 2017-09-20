package com.medicine.ssqy.ssqy.ui.activity;

import android.view.View;
import android.widget.LinearLayout;
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
import com.medicine.ssqy.ssqy.entity.SystemMsgEntity;
import com.medicine.ssqy.ssqy.entity.SystemMsgListEntity;
import com.medicine.ssqy.ssqy.ui.adapter.ItemLvSystemMsgDataAdapter;
import com.medicine.ssqy.ssqy.util.TimeFormatUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class SystemMsgActivity extends KBaseActivity implements OnRefreshListener, OnLoadMoreListener {
//    SYSTEM_MSG_URL
    
    private SwipeToLoadLayout mLayoutRefresh;
    private ListView mSwipeTarget;
    private LinearLayout mLayoutNoneSystemMsg;
    private int mPosStart = 0;
    public static final int TYPE_REFRESH = 110;
    public static final int TYPE_LOADMORE = 120;
    private int typeNet = TYPE_REFRESH;
    private int mCourseCount;
    private NetForJson mNetForJson;
    
    private ItemLvSystemMsgDataAdapter mItemLvSystemMsgDataAdapter;
    
    @Override
    public int setRootView() {
        return R.layout.activity_system_msg;
    }
    
    @Override
    public void initViews() {
        setTitleCenter("我的养生宣教");
        mLayoutRefresh = (SwipeToLoadLayout) findViewById(R.id.layout_refresh);
        mSwipeTarget = (ListView) findViewById(R.id.swipe_target);
        mLayoutNoneSystemMsg = (LinearLayout) findViewById(R.id.layout_none_system_msg);
        mLayoutRefresh.setOnRefreshListener(this);
        mLayoutRefresh.setOnLoadMoreListener(this);
        mLayoutRefresh.setSwipeStyle(SwipeToLoadLayout.STYLE.ABOVE);
        
    }
    
    @Override
    public void initDatas() {
        mNetForJson = new NetForJson(URLConstant.SYSTEM_MSG_URL, new NetCallback<List<SystemMsgEntity>>() {
            
            
            @Override
            public void onSuccess(List<SystemMsgEntity> systemMsgEntities) {
                if (systemMsgEntities.size() == 0) {
                    mLayoutRefresh.setVisibility(View.GONE);
                    mLayoutNoneSystemMsg.setVisibility(View.VISIBLE);
                    return;
                }
                mLayoutRefresh.setVisibility(View.VISIBLE);
                mLayoutNoneSystemMsg.setVisibility(View.GONE);
                Toast.makeText(mSelf, "加载成功！", Toast.LENGTH_SHORT).show();
                
                List<SystemMsgListEntity> systemMsgListEntities = convertDatas(systemMsgEntities);
                Logger.e(systemMsgEntities.size() + "  " + systemMsgListEntities.size());
                if (typeNet == TYPE_REFRESH) {
                    if (mItemLvSystemMsgDataAdapter == null) {
                        mItemLvSystemMsgDataAdapter = new ItemLvSystemMsgDataAdapter(mSelf, systemMsgListEntities);
                        mSwipeTarget.setAdapter(mItemLvSystemMsgDataAdapter);
                    } else {
                        mItemLvSystemMsgDataAdapter.setEntities(systemMsgListEntities);
                    }
                } else if (typeNet == TYPE_LOADMORE) {
                    if (mItemLvSystemMsgDataAdapter == null) {
                        mItemLvSystemMsgDataAdapter = new ItemLvSystemMsgDataAdapter(mSelf, systemMsgListEntities);
                        mSwipeTarget.setAdapter(mItemLvSystemMsgDataAdapter);
                    } else {
                        mItemLvSystemMsgDataAdapter.addEntities(systemMsgListEntities);
                    }
                    
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
                mLayoutRefresh.setRefreshing(false);
            }
        }, true);
        mLayoutRefresh.setRefreshing(true);
    }
    
    
    public List<SystemMsgListEntity> convertDatas(List<SystemMsgEntity> systemMsgEntities) {
        List<SystemMsgListEntity> systemMsgListEntities = new ArrayList<>();
        
        String timeSYG = "";
        SystemMsgListEntity listEntity = null;
        int index = 1;
        for (int i = 0; i < systemMsgEntities.size(); i++) {
            SystemMsgEntity systemMsgEntity = systemMsgEntities.get(i);
            String timeZYG = TimeFormatUtil.formatLongToNYR(systemMsgEntity.getDate());
            
            if (!timeSYG.equals(timeZYG)) {
                //发现了新时间
                listEntity = new SystemMsgListEntity();
                listEntity.setTime(timeZYG);
                systemMsgListEntities.add(listEntity);
                index = 1;
            }
            
            listEntity = new SystemMsgListEntity();
            listEntity.setMsgEntity(systemMsgEntity);
            listEntity.setIndex(index++);
            systemMsgListEntities.add(listEntity);
            
            
            timeSYG = timeZYG;
        }
        return systemMsgListEntities;
    }
    
    @Override
    protected void onRestart() {
        super.onRestart();
        mLayoutRefresh.setRefreshing(true);
    }
    
    @Override
    public void onRefresh() {
        typeNet = TYPE_REFRESH;
        mNetForJson.addParam("uid", SharePLogin.getUid());
        mNetForJson.addParam("startpos", 0);
        mNetForJson.addParam("count", 10);
        mNetForJson.excute();
    }
    
    @Override
    public void onLoadMore() {
        if (mSwipeTarget.getCount() >= 50) {
            Toast.makeText(mSelf, "只为您保留最近50条宣教内容呦！", Toast.LENGTH_SHORT).show();
            mLayoutRefresh.setLoadingMore(false);
            return;
        }
        doNetLoadMore();
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
