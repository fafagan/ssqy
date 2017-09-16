package com.medicine.ssqy.ssqy.ui.activity;

import com.example.sj.mylibrary.net.NetForJson;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.common.URLConstant;

public class HomeUtilActivity extends KBaseActivity {
    private  int mItemIndex ;
    private String mUrlList;
    private NetForJson mNetForJson;
    @Override
    public int setRootView() {
        return R.layout.activity_home_util;
    }
    
    @Override
    public void initViews() {
        mItemIndex=getIntent().getIntExtra("item", 0);
        initFlags();
    }
    
    private void initFlags() {
    
        switch (mItemIndex) {
            case 0:
                setTitleCenter("食物热量速查");
                mUrlList= URLConstant.UTIL_FOODHOT_URL;
                break;
            case 1:
                setTitleCenter("食物嘌呤速查");
                mUrlList= URLConstant.UTIL_FOODPL_URL;
                break;
            case 2:
                setTitleCenter("食物含钙量速查");
                mUrlList= URLConstant.UTIL_FOODGAI_URL;
                break;
            case 3:
                setTitleCenter("食物运动消耗速查");
                mUrlList= URLConstant.UTIL_FOODSPORT_URL;
                break;
        }
    }
    
    @Override
    public void initDatas() {
//        mNetForJson=new NetForJson(mUrlList,);
        mNetForJson.excute();
    }
}
