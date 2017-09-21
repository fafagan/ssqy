package com.medicine.ssqy.ssqy.ui.activity;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sj.mylibrary.net.NetCallback;
import com.example.sj.mylibrary.net.NetForJson;
import com.example.sj.mylibrary.util.StringEmptyUtil;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.common.URLConstant;
import com.medicine.ssqy.ssqy.entity.food.FoodGaiEntity;
import com.medicine.ssqy.ssqy.entity.food.FoodHotEntity;
import com.medicine.ssqy.ssqy.entity.food.FoodPLEntity;
import com.medicine.ssqy.ssqy.entity.food.FoodQueryData;
import com.medicine.ssqy.ssqy.entity.food.SportEntity;
import com.medicine.ssqy.ssqy.util.KeyboardUtils;
import com.medicine.ssqy.ssqy.util.UtilGetQuerySuggestion;
import com.medicine.ssqy.ssqycom.medicine.ssqy.ssqy.ui.adapter.ItemLvUtilHomeAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends KBaseActivity implements View.OnClickListener {
    private LinearLayout mLayoutSearchActivity;
    private EditText mEdt;
    private Button mBtDoSearch;
    private Button mItemCy1;
    private Button mItemCy2;
    private Button mItemCy3;
    private Button mItemCy4;
    private Button mItemCy5;
    private Button mItemCy6;
    private Button mItemCy7;
    private Button mItemCy8;
    
    private int mItemIndex;
    private ListView mLvResult;
    private NetForJson mNetForJson;
    private String mUrlList;
    private List<FoodQueryData> mFoodQueryDatas = new ArrayList<>();
    private List<String> mDatas;
    private List<TextView> mTextViewsSugg=new ArrayList<>();
    @Override
    public int setRootView() {
        return R.layout.activity_search;
    }
    
    @Override
    public void initViews() {
        mLvResult = (ListView) findViewById(R.id.lv_result);
        mItemIndex = getIntent().getIntExtra("itemIndex", 0);
//        setTitleCenter("搜 索");
        mLayoutSearchActivity = (LinearLayout) findViewById(R.id.layout_search_activity);
        mEdt = (EditText) findViewById(R.id.edt);
        mBtDoSearch = (Button) findViewById(R.id.bt_doSearch);
        mItemCy1 = (Button) findViewById(R.id.item_cy_1);
        mItemCy2 = (Button) findViewById(R.id.item_cy_2);
        mItemCy3 = (Button) findViewById(R.id.item_cy_3);
        mItemCy4 = (Button) findViewById(R.id.item_cy_4);
        mItemCy5 = (Button) findViewById(R.id.item_cy_5);
        mItemCy6 = (Button) findViewById(R.id.item_cy_6);
        mItemCy7 = (Button) findViewById(R.id.item_cy_7);
        mItemCy8 = (Button) findViewById(R.id.item_cy_8);
        mEdt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Toast.makeText(mSelf, "搜索成功", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            }
        });
    
        mBtDoSearch.setOnClickListener(this);
    
       
    }
    
    private void initSTV() {
        mTextViewsSugg.add(mItemCy1);
        mTextViewsSugg.add(mItemCy2);
        mTextViewsSugg.add(mItemCy3);
        mTextViewsSugg.add(mItemCy4);
        mTextViewsSugg.add(mItemCy5);
        mTextViewsSugg.add(mItemCy6);
        mTextViewsSugg.add(mItemCy7);
        mTextViewsSugg.add(mItemCy8);
    
        for (int i = 0; i < mTextViewsSugg.size(); i++) {
            mTextViewsSugg.get(i).setOnClickListener(this);
            mTextViewsSugg.get(i).setTag(mDatas.get(i));
            mTextViewsSugg.get(i).setText(mDatas.get(i));
        }
    }
    
    @Override
    public void initDatas() {
        switch (mItemIndex) {
            case 0:
                setTitleCenter("食物热量搜索");
                mUrlList = URLConstant.UTIL_FOODHOT_QUERY_URL;
                mDatas= UtilGetQuerySuggestion.getFoodHotDatas();
                mNetForJson = new NetForJson(mUrlList, new NetCallback<FoodHotEntity>() {
                    @Override
                    public void onSuccess(FoodHotEntity entity) {
            
                        if (entity.getCount() > 0) {
                            Toast.makeText(mSelf, "搜索完毕！", Toast.LENGTH_SHORT).show();
                            List<FoodHotEntity.FoodsDataEntity> foodsData = entity.getFoodsData();
                            for (FoodHotEntity.FoodsDataEntity foodsDataEntity : foodsData) {
                                mFoodQueryDatas.add(new FoodQueryData(foodsDataEntity.getFoodPic(), foodsDataEntity.getFoodTitle(), foodsDataEntity.getFoodhot()+" 千卡/克"));
                            }
                
                            showList(mFoodQueryDatas);
                        }else {
                            Toast.makeText(mSelf, "未检索到相关食物数据！", Toast.LENGTH_SHORT).show();
                        }
            
                    }
        
                    @Override
                    public void onError() {
                        showError();
                    }
        
                    @Override
                    public void onFinish() {
                        KeyboardUtils.hideSoftInput(mSelf);
                    }
                },true);
                break;
            case 1:
                setTitleCenter("食物嘌呤搜索");
                mUrlList = URLConstant.UTIL_FOODPL_QUERY_URL;
                mDatas= UtilGetQuerySuggestion.getFoodHotDatas();
                mNetForJson = new NetForJson(mUrlList, new NetCallback<FoodPLEntity>() {
                    @Override
                    public void onSuccess(FoodPLEntity entity) {
            
                        if (entity.getCount() > 0) {
                            Toast.makeText(mSelf, "搜索完毕！", Toast.LENGTH_SHORT).show();
                            List<FoodPLEntity.FoodsDataEntity> foodsData = entity.getFoodsData();
                            for (FoodPLEntity.FoodsDataEntity foodsDataEntity : foodsData) {
                                mFoodQueryDatas.add(new FoodQueryData(foodsDataEntity.getFoodPic(), foodsDataEntity.getFoodTitle(), foodsDataEntity.getFoodPiaoling()+" mg/100g"));
                            }
                
                            showList(mFoodQueryDatas);
                        }else {
                            Toast.makeText(mSelf, "未检索到相关食物数据！", Toast.LENGTH_SHORT).show();
                        }
            
                    }
        
                    @Override
                    public void onError() {
                        showError();
                    }
        
                    @Override
                    public void onFinish() {
                        KeyboardUtils.hideSoftInput(mSelf);
                    }
                },true);
                break;
            case 2:
                setTitleCenter("食物含钙量搜索");
                mUrlList = URLConstant.UTIL_FOODGAI_QUERY_URL;
                mDatas= UtilGetQuerySuggestion.getFoodHotDatas();
                mNetForJson = new NetForJson(mUrlList, new NetCallback<FoodGaiEntity>() {
                    @Override
                    public void onSuccess(FoodGaiEntity entity) {
                        Toast.makeText(mSelf, "搜索完毕！", Toast.LENGTH_SHORT).show();
                        if (entity.getCount() > 0) {
                            Toast.makeText(mSelf, "搜索完毕！", Toast.LENGTH_SHORT).show();
                            List<FoodGaiEntity.FoodsDataEntity> foodsData = entity.getFoodsData();
                            for (FoodGaiEntity.FoodsDataEntity foodsDataEntity : foodsData) {
                                mFoodQueryDatas.add(new FoodQueryData(foodsDataEntity.getFoodPic(), foodsDataEntity.getFoodTitle(), foodsDataEntity.getFoodGai()+" 大卡"));
                            }
                
                            showList(mFoodQueryDatas);
                        }else {
                            Toast.makeText(mSelf, "未检索到相关食物数据！", Toast.LENGTH_SHORT).show();
                        }
            
                    }
        
                    @Override
                    public void onError() {
                        showError();
                    }
        
                    @Override
                    public void onFinish() {
                        KeyboardUtils.hideSoftInput(mSelf);
                    }
                },true);
                break;
            case 3:
                setTitleCenter("运动消耗搜索");
                mUrlList = URLConstant.UTIL_FOODSPORT_QUERY_URL;
                mDatas= UtilGetQuerySuggestion.getSportDatas();
                mNetForJson = new NetForJson(mUrlList, new NetCallback<SportEntity>() {
                    @Override
                    public void onSuccess(SportEntity entity) {
            
                        if (entity.getCount() > 0) {
                            Toast.makeText(mSelf, "搜索完毕！", Toast.LENGTH_SHORT).show();
                            List<SportEntity.SportsDataEntity> foodsData = entity.getSportsData();
                            for (SportEntity.SportsDataEntity foodsDataEntity : foodsData) {
                                mFoodQueryDatas.add(new FoodQueryData(foodsDataEntity.getSportsPic(), foodsDataEntity.getSportsTitle(), foodsDataEntity.getSportsHot()));
                            }
                
                            showList(mFoodQueryDatas);
                        }else {
                            Toast.makeText(mSelf, "未检索到相关运动数据！", Toast.LENGTH_SHORT).show();
                        }
            
                    }
        
                    @Override
                    public void onError() {
                        showError();
                    }
        
                    @Override
                    public void onFinish() {
                        KeyboardUtils.hideSoftInput(mSelf);
                    }
                },true);
                break;
        }
    
        initSTV();
    }
    
    @Override
    public void onClick(View v) {
        String keyword=null;
        mFoodQueryDatas.clear();
        switch (v.getId()) {
            
            case R.id.bt_doSearch:
                keyword= mEdt.getText().toString().trim();
                if (StringEmptyUtil.isEmpty(keyword)) {
                    Toast.makeText(mSelf, "请输入搜索关键字！", Toast.LENGTH_SHORT).show();
                    return;
                }
             
                break;
            default:
                keyword=(String) v.getTag();
                break;
        }
    
        mNetForJson.addParam("keyword",keyword);
        mNetForJson.excute();
        Toast.makeText(mSelf, "为您搜索  "+keyword+"  的相关数据", Toast.LENGTH_SHORT).show();
    }
    private void showError() {
        Toast.makeText(mSelf, "网络错误，请退出重试！", Toast.LENGTH_SHORT).show();
    }
    
    private void showList(List<FoodQueryData> foodQueryDatas) {
        mLvResult.setAdapter(new ItemLvUtilHomeAdapter(mSelf,foodQueryDatas));
    }
}
