package com.medicine.ssqy.ssqy.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.ui.views.TVWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/11/25.
 */
public class DigXYJC extends Dialog implements View.OnClickListener {
    private Context mContext;
    
    private Button mDigConfirm;
    
    private EditText mEdtDy;
    private EditText mEdtGy;
    private WheelView mWvDy;
    private WheelView mWvGy;
    
    private List<String> mDYs=new ArrayList<>();
    private List<String> mGYs=new ArrayList<>();
    
    
    public DigXYJC(final Context context) {
        super(context);
        mContext=context;
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setGravity(Gravity.CENTER);
        this.setContentView(R.layout.dig_xyjc);
        mDigConfirm = (Button) findViewById(R.id.dig_confirm);
        mDigConfirm.setOnClickListener(this);
    
        mEdtDy = (EditText) findViewById(R.id.edt_dy);
        mEdtGy = (EditText) findViewById(R.id.edt_gy);
        mWvDy = (WheelView) findViewById(R.id.wv_dy);
        mWvGy = (WheelView) findViewById(R.id.wv_gy);
        
        
        
        initWVDatas();
        mWvDy.setWheelAdapter(new TVWheelAdapter(context)); // 文本数据源
        WheelView.WheelViewStyle wheelViewStyle = new WheelView.WheelViewStyle();
        wheelViewStyle.backgroundColor = 0x00ffffff;
        mWvDy.setStyle(wheelViewStyle);
        mWvDy.setSkin(WheelView.Skin.None); // common皮肤
        mWvDy.setWheelData(mDYs);  // 数据集合
        mWvDy.setLoop(true);
        mWvDy.setWheelSize(3);
        mWvDy.setSelection(5);
        mWvDy.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener() {
            @Override
            public void onItemSelected(int i, Object o) {
                mEdtDy.setText(o.toString().replace("mmHg",""));
            }
        });
    
    
        mWvGy.setWheelAdapter(new TVWheelAdapter(context)); // 文本数据源
        WheelView.WheelViewStyle wheelViewStyle2 = new WheelView.WheelViewStyle();
        wheelViewStyle.backgroundColor = 0x00ffffff;
       mWvGy.setStyle(wheelViewStyle2);
       mWvGy.setSkin(WheelView.Skin.None); // common皮肤
       mWvGy.setWheelData(mGYs);  // 数据集合
       mWvGy.setLoop(true);
       mWvGy.setWheelSize(3);
       mWvGy.setSelection(5);
       mWvGy.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener() {
            @Override
            public void onItemSelected(int i, Object o) {
                mEdtGy.setText(o.toString().replace("mmHg",""));
            }
        });
    }
    
    
    
    @Override
    public void onClick(View v) {
        Toast.makeText(mContext, "已完成记录！", Toast.LENGTH_SHORT).show();
        this.cancel();
    }
    
    private void initWVDatas() {
        for (int i = 60; i <= 90; i+=5) {
            mDYs.add(i+" mmHg");
        }
        for (int i = 100; i <= 160; i+=5) {
            mGYs.add(i+" mmHg");
        }
        
    }
}
