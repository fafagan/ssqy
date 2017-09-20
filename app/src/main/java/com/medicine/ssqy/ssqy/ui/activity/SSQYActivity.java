package com.medicine.ssqy.ssqy.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sj.mylibrary.util.AnimatorString;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.util.JQ;
import com.medicine.ssqy.ssqy.util.Yang;
import com.medicine.ssqy.ssqy.util._24SolarTerms;

import java.util.ArrayList;
import java.util.List;

public class SSQYActivity extends KBaseActivity implements View.OnClickListener {
    private RelativeLayout mActivitySsqy;
    private TextView mTvCy;
    private TextView mSlResult;
    private TextView mYangResult;
    private ImageView mTitleSsqy;
    private RelativeLayout mLayoutSlSsqy;
    private TextView mChooseSl;
    private RelativeLayout mLayoutYangSsqy;
    private TextView mChooseYang;
    private LinearLayout mLayoutYang1;
    private Button mYangXin;
    private Button mYangQi;
    private Button mYangDong;
    private Button mYangJu;
    private LinearLayout mLayoutYang2;
    private Button mYangShu;
    private Button mYangShi;
    private Button mYangYao;
    private Button mSsqyQuery;
    private Button mBtJqSsqlActivity;
    private Button mBtJq1;
    private Button mBtJq2;
    private Button mBtJq3;
    private Button mBtJq4;
    
    private Button mBtChun;
    private Button mBtXia;
    private Button mBtQiu;
    private Button mBtDong;
    

    
    private List<Button> mButtons=new ArrayList<>();
    Button mButtonCheckedSL,mButtonCheckedYang;
    private JQ mNowJQ;
    private Yang mNowYang=Yang.sYangs.get(0);
    private OnCXQDClick mOnCXQDClick;
    @Override
    public int setRootView() {
        return R.layout.activity_ssqy;
    }
    
