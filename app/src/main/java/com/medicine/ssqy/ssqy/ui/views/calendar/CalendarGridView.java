package com.medicine.ssqy.ssqy.ui.views.calendar;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.medicine.ssqy.ssqy.ui.views.MeasureGridView;

/**
 *
 * 用于生成日历展示的GridView布局
 *
 * @author nanchen
 * @date 16-8-10 上午11:35
 */
public class CalendarGridView extends MeasureGridView {
    /**
     * 当前操作的上下文对象
     */
    private Context mContext;

    /**
     * CalendarGridView 构造器
     *
     * @param context
     *            当前操作的上下文对象
     */
    public CalendarGridView(Context context) {
        super(context);
        mContext = context;
        initGirdView();
    }

    /**
     * 初始化gridView 控件的布局
     */
    private void initGirdView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);
        setNumColumns(7);// 设置每行列数
        setGravity(Gravity.CENTER_VERTICAL);// 位置居中
        setVerticalSpacing(1);// 垂直间隔
        setHorizontalSpacing(1);// 水平间隔
        setBackgroundColor(Color.argb(0xff, 0xe3, 0xee, 0xf4));

        int i = mContext.getResources().getDisplayMetrics().widthPixels / 7;
        int j = mContext.getResources().getDisplayMetrics().widthPixels
                - (i * 7);
        int x = j / 2;
        setPadding(x, 0, 0, 0);// 居中
    }
}