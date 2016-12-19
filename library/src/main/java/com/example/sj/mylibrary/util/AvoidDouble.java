package com.example.sj.mylibrary.util;

import android.os.Handler;
import android.os.Message;
import android.view.View;

/**
 * Created by Administrator on 2016/11/14.
 */
public class AvoidDouble {
    private static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            View view= (View) msg.obj;
            if (view!=null){
              
                view.setOnClickListener((View.OnClickListener) view.getTag());
            }else {
                view=null;
                System.gc();
            }
       
            
        }
    };
    public static void  avoid(View view, View.OnClickListener onClickListener,long timeInMils){
        view.setOnClickListener(null);
        Message message=Message.obtain();
        message.obj=view;
        view.setTag(onClickListener);
        mHandler.sendMessageDelayed(message,timeInMils);
    }
    
    public static void  avoid(View view,View.OnClickListener onClickListener){
        avoid(view,onClickListener,700);
    }
}
