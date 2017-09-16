package com.medicine.ssqy.ssqy.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.medicine.ssqy.ssqy.util.UtilGetJQPic;

/**
 * Created by Administrator on 2017-09-08.
 */

public class LayoutRootCommon extends RelativeLayout {
    public LayoutRootCommon(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        this.setBackgroundResource(UtilGetJQPic.getBGRes());
    }
}
