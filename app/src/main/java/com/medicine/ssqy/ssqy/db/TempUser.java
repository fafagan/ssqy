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
        nowUser.setIsMarried(isMarried+"");
        nowUser.setBirthDay(birthDay);
        nowUser.setJob(job);
        nowUser.setStudyLevel(study);
        saveOrUpdateUser(nowUser);
    }
    
    public static void saveOrUpdatePhone(String phone) {
        DbManager dbManager = x.getDb(CommonDaoConfig.getDaoConfig());
        
        try {
            UserEntity entity = dbManager.selector(UserEntity.class).where("uid", "=", SharePLogin.getUid()).findFirst();
            entity.setPhone(phone);
            if (entity != null) {
                dbManager.
                        update(entity, "phone");
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    } 
    public static void saveOrUpdateUser(UserEntity userEntity) {
        DbManager dbManager = x.getDb(CommonDaoConfig.getDaoConfig());
        try {
            UserEntity entity = dbManager.selector(UserEntity.class).where("uid", "=", userEntity.getUid()).findFirst();
            if (entity != null) {
                System.out.println(entity);
                dbManager.
                        update(userEntity, "birthDay", "headPicUrl", "job",
                                "nickName", "phone", "regTime", "sex", "studylevel",
                                "useraccount", "isMarried", "level", "state", "uid");
            } else {
                dbManager.saveBindingId(userEntity);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    
    public static UserEntity getNowUser(String uid) {
        DbManager dbManager = x.getDb(CommonDaoConfig.getDaoConfig());
        try {
            UserEntity entity = dbManager.selector(UserEntity.class).where("uid", "=", uid).findFirst();
            
            return entity;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static  void reset(){
        DbManager dbManager = x.getDb(CommonDaoConfig.getDaoConfig());
        try {
            dbManager.delete(UserEntity.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
