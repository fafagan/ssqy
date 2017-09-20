package com.medicine.ssqy.ssqy.ui.fragment.jbgl;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.sj.mylibrary.net.NetCallback;
import com.example.sj.mylibrary.net.NetForJson;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseFragment;
import com.medicine.ssqy.ssqy.common.URLConstant;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.entity.JBGLListEntity;
import com.medicine.ssqy.ssqy.ui.adapter.jbgl.JBGLBaseAdapter;
import com.medicine.ssqy.ssqy.ui.dialog.DigJBGLBase;
import com.medicine.ssqy.ssqy.ui.dialog.DigSSJL;
import com.medicine.ssqy.ssqy.ui.dialog.DigTZJC;
import com.medicine.ssqy.ssqy.ui.dialog.DigXTJC;
import com.medicine.ssqy.ssqy.ui.dialog.DigXYJC;
import com.medicine.ssqy.ssqy.ui.dialog.DigYDJL;
import com.medicine.ssqy.ssqy.ui.dialog.DigYYJL;

import org.xutils.common.util.DensityUtil;

import java.util.List;

/**
 * Created by Administrator on 2017-09-19.
 */

public abstract class JBGLBaseFragment extends KBaseFragment implements AdapterView.OnItemClickListener, OnLoadMoreListener, OnRefreshListener {
    //1.渲染LV refresh----
    //2.实现刷新，加载更多接口
    //3.条目点击---
    //4.刷新和加载更多方法
    //5.设置type参数---
    //6.设置适配器---
    //7.设置背景图----
    //8.发起网络请求--
    private SwipeToLoadLayout mLayoutRefresh;
    private SwipeMenuListView mSwipeTarget;
    private RelativeLayout mRootLayout;
    private int mRequestType=0;
    private JBGLBaseAdapter mJBGLBaseAdapter;
    private Dialog mDialogModify;
    
    public static final int TYPE_XT=0;//0血糖
    public static final int TYPE_XY=1;//1血压
    public static final int TYPE_TZ=2;//2体重
    public static final int TYPE_YY=3;//3用药
    public static final int TYPE_SS=4;//4膳食
    public static final int TYPE_YD=5;//5运动
    private NetForJson mNetForJson;
    private NetForJson mNetForJsonDel;
    private int typeNet;
    
    public abstract JBGLBaseAdapter setJBGLBaseAdapter();
    public abstract int setRequestType();
    public abstract DigJBGLBase setDialogModify();
    
    private static final int TYPE_REFRESH=110;
    private static final int TYPE_LOADMORE=120;
    private int mCourseCount;
    private boolean mFirst=true;
    private TextView mTvNoneJl;
    
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    typeNet=TYPE_REFRESH;
                    mNetForJson.addParam("uid", SharePLogin.getUid());
                    mNetForJson.addParam("startpos","0");
                    mNetForJson.addParam("count",20);
                    mNetForJson.addParam("recordtype",mRequestType);
                    mNetForJson.addParam("order","desc");
                    break;
                case 2:
                  
                    if (mSwipeTarget.getCount() >= mCourseCount) {
                        Toast.makeText(mActivity, "已全部加载完毕", Toast.LENGTH_SHORT).show();
                        mLayoutRefresh.setLoadingMore(false);
                        return;
                    }
                    typeNet=TYPE_LOADMORE;
                    mNetForJson.addParam("uid", SharePLogin.getUid());
                    mNetForJson.addParam("startpos",mSwipeTarget.getChildCount());
                    mNetForJson.addParam("count",20);
                    mNetForJson.addParam("recordtype",mRequestType);
                    mNetForJson.addParam("order","desc");
                    break;
                default:
                    break;
            }
            mNetForJson.excute();
        }
    };
    public List<JBGLListEntity.RecordHistoryEntity> mRecordHistoryEntities;
    @Override
    public void initViews() {
        mRootLayout= (RelativeLayout) findViewById(R.id.id_jbgl_root);
        mLayoutRefresh = (SwipeToLoadLayout) findViewById(R.id.layout_refresh);
        mSwipeTarget = (SwipeMenuListView) findViewById(R.id.swipe_target);
        mRootLayout.setBackgroundResource(R.drawable.bg_util_frag);
//        mSwipeTarget.setOnItemClickListener(this);
        mLayoutRefresh.setOnRefreshListener(this);
        mLayoutRefresh.setOnLoadMoreListener(this);
    
        mTvNoneJl = (TextView) findViewById(R.id.tv_none_jl);
        SwipeMenuCreator creator = new SwipeMenuCreator() {
        
            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        mActivity);
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(DensityUtil.dip2px(90));
                // set item title
                openItem.setTitle("修改记录");
                // set item title fontsize
                openItem.setTitleSize(14);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);
            
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        mActivity);
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(DensityUtil.dip2px(90));
                // set a icon
                deleteItem.setTitle("删除记录");
                deleteItem.setTitleSize(14);
                // set item title font color
                deleteItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

