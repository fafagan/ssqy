package com.medicine.ssqy.ssqy.task.download.model;

import org.xutils.DbManager;

/**
 * Created by Administrator on 2017/2/20.
 */

public class DownloadDaoConfig {
    
    
    private static DbManager.DaoConfig mDaoConfig;
    
    public static DbManager.DaoConfig getDownloadDaoConfig() {
        
        //优化效率
        if(mDaoConfig==null){
            //处理了线程安全问题
            synchronized (DownloadDaoConfig.class) {
                if (mDaoConfig == null) {
                    mDaoConfig = new DbManager.DaoConfig();
                    mDaoConfig.setDbName("downloadDB");
                    mDaoConfig.setDbVersion(1);
//                    mDaoConfig.setDbUpgradeListener(new DbManager.DbUpgradeListener() {
//                        @Override
//                        public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
//        
//                        }
//                    })
                }
            }
        }
        
        return mDaoConfig;
    }
}
