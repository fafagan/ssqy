package com.medicine.ssqy.ssqy.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.entity.CalanderListEntity;

import java.util.List;

/**
 * Created by Administrator on 2017-09-18.
 */

public class ItemRecyCalendarAdapter extends RecyclerView.Adapter<ItemRecyCalendarAdapter.ItemRecyCalendarVH> implements View.OnClickListener {
    
    private Context mContext;
    private List<CalanderListEntity.CurrentMonthEntity.RecordsEntity> mRecordsEntities;
    private LayoutInflater mLayoutInflater;
    private OnItemDateClick mOnItemDateClick;
    
    public void setOnItemDateClick(OnItemDateClick onItemDateClick) {
        mOnItemDateClick = onItemDateClick;
    }
    
    @Override
    public void onClick(View v) {
        if (mOnItemDateClick != null) {
            String date= (String) v.getTag();
            mOnItemDateClick.onItemClick(date);
        }
   
    }
    
    public interface  OnItemDateClick{
        public void onItemClick(String date);
    }
    public ItemRecyCalendarAdapter(Context context, List<CalanderListEntity.CurrentMonthEntity.RecordsEntity> recordsEntities) {
        mContext = context;
        mRecordsEntities = recordsEntities;
        mLayoutInflater=LayoutInflater.from(context);
    }
    
    public void setRecordsEntities(List<CalanderListEntity.CurrentMonthEntity.RecordsEntity> recordsEntities) {
        mRecordsEntities = recordsEntities;
        notifyDataSetChanged();
    }
    
    @Override
    public ItemRecyCalendarVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mLayoutInflater.inflate(R.layout.item_recy_calendar,parent,false);
        ItemRecyCalendarVH itemRecyCalendarVH=new ItemRecyCalendarVH(view);
        return itemRecyCalendarVH;
    }
    
    @Override
    public int getItemCount() {
        return mRecordsEntities.size();
    }
    
    @Override
    public void onBindViewHolder(ItemRecyCalendarVH holder, int position) {
        holder.mTvTimeItemCalendar.setText(mRecordsEntities.get(position).getDate().substring(5));
        holder.mTvTitleItemCalendar.setText(mRecordsEntities.get(position).getTitle());
        holder.mTvTitleItemCalendar.setTag(mRecordsEntities.get(position).getDate());
        holder.mTvTitleItemCalendar.setOnClickListener(this);
    }
    
    protected class ItemRecyCalendarVH extends RecyclerView.ViewHolder{
        private TextView mTvTimeItemCalendar;
        private TextView mTvTitleItemCalendar;
    
    
    
        public ItemRecyCalendarVH(View itemView) {
            super(itemView);
            mTvTimeItemCalendar = (TextView) itemView.findViewById(R.id.tv_time_item_calendar);
            mTvTitleItemCalendar = (TextView) itemView.findViewById(R.id.tv_title_item_calendar);
        }
    }
}
