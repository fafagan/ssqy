package com.medicine.ssqy.json;

/**
 * Created by Administrator on 2016/11/22.
 */
public class UserEntity {
    
    private boolean state;
    private String errorInfo;
    private int errno;
    private int uid;
    private String useraccount;
    private String nickName;
    private String headPicUrl;
    private String sex;
    private int level;
    private boolean isMarried;
    private String birthDay;
    private String regTime;
    private String job;
    private String studylevel;
    private String phone;
    
    public UserEntity setState(boolean state) {
        this.state = state;
        return this;
    }
    
    public UserEntity setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
        return this;
    }
    
    public UserEntity setErrno(int errno) {
        this.errno = errno;
        return this;
    }
    
    public UserEntity setUid(int uid) {
        this.uid = uid;
        return this;
    }
    
    public UserEntity setUseraccount(String useraccount) {
        this.useraccount = useraccount;
        return this;
    }
    
    public UserEntity setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }
    
    public UserEntity setHeadPicUrl(String headPicUrl) {
        this.headPicUrl = headPicUrl;
        return this;
    }
    
    public UserEntity setSex(String sex) {
        this.sex = sex;
        return this;
    }
    
    public UserEntity setLevel(int level) {
        this.level = level;
        return this;
    }
    
    public UserEntity setMarried(boolean married) {
        isMarried = married;
        return this;
    }
    
    public UserEntity setBirthDay(String birthDay) {
        this.birthDay = birthDay;
        return this;
    }
    
    public UserEntity setRegTime(String regTime) {
        this.regTime = regTime;
        return this;
    }
    
    public UserEntity setJob(String job) {
        this.job = job;
        return this;
    }
    
    public UserEntity setStudylevel(String studylevel) {
        this.studylevel = studylevel;
        return this;
    }
    
    public UserEntity setPhone(String phone) {
        this.phone = phone;
        return this;
    }
}
