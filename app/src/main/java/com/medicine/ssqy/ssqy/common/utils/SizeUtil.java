package com.medicine.ssqy.ssqy.common.utils;

/**
 * Created by Administrator on 2017/1/20.
 */
public class SizeUtil {
    //  <1M   kb   1.22M
    //  <1kb  kb   0.55kb
    public static final int MB=1024*1024;
    public static final int KB=1024;
    public static String formatSize(long sizeBytes){
        Long m=sizeBytes/MB;
        String temp=null;
        String dw=null;
        
        if (m>=1) {
            dw=" M";
            temp=String.valueOf(sizeBytes*1.0f/MB);
            
        }else {
            dw=" K";
            temp=String.valueOf(sizeBytes*1.0f/KB);
        }
    
        //123.456789
        //123.1
        int tempLength=temp.length();
        int pointerPos=temp.indexOf(".");
        int subPos=pointerPos+3;
        if (subPos<tempLength){
            temp= temp.substring(0,subPos);
        }
        return temp+dw;
            
    }
    
    public static String formatSpeed(long sizeBytes){
        Long m=sizeBytes/MB;
        String temp=null;
        String dw=null;
        
        if (m>=1) {
            dw=" mb/s";
            temp=String.valueOf(sizeBytes*1.0f/MB);
            
        }else {
            dw=" kb/s";
            temp=String.valueOf(sizeBytes*1.0f/KB);
        }
        
        //123.456789
        //123.1
        int tempLength=temp.length();
        int pointerPos=temp.indexOf(".");
        int subPos=pointerPos+3;
        if (subPos<tempLength){
            temp= temp.substring(0,subPos);
        }
        return temp+dw;
        
    }
}
