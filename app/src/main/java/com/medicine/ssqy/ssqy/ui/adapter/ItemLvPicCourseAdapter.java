package com.medicine.ssqy.ssqy.ui.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.entity.course.CoursePicListEntity;

import java.util.List;

public class ItemLvPicCourseAdapter extends BaseAdapter {
    
    private List<CoursePicListEntity.PicCourseDataEntity> mEntities;
    
    private Context context;
    private LayoutInflater layoutInflater;
    
    public ItemLvPicCourseAdapter(Context context, List<CoursePicListEntity.PicCourseDataEntity> entities) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.mEntities = entities;
    }
    
    public void setEntities(List<CoursePicListEntity.PicCourseDataEntity> entities) {
        mEntities = entities;
        notifyDataSetChanged();
    }

//    @Override
//    public int getViewTypeCount() {
//        return 2;
//    }
//    
//    @Override
//    public int getItemViewType(int position) {
//        return getItem(position).getType();
//    }
    
    @Override
    public int getCount() {
        return mEntities.size();
    }
    
    @Override
    public CoursePicListEntity.PicCourseDataEntity getItem(int position) {
        return mEntities.get(position);
    }
    
    @Override
    public long getItemId(int position) {
        return position;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
//        int itemViewType = getItemViewType(position);
        
        
        if (convertView == null) {
//                if (itemViewType==CoursePicListEntity.PicCourseDataEntity.TYPE_TIME){
//                    convertView = layoutInflater.inflate(R.layout.item_lv_pic_time, parent,false);
//                }
//    
            //   if (itemViewType==CoursePicListEntity.PicCourseDataEntity.TYPE_COURSE){
            convertView = layoutInflater.inflate(R.layout.item_lv_pic_course, parent, false);
            //    }
            
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        
        CoursePicListEntity.PicCourseDataEntity entity = getItem(position);
//        if (itemViewType==CoursePicListEntity.PicCourseDataEntity.TYPE_TIME){
//            viewHolder.mTextViewDivider.setText(entity.getTime());
//            
//        }
        // if (itemViewType==CoursePicListEntity.PicCourseDataEntity.TYPE_COURSE){
//            viewHolder.imgvItemLvPicCourse
        ;
        viewHolder.tvTitleItemLvPicCourse.setText(entity.getCourseTitle());
        if (entity.isCourseLearned()) {
            viewHolder.tvLearnedItemLvPicCourse.setText("已学过！");
            viewHolder.mLayoutCourseLearned.setVisibility(View.VISIBLE);
            
        } else {
            viewHolder.tvLearnedItemLvPicCourse.setText("该养生图文您尚未学习呦！！");
            viewHolder.mLayoutCourseLearned.setVisibility(View.GONE);
        }
        
        return convertView;
    }
    
    
    protected class ViewHolder {
        private ImageView imgvItemLvPicCourse;
        private ImageView imgvItemLvLearned;
        private TextView tvTitleItemLvPicCourse;
        private TextView tvLearnedItemLvPicCourse;
        private TextView mTextViewDivider;
        private LinearLayout mLayoutCourseLearned;
        
        public ViewHolder(View view) {
            try {
                
                imgvItemLvPicCourse = (ImageView) view.findViewById(R.id.imgv_item_lv_pic_course);
//                imgvItemLvLearned = (ImageView) view.findViewById(R.id.imgv_learned_item_lv_pic_course);
                tvTitleItemLvPicCourse = (TextView) view.findViewById(R.id.tv_title_item_lv_pic_course);
                tvLearnedItemLvPicCourse = (TextView) view.findViewById(R.id.tv_learned_item_lv_pic_course);
                mTextViewDivider = (TextView) view.findViewById(R.id.tv_time_divider_pic);
                mLayoutCourseLearned = (LinearLayout) view.findViewById(R.id.layout_course_learned);
            } catch (Exception e) {
            }
        }
    }
}
