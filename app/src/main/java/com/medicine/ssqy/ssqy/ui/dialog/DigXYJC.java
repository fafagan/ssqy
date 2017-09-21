package com.medicine.ssqy.ssqy.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.sj.mylibrary.net.NetCallback;
import com.example.sj.mylibrary.net.NetForJson;
import com.example.sj.mylibrary.util.StringEmptyUtil;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.common.URLConstant;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.entity.JCEntity;
import com.medicine.ssqy.ssqy.ui.views.TVWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import java.util.ArrayList;
import java.util.List;

import static com.umeng.socialize.utils.DeviceConfig.context;


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
    private NetForJson mNetForJson;
    private boolean mIsUpdateMode = false;
    private String mReordID;
    private SwipeToLoadLayout mSwipeToLoadLayout;
    
    public void setUpdateMode(String recordID, SwipeToLoadLayout refreshLayout) {
        mIsUpdateMode = true;
        mReordID = recordID;
        mSwipeToLoadLayout = refreshLayout;
    }
    
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
        String string1 = mEdtGy.getText().toString().trim();
        if (StringEmptyUtil.isEmpty(string1)) {
            Toast.makeText(mContext, "请填写高压值再保存！", Toast.LENGTH_SHORT).show();
            return;
        }
        initNet();
        String string2 = mEdtDy.getText().toString().trim();
        if (StringEmptyUtil.isEmpty(string2)) {
            Toast.makeText(mContext, "请填写低压值再保存！", Toast.LENGTH_SHORT).show();
            return;
        }
    
        if (mIsUpdateMode) {
            Toast.makeText(mContext, "正在修改，请稍等", Toast.LENGTH_SHORT).show();
            mNetForJson.addParam("uid", SharePLogin.getUid());
            mNetForJson.addParam("recordtype", URLConstant.RECORD_TYPE_XY);
            mNetForJson.addParam("recordID", mReordID);
            mNetForJson.addParam("pressuremax", string1);
            mNetForJson.addParam("pressuremin", string2);
            mNetForJson.excute();
        }else {
            Toast.makeText(mContext, "正在记录，请稍等", Toast.LENGTH_SHORT).show();
            mNetForJson.addParam("uid", SharePLogin.getUid());
            mNetForJson.addParam("recordtype", URLConstant.RECORD_TYPE_XY);
            mNetForJson.addParam("pressuremax", string1);
            mNetForJson.addParam("pressuremin", string2);
            mNetForJson.excute();
        }
   
    }
    private void initNet() {
        if (mNetForJson==null) {
            if (mIsUpdateMode) {
                mNetForJson=new NetForJson(URLConstant.JBGL_MODIFY_URL, new NetCallback<JCEntity>() {
                    @Override
                    public void onSuccess(JCEntity entity) {
                        if (entity.isState()) {
                            Toast.makeText(getContext(), "修改成功！", Toast.LENGTH_SHORT).show();
                            if (mSwipeToLoadLayout != null) {
                                mSwipeToLoadLayout.setRefreshing(true);
                            }
                            cancel();
                        }else {
                            Toast.makeText(getContext(), "修改异常，请重试！", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError() {
                        Toast.makeText(context, "记录失败，请检查您的网络状态！", Toast.LENGTH_SHORT).show();
                    }
                    
                    @Override
                    public void onFinish() {
                        
                    }
                },true);
            }else {
                mNetForJson=new NetForJson(URLConstant.JBGL_ADD_URL, new NetCallback<JCEntity>() {
                    @Override
                    public void onSuccess(JCEntity entity) {
                        if (entity.isState()) {
                            Toast.makeText(getContext(), "记录成功！", Toast.LENGTH_SHORT).show();
                            cancel();
                        }else {
                            Toast.makeText(getContext(), "当天记录已存在，请查看我的疾病管理", Toast.LENGTH_SHORT).show();
                            
                        }
                    }
                    
                    @Override
                    public void onError() {
                        Toast.makeText(context, "记录失败，请检查您的网络状态！", Toast.LENGTH_SHORT).show();
                    }
                    
                    @Override
                    public void onFinish() {
                        
                    }
                },true);
            }
        }
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
