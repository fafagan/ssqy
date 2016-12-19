package com.medicine.ssqy.ssqy.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.ui.views.TVWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/20.
 */
public class WheelPickDig extends Dialog {
    private final WheelView mWheelView;
    private List<String> mDatas;
    private Button mBtCancelDigWheelPicker;
    private Button mBtConfirmDigWheelPicker;
    private OnConfirmListener mOnConfirmListener;

    
    public WheelPickDig(Context context, String title, List<String> datas) {
        super(context);
        mDatas=datas;
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dig_wheel_picker);
        TextView textView= (TextView) this.findViewById(R.id.tv_title_dig_wheel_picker);
        textView.setText(title);
        mBtCancelDigWheelPicker = (Button) findViewById(R.id.bt_cancel_dig_wheel_picker);
        mBtConfirmDigWheelPicker = (Button) findViewById(R.id.bt_confirm_dig_wheel_picker);
    
        mBtCancelDigWheelPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
      
        mWheelView = (WheelView)this. findViewById(R.id.wheelView_dig_wheel_picker);
        mWheelView.setWheelAdapter(new TVWheelAdapter(context)); // 文本数据源
        mWheelView.setSkin(WheelView.Skin.None); // common皮肤
        mWheelView.setWheelData(datas);  // 数据集合
        mWheelView.setLoop(true);
        mWheelView.setWheelSize(3);
        mWheelView.setSelection(0);
      
    }
    public String getSelectedItem(){
        return (String) mWheelView.getSelectionItem();
    }
    
    public void setOnConfirmListener(OnConfirmListener onConfirmListener) {
        mOnConfirmListener = onConfirmListener;
        mBtConfirmDigWheelPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnConfirmListener.onConfirm((String) mWheelView.getSelectionItem(),v);
                cancel();
            }
        });
    }
    
    public interface OnConfirmListener{
        public void onConfirm(String item, View view);
    }
}
