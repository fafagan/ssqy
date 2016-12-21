package com.medicine.ssqy.ssqy.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.db.TempUser;
import com.medicine.ssqy.ssqy.test.DiscussEntity;
import com.medicine.ssqy.ssqy.ui.adapter.ItemDiscussLvAdapter;
import com.medicine.ssqy.ssqy.ui.dialog.DigDiscuss;
import com.medicine.ssqy.ssqy.ui.listener.MyPhoneStateListener;
import com.medicine.ssqy.ssqy.util.BrightnessUtil;
import com.medicine.ssqy.ssqy.util.TimeFormatUtil;
import com.medicine.ssqy.ssqy.util.UtilGetDiscussTime;

import org.xutils.common.util.DensityUtil;

import java.util.List;

public class VideoPlayActivity extends KBaseActivity implements View.OnClickListener {
    private SurfaceView mSvActivityVideoPlay;
    private Button mBtPlayActivityVideoPlay;
    private TextView mTvPlaytimeActivityVideoPlay;
    private ProgressBar mPbActivityVideoPlay;
    private TextView mTvTotaltimeActivityVideoPlay;
    private ImageView mBtLandscapeActivityVideoPlay;
    private TextView mTvVideotitleActivityVideoPlay;
    private TextView mTvVideocontentActivityVideoPlay;
    private TextView mTvCommentsnumActivityVideoPlay;
    private ListView mLvCommentsActivityVideoPlay;
    private CheckBox mCbPlayorstopActivityVideoPlay;
    private LinearLayout mLlLightbarActivityVideoPlay;
    private ProgressBar mPbLightActivityVideoPlay;
    private LinearLayout mLlVolumebarActivityVideoPlay;
    private ProgressBar mPbVolumeActivityVideoPlay;
    private Button mBtBackActivityVideoPlay;
    private TextView mTvVedionameActivityVideoPlay;
    private LinearLayout mLlSvtopbarActivityVideoPlay;
    private LinearLayout mLlSvbottombarActivityVideoPlay;
    private RelativeLayout mRlContentActivityVideoPlay;
    private RelativeLayout mRlActivityVideoPlay;
    private CheckBox mCbDianzanComment;
    private TextView mTvZannumComment;
    private LinearLayout mBtCommentComment;
    private GestureDetector mGestureDetector;
    //监听电话状态
    private MyPhoneStateListener mPhoneStateListener;
    /**
     * 最大声音
     */
    private int mMaxVolume;
    /**
     * 当前声音
     */
    private int mNowVolume = -1;
    /**
     * 当前亮度
     */
    private float mNowBrightness = -1f;
    //是否正在滑动调节音量或者亮度
    boolean scrolling = false;
    
