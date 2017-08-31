package com.medicine.ssqy.ssqy.ui.activity;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.ui.fragment.HomeJCFragment;

public class JBGLActivity extends KBaseActivity {
    
    @Override
    public int setRootView() {
        return R.layout.activity_jbgl;
    }
    
    @Override
    public void initViews() {
        setTitleCenter("我的疾病管理");
        addFrag(R.id.activity_jbgl,new HomeJCFragment());
    }
    
    @Override
    public void initDatas() {
        
    }
}
