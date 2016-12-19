package com.medicine.ssqy.json;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void go1(View view) {
        LoginEntity loginEntity=new LoginEntity();
        loginEntity.setState(true)
                .setIsformally(true)
                .setFisrtLogin(true)
                .setUid(10010)
                .setType("qq")
                .setErrno(101)
                .setErrorInfo("password error!")
                .setUseraccount("123456765")
                .setNickName("Lily")
                .setHeadPicUrl("http://xxxxxxxxx")
                .setSex("man")
                .setLevel(5);
        Gson gson=new Gson();
        System.out.println(gson.toJson(loginEntity));
                
    }
    
    public void go2(View view) {
        UserEntity loginEntity=new UserEntity();
        loginEntity.setState(true)
                .setUid(10010)
                .setErrno(101)
                .setErrorInfo("no data!")
                .setUseraccount("123456765")
                .setNickName("Lily")
                .setHeadPicUrl("http://xxxxxxxxx")
                .setSex("man")
                .setLevel(5)
                .setMarried(true)
                .setBirthDay("1966-5-5")
                .setRegTime("2016-10-10")
                .setJob("工人")
                .setStudylevel("博士")
                .setPhone("13112342234");
        Gson gson=new Gson();
        System.out.println(gson.toJson(loginEntity));
        
    }
    
    public void go3(View view) {
        
        
    }
    
}
