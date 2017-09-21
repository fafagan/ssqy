package com.medicine.ssqy.ssqy.util;

/**
 * Created by Administrator on 2016/12/12.
 */
public class UtilTimeConvertS {

//    public static final int HOUR=1000*60*60;
//    public static final int MINUTE=1000*60;
//    public static final int SECOND=1000;
    
    
    //    public static String formatTime(long timeInMillions){
//        
//        long hour=timeInMillions/HOUR;
//        long time1=timeInMillions%HOUR;
//    
//        long minute=time1/MINUTE;
//        long time2=time1%MINUTE;
//    
//        long second=time2/SECOND;
//    
//        return  hour+"时"+minute+"分"+second+"秒";
//    }
    public static final int HOUR = 60 * 60;
    public static final int MINUTE =  60;
 
    
    public static String formatTime(long timeInS) {
        timeInS=timeInS/1000;
        long hour=timeInS/HOUR;
        long time1=timeInS%HOUR;
    
        long minute=time1/MINUTE;
        long time2=time1%MINUTE;
    
        if (hour==0){
            return  minute+"分"+time2+"秒";
        }else {
            return  hour+"时"+minute+"分"+time2+"秒";
        }
       
    }
    
    public static String getHour(long timeInS) {
        timeInS=timeInS/1000;
        long hour=timeInS/HOUR;
        return hour+"";
    }
    public static String getMins(long timeInS) {
        timeInS=timeInS/1000;
        long hour=timeInS/HOUR;
        long time1=timeInS%HOUR;
        
        long minute=time1/MINUTE;
        
       return minute+"";
        
    }
}
