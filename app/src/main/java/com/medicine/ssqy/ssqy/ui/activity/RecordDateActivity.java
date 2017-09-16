package com.medicine.ssqy.ssqy.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sj.mylibrary.net.NetCallback;
import com.example.sj.mylibrary.net.NetForJson;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.common.URLConstant;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.entity.RecordEntity;
import com.medicine.ssqy.ssqy.ui.views.calendar.StringUtil;

public class RecordDateActivity extends Activity {
    private RelativeLayout mActivityRecordDate;
    private RelativeLayout mLayoutTitleRecord;
    private Button mBtBackRecord;
    private TextView mTvTimeRecord;
    private ImageButton mBtSaveRecord;
    private Button mBtDeleteRecord;
    private TextView mTvWelcomeRecord;
    private EditText mEdtContentRecord;
    private String mDate;
    private String mDate2;
    AlertDialog mAlertDialog;
    private NetForJson mNetForJsonQuery;
    private NetForJson mNetForJsonSave;
    private boolean mHasSaveOk=true;
    private boolean mIsFinishMode=false;
    private TextView mTvCountRecord;
    private  long mTimeBegin=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_record_date);
        mActivityRecordDate = (RelativeLayout) findViewById(R.id.activity_record_date);
        mLayoutTitleRecord = (RelativeLayout) findViewById(R.id.layout_title_record);
        mBtBackRecord = (Button) findViewById(R.id.bt_back_record);
        mTvTimeRecord = (TextView) findViewById(R.id.tv_time_record);
        mBtSaveRecord = (ImageButton) findViewById(R.id.bt_save_record);
        mBtDeleteRecord = (Button) findViewById(R.id.bt_delete_record);
        mTvWelcomeRecord = (TextView) findViewById(R.id.tv_welcome_record);
        mEdtContentRecord = (EditText) findViewById(R.id.edt_content_record);
        mEdtContentRecord.setPivotX(500);
    
        mTvCountRecord = (TextView) findViewById(R.id.tv_count_record);
        mEdtContentRecord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        
            }
    
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mHasSaveOk=false;
                mTvCountRecord.setText("已"+s.length()+"字 / 最多500字");
            }
    
            @Override
            public void afterTextChanged(Editable s) {
        
            }
        });
        mBtSaveRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBtSaveRecord.setOnClickListener(null);
                Toast.makeText(RecordDateActivity.this, "正在为您保存日记，请不要关闭程序。。", Toast.LENGTH_SHORT).show();
                doSave();
            }
        });
       
        mDate = this.getIntent().getStringExtra("date");
        mDate2 = this.getIntent().getStringExtra("date2");
        mTvTimeRecord.setText(mDate);
        mAlertDialog=new AlertDialog.Builder(this).
                setTitle("四时七养")
                .setMessage("请问您确定清空日记吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mEdtContentRecord.setText("");
        
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .create();
        mBtDeleteRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
    
                mAlertDialog.show();
            }
        });
        mBtBackRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsFinishMode=true;
                if (mHasSaveOk) {
                    finish();
                }else {
                    long timeNow=System.currentTimeMillis();
                    if (timeNow-mTimeBegin<3000) {
                        finish();
                    }else {
                        Toast.makeText(RecordDateActivity.this, "发现您尚未保存日记，确定退出吗？", Toast.LENGTH_SHORT).show();
                    }
                    mTimeBegin=timeNow;
                }
        
            }
    
            
        });
        
        
        loadDatas();
        mBtDeleteRecord.requestFocus();
    }
    
    @Override
    public void onBackPressed() {
    
        mIsFinishMode=true;
        if (mHasSaveOk) {
            super.onBackPressed();
        }else {
            long timeNow=System.currentTimeMillis();
            if (timeNow-mTimeBegin<3000) {
                finish();
            }else {
                Toast.makeText(RecordDateActivity.this, "发现您尚未保存日记，确定退出吗？", Toast.LENGTH_SHORT).show();
            }
            mTimeBegin=timeNow;
        }
        
    }
    
    private void doSave() {
    
        if (mNetForJsonSave==null) {
            mNetForJsonSave=new NetForJson(URLConstant.SAVE_DIARY_URL,new NetCallback<RecordEntity>(){
                @Override
                public void onSuccess(RecordEntity entity) {
               
                    if (entity.isState()) {
                        mHasSaveOk=true;
                      
                        Toast.makeText(RecordDateActivity.this, "日记保存成功!", Toast.LENGTH_SHORT).show();
    
                    }else {
                        Toast.makeText(RecordDateActivity.this, "保存失败，请检查网络状态", Toast.LENGTH_SHORT).show();
                        mIsFinishMode=false;
                    }
                
                    
                }
    
                @Override
                public void onError() {
                    Toast.makeText(RecordDateActivity.this, "保存失败，请检查网络状态", Toast.LENGTH_SHORT).show();
                    mIsFinishMode=false;
                    mHasSaveOk= false;
                }
    
                @Override
                public void onFinish() {
                    mBtSaveRecord.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mBtSaveRecord.setOnClickListener(null);
                            Toast.makeText(RecordDateActivity.this, "正在为您保存日记，请不要关闭程序。。", Toast.LENGTH_SHORT).show();
                            doSave();
                        }
                    });
        
                }
            },true);
        }
    
        mNetForJsonSave.addParam("date",mDate2);
        mNetForJsonSave.addParam("msg",mEdtContentRecord.getText().toString());
        mNetForJsonSave.addParam("uid", SharePLogin.getUid());
        mNetForJsonSave.excute();
    }
    
    private void loadDatas() {
        mNetForJsonQuery = new NetForJson(URLConstant.QUERY_DIARY_URL,new NetCallback<RecordEntity>(){
    
            @Override
            public void onSuccess(RecordEntity entity) {
                Toast.makeText(RecordDateActivity.this, "日记加载完毕", Toast.LENGTH_SHORT).show();
                
                if (!StringUtil.isNullOrEmpty(entity.getMsg())) {
                    mEdtContentRecord.setText(entity.getMsg());
                    mTvCountRecord.setText("已"+entity.getMsg().length()+"字 / 最多500字");
                }
            }
    
            @Override
            public void onError() {
                Toast.makeText(RecordDateActivity.this, "加载异常，请检查网络状态", Toast.LENGTH_SHORT).show();
            }
    
            @Override
            public void onFinish() {
             
            }
        },true);
        mNetForJsonQuery.addParam("date",mDate2);
        mNetForJsonQuery.addParam("uid", SharePLogin.getUid());
        mNetForJsonQuery.excute();
    }
    
}
