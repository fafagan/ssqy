package com.medicine.ssqy.ssqy.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/15.
 */
public class CourseData {
    
    private String courseTitle;
    private String courseDetail;
    private boolean courseLearned;
    private int courseID;
    private String courseType;
    private int courseStudy;
    private int courseLength;
    private String courseDay;
    
    public static List<CourseData> getDatasToday(){
        
        List<CourseData> courseDatas=new ArrayList<>();
        for (int i = 1; i <=7; i++) {
            CourseData courseData=new CourseData();
            courseData.setCourseTitle("今天学习的课程"+i);
            if (i%2==0){
                courseData.setCourseLearned(true);
            }else {
                courseData.setCourseLength(10000);
                courseData.setCourseStudy(5000);
            }
            courseDatas.add(courseData);
        }
    return courseDatas;
        
    }
    public static List<CourseData> getDatasWeek(){
        Map<String,List<CourseData>> map=new HashMap<>();
        List<CourseData> courseDatas=new ArrayList<>();
        for (int i = 1; i <=7; i++) {
            CourseData courseData=new CourseData();
            courseData.setCourseTitle("今天学习的课程"+i);
            if (i%2==0){
                courseData.setCourseLearned(true);
            }else {
                courseData.setCourseLength(10000);
                courseData.setCourseStudy(5000);
            }
            courseDatas.add(courseData);
        }
        return courseDatas;
    }
    public String getCourseTitle() {
        return courseTitle;
    }
    
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
    
    public String getCourseDetail() {
        return courseDetail;
    }
    
    public void setCourseDetail(String courseDetail) {
        this.courseDetail = courseDetail;
    }
    
    public boolean getCourseLearned() {
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
    
    public String getCourseDay() {
        return courseDay;
    }
    
    public void setCourseDay(String courseDay) {
        this.courseDay = courseDay;
    }
}
