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
<<<<<<< HEAD

                                        }
        );

        search = (Button)findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener(){

                                            public void onClick(View v){
                                                Intent theintent = new Intent(splash.this, buildingsearch.class);
                                                startActivity(theintent);
                                            }
=======

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





/*
LIST OF STYLE ASSETS TO EXTRACT:

*APP NAME

*LAUNCH ICON

*SPLASH SCREEN
    background color
    splash image

*BG IMAGE FOR EVERY PAGE:
    color
    bar at top
    logo

*CAMPUS PAGE:
    campus map pic
    name
    building button locations
    search data

*SEARCH PAGE:
    all of the items in the search would need to change, link to appropriate places
    colors (text boxes, text)
    search text colors (text box, text)

*FLOORPLAN PAGE:
    floorplan pictures (edited, linked to dropdowns)
    pin locations
    2 dropdown lists' items (floor #, room)
    2 dropdown lists' colors (background, text)
    building locations (pictures change for every building, linked up)
*/

>>>>>>> master


<<<<<<< HEAD
    }
=======


>>>>>>> master

}
