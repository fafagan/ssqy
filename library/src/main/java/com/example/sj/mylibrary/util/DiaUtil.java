package com.example.sj.mylibrary.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class DiaUtil {

	private static Toast toast = null;
    /**
     * Toast提示
     * @param context
     * @param msg
     */
    public static void showToast(Context context,String msg){
    	if (toast == null) {
			toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
		} else {
			toast.setText(msg);
		}
		toast.show();
    }
    
    public static void showToast(Context context,int msgId){
    	if (toast == null) {
			toast = Toast.makeText(context, msgId, Toast.LENGTH_SHORT);
		} else {
			toast.setText(msgId);
		}
		toast.show();
    }
    
    public static void showLocationToast(Context context, String msg) {
		if (toast == null) {
			toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
		} else {
			toast.setText(msg);
		}
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
}
