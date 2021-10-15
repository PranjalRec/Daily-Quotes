package com.pranjal.myapplicatio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class splashActivity extends AppCompatActivity {


        private static final int SPLASH_SCREEN_TIME_OUT = 1000;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.activity_splash);

            new Handler().postDelayed(() -> {
                Intent i=new Intent(splashActivity.this, MainActivity.class);

                startActivity(i);

                finish();
            }, SPLASH_SCREEN_TIME_OUT);
        }
}