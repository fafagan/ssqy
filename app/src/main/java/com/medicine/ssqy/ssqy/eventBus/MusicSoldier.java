package com.medicine.ssqy.ssqy.eventBus;

/**
 * Created by Amy on 2016/12/14.
 */
public class MusicSoldier {
    public static final int ACTION_UPDATE_TIMEDATA=110;
    public static final int ACTION_UPDATE_PAUSE_OR_PLAY=130;
    public static final int ACTION_UPDATE_PLAY_PROGRESS=140;
    public static final int ACTION_UPDATE_SEEKBAR_PROGRESS=150;
    public static final int ACTION_KILL_SERVICE=160;
    
    private int mAction;
    private String mUrl;
    private int mTotalTime;
    
    public int getCurIndex() {
        return mCurIndex;
    }
    
    public void setCurIndex(int curIndex) {
        mCurIndex = curIndex;
    }
    
    //用户拖动的进度
    private int mProgress;
    //音频播放的位置
    private int mCurIndex;
    
    public int getProgress() {
        return mProgress;
    }
    
    public void setProgress(int progress) {
        mProgress = progress;
    }
    
    public int getAction() {
        return mAction;
    }
    
    public void setAction(int action) {
        mAction = action;
    }
    
    public String getUrl() {
        return mUrl;
    }
    
    public void setUrl(String url) {
        mUrl = url;
    }
    
    public int getTotalTime() {
        return mTotalTime;
    }
    
    public void setTotalTime(int totalTime) {
        mTotalTime = totalTime;
    }
}
