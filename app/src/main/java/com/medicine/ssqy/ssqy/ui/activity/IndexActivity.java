package com.medicine.ssqy.ssqy.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.sj.mylibrary.util.StringEmptyUtil;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePFirst;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePJQ;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogo;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePNotify;
import com.medicine.ssqy.ssqy.entity.UserEntity;
import com.medicine.ssqy.ssqy.ui.dialog.DigCourseType;
import com.medicine.ssqy.ssqy.ui.dialog.DigNotify;
import com.medicine.ssqy.ssqy.ui.views.CircleMenuLayout;
import com.medicine.ssqy.ssqy.util.UtilGetJQPic;
import com.medicine.ssqy.ssqy.util._24SolarTerms;

import static com.medicine.ssqy.ssqy.util.UtilGetJQPic.getLogoRes;
import static com.medicine.ssqy.ssqy.util._24SolarTerms.getNowJQ;

public class IndexActivity extends KBaseActivity  {
    
    private String[] mItemTexts = new String[] { "1.我的体质 ", "2.我的七养", "3.我的每日养生",
            "4.我的疾病管理", "5.养生日记", "6.养生宣教","7.个人中心" };
    private int[] mItemImgs = new int[] { R.drawable.byfa,
            R.drawable.ssqy, R.drawable.wdkc,
            R.drawable.xjtz, R.drawable.xsbj,
            R.drawable.ysrj ,R.drawable.zjzx};
    private CircleMenuLayout mCircleMenuLayout;
    
    private ImageView mImgvLogoIndex;
    private RelativeLayout mIdCircleMenuItemCenter;
    
   

//    private CircleImageView mImgvHeadActivityHome;
//    private ImageView mImgvSexActivityHome;
//    private TextView mTvUsernameActivityHome;
//    private TextView mTvUserlevelActivityHome;
//    private LinearLayout mLayoutItemUserdetailActivityHome;
//    private LinearLayout mLayoutItemUserdoctorActivityHome;
//    private LinearLayout mLayoutItemUsercourseActivityHome;
//    private LinearLayout mLayoutItemUserbodyActivityHome;
//    private LinearLayout mLayoutItemUsermsgActivityHome;
//    private LinearLayout mLayoutItemUsersettingActivityHome;
    
    private UserEntity mUserEntity;
    private DigCourseType mDigCourseType;
    private DigNotify mDigNotify;
    @Override
    public int setRootView() {
        return R.layout.activity_index;
    }
    
    @Override
    public void initViews() {
        SharePFirst.saveIsFirst(false);
//        AnimationSet animationSet=new AnimationSet(true);
//        AlphaAnimation alphaAnimation=new AlphaAnimation(0f,1f);
//        alphaAnimation.setDuration(500);
//        alphaAnimation.setFillAfter(true);
//    
//    
//        ScaleAnimation scaleAnimation=new ScaleAnimation(0f,1f,0f,1f,0.5f,0.5f);
//        scaleAnimation.setDuration(500);
//        scaleAnimation.setFillAfter(true);
//    
//    
//        animationSet.addAnimation(alphaAnimation);
//        animationSet.addAnimation(scaleAnimation);
//        
//        LayoutAnimationController layoutAnimationController=new LayoutAnimationController(animationSet,1f);
//        mLayoutZhuanpan.setLayoutAnimation(layoutAnimationController);
    
    
//        mItemIndexZhuanpanWdkc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goToActivity(HomeActivity.class);
//            }
//        });
//        
//        mItemIndexZhuanpanXsbj.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//              
//            goToActivity(AppInformationActivity.class);
//            }
//        });
//    

//    
//        mItemIndexZhuanpanByfa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goToActivity(BodyAnalyseActivity.class);
//        
//            }
//        });
//        mItemIndexZhuanpanSsqy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goToActivity(SSQYActivity.class);
//        
//            }
//        });
//    
//        mItemIndexZhuanpanZjzx.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goToActivity(ChatActivity.class);
//        
//            }
//        });
    
 //       mUserEntity= TempUser.getNowUser(SharePLogin.getUid());
//        initSlide();
        if (!SharePNotify.getCancel()) {
            mDigNotify=new DigNotify(this);
            mDigNotify.show();
        }
        
        mCircleMenuLayout = (CircleMenuLayout) findViewById(R.id.id_menulayout);
        mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);
        mImgvLogoIndex = (ImageView) findViewById(R.id.imgv_logo_index);
        mIdCircleMenuItemCenter = (RelativeLayout) findViewById(R.id.id_circle_menu_item_center);
        int  logoRes= UtilGetJQPic.getLogoRes();
        int circleRes = UtilGetJQPic.getCircleRes();
        if (StringEmptyUtil.isEmpty( SharePJQ.getJQ())) {
            SharePJQ.saveJQ(getNowJQ());
            
            SharePLogo.saveCircle(logoRes);
            SharePLogo.saveCircle(circleRes);
        }else {
    
            String jqNow=_24SolarTerms.getNowJQ();
            String jqOld=SharePJQ.getJQ();
            if (!jqNow.equals(jqOld)){
                logoRes = getLogoRes();
                circleRes = UtilGetJQPic.getCircleRes();
                SharePLogo.saveCircle(logoRes);
                SharePLogo.saveCircle(circleRes);
                SharePJQ.saveJQ(_24SolarTerms.getNowJQ());
            }
        }
        
       
        
        
        
