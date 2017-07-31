package com.groupc.officelocator;

import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;

public class mapdata extends AppCompatActivity {

    public class building {
        public String buildingName;
        public int numberofFloors;
        public ArrayList<floor> floors;
    }

    public class floor {
        public ArrayList<String> roomNames;
        public int level;
    }

    public String campusName;
    public int numberofBuildings;
    public ArrayList<building> buildings;
}
