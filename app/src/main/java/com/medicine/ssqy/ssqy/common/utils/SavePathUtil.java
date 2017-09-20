package com.medicine.ssqy.ssqy.common.utils;

import android.os.Environment;

import com.medicine.ssqy.ssqy.base.KBaseApp;

import java.io.File;

/**
 * Created by Administrator on 2017/2/20.
 */

public class SavePathUtil {
    
    public static String getDirPath(){
        String packageName= KBaseApp.mContextGlobal.getPackageName();
        String dirPath= Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+packageName+"/p1/gsfsd/ce/";
        
        File file=new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return dirPath;
    }
}
