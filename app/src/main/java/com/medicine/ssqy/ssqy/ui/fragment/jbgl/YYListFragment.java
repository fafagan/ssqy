package com.medicine.ssqy.ssqy.ui.fragment.jbgl;


import android.support.v4.app.Fragment;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.ui.adapter.jbgl.ItemLVSSAdapter;
import com.medicine.ssqy.ssqy.ui.adapter.jbgl.JBGLBaseAdapter;
import com.medicine.ssqy.ssqy.ui.dialog.DigJBGLBase;

/**
 * A simple {@link Fragment} subclass.
 */
public class YYListFragment extends JBGLBaseFragment {
    
    
    @Override
    public JBGLBaseAdapter setJBGLBaseAdapter() {
        return new ItemLVSSAdapter(mActivity,R.layout.item_lv_jbgl_yygl);
    }
    
    @Override
    public int setRequestType() {
        return TYPE_YY;
    }
    
    @Override
    public DigJBGLBase setDialogModify() {
        return null;
    }
    
    @Override
    public int setRootView() {
        return R.layout.fragment_xtlist;
    }
    
}
