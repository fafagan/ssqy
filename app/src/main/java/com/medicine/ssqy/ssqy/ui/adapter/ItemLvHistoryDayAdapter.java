package com.medicine.ssqy.ssqy.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daimajia.numberprogressbar.NumberProgressBar;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.entity.CourseHistory;

import java.util.List;

public class ItemLvHistoryDayAdapter extends BaseAdapter {

    private  List<CourseHistory.CourseDataEntity> mEntities;

    private Context context;
    private LayoutInflater layoutInflater;
    private int[] tempPics={R.drawable.temp_bg1,R.drawable.temp_bg2,R.drawable.img_vedio,R.drawable.temp_bg4};
    public ItemLvHistoryDayAdapter(Context context, List<CourseHistory.CourseDataEntity> entities) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.mEntities=entities;
    }
    
    public void setEntities(List<CourseHistory.CourseDataEntity> entities) {
        mEntities = entities;
        notifyDataSetChanged();
    }
    
    @Override
    public int getCount() {
        return mEntities.size();
    }

    @Override
    public CourseHistory.CourseDataEntity getItem(int position) {
        return mEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_lv_history_day, parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((CourseHistory.CourseDataEntity)getItem(position), (ViewHolder) convertView.getTag(),position);
        return convertView;
    }

    private void initializeViews(CourseHistory.CourseDataEntity entity, ViewHolder holder,int position) {
        //TODO implement
        Glide.with(context).load(tempPics[position%4]).into(holder.imgvItemLvHistoryDay);
        holder.tvTitleItemLvHistoryDay.setText(entity.getCourseTitle());
        if (!"pic".equalsIgnoreCase(entity.getCourseType())){
            holder.pbItemLvAudioCourse.setMax(entity.getCourseLength());
            holder.pbItemLvAudioCourse.setProgress(entity.getCourseStudy());
        }else {
            holder.pbItemLvAudioCourse.setMax(100);
            holder.pbItemLvAudioCourse.setProgress(100);
        }
    }

    protected class ViewHolder {
         private ImageView imgvItemLvHistoryDay;
         private TextView tvTitleItemLvHistoryDay;
         private NumberProgressBar pbItemLvAudioCourse;

        public ViewHolder(View view) {
            imgvItemLvHistoryDay = (ImageView) view.findViewById(R.id.imgv_item_lv_history_day);
            tvTitleItemLvHistoryDay = (TextView) view.findViewById(R.id.tv_title_item_lv_history_day);
            pbItemLvAudioCourse = (NumberProgressBar) view.findViewById(R.id.pb_item_lv_history_day);
        }
    }
}
