package com.example.btlapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                nextAcitivy();
            }


        },2000);
    }
    private void nextAcitivy() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user ==null){
            // chưa login
            Intent intent = new Intent(this, com.example.btlapplication.SiginActivity.class);
            startActivity(intent);
        }
        else {
            // đã login
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        finish();
    }
}