package com.medicine.ssqy.ssqy.ui.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.common.utils.GlideCircleTransform;
import com.medicine.ssqy.ssqy.entity.SystemMsgEntity;
import com.medicine.ssqy.ssqy.entity.SystemMsgListEntity;
import com.medicine.ssqy.ssqy.ui.activity.CourseDetailPicActivity;
import com.medicine.ssqy.ssqy.util.TimeFormatUtil;

import java.util.List;

public class ItemLvSystemMsgDataAdapter extends BaseAdapter implements View.OnClickListener {
    
    private List<SystemMsgListEntity> mEntities;
    
    private Context mContext;
    private LayoutInflater layoutInflater;
    private GlideCircleTransform mGlideCircleTransform;
    
    @Override
    public int getViewTypeCount() {
        return 2;
    }
    
    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType();
    }
    
    public ItemLvSystemMsgDataAdapter(Context context, List<SystemMsgListEntity> entities) {
        this.mContext = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.mEntities = entities;
        mGlideCircleTransform=new GlideCircleTransform(context);
    }
    
    public void setEntities(List<SystemMsgListEntity> entities) {
        mEntities = entities;
        notifyDataSetChanged();
    }
    
    public void addEntities(List<SystemMsgListEntity> entities) {
        mEntities.addAll(entities);
        notifyDataSetChanged();
    }
    
    @Override
    public int getCount() {
        return mEntities.size();
    }
    
    @Override
    public SystemMsgListEntity getItem(int position) {
        return mEntities.get(position);
    }
    
    @Override
    public long getItemId(int position) {
        return position;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        
        ViewHolder viewHolder = null;
        if (convertView == null) {
            
            switch (getItemViewType(position)) {
                case 0:
                    convertView = layoutInflater.inflate(R.layout.item_lv_system_msg_time, parent, false);
                    break;
                case 1:
                    convertView = layoutInflater.inflate(R.layout.item_lv_system_msg_data, parent, false);
                    break;
            }
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SystemMsgListEntity entity = getItem(position);

        switch (getItemViewType(position)) {
            case 0:
//                Logger.e("xx  "+ viewHolder.mTvTimeSystemMsg+"  "+viewHolder.tvDataTimeSystemMsg);
                viewHolder.mTvTimeSystemMsg.setText(entity.getTime());
                break;
            case 1:
                viewHolder.tvDataTimeSystemMsg.setText(TimeFormatUtil.formatLongToNYRSFM(entity.getMsgEntity().getDate()));
                viewHolder.tvDataTitleSystemMsg.setText("   "+entity.getMsgEntity().getTitle());
                viewHolder.mLayoutRootItemDataSystem.setTag(entity.getMsgEntity());
                viewHolder.mLayoutRootItemDataSystem.setOnClickListener(this);
                Glide.with(mContext).load(entity.getMsgEntity().getPushPic()).transform(mGlideCircleTransform).placeholder(R.drawable.ssqy).into(viewHolder.mImgvPicSystemMsg);
                break;
        }
        return convertView;
    }
    
    @Override
    public void onClick(View v) {
        SystemMsgEntity entity= (SystemMsgEntity) v.getTag();
        Intent intent=new Intent(mContext, CourseDetailPicActivity.class);
    
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("type",CourseDetailPicActivity.TYPE_XJ);
        intent.putExtra("newsUrl",entity.getUrl());
        mContext.startActivity(intent);
    }
    
    
    protected class ViewHolder {
        private TextView tvDataTitleSystemMsg;
        private TextView tvDataTimeSystemMsg;
        private TextView mTvTimeSystemMsg;
        private LinearLayout mLayoutRootItemDataSystem;
        private ImageView mImgvPicSystemMsg;
    
  
    
    
    
    
        public ViewHolder(View view) {
            try {
                mImgvPicSystemMsg = (ImageView) view.findViewById(R.id.imgv_pic_system_msg);
                tvDataTitleSystemMsg = (TextView) view.findViewById(R.id.tv_data_title_system_msg);
                tvDataTimeSystemMsg = (TextView) view.findViewById(R.id.tv_data_time_system_msg);
                mTvTimeSystemMsg = (TextView) view.findViewById(R.id.tv_time_system_msg);
                mLayoutRootItemDataSystem = (LinearLayout)view. findViewById(R.id.layout_root_item_data_system);
            } catch (Exception e) {
            }
        }
    }
}
