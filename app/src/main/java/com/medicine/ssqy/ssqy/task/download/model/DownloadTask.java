package com.medicine.ssqy.ssqy.task.download.model;


import com.medicine.ssqy.ssqy.task.common.model.Task;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2017/2/20.
 */
@Table(name = "DownloadTask")
public class DownloadTask extends Task {
    @Column(name="timeExecute")
    public long timeExecute;
    @Column(name = "url")
    public String url;
    
    @Column(name = "savaPath")
    public String savaPath;
    
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getSavaPath() {
        return savaPath;
    }
    
    public void setSavaPath(String savaPath) {
        this.savaPath = savaPath;
    }
}
