package com.medicine.ssqy.ssqy.ui.fragment.coursehome;

import com.medicine.ssqy.ssqy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/11.
 */
public class FragUtilItem {
    
    public static final List<FragUtilItem> mFragUtilItems=new ArrayList<>();
    
    static {
        mFragUtilItems.add(new FragUtilItem("食物热量速查", R.drawable.icon_swrl));
        mFragUtilItems.add(new FragUtilItem("食物嘌呤速查", R.drawable.icon_swpl));
        mFragUtilItems.add(new FragUtilItem("食物含钙量速查", R.drawable.icon_swhgl));
        mFragUtilItems.add(new FragUtilItem("运动消耗速查", R.drawable.icon_ydxh));
//        mFragUtilItems.add(new FragUtilItem("常用药物速查", R.drawable.icon_cyyw));
        
    }
    
    private String title;
    private int icon;
    
    public FragUtilItem() {
    }
    
    public FragUtilItem(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getIcon() {
        return icon;
    }
    
    public void setIcon(int icon) {
        this.icon = icon;
    }
}
