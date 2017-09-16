package com.medicine.ssqy.ssqy.ui.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseFragment;
import com.medicine.ssqy.ssqy.ui.activity.HomeUtilActivity;
import com.medicine.ssqy.ssqy.ui.activity.TempActivity;
import com.medicine.ssqy.ssqy.ui.adapter.ItemLvFragUtilAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeUtilFragment extends KBaseFragment {
    private ListView mLvFragUtil;

    
    @Override
    public boolean isUseTitle() {
        return false;
    }
    @Override
    public int setRootView() {
        return R.layout.fragment_home_util;
     
    }
    
    @Override
    public void initViews() {
        setTitleCenter("健康查询");
        mLvFragUtil = (ListView)mRootView. findViewById(R.id.lv_frag_util);
        mLvFragUtil.setAdapter(new ItemLvFragUtilAdapter(mActivity));
        mLvFragUtil.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getContext(), HomeUtilActivity.class);
                intent.putExtra("item",position);
                goToActivity(TempActivity.class);
        
            }
        });
    }
    
    @Override
    public void initDatas() {
        
    }
    
}
