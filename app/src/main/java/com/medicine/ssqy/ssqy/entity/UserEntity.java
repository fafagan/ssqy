package com.medicine.ssqy.ssqy.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2016/12/9.
 */
@Table(name = "UserEntity")
public class UserEntity {
    
    
    /**
     * birthDay : 1966-5-5
     * headPicUrl : http://xxxxxxxxx
     * job : 工人
     * nickName : Lily
     * phone : 13112342234
     * regTime : 注册时间2016-10-10
     * sex : man
     * studylevel : 博士
     * useraccount : 123456765
     * isMarried : true
     * level : 5
     * state : true
     * uid : 10010
     */
    @Column(name="id",isId = true)
    private int id;
    
    @Column(name="birthDay")
    private String birthDay;
    
    @Column(name="headPicUrl")
    private String headPicUrl;
    
    @Column(name="job")
    private String job;
    
    @Column(name="nickName")
    private String nickName;
    
    @Column(name="phone")
    private String phone;
    
    @Column(name="regTime")
    private String regTime;
    
    @Column(name="sex")
    private String sex;
    
    @Column(name="studylevel")
    private String studylevel;
    
    @Column(name="useraccount")
    private String useraccount;
    
    @Column(name="isMarried")
    private boolean isMarried;
    
    @Column(name="level")
    private int level;
    
    @Column(name="state")
    private boolean state;
    
    @Column(name="isFirstLogin")
    private boolean isFirstLogin;
    
    @Column(name="uid")
    private int uid;
    private String errorInfo;
    private int errno;
    
    
    public boolean isFirstLogin() {
        return isFirstLogin;
    }
    
    public void setFirstLogin(boolean firstLogin) {
        isFirstLogin = firstLogin;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public boolean isMarried() {
        return isMarried;
    }
    
    public void setMarried(boolean married) {
        isMarried = married;
    }
    
    public String getErrorInfo() {
        return errorInfo;
    }
    
    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }
    
    public int getErrno() {
        return errno;
    }
    
    public void setErrno(int errno) {
        this.errno = errno;
    }
    
    public String getBirthDay() {
        return birthDay;
    }
    
    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }
    
    public String getHeadPicUrl() {
        return headPicUrl;
    }
    
    public void setHeadPicUrl(String headPicUrl) {
        this.headPicUrl = headPicUrl;
    }
    
    public String getJob() {
        return job;
    }
    
    public void setJob(String job) {
        this.job = job;
    }
    
    public String getNickName() {
        return nickName;
    }
    
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getRegTime() {
        return regTime;
    }
    
    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }
    
    public String getSex() {
        return sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    public String getStudylevel() {
        return studylevel;
    }
    
    public void setStudylevel(String studylevel) {
        this.studylevel = studylevel;
    }
    
    public String getUseraccount() {
        return useraccount;
    }
    
    public void setUseraccount(String useraccount) {
        this.useraccount = useraccount;
    }
    
    public boolean isIsMarried() {
        return isMarried;
    }
    
    public void setIsMarried(boolean isMarried) {
        this.isMarried = isMarried;
    }
    
    public int getLevel() {
        return level;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }
    
    public boolean isState() {
        return state;
    }
    
    public void setState(boolean state) {
        this.state = state;
    }
    
    public int getUid() {
        return uid;
    }
    
    public void setUid(int uid) {
        this.uid = uid;
    }
}
