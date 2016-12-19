package com.medicine.ssqy.ssqy.ui.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.ui.fragment.FragUtilItem;

import java.util.List;

public class ItemLvFragUtilAdapter extends BaseAdapter {

    private  List<FragUtilItem> mEntities;

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemLvFragUtilAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.mEntities=FragUtilItem.mFragUtilItems;
    }

    @Override
    public int getCount() {
        return mEntities.size();
    }

    @Override
    public FragUtilItem getItem(int position) {
        return mEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_lv_frag_util, parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((FragUtilItem)getItem(position), (ViewHolder) convertView.getTag(),position);
        return convertView;
    }

    private void initializeViews(FragUtilItem entity, ViewHolder holder,int position) {
        //TODO implement
        holder.tvItemLvFragUtil.setText(entity.getTitle());
        holder.imgvItemLvFragUtil.setImageResource(entity.getIcon());
    }

    protected class ViewHolder {
        private ImageView imgvItemLvFragUtil;
    private TextView tvItemLvFragUtil;

        public ViewHolder(View view) {
            imgvItemLvFragUtil = (ImageView) view.findViewById(R.id.imgv_item_lv_frag_util);
            tvItemLvFragUtil = (TextView) view.findViewById(R.id.tv_item_lv_frag_util);
        }
    }
}
