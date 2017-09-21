package com.medicine.ssqy.ssqy.ui.activity;

import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.common.URLConstant;
import com.medicine.ssqy.ssqy.util.JQ;
import com.medicine.ssqy.ssqy.util.Yang;

public class SSQYResultActivity extends KBaseActivity {
    private WebView mWebViewSsqyResult;
    private ProgressBar mProgressBar;
    

    
//    String mUrl="http://mp.weixin.qq.com/s?__biz=MzA5MzAwMDMxOQ==&mid=2651623704&idx=1&sn=da998ad730c9c47c8414b81aaa894ca8&chksm=8b9cb608bceb3f1e373fed437c2e4702331619cd92b2e11ec93d5b6bcae63e42dbce332fec11&mpshare=1&scene=23&srcid=0312RHyiEMkWwdNvTPxYSE3H#rd";
    String mUrl= null;
    private WebSettings mSettings;
    private JQ mNowJQ;
    private Yang mNowYang;
    @Override
    public int setRootView() {
        return R.layout.activity_ssqyresult;
    }
    
    @Override
    public void initViews() {
        Toast.makeText(mSelf, "正在加载，请稍后", Toast.LENGTH_SHORT).show();
        boolean isCxqdMode =  getIntent().getBooleanExtra("cxqdMode",false);
    
        if (isCxqdMode) {
          String  cxqdType=getIntent().getStringExtra("cxqdType");
    
            switch (cxqdType) {
                case "chun":
                    mUrl=URLConstant.SEASON_URL+"?season=1";
                    setTitleCenter("春季养生");
                    break;
                case "xia":
                    mUrl=URLConstant.SEASON_URL+"?season=2";
                    setTitleCenter("夏季养生");
                    break;
                case "qiu":
                    mUrl=URLConstant.SEASON_URL+"?season=3";
                    setTitleCenter("秋季养生");
                    break;
                case "dong":
                    mUrl=URLConstant.SEASON_URL+"?season=4";
                    setTitleCenter("冬季养生");
                    break;
            }
        }else {
            mNowJQ= (JQ) getIntent().getSerializableExtra("sl");
            mNowYang= (Yang) getIntent().getSerializableExtra("yang");
            mUrl= URLConstant.SSQY_URL+"?season24="+mNowJQ.index+"&feed7="+mNowYang.index;
//            setTitleCenter(mNowJQ.title+"  "+mNowYang.title);
            setTitleCenter("我的节气养生");
        }
     
      
      
        
        mWebViewSsqyResult = (WebView) findViewById(R.id.webView_ssqy_result);
        mSettings = mWebViewSsqyResult.getSettings();
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mWebViewSsqyResult.loadUrl(mUrl);
        //设置在当前APP打卡mUrl指向的网页
        mWebViewSsqyResult.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String mUrl) {
                super.shouldOverrideUrlLoading(view, mUrl);
                mWebViewSsqyResult.loadUrl(mUrl);
                return true;
            }
        });
        mWebViewSsqyResult.setWebChromeClient(new WebChromeClient() {
        
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            
                mProgressBar.setProgress(newProgress);
            }
        
        });
    
        mSettings.setJavaScriptEnabled(true);
    
    
    
        mSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    
        //当有表单需要处理时记得写上这2句
//        mWebViewSsqyResult.requestFocus();
//        mWebViewSsqyResult.requestFocusFromTouch();
    }
    
    @Override
    public void initDatas() {
      
    }
    
    @Override
    public void onBackPressed() {
        if (mWebViewSsqyResult.canGoBack()){
            mWebViewSsqyResult.goBack();
            //后退，前进后标题栏更新
            return;
        }

//        mWebViewSsqyResult.canGoForward()
//        mWebViewSsqyResult.goForward();
        
        super.onBackPressed();
    }
}
