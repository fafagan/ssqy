package com.medicine.ssqy.ssqy.ui.activity;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.base.KBaseFragment;
import com.medicine.ssqy.ssqy.db.TempUser;
import com.medicine.ssqy.ssqy.eventBus.FirstLoginMsg;
import com.medicine.ssqy.ssqy.ui.fragment.FirstLoginBirthFragment;
import com.medicine.ssqy.ssqy.ui.fragment.FirstLoginJobFragment;
import com.medicine.ssqy.ssqy.ui.fragment.FirstLoginSexFragment;
import com.medicine.ssqy.ssqy.ui.fragment.FirstLoginStudyLevelFragment;
import com.medicine.ssqy.ssqy.ui.pop.LoadingPopWindow;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class FirstLoginActivity extends KBaseActivity {
    private ViewPager mVpFirstLogin;
    private FirstLoginSexFragment mFirstLoginSexFragment=new FirstLoginSexFragment();
    private FirstLoginBirthFragment mFirstLoginBirthFragment=new FirstLoginBirthFragment();
    private FirstLoginJobFragment mFirstLoginJobFragment=new FirstLoginJobFragment();
    private FirstLoginStudyLevelFragment mFirstLoginStudyLevelFragment=new FirstLoginStudyLevelFragment();
    private KBaseFragment[] mKBaseFragments={mFirstLoginSexFragment,mFirstLoginBirthFragment,mFirstLoginJobFragment,mFirstLoginStudyLevelFragment};
    private LoadingPopWindow loadingPopWindow;
    public String sex;
    public boolean isMarried;
    public String birthDay;
    public String job;
    public String studyLevel;
    @Override
    public int setRootView() {
        return R.layout.activity_first_login;
    }
    
    @Override
    public void initViews() {
        loadingPopWindow=new LoadingPopWindow(this);
        mVpFirstLogin = (ViewPager) findViewById(R.id.vp_first_login);
        mVpFirstLogin.setAdapter(new AdapterVPFirstLogin(mFragmentManager));
        mVpFirstLogin.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        
            }
    
            @Override
            public void onPageSelected(int position) {
                setTitleCenter("完成资料"+(position+1)+"/"+mKBaseFragments.length);
            }
    
            @Override
            public void onPageScrollStateChanged(int state) {
              
            }
        });
        setTitleCenter("完成资料1/"+mKBaseFragments.length);
    }
    
    @Override
    public void initDatas() {
        EventBus.getDefault().register(this);
        
    }
    @Subscribe
    public void onReceive(FirstLoginMsg firstLoginMsg){
        switch (firstLoginMsg.action) {
            case FirstLoginMsg.ACTION_DONE_1:
                sex=firstLoginMsg.sex;
                isMarried=firstLoginMsg.isMarried;
                mVpFirstLogin.setCurrentItem(1);
                break;
            case FirstLoginMsg.ACTION_DONE_2:
                birthDay=firstLoginMsg.birthDay;
                mVpFirstLogin.setCurrentItem(2);
                break;
            case FirstLoginMsg.ACTION_DONE_3:
                job=firstLoginMsg.job;
                mVpFirstLogin.setCurrentItem(3);
                break;
            case FirstLoginMsg.ACTION_DONE_ALL:
                studyLevel=firstLoginMsg.studyLevel;
                doSave();
                break;
        }
            
        
    }
    
    private void doSave() {
        Toast.makeText(mSelf, "正在保存您的信息，请稍后", Toast.LENGTH_SHORT).show();
        loadingPopWindow.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                synchServer();
            }
        },700);
      
    }
    
    private void synchServer() {
        onSaveOk();
    }
    
    private void onSaveOk(){
        TempUser.saveOrUpdateUserFirst(sex,isMarried,birthDay,job,studyLevel);
        goToActivity(HomeActivity.class);
        finish();
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    
    @Override
    public void onBackPressed() {
        if (loadingPopWindow.isShowing()){
            loadingPopWindow.dismiss();
            cancelNet();
            return;
        }
        super.onBackPressed();
    }
    
    private void cancelNet() {
        
    }
    
    class AdapterVPFirstLogin extends FragmentPagerAdapter{
    
        public AdapterVPFirstLogin(FragmentManager fm) {
            super(fm);
        }
    
        @Override
        public Fragment getItem(int position) {
            return mKBaseFragments[position];
        }
    
        @Override
        public int getCount() {
            return mKBaseFragments.length;
        }
    }
}