    private boolean firstPlay = true;
    private MediaPlayer mMediaPlayer;
    private SurfaceHolder mSurfaceHolder;
    private boolean isSurfaceCreated = false;        //surface是否已经创建好
    //视频进度
    private int curIndex = 0;
    int totalTime;
    public static final int PlAYTIME_CHANGE = 1;
    public static final int BRIGHTNESS_CHANGE = 2;
    
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            
            switch (msg.what) {
                case PlAYTIME_CHANGE:
                    if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                        int currentPosition = mMediaPlayer.getCurrentPosition();
                        String timeStr = TimeFormatUtil.formatLongToTimeStr((long) currentPosition);
                        mTvPlaytimeActivityVideoPlay.setText(timeStr);
                        mPbActivityVideoPlay.setProgress(currentPosition);
                        mHandler.sendEmptyMessageDelayed(PlAYTIME_CHANGE, 1000);
                    }
                    break;
                default:
                    break;
            }
        }
    };
    private AudioManager mAudioManager;
    private MyVolumeReceiver mMyVolumeReceiver;
    private DigDiscuss mDigDiscuss;
    private List<DiscussEntity> mTempDatas;
    private ItemDiscussLvAdapter mItemDiscussLvAdapter;
    
    @Override
    public int setRootView() {
        return R.layout.activity_video_play;
    }
    
    @Override
    public boolean isUseTitle() {
        return false;
    }
    
    @Override
    public void initViews() {
        mSvActivityVideoPlay = (SurfaceView) findViewById(R.id.sv_activity_video_play);
        mBtPlayActivityVideoPlay = (Button) findViewById(R.id.bt_play_activity_video_play);
        mTvPlaytimeActivityVideoPlay = (TextView) findViewById(R.id.tv_playtime_activity_video_play);
        mPbActivityVideoPlay = (ProgressBar) findViewById(R.id.pb_activity_video_play);
        mTvTotaltimeActivityVideoPlay = (TextView) findViewById(R.id.tv_totaltime_activity_video_play);
        mBtLandscapeActivityVideoPlay = (ImageView) findViewById(R.id.bt_landscape_activity_video_play);
        mTvVideotitleActivityVideoPlay = (TextView) findViewById(R.id.tv_videotitle_activity_video_play);
        mTvVideocontentActivityVideoPlay = (TextView) findViewById(R.id.tv_videocontent_activity_video_play);
        mTvCommentsnumActivityVideoPlay = (TextView) findViewById(R.id.tv_commentsnum_comment);
        mLvCommentsActivityVideoPlay = (ListView) findViewById(R.id.lv_comments_comment);
        mCbDianzanComment = (CheckBox) findViewById(R.id.cb_dianzan_comment);
        mTvZannumComment = (TextView) findViewById(R.id.tv_zannum_comment);
        mBtCommentComment = (LinearLayout) findViewById(R.id.bt_comment_comment);
        mCbPlayorstopActivityVideoPlay = (CheckBox) findViewById(R.id.cb_playorstop_activity_video_play);
        mLlLightbarActivityVideoPlay = (LinearLayout) findViewById(R.id.ll_lightbar_activity_video_play);
        mPbLightActivityVideoPlay = (ProgressBar) findViewById(R.id.pb_light_activity_video_play);
        mLlVolumebarActivityVideoPlay = (LinearLayout) findViewById(R.id.ll_volumebar_activity_video_play);
        mPbVolumeActivityVideoPlay = (ProgressBar) findViewById(R.id.pb_volume_activity_video_play);
        mBtBackActivityVideoPlay = (Button) findViewById(R.id.bt_back_activity_video_play);
        mTvVedionameActivityVideoPlay = (TextView) findViewById(R.id.tv_vedioname_activity_video_play);
        mLlSvtopbarActivityVideoPlay = (LinearLayout) findViewById(R.id.ll_svtopbar_activity_video_play);
        mLlSvbottombarActivityVideoPlay = (LinearLayout) findViewById(R.id.ll_svbottombar_activity_video_play);
        mRlContentActivityVideoPlay = (RelativeLayout) findViewById(R.id.rl_content_activity_video_play);
        mRlActivityVideoPlay = (RelativeLayout) findViewById(R.id.rl_activity_video_play);
     
        mBtPlayActivityVideoPlay.setOnClickListener(this);
        mCbPlayorstopActivityVideoPlay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (firstPlay) {
                        firstPlay = false;
                        play(curIndex);
                        mCbPlayorstopActivityVideoPlay.setChecked(true);
                        mBtPlayActivityVideoPlay.setVisibility(View.INVISIBLE);
                    } else {
                        mMediaPlayer.start();
                        mLlSvtopbarActivityVideoPlay.setVisibility(View.GONE);
                        mHandler.sendEmptyMessageDelayed(PlAYTIME_CHANGE, 1000);
                        mBtPlayActivityVideoPlay.setVisibility(View.INVISIBLE);
                    }
                    
                } else {
                    mMediaPlayer.pause();
                    mLlSvtopbarActivityVideoPlay.setVisibility(View.VISIBLE);
                }
                
            }
        });
        mBtBackActivityVideoPlay.setOnClickListener(this);
        mBtLandscapeActivityVideoPlay.setOnClickListener(this);
        mCbDianzanComment.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    mTvZannumComment.setText("点赞（51）");
                }else {
                    mTvZannumComment.setText("点赞（50）");
                }
            }
        });
        mTempDatas = DiscussEntity.getTempDatas();
        mItemDiscussLvAdapter = new ItemDiscussLvAdapter(this, mTempDatas);
        mLvCommentsActivityVideoPlay.setAdapter(mItemDiscussLvAdapter);
        mDigDiscuss=new DigDiscuss(this);
        mDigDiscuss.setOnConfirmCallback(new DigDiscuss.OnConfirmCallback() {
            @Override
            public void onConfirm(String content) {
                DiscussEntity discussEntity=new DiscussEntity();
                discussEntity.setDetail(content);
                discussEntity.setNickName(TempUser.getNowUser(SharePLogin.getUid()).getNickName());
                discussEntity.setHeadUrl(TempUser.getNowUser(SharePLogin.getUid()).getHeadPicUrl());
                discussEntity.setTime(UtilGetDiscussTime.getTimeNow());
                mItemDiscussLvAdapter.insertToFirst(discussEntity);
                Toast.makeText(mSelf, "评论成功", Toast.LENGTH_SHORT).show();
            }
        });
        mBtCommentComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDigDiscuss.show();
            }
        });
    
      
    }
    
    @Override
    public void initDatas() {
        mMediaPlayer = new MediaPlayer();
        mPhoneStateListener = new MyPhoneStateListener(this, mMediaPlayer);
        TelephonyManager tmgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        tmgr.listen(mPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
        createSurface();
        //保持屏幕为亮的状态
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        initLight();
        initVolume();
        initGestureDetector();
        
        mMyVolumeReceiver = new MyVolumeReceiver();
        registerReceiver(mMyVolumeReceiver, new IntentFilter("android.media.VOLUME_CHANGED_ACTION"));
    }
    
    private void initGestureDetector() {
        mGestureDetector = new GestureDetector(this, new MyGestureListener());
        mSvActivityVideoPlay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mGestureDetector.onTouchEvent(event))
                    return true;
                // 处理手势结束
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_UP:
                        endGesture();
                        break;
                }
                return true;
            }
        });
    }
    
    /**
     * 获取当前屏幕亮度并设置进度
     */
    private void initLight() {
        mNowBrightness = BrightnessUtil.getScreenBrightness(this);
        mPbLightActivityVideoPlay.setMax(100);
        mPbLightActivityVideoPlay.setProgress((int) mNowBrightness * 100);
    }
    
    /**
     * 初始化音量数据
     */
    private void initVolume() {
        // 获取系统最大音量
        mMaxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        // 设置progress的最大值
        mPbVolumeActivityVideoPlay.setMax(mMaxVolume);
        // 获取到当前 设备的音量
        mNowVolume = mAudioManager
                .getStreamVolume(AudioManager.STREAM_MUSIC);
        // 显示音量
        mPbVolumeActivityVideoPlay.setProgress(mNowVolume);
    }
    
    /**
     * 创建视频展示页面
     */
    private void createSurface() {
        mSurfaceHolder = mSvActivityVideoPlay.getHolder();
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); //兼容4.0以下的版本  
        mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
            
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                isSurfaceCreated = false;
                if (mPhoneStateListener.isCalling()) {
                    curIndex = mPhoneStateListener.getCurPosition();
                } else {
                    if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                        curIndex = mMediaPlayer.getCurrentPosition();
                        mMediaPlayer.stop();
                    }
                }
                
            }
            
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                isSurfaceCreated = true;
                mMediaPlayer.seekTo(curIndex);
                mMediaPlayer.setDisplay(mSurfaceHolder);//页面创建好了以后再展示  
            }
            
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
                
            }
        });
        
    }
    
    private void play(final int currentPosition) {
        //获取res/raw文件夹下面的视频文件
        AssetFileDescriptor fileDescriptor = this.getResources().openRawResourceFd(R.raw.test);
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
            mMediaPlayer.setDisplay(mSurfaceHolder);
            mMediaPlayer.setLooping(true);
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mMediaPlayer.seekTo(currentPosition);
                    //准备完成，可以播放了
                    setVideoParams(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);
                    totalTime = mMediaPlayer.getDuration();
                    String totalTimeStr = TimeFormatUtil.formatLongToTimeStr((long) totalTime);
                    
                    mPbActivityVideoPlay.setMax(mMediaPlayer.getDuration());
                    mTvTotaltimeActivityVideoPlay.setText(totalTimeStr);
                    mMediaPlayer.start();
                    mLlSvtopbarActivityVideoPlay.setVisibility(View.GONE);
                    mLlSvbottombarActivityVideoPlay.setVisibility(View.GONE);
                    mLlSvtopbarActivityVideoPlay.setVisibility(View.GONE);
                    mHandler.sendEmptyMessageDelayed(PlAYTIME_CHANGE, 1000);
                }
            });
            
            mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    
                    return false;
                }
            });
        } catch (Exception e) {
            Log.e("VideoPlayActivity", "line:210--VideoPlayActivity--Play--error");
        }
    }
    
    /**
     * 释放播放器资源
     */
    private void releasePlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
    
    /**
     * 暂停
     */
    private void Pause() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            curIndex = mMediaPlayer.getCurrentPosition();
            mMediaPlayer.pause();
        }
    }
    
    private void setVideoParams(boolean isLand) {
        ViewGroup.LayoutParams layoutParams_sv = mSvActivityVideoPlay.getLayoutParams();
        float screenWidth = this.getResources().getDisplayMetrics().widthPixels;
        float screenHeight = this.getResources().getDisplayMetrics().heightPixels;
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (isLand) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            mRlContentActivityVideoPlay.setVisibility(View.GONE);
            ViewGroup.LayoutParams layoutParams = mRlActivityVideoPlay.getLayoutParams();
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            mRlActivityVideoPlay.setLayoutParams(layoutParams);
        } else {
            mRlContentActivityVideoPlay.setVisibility(View.VISIBLE);
            ViewGroup.LayoutParams layoutParams = mRlActivityVideoPlay.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = DensityUtil.dip2px(220);
            mRlActivityVideoPlay.setLayoutParams(layoutParams);
        }
        float videoWidth = mMediaPlayer.getVideoWidth();
        float videoHeight = mMediaPlayer.getVideoHeight();
        System.out.println("videoWidth:" + videoWidth + "--------" + "videoHeight:" + videoHeight);
        float screenPor = screenWidth / screenHeight;
        float videoPor = videoWidth / videoHeight;
        if (videoPor < screenPor) {
            layoutParams_sv.height = (int) screenHeight;
            layoutParams_sv.width = (int) (screenHeight * videoPor);
        } else {
            layoutParams_sv.width = (int) screenWidth;
            layoutParams_sv.height = (int) (screenWidth / videoPor);
        }
        mSvActivityVideoPlay.setLayoutParams(layoutParams_sv);
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //播放
            case R.id.bt_play_activity_video_play:
                firstPlay = false;
                play(curIndex);
                mCbPlayorstopActivityVideoPlay.setChecked(true);
                mBtPlayActivityVideoPlay.setVisibility(View.INVISIBLE);
                break;
            //回退
            case R.id.bt_back_activity_video_play:
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    //竖屏
                    this.finish();
                } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    //横屏
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    setVideoParams(false);
                }
                break;
            //横竖屏切换
            case R.id.bt_landscape_activity_video_play:
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    //变成横屏
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//                    setVideoLayoutParams(true);
                    setVideoParams(true);
                } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    //变成竖屏
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                    setVideoLayoutParams(false);
                    setVideoParams(false);
                }
            default:
                
                break;
        }
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //变成横屏了
            setVideoParams(true);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            //变成竖屏了
            setVideoParams(false);
        }
    }
    
    /**
     * 创建完毕页面后需要将播放操作延迟10ms防止因surface创建不及时导致播放失败
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (mCbPlayorstopActivityVideoPlay.isChecked()) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (isSurfaceCreated) {
                        play(curIndex);
                    }
                }
            }, 10);
        }
    }
    
    /**
     * 页面从前台到后台会执行 onPause ->onStop 此时Surface会被销毁，
     * 再一次从后台 到前台时需要 重新创建Surface
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        if (!isSurfaceCreated) {
            createSurface();
        }
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        Pause();
    }
    
    @Override
    protected void onStop() {
        super.onStop();
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        TelephonyManager tmgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        tmgr.listen(mPhoneStateListener, 0);
        releasePlayer();
        this.unregisterReceiver(mMyVolumeReceiver);
    }
    
    /**
     * 手势结束
     */
    private void endGesture() {
        scrolling = false;
        // 隐藏
        mDismissHandler.removeMessages(0);
        mDismissHandler.sendEmptyMessageDelayed(0, 500);
    }
    
    /**
     * 定时隐藏
     */
    private Handler mDismissHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    mLlLightbarActivityVideoPlay.setVisibility(View.GONE);
                    mLlVolumebarActivityVideoPlay.setVisibility(View.GONE);
                    break;
                case 1:
                    mLlSvbottombarActivityVideoPlay.setVisibility(View.GONE);
                    mLlSvtopbarActivityVideoPlay.setVisibility(View.GONE);
                    break;
                default:
                    
                    break;
            }
        }
    };
    
    /**
     * 滑动改变声音大小
     *
     * @param percent
     */
    private void onVolumeSlide(float percent) {
        if (mNowVolume == -1) {
            mNowVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            if (mNowVolume < 0) {
                mNowVolume = 0;
            }
        }
        // 显示
        mLlLightbarActivityVideoPlay.setVisibility(View.GONE);
        mLlVolumebarActivityVideoPlay.setVisibility(View.VISIBLE);
        
        int index = (int) (percent * mMaxVolume) + mNowVolume;
        if (index > mMaxVolume)
            index = mMaxVolume;
        else if (index < 0)
            index = 0;
        mNowVolume = index;
        // 变更声音
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, index, 0);
        
    }
    
    /**
     * 滑动改变亮度
     *
     * @param percent
     */
    private void onBrightnessSlide(float percent) {
        if (mNowBrightness < 0) {
            mNowBrightness = getWindow().getAttributes().screenBrightness;
            if (mNowBrightness <= 0.00f)
                mNowBrightness = 0.50f;
            if (mNowBrightness < 0.01f)
                mNowBrightness = 0.01f;
            
        }
        // 显示
        mLlVolumebarActivityVideoPlay.setVisibility(View.GONE);
        mLlLightbarActivityVideoPlay.setVisibility(View.VISIBLE);
        //设置屏幕亮度
        mNowBrightness += percent;
        BrightnessUtil.setScreenBrightness(this, mNowBrightness);
        //更新进度条
        mPbLightActivityVideoPlay.setProgress((int) (mNowBrightness * 100));
    }
    
    public class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            if (mLlSvtopbarActivityVideoPlay.getVisibility() == View.VISIBLE) {
                mLlSvtopbarActivityVideoPlay.setVisibility(View.GONE);
                mLlSvbottombarActivityVideoPlay.setVisibility(View.GONE);
            } else {
                mLlSvbottombarActivityVideoPlay.setVisibility(View.VISIBLE);
                mLlSvtopbarActivityVideoPlay.setVisibility(View.VISIBLE);
                mDismissHandler.sendEmptyMessageDelayed(1, 3000);
            }
            return true;
        }
        
        /**
         * 滑动
         */
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            scrolling = true;
            float mOldX = e1.getX(), mOldY = e1.getY();
            int y = (int) e2.getRawY();
            Display disp = getWindowManager().getDefaultDisplay();
            int windowWidth = disp.getWidth();
            int windowHeight = disp.getHeight();
            
            if (mOldX > windowWidth * 2.0 / 3) {// 右边滑动
                onVolumeSlide((mOldY - y) / windowHeight);
            } else if (mOldX < windowWidth / 3.0) {// 左边滑动
                onBrightnessSlide((mOldY - y) / windowHeight);
            }
            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }
    
    class MyVolumeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //监听系统音量变化
            mPbVolumeActivityVideoPlay.setProgress(mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
        }
    }
    
    @Override
    public void onBackPressed() {
//     15097635771
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            //竖屏
            super.onBackPressed();
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //横屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            setVideoParams(false);
        }
    }
}
