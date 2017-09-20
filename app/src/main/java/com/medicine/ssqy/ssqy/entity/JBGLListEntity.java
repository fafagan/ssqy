package com.medicine.ssqy.ssqy.entity;

import java.util.List;

/**
 * Created by Administrator on 2017-09-19.
 */

public class JBGLListEntity {
    
    /**
     * uid : u002
     * state : true
     * dataCount : 14
     * recordHistory : [{"recordID":"fdd171dfb55e4a07b2927ab8751755f5","recordType":"0","recordData":"123","recordDay":null,"pressureMin":null,"pressureMax":null},{"recordID":"c352396e327e4c788a747fb66475dfae","recordType":"0","recordData":"123","recordDay":null,"pressureMin":null,"pressureMax":null},{"recordID":"5126d5d1023c4fa6bd4db2ac2401fdd9","recordType":"0","recordData":"123","recordDay":null,"pressureMin":null,"pressureMax":null},{"recordID":"8d5475316c63499ca17b134b6b5015ac","recordType":"0","recordData":"12","recordDay":null,"pressureMin":null,"pressureMax":null},{"recordID":"8758afeb2a7543a48b86d5589f4d7668","recordType":"0","recordData":"55","recordDay":null,"pressureMin":null,"pressureMax":null},{"recordID":"43241b64816b497db5600a002e4c21a6","recordType":"0","recordData":"","recordDay":null,"pressureMin":null,"pressureMax":null},{"recordID":"4b596ef0c2794789bde45930f44d4e79","recordType":"0","recordData":"","recordDay":null,"pressureMin":null,"pressureMax":null},{"recordID":"34305686709842739bf00801d0ab142b","recordType":"0","recordData":"","recordDay":null,"pressureMin":null,"pressureMax":null},{"recordID":"e619d9deb5184015943fdc190fc2dac6","recordType":"0","recordData":"522","recordDay":null,"pressureMin":null,"pressureMax":null},{"recordID":"892edae96b3f4638bf712af97d835801","recordType":"0","recordData":"522","recordDay":null,"pressureMin":null,"pressureMax":null},{"recordID":"47a111f967f644f3a83ac7558cfb70df","recordType":"0","recordData":"22","recordDay":null,"pressureMin":null,"pressureMax":null},{"recordID":"779d500faad442ce9742b459eabd7652","recordType":"0","recordData":"335","recordDay":null,"pressureMin":null,"pressureMax":null},{"recordID":"90d7e8122e224cdfbb44986abf195802","recordType":"0","recordData":"225","recordDay":null,"pressureMin":null,"pressureMax":null},{"recordID":"d9b64b828f9744318b62ac4dcb20dfb5","recordType":"0","recordData":"u002","recordDay":null,"pressureMin":"99","pressureMax":"170"}]
     */
    
    private String uid;
    private boolean state;
    private int dataCount;
    private List<RecordHistoryEntity> recordHistory;
    
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
    
    public int getDataCount() {
        return dataCount;
    }
    
    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }
    
    public List<RecordHistoryEntity> getRecordHistory() {
        return recordHistory;
    }
    
    public void setRecordHistory(List<RecordHistoryEntity> recordHistory) {
        this.recordHistory = recordHistory;
    }
    
    public static class RecordHistoryEntity {
        /**
         * recordID : fdd171dfb55e4a07b2927ab8751755f5
         * recordType : 0
         * recordData : 123
         * recordDay : null
         * pressureMin : null
         * pressureMax : null
         */
        
        private String recordID;
        private String recordType;
        private String recordData;
        private String recordDay;
        private String pressureMin;
        private String pressureMax;
        
        public String getRecordID() {
            return recordID;
        }
        
        public void setRecordID(String recordID) {
            this.recordID = recordID;
        }
        
        public String getRecordType() {
            return recordType;
        }
        
        public void setRecordType(String recordType) {
            this.recordType = recordType;
        }
        
        public String getRecordData() {
            return recordData;
        }
        
        public void setRecordData(String recordData) {
            this.recordData = recordData;
        }
        
        public String getRecordDay() {
            return recordDay;
        }
        
        public void setRecordDay(String recordDay) {
            this.recordDay = recordDay;
        }
        
        public String getPressureMin() {
            return pressureMin;
        }
        
        public void setPressureMin(String pressureMin) {
            this.pressureMin = pressureMin;
        }
        
        public String getPressureMax() {
            return pressureMax;
        }
        
        public void setPressureMax(String pressureMax) {
            this.pressureMax = pressureMax;
        }
    }
}
