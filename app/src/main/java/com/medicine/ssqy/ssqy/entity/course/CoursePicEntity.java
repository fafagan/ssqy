package com.medicine.ssqy.ssqy.entity.course;

import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */
public class CoursePicEntity {
    
    /**
     * state : true
     * uid : 10010
     * courseCount : 123
     * courseData : [{"courseTitle":"大保健","courseStudy":21123,"courseLength":122321,"courseID":11,"courseType":"pic","courseDay":"2016-12-19"}]
     */
    
    private boolean state;
    private int uid;
    private int courseCount;
    /**
     * courseTitle : 大保健
     * courseStudy : 21123
     * courseLength : 122321
     * courseID : 11
     * courseType : pic
     * courseDay : 2016-12-19
     */
    
    private List<PicCourseDataEntity> picCourseData;
    
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
    
    public List<PicCourseDataEntity> getCourseData() {
        return picCourseData;
    }
    
    public void setCourseData(List<PicCourseDataEntity> picCourseData) {
        this.picCourseData = picCourseData;
    }
    
    public static class PicCourseDataEntity {
        private String courseTitle;
        private int courseID;
        private  boolean courseLearned;
        private String courseType;
        private String courseDay;
        
        public String getCourseTitle() {
            return courseTitle;
        }
        
        public void setCourseTitle(String courseTitle) {
            this.courseTitle = courseTitle;
        }
    
        public boolean isCourseLearned() {
            return courseLearned;
        }
    
        public void setCourseLearned(boolean courseLearned) {
            this.courseLearned = courseLearned;
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
