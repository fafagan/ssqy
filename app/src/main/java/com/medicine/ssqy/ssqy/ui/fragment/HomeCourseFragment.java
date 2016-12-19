package com.medicine.ssqy.ssqy.ui.fragment;


import android.animation.ObjectAnimator;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseFragment;
import com.medicine.ssqy.ssqy.ui.activity.AudioCourseListActivity;
import com.medicine.ssqy.ssqy.ui.activity.PicCourseListActivity;
import com.medicine.ssqy.ssqy.ui.activity.VedioCourseListActivity;
import com.medicine.ssqy.ssqy.ui.pop.Pop_dk;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeCourseFragment extends KBaseFragment implements View.OnClickListener {
    
    private RelativeLayout mLayoutChiyaoFragHome;
    private LinearLayout mLayoutImgVedio;
    private TextView mTvTitleVedioFragHome;
    private ImageView mImgvDkVedio;
    private TextView mTvLearnCountVedioFragHome;
    private TextView mTvTotalCountVedioFragHome;
    private LinearLayout mLayoutImgAudio;
    private TextView mTvTitleAudioFragHome;
    private ImageView mImgvDkAudio;
    private TextView mTvLearnCountAudioFragHome;
    private TextView mTvTotalCountAudioFragHome;
    private LinearLayout mLayoutImgTw;
    private TextView mTvTitleTwFragHome;
    private ImageView mImgvDkTw;
    private TextView mTvLearnCountTwFragHome;
    private TextView mTvTotalCountTwFragHome;
    private ObjectAnimator mObjectAnimatorOpen,mObjectAnimatorClose;
    private View mTop;
    private Pop_dk mPop_dk;
    private RelativeLayout mItemVedioToday;
    private RelativeLayout mItemAudioToday;
    private RelativeLayout mItemTwToday;
    private TextView mTvDaysFragCourse;
    

    
    
    
    
    @Override
    public boolean isUseTitle() {
        return false;
    }
    @Override
    public int setRootView() {
        return R.layout.fragment_home_course;
    }
    
    @Override
    public void initViews() {
        mTvDaysFragCourse = (TextView) findViewById(R.id.tv_days_frag_course);
        mLayoutChiyaoFragHome = (RelativeLayout) findViewById(R.id.layout_chiyao_frag_home);
        mLayoutImgVedio = (LinearLayout) findViewById(R.id.layout_img_vedio);
        mTvTitleVedioFragHome = (TextView) findViewById(R.id.tv_title_vedio_frag_home);
        mImgvDkVedio = (ImageView) findViewById(R.id.imgv_dk_vedio);
        mTvLearnCountVedioFragHome = (TextView) findViewById(R.id.tv_learn_count_vedio_frag_home);
        mTvTotalCountVedioFragHome = (TextView) findViewById(R.id.tv_total_count_vedio_frag_home);
        mLayoutImgAudio = (LinearLayout) findViewById(R.id.layout_img_audio);
        mTvTitleAudioFragHome = (TextView) findViewById(R.id.tv_title_audio_frag_home);
        mImgvDkAudio = (ImageView) findViewById(R.id.imgv_dk_audio);
        mTvLearnCountAudioFragHome = (TextView) findViewById(R.id.tv_learn_count_audio_frag_home);
        mTvTotalCountAudioFragHome = (TextView) findViewById(R.id.tv_total_count_audio_frag_home);
        mLayoutImgTw = (LinearLayout) findViewById(R.id.layout_img_tw);
        mTvTitleTwFragHome = (TextView) findViewById(R.id.tv_title_tw_frag_home);
        mImgvDkTw = (ImageView) findViewById(R.id.imgv_dk_tw);
        mTvLearnCountTwFragHome = (TextView) findViewById(R.id.tv_learn_count_tw_frag_home);
        mTvTotalCountTwFragHome = (TextView) findViewById(R.id.tv_total_count_tw_frag_home);
        mTop = (View) findViewById(R.id.top);
        mItemVedioToday = (RelativeLayout) findViewById(R.id.item_vedio_today);
        mItemAudioToday = (RelativeLayout) findViewById(R.id.item_audio_today);
        mItemTwToday = (RelativeLayout) findViewById(R.id.item_tw_today);
        
        
        mLayoutChiyaoFragHome.setOnClickListener(this);
        mItemVedioToday.setOnClickListener(this);
        mItemAudioToday.setOnClickListener(this);
        mItemTwToday.setOnClickListener(this);
        
    }
    
    @Override
    public void initDatas() {
        
    }
    
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_chiyao_frag_home:
                onClickLayout_chiyao();
                break;
            case R.id.item_vedio_today:
                VedioCourseListActivity.showToday(mActivity);
                break;
            case R.id.item_audio_today:
                AudioCourseListActivity.showToday(mActivity);
               
                break;
            case R.id.item_tw_today:
                PicCourseListActivity.showToday(mActivity);
               
                break;
        }
        
    }
    
    private void onClickLayout_chiyao() {
        //initDigChiYao();
        if (mPop_dk==null){
            mPop_dk = new Pop_dk(mActivity,this);
        }
        if (!mPop_dk.isShowing()){
            mPop_dk.show(mTop);
        }
        
    }
    
//    private void initDigChiYao() {
//        
//    }
    
    public  void changeDaysTextFrag(String text){
        
        mTvDaysFragCourse.setText(text);
    }
    public boolean onBackPressed(){
        if (mPop_dk!=null&&mPop_dk.isShowing()){
            
            return   mPop_dk.close();
        }
        return false;
    }
    
}
