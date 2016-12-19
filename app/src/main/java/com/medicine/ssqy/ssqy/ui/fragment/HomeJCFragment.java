package com.medicine.ssqy.ssqy.ui.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseFragment;
import com.medicine.ssqy.ssqy.ui.dialog.DigTZJC;
import com.medicine.ssqy.ssqy.ui.dialog.DigXTJC;
import com.medicine.ssqy.ssqy.ui.dialog.DigXYJC;
import com.medicine.ssqy.ssqy.ui.dialog.DigYYJL;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeJCFragment extends KBaseFragment {
    private LinearLayout mItemXtjcJcFrag;
    private LinearLayout mItemXyjcJcFrag;
    private LinearLayout mItemTzjcJcFrag;
    private LinearLayout mItemYyjlJcFrag;
    private LinearLayout mItemSsjlJcFrag;
    private LinearLayout mItemYdjlJcFrag;
    private LinearLayout mItemXtbgJcFrag;
    private LinearLayout mItemXybgJcFrag;
    private LinearLayout mItemTzbgJcFrag;
    private LinearLayout mItemYybgJcFrag;
    private LinearLayout mItemSsbgJcFrag;
    private LinearLayout mItemYdbgJcFrag;

    
    
    @Override
    public boolean isUseTitle() {
        return false;
    }
    @Override
    public int setRootView() {
        return R.layout.fragment_home_jc;
    }
    
    @Override
    public void initViews() {
    
        mItemXtjcJcFrag = (LinearLayout) findViewById(R.id.item_xtjc_jc_frag);
        mItemXyjcJcFrag = (LinearLayout) findViewById(R.id.item_xyjc_jc_frag);
        mItemTzjcJcFrag = (LinearLayout) findViewById(R.id.item_tzjc_jc_frag);
        mItemYyjlJcFrag = (LinearLayout) findViewById(R.id.item_yyjl_jc_frag);
        mItemSsjlJcFrag = (LinearLayout) findViewById(R.id.item_ssjl_jc_frag);
        mItemYdjlJcFrag = (LinearLayout) findViewById(R.id.item_ydjl_jc_frag);
        mItemXtbgJcFrag = (LinearLayout) findViewById(R.id.item_xtbg_jc_frag);
        mItemXybgJcFrag = (LinearLayout) findViewById(R.id.item_xybg_jc_frag);
        mItemTzbgJcFrag = (LinearLayout) findViewById(R.id.item_tzbg_jc_frag);
        mItemYybgJcFrag = (LinearLayout) findViewById(R.id.item_yybg_jc_frag);
        mItemSsbgJcFrag = (LinearLayout) findViewById(R.id.item_ssbg_jc_frag);
        mItemYdbgJcFrag = (LinearLayout) findViewById(R.id.item_ydbg_jc_frag);
        
        mItemXtjcJcFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DigXTJC(mActivity).show();
            }
        });
        
        mItemXyjcJcFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DigXYJC(mActivity).show();
            }
        });
        mItemTzjcJcFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DigTZJC(mActivity).show();
            }
        });
        
        mItemYyjlJcFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DigYYJL(mActivity).show();
            }
        });
        mItemSsjlJcFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DigYYJL(mActivity).show();
            }
        });
        mItemYdjlJcFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DigYYJL(mActivity).show();
            }
        });
        
    }
    
    @Override
    public void initDatas() {
        
    }

    
}
