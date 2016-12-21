package com.medicine.ssqy.ssqy.ui.listener;

import android.view.View;
import android.widget.Toast;

import com.example.sj.mylibrary.util.AvoidDouble;
import com.medicine.ssqy.ssqy.R;
import com.medicine.ssqy.ssqy.common.utils.sp.SharePLogin;
import com.medicine.ssqy.ssqy.db.TempUser;
import com.medicine.ssqy.ssqy.entity.LoginEntity;
import com.medicine.ssqy.ssqy.entity.UserEntity;
import com.medicine.ssqy.ssqy.ui.activity.HomeActivity;
import com.medicine.ssqy.ssqy.ui.activity.LoginActivity;
import com.medicine.ssqy.ssqy.ui.pop.LoadingPopWindow;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/11/14.
 */
public class LoginPlatClick extends BaseOnClick<LoginActivity> {
    private LoadingPopWindow mPopWindow;
    private UMAuthListener umDetailListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(mParent, "授权成功！", Toast.LENGTH_SHORT).show();
            mPopWindow.dismiss();
            mParent.goToActivity(HomeActivity.class);
            Set<String> strings = data.keySet();
            for (String string : strings) {
                System.out.println(data.get(string));
            }
            LoginEntity loginEntity=new LoginEntity();
            loginEntity.setFisrtLogin(true);
            loginEntity.setState(true);
            loginEntity.setIsformally(false);
            loginEntity.setUid(123456);
            loginEntity.setType("QQ");
            loginEntity.setUseraccount("123123");
            loginEntity.setNickName(data.get("screen_name"));
            loginEntity.setHeadPicUrl(data.get("profile_image_url"));
            loginEntity.setSex(data.get("gender"));
            loginEntity.setLevel(2);
            loginEntity.setFisrtLogin(true);
    
            SharePLogin.saveIsFree(true);
            SharePLogin.saveUsername(loginEntity.getUseraccount());
            SharePLogin.saveUid(loginEntity.getUid());
    
            UserEntity userEntity=new UserEntity();
            userEntity.setState(loginEntity.isState());
            userEntity.setUid(loginEntity.getUid());
            userEntity.setUseraccount(loginEntity.getUseraccount());
            userEntity.setNickName(loginEntity.getNickName());
            userEntity.setHeadPicUrl(loginEntity.getHeadPicUrl());
            userEntity.setSex(loginEntity.getSex());
            userEntity.setLevel(loginEntity.getLevel());
            userEntity.setFirstLogin(loginEntity.isFisrtLogin());
            TempUser.saveOrUpdateUser(userEntity);
            mParent.finish();
      
        }
        
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(mParent, "授权失败，请稍后重试", Toast.LENGTH_SHORT).show();
            mPopWindow.dismiss();
        }
        
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(mParent, "授权取消", Toast.LENGTH_SHORT).show();
            mPopWindow.dismiss();
        }
    };
    public LoginPlatClick(LoginActivity parent) {
        super(parent);
        mPopWindow=new LoadingPopWindow(mParent);
    }
    
    @Override
    public void onClick(View v) {
        AvoidDouble.avoid(v,this);
        mPopWindow.show();
        switch (v.getId()) {
            case R.id.layout_login_wx:
                Toast.makeText(mContext, "功能正在来临，敬请期待！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.layout_login_qq:
                mParent.mShareAPI.getPlatformInfo(mParent, SHARE_MEDIA.QQ, umDetailListener);
                break;
            case R.id.layout_login_wb:
                Toast.makeText(mContext, "功能正在来临，敬请期待！", Toast.LENGTH_SHORT).show();
               // mParent.mShareAPI.getPlatformInfo(mParent, SHARE_MEDIA.SINA, umDetailListener);
                break;
        }
     
    }
}
