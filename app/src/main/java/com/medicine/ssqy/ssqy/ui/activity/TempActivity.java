package com.medicine.ssqy.ssqy.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;

public class TempActivity extends KBaseActivity {
    private ImageView mImgv1;
    private ImageView mImgv2;
    private ImageView mImgv3;
    private ImageView mImgv4;
    private ImageView mImgv5;
    private ImageView mImgv6;
    private ImageView mImgv7;
    private ImageView mImgv8;
    private LinearLayout mLayoutSearchTemp;
    

    
    
    @Override
    public int setRootView() {
        return R.layout.activity_temp;
    }
    
    @Override
    public void initViews() {
        mLayoutSearchTemp = (LinearLayout) findViewById(R.id.layout_search_temp);
        mImgv1 = (ImageView) findViewById(R.id.imgv1);
        mImgv2 = (ImageView) findViewById(R.id.imgv2);
        mImgv3 = (ImageView) findViewById(R.id.imgv3);
        mImgv4 = (ImageView) findViewById(R.id.imgv4);
        mImgv5 = (ImageView) findViewById(R.id.imgv5);
        mImgv6 = (ImageView) findViewById(R.id.imgv6);
        mImgv7 = (ImageView) findViewById(R.id.imgv7);
        mImgv8 = (ImageView) findViewById(R.id.imgv8);
        setTitleCenter("健康工具");
    
        mLayoutSearchTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(SearchActivity.class);
            }
        });
    }
    
    @Override
    public void initDatas() {
    
        Glide.with(this).load("file:///android_asset/temp/temp_jd.jpg").into(mImgv1);
        Glide.with(this).load("file:///android_asset/temp/temp_zr.jpg").into(mImgv2);
        Glide.with(this).load("file:///android_asset/temp/temp_lj.jpg").into(mImgv3);
        Glide.with(this).load("file:///android_asset/temp/temp_df.jpg").into(mImgv4);
        Glide.with(this).load("file:///android_asset/temp/temp_px.jpg").into(mImgv5);
        Glide.with(this).load("file:///android_asset/temp/temp_nr.jpg").into(mImgv6);
        Glide.with(this).load("file:///android_asset/temp/temp_dg.jpg").into(mImgv7);
        Glide.with(this).load("file:///android_asset/temp/temp_nc.jpg").into(mImgv8);
    }
}
