package com.medicine.ssqy.ssqy.task.common.controller;

import com.medicine.ssqy.ssqy.entity.course.CourseVedioDetailEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/2/24.
 */

public interface TaskController<T> {
    
    public void addTasksToQueue(CourseVedioDetailEntity courseVedioDetailEntity) ;
//    public void addTasksToQueue(List<SDEntity> sdEntities, String cid) ;
    public List<T> getTasksUnDone() ;
    public List<T> getTasksDone() ;
    public void startTasks() ;
    public void cancelTask(T task) ;
    public void pauseTask(T task) ;
    public void resumeTask(T task) ;
    
}
