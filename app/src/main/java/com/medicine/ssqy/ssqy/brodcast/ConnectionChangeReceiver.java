package com.medicine.ssqy.ssqy.brodcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.medicine.ssqy.ssqy.task.common.controller.NetState;
import com.medicine.ssqy.ssqy.task.download.controller.DownloadController;
import com.medicine.ssqy.ssqy.task.download.model.DownloadTask;

public class ConnectionChangeReceiver extends BroadcastReceiver {
    private DownloadController mDownloadController;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (mDownloadController==null) {
            mDownloadController=new DownloadController();
        }
        ConnectivityManager connectivityManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobNetInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo  wifiNetInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        
        if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
            //改变背景或者 处理网络的全局变量
            NetState.sNetOK=false;
        }else {
            NetState.sNetOK=true;
            //改变背景或者 处理网络的全局变量
            DownloadTask downloadTaskWait = mDownloadController.getDownloadTaskWait();
            if (downloadTaskWait != null) {
                mDownloadController.startTasks();
                Toast.makeText(context, "网络恢复，继续为您缓存视频", Toast.LENGTH_SHORT).show();
            }
            
      
        }
    }
}
