package com.medicine.ssqy.ssqy.entity.course;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */
public class CoursePicListEntity {
    
    /**
     * uid : u002
     * state : true
     * courseCount : 9
     * picCourseData : [{"courseTitle":"白露节气介绍","courseStudy":0,"courseLength":0,"courseID":"mx2017081918322401185","courseType":"pic","courseDay":1504656995000,"courseLearned":false},{"courseTitle":"白露养生知识-心养","courseStudy":0,"courseLength":0,"courseID":"mx2017081918322401186","courseType":"pic","courseDay":1504865643000,"courseLearned":false},{"courseTitle":"白露养生知识-动养","courseStudy":0,"courseLength":0,"courseID":"mx2017081918322401187","courseType":"pic","courseDay":1504865643000,"courseLearned":false},{"courseTitle":"白露养生知识-气养","courseStudy":0,"courseLength":0,"courseID":"mx2017081918322401188","courseType":"pic","courseDay":1504865643000,"courseLearned":false},{"courseTitle":"白露养生知识-术养","courseStudy":0,"courseLength":0,"courseID":"mx2017081918322401189","courseType":"pic","courseDay":1504865643000,"courseLearned":false},{"courseTitle":"白露养生知识-药养","courseStudy":0,"courseLength":0,"courseID":"mx2017081918322401190","courseType":"pic","courseDay":1504865643000,"courseLearned":false},{"courseTitle":"白露养生知识-食养","courseStudy":0,"courseLength":0,"courseID":"mx2017081918322401191","courseType":"pic","courseDay":1504865643000,"courseLearned":false},{"courseTitle":"白露养生知识-居养","courseStudy":0,"courseLength":0,"courseID":"mx2017081918322401192","courseType":"pic","courseDay":1504865643000,"courseLearned":false},{"courseTitle":"白露不同体质养生原则","courseStudy":0,"courseLength":0,"courseID":"mx2017081918322401193","courseType":"pic","courseDay":1504656845000,"courseLearned":false}]
     */
    
    private String uid;
    private boolean state;
    private int courseCount;
    private List<PicCourseDataEntity> picCourseData;
    
    public String getUid() {
        return uid;
    }
    
    public void setUid(String uid) {
        this.uid = uid;
    }
    
    public boolean isState() {
        return state;
    }
    
    public void setState(boolean state) {
        this.state = state;
    }
    
    public int getCourseCount() {
        return courseCount;
    }
    
    public void setCourseCount(int courseCount) {
        this.courseCount = courseCount;
    }
    
    public List<PicCourseDataEntity> getPicCourseData() {
        return picCourseData;
    }
    
    public void setPicCourseData(List<PicCourseDataEntity> picCourseData) {
        this.picCourseData = picCourseData;
    }
    
    public static class PicCourseDataEntity implements Serializable {
        /**
         * courseTitle : 白露节气介绍
         * courseStudy : 0
         * courseLength : 0
         * courseID : mx2017081918322401185
         * courseType : pic
         * courseDay : 1504656995000
         * courseLearned : false
         */
        
        private String courseTitle;
        private int courseStudy;
        private int courseLength;
        private String courseID;
        private String courseType;
//        private long courseDay;
        private boolean courseLearned;
        
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
        
//        public long getCourseDay() {
//            return courseDay;
//        }
//        
//        public void setCourseDay(long courseDay) {
//            this.courseDay = courseDay;
//        }
        
        public boolean isCourseLearned() {
            return courseLearned;
        }
        
        public void setCourseLearned(boolean courseLearned) {
            this.courseLearned = courseLearned;
        }
    }
}
