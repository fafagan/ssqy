package com.medicine.ssqy.ssqy.entity;

import java.util.List;

/**
 * Created by Administrator on 2017-09-18.
 */

public class CalanderListEntity {
    
    
    /**
     * uid : u002
     * state : true
     * currentMonth : {"days":30,"recorddays":8,"records":[{"title":"aasksnzhs","date":"2017-09-12"},{"title":"今天吃药了啊啊","date":"2017-09-01"},{"title":"jsjdjdkd哈哈","date":"2017-09-27"},{"title":"哈哈呵呵嘿嘿哪呀垃圾","date":"2017-09-07"},{"title":"开心开心","date":"2017-09-06"},{"title":"吉拉拉","date":"2017-09-08"},{"title":"ghjkkhfxhj","date":"2017-09-09"},{"title":"今天吃药了","date":"2017-08-31"}]}
     * prevMonth : {"days":31,"recorddays":0,"records":[]}
     * nextMonth : {"days":31,"recorddays":0,"records":[]}
     */
    
    private String uid;
    private boolean state;
    private CurrentMonthEntity currentMonth;
    private PrevMonthEntity prevMonth;
    private NextMonthEntity nextMonth;
    
    public String getUid() {
        return uid;
    }
    
    public void setUid(String uid) {
        this.uid = uid;
    }
    
    public boolean isState() {
        return state;
    }
    
    public void setState(boolean state) {
        this.state = state;
    }
    
    public CurrentMonthEntity getCurrentMonth() {
        return currentMonth;
    }
    
    public void setCurrentMonth(CurrentMonthEntity currentMonth) {
        this.currentMonth = currentMonth;
    }
    
    public PrevMonthEntity getPrevMonth() {
        return prevMonth;
    }
    
    public void setPrevMonth(PrevMonthEntity prevMonth) {
        this.prevMonth = prevMonth;
    }
    
    public NextMonthEntity getNextMonth() {
        return nextMonth;
    }
    
    public void setNextMonth(NextMonthEntity nextMonth) {
        this.nextMonth = nextMonth;
    }
    
    public static class CurrentMonthEntity {
        /**
         * days : 30
         * recorddays : 8
         * records : [{"title":"aasksnzhs","date":"2017-09-12"},{"title":"今天吃药了啊啊","date":"2017-09-01"},{"title":"jsjdjdkd哈哈","date":"2017-09-27"},{"title":"哈哈呵呵嘿嘿哪呀垃圾","date":"2017-09-07"},{"title":"开心开心","date":"2017-09-06"},{"title":"吉拉拉","date":"2017-09-08"},{"title":"ghjkkhfxhj","date":"2017-09-09"},{"title":"今天吃药了","date":"2017-08-31"}]
         */
        
        private int days;
        private int recorddays;
        private List<RecordsEntity> records;
        
        public int getDays() {
            return days;
        }
        
        public void setDays(int days) {
            this.days = days;
        }
        
        public int getRecorddays() {
            return recorddays;
        }
        
        public void setRecorddays(int recorddays) {
            this.recorddays = recorddays;
        }
        
        public List<RecordsEntity> getRecords() {
            return records;
        }
        
        public void setRecords(List<RecordsEntity> records) {
            this.records = records;
        }
        
        public static class RecordsEntity {
            /**
             * title : aasksnzhs
             * date : 2017-09-12
             */
            
            private String title;
            private String date;
            
            public String getTitle() {
                return title;
            }
            
            public void setTitle(String title) {
                this.title = title;
            }
            
            public String getDate() {
                return date;
            }
            
            public void setDate(String date) {
                this.date = date;
            }
        }
    }
    
    public static class PrevMonthEntity {
        /**
         * days : 31
         * recorddays : 0
         * records : []
         */
        
        private int days;
        private int recorddays;
        private List<CurrentMonthEntity.RecordsEntity> records;
        
        public int getDays() {
            return days;
        }
        
        public void setDays(int days) {
            this.days = days;
        }
        
        public int getRecorddays() {
            return recorddays;
        }
        
        public void setRecorddays(int recorddays) {
            this.recorddays = recorddays;
        }
        
        public List<?> getRecords() {
            return records;
        }
        
        public void setRecords(List<CurrentMonthEntity.RecordsEntity> records) {
            this.records = records;
        }
    }
    
    public static class NextMonthEntity {
        /**
         * days : 31
         * recorddays : 0
         * records : []
         */
        
        private int days;
        private int recorddays;
        private List<CurrentMonthEntity.RecordsEntity> records;
        
        public int getDays() {
            return days;
        }
        
        public void setDays(int days) {
            this.days = days;
        }
        
        public int getRecorddays() {
            return recorddays;
        }
        
        public void setRecorddays(int recorddays) {
            this.recorddays = recorddays;
        }
        
        public List<?> getRecords() {
            return records;
        }
        
        public void setRecords(List<CurrentMonthEntity.RecordsEntity> records) {
            this.records = records;
        }
    }
}
