package com.medicine.ssqy.ssqy.task.common.model;


import com.medicine.ssqy.ssqy.common.utils.SizeUtil;

import org.xutils.db.annotation.Column;

/**
 * Created by Administrator on 2017/2/24.
 */

public abstract  class  Task {
    @Column(name = "id", isId = true)
    public int id;
    
    @Column(name = "courseID")
    public String courseID;
    
    @Column(name = "name")
    public String name;
    
    @Column(name = "totalSize")
    public int totalSize;
    
    @Column(name = "nowSize")
    public int nowSize;
    
    @Column(name = "totalSizeStr")
    public String totalSizeStr="0 kb";
    
    @Column(name = "nowSizeStr")
    public String nowSizeStr ="0 kb";
    
    @Column(name = "speed")
    public String speed = "0 kb/s";
    
    @Column(name = "state")
    public int state = TaskState.TASK_STATE_WAIT;
    
    @Column(name = "sha1")
    public String sha1;
    
    
    @Column(name = "ico")
    public String ico;
    
    @Column(name = "time")
    public long time;
  
//    @Column(name = "u")
//    public String u;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
//    
//    public String getFid() {
//        return fid;
//    }
//    
//    public void setFid(String fid) {
//        this.fid = fid;
//    }
    
    
    public String getCourseID() {
        return courseID;
    }
    
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getTotalSize() {
        return totalSize;
    }
    
    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
        this.totalSizeStr= SizeUtil.formatSize(totalSize);
    }
    
    public int getNowSize() {
        
        return nowSize;
    }
    
    public void setNowSize(int nowSize) {
        
        
        this.nowSize = nowSize;
        this.nowSizeStr= SizeUtil.formatSize(nowSize);
    }
    
    public String getTotalSizeStr() {
        return totalSizeStr;
    }
    
    
    public String getNowSizeStr() {
        return nowSizeStr;
    }
    
    
    public String getSpeed() {
        return speed;
    }
    
    public void setSpeed(String speed) {
        this.speed = speed;
    }
    
    public int getState() {
        return state;
    }
    
    public void setState(int state) {
        this.state = state;
    }
    
    public String getSha1() {
        return sha1;
    }
    
    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }
    
    public String getIco() {
        return ico;
    }
    
    public void setIco(String ico) {
        this.ico = ico;
    }
    
    public long getTime() {
        return time;
    }
    
    public void setTime(long time) {
        this.time = time;
    }
//    
//    public String getU() {
//        return u;
//    }
//    
//    public void setU(String u) {
//        this.u = u;
//    }
}
