package com.medicine.ssqy.ssqy.ui.activity;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;

//http://3g.163.com/touch/article.html?channel=jiankang&offset=20&docid=C85SNS4P0038804V
public class CourseDetailPicActivity extends KBaseActivity {
    private String url;
    private ProgressBar mProgressBar;
    private WebView mWv;
    private WebSettings mSettings;
    @Override
    public int setRootView() {
        return R.layout.activity_course_detail_pic;
    }
    
    @Override
    public void initViews() {
        setTitleCenter("图文课程");
        setTitleRight("分享", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mSelf, "分享该课程", Toast.LENGTH_SHORT).show();
            }
        });
        url=this.getIntent().getStringExtra("newsUrl");
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
    
        mWv = (WebView) findViewById(R.id.wv);
    
        mSettings = mWv.getSettings();
    
        mWv.loadUrl(url);
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