// set creator
        mSwipeTarget.setMenuCreator(creator);
        mSwipeTarget.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
                        if (mDialogModify==null) {
                            switch (mRequestType){
                                case TYPE_XT:
                                    DigXTJC digXTJC=new DigXTJC(mActivity);
                                    mDialogModify=digXTJC;
                                    digXTJC.setUpdateMode(mRecordHistoryEntities.get(position).getRecordID(),mLayoutRefresh);
                                    break;
                                case TYPE_XY:
                                    DigXYJC digXYJC=new DigXYJC(mActivity);
                                    mDialogModify=digXYJC;
                                    digXYJC.setUpdateMode(mRecordHistoryEntities.get(position).getRecordID(),mLayoutRefresh);
                                    break;
                                case TYPE_TZ:
                                    DigTZJC digTZJC=new DigTZJC(mActivity);
                                    mDialogModify=digTZJC;
                                    digTZJC.setUpdateMode(mRecordHistoryEntities.get(position).getRecordID(),mLayoutRefresh);
                                    break;
                                case TYPE_YY:
                                    DigYYJL digYYJL=new DigYYJL(mActivity);
                                    mDialogModify=digYYJL;
                                    digYYJL.setUpdateMode(mRecordHistoryEntities.get(position).getRecordID(),mLayoutRefresh);
                                    break;
                                case TYPE_SS:
                                    DigSSJL digSSJL=new DigSSJL(mActivity);
                                    mDialogModify=digSSJL;
                                    digSSJL.setUpdateMode(mRecordHistoryEntities.get(position).getRecordID(),mLayoutRefresh);
                                    break;
                                case TYPE_YD:
                                    DigYDJL digYDJL=new DigYDJL(mActivity);
                                    mDialogModify=digYDJL;
                                    digYDJL.setUpdateMode(mRecordHistoryEntities.get(position).getRecordID(),mLayoutRefresh);
                                    break;
                            }
                        }else {
                            switch (mRequestType){
                                case TYPE_XT:
                                    DigXTJC digXTJC= (DigXTJC) mDialogModify;
                                    digXTJC.setUpdateMode(mRecordHistoryEntities.get(position).getRecordID(),mLayoutRefresh);
                                    break;
                                case TYPE_XY:
                                    DigXYJC digXYJC= (DigXYJC) mDialogModify;
                                    digXYJC.setUpdateMode(mRecordHistoryEntities.get(position).getRecordID(),mLayoutRefresh);
                                    break;
                                case TYPE_TZ:
                                    DigTZJC digTZJC= (DigTZJC) mDialogModify;
                                    digTZJC.setUpdateMode(mRecordHistoryEntities.get(position).getRecordID(),mLayoutRefresh);
                                    break;
                                case TYPE_YY:
                                    DigYYJL digYYJL= (DigYYJL) mDialogModify;
                                    digYYJL.setUpdateMode(mRecordHistoryEntities.get(position).getRecordID(),mLayoutRefresh);
                                    break;
                                case TYPE_SS:
                                    DigSSJL digSSJL= (DigSSJL) mDialogModify;
                                    digSSJL.setUpdateMode(mRecordHistoryEntities.get(position).getRecordID(),mLayoutRefresh);
                                    break;
                                case TYPE_YD:
                                    DigYDJL digYDJL= (DigYDJL) mDialogModify;
                                    digYDJL.setUpdateMode(mRecordHistoryEntities.get(position).getRecordID(),mLayoutRefresh);
                                    break;
                            }
                        }
                        if (mDialogModify != null) {
                            mDialogModify.show();
                        }
                     
                        break;
                    case 1:
                        // delete
                        Toast.makeText(mActivity, "正在删除记录，请稍等", Toast.LENGTH_SHORT).show();
                        mNetForJsonDel.addParam("uid", SharePLogin.getUid());
