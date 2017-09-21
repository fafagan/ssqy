package com.medicine.ssqy.ssqy.util;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import com.medicine.ssqy.ssqy.base.KBaseApp;
import com.medicine.ssqy.ssqy.common.utils.MakeFileHash;

import org.greenrobot.eventbus.EventBus;
import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 * Created by Administrator on 2017-09-21.
 */

public class DownloadAPK {
    private static DbManager.DaoConfig mDaoConfig;
    
    static {
        mDaoConfig = new DbManager.DaoConfig();
        mDaoConfig.setDbVersion(1);
        mDaoConfig.setDbName("ssqydown");
    }
    
    public static DownloadAPKEntity getTaskUnDone() {
        try {
            return x.getDb(mDaoConfig).findFirst(DownloadAPKEntity.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void clearTask() {
        try {
            x.getDb(mDaoConfig).delete(DownloadAPKEntity.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    
    public static void addNewTask(DownloadAPKEntity downloadAPKEntity) {
        clearTask();
        try {
            x.getDb(mDaoConfig).saveBindingId(downloadAPKEntity);
            startDownloadAPK(downloadAPKEntity);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    
    public static DownloadAPKEntity getVersionAPK(int version) {
        try {
            return x.getDb(mDaoConfig).selector(DownloadAPKEntity.class).where("versioncode", "=", version).and("state", "=", DownloadAPKEntity.STATE_DONE).findFirst();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void startDownloadAPK(final DownloadAPKEntity downloadAPKEntity) {
        RequestParams params = new RequestParams(downloadAPKEntity.getUrl());
        params.setAutoResume(true);//设置是否在下载是自动断点续传
        params.setAutoRename(false);//设置是否根据头信息自动命名文件
      
        params.setSaveFilePath(downloadAPKEntity.getPath());
//        params.setSaveFilePath(dir.getAbsolutePath() + "/" + downloadAPKEntity.getName());
        params.setExecutor(new PriorityExecutor(1, true));//自定义线程池,有效的值范围[1, 3], 设置为3时, 可能阻塞图片加载.
        params.setCancelFast(true);//是否可以被立即停止.
        x.http().get(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onCancelled(CancelledException arg0) {
                
            }
            
            @Override
            public void onError(Throwable arg0, boolean arg1) {
    
                DownMsg downMsg=new DownMsg();
                downMsg.mustStop=true;
                EventBus.getDefault().post(downMsg);
                Toast.makeText(KBaseApp.mContextGlobal, "网络异常，升级停止！", Toast.LENGTH_SHORT).show();
            }
            
            @Override
            public void onFinished() {
            }
            
            @Override
            public void onSuccess(File arg0) {
                
                String fileSHA1 = MakeFileHash.getFileSHA1(arg0.getAbsolutePath());
                if (fileSHA1.equalsIgnoreCase(downloadAPKEntity.getSha1())) {
                    downloadAPKEntity.setState(DownloadAPKEntity.STATE_DONE);
                    try {
                        x.getDb(mDaoConfig).update(downloadAPKEntity);
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
    
                    DownMsg downMsg=new DownMsg();
                    downMsg.percent= 100;
                    downMsg.mDownloadAPKEntity=downloadAPKEntity;
                    EventBus.getDefault().post(downMsg);
                    installAPK(downloadAPKEntity);
                    
                } else {
                    Toast.makeText(KBaseApp.mContextGlobal, "文件校验失败，为您重新下载！", Toast.LENGTH_SHORT).show();
                    clearTask();
                    addNewTask(downloadAPKEntity);
                    startDownloadAPK(downloadAPKEntity);
                    
                }
            }
            
            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                if (isDownloading) {
                    System.out.println("total = [" + total + "], current = [" + current + "], isDownloading = [" + isDownloading + "]");
//                    progressDialog.setProgress((int) (current*100/total));
                    DownMsg downMsg=new DownMsg();
                    downMsg.percent= (int) (current*100.f/total);
                    downMsg.mDownloadAPKEntity=downloadAPKEntity;
                    EventBus.getDefault().post(downMsg);
                }
            }
            
            @Override
            public void onStarted() {
                
            }
            
            @Override
            public void onWaiting() {
            }
            
        });
    }
    
//    private static void installAPK(DownloadAPKEntity downloadAPKEntity) {
//        Toast.makeText(KBaseApp.mContextGlobal, "下载完毕，为您安装最新版本", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setDataAndType(Uri.parse("file://" + downloadAPKEntity.getPath()),
//                "application/vnd.android.package-archive");
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        KBaseApp.mContextGlobal.startActivity(intent);
//    }
    public static void installAPK(DownloadAPKEntity downloadAPKEntity) {
        Toast.makeText(KBaseApp.mContextGlobal, "下载完毕，为您安装最新版本", Toast.LENGTH_SHORT).show();
        
        
        File file = new File(downloadAPKEntity.getPath());
        Intent intent = new Intent(Intent.ACTION_VIEW);
        System.out.println("downloadAPKEntity.getPath()---->"+downloadAPKEntity.getPath());
        //判读版本是否在7.0以上
        if (Build.VERSION.SDK_INT >= 24) {
            //provider authorities
            Uri apkUri = FileProvider.getUriForFile(KBaseApp.mContextGlobal, "com.mydomain.fileprovider", file);
            //Granting Temporary Permissions to a URI
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
    
        KBaseApp.mContextGlobal.startActivity(intent);
    }
}
