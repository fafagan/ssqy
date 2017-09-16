package com.medicine.ssqy.ssqy.common.utils.sp;

import com.example.sj.mylibrary.util.SharedPrefrencesUtil;
import com.medicine.ssqy.ssqy.base.KBaseApp;

/**
 * Created by Administrator on 2016/11/9.
 */
public class SharePLogo {
   public static void saveLogo(int logo){
       SharedPrefrencesUtil.saveData(KBaseApp.mContextGlobal,"SharePLogo","logo",logo);
       
   }
    public static int getLogo(){
        return SharedPrefrencesUtil.getData(KBaseApp.mContextGlobal,"SharePLogo","logo",0);
        
    }
   public static void saveCircle(int circle){
       SharedPrefrencesUtil.saveData(KBaseApp.mContextGlobal,"SharePLogo","circle",circle);
       
   }
    public static int getCircle(){
        return SharedPrefrencesUtil.getData(KBaseApp.mContextGlobal,"SharePLogo","circle",0);
        
    }
    
}
