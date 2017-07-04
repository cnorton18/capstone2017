package com.groupc.officelocator;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
//import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;


public class floorplan extends AppCompatActivity {


    public Button floorplanb1;

    public Button floorplanb2;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floorplan);






        floorplanb1 = (Button)findViewById(R.id.floorplanbutton1);

        floorplanb1.setOnClickListener(new View.OnClickListener(){

                                        public void onClick(View v){
                                            Intent theintent = new Intent(floorplan.this, campus.class);

                                            startActivity(theintent);
                                        }

                                    }

        );




        floorplanb2 = (Button)findViewById(R.id.floorplanbutton2);

        floorplanb2.setOnClickListener(new View.OnClickListener(){

                                           public void onClick(View v){
                                               Intent theintent = new Intent(floorplan.this, directions.class);

                                               startActivity(theintent);
                                           }

                                       }

        );




    }










}