    @Override
    public void initViews() {
        setTitleCenter("四时七养手册");
        mActivitySsqy = (RelativeLayout) findViewById(R.id.activity_ssqy);
    
        mSsqyQuery = (Button) findViewById(R.id.ssqy_query);
        mTvCy = (TextView) findViewById(R.id.tv_cy);
        mSlResult = (TextView) findViewById(R.id.sl_result);
        mYangResult = (TextView) findViewById(R.id.yang_result);
        mTitleSsqy = (ImageView) findViewById(R.id.title_ssqy);
        mLayoutSlSsqy = (RelativeLayout) findViewById(R.id.layout_sl_ssqy);
        mChooseSl = (TextView) findViewById(R.id.choose_sl);
        mLayoutYangSsqy = (RelativeLayout) findViewById(R.id.layout_yang_ssqy);
        mChooseYang = (TextView) findViewById(R.id.choose_yang);
        mLayoutYang1 = (LinearLayout) findViewById(R.id.layout_yang_1);
        mYangXin = (Button) findViewById(R.id.yang_xin);
        mYangQi = (Button) findViewById(R.id.yang_qi);
        mYangDong = (Button) findViewById(R.id.yang_dong);
        mYangJu = (Button) findViewById(R.id.yang_ju);
        mLayoutYang2 = (LinearLayout) findViewById(R.id.layout_yang_2);
        mYangShu = (Button) findViewById(R.id.yang_shu);
        mYangShi = (Button) findViewById(R.id.yang_shi);
        mYangYao = (Button) findViewById(R.id.yang_yao);
        mBtJqSsqlActivity = (Button) findViewById(R.id.bt_jq_ssql_activity);
        mBtJq1 = (Button) findViewById(R.id.bt_jq1);
        mBtJq2 = (Button) findViewById(R.id.bt_jq2);
        mBtJq3 = (Button) findViewById(R.id.bt_jq3);
        mBtJq4 = (Button) findViewById(R.id.bt_jq4);
    
        mBtChun = (Button) findViewById(R.id.bt_chun);
        mBtXia = (Button) findViewById(R.id.bt_xia);
        mBtQiu = (Button) findViewById(R.id.bt_qiu);
        mBtDong = (Button) findViewById(R.id.bt_dong);
    
        mOnCXQDClick=new OnCXQDClick();
        mBtChun.setOnClickListener(mOnCXQDClick);
        mBtXia.setOnClickListener(mOnCXQDClick);
        mBtQiu.setOnClickListener(mOnCXQDClick);
        mBtDong.setOnClickListener(mOnCXQDClick);
        
        mButtonCheckedSL=mBtJqSsqlActivity;
        mButtonCheckedYang=mYangXin;
        mButtons.add(mBtJqSsqlActivity);
        mButtons.add(mBtJq1);
        mButtons.add(mBtJq2);
        mButtons.add(mBtJq3);
        mButtons.add(mBtJq4);
        mButtons.add(mYangQi);
        mButtons.add(mYangDong);
        mButtons.add(mYangJu);
        mButtons.add(mYangShu);
        mButtons.add(mYangShi);
        mButtons.add(mYangYao);
        mButtons.add(mYangXin);
    
    
        mBtJqSsqlActivity.setOnClickListener(this);
         mBtJq1.setOnClickListener(this);
         mBtJq2.setOnClickListener(this);
         mBtJq3.setOnClickListener(this);
         mBtJq4.setOnClickListener(this);
        mYangQi.setOnClickListener(this);
        mYangDong.setOnClickListener(this);
        mYangJu.setOnClickListener(this);
        mYangShu.setOnClickListener(this);
        mYangShi.setOnClickListener(this);
        mYangYao.setOnClickListener(this);
        mYangXin.setOnClickListener(this);
    
        mYangQi.setTag(Yang.sYangs.get(0));
        mYangDong.setTag(Yang.sYangs.get(1));
        mYangJu.setTag(Yang.sYangs.get(2));
        mYangShu.setTag(Yang.sYangs.get(3));
        mYangShi.setTag(Yang.sYangs.get(4));
        mYangYao.setTag(Yang.sYangs.get(5));
        mYangXin.setTag(Yang.sYangs.get(6));
    
        mSsqyQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mSelf,SSQYResultActivity.class);
                intent.putExtra("sl",mNowJQ);
                intent.putExtra("yang",mNowYang);
                startActivity(intent);
            }
        });
    }
    
    @Override
    public void initDatas() {
    
        mNowJQ = _24SolarTerms.getNowJQObj();
        mBtJqSsqlActivity.setTag(mNowJQ);
        mBtJqSsqlActivity.setText(mNowJQ.title);
        mSlResult.setText(mNowJQ.title);
        mYangResult.setText(mNowYang.title);
        List<JQ> near4JQObj = _24SolarTerms.getNear4JQObj();
        mBtJq1.setTag(near4JQObj.get(0));
        mBtJq2.setTag(near4JQObj.get(1));
        mBtJq3.setTag(near4JQObj.get(2));
        mBtJq4.setTag(near4JQObj.get(3));
        mBtJq1.setText(near4JQObj.get(0).title);
        mBtJq2.setText(near4JQObj.get(1).title);
        mBtJq3.setText(near4JQObj.get(2).title);
        mBtJq4.setText(near4JQObj.get(3).title);
    }
    
    @Override
    public void onClick(View v) {
        Button button= (Button) v;
        button.setBackgroundResource(R.drawable.shape_ssqy_choose);
        button.setTextColor(Color.WHITE);
    
        switch (v.getId()) {
            case R.id.bt_jq1:
            case R.id.bt_jq2:
            case R.id.bt_jq3:
            case R.id.bt_jq4:
            case R.id.bt_jq_ssql_activity:
                if (mButtonCheckedSL != null) {
                    mButtonCheckedSL.setBackgroundResource(R.drawable.shape_ssqy);
                    mButtonCheckedSL.setTextColor(getResources().getColor(R.color.text_color_red));
                    
                }
                mButtonCheckedSL=button;
                ObjectAnimator objectAnimator1=ObjectAnimator.ofFloat(mSlResult, AnimatorString.rotationY,0,360);
                objectAnimator1.setDuration(500);
                objectAnimator1.start();
                objectAnimator1.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mSlResult.setText(mButtonCheckedSL.getText());
                    }
                });
                mNowJQ= (JQ) v.getTag();
                break;
            case R.id.yang_dong:
            case R.id.yang_xin:
            case R.id.yang_yao:
            case R.id.yang_shi:
            case R.id.yang_shu:
            case R.id.yang_qi:
            case R.id.yang_ju:
                if (mButtonCheckedYang != null) {
                    mButtonCheckedYang.setBackgroundResource(R.drawable.shape_ssqy);
                    mButtonCheckedYang.setTextColor(getResources().getColor(R.color.text_color_red));
                    
                }
                mButtonCheckedYang=button;
    
                ObjectAnimator objectAnimator2=ObjectAnimator.ofFloat(mYangResult, AnimatorString.rotationY,0,360);
                objectAnimator2.setDuration(500);
                objectAnimator2.start();
                objectAnimator2.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mYangResult.setText(mButtonCheckedYang.getText());
                    }
                });
                mNowYang= (Yang) v.getTag();
                break;
        }
        
    }
    
    private class  OnCXQDClick implements View.OnClickListener{
    
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(mSelf,SSQYResultActivity.class);
            intent.putExtra("cxqdMode",true);
            switch (v.getId()) {
                case R.id.bt_chun:
                    intent.putExtra("cxqdType","chun");
                    break;
                case R.id.bt_xia:
                    intent.putExtra("cxqdType","xia");
                    break;
                case R.id.bt_qiu:
                    intent.putExtra("cxqdType","qiu");
                    break;
                case R.id.bt_dong:
                    intent.putExtra("cxqdType","dong");
                    break;
            }
           
         
            startActivity(intent);
        }
    }
}
