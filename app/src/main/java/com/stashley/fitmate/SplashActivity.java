package com.stashley.fitmate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        start MainActivity
        startActivity(new Intent(SplashActivity.this, IntroActivity.class));

//        close SplashActivity
        finish();
    }
}