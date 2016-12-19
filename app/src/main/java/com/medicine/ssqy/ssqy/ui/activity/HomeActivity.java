package com.medicine.ssqy.ssqy.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sj.mylibrary.util.StringEmptyUtil;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.base.KBaseFragment;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePFirst;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.db.TempUser;
import com.medicine.ssqy.ssqy.entity.UserEntity;
import com.medicine.ssqy.ssqy.ui.dialog.DigCourseType;
import com.medicine.ssqy.ssqy.ui.fragment.HomeCourseFragment;
import com.medicine.ssqy.ssqy.ui.fragment.HomeJCFragment;
import com.medicine.ssqy.ssqy.ui.fragment.HomeUtilFragment;
import com.medicine.ssqy.ssqy.ui.views.DragLayout;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends KBaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    private DragLayout mDlActivityHome;
    private DragLayout mDragLayout;
    
    private CircleImageView mImgvHeadActivityHome;
    private ImageView mImgvSexActivityHome;
    private TextView mTvUsernameActivityHome;
    private TextView mTvUserlevelActivityHome;
    private LinearLayout mLayoutItemUserdetailActivityHome;
    private LinearLayout mLayoutItemUserdoctorActivityHome;
    private LinearLayout mLayoutItemUsercourseActivityHome;
    private LinearLayout mLayoutItemUserbodyActivityHome;
    private LinearLayout mLayoutItemUsermsgActivityHome;
    private LinearLayout mLayoutItemUsersettingActivityHome;

