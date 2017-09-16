package com.medicine.ssqy.ssqy.common.utils.sp;

import com.example.sj.mylibrary.util.SharedPrefrencesUtil;
import com.medicine.ssqy.ssqy.base.KBaseApp;

/**
 * Created by Administrator on 2016/11/9.
 */
public class SharePNotify {
   public static void saveCancel(boolean cancel){
       SharedPrefrencesUtil.saveData(KBaseApp.mContextGlobal,"cancel","cancel",cancel);
       
   }
    public static boolean getCancel(){
        return SharedPrefrencesUtil.getData(KBaseApp.mContextGlobal,"cancel","cancel",false);
        
    }
  
    
}
