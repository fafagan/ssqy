package com.example.sj.mylibrary.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sj.mylibrary.Control.ActivityController;
import com.example.sj.mylibrary.R;

/**
 * Created by Administrator on 2016/9/1.
 */
public abstract class BaseActivity extends FragmentActivity {
    public static final String BUNDLE_KEY = "datas";
    public FragmentManager mFragmentManager;
    public BaseActivity mSelf;
    public LayoutInflater mLayoutInflater;
    public TextView mTitleLeft,mTitleCenter,mTitleRight;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityController.add(this);
        init();
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.remove(this.getClass());
    }
    
    public void killSelf(){
        this.finish();
    }
    
    //---------------------初始化
    private void init() {
        mSelf = this;
        mLayoutInflater = this.getLayoutInflater();
        mFragmentManager = this.getSupportFragmentManager();
        int rootRes = setRootView();
        
        boolean isUseTitle = isUseTitle();
        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (isUseTitle) {
          
            View rootView = addTitle(rootRes);
            setContentView(rootView);
        } else {
            setContentView(rootRes);
        }
        initViews();
        initDatas();
        initOthers();
    }
    
    public abstract void initOthers();
    
    public abstract int setRootView();
    
    public abstract void initViews();
    
    public abstract void initDatas();
    //---------------------初始化
    
    
    //-------------处理标题栏
    public boolean isUseTitle() {
        return true;
    }
    
    private View addTitle(int rootRes) {
        //新整体=  老的根布局  头顶 标题栏
        LinearLayout linearLayout = new LinearLayout(mSelf);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        //老的根布局 
        View oldRootView = mLayoutInflater.inflate(rootRes, linearLayout, false);
        //标题栏
        View titleView = mLayoutInflater.inflate(TitleBarConfig.title_resID, linearLayout, false);
    
    
        mTitleLeft= (TextView) titleView.findViewById(R.id.title_left);
        mTitleCenter= (TextView) titleView.findViewById(R.id.title_center);
        mTitleRight= (TextView) titleView.findViewById(R.id.title_right);
        
        if(mTitleLeft!=null){
            mTitleLeft.setVisibility(View.INVISIBLE);
        }
        if(mTitleRight!=null){
            mTitleRight.setVisibility(View.INVISIBLE);
        }
            
        linearLayout.addView(titleView);
        linearLayout.addView(oldRootView);
        
        return linearLayout;
        
    }
    
    public void setTitleLeft(String text, View.OnClickListener onClickListener){
    
        if (mTitleLeft!=null){
            mTitleLeft.setVisibility(View.VISIBLE);
            mTitleLeft.setClickable(true);
            if (onClickListener!=null){
                mTitleLeft.setOnClickListener(onClickListener);
            }
   
            mTitleLeft.setText(text);
        }
    }
    public void setTitleCenter(String text){
        if (mTitleCenter!=null){
            mTitleCenter.setText(text);
        }
    }
    
    public void setTitleRight(String text, View.OnClickListener onClickListener){
    
        if (mTitleRight!=null){
            mTitleRight.setVisibility(View.VISIBLE);
            mTitleRight.setClickable(true);
            if (onClickListener!=null){
                mTitleRight.setOnClickListener(onClickListener);
            }
            mTitleRight.setText(text);
        }
    }
    //-------------处理标题栏
    
    
   
    
    //---------------------组件通信
    public void goToActivity(Class aClass, Bundle bundle) {
        Intent intent = new Intent(this, aClass);
        if (bundle != null) {
            intent.putExtra(BUNDLE_KEY, bundle);
        }
        
        this.startActivity(intent);
    }
    
    public void goToActivity(Class aClass) {
        goToActivity(aClass, null);
    }
    
    public Bundle getDatas() {
        return this.getIntent().getBundleExtra(BUNDLE_KEY);
    }
    
    public void goToService(Class sClass, Bundle bundle) {
        Intent intent = new Intent(this, sClass);
        if (bundle != null) {
            intent.putExtra(BUNDLE_KEY, bundle);
        }
        this.startService(intent);
    }
    
    public void goToService(Class sClass) {
        goToService(sClass, null);
    }
    
    public void goToBroadCast(Class bClass, Bundle bundle) {
        Intent intent = new Intent(this, bClass);
        if (bundle != null) {
            intent.putExtra(BUNDLE_KEY, bundle);
        }
        this.sendBroadcast(intent);
    }
    
    public void goToBroadCast(Class bClass) {
        goToBroadCast(bClass, null);
    }
    //---------------------组件通信
    
    
    //--------------------快捷操作Fragment
    public void addFrag(int container, BaseFragment baseFragment) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(container, baseFragment, baseFragment.mTag);
        transaction.commit();
    }
    
    public void removeFrag(BaseFragment baseFragment) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.remove(baseFragment);
        transaction.commit();
    }
    
    public void replaceFrag(int container, BaseFragment newFragment) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(container, newFragment, newFragment.mTag);
        transaction.commit();
    }
    
    public void hideFrag(BaseFragment baseFragment) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.hide(baseFragment);
        transaction.commit();
    }
    
    public void showFrag(BaseFragment baseFragment) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.show(baseFragment);
        transaction.commit();
    }
    
    
    //--------------------快捷操作Fragment
    
    
}
