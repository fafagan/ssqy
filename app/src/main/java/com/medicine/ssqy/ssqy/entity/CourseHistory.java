package com.medicine.ssqy.ssqy.entity;

import java.util.List;

/**
 * Created by Administrator on 2017-09-21.
 */

public class CourseHistory {
    
    /**
     * uid : u002
     * state : true
     * courseTime : 0
     * courseData : [{"courseTitle":"六字诀","courseStudy":586734,"courseLength":907000,"courseID":"mx201709030929010002","courseType":"video","courseDay":1505742364000,"courseDetail":"","courseLearned":true},{"courseTitle":"六字诀","courseStudy":180638,"courseLength":907000,"courseID":"mx201709030929010002","courseType":"video","courseDay":1505742364000,"courseDetail":"","courseLearned":false},{"courseTitle":"断点测试","courseStudy":239752,"courseLength":241000,"courseID":"mx201709202038470001","courseType":"video","courseDay":1505911129000,"courseDetail":"","courseLearned":true},{"courseTitle":"断点测试","courseStudy":68155,"courseLength":241000,"courseID":"mx201709202038470001","courseType":"video","courseDay":1505911129000,"courseDetail":"","courseLearned":true},{"courseTitle":"八段锦","courseStudy":4184,"courseLength":720000,"courseID":"mx201709101931040002","courseType":"video","courseDay":1505742364000,"courseDetail":"","courseLearned":true},{"courseTitle":"八段锦","courseStudy":19207,"courseLength":720000,"courseID":"mx201709101931040002","courseType":"video","courseDay":1505742364000,"courseDetail":"","courseLearned":false},{"courseTitle":"白露节气介绍","courseLength":0,"courseID":"mx2017081918322401185","courseType":"pic","courseDay":1505742364000,"courseDetail":"白露节气介绍","courseLearned":true},{"courseTitle":"白露养生知识-心养","courseLength":0,"courseID":"mx2017081918322401186","courseType":"pic","courseDay":1505742364000,"courseDetail":"白露养生知识-心养","courseLearned":true},{"courseTitle":"白露养生知识-动养","courseLength":0,"courseID":"mx2017081918322401187","courseType":"pic","courseDay":1505742364000,"courseDetail":"白露养生知识-动养","courseLearned":true},{"courseTitle":"白露养生知识-食养","courseLength":0,"courseID":"mx2017081918322401191","courseType":"pic","courseDay":1505742364000,"courseDetail":"白露养生知识-食养","courseLearned":true},{"courseTitle":"白露不同体质养生原则","courseLength":0,"courseID":"mx2017081918322401193","courseType":"pic","courseDay":1505742364000,"courseDetail":"白露不同体质养生原则","courseLearned":true},{"courseTitle":"翠湖春晓","courseStudy":7481,"courseLength":229000,"courseID":"mx201708281836050018","courseType":"audio","courseDay":1505742364000,"courseDetail":"","courseLearned":true},{"courseTitle":"翠湖春晓","courseStudy":25712,"courseLength":229000,"courseID":"mx201708281836050018","courseType":"audio","courseDay":1505742364000,"courseDetail":"","courseLearned":false},{"courseTitle":"三六","courseStudy":364870,"courseLength":365000,"courseID":"mx201708281828150010","courseType":"audio","courseDay":1505742364000,"courseDetail":"","courseLearned":true},{"courseTitle":"三六","courseStudy":253074,"courseLength":365000,"courseID":"mx201708281828150010","courseType":"audio","courseDay":1505742364000,"courseDetail":"","courseLearned":true},{"courseTitle":"江河水","courseStudy":14923,"courseLength":457000,"courseID":"mx201708281836400019","courseType":"audio","courseDay":1505742364000,"courseDetail":"","courseLearned":false},{"courseTitle":"迎宾客","courseStudy":9653,"courseLength":237000,"courseID":"mx201708281833330015","courseType":"audio","courseDay":1505742364000,"courseDetail":"","courseLearned":false},{"courseTitle":"迎宾客","courseStudy":24876,"courseLength":237000,"courseID":"mx201708281833330015","courseType":"audio","courseDay":1505742364000,"courseDetail":"","courseLearned":false},{"courseTitle":"紫竹调","courseStudy":11303,"courseLength":214000,"courseID":"mx201708281833120014","courseType":"audio","courseDay":1505742364000,"courseDetail":"","courseLearned":true},{"courseTitle":"紫竹调","courseStudy":140448,"courseLength":214000,"courseID":"mx201708281833120014","courseType":"audio","courseDay":1505742364000,"courseDetail":"","courseLearned":true}]
     */
    
    private String uid;
    private boolean state;
    private int courseTime;
    private List<CourseDataEntity> courseData;
    
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
    
    public int getCourseTime() {
        return courseTime;
    }
    
    public void setCourseTime(int courseTime) {
        this.courseTime = courseTime;
    }
    
    public List<CourseDataEntity> getCourseData() {
        return courseData;
    }
    
    public void setCourseData(List<CourseDataEntity> courseData) {
        this.courseData = courseData;
    }
    
    public static class CourseDataEntity {
        /**
         * courseTitle : 六字诀
         * courseStudy : 586734
         * courseLength : 907000
         * courseID : mx201709030929010002
         * courseType : video
         * courseDay : 1505742364000
         * courseDetail : 
         * courseLearned : true
         */
        
        private String courseTitle;
        private int courseStudy;
        private int courseLength;
        private String courseID;
        private String courseType;
        private long courseDay;
        private String courseDetail;
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
        
        public long getCourseDay() {
            return courseDay;
        }
        
        public void setCourseDay(long courseDay) {
            this.courseDay = courseDay;
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
    }
}
