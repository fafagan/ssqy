package com.medicine.ssqy.ssqy.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sj.mylibrary.net.NetCallback;
import com.example.sj.mylibrary.net.NetForJson;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.entity.course.CourseAudioEntity;
import com.medicine.ssqy.ssqy.entity.course.CourseAudioListEntity;
import com.medicine.ssqy.ssqy.eventBus.MusicSoldier;
import com.medicine.ssqy.ssqy.service.MusicService;
import com.medicine.ssqy.ssqy.test.DiscussEntity;
import com.medicine.ssqy.ssqy.ui.adapter.ItemDiscussLvAdapter;
import com.medicine.ssqy.ssqy.ui.dialog.DigDiscuss;
import com.medicine.ssqy.ssqy.util.ShareUtil;
import com.medicine.ssqy.ssqy.util.TimeFormatUtil;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import static com.medicine.ssqy.ssqy.common.URLConstant.AUDIO_DETAIL_URL;


public class AudioPlayActivity extends KBaseActivity {
    private TextView mTvAudiotitleActivityAudioPlay;
    private LinearLayout mLlAudiobarActivityAudioPlay;
    private CheckBox mCbPlayorpauseActivityAudioPlay;
    private SeekBar mSbActivityAudioPlay;
    private TextView mTvTotaltimeActivityAudioPlay;
    private TextView mTvAudiocontentActivityAudioPlay;
    private ImageView mImgvActivityAudioPlay;
    private TextView mTvPlaytimeActivityAudioPlay;
    
