package com.medicine.ssqy.ssqy.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-09-08.
 */
/*1  心养  ;
2  气养  ;
3  动养  ;
4  术养  ;
5  药养  ;
6  食养  ;
7  居养  ;

22	立春
23	雨水
24	惊蛰
1	春分
2	清明
3	谷雨
4	立夏
5	小满
6	芒种
7	夏至	
8	小暑	
9	大暑	
10	立秋	
11	处暑	
12	白露	
13	秋分	
14	寒露	
15	霜降	
16	立冬	
17	小雪	
18	大雪	
19	冬至	
20	小寒
21	大寒*/
public class Yang implements Serializable{
    
    public String title;
    public int index;
    public static List<Yang> sYangs=new ArrayList<>();
    static {
    
        sYangs.add(new Yang("心养",2));
        sYangs.add(new Yang("气养",3));
        sYangs.add(new Yang("动养",7));
        sYangs.add(new Yang("术养",4));
        sYangs.add(new Yang("药养",6));
        sYangs.add(new Yang("食养",5));
        sYangs.add(new Yang("居养",1));
        
    }
    public Yang(String title, int index) {
        this.title = title;
        this.index = index;
    }
    
    public Yang() {
        
    }
}
