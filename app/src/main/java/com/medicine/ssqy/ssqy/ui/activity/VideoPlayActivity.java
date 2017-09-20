package com.medicine.ssqy.ssqy.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sj.mylibrary.net.NetCallback;
import com.example.sj.mylibrary.net.NetForJson;
import com.ksyun.media.player.IMediaPlayer;
import com.ksyun.media.player.KSYMediaPlayer;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.common.URLConstant;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.db.TempUser;
import com.medicine.ssqy.ssqy.entity.course.CourseVedioDetailEntity;
import com.medicine.ssqy.ssqy.entity.course.CourseVedioEntity;
import com.medicine.ssqy.ssqy.entity.course.CourseVideoProgressEntity;
import com.medicine.ssqy.ssqy.task.download.controller.DownloadController;
import com.medicine.ssqy.ssqy.test.DiscussEntity;
import com.medicine.ssqy.ssqy.ui.adapter.ItemDiscussLvAdapter;
import com.medicine.ssqy.ssqy.ui.dialog.DigDiscuss;
import com.medicine.ssqy.ssqy.ui.listener.MyPhoneStateListener;
import com.medicine.ssqy.ssqy.util.BrightnessUtil;
import com.medicine.ssqy.ssqy.util.ShareUtil;
import com.medicine.ssqy.ssqy.util.TimeFormatUtil;
import com.medicine.ssqy.ssqy.util.UtilGetDiscussTime;
import com.orhanobut.logger.Logger;

