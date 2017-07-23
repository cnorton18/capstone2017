package com.groupc.officelocator;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class mapstorage extends AppCompatActivity {
    public Map<String, String[]> campusMap;;

    public mapstorage() {
        campusMap = new LinkedHashMap<String, String[]>();
        String[] MiaHammSearchStrings = {"Mia Hamm 1", "Mia Hamm 1 Flyknit", "Mia Hamm 1 Air Max",
                "Mia Hamm 2", "Mia Hamm 2 LunarCharge", "Mia Hamm 2 Kobe Mamba"};
        campusMap.put("Mia Hamm", MiaHammSearchStrings);
        String[] TigerWoodsSearchStrings = {"Tiger Woods 1", "Tiger Woods 1 Air Jordan", "Tiger Woods 1 Roshe",
                "Tiger Woods 2", "Tiger Woods 2 Pegasus"};
        campusMap.put("Tiger Woods", TigerWoodsSearchStrings);
    }
}
