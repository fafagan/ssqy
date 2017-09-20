package com.medicine.ssqy.ssqy.ui.fragment.jbgl;


import android.support.v4.app.Fragment;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.ui.adapter.jbgl.ItemLVXYAdapter;
import com.medicine.ssqy.ssqy.ui.adapter.jbgl.JBGLBaseAdapter;
import com.medicine.ssqy.ssqy.ui.dialog.DigJBGLBase;

/**
 * A simple {@link Fragment} subclass.
 */
public class XYListFragment extends JBGLBaseFragment {
    
    @Override
    public JBGLBaseAdapter setJBGLBaseAdapter() {
        return new ItemLVXYAdapter(mActivity,R.layout.item_lv_jbgl_xygl);
    }
    
    @Override
    public int setRequestType() {
        return TYPE_XY;
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
