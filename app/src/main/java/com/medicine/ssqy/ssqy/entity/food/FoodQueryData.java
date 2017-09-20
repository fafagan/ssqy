package com.medicine.ssqy.ssqy.entity.food;

/**
 * Created by Administrator on 2017-09-21.
 */

public class FoodQueryData {
    private String picUrl;
    private String title;
    private String data;
    
    public FoodQueryData(String picUrl, String title, String data) {
        this.picUrl = picUrl;
        this.title = title;
        this.data = data;
    }
    
    public FoodQueryData() {
    }
    
    public String getPicUrl() {
        return picUrl;
    }
    
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getData() {
        return data;
    }
    
    public void setData(String data) {
        this.data = data;
    }
}
