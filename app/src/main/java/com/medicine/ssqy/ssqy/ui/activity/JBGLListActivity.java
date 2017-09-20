package com.medicine.ssqy.ssqy.ui.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.base.KBaseActivity;
import com.medicine.ssqy.ssqy.ui.fragment.jbgl.JBGLBaseFragment;
import com.medicine.ssqy.ssqy.ui.fragment.jbgl.SSListFragment;
import com.medicine.ssqy.ssqy.ui.fragment.jbgl.TZListFragment;
import com.medicine.ssqy.ssqy.ui.fragment.jbgl.XTListFragment;
import com.medicine.ssqy.ssqy.ui.fragment.jbgl.XYListFragment;
import com.medicine.ssqy.ssqy.ui.fragment.jbgl.YDListFragment;
import com.medicine.ssqy.ssqy.ui.fragment.jbgl.YYListFragment;

import org.xutils.common.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

public class JBGLListActivity extends KBaseActivity {
    private List<String> titleList;
    private RadioGroup myRadioGroup;
    private HorizontalScrollView hs_activity_tabbar;
    private LinearLayout ll_activity_tabbar_content;
    private int mCurrentCheckedRadioLeft;
    private JBGLBaseFragment channel;
    private JBGLBaseFragment[] mFragments=new JBGLBaseFragment[6];
    
    @Override
    public int setRootView() {
        return R.layout.activity_jbgllist;
    }
    
    @Override
    public void initViews() {
        setTitleCenter("我的日常记录");
        titleList = new ArrayList<String>();
        titleList.add("血糖记录");
        titleList.add("血压记录");
        titleList.add("体重记录");
        titleList.add("用药记录");
        titleList.add("膳食记录");
        titleList.add("运动记录");
        mFragments[0]=new XTListFragment();
        mFragments[1]=new XYListFragment();
        mFragments[2]=new TZListFragment();
        mFragments[3]=new YYListFragment();
        mFragments[4]=new SSListFragment();
        mFragments[5]=new YDListFragment();
        initGroup();
        setTitleRight("记录今天", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mSelf,HomeActivity.class);
                intent.putExtra("record",true);
                startActivity(intent);
            }
        });
    
    }
    
    private void initGroup() {
        hs_activity_tabbar= (HorizontalScrollView) this.findViewById(R.id.hs_activity_tabbar);
        ll_activity_tabbar_content= (LinearLayout) this.findViewById(R.id.ll_activity_tabbar_content);
        //选项卡布局
        myRadioGroup = new RadioGroup(this);
        myRadioGroup.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        myRadioGroup.setOrientation(LinearLayout.HORIZONTAL);
        ll_activity_tabbar_content.addView(myRadioGroup);
        for (int i = 0; i < titleList.size(); i++) {
            String channel = titleList.get(i);
            RadioButton radio = new RadioButton(this);
            radio.setButtonDrawable(android.R.color.transparent);
            radio.setBackgroundResource(R.drawable.course_center_selector);
            ColorStateList csl = getResources().getColorStateList(R.color.radiobtn_text_color);
            radio.setTextColor(csl);
            LinearLayout.LayoutParams l = new LinearLayout.LayoutParams((int) DensityUtil.dip2px(130), ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER);
            radio.setLayoutParams(l);
            radio.setTextSize(16);
            radio.setGravity(Gravity.CENTER);
            radio.setText(channel);
            radio.setTag(mFragments[i]);
            myRadioGroup.addView(radio);
        }
        
        
        myRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int radioButtonId = group.getCheckedRadioButtonId();
                //根据ID获取RadioButton的实例
                RadioButton rb = (RadioButton) findViewById(radioButtonId);
                channel = (JBGLBaseFragment) rb.getTag();
                mCurrentCheckedRadioLeft = rb.getLeft();//更新当前按钮距离左边的距离
                hs_activity_tabbar.smoothScrollTo((int) (mCurrentCheckedRadioLeft-(int) DensityUtil.dip2px(130)), 0);
                changeFrag(channel);
            }
            
        });
        //设定默认被选中的选项卡为第一项
        if (!titleList.isEmpty()) {
           RadioButton rb= (RadioButton) myRadioGroup.getChildAt(0);
            rb.setChecked(true);
        }
        
    }
    private void changeFrag(JBGLBaseFragment baseFragmentNow) {
        for (JBGLBaseFragment fragment : mFragments) {
            if (fragment!=baseFragmentNow) {
                hideFrag(fragment);
                continue;
            }
        }
        if (!baseFragmentNow.isAdded()) {
            addFrag(R.id.jbgl_frag,baseFragmentNow);
        }
        showFrag(baseFragmentNow);
    }
    
    @Override
    public void initDatas() {
        
    }
}
