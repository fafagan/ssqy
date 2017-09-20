package com.medicine.ssqy.ssqy.task.download.controller;

import android.text.TextUtils;

import com.example.sj.mylibrary.util.MakeFileHash;
import com.example.sj.mylibrary.util.StringEmptyUtil;
import com.medicine.ssqy.ssqy.common.utils.SDUtil;
import com.medicine.ssqy.ssqy.task.common.model.TaskState;
import com.medicine.ssqy.ssqy.task.download.model.DownloadTask;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2017/2/20.
 */

public class DownloadThread extends Thread {
    
    private DownloadTask mDownloadTaskNow;
    private DownloadController mDownloadController;
    private static final  Object SYNC=new Object();
    public DownloadThread() {
        mDownloadController = new DownloadController();
    }
    
    @Override
    public void run() {
        super.run();
        
       任务循环队列: while (true) {
           
           synchronized (SYNC){
               mDownloadTaskNow = mDownloadController.getDownloadTaskWait();
    
               //没有下载任务了，那么线程就终止了。。
               if (mDownloadTaskNow == null) {
                   Logger.e("全部下载完毕  ");
                   break;
               }
               mDownloadController.updateDownloadTaskState(mDownloadTaskNow, TaskState.TASK_STATE_PREPARE);
               Logger.e("找到排队任务 ：  " + mDownloadTaskNow.getName());
           }
    
            boolean isPrepareOk = doPrepare();
            //如果当前任务准备失败了，那么我们就继续下一个任务
            if (!isPrepareOk) {
                continue;
            }
            Logger.e("准备成功 ：  " + mDownloadTaskNow.getName());
    
            mDownloadController.updateDownloadTaskState(mDownloadTaskNow, TaskState.TASK_STATE_ING);
            InputStream inputStream = null;
            FileOutputStream fileOutputStream = null;
            HttpURLConnection httpURLConnection = null;
            try {
                URL url = new URL(mDownloadTaskNow.getUrl());
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setConnectTimeout(10000);
                //判断是否已经下载过一部分
                if (mDownloadTaskNow.getNowSize()!=0) {
                    
                    File file=new File(mDownloadTaskNow.getSavaPath());
                    if (file.exists()){
    
                        try {
                            fileOutputStream = new FileOutputStream(mDownloadTaskNow.getSavaPath(),true);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            mDownloadController.cancelTask(mDownloadTaskNow);
                            mDownloadController.updateDownloadTaskState(mDownloadTaskNow,TaskState.TASK_STATE_ERROR_SD);
                        }
                        // 比如我们已经下载100  "Range", "bytes=100-" 
                        httpURLConnection.setRequestProperty("Range", "bytes=" + mDownloadTaskNow.getNowSize()+ "-");
                    }else {
                        try {
                            fileOutputStream = new FileOutputStream(mDownloadTaskNow.getSavaPath());
                        } catch (FileNotFoundException e) {
                            mDownloadController.cancelTask(mDownloadTaskNow);
                            mDownloadController.updateDownloadTaskState(mDownloadTaskNow,TaskState.TASK_STATE_ERROR_SD);
                            e.printStackTrace();
                        }
                    }
      
                }else {
                    fileOutputStream = new FileOutputStream(mDownloadTaskNow.getSavaPath());
                }
                
                
                inputStream = httpURLConnection.getInputStream();
   
                
                
                byte[] bs = new byte[1024 * 100];
                int total = -1;
                
                long timeBegin=System.currentTimeMillis();
                int sizeTotal=0;
                while ((total = inputStream.read(bs)) != -1) {
                    
//                    //取消任务
//                    if (mDownloadController.hasCanceled(mDownloadTaskNow)) {
//                      如果当前任务取消了，  终止当前任务的执行，开始下一个任务
//                        release(inputStream, fileOutputStream, httpURLConnection);
//        
//                        File file = new File(mDownloadTaskNow.getSavaPath());
//                        file.delete();
                     //   continue 任务循环队列;
                 //   }
    
//                    //暂停任务
//                    if (mDownloadController.haspaused(mDownloadTaskNow)) {
//                        continue 任务循环队列;
//                    }
                    
                    //计算速度，最快每1S更新一次下载速度
//                    long timeNow=System.currentTimeMillis();
//                    sizeTotal+=total;
//                    long timeTotal=timeNow-timeBegin;
//                    if (timeTotal>=1000){
                        
                      //  long speedBytes= (long) (sizeTotal/(timeTotal/1000.0f));
                        
                    //    String speed= SizeUtil.formatSpeed(speedBytes);
                      //  mDownloadController.updateDownloadTaskSpeed(mDownloadTaskNow,speed);
                      //  timeBegin=timeNow;
                      //  sizeTotal=0;
                 //   }
                    
                    //写数据到本地
                    fileOutputStream.write(bs, 0, total);
                    mDownloadController.updateDownloadTaskSize(mDownloadTaskNow,total);
                }
                
                //验证sha1，完成任务
                String sha1Origin=mDownloadTaskNow.getSha1();
//                mDownloadController.updateDownloadTaskState(mDownloadTaskNow, TaskState.TASK_STATE_DONE);
                if (TextUtils.isEmpty(sha1Origin)) {
                    mDownloadController.updateDownloadTaskState(mDownloadTaskNow, TaskState.TASK_STATE_DONE);
                }else {
                    mDownloadController.updateDownloadTaskState(mDownloadTaskNow, TaskState.TASK_STATE_SHA1_CHECKING); 
                    
                    String sha1Local= MakeFileHash.getFileSHA1(mDownloadTaskNow.getSavaPath());
                    Logger.e(sha1Local+"  "+sha1Origin);
                    if (sha1Origin.equalsIgnoreCase(sha1Local)){
                        mDownloadController.updateDownloadTaskState(mDownloadTaskNow, TaskState.TASK_STATE_DONE);
                    }else {
                        mDownloadController.resetDownSize(mDownloadTaskNow);
                        mDownloadController.updateDownloadTaskState(mDownloadTaskNow, TaskState.TASK_STATE_ERROR_SHA1);
 
                    }
                }
            } catch (MalformedURLException e) {
                mDownloadController.updateDownloadTaskState(mDownloadTaskNow, TaskState.TASK_STATE_ERROR_NET);
                e.printStackTrace();
            } catch (IOException e) {
                mDownloadController.updateDownloadTaskState(mDownloadTaskNow, TaskState.TASK_STATE_ERROR_NET);
                e.printStackTrace();
            } finally {
                release(inputStream, fileOutputStream, httpURLConnection);
            }
        }
    }
    
