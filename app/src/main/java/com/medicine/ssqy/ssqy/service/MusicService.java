package com.medicine.ssqy.ssqy.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.eventBus.MusicSoldier;
import com.medicine.ssqy.ssqy.ui.listener.MyPhoneStateListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class MusicService extends Service {
    private int curIndex = 0;
    private int totalTime = 0;
    public static MediaPlayer mp = new MediaPlayer();
    MusicSoldier soldier = new MusicSoldier();
    Timer timer = new Timer();
    private MyPhoneStateListener mPhoneStateListener;
    
    public MusicService() {
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        mPhoneStateListener=new MyPhoneStateListener(this,mp);
        TelephonyManager tmgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        tmgr.listen(mPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
        EventBus.getDefault().register(this);
    }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        playMusic();
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
                break;
        }
    }
    
    @Override
    public void onDestroy() {
        curIndex = 0;
        release();
        timer.cancel();
        EventBus.getDefault().unregister(this);
        TelephonyManager tmgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        tmgr.listen(mPhoneStateListener, 0);
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
            mp.seekTo(curIndex);
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
        AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.testaudio);
        if (mp != null) {
            try {
                mp.reset();
                mp.setLooping(true);
                mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mp.setDataSource(file.getFileDescriptor(), file.getStartOffset(),
                        file.getLength());
                mp.prepareAsync();
                mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        totalTime = mp.getDuration();
                        soldier.setAction(MusicSoldier.ACTION_UPDATE_TIMEDATA);
                        soldier.setTotalTime(totalTime);
                        EventBus.getDefault().post(soldier);
                        mp.start();
                        timer.schedule(new MyTask(), 0, 1000);
                    }
                });
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    public void playMusic(int index) {
        if (mp != null) {
            mp.seekTo(index);
            mp.start();
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
                soldier.setAction(MusicSoldier.ACTION_UPDATE_SEEKBAR_PROGRESS);
                soldier.setCurIndex(curIndex);
                EventBus.getDefault().post(soldier);
            }
        }
    }
}
