package com.medicine.ssqy.ssqy.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.sj.mylibrary.net.NetCallback;
import com.example.sj.mylibrary.net.NetForJson;
import com.example.sj.mylibrary.util.StringEmptyUtil;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.common.URLConstant;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.entity.JCEntity;

import static com.umeng.socialize.utils.DeviceConfig.context;


/**
 * Created by Administrator on 2016/11/25.
 */
public class DigSSJL extends Dialog implements View.OnClickListener {
    private Context mContext;
    
    private Button mDigConfirm;
    
    private EditText mEdtYy;
    private NetForJson mNetForJson;
    
    private TextView mTvTitleJbgl;
    

    private boolean mIsUpdateMode=false;
    private String mReordID;
    private SwipeToLoadLayout mSwipeToLoadLayout;
    public void setUpdateMode(String recordID,SwipeToLoadLayout refreshLayout) {
        mIsUpdateMode = true;
        mReordID=recordID;
        mSwipeToLoadLayout=refreshLayout;
    }
    
    public DigSSJL(final Context context) {
        super(context);
        mContext=context;
        
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setGravity(Gravity.CENTER);
        this.setContentView(R.layout.dig_yyjl);
        mDigConfirm = (Button) findViewById(R.id.dig_confirm);
        mEdtYy = (EditText) findViewById(R.id.edt_yy);
        mDigConfirm.setOnClickListener(this);
        mTvTitleJbgl = (TextView) findViewById(R.id.tv_title_jbgl);
        mTvTitleJbgl.setText("请填写您今天的膳食情况：");
      
    }
    
    
    
    @Override
    public void onClick(View v) {
        String string = mEdtYy.getText().toString().trim();
        if (StringEmptyUtil.isEmpty(string)) {
            Toast.makeText(mContext, "请填写膳食情况再保存！", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(mContext, "正在记录，请稍等", Toast.LENGTH_SHORT).show();
    
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
    
        if (mIsUpdateMode) {
            mNetForJson.addParam("uid", SharePLogin.getUid());
            mNetForJson.addParam("recordtype", URLConstant.RECORD_TYPE_SS);
            mNetForJson.addParam("recordID", mReordID);
            mNetForJson.addParam("newdata", string);
            mNetForJson.excute();
        }else {
            mNetForJson.addParam("uid", SharePLogin.getUid());
            mNetForJson.addParam("recordtype", URLConstant.RECORD_TYPE_SS);
            mNetForJson.addParam("data", string);
            mNetForJson.excute();
        }
     
    }
}
