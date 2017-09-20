package com.medicine.ssqy.ssqy.ui.adapter.jbgl;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.entity.JBGLListEntity;

/**
 * Created by Administrator on 2017-09-19.
 */

public class ItemLVXTAdapter  extends JBGLBaseAdapter<ItemLVXTAdapter.XTVH>{
    
    public ItemLVXTAdapter(Context context, int adapterLayoutID) {
        super(context, adapterLayoutID);
    }
    
    @Override
    public XTVH setVh(View convertView) {
        return new XTVH(convertView);
    }
    
    @Override
    protected void initializeViews(JBGLListEntity.RecordHistoryEntity entity, XTVH tag, int position) {
        tag.mTvDataXt.setText(entity.getRecordData()==null?"102.5":entity.getRecordData());
        tag.mTvTimeXt.setText(entity.getRecordDay()==null?"2017-9-18":entity.getRecordDay());
    }
    
    class XTVH extends JBGLViewHolder{
        public TextView mTvDataXt;
        public TextView mTvTimeXt;
    
    
        public XTVH(View itemView) {
            super(itemView);
            mTvDataXt = (TextView) itemView.findViewById(R.id.tv_data_xt);
            mTvTimeXt = (TextView) itemView.findViewById(R.id.tv_time_xt);
        }
    }
}
