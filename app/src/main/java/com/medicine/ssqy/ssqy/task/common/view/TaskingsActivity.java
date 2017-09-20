//package com.medicine.ssqy.ssqy.task.common.view;
//
//import android.content.Context;
//import android.content.Intent;
//import android.widget.ListView;
//
//import com.medicine.ssqy.ssqy.R;
//import com.medicine.ssqy.ssqy.base.KBaseActivity;
//import com.medicine.ssqy.ssqy.task.common.controller.TaskController;
//import com.medicine.ssqy.ssqy.task.common.controller.TaskMsg;
//import com.medicine.ssqy.ssqy.task.common.model.Task;
//import com.medicine.ssqy.ssqy.task.download.controller.DownloadController;
//import com.medicine.ssqy.ssqy.task.upload.controller.UploadController;
//
//import org.greenrobot.eventbus.EventBus;
//import org.greenrobot.eventbus.Subscribe;
//import org.greenrobot.eventbus.ThreadMode;
//
//import java.util.List;
//
//public class TaskingsActivity extends KBaseActivity {
//    private ListView mLvTaskActivity;
//    private TaskController mTasksController;
//    private List<Task> mTasksUnDone;
//    private ItemLvTaskingsActivityAdapter mLvTaskingsActivityAdapter;
//    public static final int FROM_DOWN=110;
//    public static final int FROM_UP=110;
//    public static int mFrom;
//    
//    public static void goToMe(Context context,int from){
//        Intent intent=new Intent(context,TaskingsActivity.class);
//        context.startActivity(intent);
//        mFrom=from;
//        
//    }
//    @Override
//    public int setRootView() {
//        return R.layout.activity_download_task;
//    }
//    
//    @Override
//    public void initViews() {
//        mLvTaskActivity = (ListView) findViewById(R.id.lv_download_task_activity);
//        EventBus.getDefault().register(this);
//        
//    }
//    
//    @Override
//    public void initDatas() {
//        if (mFrom==FROM_DOWN) {
//            mTasksController =new DownloadController();
//            setTitleCenter("下载任务序列");
//        }else {
//           mTasksController =new UploadController();
//            setTitleCenter("上传任务序列");
//        }
//        
//
//        mTasksUnDone = mTasksController.getTasksUnDone();
//    
//        mLvTaskingsActivityAdapter = new ItemLvTaskingsActivityAdapter(this,mTasksUnDone,mTasksController);
//        mLvTaskActivity.setAdapter(mLvTaskingsActivityAdapter);
//    }
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void receive(TaskMsg downloadMsg){
//        mLvTaskingsActivityAdapter.refreshDatas(downloadMsg);
//        downloadMsg.recycle();
//    }
//    
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        EventBus.getDefault().unregister(this);
//    }
//}
