package com.medicine.ssqy.ssqy.entity;

import java.util.List;

/**
 * Created by Administrator on 2017-09-21.
 */

public class DKRecordEntity {
    
    /**
     * days : 0
     * weekFirstDay : null
     * state : null
     * dkHistory : [{"weekday":1,"date":"09.18","isRecord":false},{"weekday":2,"date":"09.19","isRecord":false},{"weekday":3,"date":"09.20","isRecord":true},{"weekday":4,"date":"09.21","isRecord":false},{"weekday":5,"date":"09.22","isRecord":false},{"weekday":6,"date":"09.23","isRecord":false},{"weekday":7,"date":"09.24","isRecord":false}]
     */
    
    private int days;
    private Object weekFirstDay;
    private Object state;
    private List<DkHistoryEntity> dkHistory;
    
    public int getDays() {
        return days;
    }
    
    public void setDays(int days) {
        this.days = days;
    }
    
    public Object getWeekFirstDay() {
        return weekFirstDay;
    }
    
    public void setWeekFirstDay(Object weekFirstDay) {
        this.weekFirstDay = weekFirstDay;
    }
    
    public Object getState() {
        return state;
    }
    
    public void setState(Object state) {
        this.state = state;
    }
    
    public List<DkHistoryEntity> getDkHistory() {
        return dkHistory;
    }
    
    public void setDkHistory(List<DkHistoryEntity> dkHistory) {
        this.dkHistory = dkHistory;
    }
    
    public static class DkHistoryEntity implements Comparable<DkHistoryEntity>{
        /**
         * weekday : 1
         * date : 09.18
         * isRecord : false
         */
        
        private int weekday;
        private String date;
        private boolean isRecord;
        
        public int getWeekday() {
            return weekday;
        }
        
        public void setWeekday(int weekday) {
            this.weekday = weekday;
        }
        
        public String getDate() {
            return date;
        }
        
        public void setDate(String date) {
            this.date = date;
        }
        
        public boolean isIsRecord() {
            return isRecord;
        }
        
        public void setIsRecord(boolean isRecord) {
            this.isRecord = isRecord;
        }
    
        @Override
        public int compareTo(DkHistoryEntity o) {
            if (o.getWeekday()>this.getWeekday()){
                return -1;
                
            }else if (o.getWeekday()<this.getWeekday()){
                return 1;
            }
            return 0;
        }
    }
}
