package com.medicine.ssqy.ssqy.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.medicine.ssqy.ssqy.R;

/**
 * Created by Amy on 2016/12/16.
 */
public class ModifySexDig extends Dialog implements View.OnClickListener {
    private TextView mTvManDigModifySex;
    private TextView mTvWomanDigModifySex;
    public ModifySexDig(Context context) {
        super(context);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setGravity(Gravity.CENTER);
        this.setContentView(R.layout.dig_modify_sex);
        initViews();
        initDatas();
    }
    
    private void initViews() {
        mTvManDigModifySex = (TextView) findViewById(R.id.tv_man_dig_modify_sex);
        mTvWomanDigModifySex = (TextView) findViewById(R.id.tv_woman_dig_modify_sex);
    
    }
    
    private void initDatas() {
        mTvManDigModifySex.setOnClickListener(this);
        mTvWomanDigModifySex.setOnClickListener(this);
    }
    public void showSelf() {
        this.getWindow().setWindowAnimations(R.style.diganim);
        this.show();
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_man_dig_modify_sex:
                this.dismiss();
                
                break;
            case R.id.tv_woman_dig_modify_sex:
                this.dismiss();
                
                break;
        
        }
    }
}
