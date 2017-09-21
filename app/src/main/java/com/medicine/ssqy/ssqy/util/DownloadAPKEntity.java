package com.medicine.ssqy.ssqy.util;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2017-09-21.
 */
@Table(name = "DownloadAPKEntity")
public class DownloadAPKEntity {
    @Column(name="id",isId = true)
    private int id;
    @Column(name="path")
    private String path;
    
    @Column(name="name")
    private String name;
    
    @Column(name="versioncode")
    private int versioncode;
    
    
    @Column(name="sha1")
    private String sha1;
    
    @Column(name="url")
    private String url;
    
    @Column(name="state")
    private int state=STATE_UNDONE;
    
    @Column(name="totalSize")
    private int totalSize;
    
    
    public static final int STATE_DONE=110;
    public static final int STATE_UNDONE=120;
    
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public int getTotalSize() {
        return totalSize;
    }
    
    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getVersioncode() {
        return versioncode;
    }
    
    public void setVersioncode(int versioncode) {
        this.versioncode = versioncode;
    }
    
    public String getSha1() {
        return sha1;
    }
    
    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }
    
    public int getState() {
        return state;
    }
    
    public void setState(int state) {
        this.state = state;
    }
}