import org.xutils.common.util.DensityUtil;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class VideoPlayActivity extends KBaseActivity implements View.OnClickListener {
    private SurfaceView mSvActivityVideoPlay;
    private Button mBtPlayActivityVideoPlay;
    private TextView mTvPlaytimeActivityVideoPlay;
    private ProgressBar mPbActivityVideoPlay;
    private TextView mTvTotaltimeActivityVideoPlay;
    private ImageView mBtLandscapeActivityVideoPlay;
    private TextView mTvVideotitleActivityVideoPlay;
    private TextView mTvVideocontentActivityVideoPlay;
    //    private TextView mTvCommentsnumActivityVideoPlay;
//    private ListView mLvCommentsActivityVideoPlay;
    private CheckBox mCbPlayorstopActivityVideoPlay;
    private LinearLayout mLlLightbarActivityVideoPlay;
    private ProgressBar mPbLightActivityVideoPlay;
    private LinearLayout mLlVolumebarActivityVideoPlay;
    private ProgressBar mPbVolumeActivityVideoPlay;
    private Button mBtBackActivityVideoPlay;
    private TextView mTvVedionameActivityVideoPlay;
    private LinearLayout mLlSvbottombarActivityVideoPlay;
    private RelativeLayout mRlContentActivityVideoPlay;
    private RelativeLayout mRlActivityVideoPlay;
    private RelativeLayout mRlSvtopbarActivityVideoPlay;
    //    private CheckBox mCbDianzanComment;
//    private TextView mTvZannumComment;
//    private LinearLayout mBtCommentComment;
    private TextView mTvShareActivityVideoPlay;
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
//    private MediaPlayer mMediaPlayer;
        private KSYMediaPlayer mMediaPlayer;
    private SurfaceHolder mSurfaceHolder;
    private boolean isSurfaceCreated = false;        //surface是否已经创建好
    //视频进度
    private int curIndex = 0;
    int totalTime;
    public static final int PlAYTIME_CHANGE = 1;
    public static final int BRIGHTNESS_CHANGE = 2;
    private boolean mPrepareOk = false;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            
            switch (msg.what) {
                case PlAYTIME_CHANGE:
                    if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                        long currentPosition = mMediaPlayer.getCurrentPosition();
                        String timeStr = TimeFormatUtil.formatLongToTimeStr((long) currentPosition);
                        mTvPlaytimeActivityVideoPlay.setText(timeStr);
                        mPbActivityVideoPlay.setProgress((int) currentPosition);
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
    private NetForJson mNetForJson;
    private boolean mLoadOk = false;
    private boolean mHasStart = false;
    private CourseVedioDetailEntity mEntityNow;
    private TextView mTvDownloadActivityVideoPlay;

    
    private IMediaPlayer.OnSeekCompleteListener mOnSeekCompletedListener = new IMediaPlayer.OnSeekCompleteListener() {
        @Override
        public void onSeekComplete(IMediaPlayer mp) {
       
        }
    };
    
    private IMediaPlayer.OnCompletionListener mOnCompletionListener = new IMediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(IMediaPlayer mp) {
         Toast.makeText(mSelf, "当前养生视频播放完毕!", Toast.LENGTH_LONG).show();
            mCbPlayorstopActivityVideoPlay.setChecked(false);
            mBtPlayActivityVideoPlay.setVisibility(View.VISIBLE);
            mTvPlaytimeActivityVideoPlay.setText(mTvTotaltimeActivityVideoPlay.getText());
            mIsLoop=true;
//           videoPlayEnd();
        }
    };
    private boolean mCBFirst =true;
    
    private void videoPlayEnd() {
        if(mMediaPlayer != null)
        {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        
    }
    private boolean mIsLoop=false;
    private IMediaPlayer.OnErrorListener mOnErrorListener = new IMediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(IMediaPlayer mp, int what, int extra) {
//            switch (what)
//            {
//                case KSYMediaPlayer.MEDIA_ERROR_UNKNOWN:
//                    Log.e(TAG, "OnErrorListener, Error Unknown:" + what + ",extra:" + extra);
//                    break;
//                default:
//                    Log.e(TAG, "OnErrorListener, Error:" + what + ",extra:" + extra);
//            }
//            
//            videoPlayEnd();
//            
    
            Toast.makeText(mSelf, "网络异常，请检查您的网络状态！", Toast.LENGTH_LONG).show();
            if(mMediaPlayer != null)
            {
//                mMediaPlayer.release();
//                mMediaPlayer = null;
                mMediaPlayer.reset();
                mHasStart=false;
                mCbPlayorstopActivityVideoPlay.setChecked(false);
                mPrepareOk=false;
                
            }
            return false;
        }
    };
    
    private IMediaPlayer.OnErrorListener mOnErrorListenerCache = new IMediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(IMediaPlayer mp, int what, int extra) {
//            
            
//            Toast.makeText(mSelf, "网络异常，请检查您的网络状态！", Toast.LENGTH_LONG).show();
            if(mMediaPlayer != null)
            {
//                mMediaPlayer.release();
//                mMediaPlayer = null;
                mMediaPlayer.reset();
                mPrepareOk=false;
            }else {
                mMediaPlayer=new KSYMediaPlayer.Builder(mSelf).build();
            }
    
            try {
                mMediaPlayer.setDataSource(mEntityNow.getCourseUrl());
            } catch (IOException e) {
                e.printStackTrace();
            }
            mMediaPlayer.setDisplay(mSurfaceHolder);
            mMediaPlayer.setOnErrorListener(mOnErrorListener);
            mMediaPlayer.setLooping(false);
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
        
                @Override
                public void onPrepared(IMediaPlayer mp) {
                    mMediaPlayer.pause();
                    mPrepareOk = true;
//                    mMediaPlayer.seekTo(currentPosition);
                    //准备完成，可以播放了
                    setVideoParams(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);
                    totalTime = mEntityNow.getCourseLength();
//                    totalTime = mMediaPlayer.getDuration();
                    String totalTimeStr = TimeFormatUtil.formatLongToTimeStr((long) totalTime);

//                    mPbActivityVideoPlay.setMax(mMediaPlayer.getDuration());
                    mPbActivityVideoPlay.setMax(mEntityNow.getCourseLength());
                    mTvTotaltimeActivityVideoPlay.setText(totalTimeStr);
            
            
                    if (mHasStart) {
                        Logger.e("准备玩。。。开始了");
                        mMediaPlayer.start();
                        if (mEntityNow.getCourseStudy()!=0&&mEntityNow.getCourseStudy()<mEntityNow.getCourseLength()){
//                            mMediaPlayer.seekTo(mEntityNow.getCourseStudy());
                            if (mIsLoop) {
                                mMediaPlayer.seekTo(0);
                            }else{
//                                    mMediaPlayer.seekTo(1000*60*4);
                                mMediaPlayer.seekTo( mEntityNow.getCourseStudy());
                                //mEntityNow.getCourseStudy()
                            }
                        }
                        if (!mHasBeginSycn) {
                            mHasBeginSycn=true;
                            mTimerSyncLearnProgress.schedule(mTimerTaskSyncLearnProgress,5000,5000);
                        }
                
                    }
//                   
                    mRlSvtopbarActivityVideoPlay.setVisibility(View.GONE);
                    mLlSvbottombarActivityVideoPlay.setVisibility(View.GONE);
                    mRlSvtopbarActivityVideoPlay.setVisibility(View.GONE);
                    mHandler.sendEmptyMessageDelayed(PlAYTIME_CHANGE, 1000);
                }
            });
            return false;
        }
    };
    public IMediaPlayer.OnInfoListener mOnInfoListener = new IMediaPlayer.OnInfoListener() {
        @Override
        public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
            switch (i) {
                case KSYMediaPlayer.MEDIA_INFO_BUFFERING_START:
//                    Log.d(TAG, "Buffering Start.");
                    break;
                case KSYMediaPlayer.MEDIA_INFO_BUFFERING_END:
                    //         Log.d(TAG, "Buffering End.");
//                    Toast.makeText(mSelf, "xx", Toast.LENGTH_SHORT).show();
                    break;
                case KSYMediaPlayer.MEDIA_INFO_AUDIO_RENDERING_START:
                    //       Toast.makeText(mContext, "Audio Rendering Start", Toast.LENGTH_SHORT).show();
                    break;
                case KSYMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                    //         Toast.makeText(mContext, "Video Rendering Start", Toast.LENGTH_SHORT).show();
                    break;
                case KSYMediaPlayer.MEDIA_INFO_SUGGEST_RELOAD:
                    // Player find a new stream(video or audio), and we could reload the video.
//                    if(ksyMediaPlayer != null)
//                        ksyMediaPlayer.reload(mDataSource, false, KSYMediaPlayer.KSYReloadMode.KSY_RELOAD_MODE_ACCURATE);
                    break;
                case KSYMediaPlayer.MEDIA_INFO_RELOADED:
//                    Toast.makeText(mContext, "Succeed to reload video.", Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "Succeed to reload video.");
                    return false;
            }
            return false;
        }
    };
    
    private Button mBtReplayVedio;
    
    private Timer mTimerSyncLearnProgress=new Timer();
    private TimerTask mTimerTaskSyncLearnProgress=new TimerTask() {
        @Override
        public void run() {
            mNetForJsonSyncLearnProgress.addParam("uid",SharePLogin.getUid());
            mNetForJsonSyncLearnProgress.addParam("type","video");
            mNetForJsonSyncLearnProgress.addParam("courseID",mEntityNow.getCourseID());
            mNetForJsonSyncLearnProgress.addParam("learnProgress",mMediaPlayer.getCurrentPosition());
            if (!mEntityNow.isCourseLearned()) {
                if (mMediaPlayer.getCurrentPosition()>=mEntityNow.getCourseLength()*0.94){
                    mNetForJsonSyncLearnProgress.addParam("courseLearned","true");
                }else {
                    mNetForJsonSyncLearnProgress.addParam("courseLearned","false");
                }
            }else {
                mNetForJsonSyncLearnProgress.addParam("courseLearned","true");
            }
            mNetForJsonSyncLearnProgress.excute();
        }
    };
    private boolean mHasBeginSycn=false;
    private NetForJson mNetForJsonSyncLearnProgress=new NetForJson(URLConstant.VIDEO_PROGRESS_URL, new NetCallback<CourseVideoProgressEntity>() {
        @Override
        public void onSuccess(CourseVideoProgressEntity entity) {
        
        }
    
        @Override
        public void onError() {
            
        }
    
        @Override
        public void onFinish() {
        
        }
    },true);
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
        mTvDownloadActivityVideoPlay = (TextView) findViewById(R.id.tv_download_activity_video_play);
        mTvShareActivityVideoPlay = (TextView) findViewById(R.id.tv_share_activity_video_play);
        mSvActivityVideoPlay = (SurfaceView) findViewById(R.id.sv_activity_video_play);
        mBtPlayActivityVideoPlay = (Button) findViewById(R.id.bt_play_activity_video_play);
        mTvPlaytimeActivityVideoPlay = (TextView) findViewById(R.id.tv_playtime_activity_video_play);
        mPbActivityVideoPlay = (ProgressBar) findViewById(R.id.pb_activity_video_play);
        mTvTotaltimeActivityVideoPlay = (TextView) findViewById(R.id.tv_totaltime_activity_video_play);
        mBtLandscapeActivityVideoPlay = (ImageView) findViewById(R.id.bt_landscape_activity_video_play);
        mTvVideotitleActivityVideoPlay = (TextView) findViewById(R.id.tv_videotitle_activity_video_play);
        mTvVideocontentActivityVideoPlay = (TextView) findViewById(R.id.tv_videocontent_activity_video_play);
