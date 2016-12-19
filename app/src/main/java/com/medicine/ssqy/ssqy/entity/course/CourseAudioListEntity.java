package com.medicine.ssqy.ssqy.entity.course;

/**
 * Created by Administrator on 2016/12/12.
 */
public class CourseAudioListEntity {
    
    private String time;
    private CourseAudioEntity.AudioCourseDataEntity mCourseAudioEntity;
    public static final int TYPE_COURSE=0;
    public static final int TYPE_TIME=1;
    
    private int type=TYPE_COURSE;
    
    public void setTime(String time) {
        type=TYPE_TIME;
        this.time = time;
    }
    
    public void setCourseAudioEntity(CourseAudioEntity.AudioCourseDataEntity courseAudioEntity) {
        type=TYPE_COURSE;
        mCourseAudioEntity = courseAudioEntity;
    }
    
    public String getTime() {
        return time;
    }
    
    public int getType() {
        return type;
    }
    
    public CourseAudioEntity.AudioCourseDataEntity getCourseAudioEntity() {
        return mCourseAudioEntity;
    }
}
