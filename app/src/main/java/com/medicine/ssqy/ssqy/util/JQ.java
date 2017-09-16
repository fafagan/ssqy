package com.medicine.ssqy.ssqy.util;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-09-08.
 */

public class JQ implements Serializable {
    
    public int index;
    public String title;
    
    public JQ() {
    }
    
    public JQ(String title, int index) {
        this.title = title;
        this.index = index;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        JQ jq = (JQ) o;
    
        return title != null ? title.equals(jq.title) : jq.title == null;
    
    }
    
    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }
}
