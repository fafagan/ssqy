package com.medicine.ssqy.ssqy.ui.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.medicine.ssqy.ssqy.ui.activity.CourseDetailPicActivity;

/**
 * Created by Administrator on 2016/12/13.
 */
public class ItemTWClickListener implements AdapterView.OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Context context = view.getContext();
    
      

            Intent intent=new Intent(context, CourseDetailPicActivity.class);
            context.startActivity(intent);
        
    }
}
