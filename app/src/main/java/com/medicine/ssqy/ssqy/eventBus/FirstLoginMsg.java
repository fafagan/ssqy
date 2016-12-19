package com.medicine.ssqy.ssqy.eventBus;

/**
 * Created by Administrator on 2016/12/11.
 */
public class FirstLoginMsg {
    public static final int ACTION_DONE_1=110;
    public static final int ACTION_DONE_2=120;
    public static final int ACTION_DONE_3=130;
    public static final int ACTION_DONE_4=140;
    public static final int ACTION_DONE_5=150;
    public static final int ACTION_DONE_ALL=9999;
    public int action;
    
    public String sex;
    public boolean isMarried;
    public String birthDay;
    public String job;
    public String studyLevel;
    
}
