package com.example.sj.mylibrary.Control;

import com.example.sj.mylibrary.base.BaseFragment;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/1.
 * 统一管理全部的Fragment
 */
public class FragmentController {
    
    private static List<BaseFragment> mFragments =new LinkedList<>();
    
    public static void add(BaseFragment baseFragment){
        mFragments.add(baseFragment);
        
    }
    
    public static void remove(Class aClass){
        for (int i = 0; i< mFragments.size(); i++){
            if (mFragments.get(i).getClass()==aClass){
                mFragments.remove(i);
            }
        }
    }
    
    public static  <T> T getFragment(Class aClass){
        for (int i = 0; i< mFragments.size(); i++){
            if (mFragments.get(i).getClass()==aClass){
                return (T) mFragments.get(i);
            }
        }
        return null;
    }
    
    public static void  killAll(){
        for (int i = mFragments.size()-1; i>=0; i--){
            mFragments.get(i).killSelf();
        }
    }
}
