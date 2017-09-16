package com.medicine.ssqy.ssqy.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.ui.activity.AudioCourseListActivity;
import com.medicine.ssqy.ssqy.ui.activity.VedioCourseListActivity;


/**
 * Created by Administrator on 2016/11/25.
 */
public class DigCourseType extends Dialog implements View.OnClickListener {
    private LinearLayout mLayoutDigCourseVedio;
    private LinearLayout mLayoutDigCourseAudio;
    private LinearLayout mLayoutDigCourseTw;
    private Context mContext;

    
    
    public DigCourseType(final Context context) {
        super(context);
        mContext=context;
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setGravity(Gravity.CENTER);
        this.setContentView(R.layout.dig_course_type);
    
        mLayoutDigCourseVedio = (LinearLayout) findViewById(R.id.layout_dig_course_vedio);
        mLayoutDigCourseAudio = (LinearLayout) findViewById(R.id.layout_dig_course_audio);
        mLayoutDigCourseTw = (LinearLayout) findViewById(R.id.layout_dig_course_tw);
    
        mLayoutDigCourseVedio.setOnClickListener(this);
        mLayoutDigCourseAudio.setOnClickListener(this);
        mLayoutDigCourseTw.setOnClickListener(this);
    }
    
    
    public void showSelf() {
        this.getWindow().setWindowAnimations(R.style.diganim);
        this.show();
//        this.getWindow().setBackgroundDrawableResource(R.drawable.bg_jc2);
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_dig_course_vedio:
                VedioCourseListActivity.showAll(mContext);
                break;
            case R.id.layout_dig_course_audio:
                AudioCourseListActivity.showAll(mContext);
                break;
//            case R.id.layout_dig_course_tw:
//                PicCourseListActivity.showAll(mContext);
//                break;
        }
        this.cancel();
    }
}
