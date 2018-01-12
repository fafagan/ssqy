package com.medicine.ssqy.ssqy.ui.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.sj.mylibrary.net.NetCallback;
import com.example.sj.mylibrary.net.NetForJson;
import com.example.sj.mylibrary.util.StringEmptyUtil;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.base.KBaseApp;
import com.medicine.ssqy.ssqy.brodcast.ConnectionChangeReceiver;
import com.medicine.ssqy.ssqy.common.URLConstant;
import com.medicine.ssqy.ssqy.common.utils.SizeUtil;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePFirst;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePJQ;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogo;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePNotify;
import com.medicine.ssqy.ssqy.entity.AppCheckEntity;
import com.medicine.ssqy.ssqy.entity.UserEntity;
import com.medicine.ssqy.ssqy.task.download.controller.DownloadController;
import com.medicine.ssqy.ssqy.task.download.model.DownloadTask;
import com.medicine.ssqy.ssqy.ui.dialog.DigCourseType;
import com.medicine.ssqy.ssqy.ui.dialog.DigNotify;
import com.medicine.ssqy.ssqy.ui.views.CircleMenuLayout;
import com.medicine.ssqy.ssqy.util.DownMsg;
import com.medicine.ssqy.ssqy.util.DownloadAPK;
import com.medicine.ssqy.ssqy.util.DownloadAPKEntity;
import com.medicine.ssqy.ssqy.util.UtilGetJQPic;
import com.medicine.ssqy.ssqy.util._24SolarTerms;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import static com.medicine.ssqy.ssqy.util.UtilGetJQPic.getLogoRes;
import static com.medicine.ssqy.ssqy.util._24SolarTerms.getNowJQ;

public class IndexActivity extends KBaseActivity {
    
    private String[] mItemTexts = new String[]{"1.我的体质 ", "2.我的七养", "3.我的每日养生",
            "4.我的疾病管理", "5.养生日记", "6.养生宣教", "7.个人中心"};
    private int[] mItemImgs = new int[]{R.drawable.byfa,
            R.drawable.ssqy, R.drawable.wdkc,
            R.drawable.xjtz, R.drawable.xsbj,
            R.drawable.ysrj, R.drawable.zjzx};
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
    
    private ConnectionChangeReceiver myReceiver;
    private DownloadController mDownloadController;
    private NetForJson mNetForJsonUpdate;
    
    private void registerReceiver() {
        if (myReceiver == null) {
            IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
            myReceiver = new ConnectionChangeReceiver();
            this.registerReceiver(myReceiver, filter);
        }
        
    }
    
    
    private void unregisterReceiver() {
        this.unregisterReceiver(myReceiver);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver();
        _24SolarTerms.clear();
        EventBus.getDefault().unregister(this);
    }
    
    @Override
    public int setRootView() {
        return R.layout.activity_index;
    }
    
    @Override
    public void initViews() {
        SharePFirst.saveIsFirst(false);
        registerReceiver();
        mDownloadController = new DownloadController();
        DownloadTask downloadTaskWait = mDownloadController.getDownloadTaskWait();
        
        if (downloadTaskWait != null) {
            mDownloadController.startTasks();
            Toast.makeText(mSelf, "网络恢复，继续为您缓存视频", Toast.LENGTH_SHORT).show();
        }
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
            mDigNotify = new DigNotify(this);
            mDigNotify.show();
        }
        
