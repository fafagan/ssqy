package com.medicine.ssqy.ssqy.util;

import android.content.Context;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by Amy on 2016/12/22.
 */
public class ShareUtil {
    
   public static void showShare(Context context,String textUrl) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("四时七养");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
//        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("四时七养中医养生："+textUrl);
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
//        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(textUrl);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("欢迎使用四时七养中医养生！");
        // site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(context);
    }
     
     public static void showShare(Context context) {
          OnekeyShare oks = new OnekeyShare();
          //关闭sso授权
          oks.disableSSOWhenAuthorize();
          // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
          oks.setTitle("四时七养");
          // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
          oks.setTitleUrl("http://sharesdk.cn");
          // text是分享文本，所有平台都需要这个字段
          oks.setText("您的好友分享了四时七养的课程，快来下载APP一起学习吧！！");
          //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
//          oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
          // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
          //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
          // url仅在微信（包括好友和朋友圈）中使用
          oks.setUrl("http://sharesdk.cn");
          // comment是我对这条分享的评论，仅在人人网和QQ空间使用
          oks.setComment("课程很精彩哦！");
          // site是分享此内容的网站名称，仅在QQ空间使用
          oks.setSite("ShareSDK");
          // siteUrl是分享此内容的网站地址，仅在QQ空间使用
          oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
          oks.show(context);
     }
}