    //    private ImageView mFengexian;
//    private TextView mTvCommentsnumComment;
//    private ListView mLvCommentsComment;0
//    private LinearLayout mLlBottombarComment;
//    private LinearLayout mLlDianzanComment;
//    private CheckBox mCbDianzanComment;
//    private TextView mTvZannumComment;
//    private LinearLayout mBtCommentComment;
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
                    int currentProgress= (Integer) msg.obj;
                    mSbActivityAudioPlay.setProgress(currentProgress);
                    mTvPlaytimeActivityAudioPlay.setText(TimeFormatUtil.formatLongToTimeStr((long) currentProgress));
                    break;
                default:
                    break;
            }
        }
    };
    private List<DiscussEntity> mTempDatas;
    private ItemDiscussLvAdapter mItemDiscussLvAdapter;
    private DigDiscuss mDigDiscuss;
    private NetForJson mNetForJson;
    private CourseAudioListEntity.AudioCourseDataEntity mCourse;
    private boolean mNetOk=false;
    private CourseAudioEntity mEntity;
    private String mCourseID;
    private String mDateNow;
    @Override
    public int setRootView() {
        return R.layout.activity_audio_play;
    }
    
    @Override
    public void initViews() {
        mDateNow=this.getIntent().getStringExtra("date");
        mTvAudiotitleActivityAudioPlay = (TextView) findViewById(R.id.tv_audiotitle_activity_audio_play);
        mLlAudiobarActivityAudioPlay = (LinearLayout) findViewById(R.id.ll_audiobar_activity_audio_play);
        mCbPlayorpauseActivityAudioPlay = (CheckBox) findViewById(R.id.cb_playorpause_activity_audio_play);
        mSbActivityAudioPlay = (SeekBar) findViewById(R.id.sb_activity_audio_play);
        mTvTotaltimeActivityAudioPlay = (TextView) findViewById(R.id.tv_totaltime_activity_audio_play);
        mTvAudiocontentActivityAudioPlay = (TextView) findViewById(R.id.tv_audiocontent_activity_audio_play);
        mImgvActivityAudioPlay = (ImageView) findViewById(R.id.imgv_activity_audio_play);
    
        mTvPlaytimeActivityAudioPlay = (TextView) findViewById(R.id.tv_playtime_activity_audio_play);
//        mFengexian = (ImageView) findViewById(R.id.fengexian);
//        mTvCommentsnumComment = (TextView) findViewById(R.id.tv_commentsnum_comment);
//        mLvCommentsComment = (ListView) findViewById(R.id.lv_comments_comment);
//        mLlBottombarComment = (LinearLayout) findViewById(R.id.ll_bottombar_comment);
//        mLlDianzanComment = (LinearLayout) findViewById(R.id.ll_dianzan_comment);
//        mCbDianzanComment = (CheckBox) findViewById(R.id.cb_dianzan_comment);
//        mTvZannumComment = (TextView) findViewById(R.id.tv_zannum_comment);
//        mBtCommentComment = (LinearLayout) findViewById(R.id.bt_comment_comment);
        setTitleCenter("养生音频");
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
  //      mTempDatas = DiscussEntity.getTempDatas();
 //       mItemDiscussLvAdapter = new ItemDiscussLvAdapter(this, mTempDatas);
 //       mLvCommentsComment.setAdapter(mItemDiscussLvAdapter);
//        mDigDiscuss=new DigDiscuss(this);
//        mDigDiscuss.setOnConfirmCallback(new DigDiscuss.OnConfirmCallback() {
//            @Override
//            public void onConfirm(String content) {
//                DiscussEntity discussEntity=new DiscussEntity();
//                discussEntity.setDetail(content);
//                discussEntity.setNickName(TempUser.getNowUser(SharePLogin.getUid()).getNickName());
//                discussEntity.setHeadUrl(TempUser.getNowUser(SharePLogin.getUid()).getHeadPicUrl());
//                discussEntity.setTime(UtilGetDiscussTime.getTimeNow());
//                mItemDiscussLvAdapter.insertToFirst(discussEntity);
//                Toast.makeText(mSelf, "评论成功", Toast.LENGTH_SHORT).show();
//            }
//        });
 //       mBtCommentComment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mDigDiscuss.show();
//            }
//        });
        mCourseID=this.getIntent().getStringExtra("courseid");
        if (mCourseID==null) {
            mCourse = (CourseAudioListEntity.AudioCourseDataEntity) this.getIntent().getSerializableExtra("course");
            mTvAudiotitleActivityAudioPlay.setText("当前正在播放："+mCourse.getCourseTitle());
        }
       
    
   
    
    }
    
    @Override
    public void initDatas() {
        EventBus.getDefault().register(this);
        setTitleRight("分享", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtil.showShare(mSelf);
            }
        });
    
        mNetForJson=new NetForJson(AUDIO_DETAIL_URL, new NetCallback<CourseAudioEntity>() {

    
            @Override
            public void onSuccess(CourseAudioEntity entity) {
                mNetOk=true;
                mEntity = entity;
                if (mEntity==null||!entity.isState()) {
                    Toast.makeText(mSelf, "抱歉，您的课程已下架！", Toast.LENGTH_SHORT).show();
                    return;
                }
                mTvAudiotitleActivityAudioPlay.setText("当前正在播放："+mEntity.getCourseTitle());
                mTvTotaltimeActivityAudioPlay.setText(TimeFormatUtil.formatLongToTimeStr((long) entity.getCourseLength()));
                mSbActivityAudioPlay.setMax(entity.getCourseLength());
                mTvAudiocontentActivityAudioPlay.setText("音频简介： "+mEntity.getCourseDetail());
                
            }
    
            @Override
            public void onError() {
                Toast.makeText(mSelf, "网络异常！", Toast.LENGTH_SHORT).show();
                mNetOk=false;
            }
    
            @Override
            public void onFinish() {
        
            }
        });
    
      
        mNetForJson.addParam("uid", SharePLogin.getUid());
        if (mCourseID==null) {
            if (mCourse==null) {
                return;
            }
            mNetForJson.addParam("courseID", mCourse.getCourseID());
        }else {
            mNetForJson.addParam("courseID", mCourseID);
        }
     
        mNetForJson.addParam("type", "audio");
        if (mDateNow==null) {
            mNetForJson.addParam("date", TimeFormatUtil.formatLongToNYR(System.currentTimeMillis()));
        }else {
            mNetForJson.addParam("date", mDateNow);
        }
        mNetForJson.excute();
        mCbPlayorpauseActivityAudioPlay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Logger.e("ischecked"+isChecked);
                if (mCbPlayorpauseActivityAudioPlay.isChecked()){
                    if (mEntity==null){
                        Toast.makeText(mSelf, "正在准备音频，请稍后重试噢！", Toast.LENGTH_SHORT).show();
                        mCbPlayorpauseActivityAudioPlay.setChecked(false);
                        return;
                    }
                }
                String courseUrl = mEntity.getCourseUrl();
                if (courseUrl==null) {
                    Toast.makeText(mSelf, "网络异常，请退出重试！", Toast.LENGTH_SHORT).show();
                    mCbPlayorpauseActivityAudioPlay.setChecked(false);
                    return;
                }
                if (firstPlay) {
                    Intent intent = new Intent(AudioPlayActivity.this, MusicService.class);
                    String replace = courseUrl.replace("http://192.168.1.25:10086", "http://106.3.41.199:8066");
//                    intent.putExtra("courseUrl",replace);
                    intent.putExtra("courseUrl",replace);
                    intent.putExtra("courseID",mEntity.getCourseID());
                    intent.putExtra("courseStudy",mEntity.getCourseStudy());
                    intent.putExtra("courseEntity",mEntity);
                    intent.putExtra("date",mDateNow);
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
                    Toast.makeText(mSelf, "安心听歌，不允许快进或者快退呦！", Toast.LENGTH_SHORT).show();
                    return;
//                    soldier.setAction(MusicSoldier.ACTION_UPDATE_PLAY_PROGRESS);
//                    soldier.setProgress(progress);
//                    EventBus.getDefault().post(soldier);
                }
            }
        
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            
            }
        
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            
            }
        });
//        mNetForJson=new NetForJson(URLConstant.AUDIO_LIST_URL,new );
        
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MusicSoldier event) {
        int action = event.getAction();
        switch (action) {
            case MusicSoldier.ACTION_UPDATE_TIMEDATA:
//                mTvTotaltimeActivityAudioPlay.setText(TimeFormatUtil.formatLongToTimeStr((long) event.getTotalTime()));
//                mSbActivityAudioPlay.setMax(event.getTotalTime());
    
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
