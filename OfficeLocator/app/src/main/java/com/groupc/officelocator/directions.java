package com.groupc.officelocator;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
//import android.widget.CheckBox;


public class directions extends AppCompatActivity {

    public Button directionsb1;
    public Button directionsb2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions);


        directionsb1 = (Button) findViewById(R.id.directionsbutton1);
        directionsb1.setOnClickListener(new View.OnClickListener() {

                                            public void onClick(View v) {
                                                Intent theintent = new Intent(directions.this, floorplan.class);
                                                startActivity(theintent);
                                            }

                                        }
        );


        directionsb2 = (Button) findViewById(R.id.directionsbutton2);
        directionsb2.setOnClickListener(new View.OnClickListener() {

                                            public void onClick(View v) {
                                                exitit();
                                            }

                                        }
        );
    }
    private void exitit() {
        ActivityCompat.finishAffinity(this);
    }

}