package com.example.sj.mylibrary.util;

/**
 * Created by wuhai on 2015/11/16.
 * 签名工具类
 * 相同的地方以“//XXXXX(接口中文名)”追加注释
 */
public class SignUtils {

    //获取话题回复详情
    private static final String signKey1="KSSEG3L3+r1I%WkAuo#=";
    public static String generateSign1(String data) {
        String signData =signKey1+data;
        return MD5.MD5(signData).substring(2, 17);
    }

    //获取评论列表
    private static final String signKey2="uZ*Rib0TI+";
    public static String generateSign2(String data) {
        String signData =signKey2+data;
        return StringUtil.reverse(MD5.MD5(signData));
    }

    //用户评论
    private static final String signKey3="o.aDXqa^j_";
    public static String generateSign3(String data) {
        String signData =signKey3+data;
        return MD5.MD5(signData);
    }
}
