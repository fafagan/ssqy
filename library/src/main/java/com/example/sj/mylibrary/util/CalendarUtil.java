package com.example.sj.mylibrary.util;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 
 * TODO 时间工具类 2013-11-23下午3:50:20
 * 
 * @author 张铭哲
 */

/**
 * @author tianxingmiao
 * 
 */
@SuppressLint("SimpleDateFormat")
public class CalendarUtil {
	public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat DATE_FORMAT_YEAR = new SimpleDateFormat("yyyy年MM月dd日");
	public static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat DATE_FORMAT_QUESTION_DATE = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static final SimpleDateFormat START_DATE_FORMAT = new SimpleDateFormat("MM月dd日  HH:mm");
	public static final SimpleDateFormat DATE_FORMAT_HOUR = new SimpleDateFormat("HH:mm");
	public static final SimpleDateFormat DATE_FORMAT_MONTH = new SimpleDateFormat("MM月dd日");

	/**
	 * long time to string
	 * 
	 * @param timeInMillis
	 * @param dateFormat
	 * @return
	 */
	public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		return dateFormat.format(new Date(timeInMillis));
	}

	/**
	 * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
	 * 
	 * @param timeInMillis
	 * @return
	 */
	public static String getTime(long timeInMillis) {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
	}

	/**
	 * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
	 * 
	 * @param timeInMillis
	 * @return
	 */
	public static String getStartTime(long timeInMillis) {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		String time = getTime(timeInMillis, START_DATE_FORMAT);
		return time;
	}

	public static String getStringFormat(String strFormat, SimpleDateFormat sFormat) {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		Date date = null;
		try {
			date = DEFAULT_DATE_FORMAT.parse(strFormat);
			return sFormat.format(date);
		} catch (ParseException pe) {
			date = null;
		}
		return "";
	}

	public static String getStringFormat(String strFormat, SimpleDateFormat oldFormat, SimpleDateFormat newFormat) {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		Date date = null;
		try {
			date = oldFormat.parse(strFormat);

		} catch (ParseException pe) {
			date = null;
		}
		return newFormat.format(date);
	}

	/**
	 * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
	 * 
	 * @param timeInMillis
	 * @return
	 */
	public static String getEndTime(long timeInMillis) {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		String time = getTime(timeInMillis, DATE_FORMAT_HOUR);
		return time;
	}

	public static String getDateSx() {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		String timeType;
		if (hour >= 0 && hour < 10) {
			timeType = "早上好:";
		}
		// else if (hour >= 8 && hour < 11) {
		// timeType = "上午好:";
		// }
		else if (hour >= 10 && hour < 14) {
			timeType = "中午好:";
		} else if (hour >= 14 && hour < 19) {
			timeType = "下午好:";
		} else {
			timeType = "晚上好:";
		}
		return timeType;
	}

	/**
	 * 将时间戳转为代表"距现在多久之前"的字符串
	 * 
	 * @param timeStr
	 *            时间戳
	 * @return
	 */
	public static String getStandardDate(String timeStr) {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		StringBuffer sb = new StringBuffer();

		long t = Long.parseLong(timeStr);
		long time = System.currentTimeMillis() - (t * 1000);
		return dealTime(sb, time);
	}

	private static String dealTime(StringBuffer sb, long time) {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		long mill = (long) Math.ceil(time / 1000);// 秒前
		long minute = (long) Math.ceil(time / 60 / 1000.0f);// 分钟前
		long hour = (long) Math.ceil(time / 60 / 60 / 1000.0f);// 小时
		long day = (long) Math.ceil(time / 24 / 60 / 60 / 1000.0f);// 天前
		if (day - 1 > 0) {
			sb.append(day + "天");
		} else if (hour - 1 > 0) {
			if (hour >= 24) {
				sb.append("1天");
			} else {
				sb.append(hour + "小时");
			}
		} else if (minute - 1 > 0) {
			if (minute == 60) {
				sb.append("1小时");
			} else {
				sb.append(minute + "分钟");
			}
		} else if (mill - 1 > 0) {
			if (mill == 60) {
				sb.append("1分钟");
			} else {
				sb.append(mill + "秒");
			}
		} else {
			sb.append("刚刚");
		}
		if (!sb.toString().equals("刚刚")) {
			sb.append("前");
		}
		return sb.toString();
	}

	public static String[] dealTime_Timeinterval(long time) {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		long times = time / 1000;
		int day = (int) (times / 24 / 60 / 60);
		int hours = (int) (times / 60 / 60 % 24);
		int minute = (int) (times / 60 % 60);
		int second = (int) (times % 60);
		String[] sb = {String.valueOf(day),String.format("%02d", hours),String.format("%02d", minute),String.format("%02d", second)};
		return sb;
	}
	
	

	/**
	 * @param start_time
	 * @param timeStr
	 * @return 俩个时间的时间间隔
	 */
	public static long getStandardDate(String start_time, String timeStr) {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));

		long t = Long.parseLong(timeStr);
		long time = (t * 1000) - Long.parseLong(start_time);
		return time;
	}

	/**
	 * @param timeStr
	 * @return 服务器时间时间戳转成字符串
	 */
	public static String getTimeforString(String timeStr) {
		long t = Long.parseLong(timeStr) * 1000;
		return getTime(t, DATE_FORMAT_DATE);
	}

	/**
	 * @param timeStr
	 * @return 距离现在的时间间隔
	 */
	public static long getStandardDateFromCurrent(String timeStr) {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));

		long t = Long.parseLong(timeStr);
		long time = (t * 1000) - System.currentTimeMillis();
		return time;
	}

	/**
	 * @param user_time
	 *            yyyy-MM-dd 格式的字符串
	 * @return 返回时间戳
	 */
	public static String getTime(String user_time, SimpleDateFormat sFormat) {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		String re_time = null;
		Date d;
		try {

			d = sFormat.parse(user_time);
			long l = d.getTime();
			String str = String.valueOf(l);
			re_time = str.substring(0, 10);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return re_time;
	}

}
