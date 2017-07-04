package com.groupc.officelocator;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class splash extends AppCompatActivity {

    public Button splashb1;
    public Button splashb2;
    public Button splashb3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashb1 = (Button)findViewById(R.id.splashbutton1);
        splashb1.setOnClickListener(new View.OnClickListener(){

                                            public void onClick(View v){
                                                Intent theintent = new Intent(splash.this, campus.class);
                                                startActivity(theintent);
                                            }

                                        }

        );

        splashb2 = (Button)findViewById(R.id.splashbutton2);
        splashb2.setOnClickListener(new View.OnClickListener(){

                                            public void onClick(View v){
                                                Intent theintent = new Intent(splash.this, floorplan.class);
                                                startActivity(theintent);
                                            }

                                        }
        );

        splashb3 = (Button)findViewById(R.id.splashbutton3);
        splashb3.setOnClickListener(new View.OnClickListener(){

                                            public void onClick(View v){
                                                Intent theintent = new Intent(splash.this, directions.class);
                                                startActivity(theintent);
                                            }

                                        }
        );
    }

}
