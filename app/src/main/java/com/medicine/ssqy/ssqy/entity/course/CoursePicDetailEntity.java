package com.medicine.ssqy.ssqy.entity.course;

/**
 * Created by Administrator on 2016/12/12.
 */
public class CoursePicDetailEntity {
    
    /**
     * state : true
     * coursePic : 
     * courseUrl : 
     * uid : u002
     * courseTitle : 白露节气介绍
     * courseID : mx2017081918322401185
     * courseType : pic
     * courseLength : 0
     * courseDay : 1504656995000
     * coursehtmlUrl : http://106.3.41.199:8066/jeesite/app/zy/coursePicDetailAtricl/?resourceid=RS000158
     */
    
    private boolean state;
    private String coursePic;
    private String courseUrl;
    private String uid;
    private String courseTitle;
    private String courseID;
    private String courseType;
    private int courseLength;
    private long courseDay;
    private String coursehtmlUrl;
    
    public boolean isState() {
        return state;
    }
    
    public void setState(boolean state) {
        this.state = state;
    }
    
    public String getCoursePic() {
        return coursePic;
    }
    
    public void setCoursePic(String coursePic) {
        this.coursePic = coursePic;
    }
    
    public String getCourseUrl() {
        return courseUrl;
    }
    
    public void setCourseUrl(String courseUrl) {
        this.courseUrl = courseUrl;
    }
    
    public String getUid() {
        return uid;
    }
    
    public void setUid(String uid) {
        this.uid = uid;
    }
    
    public String getCourseTitle() {
        return courseTitle;
    }
    
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
    
    public String getCourseID() {
        return courseID;
    }
    
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
    
    public String getCourseType() {
        return courseType;
    }
    
    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }
    
    public int getCourseLength() {
        return courseLength;
    }
    
    public void setCourseLength(int courseLength) {
        this.courseLength = courseLength;
    }
    
    public long getCourseDay() {
        return courseDay;
    }
    
    public void setCourseDay(long courseDay) {
        this.courseDay = courseDay;
    }
    
    public String getCoursehtmlUrl() {
        return coursehtmlUrl;
    }
    
    public void setCoursehtmlUrl(String coursehtmlUrl) {
        this.coursehtmlUrl = coursehtmlUrl;
    }
}