//                        Toast.makeText(mActivity, "position"+position+"  "+index, Toast.LENGTH_SHORT).show();
//                        Toast.makeText(mActivity, mRecordHistoryEntities.get(position).getRecordID(), Toast.LENGTH_SHORT).show();
                        mNetForJsonDel.addParam("recordID",mRecordHistoryEntities.get(position).getRecordID());
                        mNetForJsonDel.addParam("recordtype",mRequestType);
                        mNetForJsonDel.excute();
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
        mSwipeTarget.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
    }
    
    @Override
    public boolean isUseTitle() {
        return false;
    }
    
    @Override
    public void initDatas() {
        mJBGLBaseAdapter=setJBGLBaseAdapter();
        mSwipeTarget.setAdapter(mJBGLBaseAdapter);
        mRequestType=setRequestType();
//        mDialogModify=setDialogModify();
        mNetForJson = new NetForJson(URLConstant.JBGL_LIST_URL, new NetCallback<JBGLListEntity>() {
            @Override
            public void onSuccess(JBGLListEntity entity) {
                if (entity.isState()) {
                    mCourseCount = entity.getDataCount();
                
                    if (typeNet == TYPE_REFRESH) {
                        mRecordHistoryEntities = entity.getRecordHistory();
                        if (mRecordHistoryEntities==null||mRecordHistoryEntities.size()==0) {
                            mTvNoneJl.setVisibility(View.VISIBLE);
                        }else {
                            mTvNoneJl.setVisibility(View.GONE);
                        }
                        if (mJBGLBaseAdapter == null) {
                            return;
                        } else {
                            mJBGLBaseAdapter.setEntities(mRecordHistoryEntities);
                        }
                    
                    
                    } else if (typeNet == TYPE_LOADMORE) {
                        mRecordHistoryEntities.addAll(entity.getRecordHistory());
                        if (mJBGLBaseAdapter == null) {
                            return;
                        } else {
                            mJBGLBaseAdapter.addEntities(entity.getRecordHistory());
                        }
                    }
                } else {
                    Toast.makeText(mActivity, "加载错误，请检查您的网络状态！", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onError() {
                Toast.makeText(mActivity, "加载错误，请检查您的网络状态！", Toast.LENGTH_SHORT).show();
            
            }
        
            @Override
            public void onFinish() {
                typeNet = TYPE_REFRESH;
                mLayoutRefresh.setLoadingMore(false);
                mLayoutRefresh.setRefreshing(false);
            }
        },true);
        mNetForJsonDel = new NetForJson(URLConstant.JBGL_DEL_URL, new NetCallback<JBGLListEntity>() {
            @Override
            public void onSuccess(JBGLListEntity entity) {
                if (entity.isState()) {
                    Toast.makeText(mActivity, "删除成功！", Toast.LENGTH_SHORT).show();
                    mLayoutRefresh.setRefreshing(true);
                } else {
                    Toast.makeText(mActivity, "删除错误，请检查您的网络状态！", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onError() {
                Toast.makeText(mActivity, "删除错误，请检查您的网络状态！", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFinish() {
//                typeNet = TYPE_REFRESH;
//                mLayoutRefresh.setLoadingMore(false);
//                mLayoutRefresh.setRefreshing(false);
            }
        },true);
        
        
    }
    
    @Override
    public void onResume() {
        super.onResume();
        mLayoutRefresh.setRefreshing(true);
    }
    
    @Override
    public void onPause() {
        super.onPause();
//        if (mNetForJson != null) {
//            mNetForJson.cancel();
//        }

    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mDialogModify != null) {
            mDialogModify.show();
        }
    }
    
    @Override
    public void onLoadMore() {
        if (mFirst) {
            mFirst=false;
            Toast.makeText(mActivity, "正在加载，请稍等", Toast.LENGTH_SHORT).show();
        }
   
        mHandler.sendEmptyMessageDelayed(2,600);
    }
    
    @Override
    public void onRefresh() {
        if (mFirst) {
            mFirst=false;
            Toast.makeText(mActivity, "正在加载，请稍等", Toast.LENGTH_SHORT).show();
        }
        mHandler.sendEmptyMessageDelayed(1,600);
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(1);
        mHandler.removeMessages(2);
        mNetForJson.cancel();
    }
}
