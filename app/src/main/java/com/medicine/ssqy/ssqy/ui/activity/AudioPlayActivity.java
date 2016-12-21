package com.medicine.ssqy.ssqy.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.eventBus.MusicSoldier;
import com.medicine.ssqy.ssqy.service.MusicService;

import com.medicine.ssqy.ssqy.test.DiscussEntity;
import com.medicine.ssqy.ssqy.ui.adapter.ItemDiscussLvAdapter;
import com.medicine.ssqy.ssqy.ui.dialog.DigDiscuss;
import com.medicine.ssqy.ssqy.util.TimeFormatUtil;
import com.medicine.ssqy.ssqy.util.ShareUtil;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class AudioPlayActivity extends KBaseActivity {
    private TextView mTvAudiotitleActivityAudioPlay;
    private LinearLayout mLlAudiobarActivityAudioPlay;
    private CheckBox mCbPlayorpauseActivityAudioPlay;
    private SeekBar mSbActivityAudioPlay;
    private TextView mTvTotaltimeActivityAudioPlay;
    private TextView mTvAudiocontentActivityAudioPlay;
    private ImageView mImgvActivityAudioPlay;
    private ImageView mFengexian;
    private TextView mTvCommentsnumComment;
    private ListView mLvCommentsComment;
    private LinearLayout mLlBottombarComment;
    private LinearLayout mLlDianzanComment;
    private CheckBox mCbDianzanComment;
    private TextView mTvZannumComment;
    private LinearLayout mBtCommentComment;
    private MusicSoldier soldier = new MusicSoldier();
    private boolean firstPlay = true;
    private int curIndex = 0;
    public static final int UPDATE_PROGRESS = 10;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UPDATE_PROGRESS:
                    mSbActivityAudioPlay.setProgress((Integer) msg.obj);
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    public int setRootView() {
        return R.layout.activity_audio_play;
    }
    
    @Override
    public void initViews() {
        mTvAudiotitleActivityAudioPlay = (TextView) findViewById(R.id.tv_audiotitle_activity_audio_play);
        mLlAudiobarActivityAudioPlay = (LinearLayout) findViewById(R.id.ll_audiobar_activity_audio_play);
        mCbPlayorpauseActivityAudioPlay = (CheckBox) findViewById(R.id.cb_playorpause_activity_audio_play);
        mSbActivityAudioPlay = (SeekBar) findViewById(R.id.sb_activity_audio_play);
        mTvTotaltimeActivityAudioPlay = (TextView) findViewById(R.id.tv_totaltime_activity_audio_play);
        mTvAudiocontentActivityAudioPlay = (TextView) findViewById(R.id.tv_audiocontent_activity_audio_play);
        mImgvActivityAudioPlay = (ImageView) findViewById(R.id.imgv_activity_audio_play);
        mFengexian = (ImageView) findViewById(R.id.fengexian);
        mTvCommentsnumComment = (TextView) findViewById(R.id.tv_commentsnum_comment);
        mLvCommentsComment = (ListView) findViewById(R.id.lv_comments_comment);
        mLlBottombarComment = (LinearLayout) findViewById(R.id.ll_bottombar_comment);
        mLlDianzanComment = (LinearLayout) findViewById(R.id.ll_dianzan_comment);
        mCbDianzanComment = (CheckBox) findViewById(R.id.cb_dianzan_comment);
        mTvZannumComment = (TextView) findViewById(R.id.tv_zannum_comment);
        mBtCommentComment = (LinearLayout) findViewById(R.id.bt_comment_comment);
        setTitleCenter("音频课程");
        
    }
    
    @Override
    public void initDatas() {
        EventBus.getDefault().register(this);
        setTitleRight("分享",null);
        mCbPlayorpauseActivityAudioPlay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            
                if (firstPlay) {
                    Intent intent = new Intent(AudioPlayActivity.this, MusicService.class);
                    startService(intent);
                    firstPlay = false;
                } else {
                    soldier.setAction(MusicSoldier.ACTION_UPDATE_PAUSE_OR_PLAY);
                    EventBus.getDefault().post(soldier);
                }
            
            }
        });
        mSbActivityAudioPlay.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    soldier.setAction(MusicSoldier.ACTION_UPDATE_PLAY_PROGRESS);
                    soldier.setProgress(progress);
                    EventBus.getDefault().post(soldier);
                }
            }
        
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            
            }
        
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            
            }
        });
        
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MusicSoldier event) {
        int action = event.getAction();
        switch (action) {
            case MusicSoldier.ACTION_UPDATE_TIMEDATA:
                mTvTotaltimeActivityAudioPlay.setText(TimeFormatUtil.formatLongToTimeStr((long) event.getTotalTime()));
                mSbActivityAudioPlay.setMax(event.getTotalTime());
                break;
            case MusicSoldier.ACTION_UPDATE_SEEKBAR_PROGRESS:
                curIndex = event.getCurIndex();
                Message message=new Message();
                message.what=UPDATE_PROGRESS;
                message.obj=curIndex;
                mHandler.sendMessage(message);
                break;
            
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        soldier.setAction(MusicSoldier.ACTION_KILL_SERVICE);
        EventBus.getDefault().post(soldier);
        EventBus.getDefault().unregister(this);
    }
}
