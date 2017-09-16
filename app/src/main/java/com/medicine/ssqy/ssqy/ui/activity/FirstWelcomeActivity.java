package com.medicine.ssqy.ssqy.ui.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.sj.mylibrary.adapter.SimplePagerAdapter;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;

public class FirstWelcomeActivity extends KBaseActivity {
    private RelativeLayout mActivityFirstWelcome;
    private ViewPager mVpWelcome;
    
    @Override
    public boolean isUseTitle() {
        return false;
    }
    
    @Override
    public int setRootView() {
        return R.layout.activity_first_welcome;
    }
    
    @Override
    public void initViews() {
        mActivityFirstWelcome = (RelativeLayout) findViewById(R.id.activity_first_welcome);
        mVpWelcome = (ViewPager) findViewById(R.id.vp_welcome);
        mVpWelcome.setOffscreenPageLimit(2);
        mVpWelcome.setAdapter(new SimplePagerAdapter(this) {
            @Override
            public View initView(ViewGroup container, int position) {
                View itemView=null;
                if (position==2){
                    itemView=mLayoutInflater.inflate(R.layout.activity_main,container,false);
                    View login = itemView.findViewById(R.id.bt_login_activity_welcome);
                    View reg = itemView.findViewById(R.id.bt_reg_activity_welcome);
                    login.setVisibility(View.VISIBLE);
                    reg.setVisibility(View.VISIBLE);
                    login.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            goToActivity(LoginActivity.class);
                            finish();
                        }
                    });
                    reg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            goToActivity(RegActivity.class);
                            finish();
                        }
                    });
                }else {
                    
                    if (position==0) {
                        itemView=mLayoutInflater.inflate(R.layout.item_vp_welcome,container,false);
                        ImageView  mImgvWelcome = (ImageView)itemView. findViewById(R.id.imgv_welcome);
                        ImageView  mImgvIndexWelcome = (ImageView)itemView. findViewById(R.id.imgv_index_welcome);
                        mImgvWelcome.setImageResource(R.drawable.welcomepage1);
                        mImgvIndexWelcome.setImageResource(R.drawable.mq_loading_1);
                    }
                    else   if (position==1){
                        itemView=mLayoutInflater.inflate(R.layout.layout_welcome_app,container,false);
                        ImageView  mImgvIndexWelcome = (ImageView)itemView. findViewById(R.id.imgv_index_welcome);
                        mImgvIndexWelcome.setImageResource(R.drawable.mq_loading_2);
                    }  
                }
                return itemView;
            }
    
            @Override
            public int getCount() {
                return 3;
            }
        });
    }
    
    @Override
    public void initDatas() {
        
    }
}
