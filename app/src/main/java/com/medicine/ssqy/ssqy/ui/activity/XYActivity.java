package com.medicine.ssqy.ssqy.ui.activity;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;

public class XYActivity extends KBaseActivity {
    private WebView mWvAbout;
    private WebSettings mSettings;
    
    private String mUrl="file:///android_asset/xy.htm";
    
    
    @Override
    public int setRootView() {
        return R.layout.activity_xy;
    }
    
    @Override
    public void initViews() {
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
    
        mSettings.setJavaScriptEnabled(true);
        mSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        setTitleCenter("四时七养--用户协议");
    }
    
    @Override
    public void initDatas() {
        
    }
}
