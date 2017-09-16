package com.medicine.ssqy.ssqy.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2017-08-31.
 */
@Table(name = "user")
public class UserEntity {
    
    
    /**
     * id : 53372dda9a4d46b7b7c067f4d1de1614
     * isNewRecord : false
     * remarks : null
     * createDate : 2017-08-31 11:10:20
     * updateDate : 2017-08-31 11:10:20
     * uid : u201708311910200002
     * realName : testuser2211
     * usertype : 0
     * headPicUrl : null
     * nickName : testuser2211
     * sex : null
     * age : null
     * password : 75da67209aec0e5f0ac4ab17695efa1a669b491680ca184f07139856
     * type : null
     * useraccount : 13718454853
     * isFisrtLogin : true
     * isformally : null
     * level : 1
     * regTime : null
     * loginip : null
     * loginteime : null
     * sm : null
     * bz : null
     * phone : null
     * job : null
     * studyLevel : null
     * birthDay : null
     * isMarried : null
     * tjbh : null
     * state : true
     * doctorid : null
     * tz : null
     * tzs : null
     */
    @Column(name="id",isId = true)
    private String id;
    private boolean isNewRecord;
    private String remarks;
    @Column(name="createDate")
    private String createDate;
    private String updateDate;
    private String realName;
    private String usertype;
    private String age;
    private String password;
    private String type;
    private String isformally;
    private String loginip;
    private String loginteime;
    private String sm;
    private String bz;
    private String studyLevel;
    private String tjbh;
    private String doctorid;
    private String tz;
    private String tzs;
    
    
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
    private String isMarried;
    
    @Column(name="level")
    private int level;
    
    @Column(name="state")
    private boolean state;
    
    @Column(name="isFisrtLogin")
    private String isFisrtLogin;
    
    @Column(name="uid")
    private String uid;
    
    
    private String errorInfo;
    private int errno;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public boolean isIsNewRecord() {
        return isNewRecord;
    }
    
    public void setIsNewRecord(boolean isNewRecord) {
        this.isNewRecord = isNewRecord;
    }
    
    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    public String getCreateDate() {
        return createDate;
    }
    
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    
    public String getUpdateDate() {
        return updateDate;
    }
    
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
    
    public String getUid() {
        return uid;
    }
    
    public void setUid(String uid) {
        this.uid = uid;
    }
    
    public String getRealName() {
        return realName;
    }
    
    public void setRealName(String realName) {
        this.realName = realName;
    }
    
    public String getUsertype() {
        return usertype;
    }
    
    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
    
    public String getHeadPicUrl() {
        return headPicUrl;
    }
    
    public void setHeadPicUrl(String headPicUrl) {
        this.headPicUrl = headPicUrl;
    }
    
    public String getNickName() {
        return nickName;
    }
    
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    
    public String getSex() {
        return sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    public String getAge() {
        return age;
    }
    
    public void setAge(String age) {
        this.age = age;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getUseraccount() {
        return useraccount;
    }
    
    public void setUseraccount(String useraccount) {
        this.useraccount = useraccount;
    }
    
    public String isIsFisrtLogin() {
        return isFisrtLogin;
    }
    
    public void setIsFisrtLogin(String isFisrtLogin) {
        this.isFisrtLogin = isFisrtLogin;
    }
    
    public String getIsformally() {
        return isformally;
    }
    
    public void setIsformally(String isformally) {
        this.isformally = isformally;
    }
    
    public int getLevel() {
        return level;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }
    
    public String getRegTime() {
        return regTime;
    }
    
    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }
    
    public String getLoginip() {
        return loginip;
    }
    
    public void setLoginip(String loginip) {
        this.loginip = loginip;
    }
    
    public String getLoginteime() {
        return loginteime;
    }
    
    public void setLoginteime(String loginteime) {
        this.loginteime = loginteime;
    }
    
    public String getSm() {
        return sm;
    }
    
    public void setSm(String sm) {
        this.sm = sm;
    }
    
    public String getBz() {
        return bz;
    }
    
    public void setBz(String bz) {
        this.bz = bz;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getJob() {
        return job;
    }
    
    public void setJob(String job) {
        this.job = job;
    }
    
    public String getStudyLevel() {
        return studyLevel;
    }
    
    public void setStudyLevel(String studyLevel) {
        this.studyLevel = studyLevel;
    }
    
    public String getBirthDay() {
        return birthDay;
    }
    
    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }
    
    public String getIsMarried() {
        return isMarried;
    }
    
    public void setIsMarried(String isMarried) {
        this.isMarried = isMarried;
    }
    
    public String getTjbh() {
        return tjbh;
    }
    
    public void setTjbh(String tjbh) {
        this.tjbh = tjbh;
    }
    
    public boolean isState() {
        return state;
    }
    
    public void setState(boolean state) {
        this.state = state;
    }
    
    public String getDoctorid() {
        return doctorid;
    }
    
    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid;
    }
    
    public String getTz() {
        return tz;
    }
    
    public void setTz(String tz) {
        this.tz = tz;
    }
    
    public String getTzs() {
        return tzs;
    }
    
    public void setTzs(String tzs) {
        this.tzs = tzs;
    }
    
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserEntity{");
        sb.append("id='").append(id).append('\'');
        sb.append(", isNewRecord=").append(isNewRecord);
        sb.append(", remarks='").append(remarks).append('\'');
        sb.append(", createDate='").append(createDate).append('\'');
        sb.append(", updateDate='").append(updateDate).append('\'');
        sb.append(", realName='").append(realName).append('\'');
        sb.append(", usertype='").append(usertype).append('\'');
        sb.append(", age='").append(age).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", isFisrtLogin='").append(isFisrtLogin).append('\'');
        sb.append(", isformally='").append(isformally).append('\'');
        sb.append(", loginip='").append(loginip).append('\'');
        sb.append(", loginteime='").append(loginteime).append('\'');
        sb.append(", sm='").append(sm).append('\'');
        sb.append(", bz='").append(bz).append('\'');
        sb.append(", studyLevel='").append(studyLevel).append('\'');
        sb.append(", tjbh='").append(tjbh).append('\'');
        sb.append(", doctorid='").append(doctorid).append('\'');
        sb.append(", tz='").append(tz).append('\'');
        sb.append(", tzs='").append(tzs).append('\'');
        sb.append(", birthDay='").append(birthDay).append('\'');
        sb.append(", headPicUrl='").append(headPicUrl).append('\'');
        sb.append(", job='").append(job).append('\'');
        sb.append(", nickName='").append(nickName).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", regTime='").append(regTime).append('\'');
        sb.append(", sex='").append(sex).append('\'');
        sb.append(", studylevel='").append(studylevel).append('\'');
        sb.append(", useraccount='").append(useraccount).append('\'');
        sb.append(", isMarried='").append(isMarried).append('\'');
        sb.append(", level=").append(level);
        sb.append(", state=").append(state);
        sb.append(", isFirstLogin=").append(isFisrtLogin);
        sb.append(", uid='").append(uid).append('\'');
        sb.append(", errorInfo='").append(errorInfo).append('\'');
        sb.append(", errno=").append(errno);
        sb.append('}');
        return sb.toString();
    }
}   
