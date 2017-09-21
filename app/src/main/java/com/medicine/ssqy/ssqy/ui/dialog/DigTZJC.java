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
public class DigTZJC extends Dialog implements View.OnClickListener {
    private Context mContext;
    
    private Button mDigConfirm;
    private List<String> mTZs = new ArrayList<>();
    private EditText mEdtTz;
    
    private WheelView wvJobFirstLogin;
    private NetForJson mNetForJson;
    private boolean mIsUpdateMode = false;
    private String mReordID;
    private SwipeToLoadLayout mSwipeToLoadLayout;
    
    public void setUpdateMode(String recordID, SwipeToLoadLayout refreshLayout) {
        mIsUpdateMode = true;
        mReordID = recordID;
        mSwipeToLoadLayout = refreshLayout;
    }
    
    public DigTZJC(final Context context) {
        super(context);
        mContext = context;
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setGravity(Gravity.CENTER);
        this.setContentView(R.layout.dig_tzjc);
        mDigConfirm = (Button) findViewById(R.id.dig_confirm);
        mEdtTz = (EditText) findViewById(R.id.edt_tz);
        mDigConfirm.setOnClickListener(this);
        wvJobFirstLogin = (WheelView) findViewById(R.id.wv_job_first_login);
        initWVDatas();
        wvJobFirstLogin.setWheelAdapter(new TVWheelAdapter(context)); // 文本数据源
        WheelView.WheelViewStyle wheelViewStyle = new WheelView.WheelViewStyle();
        wheelViewStyle.backgroundColor = 0x00ffffff;
        wvJobFirstLogin.setStyle(wheelViewStyle);
        wvJobFirstLogin.setSkin(WheelView.Skin.None); // common皮肤
        wvJobFirstLogin.setWheelData(mTZs);  // 数据集合
        wvJobFirstLogin.setLoop(true);
        wvJobFirstLogin.setWheelSize(3);
        wvJobFirstLogin.setSelection(6);
        wvJobFirstLogin.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener() {
            @Override
            public void onItemSelected(int i, Object o) {
                mEdtTz.setText(o.toString().replace("公斤", ""));
            }
        });
        
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
    
    
    @Override
    public void onClick(View v) {
        
        
        String string = mEdtTz.getText().toString().trim();
        if (StringEmptyUtil.isEmpty(string)) {
            Toast.makeText(mContext, "请填写体重值再保存！", Toast.LENGTH_SHORT).show();
            return;
        }
        
        Toast.makeText(mContext, "正在记录，请稍等", Toast.LENGTH_SHORT).show();
        initNet();
        if (mIsUpdateMode) {
            mNetForJson.addParam("uid", SharePLogin.getUid());
            mNetForJson.addParam("recordtype", URLConstant.RECORD_TYPE_TZ);
            mNetForJson.addParam("recordID", mReordID);
            mNetForJson.addParam("newdata", string);
            mNetForJson.excute();
        } else {
            mNetForJson.addParam("uid", SharePLogin.getUid());
            mNetForJson.addParam("recordtype", URLConstant.RECORD_TYPE_TZ);
            mNetForJson.addParam("data", string);
            mNetForJson.excute();
        }
    }
    
    private void initWVDatas() {
        for (int i = 40; i <= 150; i += 5) {
            mTZs.add(i + " 公斤");
        }
        
    }
}
