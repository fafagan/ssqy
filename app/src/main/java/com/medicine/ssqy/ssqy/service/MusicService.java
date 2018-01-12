package com.medicine.ssqy.ssqy.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.example.sj.mylibrary.net.NetCallback;
import com.example.sj.mylibrary.net.NetForJson;
import com.medicine.ssqy.ssqy.common.URLConstant;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.entity.course.CourseAudioEntity;
import com.medicine.ssqy.ssqy.entity.course.CourseVideoProgressEntity;
import com.medicine.ssqy.ssqy.eventBus.MusicSoldier;
import com.medicine.ssqy.ssqy.ui.listener.MyPhoneStateListener;
import com.medicine.ssqy.ssqy.util.TimeFormatUtil;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class MusicService extends Service {
    private int curIndex = 0;
    private int totalTime = 0;
    public static MediaPlayer mp;
    MusicSoldier soldier = new MusicSoldier();
    Timer timer = new Timer();
    private MyPhoneStateListener mPhoneStateListener;
    private String mCourseUrl;
    
    private Timer mTimerSyncLearnProgress;
    private String courseID;
    private CourseAudioEntity mCourseAudioEntity;
    private TimerTask mTimerTaskSyncLearnProgress = new TimerTask() {
        @Override
        public void run() {
            mNetForJsonSyncLearnProgress.addParam("uid", SharePLogin.getUid());
            mNetForJsonSyncLearnProgress.addParam("type", "audio");
            mNetForJsonSyncLearnProgress.addParam("courseID", courseID);
            if (mDateNow==null) {
                mNetForJsonSyncLearnProgress.addParam("date", TimeFormatUtil.formatLongToNYR(System.currentTimeMillis()));
            }else {
                mNetForJsonSyncLearnProgress.addParam("date", mDateNow);
            }
            if (mp != null) {
                mNetForJsonSyncLearnProgress.addParam("learnProgress", mp.getCurrentPosition());
                mNetForJsonSyncLearnProgress.excute();
            }
            if (mCourseAudioEntity != null) {
                if (!mCourseAudioEntity.isCourseLearned()) {
                    mNetForJsonSyncLearnProgress.addParam("courseLearned", "false");
                    if (mp != null) {
                        if (mp.getCurrentPosition() >= mCourseAudioEntity.getCourseLength() * 0.94) {
                            mNetForJsonSyncLearnProgress.addParam("courseLearned", "true");
                        } 
                    }
                    
                } else {
                    mNetForJsonSyncLearnProgress.addParam("courseLearned", "true");
                }
            }else {
                mNetForJsonSyncLearnProgress.addParam("courseLearned", "false");
            }
            
        }
    };
    private boolean mHasBeginSycn = false;
    private NetForJson mNetForJsonSyncLearnProgress = new NetForJson(URLConstant.AUDIO_PROGRESS_URL, new NetCallback<CourseVideoProgressEntity>() {
        @Override
        public void onSuccess(CourseVideoProgressEntity entity) {
            
        }
        
        @Override
        public void onError() {
            
        }
        
        @Override
        public void onFinish() {
            
        }
    }, true);
    private int courseStudy;
    private String mDateNow;
    
    public MusicService() {
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        mPhoneStateListener = new MyPhoneStateListener(this, mp);
        TelephonyManager tmgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        tmgr.listen(mPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
        EventBus.getDefault().register(this);
    }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mCourseUrl = intent.getStringExtra("courseUrl");
        courseID = intent.getStringExtra("courseID");
        courseStudy = intent.getIntExtra("courseStudy", 0);
        mDateNow=intent.getStringExtra("date");
        mCourseAudioEntity = (CourseAudioEntity) intent.getSerializableExtra("courseEntity");
        mp = new MediaPlayer();
        if (mCourseUrl != null && courseID != null) {
            playMusic();
        }
        
        
        return super.onStartCommand(intent, flags, startId);
    }
    
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MusicSoldier event) {
        int action = event.getAction();
        switch (action) {
            case MusicSoldier.ACTION_UPDATE_PAUSE_OR_PLAY:
                playOrPause();
                break;
            case MusicSoldier.ACTION_UPDATE_PLAY_PROGRESS:
                playMusic(event.getProgress());
                break;
            case MusicSoldier.ACTION_KILL_SERVICE:
                mp.stop();
                mp.release();
                mp = null;
                Intent intent=new Intent(this,MusicService.class);
                this.stopService(intent);
                break;
        }
    }
    
    @Override
    public void onDestroy() {
        curIndex = 0;
        if (mp != null) {
            release();
        }
        
      
        timer.cancel();
        EventBus.getDefault().unregister(this);
        TelephonyManager tmgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        tmgr.listen(mPhoneStateListener, 0);
        mTimerSyncLearnProgress.cancel();
        mTimerSyncLearnProgress = null;
        mHasBeginSycn = false;
        super.onDestroy();
    }
    
    //通知更新ui
    public void updataSeekBar() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (mp != null && mp.isPlaying()) {
                    curIndex = (int) (mp.getCurrentPosition());
                    soldier.setAction(MusicSoldier.ACTION_UPDATE_SEEKBAR_PROGRESS);
                    soldier.setCurIndex(curIndex);
                }
            }
        };
        timer.schedule(timerTask, 0, 1000);
        
    }
    
    public void playOrPause() {
        if (mp != null && mp.isPlaying()) {
            curIndex = mp.getCurrentPosition();
            mp.pause();
        } else {
//            mp.seekTo(curIndex);
            mp.start();
            
            timer.schedule(new MyTask(), 0, 1000);
        }
    }
    
    public void release() {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
        
    }
    
    public void playMusic() {
//        AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.testaudio);
        if (mp != null) {
            try {
                mp.reset();
                mp.setLooping(true);
                mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                mp.setDataSource(file.getFileDescriptor(), file.getStartOffset(),
//                        file.getLength());
//                mp.setDataSource("http://192.168.0.104:8080/test/test.ogg");
                mp.setDataSource(mCourseUrl);
                mp.prepareAsync();
                mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        totalTime = mp.getDuration();
                        
                        soldier.setAction(MusicSoldier.ACTION_UPDATE_TIMEDATA);
                        soldier.setTotalTime(totalTime);
                        EventBus.getDefault().post(soldier);
                        mp.start();
                        if (courseStudy != 0) {
                            mp.seekTo((int) courseStudy);
                            
                        }
                        if (!mHasBeginSycn) {
                            mHasBeginSycn = true;
                            mTimerSyncLearnProgress = new Timer();
                            mTimerSyncLearnProgress.schedule(mTimerTaskSyncLearnProgress, 5000, 5000);
                        }
                        timer.schedule(new MyTask(), 0, 1000);
                    }
                });
//                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    public void playMusic(int index) {
        if (mp != null) {
//            mp.seekTo(index);
            
            if (!mHasBeginSycn) {
                mHasBeginSycn = true;
                mTimerSyncLearnProgress.schedule(mTimerTaskSyncLearnProgress, 5000, 5000);
            }
            mp.start();
            mp.seekTo(index);
            timer.schedule(new MyTask(), 0, 1000);
        }
    }
    
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    class MyTask extends TimerTask {
        
        @Override
        public void run() {
            if (mp != null && mp.isPlaying()) {
                
                
                curIndex = (int) (mp.getCurrentPosition());
                Logger.e("curIndex: " + curIndex);
                Logger.e("total: " + mp.getDuration());
                soldier.setAction(MusicSoldier.ACTION_UPDATE_SEEKBAR_PROGRESS);
                soldier.setCurIndex(curIndex);
                soldier.setTotalTime(totalTime);
                EventBus.getDefault().post(soldier);
            }
        }
    }
}
