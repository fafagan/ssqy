package com.medicine.ssqy.ssqy.entity;

/**
 * Created by Administrator on 2017-09-18.
 */

public class SystemMsgListEntity {
    
    public static final int TYPE_TIME = 0;
    public static final int TYPE_DATA = 1;
    private int type;
    private String time;
    private int index;
    
    public int getIndex() {
        return index;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    //    private List<SystemMsgEntity> mSystemMsgEntities;
    private SystemMsgEntity mMsgEntity;
    
    public String getTime() {
        return time;
    }
    
    public void setTime(String time) {
        this.time = time;
        type = TYPE_TIME;
    }
    
    public SystemMsgEntity getMsgEntity() {
        return mMsgEntity;
    }
    
    public void setMsgEntity(SystemMsgEntity msgEntity) {
        type=TYPE_DATA;
        mMsgEntity = msgEntity;
    }
    
    public int getType() {
        return type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    //    public List<SystemMsgEntity> getSystemMsgEntities() {
//        return mSystemMsgEntities;
//    }
//    
//    public void setSystemMsgEntities(List<SystemMsgEntity> systemMsgEntities) {
//        mSystemMsgEntities = systemMsgEntities;
//    }
//    
//    public void addEntity(SystemMsgEntity msgEntity) {
//        type=TYPE_DATA;
//        if (mSystemMsgEntities==null) {
//            mSystemMsgEntities=new ArrayList<>();
//        }
//    
//        mSystemMsgEntities.add(msgEntity);
//    }
}
