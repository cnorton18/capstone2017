package com.groupc.officelocator;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class mapdata extends AppCompatActivity {

    public class building {
        public String buildingName;
        public int numberofFloors;
        public ArrayList<floor> floors;
    }

    public class floor {
        public String[] roomNames;
    }

    public String campusName;
    public int numberofBuildings;
    public ArrayList<building> buildings;
}
