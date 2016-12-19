package com.medicine.ssqy.ssqy.entity.course;

/**
 * Created by Administrator on 2016/12/12.
 */
public class CoursePicListEntity {
    
    private String time;
    private CoursePicEntity.PicCourseDataEntity mCoursePicEntity;
    public static final int TYPE_COURSE=0;
    public static final int TYPE_TIME=1;
    
    private int type=TYPE_COURSE;
    
    public void setTime(String time) {
        type=TYPE_TIME;
        this.time = time;
    }
    
    public void setCoursePicEntity(CoursePicEntity.PicCourseDataEntity coursePicEntity) {
        type=TYPE_COURSE;
        mCoursePicEntity = coursePicEntity;
    }
    
    public String getTime() {
        return time;
    }
    
    public int getType() {
        return type;
    }
    
    public CoursePicEntity.PicCourseDataEntity getCoursePicEntity() {
        return mCoursePicEntity;
    }
}
