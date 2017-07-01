package com.groupc.officelocator;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by zachary on 6/26/17.
 */

public class about extends AppCompatActivity {

    public TextView textView4;
    String vNum = null;

    /*
    * retrieveVNum
    * Sets the version number to a String and returns that String.
    * If the version number cannot be found, String set to ERROR message
    * */
    public String retrieveVNum(){
        PackageManager mngr = this.getPackageManager();
        try {
            Log.d("","problem");
            PackageInfo info = mngr.getPackageInfo(getPackageName(), 0);
            Log.d("","here");
            vNum = "Version: " + info.versionName;
        }catch(PackageManager.NameNotFoundException e){
            vNum = "ERROR: Version Number Not Found";
        }
        return vNum;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        Log.d("","why");
        super.onCreate(savedInstanceState);
        Log.d("","sigh");
        setContentView(R.layout.about);
        Log.d("","whyyyy");
        textView4 = (TextView)findViewById(R.id.textView4);
        textView4.setText(retrieveVNum());//Sets version number to textfield

    }

}
