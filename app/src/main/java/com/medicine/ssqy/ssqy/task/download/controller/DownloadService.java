package com.medicine.ssqy.ssqy.task.download.controller;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.medicine.ssqy.ssqy.task.common.controller.NetState;
import com.medicine.ssqy.ssqy.task.common.controller.TaskMsg;
import com.medicine.ssqy.ssqy.task.common.controller.UpdatePart;
import com.medicine.ssqy.ssqy.task.common.model.TaskState;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.data.JPushLocalNotification;

public class DownloadService extends Service {
    
    DownloadThread[] mDownloadThreads = new DownloadThread[DownloadThreadCount.MAX_COUNT];
    
    public DownloadService() {
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    
        for (int i = 0; i < mDownloadThreads.length; i++) {
            if (mDownloadThreads[i] != null) {
                try {
                    mDownloadThreads[i].stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receive(TaskMsg downloadMsg) {
        if (downloadMsg.mUpdatePart == UpdatePart.STATE) {
            if (downloadMsg.mTask.getState()== TaskState.TASK_STATE_ERROR_SD) {
                Toast.makeText(this, "您的手机空间不足，请清理存储卡后重试！", Toast.LENGTH_SHORT).show();
            }
    
            if (downloadMsg.mTask.getState()== TaskState.TASK_STATE_ERROR_NET||downloadMsg.mTask.getState()== TaskState.TASK_STATE_ERROR_SERVER) {
                Toast.makeText(this, "网络异常，视频缓存停止", Toast.LENGTH_SHORT).show();
                NetState.sNetOK=false;
            }
            if (downloadMsg.mTask.getState()== TaskState.TASK_STATE_DONE){
                JPushLocalNotification ln = new JPushLocalNotification();
                ln.setBuilderId(0);
                ln.setContent("您的养生视频："+downloadMsg.mTask.getName()+"已成功缓存！");
                ln.setTitle("四时七养中医养生");
                long id=11111+new Random().nextInt(12345);
                ln.setNotificationId(id) ;
                ln.setBroadcastTime(System.currentTimeMillis()+1000);
    
                Map<String , Object> map = new HashMap<String, Object>() ;
                map.put("name", "jpush") ;
                map.put("type", "downover") ;
                map.put("notiid", "id") ;
                JSONObject json = new JSONObject(map) ;
                ln.setExtras(json.toString()) ;
                JPushInterface.addLocalNotification(getApplicationContext(), ln);
            }
        }
        downloadMsg.recycle();
    }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        for (int i = 0; i < mDownloadThreads.length; i++) {
            if (mDownloadThreads[i] == null || !mDownloadThreads[i].isAlive()) {
                
                mDownloadThreads[i] = new DownloadThread();
                mDownloadThreads[i].start();
            }
        }
        
        return super.onStartCommand(intent, flags, startId);
    }
    
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
