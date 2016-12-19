package com.medicine.ssqy.ssqy.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/12.
 */
public class UtilCourseDay {
    
    private static SimpleDateFormat mSimpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
    
    public static String getToday(){
        return mSimpleDateFormat.format(new Date());
    }
    public static String getYesterday(){
        Date date=new Date();
        date.setTime(System.currentTimeMillis()-24*60*60*1000);
        return mSimpleDateFormat.format(date);
    }
    
    public static String getBeforeYesterday(){
        Date date=new Date();
        date.setTime(System.currentTimeMillis()-24*60*60*1000*2);
        return mSimpleDateFormat.format(date);
    }
}
