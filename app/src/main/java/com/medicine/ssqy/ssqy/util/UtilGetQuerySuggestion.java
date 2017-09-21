package com.medicine.ssqy.ssqy.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-09-21.
 */

public class UtilGetQuerySuggestion {
    private static List<String> mDatas=new ArrayList<>();
    public static List<String> getFoodHotDatas(){
        mDatas.clear();;
       mDatas.add("小米");
       mDatas.add("大米");
       mDatas.add("馒头");
       mDatas.add("玉米");
       mDatas.add("米粥");
       mDatas.add("豆浆");
       mDatas.add("腐竹");
       mDatas.add("烧饼");
        return mDatas;
    }
    
    
    public static List<String> getSportDatas(){
        mDatas.clear();;
        mDatas.add("逛街");
        mDatas.add("开车");
        mDatas.add("骑脚踏");
        mDatas.add("打网球");
        mDatas.add("郊游");
        mDatas.add("跳有氧运动");
        mDatas.add("打拳");
        mDatas.add("工作");
        return mDatas;
    }
}
