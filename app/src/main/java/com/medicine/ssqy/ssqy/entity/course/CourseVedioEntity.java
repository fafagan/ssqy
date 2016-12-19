package com.medicine.ssqy.ssqy.entity.course;

import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */
public class CourseVedioEntity {
    
    /**
     * state : true
     * uid : 10010
     * courseCount : 123
     * courseData : [{"courseTitle":"大保健","courseStudy":21123,"courseLength":122321,"courseID":11,"courseType":"audio","courseDay":"2016-12-19"}]
     */
    
    private boolean state;
    private int uid;
    private int courseCount;
    /**
     * courseTitle : 大保健
     * courseStudy : 21123
     * courseLength : 122321
     * courseID : 11
     * courseType : audio
     * courseDay : 2016-12-19
     */
    
    private List<VedioCourseDataEntity> vedioCourseData;
    
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
    
    public int getCourseCount() {
        return courseCount;
    }
    
    public void setCourseCount(int courseCount) {
        this.courseCount = courseCount;
    }
    
    public List<VedioCourseDataEntity> getCourseData() {
        return vedioCourseData;
    }
    
    public void setCourseData(List<VedioCourseDataEntity> vedioCourseData) {
        this.vedioCourseData = vedioCourseData;
    }
    
    public static class VedioCourseDataEntity {
        private String courseTitle;
        private int courseStudy;
        private int courseLength;
        private int courseID;
        private String courseType;
        private String courseDay;
        
        public String getCourseTitle() {
            return courseTitle;
        }
        
        public void setCourseTitle(String courseTitle) {
            this.courseTitle = courseTitle;
        }
        
        public int getCourseStudy() {
            return courseStudy;
        }
        
        public void setCourseStudy(int courseStudy) {
            this.courseStudy = courseStudy;
        }
        
        public int getCourseLength() {
            return courseLength;
        }
        
        public void setCourseLength(int courseLength) {
            this.courseLength = courseLength;
        }
        
        public int getCourseID() {
            return courseID;
        }
        
        public void setCourseID(int courseID) {
            this.courseID = courseID;
        }
        
        public String getCourseType() {
            return courseType;
        }
        
        public void setCourseType(String courseType) {
            this.courseType = courseType;
        }
        
        public String getCourseDay() {
            return courseDay;
        }
        
        public void setCourseDay(String courseDay) {
            this.courseDay = courseDay;
        }
    }
}
