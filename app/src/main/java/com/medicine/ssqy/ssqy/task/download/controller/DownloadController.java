package com.medicine.ssqy.ssqy.task.download.controller;

import android.content.Intent;

import com.medicine.ssqy.ssqy.base.KBaseApp;
import com.medicine.ssqy.ssqy.common.utils.SavePathUtil;
import com.medicine.ssqy.ssqy.common.utils.SizeUtil;
import com.medicine.ssqy.ssqy.entity.course.CourseVedioDetailEntity;
import com.medicine.ssqy.ssqy.task.common.controller.NetState;
import com.medicine.ssqy.ssqy.task.common.controller.TaskController;
import com.medicine.ssqy.ssqy.task.common.controller.TaskMsg;
import com.medicine.ssqy.ssqy.task.common.controller.UpdatePart;
import com.medicine.ssqy.ssqy.task.common.model.TaskState;
import com.medicine.ssqy.ssqy.task.download.model.DownloadDaoConfig;
import com.medicine.ssqy.ssqy.task.download.model.DownloadTask;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2017/2/20.
 */

public class DownloadController implements TaskController<DownloadTask> {
    
    private DbManager.DaoConfig mDaoConfig;
    
    public DownloadController() {
        
        mDaoConfig = DownloadDaoConfig.getDownloadDaoConfig();
    }
    
    /**
     * 添加一堆任务到下载队列
     */
    public void addTasksToQueue(CourseVedioDetailEntity courseVedioDetailEntity) {

//        for (FileListEntity.DataEntity dataEntity : dataEntities) {
//            if (TextUtils.isEmpty(dataEntity.getCid())) {
//                //添加一个文件
        addFileTaskToQueue(courseVedioDetailEntity);
//            } else {
//                //添加一个文件夹
//                addDirTaskToQueue(dataEntity, pathEntities);
//            }
//        }
        
        startTasks();
        
    }

//    @Override
//    public void addTasksToQueue(List<SDEntity> sdEntities, String cid) {
//        
//    }
    
    
    private void addFileTaskToQueue(CourseVedioDetailEntity courseVedioDetailEntity) {
        
        DownloadTask downloadTask = new DownloadTask();
        downloadTask.setCourseID(courseVedioDetailEntity.getCourseID());
        downloadTask.setName(courseVedioDetailEntity.getCourseTitle());
        downloadTask.setTotalSize((int) courseVedioDetailEntity.getTotalSize());
        downloadTask.setSavaPath(SavePathUtil.getDirPath() + courseVedioDetailEntity.getCourseID());
        downloadTask.setSha1(courseVedioDetailEntity.getSha1());
        downloadTask.setTime(System.currentTimeMillis());
        downloadTask.setUrl(courseVedioDetailEntity.getCourseUrl());
        try {
            x.getDb(mDaoConfig).save(downloadTask);
        } catch (DbException e) {
            e.printStackTrace();
        }
        
    }
    
    
    protected void updateDownloadTaskState(DownloadTask downloadTask, int newState) {
        downloadTask.setState(newState);
        try {
            x.getDb(mDaoConfig).update(downloadTask, "state");
        } catch (DbException e) {
            e.printStackTrace();
        }
        
        TaskMsg downloadMsg = TaskMsg.obtain();
        downloadMsg.mUpdatePart = UpdatePart.STATE;
        downloadMsg.mTask = downloadTask;
        EventBus.getDefault().post(downloadMsg);
    }
    
    /**
     * @param downloadTask 哪个任务
     * @param newSize      又下载了多少
     */
    protected void updateDownloadTaskSize(DownloadTask downloadTask, int newSize) {
        downloadTask.setNowSize(downloadTask.getNowSize() + newSize);
        try {
            x.getDb(mDaoConfig).update(downloadTask, "nowSize", "nowSizeStr");
        } catch (DbException e) {
            e.printStackTrace();
        }
        TaskMsg downloadMsg = TaskMsg.obtain();
        downloadMsg.mUpdatePart = UpdatePart.SIZE;
        downloadMsg.mTask = downloadTask;
        EventBus.getDefault().post(downloadMsg);
    }
    
    /**
     * @param downloadTask 哪个任务
     * @param newSpeed     新速度
     */
    protected void updateDownloadTaskSpeed(DownloadTask downloadTask, String newSpeed) {
        downloadTask.setSpeed(newSpeed);
        try {
            x.getDb(mDaoConfig).update(downloadTask, "speed");
        } catch (DbException e) {
            e.printStackTrace();
        }
        TaskMsg downloadMsg = TaskMsg.obtain();
        downloadMsg.mUpdatePart = UpdatePart.SPEED;
        downloadMsg.mTask = downloadTask;
        EventBus.getDefault().post(downloadMsg);
    }
    
    /**
     * 重置下载量为0
     */
    
    protected void resetDownSize(DownloadTask downloadTask) {
        downloadTask.setNowSize(0);
        File file = new File(downloadTask.getSavaPath());
        file.delete();
        try {
            x.getDb(mDaoConfig).update(downloadTask, "nowSize", "nowSizeStr");
        } catch (DbException e) {
            e.printStackTrace();
        }
        TaskMsg downloadMsg = TaskMsg.obtain();
        downloadMsg.mUpdatePart = UpdatePart.SIZE;
        downloadMsg.mTask = downloadTask;
        EventBus.getDefault().post(downloadMsg);
    }
    
