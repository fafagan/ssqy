package com.medicine.ssqy.ssqy.ui.pop;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.medicine.ssqy.ssqy.R;

import org.xutils.common.util.DensityUtil;

/**
 * Created by Administrator on 2016/11/20.
 */
public class LoadingPopWindow extends PopupWindow {
    private ImageView mImgvLoading;
    private AnimationDrawable mAnimationDrawable;
    private View contentView;
    public LoadingPopWindow(Context context) {
        super(context);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setWidth(DensityUtil.getScreenWidth());
        this.setHeight(DensityUtil.getScreenHeight());
        contentView = LayoutInflater.from(context).inflate(R.layout.dig_loading, null);
        this.setContentView(contentView);
        mImgvLoading = (ImageView) contentView.findViewById(R.id.imgv_loading);
        mImgvLoading.setBackgroundResource(R.drawable.bga_refresh_loding);
        mAnimationDrawable = (AnimationDrawable) mImgvLoading.getBackground();
        
        
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                mAnimationDrawable.stop();
            }
            
        });
    }
    
    public void show() {
        mAnimationDrawable.start();
        super.showAtLocation(contentView, Gravity.CENTER, 0, 0);
    }
    
    
}
