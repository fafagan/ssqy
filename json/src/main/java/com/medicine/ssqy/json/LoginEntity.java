package com.medicine.ssqy.json;

/**
 * Created by Administrator on 2016/11/22.
 */
public class LoginEntity {
    private boolean state;
    private String errorInfo;
    private int errno;
    private boolean isformally;
    private boolean isFisrtLogin;
    private int uid;
    private String type;
    private String useraccount;
    private String nickName;
    private String headPicUrl;
    private String sex;
    private int level;
    
    public LoginEntity setState(boolean state) {
        this.state = state;
        return this;
    }
    
    public LoginEntity setIsformally(boolean isformally) {
        this.isformally = isformally;
        return this;
    }
    
    public LoginEntity setFisrtLogin(boolean fisrtLogin) {
        isFisrtLogin = fisrtLogin;
        return this;
    }
    
    public LoginEntity setUid(int uid) {
        this.uid = uid;
        return this;
    }
    
    public LoginEntity setType(String type) {
        this.type = type;
        return this;
    }
    public LoginEntity setErrno(int errno) {
        this.errno = errno;
        return this;
    }
    
    public LoginEntity setUseraccount(String useraccount) {
        this.useraccount = useraccount;
        return this;
    }
    
    public LoginEntity setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }
    
    public LoginEntity setHeadPicUrl(String headPicUrl) {
        this.headPicUrl = headPicUrl;
        return this;
    }
    
    public LoginEntity setSex(String sex) {
        this.sex = sex;
        return this;
    }
    
    public LoginEntity setLevel(int level) {
        this.level = level;
        return this;
    }
    
    public LoginEntity setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
        return this;
    }
}
