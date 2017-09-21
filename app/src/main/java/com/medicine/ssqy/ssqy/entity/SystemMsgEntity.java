package com.medicine.ssqy.ssqy.entity;

/**
 * Created by Administrator on 2017-09-18.
 */

public class SystemMsgEntity {
    
    
    /**
     * title : 强筋健骨防摔倒 预防骨质疏松性骨折 系列宣教之三 骨质疏松的常见问题
     * url : http://106.3.41.199:8066/jeesite/app/zy/news?pushid=p201709111051150003
     * date : 1505098536000
     */
    
    private String title;
    private String url;
    private long date;
    /**
     * pushPic : http://106.3.41.199:8066/jeesite/resourceFiles/images/img2.png
     */
    
    private String pushPic;
    /**
     * courseLearned : false
     */
    
    private boolean courseLearned;
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public long getDate() {
        return date;
    }
    
    public void setDate(long date) {
        this.date = date;
    }
    
    public String getPushPic() {
        return pushPic;
    }
    
    public void setPushPic(String pushPic) {
        this.pushPic = pushPic;
    }
    
    public boolean isCourseLearned() {
        return courseLearned;
    }
    
    public void setCourseLearned(boolean courseLearned) {
        this.courseLearned = courseLearned;
    }
}
