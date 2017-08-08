package com.groupc.officelocator;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1500;

    public Button locate;
    public Button search;
    public mapdata data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        //Splash Screen delay
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent intent = new Intent(MainActivity.this, campus.class);
                intent.putExtras(dataContainer);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();
            }
        }, SPLASH_TIME_OUT);
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
                            currentFloor.image = parser.getAttributeValue(null, "src");
                            /* Example of how to load a drawable from string
                            Context context = getApplicationContext();
                            int id = context.getResources().getIdentifier(parser.getAttributeValue(null, "src"), "drawable", getPackageName());
                            Drawable d = getResources().getDrawable(id, null);
                            */
                        }
                        else if(currentFloor != null) {
                            if (name.equals("room")) {
                                currentRoom = new mapdata.room();
                            } else if (name.equals("roomName")) {
                                currentRoom.roomName = parser.nextText();
                            } else if (name.equals("coords")) {
                                String coords = parser.nextText();
                                //parse later
                                currentRoom.vert = 0;
                                currentRoom.horiz = 0;
                            }
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
                    if(name.equalsIgnoreCase("room") && currentRoom != null) {
                        currentFloor.rooms.add(currentRoom);
                        currentRoom = null;
                    }
                    break;
            }
            eventType = parser.next();
        }
    }
}
