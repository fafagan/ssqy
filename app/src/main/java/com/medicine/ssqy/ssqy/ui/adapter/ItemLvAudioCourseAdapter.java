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
import com.medicine.ssqy.ssqy.entity.course.CourseAudioEntity;
import com.medicine.ssqy.ssqy.entity.course.CourseAudioListEntity;
import com.medicine.ssqy.ssqy.util.UtilTimeConvertS;

import java.util.List;

public class ItemLvAudioCourseAdapter extends BaseAdapter {

    private  List<CourseAudioListEntity> mEntities;

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemLvAudioCourseAdapter(Context context, List<CourseAudioListEntity> entities) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.mEntities=entities;
    }
    
    @Override
    public int getViewTypeCount() {
        return 2;
    }
    
    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType();
    }
    
    @Override
    public int getCount() {
        return mEntities.size();
    }

    @Override
    public CourseAudioListEntity getItem(int position) {
        return mEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        int itemViewType = getItemViewType(position);
      
    
      
            if (convertView == null) {
                if (itemViewType==CourseAudioListEntity.TYPE_TIME){
                    convertView = layoutInflater.inflate(R.layout.item_lv_audio_time, parent,false);
                }
    
                if (itemViewType==CourseAudioListEntity.TYPE_COURSE){
                    convertView = layoutInflater.inflate(R.layout.item_lv_audio_course, parent,false);
                }
             
                viewHolder=new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }else {
                viewHolder= (ViewHolder) convertView.getTag();
            }
    
        CourseAudioListEntity entity = getItem(position);
        if (itemViewType==CourseAudioListEntity.TYPE_TIME){
            viewHolder.mTextViewDivider.setText(entity.getTime());
            
        }
        if (itemViewType==CourseAudioListEntity.TYPE_COURSE){
//            viewHolder.imgvItemLvAudioCourse
            CourseAudioEntity.AudioCourseDataEntity courseAudioEntity = entity.getCourseAudioEntity();
            viewHolder.tvTimeItemLvAudioCourse.setText(UtilTimeConvertS.formatTime(courseAudioEntity.getCourseLength()));
            viewHolder.tvTitleItemLvAudioCourse.setText(courseAudioEntity.getCourseTitle());
            viewHolder.pbItemLvAudioCourse.setMax(courseAudioEntity.getCourseLength());
            viewHolder.pbItemLvAudioCourse.setProgress(courseAudioEntity.getCourseStudy());
        }
       
 
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
