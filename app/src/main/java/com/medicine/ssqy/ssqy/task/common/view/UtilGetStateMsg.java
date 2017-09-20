package com.medicine.ssqy.ssqy.task.common.view;


import com.medicine.ssqy.ssqy.task.common.model.TaskState;

/**
 * Created by Administrator on 2017/2/22.
 */

public class UtilGetStateMsg {
    
    public static String getStateMsg(int state){
        switch (state){
            case TaskState.TASK_STATE_WAIT:
                return "任务排队中...";
            case TaskState.TASK_STATE_PREPARE:
                return "准备中...";
            case TaskState.TASK_STATE_PAUSE:
                return "已暂停";
            case TaskState.TASK_STATE_SHA1_CHECKING:
                return "文件校验中...";
            case TaskState.TASK_STATE_DONE:
                return "已完成...";
            case TaskState.TASK_STATE_ERROR_NET:
                return "破网！！！";
            case TaskState.TASK_STATE_ERROR_SHA1:
                return "破文件！！！";
            case TaskState.TASK_STATE_ERROR_SERVER:
                return "破服务器！！！";
            case TaskState.TASK_STATE_ERROR_SD:
                return "破手机！！！";
            case TaskState.TASK_STATE_ING:
                return "正在传输数据。。。";
            default:
                return "呵呵";
        }
    }
}
