package com.example.sj.mylibrary.util;

import android.text.InputFilter;
import android.text.LoginFilter;
import android.widget.EditText;

/**
 * Created by Administrator on 2016/11/14.
 */
public class EdtJava  {
    public static  void makeEdtJava(EditText editText){
      
    
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new MyInputFilter();
        editText.setFilters(filters);
    }
    
    public static class MyInputFilter extends LoginFilter.UsernameFilterGeneric {
        private String mAllowedDigits="1234567890abcdefghijklmnopqrstuvwxyz_";
        
        
        @Override
        public boolean isAllowed(char c) {
            if (mAllowedDigits.indexOf(c) != -1) {
                return true;
            }
            return false;
        }
    }
}
