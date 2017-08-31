package com.medicine.ssqy.ssqy.util;

import com.medicine.ssqy.ssqy.R;

/**
 * Created by Administrator on 2017-08-31.
 */

public class UtilGetJQPic {
    
    public static int getLogoRes(){
        String nowJQ = _24SolarTerms.getNowJQ();
        if (nowJQ==null) {
            return R.drawable.title_index;
        }
        switch (nowJQ){
            case SolarName.LICHUN:
                return R.drawable.lichun;
            case SolarName.YUSHUI:
                return R.drawable.yushui;
            case SolarName.JINGZHE:
                return R.drawable.jingzhe;
            case SolarName.CHUNFEN:
                return R.drawable.chunfen;
            case SolarName.QINGMING:
                return R.drawable.qingm;
            case SolarName.GUYU:
                return R.drawable.guyu;
            case SolarName.LIXIA:
                return R.drawable.lixia;
            case SolarName.XIAOMAN:
                return R.drawable.xiaoman;
            case SolarName.MANGZHONG:
                return R.drawable.mangzhong;
            case SolarName.XIAZHI:
                return R.drawable.xiazhi;
            case SolarName.XIAOSHU:
                return R.drawable.xiaoshu;
            case SolarName.DASHU:
                return R.drawable.dashu;
            case SolarName.LIQIU:
                return R.drawable.liqiu;
            case SolarName.CHUSHU:
                return R.drawable.chushu;
            case SolarName.BAILU:
                return R.drawable.bailu;
            case SolarName.QIUFEN:
                return R.drawable.qiufen;
            case SolarName.HANLU:
                return R.drawable.hanlu;
            case SolarName.SHUANGJIANG:
                return R.drawable.shuangjiang;
            case SolarName.LIDONG:
                return R.drawable.lidong;
            case SolarName.XIAOXUE:
                return R.drawable.xiaoxue;
            case SolarName.DAXUE:
                return R.drawable.daxue;
            case SolarName.DONGZHI:
                return R.drawable.dongzhi;
            case SolarName.XIAOHAN:
                return R.drawable.xiaohan;
            case SolarName.DAHAN:
                return R.drawable.dahan;
        }
        
        return R.drawable.title_index;
    }
    
    public static int getCircleRes(){
        String nowJQ = _24SolarTerms.getNowJQ();
        if (nowJQ==null) {
            return R.drawable.dahanimg;
        }
        switch (nowJQ){
            case SolarName.LICHUN:
                return R.drawable.lichunimg;
            case SolarName.YUSHUI:
                return R.drawable.yushuiimg;
            case SolarName.JINGZHE:
                return R.drawable.jingzheimg;
            case SolarName.CHUNFEN:
                return R.drawable.chunfenimg;
            case SolarName.QINGMING:
                return R.drawable.qingmingimg;
            case SolarName.GUYU:
                return R.drawable.guyuimg;
            case SolarName.LIXIA:
                return R.drawable.lixiaimg;
            case SolarName.XIAOMAN:
                return R.drawable.xiaomanimg;
            case SolarName.MANGZHONG:
                return R.drawable.mangzhongimg;
            case SolarName.XIAZHI:
                return R.drawable.xiazhiimg;
            case SolarName.XIAOSHU:
                return R.drawable.xiaoshuimg;
            case SolarName.DASHU:
                return R.drawable.dashuimg;
            case SolarName.LIQIU:
                return R.drawable.liqiuimg;
            case SolarName.CHUSHU:
                return R.drawable.chushuimg;
            case SolarName.BAILU:
                return R.drawable.bailuimg;
            case SolarName.QIUFEN:
                return R.drawable.qiufenimg;
            case SolarName.HANLU:
                return R.drawable.hanluimg;
            case SolarName.SHUANGJIANG:
                return R.drawable.shuangjiangimg;
            case SolarName.LIDONG:
                return R.drawable.lidongimg;
            case SolarName.XIAOXUE:
                return R.drawable.xiaoxueimg;
            case SolarName.DAXUE:
                return R.drawable.daxueimg;
            case SolarName.DONGZHI:
                return R.drawable.dongzhiimg;
            case SolarName.XIAOHAN:
                return R.drawable.xiaohanimg;
            case SolarName.DAHAN:
                return R.drawable.dahanimg;
        }
        
        return R.drawable.dahanimg;
    }
}