//        mTvCommentsnumActivityVideoPlay = (TextView) findViewById(R.id.tv_commentsnum_comment);
//        mLvCommentsActivityVideoPlay = (ListView) findViewById(R.id.lv_comments_comment);
//        mCbDianzanComment = (CheckBox) findViewById(R.id.cb_dianzan_comment);
//        mTvZannumComment = (TextView) findViewById(R.id.tv_zannum_comment);
//        mBtCommentComment = (LinearLayout) findViewById(R.id.bt_comment_comment);
        mCbPlayorstopActivityVideoPlay = (CheckBox) findViewById(R.id.cb_playorstop_activity_video_play);
        mLlLightbarActivityVideoPlay = (LinearLayout) findViewById(R.id.ll_lightbar_activity_video_play);
        mPbLightActivityVideoPlay = (ProgressBar) findViewById(R.id.pb_light_activity_video_play);
        mLlVolumebarActivityVideoPlay = (LinearLayout) findViewById(R.id.ll_volumebar_activity_video_play);
        mPbVolumeActivityVideoPlay = (ProgressBar) findViewById(R.id.pb_volume_activity_video_play);
        mBtBackActivityVideoPlay = (Button) findViewById(R.id.bt_back_activity_video_play);
        mTvVedionameActivityVideoPlay = (TextView) findViewById(R.id.tv_vedioname_activity_video_play);
        mRlSvtopbarActivityVideoPlay = (RelativeLayout) findViewById(R.id.rl_svtopbar_activity_video_play);
        mLlSvbottombarActivityVideoPlay = (LinearLayout) findViewById(R.id.ll_svbottombar_activity_video_play);
        mRlContentActivityVideoPlay = (RelativeLayout) findViewById(R.id.rl_content_activity_video_play);
        mRlActivityVideoPlay = (RelativeLayout) findViewById(R.id.rl_activity_video_play);
    
        mBtReplayVedio = (Button) findViewById(R.id.bt_replay_vedio);
        mBtPlayActivityVideoPlay.setOnClickListener(this);
        mTvShareActivityVideoPlay.setOnClickListener(this);
        mTvDownloadActivityVideoPlay.setOnClickListener(this);
        mCbPlayorstopActivityVideoPlay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mMediaPlayer==null) {
                    Toast.makeText(mSelf, "网络异常，请退出重试", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (isChecked) {
                    Logger.e("开始了。。");
                    if (firstPlay) {
                        firstPlay = false;
                        play(curIndex);
                        mCbPlayorstopActivityVideoPlay.setChecked(true);
                        mBtPlayActivityVideoPlay.setVisibility(View.INVISIBLE);
                    } else {
                        if (!mPrepareOk) {
                            return;
                        }
                        mMediaPlayer.start();
                        mRlSvtopbarActivityVideoPlay.setVisibility(View.GONE);
                        mHandler.sendEmptyMessageDelayed(PlAYTIME_CHANGE, 1000);
                        mBtPlayActivityVideoPlay.setVisibility(View.INVISIBLE);
                    }
                } else {
                    if (!mPrepareOk) {
                        return;
                    }
                    mMediaPlayer.pause();
                    mRlSvtopbarActivityVideoPlay.setVisibility(View.VISIBLE);
                }
                
            }
        });
    
        mBtReplayVedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaPlayer!=null) {
                    try {
                        mMediaPlayer.seekTo(0);
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    }
    
                }
            }
        });
        mBtBackActivityVideoPlay.setOnClickListener(this);
        mBtLandscapeActivityVideoPlay.setOnClickListener(this);
