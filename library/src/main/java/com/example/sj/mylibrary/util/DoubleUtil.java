package com.example.sj.mylibrary.util;

import android.annotation.SuppressLint;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DoubleUtil {
	/**
	 * 
	 * @param dot 输入0.0或者0.00标识保留几位
	 * @return
	 */
	public static String getDouble(String dot, Double dotNumber) {
		DecimalFormat df = new DecimalFormat(dot);
		String number = df.format(dotNumber);
		return number;
	}
	@SuppressLint("SimpleDateFormat") public static String convertDate(long mill){
		Date date=new Date(mill*1000L);
		String strs="";
		try {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		strs=sdf.format(date);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return strs;
		}

}
