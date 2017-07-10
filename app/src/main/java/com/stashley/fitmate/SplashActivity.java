package com.stashley.fitmate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        start MainActivity
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));

//        close SplashActivity
        finish();
    }
}
