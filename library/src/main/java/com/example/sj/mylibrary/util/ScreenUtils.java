package com.example.sj.mylibrary.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.Log;

/**
 * ScreenUtils
 * <ul>
 * <strong>Convert between dp and sp</strong>
 * <li>{@link ScreenUtils#dpToPx(Context, float)}</li>
 * <li>{@link ScreenUtils#pxToDp(Context, float)}</li>
 * </ul>
 * 
 */
public class ScreenUtils {
	
	/**
	 * @param context
	 * @return 状态栏的高度
	 */
	public static int getStatusBarHeight(Activity context) {
		Rect frame = new Rect();
		context.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		return frame.top;
	}
	
	/**
	 * 
	 * @param context
	 * 获取手机屏幕信息
	 */

	public static void getScreenInfo(Context context) {
		float WidthPx = getScreenWidth(context);
		float HeightPx = getScreenHeight(context);
		float Density = getDensity(context);
		float densityDpi = getDensityDPI(context);
		float WidthDp = pxToDp(context, WidthPx);
		float HeightDp = pxToDp(context, HeightPx);
		
		double in = Math.sqrt(Math.pow(WidthPx, 2) + Math.pow(HeightPx, 2)) / densityDpi;
		
		Log.i(context.getClass().getName(), "屏幕宽px:" + WidthPx + "\n屏幕高Px:" + HeightPx + "\n屏幕密度比:" + Density
				+ "\n屏幕密度dpi:" + densityDpi + "\n屏幕宽dp:" + WidthDp + "\n屏幕高dp:" + HeightDp + "\n英寸：" + in);
	}
	
	/**
	 * 
	 * @param context
	 * @return // 屏幕密度比（像素比例：0.75/1.0/1.5/2.0）
	 */
	public static float getDensity(Context context) {
		if (context == null) {
			return -1;
		}
		return context.getResources().getDisplayMetrics().density;
	}

	/**
	 * 
	 * @param context
	 * @return // 屏幕密度(dpi):指每英寸中的像素数（每寸像素：120/160/240/320）
	 */
	public static int getDensityDPI(Context context) {
		if (context == null) {
			return -1;
		}
		return context.getResources().getDisplayMetrics().densityDpi;
	}

	/**
	 * 
	 * @param context
	 * @return // 屏幕宽（像素，如：480px）
	 */
	public static float getScreenWidth(Context context) {
		if (context == null) {
			return -1;
		}
		return context.getResources().getDisplayMetrics().widthPixels;
	}

	/**
	 * 
	 * @param context
	 * @return // 屏幕高（像素，如：800px）
	 */
	public static float getScreenHeight(Context context) {
		if (context == null) {
			return -1;
		}
		return context.getResources().getDisplayMetrics().heightPixels;
	}

	public static float dpToPx(Context context, float dp) {
		if (context == null) {
			return -1;
		}
		return dp * context.getResources().getDisplayMetrics().density;
	}

	public static float pxToDp(Context context, float px) {
		if (context == null) {
			return -1;
		}
		return px / context.getResources().getDisplayMetrics().density;
	}

	public static float dpToPxInt(Context context, float dp) {
		return (dpToPx(context, dp) + 0.5f);
	}

	public static float pxToDpCeilInt(Context context, float px) {
		return (int) (pxToDp(context, px) + 0.5f);
	}
	
	public static int sp2px(Context context,float spValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		 return (int) (spValue * scale + 0.5f);
	}
	
}
