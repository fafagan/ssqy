//package com.medicine.ssqy.ssqy.task.common.view;
//
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.Color;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.daimajia.numberprogressbar.NumberProgressBar;
//import com.nostra13.universalimageloader.core.DisplayImageOptions;
//import com.nostra13.universalimageloader.core.ImageLoader;
//import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
//import com.orhanobut.logger.Logger;
//import com.szt20.yun.yun20.R;
//import com.szt20.yun.yun20.task.common.controller.TaskController;
//import com.szt20.yun.yun20.task.common.controller.TaskMsg;
//import com.szt20.yun.yun20.task.common.controller.UpdatePart;
//import com.szt20.yun.yun20.task.common.model.Task;
//import com.szt20.yun.yun20.task.common.model.TaskState;
//import com.szt20.yun.yun20.utils.IconUtil;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ItemLvTaskingsActivityAdapter extends BaseAdapter implements View.OnClickListener {
//    
//    private final DisplayImageOptions mOptions;
//    private List<Task> mEntities;
//    
//    private Context mContext;
//    private LayoutInflater layoutInflater;
//    private TaskController mTaskController;
//    //    private List<TextView> mTextViewsSpeed=new ArrayList<>();
////    private List<TextView> mTextViewsSize=new ArrayList<>();
//    private List<ViewHolder> mViewHolders = new ArrayList<>();
//    
//    public ItemLvTaskingsActivityAdapter(Context context, List<Task> entities, TaskController taskController) {
//        this.mContext = context;
//        this.layoutInflater = LayoutInflater.from(context);
//        this.mEntities = entities;
//        
//        mTaskController = taskController;
//        mOptions = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.file_more)
//                .showImageForEmptyUri(R.drawable.file_more)
//                .showImageOnFail(R.drawable.file_more)
//                .cacheInMemory(true)
//                .cacheOnDisk(true)
//                .considerExifParams(false)
//                .bitmapConfig(Bitmap.Config.RGB_565)
//                .displayer(new RoundedBitmapDisplayer(7))
//                .build();
//    }
//    
//    public void refreshDatas(TaskMsg downloadMsg) {
//        UpdatePart updatePart = downloadMsg.mUpdatePart;
//        Task downloadTask = downloadMsg.mTask;
//        
//        //1.更新原始数据
////        for (Task entity : mEntities) {
////            if (entity.getId()==downloadTask.getId()){
////                entity=downloadTask;
////                break;
////            }
////        }
//    
//        //1.更新原始数据
//        for (int i = 0; i < mEntities.size(); i++) {
//            if (mEntities.get(i).getId() == downloadTask.getId()) {
//                mEntities.set(i, downloadTask);
//                break;
//            }
//        }
//    
//        //2.找我们更新了的downloadTask的对应的viewHolder，进而从viewHolder中再取出对应控件更新数据
//        ViewHolder holderDes = null;
//        for (ViewHolder viewHolder : mViewHolders) {
//            Task entity = viewHolder.getEntity();
//            if (entity.getId() == downloadTask.getId()) {
//                holderDes = viewHolder;
//                break;
//            }
//        }
//        //不在界面上此时呈现的部分，我们不做局部刷新，等待其回到界面上的时候自然会重新加载一次的
//        if (holderDes == null) {
//            return;
//        }
//        
//        //3.更新界面数据
//        if (updatePart == UpdatePart.SPEED) {
//            
//            holderDes.tvSpeedItemLvDownloadActivity.setTextColor(Color.BLACK);
//            holderDes.tvSpeedItemLvDownloadActivity.setText(downloadTask.getSpeed());
//        }
//        if (updatePart == UpdatePart.SIZE) {
//            Logger.e("触发更新Size" + "  " + downloadTask.getTotalSize() + "  " + downloadTask.getNowSize());
//            holderDes.tvSizeItemLvDownloadActivity.setText(downloadTask.getNowSizeStr() + " / " + downloadTask.getTotalSizeStr());
//            holderDes.mNumberProgressBar.setProgress(downloadTask.getNowSize());
//        }
//        if (updatePart == UpdatePart.STATE) {
//            if (downloadTask.getState() == TaskState.TASK_STATE_CANCEL) {
//                mEntities.remove(downloadTask);
//                notifyDataSetChanged();
//            }
//            holderDes.tvSpeedItemLvDownloadActivity.setTextColor(Color.RED);
//            holderDes.tvSpeedItemLvDownloadActivity.setText(UtilGetStateMsg.getStateMsg(downloadTask.getState()));
//            
//            if (downloadTask.getState() == TaskState.TASK_STATE_WAIT || downloadTask.getState() == TaskState.TASK_STATE_PREPARE
//                    || downloadTask.getState() == TaskState.TASK_STATE_ING || downloadTask.getState() == TaskState.TASK_STATE_SHA1_CHECKING) {
//                holderDes.cbPrItemLvDownloadActivity.setChecked(false);
//                
//            } else {
//                holderDes.cbPrItemLvDownloadActivity.setChecked(true);
//            }
//        }
//        
//    }
//    
//    @Override
//    public int getCount() {
//        return mEntities.size();
//    }
//    
//    @Override
//    public Task getItem(int position) {
//        return mEntities.get(position);
//    }
//    
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//    
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if (convertView == null) {
//            convertView = layoutInflater.inflate(R.layout.item_lv_download_activity, parent, false);
//            convertView.setTag(new ViewHolder(convertView));
//        }
//        initializeViews((Task) getItem(position), (ViewHolder) convertView.getTag(), position);
//        return convertView;
//    }
//    
//    private void initializeViews(Task entity, ViewHolder holder, int position) {
//        //TODO implement
//        
//        if (TextUtils.isEmpty(entity.getU())) {
//            //加载本地图标
//            int icon = IconUtil.getDrawable(entity.getIco());
////                viewHolder.mImageViewIcon.setImageResource(icon);
//            ImageLoader.getInstance().displayImage("drawable://" + icon, holder.imgvIconItemLvDownloadActivity, mOptions);
//        } else {
//            //加载缩略图
//            ImageLoader.getInstance().displayImage(entity.getU(), holder.imgvIconItemLvDownloadActivity, mOptions);
//            
//        }
//        
//        holder.setEntity(entity);
//        holder.tvTitleItemLvDownloadActivity.setText(entity.getName());
//        holder.tvSizeItemLvDownloadActivity.setText(entity.getNowSizeStr() + " / " + entity.getTotalSizeStr());
//        //速度tv---如果处于下载中，那么就呈现速度，否则呈现当前状态
//        if (entity.getState() == TaskState.TASK_STATE_ING) {
//            holder.tvSpeedItemLvDownloadActivity.setTextColor(Color.BLACK);
//            holder.tvSpeedItemLvDownloadActivity.setText(entity.getSpeed());
//        } else {
//            holder.tvSpeedItemLvDownloadActivity.setTextColor(Color.RED);
//            holder.tvSpeedItemLvDownloadActivity.setText(UtilGetStateMsg.getStateMsg(entity.getState()));
//        }
//        holder.mNumberProgressBar.setMax(entity.getTotalSize());
//        holder.mNumberProgressBar.setProgress(entity.getNowSize());
//        
//        holder.cbPrItemLvDownloadActivity.setTag(entity);
//        holder.cbPrItemLvDownloadActivity.setOnClickListener(this);
//        //处理CB的滑动混乱
//        if (entity.getState() == TaskState.TASK_STATE_WAIT || entity.getState() == TaskState.TASK_STATE_PREPARE
//                || entity.getState() == TaskState.TASK_STATE_ING || entity.getState() == TaskState.TASK_STATE_SHA1_CHECKING) {
//            holder.cbPrItemLvDownloadActivity.setChecked(false);
//            
//        } else {
//            holder.cbPrItemLvDownloadActivity.setChecked(true);
//        }
//        
//        holder.btCancelItemLvDownloadActivity.setTag(entity);
//        holder.btCancelItemLvDownloadActivity.setOnClickListener(this);
//    }
//    
//    @Override
//    public void onClick(View v) {
//        Task task = (Task) v.getTag();
//        if (v.getId() == R.id.cb_pr_item_lv_download_activity) {
//            CheckBox cb = (CheckBox) v;
//            if (cb.isChecked()) {
//                //暂停该任务
//                mTaskController.pauseTask(task);
//            } else {
//                mTaskController.resumeTask(task);
//            }
//        }
//        if (v.getId() == R.id.bt_cancel_item_lv_download_activity) {
//            mTaskController.cancelTask(task);
//            
//        }
//    }
//    
//    protected class ViewHolder {
//        private ImageView imgvIconItemLvDownloadActivity;
//        private TextView tvTitleItemLvDownloadActivity;
//        private TextView tvSizeItemLvDownloadActivity;
//        private TextView tvSpeedItemLvDownloadActivity;
//        private CheckBox cbPrItemLvDownloadActivity;
//        private Button btCancelItemLvDownloadActivity;
//        private NumberProgressBar mNumberProgressBar;
//        private Task entity;
//        
//        public ViewHolder(View view) {
//            imgvIconItemLvDownloadActivity = (ImageView) view.findViewById(R.id.imgv_icon_item_lv_download_activity);
//            tvTitleItemLvDownloadActivity = (TextView) view.findViewById(R.id.tv_title_item_lv_download_activity);
//            tvSizeItemLvDownloadActivity = (TextView) view.findViewById(R.id.tv_size_item_lv_download_activity);
//            tvSpeedItemLvDownloadActivity = (TextView) view.findViewById(R.id.tv_speed_item_lv_download_activity);
//            cbPrItemLvDownloadActivity = (CheckBox) view.findViewById(R.id.cb_pr_item_lv_download_activity);
//            btCancelItemLvDownloadActivity = (Button) view.findViewById(R.id.bt_cancel_item_lv_download_activity);
//            mNumberProgressBar = (NumberProgressBar) view.findViewById(R.id.pb_item_lv_download_activity);
//            mViewHolders.add(this);
//        }
//        
//        
//        public Task getEntity() {
//            return entity;
//        }
//        
//        public void setEntity(Task entity) {
//            this.entity = entity;
//        }
//    }
//}
