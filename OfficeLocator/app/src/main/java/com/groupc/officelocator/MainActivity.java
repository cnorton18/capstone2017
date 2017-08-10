package com.groupc.officelocator;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.graphics.*;

public class MainActivity extends AppCompatActivity {
private static int SPLASH_TIME_OUT = 1500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) {
            actionBar.hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* TextView tv = (TextView) findViewById(R.id.logoText);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/LeagueGothic-Regular.ttf");
        tv.setTypeface(tf);*/

        //Splash Screen delay
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent intent = new Intent(MainActivity.this, campus.class);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();
            }
        }, SPLASH_TIME_OUT);


    }












}
