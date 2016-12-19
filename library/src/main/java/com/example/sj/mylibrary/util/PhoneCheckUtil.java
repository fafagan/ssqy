package com.example.sj.mylibrary.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/11/14.
 */
public class PhoneCheckUtil {
    public  static  boolean isRight(String phoneNum){
        Pattern pattern=Pattern.compile("[1][35789]\\d{9}");
        Matcher matcher = pattern.matcher(phoneNum);
        return  matcher.matches();
    
    }
}
