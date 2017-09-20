package com.medicine.ssqy.ssqy.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePNotify;

import org.xutils.common.util.DensityUtil;

/**
 * Created by Amy on 2016/12/16.
 */
public class DigNotify extends Dialog{
    private Button mBtConfirm;
    private CheckBox mCbCancel;
    

    
    
    
    public DigNotify(Context context) {
        super(context);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setGravity(Gravity.CENTER);
        this.setContentView(R.layout.dig_notify);
        initViews();
        initDatas();
        
    }
    
    private void initViews() {
        mBtConfirm = (Button) findViewById(R.id.bt_confirm);
        mBtConfirm .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCbCancel.isChecked()) {
                    SharePNotify.saveCancel(true);
                }
                cancel();
            }
        });
        mCbCancel = (CheckBox) findViewById(R.id.cb_cancel);
    }
    
    private void initDatas() {
    }
    
    public void showSelf() {
        this.getWindow().setWindowAnimations(R.style.diganim);
        this.show();
        this.getWindow().setLayout(DensityUtil.dip2px(330) , DensityUtil.dip2px(360));
    }
    
}
