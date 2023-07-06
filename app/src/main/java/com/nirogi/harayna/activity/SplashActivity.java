package com.nirogi.harayna.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.nirogi.harayna.R;
import com.nirogi.harayna.utils.BaseActivity;
import com.nirogi.harayna.utils.Nirogi;
import com.nirogi.harayna.utils.SharedParams;


public class SplashActivity extends BaseActivity {

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = Nirogi.getInstance().getPreferences();
        setContentView(R.layout.activity_splash_screen);

        int SPLASH_TIME_OUT = 3000;
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if(TextUtils.isEmpty(preferences.getString(SharedParams.USERNAME,"")))
                {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }else
                {
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                }

            }
        }, SPLASH_TIME_OUT);


    }

}
