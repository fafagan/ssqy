package com.medicine.ssqy.ssqy.ui.adapter.jbgl;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.entity.JBGLListEntity;

/**
 * Created by Administrator on 2017-09-19.
 */

public class ItemLVXYAdapter extends JBGLBaseAdapter<ItemLVXYAdapter.XYVH>{
    
    public ItemLVXYAdapter(Context context, int adapterLayoutID) {
        super(context, adapterLayoutID);
    }
    
    @Override
    public XYVH setVh(View convertView) {
        return new XYVH(convertView);
    }
    
    @Override
    protected void initializeViews(JBGLListEntity.RecordHistoryEntity entity, XYVH tag, int position) {
        tag.mTvDataXyd.setText(entity.getPressureMin()==null?"70":entity.getPressureMin());
        tag.mTvDataXyg.setText(entity.getPressureMax()==null?"110":entity.getPressureMax());
        tag.mTvTimeXt.setText(entity.getRecordDay()==null?"2017-9-18":entity.getRecordDay());
    }
    
    class XYVH extends JBGLViewHolder{
        private TextView mTvDataXyg;
        private TextView mTvDataXyd;
        private TextView mTvTimeXt;
    
     
    
    
    
        public XYVH(View itemView) {
            super(itemView);
            mTvDataXyg = (TextView)itemView. findViewById(R.id.tv_data_xyg);
            mTvDataXyd = (TextView)itemView.  findViewById(R.id.tv_data_xyd);
            mTvTimeXt = (TextView)itemView.  findViewById(R.id.tv_time_xt);
        }
    }
}
