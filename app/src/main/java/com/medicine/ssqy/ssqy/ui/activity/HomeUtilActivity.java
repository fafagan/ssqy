package com.medicine.ssqy.ssqy.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sj.mylibrary.net.NetCallback;
import com.example.sj.mylibrary.net.NetForJson;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.common.URLConstant;
import com.medicine.ssqy.ssqy.entity.food.FoodGaiEntity;
import com.medicine.ssqy.ssqy.entity.food.FoodHotEntity;
import com.medicine.ssqy.ssqy.entity.food.FoodPLEntity;
import com.medicine.ssqy.ssqy.entity.food.FoodQueryData;
import com.medicine.ssqy.ssqy.entity.food.SportEntity;
import com.medicine.ssqy.ssqycom.medicine.ssqy.ssqy.ui.adapter.ItemLvUtilHomeAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeUtilActivity extends KBaseActivity {
    private int mItemIndex;
    private String mUrlList;
    private NetForJson mNetForJson;
    private List<FoodQueryData> mFoodQueryDatas = new ArrayList<>();
    private ListView mLvDataActivityUtil;
    
    private LinearLayout mLayoutSearchTemp;
    


    @Override
    public int setRootView() {
        return R.layout.activity_home_util;
    }
    
    @Override
    public void initViews() {
        mItemIndex = getIntent().getIntExtra("item", 0);
        mLvDataActivityUtil = (ListView) findViewById(R.id.lv_data_activity_util);
        mLayoutSearchTemp = (LinearLayout) findViewById(R.id.layout_search_temp);
        mLayoutSearchTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mSelf,SearchActivity.class);
                intent.putExtra("itemIndex",mItemIndex);
                startActivity(intent);
            }
        });
        initFlags();
    }
    
    private void initFlags() {
        
        switch (mItemIndex) {
            case 0:
                setTitleCenter("食物热量速查");
                mUrlList = URLConstant.UTIL_FOODHOT_URL;
                mNetForJson = new NetForJson(mUrlList, new NetCallback<FoodHotEntity>() {
                    @Override
                    public void onSuccess(FoodHotEntity entity) {
                        
                        if (entity.getCount() > 0) {
                            List<FoodHotEntity.FoodsDataEntity> foodsData = entity.getFoodsData();
                            for (FoodHotEntity.FoodsDataEntity foodsDataEntity : foodsData) {
                                mFoodQueryDatas.add(new FoodQueryData(foodsDataEntity.getFoodPic(), foodsDataEntity.getFoodTitle(), foodsDataEntity.getFoodhot()+" 千卡/克"));
                            }
                            
                            showList(mFoodQueryDatas);
                        }
                        
                    }
                    
                    @Override
                    public void onError() {
                        showError();
                    }
                    
                    @Override
                    public void onFinish() {
                        
                    }
                });
                break;
            case 1:
                setTitleCenter("食物嘌呤速查");
                mUrlList = URLConstant.UTIL_FOODPL_URL;
    
                mNetForJson = new NetForJson(mUrlList, new NetCallback<FoodPLEntity>() {
                    @Override
                    public void onSuccess(FoodPLEntity entity) {
            
                        if (entity.getCount() > 0) {
                            List<FoodPLEntity.FoodsDataEntity> foodsData = entity.getFoodsData();
                            for (FoodPLEntity.FoodsDataEntity foodsDataEntity : foodsData) {
                                mFoodQueryDatas.add(new FoodQueryData(foodsDataEntity.getFoodPic(), foodsDataEntity.getFoodTitle(), foodsDataEntity.getFoodPiaoling()+" mg/100g"));
                            }
                
                            showList(mFoodQueryDatas);
                        }
            
                    }
        
                    @Override
                    public void onError() {
                        showError();
                    }
        
                    @Override
                    public void onFinish() {
            
                    }
                });
                break;
            case 2:
                setTitleCenter("食物含钙量速查");
                mUrlList = URLConstant.UTIL_FOODGAI_URL;
                mNetForJson = new NetForJson(mUrlList, new NetCallback<FoodGaiEntity>() {
                    @Override
                    public void onSuccess(FoodGaiEntity entity) {
            
                        if (entity.getCount() > 0) {
                            List<FoodGaiEntity.FoodsDataEntity> foodsData = entity.getFoodsData();
                            for (FoodGaiEntity.FoodsDataEntity foodsDataEntity : foodsData) {
                                mFoodQueryDatas.add(new FoodQueryData(foodsDataEntity.getFoodPic(), foodsDataEntity.getFoodTitle(), foodsDataEntity.getFoodGai()+" 大卡"));
                            }
                
                            showList(mFoodQueryDatas);
                        }
            
                    }
        
                    @Override
                    public void onError() {
                        showError();
                    }
        
                    @Override
                    public void onFinish() {
            
                    }
                });
                break;
            case 3:
                setTitleCenter("食物运动消耗速查");
                mUrlList = URLConstant.UTIL_FOODSPORT_URL;
                mNetForJson = new NetForJson(mUrlList, new NetCallback<SportEntity>() {
                    @Override
                    public void onSuccess(SportEntity entity) {
            
                        if (entity.getCount() > 0) {
                            List<SportEntity.SportsDataEntity> foodsData = entity.getSportsData();
                            for (SportEntity.SportsDataEntity foodsDataEntity : foodsData) {
                                mFoodQueryDatas.add(new FoodQueryData(foodsDataEntity.getSportsPic(), foodsDataEntity.getSportsTitle(), foodsDataEntity.getSportsHot()));
                            }
                
                            showList(mFoodQueryDatas);
                        }
            
                    }
        
                    @Override
                    public void onError() {
                        showError();
                    }
        
                    @Override
                    public void onFinish() {
            
                    }
                });
                break;
        }
    }
    
    private void showList(List<FoodQueryData> foodQueryDatas) {
        mLvDataActivityUtil.setAdapter(new ItemLvUtilHomeAdapter(mSelf,foodQueryDatas));
    }
    
    private void showError() {
        Toast.makeText(mSelf, "网络错误，请退出重试！", Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void initDatas() {
        
        mNetForJson.excute();
    }
}
