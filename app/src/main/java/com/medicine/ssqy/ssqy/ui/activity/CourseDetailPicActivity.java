package com.medicine.ssqy.ssqy.ui.activity;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sj.mylibrary.net.NetCallback;
import com.example.sj.mylibrary.net.NetForJson;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.common.URLConstant;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.entity.course.CoursePicDetailEntity;
import com.medicine.ssqy.ssqy.entity.course.CoursePicListEntity;

//http://3g.163.com/touch/article.html?channel=jiankang&offset=20&docid=C85SNS4P0038804V
public class CourseDetailPicActivity extends KBaseActivity {
    private String url;
    private ProgressBar mProgressBar;
    private WebView mWv;
    private WebSettings mSettings;
    private NetForJson mNetForJson;
    private   CoursePicListEntity.PicCourseDataEntity pc;
    
    public static final int TYPE_XJ=110;
    private int type=-1;
//    private NetForJson mNetForJsonSyncLearnProgress=new NetForJson(URLConstant.VIDEO_PROGRESS_URL, new NetCallback<CourseVideoProgressEntity>() {
//        @Override
//        public void onSuccess(CourseVideoProgressEntity entity) {
//            
//        }
//        
//        @Override
//        public void onError() {
//            
//        }
//        
//        @Override
//        public void onFinish() {
//            
//        }
//    },true);
    @Override
    public int setRootView() {
        return R.layout.activity_course_detail_pic;
    }
    
    @Override
    public void initViews() {
        
        type=this.getIntent().getIntExtra("type",-1);

        setTitleRight("分享", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mSelf, "分享该课程", Toast.LENGTH_SHORT).show();
            }
        });
        Toast.makeText(mSelf, "正在为您加载相关图文，请稍后", Toast.LENGTH_SHORT).show();
        pc= (CoursePicListEntity.PicCourseDataEntity) this.getIntent().getSerializableExtra("pc");
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
    
        mWv = (WebView) findViewById(R.id.wv);
    
        mSettings = mWv.getSettings();
    
      
        //设置在当前APP打卡url指向的网页
        mWv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                super.shouldOverrideUrlLoading(view, url);
                mWv.loadUrl(url);
                return true;
            }
        });
        mWv.setWebChromeClient(new WebChromeClient() {
        
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            
                mProgressBar.setProgress(newProgress);
            }
        
        });
    
        mSettings.setJavaScriptEnabled(true);

       mSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    
        //当有表单需要处理时记得写上这2句
//        mWv.requestFocus();
//        mWv.requestFocusFromTouch();
    }
    
    @Override
    public void initDatas() {
        if (type==-1) {
            setTitleCenter("养生图文");
            mNetForJson=new NetForJson(URLConstant.PIC_DETAIL_URL, new NetCallback<CoursePicDetailEntity>() {
                @Override
                public void onSuccess(CoursePicDetailEntity entity) {
                    if (entity.isState()){
                        url=entity.getCoursehtmlUrl();
                        loadWV();
                    }else {
                        Toast.makeText(mSelf, "加载异常，请退出重试", Toast.LENGTH_SHORT).show();
                    }
            
                }
        
                @Override
                public void onError() {
                    Toast.makeText(mSelf, "网络异常，请检查网络状态！", Toast.LENGTH_SHORT).show();
                }
        
                @Override
                public void onFinish() {
            
                }
            });
    
            mNetForJson.addParam("uid", SharePLogin.getUid());
            mNetForJson.addParam("courseID", pc.getCourseID());
            mNetForJson.addParam("type", "pic");
            mNetForJson.excute();
    
//    
//            mNetForJsonSyncLearnProgress.addParam("uid",SharePLogin.getUid());
//            mNetForJsonSyncLearnProgress.addParam("type","video");
//            mNetForJsonSyncLearnProgress.addParam("courseID",pc.getCourseID());
//            mNetForJsonSyncLearnProgress.addParam("learnProgress",10000);
//            mNetForJsonSyncLearnProgress.addParam("courseLearned","true");
        }else {
            setTitleCenter("养生宣教");
            url=getIntent().getStringExtra("newsUrl");
            loadWV();
        }
      
    }
    
    private void loadWV() {
        mWv.loadUrl(url);
    }
    
    @Override
    public void onBackPressed() {
        if (mWv.canGoBack()){
            mWv.goBack();
            //后退，前进后标题栏更新
            return;
        }

//        mWv.canGoForward()
//        mWv.goForward();
        
        super.onBackPressed();
    }
}
