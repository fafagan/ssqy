package com.medicine.ssqy.ssqy.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

import com.medicine.ssqy.ssqy.R;

/**
 * Created by Administrator on 2016/11/20.
 */
public class LoadingDig extends Dialog {
    private  ImageView mImgvLoading;
    private  AnimationDrawable mAnimationDrawable;
    public LoadingDig(Context context) {
        super(context,R.style.dig_alpha);
        this.setContentView(R.layout.dig_loading);
        mImgvLoading = (ImageView) findViewById(R.id.imgv_loading);
        mImgvLoading.setBackgroundResource(R.drawable.bga_refresh_loding);
        mAnimationDrawable = (AnimationDrawable)mImgvLoading.getBackground();
 
        
        this.setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                mAnimationDrawable.stop();
            }
        });
    }
    
    public void show(){
        mAnimationDrawable.start();
        super.show();
    }
    
    
}
