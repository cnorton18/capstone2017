package com.groupc.officelocator;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class splash extends AppCompatActivity {

    public Button locate;
    public Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        locate = (Button)findViewById(R.id.locate);
        locate.setOnClickListener(new View.OnClickListener(){

                                            public void onClick(View v){
                                                Intent theintent = new Intent(splash.this, campus.class);
                                                startActivity(theintent);
                                            }

                                        }
        );

        search = (Button)findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener(){

                                            public void onClick(View v){
                                                Intent theintent = new Intent(splash.this,masterSearchWithHeaders.class);
                                                startActivity(theintent);
                                            }

                                        }

        );

    }

}
