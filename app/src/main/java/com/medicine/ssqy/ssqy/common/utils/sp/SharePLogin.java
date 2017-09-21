package com.medicine.ssqy.ssqy.common.utils.sp;

import com.example.sj.mylibrary.util.SharedPrefrencesUtil;
import com.medicine.ssqy.ssqy.base.KBaseApp;

/**
 * Created by Administrator on 2016/11/9.
 */
public class SharePLogin {
   public static void saveIsFree(boolean isFree){
       SharedPrefrencesUtil.saveData(KBaseApp.mContextGlobal,"ssqylogin","isFree",isFree);
       
   }
    public static boolean isFree(){
        return SharedPrefrencesUtil.getData(KBaseApp.mContextGlobal,"ssqylogin","isFree",false);
        
    }
    
    public static void saveUsername(String username){
        SharedPrefrencesUtil.saveData(KBaseApp.mContextGlobal,"ssqylogin","username",username);
        saveIsFree(true);
        
    }
    public static String getUsername(){
        return SharedPrefrencesUtil.getData(KBaseApp.mContextGlobal,"ssqylogin","username","");
        
    }
    public static void saveUid(String uid){
        SharedPrefrencesUtil.saveData(KBaseApp.mContextGlobal,"ssqylogin","uid",uid);
        saveIsFree(true);
        
    }
    public static String getUid(){
        return SharedPrefrencesUtil.getData(KBaseApp.mContextGlobal,"ssqylogin","uid","");
        
    }
    public static void saveUserpwd(String userpwd){
        SharedPrefrencesUtil.saveData(KBaseApp.mContextGlobal,"ssqylogin","userpwd",userpwd);
        saveIsFree(true);
    }
    public static String getUserpwd(){
        return SharedPrefrencesUtil.getData(KBaseApp.mContextGlobal,"ssqylogin","userpwd","");
        
    }
    
    public static void saveLastPhone(String phone){
        SharedPrefrencesUtil.saveData(KBaseApp.mContextGlobal,"ssqylogin","LastPhone",phone);
 
    }
    public static String getLastPhone(){
        return SharedPrefrencesUtil.getData(KBaseApp.mContextGlobal,"ssqylogin","LastPhone","");
        
    }
}
