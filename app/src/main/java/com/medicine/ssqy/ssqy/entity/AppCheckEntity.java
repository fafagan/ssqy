package com.medicine.ssqy.ssqy.entity;

/**
 * Created by Administrator on 2017-09-21.
 */

public class AppCheckEntity {
    
    /**
     * needUpdate : true
     * newVersionCode : 10001
     * newVersionName : 1.1
     * appurl : http://106.3.41.199:8066/jeesite/app/zy/files?type=app&filename=ssqy1.1.apk
     */
    
    private boolean needUpdate;
    private String newVersionCode;
    private String newVersionName;
    private String appurl;
    /**
     * sha1 : 5B9AD0A62A7C404A8A76BA9446EF63CDF56A203F
     * totalSize : 29554601
     * filename : ssqy2.0.apk
     */
    
    private String sha1;
    private int totalSize;
    private String filename;
    
    public boolean isNeedUpdate() {
        return needUpdate;
    }
    
    public void setNeedUpdate(boolean needUpdate) {
        this.needUpdate = needUpdate;
    }
    
    public String getNewVersionCode() {
        return newVersionCode;
    }
    
    public void setNewVersionCode(String newVersionCode) {
        this.newVersionCode = newVersionCode;
    }
    
    public String getNewVersionName() {
        return newVersionName;
    }
    
    public void setNewVersionName(String newVersionName) {
        this.newVersionName = newVersionName;
    }
    
    public String getAppurl() {
        return appurl;
    }
    
    public void setAppurl(String appurl) {
        this.appurl = appurl;
    }
    
    public String getSha1() {
        return sha1;
    }
    
    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }
    
    public int getTotalSize() {
        return totalSize;
    }
    
    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }
    
    public String getFilename() {
        return filename;
    }
    
    public void setFilename(String filename) {
        this.filename = filename;
    }
}
