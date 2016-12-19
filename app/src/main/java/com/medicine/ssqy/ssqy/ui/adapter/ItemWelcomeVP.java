package com.medicine.ssqy.ssqy.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.sj.mylibrary.adapter.SimplePagerAdapter;
import com.medicine.ssqy.ssqy.common.DrawableConstant;

/**
 * Created by Administrator on 2016/11/14.
 */
public class ItemWelcomeVP extends SimplePagerAdapter {
    private int[] mDrawableIds= DrawableConstant.VP_WELCOME_DRAWABLES;
    
    public ItemWelcomeVP(Context context) {
        super(context);
    }
    
    @Override
    public View initView(ViewGroup container, int position) {
        ImageView imageView=new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(mDrawableIds[position]);
        return imageView;
    }
    
    @Override
    public int getCount() {
        return mDrawableIds.length;
    }
}
