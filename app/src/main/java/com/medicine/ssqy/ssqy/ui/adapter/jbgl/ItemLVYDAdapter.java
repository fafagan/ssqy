package com.medicine.ssqy.ssqy.ui.adapter.jbgl;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.entity.JBGLListEntity;

/**
 * Created by Administrator on 2017-09-19.
 */

public class ItemLVYDAdapter extends JBGLBaseAdapter<ItemLVYDAdapter.SSVH>{
    
    public ItemLVYDAdapter(Context context, int adapterLayoutID) {
        super(context, adapterLayoutID);
    }
    
    @Override
    public SSVH setVh(View convertView) {
        return new SSVH(convertView);
    }
    
    @Override
    protected void initializeViews(JBGLListEntity.RecordHistoryEntity entity, SSVH tag, int position) {
        tag.mTvDataSs.setText(entity.getRecordData()==null?"102.5":entity.getRecordData());
        tag.mTvTimeXt.setText(entity.getRecordDay()==null?"2017-9-18":entity.getRecordDay());
    }
    
    class SSVH extends JBGLViewHolder{
        private TextView mTvTimeXt;
        private TextView mTvDataSs;
    
  
    
    
    
        public SSVH(View itemView) {
            super(itemView);
            mTvTimeXt = (TextView) itemView.findViewById(R.id.tv_time_xt);
            mTvDataSs = (TextView) itemView.findViewById(R.id.tv_data_ss);
        }
    }
}
