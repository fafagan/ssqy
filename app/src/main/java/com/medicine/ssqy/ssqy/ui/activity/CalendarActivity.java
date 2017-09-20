package com.medicine.ssqy.ssqy.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.widget.Toast;

import com.example.sj.mylibrary.net.NetCallback;
import com.example.sj.mylibrary.net.NetForJson;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.common.URLConstant;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.entity.CalanderListEntity;
import com.medicine.ssqy.ssqy.ui.adapter.ItemRecyCalendarAdapter;
import com.medicine.ssqy.ssqy.ui.views.calendar.CalendarView;
import com.medicine.ssqy.ssqy.util.TimeFormatUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarActivity extends Activity {
    private SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
    private SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
    List<Date> markDates = new ArrayList<>();
    private CalendarView calendarView;
    private RecyclerView mRecyCalendar;
    
    private ItemRecyCalendarAdapter mItemRecyCalendarAdapter;
    private NetForJson mNetForJson;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_calendar);
        
        //在代码中
        calendarView = (CalendarView) findViewById(R.id.calender);
        //设置标注日期

//        markDates.add(new Date());
//        markDates.add(new Date(System.currentTimeMillis()+1000*60*60*24));
//        markDates.add(new Date(System.currentTimeMillis()+2*1000*60*60*24));
//        markDates.add(new Date(System.currentTimeMillis()+3*1000*60*60*24));
        
        mRecyCalendar = (RecyclerView) findViewById(R.id.recy_calendar);
        mRecyCalendar.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        
        //设置点击操作
        calendarView.setOnCalendarViewListener(new CalendarView.OnCalendarViewListener() {
            
            @Override
            public void onCalendarItemClick(CalendarView view, Date date) {
                if (date.getTime()>System.currentTimeMillis()){
                    Toast.makeText(CalendarActivity.this, "只能记录今天及以前的日记呦！", Toast.LENGTH_SHORT).show();
                    return;
                }
                markDates.add(date);
                calendarView.setMarkDates(markDates);
//                Toast.makeText(MainActivity.this, format.format(date), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CalendarActivity.this, RecordDateActivity.class);
                intent.putExtra("date", format.format(date));
                intent.putExtra("date2", format2.format(date));
                startActivity(intent);
            }
        });
        
        
        mNetForJson = new NetForJson(URLConstant.QUERY_MONTH_DIARYS_URL, new NetCallback<CalanderListEntity>() {
            @Override
            public void onSuccess(CalanderListEntity entity) {
                if (entity.isState()) {
                    if (mItemRecyCalendarAdapter == null) {
                        for (CalanderListEntity.CurrentMonthEntity.RecordsEntity recordsEntity : entity.getCurrentMonth().getRecords()) {
                            Date date=new Date();
    
                            try {
                                String[] split = recordsEntity.getDate().split("-");
                                date.setYear(Integer.parseInt(split[0])-1900);
                                date.setMonth(Integer.parseInt(split[1])-1);
                                date.setDate(Integer.parseInt(split[2]));
                               
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            markDates.add(date);
                        }
                        calendarView.setMarkDates(markDates);
                        mItemRecyCalendarAdapter=new ItemRecyCalendarAdapter(CalendarActivity.this,entity.getCurrentMonth().getRecords());
                        mItemRecyCalendarAdapter.setOnItemDateClick(new ItemRecyCalendarAdapter.OnItemDateClick() {
                            @Override
                            public void onItemClick(String date) {
                                Intent intent = new Intent(CalendarActivity.this, RecordDateActivity.class);
                                intent.putExtra("date", date);
                                intent.putExtra("date2", date);
                                startActivity(intent);
                            }
                        });
                        mRecyCalendar.setAdapter(mItemRecyCalendarAdapter);
                    } else {
                        mItemRecyCalendarAdapter.setRecordsEntities(entity.getCurrentMonth().getRecords());
                    }
                } else {
                    Toast.makeText(CalendarActivity.this, "加载失败，请检查您的网络状态！", Toast.LENGTH_SHORT).show();
                }
            }
            
            @Override
            public void onError() {
                Toast.makeText(CalendarActivity.this, "加载失败，请检查您的网络状态！", Toast.LENGTH_SHORT).show();
            }
            
            @Override
            public void onFinish() {
                
            }
        }, true);
        mNetForJson.addParam("uid", SharePLogin.getUid());
        mNetForJson.addParam("date", TimeFormatUtil.formatLongToNYR(System.currentTimeMillis()));
        mNetForJson.excute();
    }
}