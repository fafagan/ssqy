package com.medicine.ssqy.ssqy.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.medicine.ssqy.ssqy.R;

import org.xutils.common.util.DensityUtil;

/**
 * Created by Amy on 2016/12/16.
 */
public class DigNotifyQuestion extends Dialog{
    private Button mBtConfirm;
    
    private View.OnClickListener mOnClickListener;
    
    public void setOnClickListener(View.OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
        mBtConfirm.setOnClickListener(mOnClickListener);
    }
    
    public DigNotifyQuestion(Context context) {
        super(context);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setGravity(Gravity.CENTER);
        this.setContentView(R.layout.dig_notify_ques);
        initViews();
        initDatas();
        
    }
    
    private void initViews() {
        mBtConfirm = (Button) findViewById(R.id.bt_confirm);
    }
    
    private void initDatas() {
    }
    
    public void showSelf() {
        this.getWindow().setWindowAnimations(R.style.diganim);
        this.show();
        this.getWindow().setLayout(DensityUtil.dip2px(330) , DensityUtil.dip2px(180));
    }
    
}
