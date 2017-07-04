package com.groupc.officelocator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
//import android.widget.CheckBox;


public class campus extends AppCompatActivity {




    public Button campusb2;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus);






        campusb2 = (Button)findViewById(R.id.campusbutton2);

        campusb2.setOnClickListener(new View.OnClickListener(){

                                            public void onClick(View v){
                                                Intent theintent = new Intent(campus.this, floorplan.class);

                                                startActivity(theintent);
                                            }

                                        }

        );





    }











}