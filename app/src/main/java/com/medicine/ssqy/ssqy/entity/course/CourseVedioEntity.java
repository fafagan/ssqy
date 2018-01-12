package com.medicine.ssqy.ssqy.entity.course;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */
public class CourseVedioEntity {
    /**
     * uid : u002
     * state : true
     * courseCount : 1
     * videoCourseData : [{"courseTitle":"六字诀","courseStudy":0,"courseLength":907000,"courseID":"mx201709030929010002","courseType":"video","courseDay":1504617017000,"courseLearned":false}]
     */
    
    private String uid;
    private boolean state;
    private int courseCount;
    private List<VideoCourseDataEntity> videoCourseData;
    
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
    
    public List<VideoCourseDataEntity> getVideoCourseData() {
        return videoCourseData;
    }
    
    public void setVideoCourseData(List<VideoCourseDataEntity> videoCourseData) {
        this.videoCourseData = videoCourseData;
    }
    
    public static class VideoCourseDataEntity implements Serializable{
        /**
         * courseTitle : 六字诀
         * courseStudy : 0
         * courseLength : 907000
         * courseID : mx201709030929010002
         * courseType : video
         * courseDay : 1504617017000
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
    
    /**
     * state : true
     * uid : 10010
     * courseCount : 123
     * courseData : [{"courseTitle":"大保健","courseStudy":21123,"courseLength":122321,"courseID":11,"courseType":"audio","courseDay":"2016-12-19"}]
     */
//    
//    private boolean state;
//    private int uid;
//    private int courseCount;
//    /**
//     * courseTitle : 大保健
//     * courseStudy : 21123
//     * courseLength : 122321
//     * courseID : 11
//     * courseType : audio
//     * courseDay : 2016-12-19
//     */
//    
//    private List<VedioCourseDataEntity> vedioCourseData;
//    
//    public boolean isState() {
//        return state;
//    }
//    
//    public void setState(boolean state) {
//        this.state = state;
//    }
//    
//    public int getUid() {
//        return uid;
//    }
//    
//    public void setUid(int uid) {
//        this.uid = uid;
//    }
//    
//    public int getCourseCount() {
//        return courseCount;
//    }
//    
//    public void setCourseCount(int courseCount) {
//        this.courseCount = courseCount;
//    }
//    
//    public List<VedioCourseDataEntity> getCourseData() {
//        return vedioCourseData;
//    }
//    
//    public void setCourseData(List<VedioCourseDataEntity> vedioCourseData) {
//        this.vedioCourseData = vedioCourseData;
//    }
//    
//    public static class VedioCourseDataEntity {
//        private String courseTitle;
//        private int courseStudy;
//        private int courseLength;
//        private int courseID;
//        private String courseType;
//        private String courseDay;
//        
//        public String getCourseTitle() {
//            return courseTitle;
//        }
//        
//        public void setCourseTitle(String courseTitle) {
//            this.courseTitle = courseTitle;
//        }
//        
//        public int getCourseStudy() {
//            return courseStudy;
//        }
//        
//        public void setCourseStudy(int courseStudy) {
//            this.courseStudy = courseStudy;
//        }
//        
//        public int getCourseLength() {
//            return courseLength;
//        }
//        
//        public void setCourseLength(int courseLength) {
//            this.courseLength = courseLength;
//        }
//        
//        public int getCourseID() {
//            return courseID;
//        }
//        
//        public void setCourseID(int courseID) {
//            this.courseID = courseID;
//        }
//        
//        public String getCourseType() {
//            return courseType;
//        }
//        
//        public void setCourseType(String courseType) {
//            this.courseType = courseType;
//        }
//        
//        public String getCourseDay() {
//            return courseDay;
//        }
//        
//        public void setCourseDay(String courseDay) {
//            this.courseDay = courseDay;
//        }
//    }
}
