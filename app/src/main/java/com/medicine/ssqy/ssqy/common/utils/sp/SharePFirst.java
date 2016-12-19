package com.medicine.ssqy.ssqy.common.utils.sp;

import com.example.sj.mylibrary.util.SharedPrefrencesUtil;
import com.medicine.ssqy.ssqy.base.KBaseApp;

/**
 * Created by Administrator on 2016/11/9.
 */
public class SharePFirst {
   public static void saveIsFirst(boolean isFirst){
       SharedPrefrencesUtil.saveData(KBaseApp.mContextGlobal,"ssqyIsFirst","IsFirst",isFirst);
       
   }
    public static boolean isFirst(){
        return SharedPrefrencesUtil.getData(KBaseApp.mContextGlobal,"ssqyIsFirst","IsFirst",true);
        
    }
    
}
