package com.medicine.ssqy.ssqy.base;

import android.view.View;

import com.example.sj.mylibrary.base.BaseFragment;

/**
 * Created by Administrator on 2016/11/9.
 */
public abstract class KBaseFragment extends BaseFragment {
    
    public View findViewById(int id){
        return mRootView.findViewById(id);
        
    }
}
