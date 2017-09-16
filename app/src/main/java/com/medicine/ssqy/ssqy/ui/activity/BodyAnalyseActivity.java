package com.medicine.ssqy.ssqy.ui.activity;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.common.URLConstant;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.ui.views.ProgressWheel;

public class BodyAnalyseActivity extends KBaseActivity {
    private ProgressWheel mPbLoadingBody;
    private RelativeLayout mLayoutReport;
    private ProgressBar mProgressBar;
    private WebView mWvAbout;
    
    private WebSettings mSettings;
    
    @Override
    public int setRootView() {
        return R.layout.activity_body_analyse;
    }
    
    @Override
    public void initViews() {
        setTitleCenter("中医体质辨识与分析");
        mPbLoadingBody = (ProgressWheel) findViewById(R.id.pb_loading_body);
        mLayoutReport = (RelativeLayout) findViewById(R.id.layout_report);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mWvAbout = (WebView) findViewById(R.id.wv_about);
        mSettings = mWvAbout.getSettings();
        mWvAbout.loadUrl(URLConstant.SURVEY_RESULT_URL+ SharePLogin.getUid());
        //设置在当前APP打卡mUrl指向的网页
        mWvAbout.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String mUrl) {
                super.shouldOverrideUrlLoading(view, mUrl);
                mWvAbout.loadUrl(mUrl);
                return true;
            }
        });
        mWvAbout.setWebChromeClient(new WebChromeClient() {
        
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            
                mProgressBar.setProgress(newProgress);
                if (newProgress>90) {
                    mPbLoadingBody.stopSpinning();
                    mPbLoadingBody.setVisibility(View.GONE);
                }
            }
        });
        mSettings.setJavaScriptEnabled(true);
        mSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
    }
    
    @Override
    public void initDatas() {
    }
}
