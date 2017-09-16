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

import com.example.sj.mylibrary.util.AnimatorString;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.ui.fragment.HomeCourseFragment;

import org.xutils.common.util.DensityUtil;

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
        
        initDKTVs();
    }
    
    private void initDKTVs() {
    
        mTvDkDay1 = (TextView)contentView.findViewById(R.id.tv_dk_day1);
        mTvDkDay2 = (TextView)contentView.findViewById(R.id.tv_dk_day2);
        mTvDkDay3 = (TextView)contentView.findViewById(R.id.tv_dk_day3);
        mTvDkDay4 = (TextView)contentView.findViewById(R.id.tv_dk_day4);
        mTvDkDay5 = (TextView)contentView.findViewById(R.id.tv_dk_day5);
        mTvDkDay6 = (TextView)contentView.findViewById(R.id.tv_dk_day6);
        mTvDkToday = (TextView) contentView.findViewById(R.id.tv_dk_today);
        mLayoutSuccess = (RelativeLayout)contentView. findViewById(R.id.layout_success);
    
        setTvHeight(mTvDkDay1);
        setTvHeight(mTvDkDay2);
        setTvHeight(mTvDkDay3);
        setTvHeight(mTvDkDay4);
        setTvHeight(mTvDkDay5);
        setTvHeight(mTvDkDay6);
    
        mTvDkDay1.setText("9.07");
        mTvDkDay2.setText("9.08");
        mTvDkDay3.setText("9.09");
        mTvDkDay4.setText("9.10");
        mTvDkDay5.setText("9.12");
        mTvDkDay6.setText("9.13");
        mTvDkToday.setText("9.11");
        setDKYES(mTvDkDay1);
        setDKYES(mTvDkDay4);
//        setDKYES(mTvDkDay5);
        setDKNO(mTvDkDay2);
        setDKNO(mTvDkDay3);
        mTvDkToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "打卡成功！", Toast.LENGTH_SHORT).show();
                mLayoutSuccess.setVisibility(View.VISIBLE);
                setDKYES(mTvDkToday);
                mTvDaysDk.setText("3 天");
                mHomeCourseFragment.changeDaysTextFrag("已连吃 3 天");
                mTvDkToday.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "您今天已经打过卡了哟！", Toast.LENGTH_SHORT).show();
                         
                    }
                });
            }
        });
    }
    

    private  void setDKYES(TextView tv){
        tv.setBackgroundResource(R.drawable.tv_dk_yes);
        tv.setTextColor(0xffffffff);
    }
    private  void setDKNO(TextView tv){
        tv.setBackgroundResource(R.drawable.tv_dk_no);
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
        tv.setLayoutParams(layoutParams);
    }
}
