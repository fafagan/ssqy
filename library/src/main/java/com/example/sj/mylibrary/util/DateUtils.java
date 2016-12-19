package com.example.sj.mylibrary.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateUtils {

	public static String getDateString(String format, long longTime) {
		SimpleDateFormat sformat = null;
		if (format == null || "".equals(format)) {
			sformat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		} else {
			sformat = new SimpleDateFormat(format, Locale.getDefault());
		}
		if (longTime == 0) {
			return sformat.format(new Date(System.currentTimeMillis()));
		}
		return sformat.format(new Date(longTime));
	}

}
