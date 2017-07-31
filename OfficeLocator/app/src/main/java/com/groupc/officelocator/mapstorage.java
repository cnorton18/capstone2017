package com.groupc.officelocator;

<<<<<<< HEAD
import android.support.v7.app.AppCompatActivity;
import java.util.HashMap;
import java.util.Map;

import android.support.v7.app.AppCompatActivity;
import java.util.HashMap;
=======
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
>>>>>>> master
import java.util.Map;

public class mapstorage extends AppCompatActivity {
    public Map<String, String[]> campusMap;;
<<<<<<< HEAD

    public mapstorage() {
        campusMap = new HashMap<String, String[]>();
        String[] HammRooms = {"Flyknit", "Air Max"};
        campusMap.put("Mia Hamm", HammRooms);
        String[] WoodsRooms = {"Air Jordan", "Roshe"};
        campusMap.put("Tiger Woods", WoodsRooms);
=======
    public int[] numberOfFloors;
    public int numberOfBuildings;
    public String[] buildingNames;

    public mapstorage() {

        //When inputting Building data, format is {Floor X, Floor X Room 1, Floor X Room 2, Floor Y, Floor Y Room 2, etc)
        campusMap = new LinkedHashMap<String, String[]>();
        String[] MiaHammSearchStrings = {"Mia Hamm 1", "Mia Hamm 1 Flyknit", "Mia Hamm 1 Air Max",
                "Mia Hamm 2", "Mia Hamm 2 LunarCharge", "Mia Hamm 2 Kobe Mamba"};
        campusMap.put("Mia Hamm", MiaHammSearchStrings);
        String[] TigerWoodsSearchStrings = {"Tiger Woods 1", "Tiger Woods 1 Air Jordan", "Tiger Woods 1 Roshe",
                "Tiger Woods 2", "Tiger Woods 2 Pegasus", "Tiger Woods 2 VaporMax"};
        campusMap.put("Tiger Woods", TigerWoodsSearchStrings);

        //Number of floors for each building, in order (e.g. Mia Hamm, Tiger Woods...)
        numberOfFloors = new int[]{2,2};

        buildingNames = campusMap.keySet().toArray(new String[0]);
        numberOfBuildings = buildingNames.length;
>>>>>>> master
    }
}
