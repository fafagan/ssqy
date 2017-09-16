package com.medicine.ssqy.ssqy.entity;

/**
 * Created by Administrator on 2017-09-07.
 */

public class RecordEntity {
    
    /**
     * state : true
     * uid : xxx
     * msg : xxxxxxx
     */
    
    private boolean state;
    private String uid;
    private String msg;
    
    public boolean isState() {
        return state;
    }
    
    public void setState(boolean state) {
        this.state = state;
    }
    
    public String getUid() {
        return uid;
    }
    
    public void setUid(String uid) {
        this.uid = uid;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
