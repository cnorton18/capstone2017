package com.groupc.officelocator;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class splash extends AppCompatActivity {

    public Button locate;
    public Button search;
    public mapdata data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        data = new mapdata();

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

        final Bundle dataContainer = new Bundle();
        dataContainer.putParcelable("parse", data);

        locate = (Button)findViewById(R.id.locate);
        locate.setOnClickListener(new View.OnClickListener(){

                                            public void onClick(View v){
                                                Intent theintent = new Intent(splash.this, campus.class);
                                                theintent.putExtras(dataContainer);
                                                startActivity(theintent);
                                            }

                                        }
        );

        search = (Button)findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener(){

                                            public void onClick(View v){
                                                Intent theintent = new Intent(splash.this,masterSearchWithHeaders.class);
                                                theintent.putExtras(dataContainer);
                                                startActivity(theintent);
                                            }

                                        }

        );

    }

    private void parseXML(XmlPullParser parser) throws XmlPullParserException,IOException {
        data.buildings = null; //Move this to a constructor later.
        int eventType = parser.getEventType();
        mapdata.building currentBuilding = null;
        mapdata.floor currentFloor = null;
        mapdata.room currentRoom = null;

        while(eventType != XmlPullParser.END_DOCUMENT) {
            String name = null;
            switch(eventType) {

                case XmlPullParser.START_DOCUMENT:
                    break;

                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if(name.equals("campus")){
                        data.buildings = new ArrayList();
                        data.campusName = new String();
                    }
                    if(name.equals("campusName"))
                        data.campusName = parser.nextText();
                    if(name.equals("numberofBuildings"))
                        data.numberofBuildings = Integer.parseInt(parser.nextText());
                    else if(name.equals("building")) {
                        currentBuilding = new mapdata.building();
                        currentBuilding.floors = new ArrayList();
                    }
                    else if(currentBuilding != null) {
                        if(name.equals("buildingName"))
                            currentBuilding.buildingName = parser.nextText();
                        else if(name.equals("numberofFloors"))
                            currentBuilding.numberofFloors = Integer.parseInt(parser.nextText());
                        else if(name.equals("floor") && currentFloor == null) {
                            currentFloor = new mapdata.floor();
                            currentFloor.level = Integer.parseInt(parser.getAttributeValue(null,"level"));
                            currentFloor.rooms = new ArrayList();
                        }
                        else if(name.equals("roomName") && currentFloor != null) {
                            currentRoom = new mapdata.room();
                            currentRoom.roomName = parser.nextText();
                            currentFloor.rooms.add(currentRoom);
                            currentRoom = null;
                        }
                    }
                    break;

                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if(name.equalsIgnoreCase("building") && currentBuilding != null) {
                        data.buildings.add(currentBuilding);
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








}
