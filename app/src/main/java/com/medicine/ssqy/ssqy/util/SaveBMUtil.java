package com.medicine.ssqy.ssqy.util;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by SJ on 2015/12/20.
 */
public class SaveBMUtil {

    public static  File saveMyBitmap(Bitmap mBitmap, String bitName)  {

        String sdPath= Environment.getExternalStorageDirectory().getAbsolutePath();
        File f = new File(sdPath+ "/"+bitName + ".jpg");
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        
        int bl=100;
        while (true){
            mBitmap.compress(Bitmap.CompressFormat.JPEG, bl, byteArrayOutputStream);
            if (byteArrayOutputStream.size()<150*1024) {
                break;
            }
            if (bl<=20){
                break;
            }
            bl-=5;
            byteArrayOutputStream.reset();
        }
        
        //把BitMap保存到文件中
        mBitmap.compress(Bitmap.CompressFormat.JPEG, bl, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  f;
    }
}
