package com.example.sj.mylibrary.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/3/11.
 */
public abstract class SimplePagerAdapter extends PagerAdapter {

    public Context mContext;
    
    public SimplePagerAdapter(Context context) {
        mContext = context;
    }
    
    public abstract  View initView(ViewGroup container, int position);
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view =initView(container,position);
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
