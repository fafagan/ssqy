package com.medicine.ssqy.ssqy.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.medicine.ssqy.ssqy.R;

import java.io.File;

/**
 * Created by Amy on 2016/12/16.
 */
public class DigPhotoChoose extends Dialog implements View.OnClickListener {
    //拍照
    public static final int REQUEST_CODE_TAKE_PHOTO = 10;
    //截取
    public static final int REQUEST_CODE_CLIP_PHOTO = 20;
    //相册
    public static final int REQUEST_CODE_GALLARY_CROP = 120;
    public File mOutputFile;
    private Button mBtnCamera;
    private Button mBtnAlbum;
    private Activity mContext;
    public DigPhotoChoose(Context context) {
        super(context);
        mContext= (Activity) context;
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setGravity(Gravity.CENTER);
        this.setContentView(R.layout.dig_choose);
        initViews();
        initDatas();
    }
    
    private void initViews() {
    
    
    }
    
    private void initDatas() {
    
    
        mBtnCamera = (Button) findViewById(R.id.btn_camera);
        mBtnAlbum = (Button) findViewById(R.id.btn_album);
        mBtnCamera.setOnClickListener(this);
        mBtnAlbum.setOnClickListener(this);
    
    }
    public void showSelf() {
        this.getWindow().setWindowAnimations(R.style.diganim);
        this.show();
    }
    @Override
    public void onClick(View v) {
    
        if (this.isShowing()) {
            this.cancel();
        }
        switch (v.getId()) {
            case R.id.btn_camera:
                takePhoto();
                break;
            case R.id.btn_album:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                mContext.startActivityForResult(intent, REQUEST_CODE_GALLARY_CROP);
                break;
        
        }
    }
    
    //拍照方法
    private void takePhoto() {
        if (!hasCarema()){
            return;
        }
        //生成一个文件，存储我们将来拍照的照片
        String sdPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath();
        mOutputFile = new File(sdPath, System.currentTimeMillis() + ".tmp");
        Uri uri = Uri.fromFile(mOutputFile);
        
        //跳转到我们系统的相机界面
        //自己写相机界面   Camera +SurfaceView +SurfaceHolder
        Intent newIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //最终把我们拍摄的相片，输出到uri指向
        newIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        mContext.startActivityForResult(newIntent, REQUEST_CODE_TAKE_PHOTO);
        
    }
    
    
    //判断是否有支持相机
    private boolean hasCarema() {
        PackageManager pm =mContext. getPackageManager();
        if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)
                && !pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)) {
            Toast.makeText(mContext, "打开相机失败！", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
