package com.example.sj.mylibrary.util;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 字符串操作工具类
 */

public class StringUtil {
	public static String stringFilter(String str) throws PatternSyntaxException {
		// 只允许字母、数字和汉字
		// String regEx = "[^~$#<>'^&*\\x22]+ ";
		String regEx = "[<>'#]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	/*
 	* 字符串反转成String
 	*/
	public static String reverse(String str){
		return new StringBuilder(str).reverse().toString();
	}

	/*
     * 字符数组反转成String
     */
	public static String reverse(char[] str){
		String reverse = "";  //注意这是空串，不是null
		for (int i = str.length - 1; i >= 0; i--)
			reverse += str[i];
		return reverse;
	}

	/**
	 * 判断字符串是否为空
	 * @param str		字符串
	 * @return			当字符串为null或者为"null"或者length为0时返回true，否则返回false
	 */
	public static boolean isEmpty(String str){
		if(TextUtils.isEmpty(str) || "null".equals(str)){
			return true;
		} else{
			return false;
		}
	}
}
