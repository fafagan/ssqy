package com.medicine.ssqy.ssqy.db;

import org.xutils.DbManager;

/**
 * Created by Administrator on 2016/12/9.
 */
public class CommonDaoConfig {
    private static DbManager.DaoConfig mDaoConfig;
    
    public static DbManager.DaoConfig getDaoConfig(){
        if (mDaoConfig==null){
            mDaoConfig=new DbManager.DaoConfig();
            mDaoConfig.setDbName("ssqy_12_9");
            mDaoConfig.setDbVersion(1);
            
        }
        
        return mDaoConfig;
    }
}
