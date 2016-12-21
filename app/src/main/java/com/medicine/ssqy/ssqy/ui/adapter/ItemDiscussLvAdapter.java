package com.medicine.ssqy.ssqy.ui.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sj.mylibrary.util.StringEmptyUtil;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.test.DiscussEntity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ItemDiscussLvAdapter extends BaseAdapter {

    private  List<DiscussEntity> mEntities;

    private Context context;
    private LayoutInflater layoutInflater;
    public void insertToFirst(DiscussEntity discussEntity){
        mEntities.add(0,discussEntity);
        notifyDataSetChanged();
        
    }
    public ItemDiscussLvAdapter(Context context, List<DiscussEntity> entities) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.mEntities=entities;
    }

    @Override
    public int getCount() {
        return mEntities.size();
    }

    @Override
    public DiscussEntity getItem(int position) {
        return mEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_discuss_lv, parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((DiscussEntity)getItem(position), (ViewHolder) convertView.getTag(),position);
        return convertView;
    }

    private void initializeViews(DiscussEntity entity, ViewHolder holder,int position) {
        //TODO implement
        
        holder.tvContentItemDiscussLv.setText(entity.getDetail());
        holder.tvTimeItemDiscussLv.setText(entity.getTime());
        holder.tvNameItemDiscussLv.setText(entity.getNickName());
        if (StringEmptyUtil.isEmpty(entity.getHeadUrl())){
            Glide.with(context).load(entity.getHeadUrl1()).into(holder.imgvHeadItemDiscussLv);
        }else {
            Glide.with(context).load(entity.getHeadUrl()).into(holder.imgvHeadItemDiscussLv);
        }
       
    }

    protected class ViewHolder {
        private CircleImageView imgvHeadItemDiscussLv;
    private TextView tvNameItemDiscussLv;
    private TextView tvContentItemDiscussLv;
    private TextView tvTimeItemDiscussLv;

        public ViewHolder(View view) {
            imgvHeadItemDiscussLv = (CircleImageView) view.findViewById(R.id.imgv_head_item_discuss_lv);
            tvNameItemDiscussLv = (TextView) view.findViewById(R.id.tv_name_item_discuss_lv);
            tvContentItemDiscussLv = (TextView) view.findViewById(R.id.tv_content_item_discuss_lv);
            tvTimeItemDiscussLv = (TextView) view.findViewById(R.id.tv_time_item_discuss_lv);
        }
    }
}