    /**
     * 用于正在执行的下载任务列表
     *
     * @return
     */
    public List<DownloadTask> getTasksUnDone() {
        try {
            return x.getDb(mDaoConfig).selector(DownloadTask.class).where("state", "<>", TaskState.TASK_STATE_DONE)
                    .and("state", "<>", TaskState.TASK_STATE_CANCEL).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        
        return null;
        
    }
    
    /**
     * 用于已完成的下载任务列表
     *
     * @return
     */
    public List<DownloadTask> getTasksDone() {
        try {
            return x.getDb(mDaoConfig).selector(DownloadTask.class).where("state", "==", TaskState.TASK_STATE_DONE)
                    .findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        
        return null;
        
    }
    
    /**
     * 找到一个处于排队的下载任务，我们把它拿去执行下载动作
     *
     * @return 处于排队的下载任务
     */
    public DownloadTask getDownloadTaskWait() {
//        try {
//            return x.getDb(mDaoConfig).selector(DownloadTask.class).where("state", "=", TaskState.TASK_STATE_WAIT).findFirst();
//        } catch (DbException e) {
//            e.printStackTrace();
//        }
        if (!NetState.sNetOK) {
            return null;
        }
        try {
            return x.getDb(mDaoConfig).selector(DownloadTask.class).where("state", "<>", TaskState.TASK_STATE_DONE)
                    .and("state", "<>", TaskState.TASK_STATE_CANCEL).and("state", "<>", TaskState.TASK_STATE_ING).and("state", "<>", TaskState.TASK_STATE_PREPARE).and("state", "<>", TaskState.TASK_STATE_SHA1_CHECKING)
                    .and("state", "<>", TaskState.TASK_STATE_ERROR_SD)
                    .findFirst();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
        
    }
    
    
    /**
     * 开始执行下载
     */
    public void startTasks() {
        Intent intent = new Intent(KBaseApp.mContextGlobal, DownloadService.class);
        KBaseApp.mContextGlobal.startService(intent);
    }
    
    /**
     * 删除指定任务
     *
     * @param downloadTask
     */
    public void cancelTask(DownloadTask downloadTask) {
        
        File file = new File(downloadTask.getSavaPath());
        file.delete();
        
        try {
            x.getDb(mDaoConfig).delete(downloadTask);
        } catch (DbException e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * 暂停指定任务
     *
     * @param downloadTask
     */
    public void pauseTask(DownloadTask downloadTask) {
        
        
        updateDownloadTaskState(downloadTask, TaskState.TASK_STATE_PAUSE);
        
    }
    
    /**
     * 暂停指定任务
     *
     * @param downloadTask
     */
    public void resumeTask(DownloadTask downloadTask) {
        
        updateDownloadTaskState(downloadTask, TaskState.TASK_STATE_WAIT);
        startTasks();
        
    }
    
    
    /**
     * 判断某个任务是否被删除
     */
    
    protected boolean hasCanceled(DownloadTask downloadTask) {
        try {
            DownloadTask task = x.getDb(mDaoConfig).findById(DownloadTask.class, downloadTask.getId());
            
            if (task == null) {
                return true;
            }
            
            if (task.getState() == TaskState.TASK_STATE_CANCEL) {
                return true;
            }
            return false;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * 判断某个任务是否被暂停
     */
    
    protected boolean haspaused(DownloadTask downloadTask) {
        try {
            DownloadTask task = x.getDb(mDaoConfig).findById(DownloadTask.class, downloadTask.getId());
            
            if (task == null) {
                return true;
            }
            
            if (task.getState() == TaskState.TASK_STATE_PAUSE) {
                return true;
            }
            return false;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * 查询当前正在执行的全部任务的存储需求
     */
    public int getTotalSizeIngRequire() {
        try {
            List<DownloadTask> downloadTasks =
                    x.getDb(mDaoConfig).
                            selector(DownloadTask.class).
                            where("state", "=", TaskState.TASK_STATE_ING).
                            findAll();
            
            if (downloadTasks == null) {
                return 0;
            }
            int total = 0;
            for (DownloadTask downloadTask : downloadTasks) {
                total += (downloadTask.getTotalSize() - downloadTask.getNowSize());
            }
            return total;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public String findCourseCacheUrl(String courseID) {
        try {
            Logger.e("courseID" + courseID);
            List<DownloadTask> courseID1 = x.getDb(mDaoConfig).selector(DownloadTask.class).where("courseID", "=", courseID).findAll();
            if (courseID1 == null) {
                return null;
            }
            for (DownloadTask downloadTask : courseID1) {
                Logger.e("downloadTask" + downloadTask.getState() + "  " + downloadTask.getTotalSize() + "  " + downloadTask.getNowSize());
                if (downloadTask.getState() == TaskState.TASK_STATE_DONE) {
                    return "file:///" + downloadTask.getSavaPath();
                }
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public boolean findCourseCacheIng(String courseID) {
        try {
            Logger.e("courseID" + courseID);
            List<DownloadTask> courseID1 = x.getDb(mDaoConfig).selector(DownloadTask.class).where("courseID", "=", courseID).findAll();
            return courseID1 != null && courseID1.size() != 0;
        } catch (DbException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public void removeAllCache() {
        
        try {
            Intent intent = new Intent(KBaseApp.mContextGlobal, DownloadService.class);
            KBaseApp.mContextGlobal.stopService(intent);
            List<DownloadTask> downloadTasks = x.getDb(mDaoConfig).findAll(DownloadTask.class);
            if (downloadTasks != null && downloadTasks.size() != 0) {
                for (DownloadTask downloadTask : downloadTasks) {
                    cancelTask(downloadTask);
                }
            }
    
            File file = new File(SavePathUtil.getDirPath());
            File[] files = file.listFiles();
            if (files != null && files.length != 0) {
                for (File file1 : files) {
                    file1.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getAllCache() {
        int total = 0;
        try {
            File file = new File(SavePathUtil.getDirPath());
            File[] files = file.listFiles();
            if (files != null && files.length != 0) {
                for (File file1 : files) {
                    total += file1.length();
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SizeUtil.formatSize(total);
    }
}
