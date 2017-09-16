package com.medicine.ssqy.ssqy.ui.views.calendar;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.TextView;


/**
 * 显示week的布局adapter
 * @author nanchen
 * @date 16-8-10 上午11:39
 */
public class WeekGridAdapter extends BaseAdapter {
    final String[] titles = new String[] { "日", "一", "二", "三", "四", "五", "六" };
    private Context mContext;

    public WeekGridAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int position) {
        return titles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView week = new TextView(mContext);
        LayoutParams week_params = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                90);
        week.setLayoutParams(week_params);
        week.setPadding(0, 0, 0, 0);
        week.setGravity(Gravity.CENTER);
        week.setFocusable(false);
        week.setBackgroundColor(Color.TRANSPARENT);

//        if (position == 6) { // 周六
//            week.setBackgroundColor(R.color.date_weekend_text_color);
//            week.setTextColor(Color.WHITE);
//        } else if (position == 0) { // 周日
//            week.setBackgroundColor(R.color.date_weekend_text_color);
//            week.setTextColor(Color.WHITE);
//        } else {
            week.setTextColor(Color.BLACK);
//        }
        week.setText(getItem(position)+"");
        return week;
    }
}