package com.example.sj.mylibrary.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sj.mylibrary.Control.FragmentController;
import com.example.sj.mylibrary.R;

/**
 * Created by Administrator on 2016/9/1.
 */
public abstract class BaseFragment  extends Fragment{
    public String mTag;
    public static final String BUNDLE_KEY = "datas";
    public FragmentManager mFragmentManager;
    public BaseActivity mActivity;
    public BaseFragment mSelf;
    public LayoutInflater mLayoutInflater;
    public TextView mTitleLeft,mTitleCenter,mTitleRight;
    public View mRootView;
    public ViewGroup mViewGroupParent;
    public BaseFragment() {
        mTag=this.getClass().getSimpleName();
    }
    
   
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        FragmentController.remove(this.getClass());
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentController.add(this);
        init(container);
        mViewGroupParent=container;
        return mRootView;
    }
    
    public void killSelf(){
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.remove(this);
        fragmentTransaction.commit();
    }
    
    //---------------------初始化
    private void init(ViewGroup viewGroup) {
        mSelf = this;
        mActivity= (BaseActivity) this.getActivity();
        mLayoutInflater = mActivity.getLayoutInflater();
        mFragmentManager = this.getFragmentManager();
        int rootRes = setRootView();
        
        boolean isUseTitle = isUseTitle();
        if (isUseTitle) {
            mRootView = addTitle(rootRes);
            
        } else {
            mRootView=mLayoutInflater.inflate(rootRes, viewGroup, false);
        }
        initViews();
        initDatas();
    }
    
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
        LinearLayout linearLayout = new LinearLayout(mActivity);
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
        mTitleLeft.setVisibility(View.VISIBLE);
        if (mTitleLeft!=null){
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
        mTitleRight.setVisibility(View.VISIBLE);
        if (mTitleRight!=null){
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
        Intent intent = new Intent(mActivity, aClass);
        if (bundle != null) {
            intent.putExtra(BUNDLE_KEY, bundle);
        }
        
        this.startActivity(intent);
    }
    
    public void goToActivity(Class aClass) {
        goToActivity(aClass, null);
    }
    
    public Bundle getDatas() {
        return mActivity.getIntent().getBundleExtra(BUNDLE_KEY);
    }
    
    public void goToService(Class sClass, Bundle bundle) {
        Intent intent = new Intent(mActivity, sClass);
        if (bundle != null) {
            intent.putExtra(BUNDLE_KEY, bundle);
        }
        mActivity.startService(intent);
    }
    
    public void goToService(Class sClass) {
        goToService(sClass, null);
    }
    
    public void goToBroadCast(Class bClass, Bundle bundle) {
        Intent intent = new Intent(mActivity, bClass);
        if (bundle != null) {
            intent.putExtra(BUNDLE_KEY, bundle);
        }
        mActivity.sendBroadcast(intent);
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
    public View findViewByid(int id){
        
        return mRootView.findViewById(id);
    }
    
}
