package com.medicine.ssqy.ssqy.ui.views.calendar;

/**
 * @author nanchen
 * @date 16-8-10 上午11:38
 */
public class NumberHelper {
    public static String LeftPad_Tow_Zero(int str) {
        java.text.DecimalFormat format = new java.text.DecimalFormat("00");
        return format.format(str);
    }
}