package com.medicine.ssqy.ssqy.ui.views.calendar;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.medicine.ssqy.ssqy.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author nanchen
 * @date 16-8-10 上午11:35
 */
public class CalendarGridViewAdapter extends BaseAdapter {
    /** 日历item中默认id从0xff0000开始 */
    private final static int DEFAULT_ID = 0xff0000;
    private Calendar calStartDate = Calendar.getInstance();// 当前显示的日历
    private Calendar calSelected = Calendar.getInstance(); // 选择的日历

    /** 标注的日期 */
    private List<Date> markDates;

    private Context mContext;

    private Calendar calToday = Calendar.getInstance(); // 今日
    private ArrayList<Date> titles;
    private boolean mIsFirst=true;
    private ArrayList<Date> getDates() {

        UpdateStartDateForMonth();

        ArrayList<Date> alArrayList = new ArrayList<Date>();

        for (int i = 1; i <= 42; i++) {
            alArrayList.add(calStartDate.getTime());
            calStartDate.add(Calendar.DAY_OF_MONTH, 1);
        }

        return alArrayList;
    }

    // construct
    public CalendarGridViewAdapter(Context context, Calendar cal, List<Date> dates) {
        calStartDate = cal;
        this.mContext = context;
        titles = getDates();
        this.markDates = dates;
    }

    public CalendarGridViewAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public Object getItem(int position) {
        return titles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressWarnings("deprecation")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 整个Item
        LinearLayout itemLayout = new LinearLayout(mContext);
        itemLayout.setId(position + DEFAULT_ID);
        itemLayout.setGravity(Gravity.CENTER);
        itemLayout.setOrientation(1);
        itemLayout.setBackgroundColor(Color.WHITE);

        Date myDate = (Date) getItem(position);
        itemLayout.setTag(myDate);
        Calendar calCalendar = Calendar.getInstance();
        calCalendar.setTime(myDate);

        // 显示日期day
        TextView textDay = new TextView(mContext);// 日期
        LinearLayout.LayoutParams text_params = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,90);
        textDay.setGravity(Gravity.CENTER);
        int day = myDate.getDate(); // 日期
        textDay.setTextSize(15);
        textDay.setText(String.valueOf(day));
        textDay.setId(position + DEFAULT_ID);
        itemLayout.addView(textDay, text_params);

        // 显示农历
        TextView chineseDay = new TextView(mContext);
        LinearLayout.LayoutParams chinese_params = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, 60);
        chineseDay.setGravity(Gravity.CENTER);
        chineseDay.setTextSize(12);
        CalendarUtil calendarUtil = new CalendarUtil(calCalendar);
        chineseDay.setText(calendarUtil.toString());
        itemLayout.addView(chineseDay, chinese_params);//把农历添加在公历下面

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
     //   Log.e("flag",equalsDate(calToday.getTime(),myDate)+sdf.format(calToday.getTime()));
        // 如果是当前日期则显示不同颜色
      

//        Log.e("tag",equalsDate(myDate,calToday.getTime())+sdf.format(myDate));

//        Log.e("tag",myDate.getMonth()+"---"+sdf.format(myDate));
//        Log.e("tag",calToday.getTime().getMonth()+"====="+sdf.format(calToday.getTime()));
    //    System.out.println("myDate  "+myDate.getMonth()+"　　calStartDate "+calStartDate.getTime().getMonth());
        if (myDate.getMonth()==11&&+calStartDate.getTime().getMonth()==0){
            textDay.setTextColor(mContext.getResources().getColor(R.color.text_color_red));
            chineseDay.setTextColor(mContext.getResources().getColor(R.color.text_color_red));
        }else {
            if (equalsMonth(myDate,calStartDate.getTime())){
                textDay.setTextColor(mContext.getResources().getColor(R.color.text_color_red));
                chineseDay.setTextColor(mContext.getResources().getColor(R.color.text_color_red));
            }else{
                textDay.setTextColor(mContext.getResources().getColor(R.color.date_pre_color));
                chineseDay.setTextColor(mContext.getResources().getColor(R.color.date_pre_color));
            }
        }
    
    
        if (equalsDate(calToday.getTime(), myDate)) {//假定当前是8月10日
//            itemLayout.setBackgroundColor(mContext.getResources().getColor(R.color.date_today_text_color));
            itemLayout.setBackgroundResource(R.drawable.shape_today_calendar);
            chineseDay.setTextColor(0xffffffff);
            textDay.setTextColor(0xffffffff);
        }




        /** 设置标注日期颜色 */
        if (markDates != null) {
            final SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
            for (Date date : markDates) {
                if (format.format(myDate).equals(format.format(date))) {
//                    itemLayout.setBackgroundColor(mContext.getResources().getColor(R.color.date_select_color));
                    chineseDay.setTextColor(0xffffffff);
                    textDay.setTextColor(0xffffffff);
                    itemLayout.setBackgroundResource(R.drawable.shape_record_calendar);
                    break;
                }
            }
        }
        return itemLayout;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @SuppressWarnings("deprecation")
    private Boolean equalsDate(Date date1, Date date2) {
        if (date1.getYear() == date2.getYear()
                && date1.getMonth() == date2.getMonth()
                && date1.getDate() == date2.getDate()) {
            return true;
        } else {
            return false;
        }
    }
    @SuppressWarnings("deprecation")
    private boolean equalsMonth(Date date1,Date date2){
        if (date1.getMonth() == date2.getMonth()-1){
            return true;
        }
        return false;
    }

    // 根据改变的日期更新日历
    // 填充日历控件用
    private void UpdateStartDateForMonth() {

        calStartDate.set(Calendar.DATE, 1); // 设置成当月第一天

        // 星期一是2 星期天是1 填充剩余天数
        int iDay = 0;
        int iFirstDayOfWeek = Calendar.MONDAY;
        int iStartDay = iFirstDayOfWeek;
        if (iStartDay == Calendar.MONDAY) {
            iDay = calStartDate.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY;
            if (iDay < 0)
                iDay = 6;
        }
        if (iStartDay == Calendar.SUNDAY) {
            iDay = calStartDate.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY;
            if (iDay < 0)
                iDay = 6;
        }
        calStartDate.add(Calendar.DAY_OF_WEEK, -iDay);
        calStartDate.add(Calendar.DAY_OF_MONTH, -1);// 周日第一位
    }

    public void setSelectedDate(Calendar cal) {
        calSelected = cal;
    }

}