//        mCbDianzanComment.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked){
//                    mTvZannumComment.setText("点赞（51）");
//                }else {
//                    mTvZannumComment.setText("点赞（50）");
//                }
//            }
//        });
        mTempDatas = DiscussEntity.getTempDatas();
//        mItemDiscussLvAdapter = new ItemDiscussLvAdapter(this, mTempDatas);
//        mLvCommentsActivityVideoPlay.setAdapter(mItemDiscussLvAdapter);
        mDigDiscuss = new DigDiscuss(this);
        mDigDiscuss.setOnConfirmCallback(new DigDiscuss.OnConfirmCallback() {
            @Override
            public void onConfirm(String content) {
                DiscussEntity discussEntity = new DiscussEntity();
                discussEntity.setDetail(content);
                discussEntity.setNickName(TempUser.getNowUser(SharePLogin.getUid()).getNickName());
                discussEntity.setHeadUrl(TempUser.getNowUser(SharePLogin.getUid()).getHeadPicUrl());
                discussEntity.setTime(UtilGetDiscussTime.getTimeNow());
                mItemDiscussLvAdapter.insertToFirst(discussEntity);
                Toast.makeText(mSelf, "评论成功", Toast.LENGTH_SHORT).show();
            }
        });
//        mBtCommentComment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mDigDiscuss.show();
//            }
//        });
        
        
    }
    private void  initMP(){
        //        mMediaPlayer = new MediaPlayer();
        mMediaPlayer = new KSYMediaPlayer.Builder(this).build();
//        mMediaPlayer.setOnBufferingUpdateListener(mOnBufferingUpdateListener);
        mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
//        mMediaPlayer.setOnPreparedListener(mOnPreparedListener);
//        mMediaPlayer.setOnInfoListener(mOnInfoListener);
//        mMediaPlayer.setOnVideoSizeChangedListener(mOnVideoSizeChangeListener);
//        mMediaPlayer.setOnErrorListener(mOnErrorListener);
//        mMediaPlayer.setOnSeekCompleteListener(mOnSeekCompletedListener);
        mMediaPlayer.setScreenOnWhilePlaying(true);
        mMediaPlayer.setBufferTimeMax(3.0f);
        mMediaPlayer.setTimeout(500, 3000);
        mMediaPlayer.setDecodeMode(KSYMediaPlayer.KSYDecodeMode.KSY_DECODE_MODE_AUTO);
        TelephonyManager tmgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (mPhoneStateListener != null) {
           
            tmgr.listen(mPhoneStateListener, 0);
        }
        
        mPhoneStateListener = new MyPhoneStateListener(this, mMediaPlayer);
        tmgr.listen(mPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
    }
    @Override
    public void initDatas() {
        initMP();
        createSurface();
        //保持屏幕为亮的状态
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        initLight();
        initVolume();
        initGestureDetector();
        
        mMyVolumeReceiver = new MyVolumeReceiver();
        registerReceiver(mMyVolumeReceiver, new IntentFilter("android.media.VOLUME_CHANGED_ACTION"));
        
        CourseVedioEntity.VideoCourseDataEntity videoCourseDataEntity = (CourseVedioEntity.VideoCourseDataEntity) this.getIntent().getSerializableExtra("vd");
        mTvVideotitleActivityVideoPlay.setText("四时七养--"+videoCourseDataEntity.getCourseTitle());
        mTvTotaltimeActivityVideoPlay.setText(TimeFormatUtil.formatLongToTimeStr((long) videoCourseDataEntity.getCourseLength()));
        mTvVedionameActivityVideoPlay.setText("四时七养--"+videoCourseDataEntity.getCourseTitle());
//        mTvVedionameActivityVideoPlay.setText();
        mNetForJson = new NetForJson(URLConstant.VIDEO_DETAIL_URL, new NetCallback<CourseVedioDetailEntity>() {
            
            
            @Override
            public void onSuccess(CourseVedioDetailEntity entity) {
                mLoadOk = true;
                mEntityNow = entity;
                mTvVideocontentActivityVideoPlay.setText("视频简介： "+entity.getCourseDetail());
                if (!mPrepareOk) {
                    prepareMP();
                }
            }
            
            @Override
            public void onError() {
                Toast.makeText(mSelf, "加载失败，请检查您的网络状态！", Toast.LENGTH_SHORT).show();
                mLoadOk = false;
            }
            
            @Override
            public void onFinish() {
                
            }
        }, true);
        
        mNetForJson.addParam("courseID", videoCourseDataEntity.getCourseID());
        mNetForJson.addParam("uid", SharePLogin.getUid());
        mNetForJson.addParam("type", "video");
        mNetForJson.excute();
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
                        curIndex = (int) mMediaPlayer.getCurrentPosition();
                        mMediaPlayer.stop();
                        mPrepareOk = false;
                    }
                }
                
            }
            
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                isSurfaceCreated = true;
//                mMediaPlayer.seekTo(curIndex);
                if (mMediaPlayer != null) {
                    mMediaPlayer.setDisplay(mSurfaceHolder);//页面创建好了以后再展示  
                }

            }
            
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
                
            }
        });
        
    }
    
    private void play(final int currentPosition) {
        Logger.e("play--->");
        if (mIsLoop){
            mMediaPlayer.release();
            initMP();
            mMediaPlayer.setDisplay(mSurfaceHolder);
            prepareMP();
         
        }
        mHasStart = !mHasStart;
        if (!mLoadOk) {
            return;
        }
        if (!mPrepareOk) {
            Toast.makeText(mSelf, "正在缓冲视频，请稍等", Toast.LENGTH_SHORT).show();
            prepareMP();
           
        }else {
           
            if (mCBFirst){
                mCBFirst=false;
                mMediaPlayer.seekTo(mEntityNow.getCourseStudy());
            }
            mMediaPlayer.start();
            if (!mHasBeginSycn) {
                mHasBeginSycn=true;
                mTimerSyncLearnProgress.schedule(mTimerTaskSyncLearnProgress,5000,5000);
            }
        }
     
    }
    
    private void prepareMP() {
        if (mEntityNow == null) {
            return;
        }
        //获取res/raw文件夹下面的视频文件
//        AssetFileDescriptor fileDescriptor = this.getResources().openRawResourceFd(R.raw.test);
        try {
            mMediaPlayer.reset();
//            mMediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
//            mMediaPlayer.setDataSource("http://192.168.1.21:8080/test/test.mp4");
            if (mDownloadController == null) {
                mDownloadController=new DownloadController();
            }
            String cacheUrl = mDownloadController.findCourseCacheUrl(mEntityNow.getCourseID());
            if (cacheUrl != null) {
                Toast.makeText(mSelf, "已缓存完毕，为您零流量播放视频！", Toast.LENGTH_LONG).show();
                mMediaPlayer.setDataSource(cacheUrl);
                mMediaPlayer.setDisplay(mSurfaceHolder);
                mMediaPlayer.setOnErrorListener(mOnErrorListenerCache);
                mMediaPlayer.setLooping(false);
                mMediaPlayer.prepareAsync();
                mMediaPlayer.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
        
                    @Override
                    public void onPrepared(IMediaPlayer mp) {
                        mMediaPlayer.pause();
                        mPrepareOk = true;
//                    mMediaPlayer.seekTo(currentPosition);
                        //准备完成，可以播放了
                        setVideoParams(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);
                        totalTime = mEntityNow.getCourseLength();
//                    totalTime = mMediaPlayer.getDuration();
                        String totalTimeStr = TimeFormatUtil.formatLongToTimeStr((long) totalTime);

//                    mPbActivityVideoPlay.setMax(mMediaPlayer.getDuration());
                        mPbActivityVideoPlay.setMax(mEntityNow.getCourseLength());
                        mTvTotaltimeActivityVideoPlay.setText(totalTimeStr);
                        mTvDownloadActivityVideoPlay.setText("已缓存");
                        
                        if (mHasStart||mIsLoop) {
                          
                            Logger.e("准备玩。。。开始了");
                            mMediaPlayer.start();
                            if (mEntityNow.getCourseStudy()!=0&&mEntityNow.getCourseStudy()<mEntityNow.getCourseLength()){
//                                mMediaPlayer.seekTo(mEntityNow.getCourseStudy());
                                if (mIsLoop) {
                                    mMediaPlayer.seekTo(0);
                                }else{
//                                    mMediaPlayer.seekTo(1000*60*4);
                                    mMediaPlayer.seekTo( mEntityNow.getCourseStudy());
        
                                   
                                }
                            }
                            if (!mHasBeginSycn) {
                                mHasBeginSycn=true;
                                mTimerSyncLearnProgress.schedule(mTimerTaskSyncLearnProgress,5000,5000);
                            }
                            mIsLoop=false;
                        }
//                   
                        mRlSvtopbarActivityVideoPlay.setVisibility(View.GONE);
                        mLlSvbottombarActivityVideoPlay.setVisibility(View.GONE);
                        mRlSvtopbarActivityVideoPlay.setVisibility(View.GONE);
                        mHandler.sendEmptyMessageDelayed(PlAYTIME_CHANGE, 1000);
                    }
                });
            }else {
                try {
                    mMediaPlayer.setDataSource(mEntityNow.getCourseUrl());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mMediaPlayer.setDisplay(mSurfaceHolder);
                mMediaPlayer.setOnErrorListener(mOnErrorListener);
                mMediaPlayer.setLooping(false);
                mMediaPlayer.prepareAsync();
                mMediaPlayer.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
        
                    @Override
                    public void onPrepared(IMediaPlayer mp) {
                        mMediaPlayer.pause();
                        mPrepareOk = true;
//                    mMediaPlayer.seekTo(currentPosition);
                        //准备完成，可以播放了
                        setVideoParams(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);
                        totalTime = mEntityNow.getCourseLength();
//                    totalTime = mMediaPlayer.getDuration();
                        String totalTimeStr = TimeFormatUtil.formatLongToTimeStr((long) totalTime);

//                    mPbActivityVideoPlay.setMax(mMediaPlayer.getDuration());
                        mPbActivityVideoPlay.setMax(mEntityNow.getCourseLength());
                        mTvTotaltimeActivityVideoPlay.setText(totalTimeStr);
            
            
                        if (mHasStart||mIsLoop) {
                         
                            Logger.e("准备玩。。。开始了");
                            mMediaPlayer.start();
                            if (mEntityNow.getCourseStudy()!=0&&mEntityNow.getCourseStudy()<mEntityNow.getCourseLength()){
                                if (mIsLoop) {
                                    mMediaPlayer.seekTo(0);
                                }else{
//                                    mMediaPlayer.seekTo(1000*60*4);
                                    mMediaPlayer.seekTo( mEntityNow.getCourseStudy());
                                    //mEntityNow.getCourseStudy()
                                }
                               
                            }
                            if (!mHasBeginSycn) {
                                mHasBeginSycn=true;
                                mTimerSyncLearnProgress.schedule(mTimerTaskSyncLearnProgress,5000,5000);
                            }
                            mIsLoop=false;
                        }
//                   
                        mRlSvtopbarActivityVideoPlay.setVisibility(View.GONE);
                        mLlSvbottombarActivityVideoPlay.setVisibility(View.GONE);
                        mRlSvtopbarActivityVideoPlay.setVisibility(View.GONE);
                        mHandler.sendEmptyMessageDelayed(PlAYTIME_CHANGE, 1000);
                    }
                });
            }
         
            
