package com.medicine.ssqy.ssqy.ui.pop;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sj.mylibrary.net.NetCallback;
import com.example.sj.mylibrary.net.NetForJson;
import com.example.sj.mylibrary.util.AnimatorString;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.common.URLConstant;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.entity.DKEntity;
import com.medicine.ssqy.ssqy.entity.DKRecordEntity;
import com.medicine.ssqy.ssqy.ui.fragment.coursehome.HomeCourseFragment;
import com.orhanobut.logger.Logger;

import org.xutils.common.util.DensityUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/11/20.
 */
public class Pop_dk extends PopupWindow{
    private ImageView mImgvLoading;
    private AnimationDrawable mAnimationDrawable;
    private View contentView;
    private RelativeLayout mContentDigDk;
    private FrameLayout mLayoutBackPopDk;
    private Context mContext;
    public boolean mHasClosed=true;
    
    private ObjectAnimator mObjectAnimatorOpenContent,mObjectAnimatorCloseContent;
    private ObjectAnimator mObjectAnimatorOpenBack,mObjectAnimatorCloseBack;
    private TextView mTv;
    private TextView mTvDkDay1;
    private TextView mTvDkDay2;
    private TextView mTvDkDay3;
    private TextView mTvDkDay4;
    private TextView mTvDkDay5;
    private TextView mTvDkDay6;
    private TextView mTvDkToday;
    private RelativeLayout mLayoutSuccess;
    private TextView mTvDaysDk;
    private HomeCourseFragment mHomeCourseFragment;
    
    private int mTvDKHeight=-1;
    
    private NetForJson mNetForJsonGetDKRecord;
    private NetForJson mNetForJsonDK;
    private List<TextView> mTextViewsDate=new ArrayList<>();
    private  int mDays=0;
    private  boolean mDKToday=true;
    public Pop_dk(Context context, HomeCourseFragment homeCourseFragment) {
        super(context);
        mHomeCourseFragment=homeCourseFragment;
        mContext=context;
        this.setFocusable(false);
        this.setOutsideTouchable(true);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setWidth(DensityUtil.getScreenWidth());
        this.setHeight(DensityUtil.getScreenHeight());
        contentView = LayoutInflater.from(context).inflate(R.layout.dig_dk_frag, null);
        mContentDigDk = (RelativeLayout)contentView. findViewById(R.id.content_dig_dk);
        mTvDaysDk = (TextView)contentView.findViewById(R.id.tv_days_dk);

        mTv = (TextView) contentView.findViewById(R.id.tv);
        mLayoutBackPopDk = (FrameLayout)contentView.findViewById(R.id.layout_back_pop_dk);
        mLayoutBackPopDk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCloseBackAnim();
        
            }
        });
        this.setContentView(contentView);
    
        mTvDkDay1 = (TextView)contentView.findViewById(R.id.tv_dk_day1);
        mTvDkDay2 = (TextView)contentView.findViewById(R.id.tv_dk_day2);
        mTvDkDay3 = (TextView)contentView.findViewById(R.id.tv_dk_day3);
        mTvDkDay4 = (TextView)contentView.findViewById(R.id.tv_dk_day4);
        mTvDkDay5 = (TextView)contentView.findViewById(R.id.tv_dk_day5);
        mTvDkDay6 = (TextView)contentView.findViewById(R.id.tv_dk_day6);
        mTvDkToday = (TextView) contentView.findViewById(R.id.tv_dk_today);
        mLayoutSuccess = (RelativeLayout)contentView. findViewById(R.id.layout_success);
        mTextViewsDate.add(mTvDkDay1);
        mTextViewsDate.add(mTvDkDay2);
        mTextViewsDate.add(mTvDkDay3);
        mTextViewsDate.add(mTvDkDay4);
        mTextViewsDate.add(mTvDkDay5);
        mTextViewsDate.add(mTvDkDay6);
        mTextViewsDate.add(mTvDkToday);
        for (TextView textView : mTextViewsDate) {
            setTvHeight(textView);
            setDKNO(textView);
        }
    
        initNet();
  
        mTvDkToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, "打卡成功！", Toast.LENGTH_SHORT).show();
