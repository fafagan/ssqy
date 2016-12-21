package com.medicine.ssqy.ssqy.util;

/**
 * Created by Amy on 2016/12/12.
 */
public class TimeFormatUtil {
    public static String formatLongToTimeStr(Long l) {
        int hour = 0;
        int minute = 0;
        int second = 0;
        
        second = l.intValue() / 1000;
        
        if (second > 60) {
            minute = second / 60;
            second = second % 60;
        }
        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        if (hour==0) {
            return ( getTwoLength(minute)  + ":"  + getTwoLength(second));
        }else {
            return (getTwoLength(hour) + ":" + getTwoLength(minute)  + ":"  + getTwoLength(second));
        }
      
    }
    
    private static String getTwoLength(final int data) {
        if(data < 10) {
            return "0" + data;
        } else {
            return "" + data;
        }
    }
}
