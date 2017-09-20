package com.medicine.ssqy.ssqy.ui.activity;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;

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
    
    @Override
    public int setRootView() {
        return R.layout.activity_search;
    }
    
    @Override
    public void initViews() {
    
        mItemIndex = getIntent().getIntExtra("item", 0);
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
        mItemCy1.setOnClickListener(this);
        mItemCy2.setOnClickListener(this);
        mItemCy3.setOnClickListener(this);
        mItemCy4.setOnClickListener(this);
        mItemCy5.setOnClickListener(this);
        mItemCy6.setOnClickListener(this);
        mItemCy7.setOnClickListener(this);
        mItemCy8.setOnClickListener(this);
        
    }
    
    @Override
    public void initDatas() {
        switch (mItemIndex) {
            case 0:
                setTitleCenter("食物热量搜索");
                break;
            case 1:
                setTitleCenter("食物嘌呤搜索");
                break;
            case 2:
                setTitleCenter("食物含钙量搜索");
                break;
            case 3:
                setTitleCenter("运动消耗搜索");
                break;
        }
    }
    
    @Override
    public void onClick(View v) {
        Toast.makeText(mSelf, "搜索成功", Toast.LENGTH_SHORT).show();
        finish();
    }
}
