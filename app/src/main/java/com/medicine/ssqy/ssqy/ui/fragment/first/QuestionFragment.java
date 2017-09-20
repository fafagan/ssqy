package com.medicine.ssqy.ssqy.ui.fragment.first;


import android.support.v4.app.Fragment;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseFragment;
import com.medicine.ssqy.ssqy.common.URLConstant;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.js.QuestionInterface;
import com.orhanobut.logger.Logger;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends KBaseFragment {
    
    private RelativeLayout mLayoutWelcomeQuestion;
    private Button mBtBeginQuestion;
    private RelativeLayout mLayoutQuestionWv;
//    private ProgressBar mProgressBar;
    private WebView mWvAbout;
    private WebSettings mSettings;
    @Override
    public boolean isUseTitle() {
        return false;
    }
    
    @Override
    public int setRootView() {
        return R.layout.fragment_question;
    }
    @Override
    public void initViews() {
        mLayoutWelcomeQuestion = (RelativeLayout) findViewById(R.id.layout_welcome_question);
        mBtBeginQuestion = (Button) findViewById(R.id.bt_begin_question);
        mLayoutQuestionWv = (RelativeLayout) findViewById(R.id.layout_question_wv);
//        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mWvAbout = (WebView) findViewById(R.id.wv_about);
        mBtBeginQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLayoutWelcomeQuestion.setVisibility(View.GONE);
                mLayoutQuestionWv.setVisibility(View.VISIBLE);
            }
        });
    }
    
    @Override
    public void initDatas() {
        mSettings = mWvAbout.getSettings();
        Logger.e("QuestionFragment--->"+SharePLogin.getUid());
        mWvAbout.loadUrl(URLConstant.QUESTION_URL+ SharePLogin.getUid());
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
//                mProgressBar.setProgress(newProgress);
            }
        
        });
        
        mSettings.setJavaScriptEnabled(true);
        mWvAbout.addJavascriptInterface(new QuestionInterface(mActivity),"goHome");
        mSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }
}
