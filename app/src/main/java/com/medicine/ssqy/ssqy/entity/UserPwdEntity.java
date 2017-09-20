package com.medicine.ssqy.ssqy.entity;

/**
 * Created by Administrator on 2017-09-21.
 */

public class UserPwdEntity {
    
    /**
     * uid : 123123
     * state : true
     */
    
    private String uid;
    private boolean state;
    
    public String getUid() {
        return uid;
    }
    
    public void setUid(String uid) {
        this.uid = uid;
    }
    
    public boolean isState() {
        return state;
    }
    
    public void setState(boolean state) {
        this.state = state;
    }
}
