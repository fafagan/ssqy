package com.medicine.ssqy.ssqy.db;

import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.entity.UserEntity;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

/**
 * Created by Administrator on 2016/12/9.
 */
public class TempUser {
    
    public static void saveOrUpdateUserFirst(String sex,boolean isMarried,String birthDay,String job,String study){
        UserEntity nowUser = getNowUser(SharePLogin.getUid());
        nowUser.setSex(sex);
        nowUser.setMarried(isMarried);
        nowUser.setBirthDay(birthDay);
        nowUser.setJob(job);
        nowUser.setStudylevel(study);
        saveOrUpdateUser(nowUser);
    }
    
    
    public static void saveOrUpdateUser(UserEntity userEntity) {
        DbManager dbManager = x.getDb(CommonDaoConfig.getDaoConfig());
        
        try {
            UserEntity entity = dbManager.selector(UserEntity.class).where("uid", "=", userEntity.getUid()).findFirst();
            if (entity != null) {
                System.out.println("11111111");
                dbManager.
                        update(userEntity, "birthDay", "headPicUrl", "job",
                                "nickName", "phone", "regTime", "sex", "studylevel",
                                "useraccount", "isMarried", "level", "state", "uid");
            } else {
    
                System.out.println("22222222");
                dbManager.saveBindingId(userEntity);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    
    public static UserEntity getNowUser(int uid) {
        DbManager dbManager = x.getDb(CommonDaoConfig.getDaoConfig());
        try {
            UserEntity entity = dbManager.selector(UserEntity.class).where("uid", "=", uid).findFirst();
            
            return entity;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }
}
