package com.medicine.ssqy.ssqy.ui.activity;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;

public class ChatActivity extends KBaseActivity {
    
    @Override
    public int setRootView() {
        return R.layout.activity_chat;
    }
    
    @Override
    public void initViews() {
        setTitleCenter("我的医生");
    }
    
    @Override
    public void initDatas() {
        
//        FragmentManager fragmentManager = this.getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        
//        transaction.add(R.id.layout_chat,new EaseChatFragment(),"Xx");
//        transaction.commit();
        
    }
}
