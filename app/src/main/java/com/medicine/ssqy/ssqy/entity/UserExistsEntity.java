package com.medicine.ssqy.ssqy.entity;

/**
 * Created by Administrator on 2017-09-21.
 */

public class UserExistsEntity {
    
    /**
     * uid : u002
     * state : true
     * userexists : true
     */
    
    private String uid;
    private boolean state;
    private boolean userexists;
    
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
    
    public boolean isUserexists() {
        return userexists;
    }
    
    public void setUserexists(boolean userexists) {
        this.userexists = userexists;
    }
}
