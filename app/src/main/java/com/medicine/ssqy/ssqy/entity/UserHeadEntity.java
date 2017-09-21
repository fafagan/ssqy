package com.medicine.ssqy.ssqy.entity;

/**
 * Created by Administrator on 2017-09-22.
 */

public class UserHeadEntity {
    
    /**
     * uid : u201709212136580003
     * state : true
     * userexists : null
     */
    
    private String headPicUrl;
    private String uid;
    private boolean state;
    private boolean userexists;
    
    public String getHeadPicUrl() {
        return headPicUrl;
    }
    
    public void setHeadPicUrl(String headPicUrl) {
        this.headPicUrl = headPicUrl;
    }
    
    public boolean isUserexists() {
        return userexists;
    }
    
    public void setUserexists(boolean userexists) {
        this.userexists = userexists;
    }
    
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