    private boolean doPrepare() {

        boolean isSDOK = checkSD();
        if (!isSDOK) {
            mDownloadController.updateDownloadTaskState(mDownloadTaskNow, TaskState.TASK_STATE_ERROR_SD);
            mDownloadController.cancelTask(mDownloadTaskNow);
            return false;
        }
        
        boolean isURLOK = checkURL();
        if (!isURLOK) {
            mDownloadController.updateDownloadTaskState(mDownloadTaskNow, TaskState.TASK_STATE_ERROR_NET);
            return false;
        }
        
        return true;
    }
    
    private boolean checkURL() {
        return !StringEmptyUtil.isEmpty(mDownloadTaskNow.getUrl());
    }
    
    private boolean checkSD() {
        synchronized (SYNC){
            int sizeRequire=mDownloadTaskNow.getTotalSize()+mDownloadController.getTotalSizeIngRequire();
            return SDUtil.canUseSDCard(sizeRequire);
        }
        
    }
    
    public void release(InputStream inputStream, FileOutputStream fileOutputStream, HttpURLConnection httpURLConnection) {
        if (inputStream != null) {
            try {
                inputStream.close();
                inputStream = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        if (fileOutputStream != null) {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                    fileOutputStream = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
            httpURLConnection = null;
        }
        
    }
    
    
}
