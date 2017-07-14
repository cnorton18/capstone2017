package com.groupc.officelocator;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class roomsearch extends mapstorage {
    private ListView searchList;
    private EditText searchBar;
    private ArrayAdapter<String> adapter;
    private String choice;
    //String[] arrayFloorPlanImages = {"miahamm", "tigerwoods"};
    private String[] mia = {"flyknit", "airmax"};
    private String[] tiger = {"airjordan", "roshe"};
    private String[][] roomList = {mia, tiger};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // roomList[0] = {"flyknit", "airmax"};

        Intent extras = getIntent();
        final String building = extras.getStringExtra("building");
        final String[] rooms = campusMap.get(building);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roomsearch);

        searchList = (ListView)findViewById(R.id.searchList);
        searchList.setEmptyView(findViewById(R.id.empty));
        searchBar = (EditText)findViewById(R.id.searchBar);
        adapter = new ArrayAdapter<String>(this,R.layout.list_items,R.id.textView,rooms);
        searchList.setAdapter(adapter);
        searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                choice = searchList.getItemAtPosition(position).toString();
                int buildings = -1;
                if(building.equals("Mia Hamm")){
                    buildings = 0;
                }
                if(building.equals("Tiger Woods")){
                    buildings = 1;
                }
                for (int i = 0; i < rooms.length; ++i) {
                    final int j = i;
                    if (choice.equals(rooms[i])) {
                        Intent goToRoom = new Intent(roomsearch.this, floorplan.class);
                        goToRoom.putExtra("fpname", choice);
                        goToRoom.putExtra("imageName", roomList[buildings][j]);
                        goToRoom.putExtra("spinnerNumber", buildings);
                        startActivity(goToRoom);
                    }
                }

            }
        });

        searchBar.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                roomsearch.this.adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

    }
}