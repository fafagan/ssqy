package com.medicine.ssqy.ssqy.entity;

/**
 * Created by Administrator on 2017-09-01.
 */

public class UsersetEntity {
    
    /**
     * uid : u002
     * state : true
     * userexists : null
     */
    
    private String uid;
    private boolean state;
    private Object userexists;
    
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
    
    public Object getUserexists() {
        return userexists;
    }
    
    public void setUserexists(Object userexists) {
        this.userexists = userexists;
    }
}
