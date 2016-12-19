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
import com.medicine.ssqy.ssqy.entity.course.CourseVedioEntity;
import com.medicine.ssqy.ssqy.entity.course.CourseVedioListEntity;
import com.medicine.ssqy.ssqy.util.UtilTimeConvertS;

import java.util.List;

public class ItemLvVedioCourseAdapter extends BaseAdapter {

    private  List<CourseVedioListEntity> mEntities;

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemLvVedioCourseAdapter(Context context, List<CourseVedioListEntity> entities) {
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
    public CourseVedioListEntity getItem(int position) {
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
                if (itemViewType==CourseVedioListEntity.TYPE_TIME){
                    convertView = layoutInflater.inflate(R.layout.item_lv_vedio_time, parent,false);
                }
    
                if (itemViewType==CourseVedioListEntity.TYPE_COURSE){
                    convertView = layoutInflater.inflate(R.layout.item_lv_vedio_course, parent,false);
                }
             
                viewHolder=new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }else {
                viewHolder= (ViewHolder) convertView.getTag();
            }
    
        CourseVedioListEntity entity = getItem(position);
        if (itemViewType==CourseVedioListEntity.TYPE_TIME){
            viewHolder.mTextViewDivider.setText(entity.getTime());
            
        }
        if (itemViewType==CourseVedioListEntity.TYPE_COURSE){
//            viewHolder.imgvItemLvVedioCourse
            CourseVedioEntity.VedioCourseDataEntity courseVedioEntity = entity.getCourseVedioEntity();
            viewHolder.tvTimeItemLvVedioCourse.setText(UtilTimeConvertS.formatTime(courseVedioEntity.getCourseLength()));
            viewHolder.tvTitleItemLvVedioCourse.setText(courseVedioEntity.getCourseTitle());
            viewHolder.pbItemLvVedioCourse.setMax(courseVedioEntity.getCourseLength());
            viewHolder.pbItemLvVedioCourse.setProgress(courseVedioEntity.getCourseStudy());
        }
       
 
        return convertView;
    }

    
    protected class ViewHolder {
    private ImageView imgvItemLvVedioCourse;
    private TextView tvTitleItemLvVedioCourse;
    private TextView tvTimeItemLvVedioCourse;
    private NumberProgressBar pbItemLvVedioCourse;
    private TextView mTextViewDivider;

        public ViewHolder(View view) {
            try {
                imgvItemLvVedioCourse = (ImageView) view.findViewById(R.id.imgv_item_lv_vedio_course);
                tvTitleItemLvVedioCourse = (TextView) view.findViewById(R.id.tv_title_item_lv_vedio_course);
                tvTimeItemLvVedioCourse = (TextView) view.findViewById(R.id.tv_time_item_lv_vedio_course);
                pbItemLvVedioCourse = (NumberProgressBar) view.findViewById(R.id.pb_item_lv_vedio_course);
                mTextViewDivider= (TextView) view.findViewById(R.id.tv_time_divider_vedio);
            } catch (Exception e) {
            }
        }
    }
}