//                mLayoutSuccess.setVisibility(View.VISIBLE);
//                setDKYES(mTvDkToday);
//                mTvDaysDk.setText("3 天");
//                mHomeCourseFragment.changeDaysTextFrag("已连吃 3 天");
//                mTvDkToday.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(mContext, "您今天已经打过卡了哟！", Toast.LENGTH_SHORT).show();
//                    
//                    }
//                });
                if (mDKToday){
    
                    Toast.makeText(mContext, "您今天已经打过卡了哟！", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(mContext, "正在记录，请稍后", Toast.LENGTH_SHORT).show();
                    mNetForJsonDK.excute();
                }
               
            }
        });
    }
    
    private void initNet() {
    
        mNetForJsonGetDKRecord=new NetForJson(URLConstant.DK_RECORD_URL, new NetCallback<DKRecordEntity>() {
            @Override
            public void onSuccess(DKRecordEntity entity) {
                
                List<DKRecordEntity.DkHistoryEntity> dkHistory = entity.getDkHistory();
                if (dkHistory != null&&dkHistory.size()>0) {
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM.dd");
                    String today = simpleDateFormat.format(new Date());
                    
                    Collections.sort(dkHistory);
                    int index=0;
                    mDays=entity.getDays();
                    mHomeCourseFragment.mTvDaysFragCourse.setText("已连吃 "+ mDays+" 天");
                    mTvDaysDk.setText(mDays+" 天");
                    mDKToday=false;
                    for (DKRecordEntity.DkHistoryEntity dkHistoryEntity : dkHistory) {
                        if (today.equals(dkHistoryEntity.getDate())){
    
                            mTvDkToday.setText(dkHistoryEntity.getDate());
                            if (dkHistoryEntity.isIsRecord()) {
                                setDKYES(mTvDkToday);
                                mDKToday=true;
                                mLayoutSuccess.setVisibility(View.VISIBLE);
                            }else {
                                setDKNO(mTvDkToday);
                                mLayoutSuccess.setVisibility(View.GONE);
                            }
                        }else {
                            mTextViewsDate.get(index).setTag(dkHistoryEntity);
                            mTextViewsDate.get(index).setText(dkHistoryEntity.getDate());
                            if (dkHistoryEntity.isIsRecord()) {
                                setDKYES(mTextViewsDate.get(index));
                            }else {
                                setDKNO(mTextViewsDate.get(index));
                            }
                            index++;
                        }
                    }
                    
                    
                }
            }
    
            @Override
            public void onError() {
        
            }
    
            @Override
            public void onFinish() {
        
            }
        },true);
        
        mNetForJsonGetDKRecord.addParam("uid",SharePLogin.getUid());
        mNetForJsonGetDKRecord.excute();
        mNetForJsonDK=new NetForJson(URLConstant.DK_URL, new NetCallback<DKEntity>() {
            @Override
            public void onSuccess(DKEntity entity) {
                Toast.makeText(mContext, "打卡成功！", Toast.LENGTH_SHORT).show();
                mLayoutSuccess.setVisibility(View.VISIBLE);
                setDKYES(mTvDkToday);
                mTvDaysDk.setText((mDays+1)+" 天");
                mHomeCourseFragment.changeDaysTextFrag("已连吃 "+ (mDays+1)+" 天");
                mTvDkToday.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "您今天已经打过卡了哟！", Toast.LENGTH_SHORT).show();
            
                    }
                });
            }
    
            @Override
            public void onError() {
        
            }
    
            @Override
            public void onFinish() {
        
            }
        },true);
        mNetForJsonDK.addParam("uid", SharePLogin.getUid());
        mNetForJsonDK.addParam("time",System.currentTimeMillis());
        Logger.e("time-->"+System.currentTimeMillis());
        Logger.e("uid-->"+SharePLogin.getUid());
    }
    
    private void initDKTVs() {
    
    
    
      
      
    }
    

    private  void setDKYES(TextView tv){
        tv.setBackgroundResource(R.drawable.tv_dk_yes);
        tv.setTextColor(0xffffffff);
    }
    private  void setDKNO(TextView tv){
        tv.setBackgroundResource(R.drawable.tv_dk_futrue_selector);
        tv.setTextColor(0xff777777);
    }
    
    private void startCloseContentAnim() {
        if (mObjectAnimatorCloseContent==null){
            mObjectAnimatorCloseContent=ObjectAnimator.ofFloat(mContentDigDk, AnimatorString.translationY,0,-mContext.getResources().getDimensionPixelSize(R.dimen.dig_dk_main_height));
            mObjectAnimatorCloseContent.setDuration(400);
            mObjectAnimatorCloseContent.setInterpolator(new DecelerateInterpolator());
            mObjectAnimatorCloseContent.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                  
                }
            
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation); 
                    mContentDigDk.setVisibility(View.GONE);
                    try {
                        dismiss();
                    } catch (Exception e) {
                    }
                }
            });
        
        }
        mObjectAnimatorCloseContent.start();
    }
    
    public void show(View anchor) {
        mHasClosed=false;
        this.showAsDropDown(anchor);
        startOpenContentAnim();
    }
    
    private void startOpenContentAnim() {
        if (mObjectAnimatorOpenContent==null){
            mObjectAnimatorOpenContent=ObjectAnimator.ofFloat(mContentDigDk, AnimatorString.translationY,-mContext.getResources().getDimensionPixelSize(R.dimen.dig_dk_main_height),0);
            mObjectAnimatorOpenContent.setDuration(400);
            mObjectAnimatorOpenContent.setInterpolator(new DecelerateInterpolator());
            mObjectAnimatorOpenContent.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    mContentDigDk.setVisibility(View.VISIBLE);
                }
    
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    startOpenBackAnim();
                }
            });
    
        }
        mObjectAnimatorOpenContent.start();
    }
    
    private void startOpenBackAnim() {
        if (mObjectAnimatorOpenBack==null){
           mObjectAnimatorOpenBack=ObjectAnimator.ofFloat(mLayoutBackPopDk, AnimatorString.translationY,-mContext.getResources().getDimensionPixelSize(R.dimen.height_pop_dk_back),0);
           mObjectAnimatorOpenBack.setDuration(400);
           mObjectAnimatorOpenBack.setInterpolator(new DecelerateInterpolator());
           mObjectAnimatorOpenBack.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    mLayoutBackPopDk.setVisibility(View.VISIBLE);
                }
            });
        
        }
        mObjectAnimatorOpenBack.start();
    }
    
    private void startCloseBackAnim() {
        if (mObjectAnimatorCloseBack==null){
            mObjectAnimatorCloseBack=ObjectAnimator.ofFloat(mLayoutBackPopDk, AnimatorString.translationY,0,-mContext.getResources().getDimensionPixelSize(R.dimen.height_pop_dk_back));
            mObjectAnimatorCloseBack.setDuration(400);
            mObjectAnimatorCloseBack.setInterpolator(new DecelerateInterpolator());
            mObjectAnimatorCloseBack.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                 
                }
    
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mLayoutBackPopDk.setVisibility(View.GONE);
                    startCloseContentAnim();
                }
            });
            
        }
        mObjectAnimatorCloseBack.start();
    }
    public boolean close(){
        if (mHasClosed){
            return this.isShowing();
            
        }
        if (this.isShowing()){
            
            mHasClosed=true;
            startCloseBackAnim();
            return true;
        }
        
        return false;
    }
    
    public void setTvHeight(TextView tv) {
        if (mTvDKHeight==-1) {
            mTvDKHeight=  ( DensityUtil.getScreenWidth()
                    - mContext.getResources().getDimensionPixelSize(R.dimen.padding_dk_root) * 2
                    - mContext.getResources().getDimensionPixelSize(R.dimen.margin_dk_tv) * 2 * 6)/6+1;
        }
        ViewGroup.LayoutParams layoutParams = tv.getLayoutParams();
        layoutParams.height=mTvDKHeight;
        layoutParams.width=mTvDKHeight;
        tv.setLayoutParams(layoutParams);
    }
}
