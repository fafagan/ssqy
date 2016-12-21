package com.medicine.ssqy.ssqy.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.medicine.ssqy.ssqy.R;

/**
 * Created by Administrator on 2016/12/22.
 */
public class DigPhoneOK extends Dialog {
    private TextView mTvGo;
    
    
    public DigPhoneOK(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dig_phone_ok);
        mTvGo = (TextView) findViewById(R.id.tv_go);
        mTvGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
        
            }
        });
    }
}