        mImgvLogoIndex.setImageResource(logoRes);
        mIdCircleMenuItemCenter.setBackgroundResource(circleRes);
    
        mCircleMenuLayout.setOnMenuItemClickListener(new CircleMenuLayout.OnMenuItemClickListener()
        {
        
            @Override
            public void itemClick(View view, int pos)
            {
                switch (pos){
                    case 0:
                        goToActivity(BodyAnalyseActivity.class);
                        break;
                    case 1:
                        goToActivity(SSQYActivity.class);
                        break;
                    case 2:
                        goToActivity(HomeActivity.class);
                        break;
                    case 3:
                        goToActivity(JBGLActivity.class);
                        break;
                    case 4:
                        goToActivity(CalendarActivity.class);
                        break;
                    case 5:
                        goToActivity(SystemMsgActivity.class);
                        break;
                    case 6:
                        goToActivity(SettingActivity.class);
                        break;
                    
                }
            }
        
            @Override
            public void itemCenterClick(View view)
            {
            
            }
        });
    }
    
//    private void initSlide() {
//        mImgvHeadActivityHome = (CircleImageView) findViewById(R.id.imgv_head_activity_home);
//        mImgvSexActivityHome = (ImageView) findViewById(R.id.imgv_sex_activity_home);
//        mTvUsernameActivityHome = (TextView) findViewById(R.id.tv_username_activity_home);
//        mTvUserlevelActivityHome = (TextView) findViewById(R.id.tv_userlevel_activity_home);
//        mLayoutItemUserdetailActivityHome = (LinearLayout) findViewById(R.id.layout_item_userdetail_activity_home);
//        mLayoutItemUserdoctorActivityHome = (LinearLayout) findViewById(R.id.layout_item_userdoctor_activity_home);
//        mLayoutItemUsercourseActivityHome = (LinearLayout) findViewById(R.id.layout_item_usercourse_activity_home);
//        mLayoutItemUserbodyActivityHome = (LinearLayout) findViewById(R.id.layout_item_userbody_activity_home);
//        mLayoutItemUsermsgActivityHome = (LinearLayout) findViewById(R.id.layout_item_usermsg_activity_home);
//        mLayoutItemUsersettingActivityHome = (LinearLayout) findViewById(R.id.layout_item_usersetting_activity_home);
//        
//        
//        if (!StringEmptyUtil.isEmpty(mUserEntity.getHeadPicUrl())) {
//            Glide.with(this).load(mUserEntity.getHeadPicUrl()).dontAnimate().placeholder(R.drawable.avatar).into(mImgvHeadActivityHome);
//        }
//        
//        if (!StringEmptyUtil.isEmpty(mUserEntity.getSex())) {
//            if (mUserEntity.getSex().equals("woman")){
//                mImgvSexActivityHome.setImageResource(R.drawable.sex_woman);
//                
//            }else {
//                mImgvSexActivityHome.setImageResource(R.drawable.sex_man);
//            }
//        }
//        
//        if (mUserEntity.getLevel()>0){
//            mTvUserlevelActivityHome.setText(" LV "+mUserEntity.getLevel());
//        }else {
//            mTvUserlevelActivityHome.setText(" LV 1");
//        }
//        
//        if (!StringEmptyUtil.isEmpty(mUserEntity.getNickName())) {
//            mTvUsernameActivityHome.setText(mUserEntity.getNickName());
//        }else {
//            mTvUsernameActivityHome.setText("美丽人生");
//        }
//        
//        
//        mLayoutItemUserdetailActivityHome.setOnClickListener(this);
//        mLayoutItemUserdoctorActivityHome.setOnClickListener(this);
//        mLayoutItemUsercourseActivityHome.setOnClickListener(this);
//        mLayoutItemUserbodyActivityHome.setOnClickListener(this);
//        mLayoutItemUsermsgActivityHome.setOnClickListener(this);
//        mLayoutItemUsersettingActivityHome.setOnClickListener(this);
//        
//        
//    }
    @Override
    public void initDatas() {
        
    }
    
    @Override
    public boolean isUseTitle() {
        return false;
    }
    
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.layout_item_usercourse_activity_home:
//                if (mDigCourseType==null) {
//                    mDigCourseType = new DigCourseType(this);
//                }
//                mDigCourseType.showSelf();
//                break;
//            
//            case R.id.layout_item_userbody_activity_home:
//                goToActivity(BodyAnalyseActivity.class);
//                break;
//            case R.id.layout_item_usermsg_activity_home:
//                goToActivity(SystemMsgActivity.class);
//                break;
//            case R.id.layout_item_userdetail_activity_home:
//                Intent intent=new Intent(this,MyInformationActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.layout_item_usersetting_activity_home:
//                Intent intent_setting=new Intent(this,SettingActivity.class);
//                startActivity(intent_setting);
//                break;
//        }
        
 //   }
}
