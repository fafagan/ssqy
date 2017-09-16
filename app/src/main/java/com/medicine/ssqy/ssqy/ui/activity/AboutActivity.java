package com.medicine.ssqy.ssqy.ui.activity;

import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.common.URLConstant;

public class AboutActivity extends KBaseActivity {
    private WebView mWvAbout;
    private WebSettings mSettings;
    private ProgressBar mProgressBar;
    
    private String mUrl= URLConstant.BASE_URL+"aboutwe";
    
    
    @Override
    public int setRootView() {
        return R.layout.activity_about;
    }
    
    @Override
    public void initViews() {
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mWvAbout = (WebView) findViewById(R.id.wv_about);
        mSettings = mWvAbout.getSettings();
        mWvAbout.loadUrl(mUrl);
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
            }
        
        });
    
        mSettings.setJavaScriptEnabled(true);
    
    
    
        mSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        
        setTitleCenter("四时七养--中医养生");
    }
    
    @Override
    public void initDatas() {
        
    }
}
