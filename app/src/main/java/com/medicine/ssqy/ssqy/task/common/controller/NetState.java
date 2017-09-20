package com.medicine.ssqy.ssqy.task.common.controller;

import android.os.Handler;
import android.os.Message;

import com.medicine.ssqy.ssqy.task.download.controller.DownloadController;

/**
 * Created by Administrator on 2017-09-20.
 */

public class NetState {
    public static boolean sNetOK=true;
    private DownloadController mDownloadController;
    public NetState() {
        mHandler.sendEmptyMessageDelayed(1000*60*60,1);
        mDownloadController=new DownloadController();
    }
    
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    sNetOK=true;
                    mDownloadController.startTasks();
                    break;
                default:
                    break;
            }
        }
    };
}
