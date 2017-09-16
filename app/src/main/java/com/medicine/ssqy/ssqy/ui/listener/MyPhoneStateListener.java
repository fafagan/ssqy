package com.medicine.ssqy.ssqy.ui.listener;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.ksyun.media.player.IMediaPlayer;

/**
 * Created by Amy on 2016/12/14.
 */
public class MyPhoneStateListener extends PhoneStateListener {
    private Context mContext;
    private IMediaPlayer mMediaPlayer;
    private MediaPlayer mMediaPlayer2;
    private boolean mResumeAfterCall = false;
    private int curPosition;
    private boolean isCalling;
    
    public int getCurPosition() {
        return curPosition;
    }
    
    public boolean isCalling() {
        return isCalling;
    }
    
    public MyPhoneStateListener(Context context, IMediaPlayer mediaPlayer) {
        mContext = context;
        mMediaPlayer = mediaPlayer;
    }
    public MyPhoneStateListener(Context context, MediaPlayer mediaPlayer) {
        mContext = context;
        mMediaPlayer2 = mediaPlayer;
    }
    
    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        if (mMediaPlayer2==null) {
            if (state == TelephonyManager.CALL_STATE_RINGING) {
                AudioManager audioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
                int ringvolume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
                if (ringvolume > 0) {
                    mResumeAfterCall = (mMediaPlayer2.isPlaying() || mResumeAfterCall);
                    //当电话打进来时，就设置为停止播放状态  
                    mMediaPlayer2.pause();
                    isCalling=true;
                    curPosition= (int) mMediaPlayer2.getCurrentPosition();
            
                }
            } else if (state == TelephonyManager.CALL_STATE_OFFHOOK) {
                // pause the music while a conversation is in progress  
                isCalling=true;
                mResumeAfterCall = (mMediaPlayer2.isPlaying() || mResumeAfterCall);
                mMediaPlayer2.pause();
                curPosition= (int) mMediaPlayer2.getCurrentPosition();
            } else if (state == TelephonyManager.CALL_STATE_IDLE) {
                // start playing again  
                if (mResumeAfterCall) {
                    // resume playback only if music was playing  
                    // when the call was answered 
                    isCalling=false;
//                mMediaPlayer2.seekTo(curPosition);
                    mMediaPlayer2.start();
                    mResumeAfterCall = false;
                }
            }
        }else {
            
        } if (state == TelephonyManager.CALL_STATE_RINGING) {
            AudioManager audioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
            int ringvolume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
            if (ringvolume > 0) {
                mResumeAfterCall = (mMediaPlayer.isPlaying() || mResumeAfterCall);
                //当电话打进来时，就设置为停止播放状态  
                mMediaPlayer.pause();
                isCalling=true;
                curPosition= (int) mMediaPlayer.getCurrentPosition();
            
            }
        } else if (state == TelephonyManager.CALL_STATE_OFFHOOK) {
            // pause the music while a conversation is in progress  
            isCalling=true;
            mResumeAfterCall = (mMediaPlayer.isPlaying() || mResumeAfterCall);
            mMediaPlayer.pause();
            curPosition= (int) mMediaPlayer.getCurrentPosition();
        } else if (state == TelephonyManager.CALL_STATE_IDLE) {
            // start playing again  
            if (mResumeAfterCall) {
                // resume playback only if music was playing  
                // when the call was answered 
                isCalling=false;
//                mMediaPlayer.seekTo(curPosition);
                mMediaPlayer.start();
                mResumeAfterCall = false;
            }
        }
       
    }
}
