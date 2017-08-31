package com.medicine.ssqy.ssqy.entity.course;

import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */
public class CourseAudioEntity {
    
    /**
     * state : true
     * coursePic : http://192.168.1.25:10086/jeesite/resourceFiles/audio/null
     * courseUrl : http://192.168.1.25:10086/jeesite/resourceFiles/audio/jin_cui_hu_chun_xiao.ogg
     * uid : u201709010039260001
     * courseTitle : 翠湖春晓
     * courseID : mx201708281836050018
     * courseType : audio
     * courseLength : 229000
     * courseDay : 1503910806000
     * discussCount : 0
     * discuss : []
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
    private int discussCount;
    private List<?> discuss;
    
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
    
    public int getDiscussCount() {
        return discussCount;
    }
    
    public void setDiscussCount(int discussCount) {
        this.discussCount = discussCount;
    }
    
    public List<?> getDiscuss() {
        return discuss;
    }
    
    public void setDiscuss(List<?> discuss) {
        this.discuss = discuss;
    }
}