//    private LoginEntity mLoginEntity;
    private UserEntity mUserEntity;
    
    
    private ImageView mImgvHomeUsercenter;
    private RadioGroup mRgTitleHome;
    private RadioButton mRbHomeCourse;
    private RadioButton mRbHomeUtil;
    private RadioButton mRbHomeJc;
    private ImageView mImgvHomeHistory;
    private FrameLayout mLayoutHomeFrags;
    
    HomeCourseFragment mHomeCourseFragment=new HomeCourseFragment();
    HomeJCFragment mHomeJCFragment=new HomeJCFragment();
    HomeUtilFragment mHomeUtilFragment=new HomeUtilFragment();
    
    
    private KBaseFragment[] mFragmentHome={mHomeCourseFragment,mHomeUtilFragment,mHomeJCFragment};
    private DigCourseType mDigCourseType;
    
    @Override
    public int setRootView() {
        return R.layout.activity_home;
    }
    
    @Override
    public boolean isUseTitle() {
        return false;
    }
    
    @Override
    public void initViews() {
        SharePFirst.saveIsFirst(false);
        mDlActivityHome = (DragLayout) findViewById(R.id.dl_activity_home);
        initDragLayout();
        initTitles();

        
        
        mUserEntity= TempUser.getNowUser(SharePLogin.getUid());
//        if (!SharePLogin.isFree()){
//            mLoginEntity= (LoginEntity) getDatas().getSerializable("entity");
//        }
        initSlide();
    }
    
    private void initTitles() {
    
    
        mImgvHomeUsercenter = (ImageView) findViewById(R.id.imgv_home_usercenter);
        mRgTitleHome = (RadioGroup) findViewById(R.id.rg_title_home);
        mRbHomeCourse = (RadioButton) findViewById(R.id.rb_home_course);
        mRbHomeUtil = (RadioButton) findViewById(R.id.rb_home_util);
        mRbHomeJc = (RadioButton) findViewById(R.id.rb_home_jc);
        mImgvHomeHistory = (ImageView) findViewById(R.id.imgv_home_history);
        mLayoutHomeFrags = (FrameLayout) findViewById(R.id.layout_home_frags);
    
        mImgvHomeUsercenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDragLayout.open();
            }
        });
    
        mImgvHomeHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(CourseHistoryActivity.class);
            }
        });
        mRgTitleHome.setOnCheckedChangeListener(this);
        mRbHomeCourse.setChecked(true);
    
    
    
    }
    
    private void initSlide() {
    
        mImgvHeadActivityHome = (CircleImageView) findViewById(R.id.imgv_head_activity_home);
        mImgvSexActivityHome = (ImageView) findViewById(R.id.imgv_sex_activity_home);
        mTvUsernameActivityHome = (TextView) findViewById(R.id.tv_username_activity_home);
        mTvUserlevelActivityHome = (TextView) findViewById(R.id.tv_userlevel_activity_home);
        mLayoutItemUserdetailActivityHome = (LinearLayout) findViewById(R.id.layout_item_userdetail_activity_home);
        mLayoutItemUserdoctorActivityHome = (LinearLayout) findViewById(R.id.layout_item_userdoctor_activity_home);
        mLayoutItemUsercourseActivityHome = (LinearLayout) findViewById(R.id.layout_item_usercourse_activity_home);
        mLayoutItemUserbodyActivityHome = (LinearLayout) findViewById(R.id.layout_item_userbody_activity_home);
        mLayoutItemUsermsgActivityHome = (LinearLayout) findViewById(R.id.layout_item_usermsg_activity_home);
        mLayoutItemUsersettingActivityHome = (LinearLayout) findViewById(R.id.layout_item_usersetting_activity_home);
        
       
        if (!StringEmptyUtil.isEmpty(mUserEntity.getHeadPicUrl())) {
            Glide.with(this).load(mUserEntity.getHeadPicUrl()).dontAnimate().placeholder(R.drawable.avatar).into(mImgvHeadActivityHome);
        }
    
        if (!StringEmptyUtil.isEmpty(mUserEntity.getSex())) {
            if (mUserEntity.getSex().equals("woman")){
                mImgvSexActivityHome.setImageResource(R.drawable.sex_woman);
            
            }else {
                mImgvSexActivityHome.setImageResource(R.drawable.sex_man);
            }
        }
        
        if (mUserEntity.getLevel()>0){
            mTvUserlevelActivityHome.setText(" LV "+mUserEntity.getLevel());
        }else {
            mTvUserlevelActivityHome.setText(" LV 1");
        }
        
        if (!StringEmptyUtil.isEmpty(mUserEntity.getNickName())) {
            mTvUsernameActivityHome.setText(mUserEntity.getNickName());
        }else {
            mTvUsernameActivityHome.setText("美丽人生");
        }
    
    
        mLayoutItemUserdetailActivityHome.setOnClickListener(this);
        mLayoutItemUserdoctorActivityHome.setOnClickListener(this);
        mLayoutItemUsercourseActivityHome.setOnClickListener(this);
        mLayoutItemUserbodyActivityHome.setOnClickListener(this);
        mLayoutItemUsermsgActivityHome.setOnClickListener(this);
        mLayoutItemUsersettingActivityHome.setOnClickListener(this);
    
    
    }
    
    private void initDragLayout() {
    
        mDragLayout = (DragLayout) findViewById(R.id.dl_activity_home);
//        mDragLayout.setDragListener(new DragLayout.DragListener() {
//            //界面打开的时候
//            @Override
//            public void onOpen() {
//                mBtOverlayActivityHome.setVisibility(View.VISIBLE);
//            }
//            //界面关闭的时候
//            @Override
//            public void onClose() {
//                mBtOverlayActivityHome.setVisibility(View.GONE);
//            }
//        
//            //界面滑动的时候
//            @Override
//            public void onDrag(float percent) {
//                System.out.println(percent);
//                mBtOverlayActivityHome.setAlpha(percent);
//                if (percent!=0){
//                    mBtOverlayActivityHome.setVisibility(View.VISIBLE);
//                }else {
//                    mBtOverlayActivityHome.setVisibility(View.GONE);
//                }
//            }
//        });
    }
    
    @Override
    public void initDatas() {
        
    }
    
    
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        KBaseFragment kBaseFragmentShow=null;
        switch (checkedId){
            case R.id.rb_home_course:
                kBaseFragmentShow=mFragmentHome[0];
                break;
            case R.id.rb_home_util:
                kBaseFragmentShow=mFragmentHome[1];
                break;
            case R.id.rb_home_jc:
                kBaseFragmentShow=mFragmentHome[2];
                break;
            
        }
        changeFragHS(kBaseFragmentShow);
    }
    
    private void changeFragHS(KBaseFragment showFrag) {
        
        if (!showFrag.isAdded()) {
            addFrag(R.id.layout_home_frags, showFrag);
        }
        for (KBaseFragment scBaseFragment : mFragmentHome) {
            if (scBaseFragment!=showFrag){
                hideFrag(scBaseFragment);
            }
        }
        showFrag(showFrag);
    }
    
    @Override
    public void onBackPressed() {

        if (mHomeCourseFragment.onBackPressed()){
            return;
        }
        super.onBackPressed();
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_item_usercourse_activity_home:
                if (mDigCourseType==null) {
                    mDigCourseType = new DigCourseType(this);
                }
                mDigCourseType.showSelf();
                break;
    
            case R.id.layout_item_userbody_activity_home:
                goToActivity(BodyAnalyseActivity.class);
                break;
            case R.id.layout_item_usermsg_activity_home:
                goToActivity(SystemMsgActivity.class);
                break;
            case R.id.layout_item_userdetail_activity_home:
                Intent intent=new Intent(this,MyInformationActivity.class);
                startActivity(intent);
                break;
            case R.id.layout_item_usersetting_activity_home:
                Intent intent_setting=new Intent(this,SettingActivity.class);
                startActivity(intent_setting);
                break;
        }
        
    }
}
