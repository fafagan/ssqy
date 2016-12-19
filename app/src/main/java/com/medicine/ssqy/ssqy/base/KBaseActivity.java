package com.medicine.ssqy.ssqy.base;

import android.view.View;

import com.example.sj.mylibrary.base.BaseActivity;

/**
 * Created by Administrator on 2016/11/9.
 */
public abstract class KBaseActivity extends BaseActivity{
    @Override
    public void initOthers() {
        setTitleLeft("", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                killSelf();
            }
        });
    }
}
