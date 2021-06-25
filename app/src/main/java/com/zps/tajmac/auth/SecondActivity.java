package com.zps.tajmac.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }


    @Override
    public void onBackPressed() {


        System.exit(1);
        //super.onCreate(null);
         super.onDestroy();
    }
}
