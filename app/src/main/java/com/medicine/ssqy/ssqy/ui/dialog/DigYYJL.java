package com.medicine.ssqy.ssqy.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.medicine.ssqy.ssqy.R;


/**
 * Created by Administrator on 2016/11/25.
 */
public class DigYYJL extends Dialog implements View.OnClickListener {
    private Context mContext;
    
    private Button mDigConfirm;
    
  
    
    
    public DigYYJL(final Context context) {
        super(context);
        mContext=context;
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setGravity(Gravity.CENTER);
        this.setContentView(R.layout.dig_yyjl);
        mDigConfirm = (Button) findViewById(R.id.dig_confirm);
        mDigConfirm.setOnClickListener(this);
    }
    
    
    
    @Override
    public void onClick(View v) {
        Toast.makeText(mContext, "已完成记录！", Toast.LENGTH_SHORT).show();
        this.cancel();
    }
}
