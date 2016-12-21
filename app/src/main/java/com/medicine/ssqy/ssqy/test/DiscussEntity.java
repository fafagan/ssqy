package com.medicine.ssqy.ssqy.test;

import com.medicine.ssqy.ssqy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/13.
 */
public class DiscussEntity {
    
    private String nickName;
    private String time;
    private String detail;
    private int headUrl1=R.drawable.pic5;
    private String headUrl;
    
    public String getHeadUrl() {
        return headUrl;
    }
    
    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
    
    public static List<DiscussEntity>  getTempDatas(){
       List<DiscussEntity> discussEntities=new ArrayList<>();
       for (int i=1;i<10;i++){
           DiscussEntity discussEntity=new DiscussEntity();
           discussEntity.setNickName("养生爱好者 "+i);
           discussEntity.setTime("2016-12-22 15:40:20");
           StringBuilder stringBuilder=new StringBuilder();
           for (int i1 = 1; i1 <=i; i1++) {
               stringBuilder.append("非常棒的课程！非常喜欢! "+i1+"\n");
              
           }
           discussEntity.setDetail(stringBuilder.toString());
           discussEntity.setHeadUrl1(R.drawable.pic5);
           discussEntities.add(discussEntity);
       }
       return discussEntities;
   }
    public int getHeadUrl1() {
        return headUrl1;
    }
    
    public void setHeadUrl1(int headUrl1) {
        this.headUrl1 = headUrl1;
    }
    
    public String getNickName() {
        return nickName;
    }
    
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    
    public String getTime() {
        return time;
    }
    
    public void setTime(String time) {
        this.time = time;
    }
    
    public String getDetail() {
        return detail;
    }
    
    public void setDetail(String detail) {
        this.detail = detail;
    }
}
