package com.example.sj.mylibrary.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOpreateUtils {

	public static Bitmap decodeFile(File f) {
		try {
			// Decode image size
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(new FileInputStream(f), null, o);

			// The new size we want to scale to
			final int REQUIRED_SIZE = 300;

			// Find the correct scale value. It should be the power of 2.
			int scale = 1;
			while (o.outWidth / scale / 2 >= REQUIRED_SIZE
					&& o.outHeight / scale / 2 >= REQUIRED_SIZE)
				scale *= 2;

			// Decode with inSampleSize
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
		} catch (FileNotFoundException e) {

		}
		return null;
	}

	/**
	 * 
	 * @param fromFile
	 *            被复制的文件
	 * @param toFile
	 *            复制的目录文件
	 * @param rewrite
	 *            是否重新创建文件
	 * 
	 *            <p>
	 *            文件的复制操作方法
	 */
	public static void copyfile(File fromFile, File toFile, Boolean rewrite) {
		if (!fromFile.exists()) {
			return;
		}

		if (!fromFile.isFile()) {
			return;
		}
		if (!fromFile.canRead()) {
			return;
		}
		Bitmap bmp = null;
		bmp = decodeFile(fromFile);
		bmp = dealExifInterface(fromFile, bmp);

		if (!toFile.getParentFile().exists()) {
			toFile.getParentFile().mkdirs();
		}
		if (toFile.exists() && rewrite) {
			toFile.delete();
		}
		FileOutputStream bmpFile = null;
		try {
			bmpFile = new FileOutputStream(toFile);
			bmp.compress(Bitmap.CompressFormat.JPEG, 100, bmpFile);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bmpFile.flush();
				bmpFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static Bitmap dealExifInterface(File fromFile, Bitmap bmp) {
		int degree = 0;
		ExifInterface exif;
		try {
			exif = new ExifInterface(fromFile.getAbsolutePath());
			int orc = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);

			if (orc == ExifInterface.ORIENTATION_ROTATE_90) {
				degree = 90;
			} else if (orc == ExifInterface.ORIENTATION_ROTATE_180) {
				degree = 180;
			} else if (orc == ExifInterface.ORIENTATION_ROTATE_270) {
				degree = 270;
			}
			if (degree != 0) {
				Matrix matrix = new Matrix();
				matrix.postRotate(degree);
				bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(),
						bmp.getHeight(), matrix, true);
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // Since API Level 5
		return bmp;
	}
}