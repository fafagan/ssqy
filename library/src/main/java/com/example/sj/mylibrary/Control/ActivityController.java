package com.example.sj.mylibrary.Control;

import com.example.sj.mylibrary.base.BaseActivity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/1.
 * 统一管理全部的Activity
 */
public class ActivityController {
    
    private static List<BaseActivity>  mActivities=new LinkedList<>();
    
    public static void add(BaseActivity baseActivity){
        mActivities.add(baseActivity);
        
    }
    
    public static void remove(Class aClass){
        for (int i=0;i<mActivities.size();i++){
            if (mActivities.get(i).getClass()==aClass){
                mActivities.remove(i);
            }
        }
    }
    
    public static  <T> T getActivity(Class aClass){
        for (int i=0;i<mActivities.size();i++){
            if (mActivities.get(i).getClass()==aClass){
                return (T) mActivities.get(i);
            }
        }
        return null;
    }
    
    public static void  killAll(){
        for (int i=mActivities.size()-1;i>=0;i--){
            mActivities.get(i).killSelf();
        }
    }
}
