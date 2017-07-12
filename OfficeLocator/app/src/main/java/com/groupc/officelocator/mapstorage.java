package com.groupc.officelocator;

import android.support.v7.app.AppCompatActivity;
import java.util.HashMap;
import java.util.Map;

import android.support.v7.app.AppCompatActivity;
import java.util.HashMap;
import java.util.Map;

public class mapstorage extends AppCompatActivity {
    public Map<String, String[]> campusMap;;

    public mapstorage() {
        campusMap = new HashMap<String, String[]>();
        String[] HammRooms = {"Flyknit", "Air Max"};
        campusMap.put("Mia Hamm", HammRooms);
        String[] WoodsRooms = {"Air Jordan", "Roshe"};
        campusMap.put("Tiger Woods", WoodsRooms);
    }
}
