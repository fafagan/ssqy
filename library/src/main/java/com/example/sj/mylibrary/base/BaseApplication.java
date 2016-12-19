package com.example.sj.mylibrary.base;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import org.xutils.common.util.LogUtil;
import org.xutils.x;

/**
 * Created by Administrator on 2016/9/1.
 */
public abstract class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        int titleResId = initTitle();
        if (titleResId != 0) {
            TitleBarConfig.title_resID = titleResId;
        }
        initX();
        initLog();
        initOthers();
    }
    
    public abstract  void initOthers();
    private void initLog() {
        if (isDebug()) {
            LogUtil.customTagPrefix=this.getPackageName();
            Logger.init(this.getPackageName())
                    .logLevel(LogLevel.FULL)
                    .methodCount(1)                 // default 2
                    .hideThreadInfo();            // default shown
        }else {
            Logger.init(this.getPackageName())
                    .logLevel(LogLevel.NONE)
                    .methodCount(1)                 // default 2
                    .hideThreadInfo();            // default shown
        }
    }
    
    protected abstract boolean isDebug();
    
    private void initX() {
        x.Ext.init(this);
        x.Ext.setDebug(isDebug());
      
    }
    
    protected abstract int initTitle();
    
    
}
