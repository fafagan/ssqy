package com.medicine.ssqy.ssqy.ui.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sj.mylibrary.util.StringEmptyUtil;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.base.KBaseFragment;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.db.TempUser;
import com.medicine.ssqy.ssqy.entity.UserEntity;
import com.medicine.ssqy.ssqy.ui.dialog.DigCourseType;
import com.medicine.ssqy.ssqy.ui.dialog.DigPhotoChoose;
import com.medicine.ssqy.ssqy.ui.fragment.coursehome.HomeCourseFragment;
import com.medicine.ssqy.ssqy.ui.fragment.coursehome.HomeJCFragment;
import com.medicine.ssqy.ssqy.ui.fragment.coursehome.HomeUtilFragment;
import com.medicine.ssqy.ssqy.ui.views.DragLayout;
import com.medicine.ssqy.ssqy.util.SaveBMUtil;

import java.io.File;
import java.io.FileNotFoundException;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.medicine.ssqy.ssqy.ui.dialog.DigPhotoChoose.REQUEST_CODE_CLIP_PHOTO;

public class HomeActivity extends KBaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    private DragLayout mDlActivityHome;
    private ImageView mImgvHomeUsercenter;
    private RadioGroup mRgTitleHome;
    private RadioButton mRbHomeCourse;
    private RadioButton mRbHomeUtil;
    private RadioButton mRbHomeJc;
    private ImageView mImgvHomeHistory;
    private FrameLayout mLayoutHomeFrags;
    
    HomeCourseFragment mHomeCourseFragment = new HomeCourseFragment();
    HomeJCFragment mHomeJCFragment = new HomeJCFragment();
    HomeUtilFragment mHomeUtilFragment = new HomeUtilFragment();
    
    private KBaseFragment[] mFragmentHome = {mHomeCourseFragment, mHomeUtilFragment, mHomeJCFragment};
    private DigCourseType mDigCourseType;
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
    private UserEntity mUserEntity;
    private DigPhotoChoose mDigPhotoChoose;
    private Bitmap mBMTX;
 
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
        
        
        mDlActivityHome = (DragLayout) findViewById(R.id.dl_activity_home);
        initTitles();
        mUserEntity = TempUser.getNowUser(SharePLogin.getUid());
        initSlide();
        mDigPhotoChoose=new DigPhotoChoose(this);
        
        
        
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
            if (mUserEntity.getSex().equals("woman")) {
                mImgvSexActivityHome.setImageResource(R.drawable.sex_woman);
                
            } else {
                mImgvSexActivityHome.setImageResource(R.drawable.sex_man);
            }
        }
        
        if (mUserEntity.getLevel() > 0) {
            mTvUserlevelActivityHome.setText(" LV " + mUserEntity.getLevel());
        } else {
            mTvUserlevelActivityHome.setText(" LV 1");
        }
        
        if (!StringEmptyUtil.isEmpty(mUserEntity.getNickName())) {
            mTvUsernameActivityHome.setText(mUserEntity.getNickName());
        } else {
            mTvUsernameActivityHome.setText("美丽人生");
        }
        
        
        mLayoutItemUserdetailActivityHome.setOnClickListener(this);
        mLayoutItemUserdoctorActivityHome.setOnClickListener(this);
        mLayoutItemUsercourseActivityHome.setOnClickListener(this);
        mLayoutItemUserbodyActivityHome.setOnClickListener(this);
        mLayoutItemUsermsgActivityHome.setOnClickListener(this);
        mLayoutItemUsersettingActivityHome.setOnClickListener(this);
        mImgvHeadActivityHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDigPhotoChoose.show();
        
            }
        });
        
    }
    
    private void initTitles() {
        
        
        mRgTitleHome = (RadioGroup) findViewById(R.id.rg_title_home);
        mRbHomeCourse = (RadioButton) findViewById(R.id.rb_home_course);
        mRbHomeUtil = (RadioButton) findViewById(R.id.rb_home_util);
        mRbHomeJc = (RadioButton) findViewById(R.id.rb_home_jc);
        mImgvHomeHistory = (ImageView) findViewById(R.id.imgv_home_history);
        mLayoutHomeFrags = (FrameLayout) findViewById(R.id.layout_home_frags);
        mImgvHomeUsercenter = (ImageView) findViewById(R.id.imgv_home_usercenter);
        mImgvHomeUsercenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (mDigCourseType==null) {
//                    mDigCourseType = new DigCourseType(mSelf);
//                }
//                mDigCourseType.showSelf();
                mDlActivityHome.open();
            }
        });
        
        mImgvHomeHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(CourseHistoryActivity.class);
            }
        });
        mRgTitleHome.setOnCheckedChangeListener(this);
        boolean record = this.getIntent().getBooleanExtra("record", false);
        if(record){
            mRbHomeJc.setChecked(true);
        }else {
            mRbHomeCourse.setChecked(true);
        }
        
        
        
    }
    
    
    @Override
    public void initDatas() {
        
    }
    
    
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        KBaseFragment kBaseFragmentShow = null;
        switch (checkedId) {
            case R.id.rb_home_course:
                kBaseFragmentShow = mFragmentHome[0];
                break;
            case R.id.rb_home_util:
                kBaseFragmentShow = mFragmentHome[1];
                break;
            case R.id.rb_home_jc:
                kBaseFragmentShow = mFragmentHome[2];
                break;
            
        }
        changeFragHS(kBaseFragmentShow);
    }
    
    private void changeFragHS(KBaseFragment showFrag) {
        
        if (!showFrag.isAdded()) {
            addFrag(R.id.layout_home_frags, showFrag);
        }
        for (KBaseFragment scBaseFragment : mFragmentHome) {
            if (scBaseFragment != showFrag) {
                hideFrag(scBaseFragment);
            }
        }
        showFrag(showFrag);
    }
    
    @Override
    public void onBackPressed() {
        
        if (mHomeCourseFragment.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_item_usercourse_activity_home:
                if (mDigCourseType == null) {
                    mDigCourseType = new DigCourseType(this);
                }
                mDigCourseType.showSelf();
                break;
            case R.id.layout_item_userdoctor_activity_home:
                Toast.makeText(mSelf, "该功能尚未开放，敬请关注！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.layout_item_userbody_activity_home:
                goToActivity(BodyAnalyseActivity.class);
                break;
            case R.id.layout_item_usermsg_activity_home:
                goToActivity(SystemMsgActivity.class);
                break;
            case R.id.layout_item_userdetail_activity_home:
                Intent intent = new Intent(this, MyInformationActivity.class);
                startActivity(intent);
                break;
            case R.id.layout_item_usersetting_activity_home:
                Intent intent_setting = new Intent(this, SettingActivity.class);
                startActivity(intent_setting);
                break;
        }
        
    }
    
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
  
        if (resultCode == RESULT_OK) {
            //处理裁剪结果
            if (requestCode == DigPhotoChoose.REQUEST_CODE_CLIP_PHOTO) {
                onClipPhotoFinished(resultCode, data);
                return;
            }
            
            if (requestCode == DigPhotoChoose.REQUEST_CODE_TAKE_PHOTO){
                clipPhoto(Uri.fromFile(mDigPhotoChoose.mOutputFile));
                return;
            }else {
                //针对4.4前后的路径不一致问题：http://blog.csdn.net/tempersitu/article/details/20557383
                //以下处理选择图片结果
                //---
                //获取到选择的图片，并且把从相册中选好的图片保存在SD卡
                Uri uri = data.getData();
                ContentResolver cr = this.getContentResolver();
                try {
                    mBMTX = BitmapFactory.decodeStream(cr.openInputStream(uri));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                //保存图片到本地，随后准备上传
                mDigPhotoChoose.mOutputFile = SaveBMUtil.saveMyBitmap(mBMTX, "tx" + System.currentTimeMillis());
                //---
                //选择并截取
                clipPhoto(Uri.fromFile(mDigPhotoChoose.mOutputFile));
            }
            
            
            
        }else {
            Toast.makeText(mSelf, "图片处理失败，请重试！", Toast.LENGTH_SHORT).show();
        }
        
    }
    
    //裁剪图片方法
    private void clipPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 720);
        intent.putExtra("outputY", 720);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(new File(mDigPhotoChoose.mOutputFile.getAbsoluteFile() + "tmp")));
        startActivityForResult(intent, REQUEST_CODE_CLIP_PHOTO);
    }
    
    //裁剪返回
    private void onClipPhotoFinished(int resultCode, Intent data) {
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "clip photo canceled", Toast.LENGTH_SHORT)
                    .show();
            return;
        } else if (resultCode != RESULT_OK) {
            Toast.makeText(this, "take photo failed", Toast.LENGTH_SHORT)
                    .show();
        } else {
            //此处可替换成ImageLoader处理
            Bitmap bm = BitmapFactory.decodeFile(mDigPhotoChoose.mOutputFile.getAbsolutePath()
                    + "tmp");
            mImgvHeadActivityHome.setImageBitmap(bm);
        }
    }
}
