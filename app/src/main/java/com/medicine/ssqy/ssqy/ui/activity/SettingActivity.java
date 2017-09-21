package com.medicine.ssqy.ssqy.ui.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sj.mylibrary.net.NetCallback;
import com.example.sj.mylibrary.net.NetForJson;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.base.KBaseApp;
import com.medicine.ssqy.ssqy.common.URLConstant;
import com.medicine.ssqy.ssqy.common.utils.SizeUtil;
import com.medicine.ssqy.ssqy.entity.AppCheckEntity;
import com.medicine.ssqy.ssqy.task.download.controller.DownloadController;
import com.medicine.ssqy.ssqy.ui.dialog.AccoutDig;
import com.medicine.ssqy.ssqy.ui.dialog.LogoutDig;
import com.medicine.ssqy.ssqy.util.DownMsg;
import com.medicine.ssqy.ssqy.util.DownloadAPK;
import com.medicine.ssqy.ssqy.util.DownloadAPKEntity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

public class SettingActivity extends KBaseActivity implements View.OnClickListener {
    private TextView mTvAccountActivitySetting;
    private TextView mTvAboutusActivitySetting;
    private TextView mTvExitActivitySetting;
    private LogoutDig mDialogLogout;
    
    private TextView mTvUsercenterActivitySetting;
    private TextView mTvClearCacheActivitySetting;
    
    private DownloadController mDownloadController;
    
    private TextView mTvUpdateActivitySetting;
    private int mVersionNow;
    
    private NetForJson mNetForJsonUpdate;
    
    @Override
    public int setRootView() {
        return R.layout.activity_setting;
    }
    
    @Override
    public void initViews() {
        setTitleCenter("设置");
        mTvAccountActivitySetting = (TextView) findViewById(R.id.tv_account_activity_setting);
        mTvAboutusActivitySetting = (TextView) findViewById(R.id.tv_aboutus_activity_setting);
        mTvExitActivitySetting = (TextView) findViewById(R.id.tv_exit_activity_setting);
        mDialogLogout = new LogoutDig(this);
        mTvUsercenterActivitySetting = (TextView) findViewById(R.id.tv_usercenter_activity_setting);
        mTvClearCacheActivitySetting = (TextView) findViewById(R.id.tv_clearCache_activity_setting);
        mTvUpdateActivitySetting = (TextView) findViewById(R.id.tv_update_activity_setting);
    }
    
    @Override
    public void initDatas() {
        mTvAccountActivitySetting.setOnClickListener(this);
        mTvAboutusActivitySetting.setOnClickListener(this);
        mTvExitActivitySetting.setOnClickListener(this);
        mTvUsercenterActivitySetting.setOnClickListener(this);
        mTvClearCacheActivitySetting.setOnClickListener(this);
        mTvUpdateActivitySetting.setOnClickListener(this);
        mDownloadController = new DownloadController();
        mTvClearCacheActivitySetting.setText("清除系统缓存：  共" + mDownloadController.getAllCache());
        try {
            mVersionNow = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        mTvUpdateActivitySetting.setText("系统升级     当前版本是:" + mVersionNow + ".0");
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_account_activity_setting:
                AccoutDig accountDig = new AccoutDig(this);
                accountDig.showSelf();
                break;
            case R.id.tv_aboutus_activity_setting:
                goToActivity(AboutActivity.class);
                break;
            case R.id.tv_exit_activity_setting:
                mDialogLogout.showSelf();
                break;
            case R.id.tv_usercenter_activity_setting:
                goToActivity(MyInformationActivity.class);
                break;
            case R.id.tv_clearCache_activity_setting:
                mDownloadController.removeAllCache();
                Toast.makeText(mSelf, "清除完毕！", Toast.LENGTH_SHORT).show();
                mTvClearCacheActivitySetting.setText("清除系统缓存：  共0M");
                break;
            case R.id.tv_update_activity_setting:
                
                if (mNetForJsonUpdate == null) {
                    mNetForJsonUpdate = new NetForJson(URLConstant.APP_UPDATE_URL, new NetCallback<AppCheckEntity>() {
                        @Override
                        public void onSuccess(final AppCheckEntity entity) {
                            if (entity.isNeedUpdate()) {
                                if (mAlertDialogUpdate == null) {
                                    mAlertDialogUpdate = new AlertDialog.Builder(mSelf).
                                            setTitle("四时七养版本升级")
                                            .setMessage("检测到最新版本： " + entity.getNewVersionCode() + ".0，是否为您升级？").
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
                break;
        }
    }
    
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
        downloadAPKEntity.setVersioncode(Integer.parseInt(entity.getNewVersionCode()));
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
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    
    private AlertDialog mAlertDialogUpdate;
    private ProgressDialog mProgressDialog;
}
