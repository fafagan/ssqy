package com.medicine.ssqy.ssqy.ui.adapter.jbgl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.medicine.ssqy.ssqy.entity.JBGLListEntity;

import java.util.List;

/**
 * Created by Administrator on 2017-09-19.
 */

public  abstract class JBGLBaseAdapter<T extends  JBGLViewHolder> extends BaseAdapter {
    public List<JBGLListEntity.RecordHistoryEntity> mEntities;
    public Context mContext;
    public LayoutInflater mLayoutInflater;
    private int mAdapterLayoutID;
    
    public abstract T setVh(View convertView) ;
    
    public JBGLBaseAdapter(Context context, int adapterLayoutID) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mAdapterLayoutID = adapterLayoutID;
    }
    
    public void setEntities(List<JBGLListEntity.RecordHistoryEntity> entities) {
        mEntities = entities;
        notifyDataSetChanged();
    }
    
    public void addEntities(List<JBGLListEntity.RecordHistoryEntity> recordHistory) {
        mEntities.addAll(recordHistory);
        notifyDataSetChanged();
    }
    
    @Override
    public int getCount() {
        if (mEntities == null) {
            return 0;
        } else {
            return mEntities.size();
        }
        
    }
    
    @Override
    public Object getItem(int position) {
        if (mEntities == null) {
            return null;
        } else {
            return mEntities.get(position);
        }
    }
    
    @Override
    public long getItemId(int position) {
        return position;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mEntities == null) {
            return null;
        }
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(mAdapterLayoutID, parent, false);
            T vh=setVh(convertView);
            convertView.setTag(vh);
        }
        initializeViews((JBGLListEntity.RecordHistoryEntity) getItem(position), (T) convertView.getTag(), position);
        return convertView;
    }
    
    protected abstract void initializeViews(JBGLListEntity.RecordHistoryEntity entity, T vh, int position);
    
    
}
