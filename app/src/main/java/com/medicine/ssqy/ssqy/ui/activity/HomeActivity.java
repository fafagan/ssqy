package com.medicine.ssqy.ssqy.ui.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.sj.mylibrary.net.NetCallback;
import com.example.sj.mylibrary.net.NetForJson;
import com.example.sj.mylibrary.util.StringEmptyUtil;
import com.google.gson.Gson;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.base.KBaseFragment;
import com.medicine.ssqy.ssqy.common.URLConstant;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.db.TempUser;
import com.medicine.ssqy.ssqy.entity.SystemMsgEntity;
import com.medicine.ssqy.ssqy.entity.UserEntity;
import com.medicine.ssqy.ssqy.entity.UserHeadEntity;
import com.medicine.ssqy.ssqy.ui.dialog.DigCourseType;
import com.medicine.ssqy.ssqy.ui.dialog.DigPhotoChoose;
import com.medicine.ssqy.ssqy.ui.fragment.coursehome.HomeCourseFragment;
import com.medicine.ssqy.ssqy.ui.fragment.coursehome.HomeJCFragment;
import com.medicine.ssqy.ssqy.ui.fragment.coursehome.HomeUtilFragment;
import com.medicine.ssqy.ssqy.ui.views.DragLayout;
import com.medicine.ssqy.ssqy.util.SaveBMUtil;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

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
    private ImageView mImgvHeadActivityHome;
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
    private NetForJson mNetForJson;
    private List<SystemMsgEntity> mSystemMsgEntities;
    private TextView mTvPopSystemMsg;
    
    private boolean mHasBeiginUpload = false;
    
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
        mDigPhotoChoose = new DigPhotoChoose(this);
        
        
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        syncDatas();
    }
    
    private void syncDatas() {
        if (mNetForJson == null) {
            
            mNetForJson = new NetForJson(URLConstant.SYSTEM_MSG_URL, new NetCallback<List<SystemMsgEntity>>() {
                
                @Override
                public void onSuccess(List<SystemMsgEntity> systemMsgEntities) {
                    
                    mSystemMsgEntities = systemMsgEntities;
                    int num = 0;
                    if (mSystemMsgEntities != null) {
                        for (SystemMsgEntity systemMsgEntity : mSystemMsgEntities) {
                            if (!systemMsgEntity.isCourseLearned()) {
                                num++;
                            }
                        }
                    }
                    
                    if (num == 0) {
                        mTvPopSystemMsg.setVisibility(View.GONE);
                    } else {
                        mTvPopSystemMsg.setVisibility(View.VISIBLE);
                        mTvPopSystemMsg.setText(num + "");
                    }
                    
                }
                
                @Override
                public void onError() {
                    
                }
                
                @Override
                public void onFinish() {
                }
            }, true);
            
            
        }
        mNetForJson.addParam("uid", SharePLogin.getUid());
        mNetForJson.addParam("startPos", 0);
        mNetForJson.addParam("count", 50);
        mNetForJson.excute();
    }
    
    private void initSlide() {
        mImgvHeadActivityHome = (ImageView) findViewById(R.id.imgv_head_activity_home);
        mImgvSexActivityHome = (ImageView) findViewById(R.id.imgv_sex_activity_home);
        mTvUsernameActivityHome = (TextView) findViewById(R.id.tv_username_activity_home);
        mTvUserlevelActivityHome = (TextView) findViewById(R.id.tv_userlevel_activity_home);
        mLayoutItemUserdetailActivityHome = (LinearLayout) findViewById(R.id.layout_item_userdetail_activity_home);
        mLayoutItemUserdoctorActivityHome = (LinearLayout) findViewById(R.id.layout_item_userdoctor_activity_home);
        mLayoutItemUsercourseActivityHome = (LinearLayout) findViewById(R.id.layout_item_usercourse_activity_home);
        mLayoutItemUserbodyActivityHome = (LinearLayout) findViewById(R.id.layout_item_userbody_activity_home);
        mLayoutItemUsermsgActivityHome = (LinearLayout) findViewById(R.id.layout_item_usermsg_activity_home);
        mLayoutItemUsersettingActivityHome = (LinearLayout) findViewById(R.id.layout_item_usersetting_activity_home);
        mTvPopSystemMsg = (TextView) findViewById(R.id.tv_pop_system_msg);
        
        if (!StringEmptyUtil.isEmpty(mUserEntity.getHeadPicUrl())) {
//            Glide.with(this).load(mUserEntity.getHeadPicUrl()).centerCrop().transform(new GlideCircleTransform(mSelf)).placeholder(R.drawable.avatar).into(mImgvHeadActivityHome);
            Glide.with(mSelf).load(mUserEntity.getHeadPicUrl()).asBitmap().centerCrop().placeholder(R.drawable.avatar).into(new BitmapImageViewTarget(mImgvHeadActivityHome) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(mSelf.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    mImgvHeadActivityHome.setImageDrawable(circularBitmapDrawable);
                }
            });
    
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
                    if (mHasBeiginUpload) {
                        Toast.makeText(mSelf, "正在同步您的头像，请稍后重试！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    mDigPhotoChoose.show();
            
                }
            });
        }
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
        if (record) {
            mRbHomeJc.setChecked(true);
        } else {
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
            
            if (requestCode == DigPhotoChoose.REQUEST_CODE_TAKE_PHOTO) {
                clipPhoto(Uri.fromFile(mDigPhotoChoose.mOutputFile));
                return;
            } else {
                //针对4.4前后的路径不一致问题：http://blog.csdn.net/tempersitu/article/details/20557383
                //以下处理选择图片结果
                //---
                //获取到选择的图片，并且把从相册中选好的图片保存在SD卡
                Uri uri = data.getData();
                ContentResolver cr = this.getContentResolver();
                try {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.RGB_565;
                    options.inSampleSize = 2;
                    mBMTX = BitmapFactory.decodeStream(cr.openInputStream(uri), null, options);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                //保存图片到本地，随后准备上传
                mDigPhotoChoose.mOutputFile = SaveBMUtil.saveMyBitmap(mBMTX, "tx" + System.currentTimeMillis());
                mBMTX.recycle();
                mBMTX = null;
                System.gc();
                //---
                //选择并截取
                clipPhoto(Uri.fromFile(mDigPhotoChoose.mOutputFile));
            }
            
            
        } else {
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
        intent.putExtra("outputX", 960);
        intent.putExtra("outputY", 960);
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
            File file = new File(mDigPhotoChoose.mOutputFile.getAbsoluteFile() + "tmp");
            if (!file.exists()) {
                Toast.makeText(mSelf, "头像设置失败", Toast.LENGTH_SHORT).show();
                return;
            }
            Bitmap bm = BitmapFactory.decodeFile(file.getAbsolutePath());
            if (bm == null) {
                Toast.makeText(mSelf, "头像设置失败", Toast.LENGTH_SHORT).show();
                return;
            }
            final File fileCom = compressBM(file, bm);
            
            if (fileCom == null || !fileCom.exists()) {
                Toast.makeText(mSelf, "头像设置失败", Toast.LENGTH_SHORT).show();
                return;
            }

//            mImgvHeadActivityHome.setImageBitmap(bm);
            Glide.with(mSelf).load(fileCom).asBitmap().centerCrop().placeholder(R.drawable.avatar).into(new BitmapImageViewTarget(mImgvHeadActivityHome) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(mSelf.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    mImgvHeadActivityHome.setImageDrawable(circularBitmapDrawable);
                }
    
            });
//           
    
            RequestParams requestParams=new RequestParams(URLConstant.BASE_URL + "userhead");
            requestParams.addParameter("uid",SharePLogin.getUid());
            requestParams.addBodyParameter("headfile",fileCom);
            x.http().post(requestParams, new Callback.CommonCallback<String>() {
    
                @Override
                public void onSuccess(String result) {
                    System.out.println("result-->"+result);
                    Gson gson=new Gson();
                    UserHeadEntity userHeadEntity = gson.fromJson(result, UserHeadEntity.class);
                    if (userHeadEntity.isState()) {
                        Toast.makeText(mSelf, "头像上传成功！", Toast.LENGTH_SHORT).show();
    
                        UserEntity nowUser = TempUser.getNowUser(SharePLogin.getUid());
                        nowUser.setHeadPicUrl(userHeadEntity.getHeadPicUrl());
                        TempUser.saveOrUpdateUser(nowUser);
                    }else {
                        Toast.makeText(mSelf, "头像同步服务器失败，请检查网络状态！", Toast.LENGTH_SHORT).show();
                    }
                  
                }
    
                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Toast.makeText(mSelf, "头像同步服务器失败，请检查网络状态！", Toast.LENGTH_SHORT).show();
                }
    
                @Override
                public void onCancelled(CancelledException cex) {
        
                }
    
                @Override
                public void onFinished() {
                    mHasBeiginUpload = false;
                }
            });
        }
    }
    
    private File compressBM(File file, Bitmap bm) {
        File comFile = new File(file.getAbsolutePath() + "comp");
        if (comFile.exists()) {
            comFile.delete();
        }
        
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(comFile);
            int bl = 100;
            while (true) {
                
                bm.compress(Bitmap.CompressFormat.JPEG, bl, byteArrayOutputStream);
                if (byteArrayOutputStream.size() < 150 * 1024) {
                    break;
                }
                if (bl <= 20) {
                    break;
                }
                bl -= 5;
                byteArrayOutputStream.reset();
            }
            
            //把BitMap保存到文件中
            bm.compress(Bitmap.CompressFormat.JPEG, bl, fOut);
            return comFile;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(mSelf, "头像设置失败！", Toast.LENGTH_SHORT).show();
        }
        return null;
    }
    
}
