package com.medicine.ssqy.ssqy.ui.listener;

import android.content.Context;
import android.view.View;

import com.medicine.ssqy.ssqy.base.KBaseApp;

/**
 * Created by Administrator on 2016/11/14.
 */
public abstract class BaseOnClick<E> implements View.OnClickListener {
    
    
//    public KBaseActivity mKBaseActivity;
//    public KBaseFragment mKBaseFragment;
//    public BaseAdapter mBaseAdapter;
//    public String[] mStrDatas;
//    public int[] mIntDatas;
//    public boolean[] mBooleanDatas;
//    public Double[] mDoubleDatas;
//    public T[] mRefDatas;
    public E mParent;
    public Context mContext;
    
    
    public BaseOnClick(E parent) {
        mParent = parent;
        mContext= KBaseApp.mContextGlobal;
    }
    
    
    
}
