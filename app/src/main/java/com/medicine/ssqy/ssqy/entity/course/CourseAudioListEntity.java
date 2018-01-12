package com.medicine.ssqy.ssqy.entity.course;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */
public class CourseAudioListEntity {
    
    
    /**
     * uid : u201709010039260001
     * state : true
     * courseCount : 10
     * audioCourseData : [{"courseTitle":"到春来","courseStudy":0,"courseLength":184000,"courseID":"mx201708281835330017","courseType":"audio","courseDay":1503910806000,"courseLearned":false},{"courseTitle":"翠湖春晓","courseStudy":0,"courseLength":229000,"courseID":"mx201708281836050018","courseType":"audio","courseDay":1503910806000,"courseLearned":false},{"courseTitle":"江河水","courseStudy":0,"courseLength":457000,"courseID":"mx201708281836400019","courseType":"audio","courseDay":1503910806000,"courseLearned":false},{"courseTitle":"三六","courseStudy":0,"courseLength":365000,"courseID":"mx201708281828150010","courseType":"audio","courseDay":1503910806000,"courseLearned":false},{"courseTitle":"鸥鹭忘机","courseStudy":0,"courseLength":404000,"courseID":"mx201708281834040016","courseType":"audio","courseDay":1503910806000,"courseLearned":false},{"courseTitle":"四合如意","courseStudy":0,"courseLength":959000,"courseID":"mx201708281832500013","courseType":"audio","courseDay":1503910806000,"courseLearned":false},{"courseTitle":"迎宾客","courseStudy":0,"courseLength":237000,"courseID":"mx201708281833330015","courseType":"audio","courseDay":1503910806000,"courseLearned":false},{"courseTitle":"云庆","courseStudy":0,"courseLength":255000,"courseID":"mx201708281828510011","courseType":"audio","courseDay":1503910806000,"courseLearned":false},{"courseTitle":"紫竹调","courseStudy":0,"courseLength":214000,"courseID":"mx201708281833120014","courseType":"audio","courseDay":1503910806000,"courseLearned":false},{"courseTitle":"行街","courseStudy":0,"courseLength":326000,"courseID":"mx201708281829230012","courseType":"audio","courseDay":1503910806000,"courseLearned":false}]
     */
    
    private String uid;
    private boolean state;
    private int courseCount;
    private List<AudioCourseDataEntity> audioCourseData;
    
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
    
    public List<AudioCourseDataEntity> getAudioCourseData() {
        return audioCourseData;
    }
    
    public void setAudioCourseData(List<AudioCourseDataEntity> audioCourseData) {
        this.audioCourseData = audioCourseData;
    }
    
    public static class AudioCourseDataEntity implements Serializable {
        /**
         * courseTitle : 到春来
         * courseStudy : 0
         * courseLength : 184000
         * courseID : mx201708281835330017
         * courseType : audio
         * courseDay : 1503910806000
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