        mCircleMenuLayout = (CircleMenuLayout) findViewById(R.id.id_menulayout);
        mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);
        mImgvLogoIndex = (ImageView) findViewById(R.id.imgv_logo_index);
        mIdCircleMenuItemCenter = (RelativeLayout) findViewById(R.id.id_circle_menu_item_center);
        int logoRes = UtilGetJQPic.getLogoRes();
        int circleRes = UtilGetJQPic.getCircleRes();
        if (StringEmptyUtil.isEmpty(SharePJQ.getJQ())) {
            SharePJQ.saveJQ(getNowJQ());
            
            SharePLogo.saveCircle(logoRes);
            SharePLogo.saveCircle(circleRes);
        } else {
            
            String jqNow = _24SolarTerms.getNowJQ();
            String jqOld = SharePJQ.getJQ();
            if (!jqNow.equals(jqOld)) {
                logoRes = getLogoRes();
                circleRes = UtilGetJQPic.getCircleRes();
                SharePLogo.saveCircle(logoRes);
                SharePLogo.saveCircle(circleRes);
                SharePJQ.saveJQ(_24SolarTerms.getNowJQ());
            }
        }
        
        
        mImgvLogoIndex.setImageResource(logoRes);
        mIdCircleMenuItemCenter.setBackgroundResource(circleRes);
        
        mCircleMenuLayout.setOnMenuItemClickListener(new CircleMenuLayout.OnMenuItemClickListener() {
            
            @Override
            public void itemClick(View view, int pos) {
                switch (pos) {
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
                        goToActivity(JBGLListActivity.class);
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
            public void itemCenterClick(View view) {
                
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
        try {
            mVersionNow = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        checkUpdate();
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
    private AlertDialog mAlertDialogUpdate;
    private ProgressDialog mProgressDialog;
    
    
    private void doUpdate(AppCheckEntity entity) {
        DownloadAPKEntity downloadAPKEntity=new DownloadAPKEntity();
        downloadAPKEntity.setName(entity.getFilename());
        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + KBaseApp.mContextGlobal.getPackageName() + "/update/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
        downloadAPKEntity.setPath(   dir.getAbsolutePath() + "/" + downloadAPKEntity.getName());
        downloadAPKEntity.setSha1(entity.getSha1());
        downloadAPKEntity.setTotalSize(entity.getTotalSize());
        downloadAPKEntity.setUrl(entity.getAppurl());
        try {
            downloadAPKEntity.setVersioncode(Integer.parseInt(entity.getNewVersionCode()));
        } catch (NumberFormatException e) {
            Toast.makeText(mSelf, "服务器版本异常，请联系管理人员！", Toast.LENGTH_SHORT).show();
            return;
        }
        DownloadAPK.addNewTask(downloadAPKEntity);
        if (mProgressDialog==null) {
            mProgressDialog=new ProgressDialog(mSelf);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.setTitle("为您升级APP中，共 "+ SizeUtil.formatSize(entity.getTotalSize()));
            mProgressDialog.setMax(100);
            mProgressDialog.setProgress(0);
            mProgressDialog.setCancelable(false);
            
            
        }
        EventBus.getDefault().register(this);
        mProgressDialog.show();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(DownMsg downMsg){
        if (downMsg.mustStop) {
            mProgressDialog.cancel();
            return;
        }
        if (downMsg.mDownloadAPKEntity.getState()==DownloadAPKEntity.STATE_DONE){
            mProgressDialog.cancel();
            EventBus.getDefault().unregister(this);
            return;
        }
        mProgressDialog.setProgress(downMsg.percent);
        
        
    }
    
    private void checkUpdate(){
        if (mNetForJsonUpdate == null) {
            mNetForJsonUpdate = new NetForJson(URLConstant.APP_UPDATE_URL, new NetCallback<AppCheckEntity>() {
                @Override
                public void onSuccess(final AppCheckEntity entity) {
                    if (entity.isNeedUpdate()) {
                        if (mAlertDialogUpdate == null) {
                            mAlertDialogUpdate = new AlertDialog.Builder(mSelf).
                                    setTitle("四时七养版本升级")
                                
                                    .setMessage("当前版本："+mVersionNow+".0，检测到最新版本： " + entity.getNewVersionCode() + ".0，必须升级才可继续使用呦！！").
                                            setPositiveButton("升级", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Toast.makeText(mSelf, "开始为您升级", Toast.LENGTH_SHORT).show();
                                                    doUpdate(entity);
                                                    dialog.cancel();
                                                }
                                            })
                                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            
                                            dialog.cancel();
//                                            System.exit(0);
                                        }
                                    }).
                                            create();
                        }
                        mAlertDialogUpdate.show();
                    } else {
                        Toast.makeText(mSelf, "您当前已是最新版本！", Toast.LENGTH_SHORT).show();
                    }
                }
            
                @Override
                public void onError() {
                    Toast.makeText(mSelf, "网络错误", Toast.LENGTH_SHORT).show();
                }
            
                @Override
                public void onFinish() {
                
                }
            });
        }
        mNetForJsonUpdate.addParam("versioncode",mVersionNow);
        mNetForJsonUpdate.excute();
    }
    private int mVersionNow;
}
