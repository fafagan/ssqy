package com.medicine.ssqy.ssqy.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/22.
 */
public class UtilGetDiscussTime {
    
    private static SimpleDateFormat mSimpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String getTimeNow(){
        return  mSimpleDateFormat.format(new Date());
    }
}
