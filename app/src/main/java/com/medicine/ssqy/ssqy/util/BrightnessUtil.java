package com.medicine.ssqy.ssqy.util;

import android.app.Activity;
import android.provider.Settings;
import android.view.WindowManager;

/**
 * Created by Amy on 2016/12/12.
 */
public class BrightnessUtil {
    public static int getScreenMode(Activity activity) {
        int screenMode = 0;
        try {
            screenMode = Settings.System.getInt(activity.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE);
        } catch (Exception localException) {
            
        }
        return screenMode;
    }
    /**
     * 获得当前屏幕亮度值 0--255
     */
    public static float getScreenBrightness(Activity activity) {
        WindowManager.LayoutParams lpa = activity.getWindow().getAttributes();
        
        return lpa.screenBrightness;
    }
    /**
     * 设置当前屏幕亮度值
     */
    public static void setScreenBrightness(Activity activity,float screenBrightness) {
        WindowManager.LayoutParams lpa = activity.getWindow().getAttributes();
        lpa.screenBrightness = screenBrightness;
        if (lpa.screenBrightness > 1.0f)
            lpa.screenBrightness = 1.0f;
        else if (lpa.screenBrightness < 0.01f)
            lpa.screenBrightness = 0.01f;
        activity.getWindow().setAttributes(lpa);
        
    }
}
