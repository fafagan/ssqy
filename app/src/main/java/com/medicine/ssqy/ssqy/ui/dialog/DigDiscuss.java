package com.medicine.ssqy.ssqy.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.sj.mylibrary.util.EdtUtil;
import com.medicine.ssqy.ssqy.R;

/**
 * Created by Administrator on 2016/12/22.
 */
public class DigDiscuss extends Dialog {
    private EditText mEdtDiscussDig;
    private Button mBtDiscussDig;
    private OnConfirmCallback mOnConfirmCallback;
    
    
    public void setOnConfirmCallback(OnConfirmCallback onConfirmCallback) {
        mOnConfirmCallback = onConfirmCallback;
    }
    
    public interface  OnConfirmCallback{
        public void onConfirm(String content);
    }

    
    public DigDiscuss(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setGravity(Gravity.TOP);
        
        setContentView(R.layout.dig_discuss);
        mEdtDiscussDig = (EditText) findViewById(R.id.edt_discuss_dig);
        mBtDiscussDig = (Button) findViewById(R.id.bt_discuss_dig);
    
    
        mBtDiscussDig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnConfirmCallback.onConfirm(EdtUtil.getEdtText(mEdtDiscussDig));
                dismiss();
            }
        });
    }
}
