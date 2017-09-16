package com.medicine.ssqy.ssqy.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.ui.views.calendar.CalendarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarActivity extends Activity {
    private SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
    private SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
    List<Date> markDates = new ArrayList<>();
    private CalendarView calendarView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_calendar);
        
        //在代码中
        calendarView= (CalendarView) findViewById(R.id.calender);
        //设置标注日期
    
//        markDates.add(new Date());
//        markDates.add(new Date(System.currentTimeMillis()+1000*60*60*24));
//        markDates.add(new Date(System.currentTimeMillis()+2*1000*60*60*24));
//        markDates.add(new Date(System.currentTimeMillis()+3*1000*60*60*24));
      
        
        //设置点击操作
        calendarView.setOnCalendarViewListener(new CalendarView.OnCalendarViewListener() {
            
            @Override
            public void onCalendarItemClick(CalendarView view, Date date) {
                markDates.add(date);
                calendarView.setMarkDates(markDates);
//                Toast.makeText(MainActivity.this, format.format(date), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(CalendarActivity.this,RecordDateActivity.class);
                intent.putExtra("date",format.format(date));
                intent.putExtra("date2",format2.format(date));
                startActivity(intent);
            }
        });
    }
}