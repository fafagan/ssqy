package com.medicine.ssqy.ssqy.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sj.mylibrary.net.NetCallback;
import com.example.sj.mylibrary.net.NetForJson;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.common.URLConstant;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.entity.JCEntity;

import static com.umeng.socialize.utils.DeviceConfig.context;


/**
 * Created by Administrator on 2016/11/25.
 */
public class DigXTJC extends Dialog implements View.OnClickListener {
    private Context mContext;
    
    private Button mDigConfirm;
    
    private NetForJson mNetForJson;
    private EditText mEdtTz;
    

    
    public DigXTJC(final Context context) {
        super(context);
        mContext=context;
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setGravity(Gravity.CENTER);
        this.setContentView(R.layout.dig_xtjc);
        mDigConfirm = (Button) findViewById(R.id.dig_confirm);
        mDigConfirm.setOnClickListener(this);
        mEdtTz = (EditText) findViewById(R.id.edt_xt);
        initNet();
    }
    
    private void initNet() {
    
        mNetForJson=new NetForJson(URLConstant.JBGL_ADD_URL, new NetCallback<JCEntity>() {
            @Override
            public void onSuccess(JCEntity entity) {
                Toast.makeText(getContext(), "记录成功！", Toast.LENGTH_SHORT).show();
                cancel();
                
            }
        
            @Override
            public void onError() {
                Toast.makeText(context, "记录失败，请检查您的网络状态！", Toast.LENGTH_SHORT).show();
            
            }
        
            @Override
            public void onFinish() {
            
            }
        });
    
    }
    
    
    @Override
    public void onClick(View v) {
        mNetForJson.addParam("uid", SharePLogin.getUid());
        mNetForJson.addParam("recordtype", URLConstant.RECORD_TYPE_XT);
        mNetForJson.addParam("data", mEdtTz.getText().toString());
        mNetForJson.addParam("time", System.currentTimeMillis());
        mNetForJson.excute();
    }
}
