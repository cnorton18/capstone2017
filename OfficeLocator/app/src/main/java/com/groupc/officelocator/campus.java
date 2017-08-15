package com.groupc.officelocator;

import android.graphics.Typeface;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class campus extends mapstorage {
        RelativeLayout relativeLayout;

        //Buttons for just 2 buildings


        public Button miaHamm, tigerWoods, search, globe;


        private static int globesetting = 0;
        ImageView mapimage, mapicon;

        Button[] arrayButtons = {miaHamm, tigerWoods};//, danFouts, tigerWoods, nolanRyan1};
        int[] buttonNames = {R.id.mh1, R.id.tw1};// R.id.df1, R.id.tw1, R.id.nr1};

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            android.support.v7.app.ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.hide();
            }
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_campus);

            ZoomLayout myZoomView = new ZoomLayout(campus.this);

            relativeLayout = (RelativeLayout) findViewById(R.id.zoom);
            relativeLayout.addView(myZoomView);

            TextView text = (TextView) findViewById(R.id.campus);
            Typeface myCustomfont = Typeface.createFromAsset(getAssets(), "fonts/newsgothiccondensedbold.ttf");
            text.setTypeface(myCustomfont);

            for (int i = 0; i < numberOfBuildings; i++) {
                final int j = i;
                arrayButtons[i] = (Button) findViewById(buttonNames[i]);
                arrayButtons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent goToFloorPlan = new Intent(campus.this, floorplan.class);
                        goToFloorPlan.putExtra("fpname", buildingNames[j]);
                        goToFloorPlan.putExtra("imageName", buildingNames[j].replaceAll("\\s", "").toLowerCase());
                        goToFloorPlan.putExtra("spinnerNumber", j);
                        goToFloorPlan.putExtra("numberOfFloors", numberOfFloors[j]);
                        goToFloorPlan.putExtra("floorNumber", "1");
                        startActivity(goToFloorPlan);
                    }
                });
            }
            search = (Button) findViewById(R.id.searchbutton);
            search.setOnClickListener(new View.OnClickListener() {

                                          public void onClick(View v) {
                                              Intent theintent = new Intent(campus.this, masterSearchWithHeaders.class);
                                              startActivity(theintent);
                                          }

                                      }

            );






            mapimage = (ImageView) findViewById(R.id.themap);
            mapicon = (ImageView) findViewById(R.id.globeicon);


            //set up satellite view when going back to the campus map from another screen
            if(globesetting == 1){
                //change to satellite
                mapimage.setImageResource(R.drawable.campusaboveorange);
                mapicon.setImageResource(R.drawable.globe2);
                globesetting = 1;


                //satellite view
            }else{
                //change to normal
                mapimage.setImageResource(R.drawable.campusorange);
                mapicon.setImageResource(R.drawable.globe);
                globesetting = 0;

            }



            //satellite view
            globe = (Button) findViewById(R.id.maptoggle1);
            globe.setOnClickListener(new View.OnClickListener() {

                                          public void onClick(View v) {
                                              //normal view
                                              if(globesetting == 0){
                                                  //change to satellite
                                                  mapimage.setImageResource(R.drawable.campusaboveorange);
                                                  mapicon.setImageResource(R.drawable.globe2);
                                                  globesetting = 1;


                                                  //satellite view
                                              }else{
                                                  //change to normal
                                                  mapimage.setImageResource(R.drawable.campusorange);
                                                  mapicon.setImageResource(R.drawable.globe);
                                                  globesetting = 0;

                                              }


                                          }

                                      }

            );



        }


    @Override
    public void onRestart(){
        super.onRestart();
        startActivity(new Intent(this, campus.class));
    }



}
