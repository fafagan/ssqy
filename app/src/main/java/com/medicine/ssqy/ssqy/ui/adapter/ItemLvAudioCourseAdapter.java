package com.medicine.ssqy.ssqy.ui.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.entity.course.CourseAudioListEntity;
import com.medicine.ssqy.ssqy.util.UtilTimeConvertS;

import java.util.List;

public class ItemLvAudioCourseAdapter extends BaseAdapter {

    private  List<CourseAudioListEntity.AudioCourseDataEntity> mEntities;

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemLvAudioCourseAdapter(Context context, List<CourseAudioListEntity.AudioCourseDataEntity> entities) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.mEntities=entities;
    }
    
    public void setEntities(List<CourseAudioListEntity.AudioCourseDataEntity> entities) {
        mEntities = entities;
        notifyDataSetChanged();
    }
    public void addEntities(List<CourseAudioListEntity.AudioCourseDataEntity> entities) {
        mEntities .addAll(entities) ;
        notifyDataSetChanged();
    }
    
    @Override
    public int getCount() {
        return mEntities.size();
    }

    @Override
    public CourseAudioListEntity.AudioCourseDataEntity getItem(int position) {
        return mEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
      
            if (convertView == null) {
    
                convertView = layoutInflater.inflate(R.layout.item_lv_audio_course, parent,false);
                viewHolder=new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }else {
                viewHolder= (ViewHolder) convertView.getTag();
            }
    
        CourseAudioListEntity.AudioCourseDataEntity courseAudioEntity = getItem(position);
//            viewHolder.imgvItemLvAudioCourse
            viewHolder.tvTimeItemLvAudioCourse.setText(UtilTimeConvertS.formatTime(courseAudioEntity.getCourseLength()));
            viewHolder.tvTitleItemLvAudioCourse.setText(courseAudioEntity.getCourseTitle());
            viewHolder.pbItemLvAudioCourse.setMax(courseAudioEntity.getCourseLength());
            viewHolder.pbItemLvAudioCourse.setProgress(courseAudioEntity.getCourseStudy());
       
 
        return convertView;
    }

    
    protected class ViewHolder {
    private ImageView imgvItemLvAudioCourse;
    private TextView tvTitleItemLvAudioCourse;
    private TextView tvTimeItemLvAudioCourse;
    private NumberProgressBar pbItemLvAudioCourse;
    private TextView mTextViewDivider;

        public ViewHolder(View view) {
            try {
                imgvItemLvAudioCourse = (ImageView) view.findViewById(R.id.imgv_item_lv_audio_course);
                tvTitleItemLvAudioCourse = (TextView) view.findViewById(R.id.tv_title_item_lv_audio_course);
                tvTimeItemLvAudioCourse = (TextView) view.findViewById(R.id.tv_time_item_lv_audio_course);
                pbItemLvAudioCourse = (NumberProgressBar) view.findViewById(R.id.pb_item_lv_audio_course);
                mTextViewDivider= (TextView) view.findViewById(R.id.tv_time_divider_audio);
            } catch (Exception e) {
            }
        }
    }
}
