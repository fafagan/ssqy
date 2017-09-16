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
    /**
     * courseDetail : 现代心理研究认为，音乐的频率、强度和节奏传入人体的听觉中枢神经后，可以引起各种不同的共鸣反应，激发机体的潜能，使某些部位相应地由静止状态而转变为动态，以发挥一种物理性的治疗作用。特别是中国的古典音乐，曲调柔和舒缓，旋律优美动听，能使人忘却烦恼，开阔胸襟，促进身心健康。 近年来，中华中医学会著名的张辰赓教授在医疗保健工作中运用民族乐曲，受到道教和佛教的思想熏陶，追寻吐故纳新，天人合一的至高境界，开出“音乐处方”，让身心得以调整，达到最佳的心理平衡状态。
     */
    
    private String courseDetail;
    /**
     * courseLearned : false
     * courseStudy : 1234
     */
    
    private boolean courseLearned;
    private int courseStudy;
    
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
    
    public String getCourseDetail() {
        return courseDetail;
    }
    
    public void setCourseDetail(String courseDetail) {
        this.courseDetail = courseDetail;
    }
    
    public boolean isCourseLearned() {
        return courseLearned;
    }
    
    public void setCourseLearned(boolean courseLearned) {
        this.courseLearned = courseLearned;
    }
    
    public int getCourseStudy() {
        return courseStudy;
    }
    
    public void setCourseStudy(int courseStudy) {
        this.courseStudy = courseStudy;
    }
}
