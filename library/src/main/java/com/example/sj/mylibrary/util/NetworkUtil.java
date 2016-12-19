package com.example.sj.mylibrary.util;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetworkUtil {
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
			boolean wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
					.isConnectedOrConnecting();
			boolean internet = cm.getNetworkInfo(
					ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
			if(wifi|internet){
			return true;
		}else{
			return false;
		}
			
		
	}
}