//            mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
//                
//                @Override
//                public boolean onError(MediaPlayer mp, int what, int extra) {
//                    mPrepareOk = false;
////                    Toast.makeText(mSelf, "网络异常，请退出重试！", Toast.LENGTH_SHORT).show();
//                    return false;
//                }
//            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("VideoPlayActivity", "line:210--VideoPlayActivity--Play--error");
            mPrepareOk = false;
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
            curIndex = (int) mMediaPlayer.getCurrentPosition();
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
            screenHeight=DensityUtil.dip2px(220);
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
    private DownloadController mDownloadController;
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
                break;
            //分享    
            case R.id.tv_share_activity_video_play:
                ShareUtil.showShare(this);
                break;
            case R.id.tv_download_activity_video_play:
                if (mEntityNow==null) {
                    Toast.makeText(mSelf, "网络状态异常，请退出重试！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mDownloadController == null) {
                    mDownloadController=new DownloadController();
                }
                String courseCacheUrl = mDownloadController.findCourseCacheUrl(mEntityNow.getCourseID());
                if (courseCacheUrl != null) {
                    Toast.makeText(mSelf, "该视频已缓存完毕，如需重新下载请先清除缓存！", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                if (mDownloadController.findCourseCacheIng(mEntityNow.getCourseID())){
                    Toast.makeText(mSelf, "视频正在努力缓存，请稍等！", Toast.LENGTH_SHORT).show();
                    return;
                    
                }
            
                mDownloadController.addTasksToQueue(mEntityNow);
                Toast.makeText(mSelf, "开始为您缓存该视频！", Toast.LENGTH_SHORT).show();
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
        if (mHasStart&&mCbPlayorstopActivityVideoPlay.isChecked()) {
            
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
        mTimerSyncLearnProgress.cancel();
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
                    mRlSvtopbarActivityVideoPlay.setVisibility(View.GONE);
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
            mDismissHandler.removeMessages(1);
            if (mRlSvtopbarActivityVideoPlay.getVisibility() == View.VISIBLE) {
                mRlSvtopbarActivityVideoPlay.setVisibility(View.GONE);
                mLlSvbottombarActivityVideoPlay.setVisibility(View.GONE);
            } else {
                mLlSvbottombarActivityVideoPlay.setVisibility(View.VISIBLE);
                mRlSvtopbarActivityVideoPlay.setVisibility(View.VISIBLE);
                mDismissHandler.sendEmptyMessageDelayed(1, 5000);
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
