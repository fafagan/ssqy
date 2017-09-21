package com.medicine.ssqy.ssqy.entity;

/**
 * Created by Administrator on 2017-09-21.
 */

public class DKEntity {
    
    /**
     * errno : 101
     * errorInfo : error!
     * state : false
     */
    
    private int errno;
    private String errorInfo;
    private boolean state;
    
    public int getErrno() {
        return errno;
    }
    
    public void setErrno(int errno) {
        this.errno = errno;
    }
    
    public String getErrorInfo() {
        return errorInfo;
    }
    
    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }
    
    public boolean isState() {
        return state;
    }
    
    public void setState(boolean state) {
        this.state = state;
    }
}


