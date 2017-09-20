package com.medicine.ssqy.ssqycom.medicine.ssqy.ssqy.ui.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.entity.food.FoodQueryData;

import java.util.List;

public class ItemLvUtilHomeAdapter extends BaseAdapter {
    
    private List<FoodQueryData> mEntities;
    
    private Context mContext;
    private LayoutInflater layoutInflater;
    
    public ItemLvUtilHomeAdapter(Context context, List<FoodQueryData> entities) {
        this.mContext = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.mEntities = entities;
    }
    
    @Override
    public int getCount() {
        return mEntities.size();
    }
    
    @Override
    public FoodQueryData getItem(int position) {
        return mEntities.get(position);
    }
    
    @Override
    public long getItemId(int position) {
        return position;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_lv_util_home, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((FoodQueryData) getItem(position), (ViewHolder) convertView.getTag(), position);
        return convertView;
    }
    
    private void initializeViews(FoodQueryData entity, ViewHolder holder, int position) {
        //TODO implement
        holder.tvTitle.setText(entity.getTitle());
        holder.tvData.setText(entity.getData());
        Glide.with(mContext).load(entity.getPicUrl()).placeholder(R.drawable.img).centerCrop().into(holder.itemPic);
    }
    
    protected class ViewHolder {
        private ImageView itemPic;
        private TextView tvTitle;
        private TextView tvData;
        
        public ViewHolder(View view) {
            itemPic = (ImageView) view.findViewById(R.id.item_pic);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            tvData = (TextView) view.findViewById(R.id.tv_data);
        }
    }
}
