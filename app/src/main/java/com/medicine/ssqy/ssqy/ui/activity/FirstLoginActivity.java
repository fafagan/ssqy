package com.medicine.ssqy.ssqy.ui.activity;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.sj.mylibrary.net.NetCallback;
import com.example.sj.mylibrary.net.NetForJson;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.base.KBaseFragment;
import com.medicine.ssqy.ssqy.common.URLConstant;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.db.TempUser;
import com.medicine.ssqy.ssqy.entity.UserEntity;
import com.medicine.ssqy.ssqy.entity.UsersetEntity;
import com.medicine.ssqy.ssqy.eventBus.FirstLoginMsg;
import com.medicine.ssqy.ssqy.ui.fragment.FirstLoginBirthFragment;
import com.medicine.ssqy.ssqy.ui.fragment.FirstLoginJobFragment;
import com.medicine.ssqy.ssqy.ui.fragment.FirstLoginSexFragment;
import com.medicine.ssqy.ssqy.ui.fragment.FirstLoginStudyLevelFragment;
import com.medicine.ssqy.ssqy.ui.fragment.QuestionFragment;
import com.medicine.ssqy.ssqy.ui.pop.LoadingPopWindow;
import com.orhanobut.logger.Logger;

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
    public String isMarried;
    public String birthDay;
    public String job;
    public String studyLevel;
    NetForJson mNetForJson;
    private RelativeLayout mContainerQuestionFrag;
    

    
    @Override
    public int setRootView() {
        return R.layout.activity_first_login;
    }
    
    @Override
    public void initViews() {
        mContainerQuestionFrag = (RelativeLayout) findViewById(R.id.container_question_frag);
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
        mNetForJson=new NetForJson(URLConstant.USERSET_URL, new NetCallback<UsersetEntity>() {
            @Override
            public void onSuccess(UsersetEntity entity) {
                onSaveOk();
            }
    
            @Override
            public void onError() {
                Toast.makeText(mSelf, "保存失败，请重试！", Toast.LENGTH_SHORT).show();
            }
    
            @Override
            public void onFinish() {
                loadingPopWindow.dismiss();
            }
        },true);
    }
    @Subscribe
    public void onReceive(FirstLoginMsg firstLoginMsg){
        switch (firstLoginMsg.action) {
            case FirstLoginMsg.ACTION_DONE_1:
                sex=firstLoginMsg.sex;
                isMarried=firstLoginMsg.
                        isMarried;
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
        },300);
      
    }
    
    private void synchServer() {
        UserEntity nowUser = TempUser.getNowUser(SharePLogin.getUid());
        Logger.e(nowUser.toString());
        mNetForJson.addParam("nickName",nowUser.getNickName());
        mNetForJson.addParam("sex","man".equals(sex)?1:2);
        mNetForJson.addParam("marry",isMarried);
        mNetForJson.addParam("birth",birthDay);
        mNetForJson.addParam("phone",nowUser.getUseraccount());
        mNetForJson.addParam("job",job);
        mNetForJson.addParam("studylevel",studyLevel);
        mNetForJson.addParam("isFirstLogin",false);
        mNetForJson.addParam("level",1);
        mNetForJson.excute();
        
      
    }
    
    private void onSaveOk(){
//        TempUser.saveOrUpdateUserFirst(sex,isMarried,birthDay,job,studyLevel);
        UserEntity nowUser = TempUser.getNowUser(SharePLogin.getUid());
        nowUser.setIsMarried(isMarried);
        nowUser.setBirthDay(birthDay);
        nowUser.setJob(job);
        nowUser.setStudyLevel(studyLevel);
        nowUser.setIsFisrtLogin("true");
        nowUser.setSex(sex);
        TempUser.saveOrUpdateUser(nowUser);
//        goToActivity(IndexActivity.class);
        showQuestionFrag();
    }
    
    private void showQuestionFrag() {
        setTitleCenter("填写调查问卷");
        mContainerQuestionFrag.setVisibility(View.VISIBLE);
        mVpFirstLogin.setVisibility(View.GONE);
        addFrag(R.id.container_question_frag,new QuestionFragment());
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
