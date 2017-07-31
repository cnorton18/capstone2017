package com.groupc.officelocator;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class campus extends mapdata {
        RelativeLayout relativeLayout;

        //Buttons for just 2 buildings
        public Button miaHamm, tigerWoods, search;
        Button[] arrayButtons = {miaHamm, tigerWoods};//, danFouts, tigerWoods, nolanRyan1};
        int[] buttonNames = {R.id.mh1, R.id.tw1};// R.id.df1, R.id.tw1, R.id.nr1};

        int numberofFloors;

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

            XmlPullParserFactory pullParserFactory;
            try {
                pullParserFactory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = pullParserFactory.newPullParser();

                InputStream input = getApplicationContext().getAssets().open("data.xml");
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(input,null);
                parseXML(parser);
            }
            catch(XmlPullParserException e) {
                e.printStackTrace();
            }
            catch(IOException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < buildings.size(); i++) {
                final int j = i;
                arrayButtons[i] = (Button) findViewById(buttonNames[i]);
                arrayButtons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent goToFloorPlan = new Intent(campus.this, floorplan.class);
                        goToFloorPlan.putExtra("fpname", buildings.get(j).buildingName);
                        goToFloorPlan.putExtra("imageName",buildings.get(j).buildingName.replaceAll("\\s","").toLowerCase());
                        goToFloorPlan.putExtra("spinnerNumber", j);
                        goToFloorPlan.putExtra("numberOfFloors", buildings.get(j).numberofFloors);
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
        }

    @Override
    public void onRestart(){
        super.onRestart();
        startActivity(new Intent(this, campus.class));
    }

    private void parseXML(XmlPullParser parser) throws XmlPullParserException,IOException {
        buildings = null; //Move this to a constructor later.
        int eventType = parser.getEventType();
        building currentBuilding = null;
        floor currentFloor = null;

        while(eventType != XmlPullParser.END_DOCUMENT) {
            String name = null;
            switch(eventType) {

                case XmlPullParser.START_DOCUMENT:
                    break;

                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if(name.equals("campus")){
                        buildings = new ArrayList();
                        campusName = new String();
                    }
                    if(name.equals("campusName"))
                        campusName = parser.nextText();
                    if(name.equals("numberofBuildings"))
                        numberofBuildings = Integer.parseInt(parser.nextText());
                    else if(name.equals("building")) {
                        currentBuilding = new building();
                        currentBuilding.floors = new ArrayList();
                    }
                    else if(currentBuilding != null) {
                        if(name.equals("buildingName"))
                            currentBuilding.buildingName = parser.nextText();
                        else if(name.equals("numberofFloors"))
                            currentBuilding.numberofFloors = Integer.parseInt(parser.nextText());
                        else if(name.equals("floor") && currentFloor == null) {
                            currentFloor = new floor();
                            currentFloor.level = Integer.parseInt(parser.getAttributeValue(null,"level"));
                            currentFloor.roomNames = new ArrayList();
                        }
                        else if(name.equals("roomName") && currentFloor != null)
                            currentFloor.roomNames.add(parser.nextText());
                    }
                    break;

                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if(name.equalsIgnoreCase("building") && currentBuilding != null) {
                        buildings.add(currentBuilding);
                        currentBuilding = null;
                    }
                    if(name.equalsIgnoreCase("floor") && currentFloor != null) {
                        currentBuilding.floors.add(currentFloor);
                        currentFloor = null;
                    }
                    break;
            }
            eventType = parser.next();
        }
    }

}
