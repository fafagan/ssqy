package com.medicine.ssqy.ssqy.ui.adapter.jbgl;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.entity.JBGLListEntity;

/**
 * Created by Administrator on 2017-09-19.
 */

public class ItemLVTZAdapter extends JBGLBaseAdapter<ItemLVTZAdapter.TZVH>{
    
    public ItemLVTZAdapter(Context context, int adapterLayoutID) {
        super(context, adapterLayoutID);
    }
    
    @Override
    public TZVH setVh(View convertView) {
        return new TZVH(convertView);
    }
    
    @Override
    protected void initializeViews(JBGLListEntity.RecordHistoryEntity entity, TZVH tag, int position) {
        tag.mTvDataXt.setText(entity.getRecordData()==null?"80":entity.getRecordData());
        tag.mTvTimeXt.setText(entity.getRecordDay()==null?"2017-9-18":entity.getRecordDay());
    }
    
    class TZVH extends JBGLViewHolder{
        public TextView mTvDataXt;
        public TextView mTvTimeXt;
    
    
        public TZVH(View itemView) {
            super(itemView);
            mTvDataXt = (TextView) itemView.findViewById(R.id.tv_data_xt);
            mTvTimeXt = (TextView) itemView.findViewById(R.id.tv_time_xt);
        }
    }
}
