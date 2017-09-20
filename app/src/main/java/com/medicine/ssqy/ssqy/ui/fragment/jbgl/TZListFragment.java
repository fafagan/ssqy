package com.medicine.ssqy.ssqy.ui.fragment.jbgl;


import android.support.v4.app.Fragment;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.ui.adapter.jbgl.ItemLVTZAdapter;
import com.medicine.ssqy.ssqy.ui.adapter.jbgl.JBGLBaseAdapter;
import com.medicine.ssqy.ssqy.ui.dialog.DigJBGLBase;

/**
 * A simple {@link Fragment} subclass.
 */
public class TZListFragment extends JBGLBaseFragment {
    
    
    @Override
    public JBGLBaseAdapter setJBGLBaseAdapter() {
        return new ItemLVTZAdapter(mActivity,R.layout.item_lv_jbgl_tvgl);
    }
    
    @Override
    public int setRequestType() {
        return TYPE_TZ;
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
