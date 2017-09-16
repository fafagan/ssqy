package com.medicine.ssqy.ssqy.common.utils.sp;

import com.example.sj.mylibrary.util.SharedPrefrencesUtil;
import com.medicine.ssqy.ssqy.base.KBaseApp;

/**
 * Created by Administrator on 2016/11/9.
 */
public class SharePJQ {
   public static void saveJQ(String jq){
       SharedPrefrencesUtil.saveData(KBaseApp.mContextGlobal,"jq","jq",jq);
       
   }
    public static String getJQ(){
        return SharedPrefrencesUtil.getData(KBaseApp.mContextGlobal,"jq","jq","");
        
    }
  
    
}
