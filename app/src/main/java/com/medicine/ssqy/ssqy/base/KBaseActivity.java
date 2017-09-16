package com.medicine.ssqy.ssqy.base;

import android.view.View;

import com.example.sj.mylibrary.base.BaseActivity;
import com.umeng.analytics.MobclickAgent;

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
    
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
