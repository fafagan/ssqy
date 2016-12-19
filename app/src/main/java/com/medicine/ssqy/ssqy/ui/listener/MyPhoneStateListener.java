package com.medicine.ssqy.ssqy.ui.listener;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

/**
 * Created by Amy on 2016/12/14.
 */
public class MyPhoneStateListener extends PhoneStateListener {
    private Context mContext;
    private MediaPlayer mMediaPlayer;
    private boolean mResumeAfterCall = false;
    private int curPosition;
    private boolean isCalling;
    
    public int getCurPosition() {
        return curPosition;
    }
    
    public boolean isCalling() {
        return isCalling;
    }
    
    public MyPhoneStateListener(Context context, MediaPlayer mediaPlayer) {
        mContext = context;
        mMediaPlayer = mediaPlayer;
    }
    
    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        if (state == TelephonyManager.CALL_STATE_RINGING) {
            AudioManager audioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
            int ringvolume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
            if (ringvolume > 0) {
                mResumeAfterCall = (mMediaPlayer.isPlaying() || mResumeAfterCall);
                //当电话打进来时，就设置为停止播放状态  
                mMediaPlayer.pause();
                isCalling=true;
                curPosition=mMediaPlayer.getCurrentPosition();
                
            }
        } else if (state == TelephonyManager.CALL_STATE_OFFHOOK) {
            // pause the music while a conversation is in progress  
            isCalling=true;
            mResumeAfterCall = (mMediaPlayer.isPlaying() || mResumeAfterCall);
            mMediaPlayer.pause();
            curPosition=mMediaPlayer.getCurrentPosition();
        } else if (state == TelephonyManager.CALL_STATE_IDLE) {
            // start playing again  
            if (mResumeAfterCall) {
                // resume playback only if music was playing  
                // when the call was answered 
                isCalling=false;
                mMediaPlayer.seekTo(curPosition);
                mMediaPlayer.start();
                mResumeAfterCall = false;
            }
        }
    }
}
