package com.example.sj.mylibrary.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

public class FileUtil {
	/**
	 * 检验SDcard状态
	 * 
	 * @return boolean
	 */
	public static boolean checkSDCard() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	public static String getSDPath(Context context) {
		String sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory().toString();// 获取跟目录
		} else {
			// SD卡不存在返回 内存路径
			sdDir = context.getCacheDir().getAbsolutePath();
		}
		return sdDir;

	}

	/**
	 * 保存文件文件到目录
	 * 
	 * @param context
	 * @return 文件保存的目录
	 */
	public static String setMkdir(Context context) {
		String filePath;
		if (checkSDCard()) {
			filePath = Environment.getExternalStorageDirectory()
					+ File.separator + "DownloadImage";
		} else {
			filePath = context.getCacheDir().getAbsolutePath() + File.separator
					+ "DownloadImage";
		}
		File file = new File(filePath);
		if (!file.exists()) {
			boolean b = file.mkdirs();
			Log.e("file", "文件不存在  创建文件    " + b);
		} else {
			Log.e("file", "文件存在");
		}
		return filePath;
	}
	 public static void mkDirs(String url) {
	        File file = new File(url);
	        if (!file.exists())
	            file.mkdirs();
	    }
	 public static boolean isExist(String url) {
	        File file = new File(url);
	        return file.exists();
	    }
	 public static Object readFromFile(String url) {
	        if (isExist(url) == false)
	            return null;
	        
	        Object object = null;
	        FileInputStream fileStream = null;
	        ObjectInputStream objectStream = null;

	        try {
	            fileStream = new FileInputStream(url);
	            objectStream = new ObjectInputStream(fileStream);
	            object = objectStream.readObject();
	            return object;

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (StreamCorruptedException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } finally {
	            if (fileStream != null)
	                try {
	                    fileStream.close();
	                } catch (IOException e1) {
	                    e1.printStackTrace();
	                }

	            if (objectStream != null)
	                try {
	                    objectStream.close();
	                } catch (IOException e1) {
	                    e1.printStackTrace();
	                }
	        }
	        //ִ�е������Ѿ������л�ʧ���ˣ���ʱ����Ч���ļ�ɾ��
	        deleteFile(url);
	        return object;
	    }

	    public static boolean deleteFile(String url) {
	        File file = new File(url);
	        return file.delete();
	    }
	    public static boolean writeToFile(Object obj, String url) {
	        FileOutputStream fileStream = null;
	        ObjectOutputStream objectStream = null;

	        try {
	            File file = new File(url);

	            if (file.exists() == false)
	                file.createNewFile();

	            fileStream = new FileOutputStream(file);
	            objectStream = new ObjectOutputStream(fileStream);
	            objectStream.writeObject(obj);
	            objectStream.flush();
	            return true;
	        } catch (Exception e) {
	            return false;
	        } finally {
	            if (fileStream != null)
	                try {
	                    fileStream.close();
	                } catch (IOException e1) {
	                    e1.printStackTrace();
	                }

	            if (objectStream != null)
	                try {
	                    objectStream.close();
	                } catch (IOException e1) {
	                    e1.printStackTrace();
	                }
	        }
	    }
}