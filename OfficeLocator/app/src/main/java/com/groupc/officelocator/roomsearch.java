package com.groupc.officelocator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.groupc.officelocator.R;
import com.groupc.officelocator.directions;

public class roomsearch extends mapstorage {
    private ListView searchList;
    private EditText searchBar;
    private ArrayAdapter<String> adapter;
    private String choice;
    String[] arrayFloorPlanImages = {"miahamm", "tigerwoods"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {

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
                for (int i = 0; i < rooms.length; ++i) {
                    final int j = i;
                    if (choice.equals(rooms[i])) {
                        Intent goToRoom = new Intent(roomsearch.this, floorplan.class);
                        goToRoom.putExtra("fpname", choice);
                        goToRoom.putExtra("imageName", arrayFloorPlanImages[j]);
                        goToRoom.putExtra("spinnerNumber", j);

                        //goToRoom.putExtra("buildingName", building);
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