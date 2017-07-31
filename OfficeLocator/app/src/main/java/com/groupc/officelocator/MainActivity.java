package com.groupc.officelocator;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
private static int SPLASH_TIME_OUT = 1500;
<<<<<<< HEAD

=======
>>>>>>> master


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
<<<<<<< HEAD

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

=======
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Splash Screen delay
>>>>>>> master
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent intent = new Intent(MainActivity.this, campus.class);
<<<<<<< HEAD

=======
>>>>>>> master
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();
            }
        }, SPLASH_TIME_OUT);


    }












}
