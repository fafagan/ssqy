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
public class ModifyMarriageDig extends Dialog implements View.OnClickListener {
    
    private TextView mTvMarriedDigModifySex;
    private TextView mTvUnmarriedDigModifySex;
    
    public ModifyMarriageDig(Context context) {
        super(context);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setGravity(Gravity.CENTER);
        this.setContentView(R.layout.dig_modify_marriage);
        initViews();
        initDatas();
    }
    
    private void initViews() {
        mTvMarriedDigModifySex = (TextView) findViewById(R.id.tv_married_dig_modify_sex);
        mTvUnmarriedDigModifySex = (TextView) findViewById(R.id.tv_unmarried_dig_modify_sex);
    }
    
    private void initDatas() {
        mTvMarriedDigModifySex.setOnClickListener(this);
        mTvUnmarriedDigModifySex.setOnClickListener(this);
    }
    
    public void showSelf() {
        this.getWindow().setWindowAnimations(R.style.diganim);
        this.show();
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_married_dig_modify_sex:
                this.dismiss();
                
                break;
            case R.id.tv_unmarried_dig_modify_sex:
                this.dismiss();
                
                break;
            
        }
    }
}
