package com.android.ctmanager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);

//        Log.e("SplashActivity","token:"+token);
//        //  if (LoginUserManager.getInstance().currentUser() != null) {
//        if (!TextUtils.isEmpty(SPUtils.getInstance().getString(AppConst.APP_TOKEN))){
//            intent = new Intent(SplashActivity.this, MainActivity.class);
//        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startActivity(intent);
        finish();

    }



}