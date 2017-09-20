package com.medicine.ssqy.ssqy.ui.fragment.first;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseFragment;
import com.medicine.ssqy.ssqy.eventBus.FirstLoginMsg;

import org.greenrobot.eventbus.EventBus;

public class FirstLoginSexFragment extends KBaseFragment implements RadioGroup.OnCheckedChangeListener {
    
    private RadioButton mRbFirstLoginSexMan;
    private RadioButton mRbFirstLoginSexWoman;
    private ImageView mImgvFirstLoginSexMan;
    private TextView mTvFirstLoginSexMan;
    private ImageView mImgvFirstLoginSexWoman;
    private TextView mTvFirstLoginSexWoman;
    private RadioButton mRbFirstLoginMarryNo;
    private RadioButton mRbFirstLoginMarryYes;
    private ImageView mImgvFirstLoginMarryNo;
    private TextView mTvFirstLoginSexMarryNo;
    private ImageView mImgvFirstLoginMarryYes;
    private TextView mTvFirstLoginSexMarryYes;
    private Button mBtNext;
    private RadioGroup mRgSex;
    private RadioGroup mRgMarray;
    private int mTextColorDefault;

    
    
    @Override
    public boolean isUseTitle() {
        return false;
    }
    
    @Override
    public int setRootView() {
        return R.layout.fragment_first_login_sex;
    }
    
    @Override
    public void initViews() {
        mTextColorDefault=this.getResources().getColor(R.color.text_common_default);
        mRbFirstLoginSexMan = (RadioButton) findViewById(R.id.rb_first_login_sex_man);
        mRbFirstLoginSexWoman = (RadioButton) findViewById(R.id.rb_first_login_sex_woman);
        mImgvFirstLoginSexMan = (ImageView) findViewById(R.id.imgv_first_login_sex_man);
        mTvFirstLoginSexMan = (TextView) findViewById(R.id.tv_first_login_sex_man);
        mImgvFirstLoginSexWoman = (ImageView) findViewById(R.id.imgv_first_login_sex_woman);
        mTvFirstLoginSexWoman = (TextView) findViewById(R.id.tv_first_login_sex_woman);
        mRbFirstLoginMarryNo = (RadioButton) findViewById(R.id.rb_first_login_marry_no);
        mRbFirstLoginMarryYes = (RadioButton) findViewById(R.id.rb_first_login_marry_yes);
        mImgvFirstLoginMarryNo = (ImageView) findViewById(R.id.imgv_first_login_marry_no);
        mTvFirstLoginSexMarryNo = (TextView) findViewById(R.id.tv_first_login_sex_marry_no);
        mImgvFirstLoginMarryYes = (ImageView) findViewById(R.id.imgv_first_login_marry_yes);
        mTvFirstLoginSexMarryYes = (TextView) findViewById(R.id.tv_first_login_sex_marry_yes);
        mBtNext = (Button) findViewById(R.id.bt_next);
        mRgSex = (RadioGroup) findViewById(R.id.rg_sex);
        mRgMarray = (RadioGroup) findViewById(R.id.rg_marray);
    
        mRgSex.setOnCheckedChangeListener(this);
        mRgMarray.setOnCheckedChangeListener(this);
        
        mRbFirstLoginSexMan.setChecked(true);
        mRbFirstLoginMarryNo.setChecked(true);
    
        mBtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
    
                FirstLoginMsg firstLoginMsg=new FirstLoginMsg();
                firstLoginMsg.action=FirstLoginMsg.ACTION_DONE_1;
//                firstLoginMsg.sex=mRbFirstLoginSexMan.isChecked()?"man":"woman";
                firstLoginMsg.sex=mRbFirstLoginSexMan.isChecked()?"1":"2";
                firstLoginMsg.isMarried=mRbFirstLoginMarryYes.isChecked()?"1":"0";
                EventBus.getDefault().post(firstLoginMsg);
            }
        });
    
    }
    
    @Override
    public void initDatas() {
        
    }
    
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (group==mRgSex){
    
            switch (checkedId) {
                case R.id.rb_first_login_sex_man:
                    mTvFirstLoginSexMan.setTextColor(0xff11509d);
                    mImgvFirstLoginSexMan.setImageResource(R.drawable.reg_sex_man_checked);
                    
                    mTvFirstLoginSexWoman.setTextColor(mTextColorDefault);
                    mImgvFirstLoginSexWoman.setImageResource(R.drawable.reg_sex_woman_normal);
                    break;
                case R.id.rb_first_login_sex_woman:
                    mTvFirstLoginSexWoman.setTextColor(0xFFAD5252);
                    mImgvFirstLoginSexWoman.setImageResource(R.drawable.reg_sex_woman_checked);
    
                    mTvFirstLoginSexMan.setTextColor(mTextColorDefault);
                    mImgvFirstLoginSexMan.setImageResource(R.drawable.reg_sex_man_normal);
                    break;
            }
            
        }else {
            switch (checkedId) {
                case R.id.rb_first_login_marry_no:
                    mTvFirstLoginSexMarryNo.setTextColor(0xff11509d);
                    mImgvFirstLoginMarryNo.setImageResource(R.drawable.reg_marry_no_checked);
            
                    mTvFirstLoginSexMarryYes.setTextColor(mTextColorDefault);
                    mImgvFirstLoginMarryYes.setImageResource(R.drawable.reg_marry_yes_normal);
                    break;
                case R.id.rb_first_login_marry_yes:
                    mTvFirstLoginSexMarryYes.setTextColor(0xFFAD5252);
                    mImgvFirstLoginMarryYes.setImageResource(R.drawable.reg_marry_yes_checked);
            
                    mTvFirstLoginSexMarryNo.setTextColor(mTextColorDefault);
                    mImgvFirstLoginMarryNo.setImageResource(R.drawable.reg_marry_no_normal);
                    break;
            }
            
        }
    }
}
