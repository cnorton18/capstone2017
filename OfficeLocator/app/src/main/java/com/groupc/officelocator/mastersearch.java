package com.groupc.officelocator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class mastersearch extends AppCompatActivity {
    private ListView searchList;
    private EditText searchBar;
    private ArrayAdapter<String> adapter;
    private String choice, fpname, floorNumber;
    private String[] listValues;
    private int floorCode;
    int [] numberOfFloors = {2,2}; //Mia Hamm, Tiger Wood # of floors

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mastersearch);
        floorplan.fromSearch = 1;

        searchList = (ListView)findViewById(R.id.searchList);
        searchList.setEmptyView(findViewById(R.id.empty));
        searchBar = (EditText)findViewById(R.id.searchBar);
        listValues = getResources().getStringArray(R.array.entireCampus);
        adapter = new ArrayAdapter<String>(this,R.layout.list_items,R.id.searchItems,listValues);
        searchList.setAdapter(adapter);
        searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                choice = searchList.getItemAtPosition(position).toString();
                    if (choice.contains("Mia Hamm")) {
                        fpname = "Mia Hamm";
                        floorCode = 0; //Set to match the spinner values in the campus and floorplan class
                    }
                    else if(choice.contains("Tiger Woods")){
                        fpname = "Tiger Woods";
                        floorCode = 1;
                    }
                    floorNumber = choice.replaceAll("\\D+",""); //Grab the floor # from the search result
                    floorplan.buildingselected = floorCode + 1; //Used in floorplan class
                    Intent goToFloorPlan = new Intent(mastersearch.this, floorplan.class);
                    goToFloorPlan.putExtra("fpname", fpname);
                    if(choice.equals(fpname + " " + floorNumber)) {
                        goToFloorPlan.putExtra("imageName", fpname.toLowerCase().replaceAll("\\s", "") + floorNumber);
                    }
                    else{
                        floorplan.setRoomfromSearch = 1;
                        goToFloorPlan.putExtra("roomName", choice.replaceAll(fpname + " " + floorNumber + " ",""));
                        goToFloorPlan.putExtra("imageName", choice.replaceAll(fpname + " " + floorNumber + " ","").toLowerCase().replaceAll("\\s",""));
                    }
                    goToFloorPlan.putExtra("spinnerNumber", floorCode);
                    goToFloorPlan.putExtra("numberOfFloors", numberOfFloors[floorCode]);
                    goToFloorPlan.putExtra("floorNumber", floorNumber);
                    startActivity(goToFloorPlan);
                }

            });

        searchBar.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mastersearch.this.adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

    }
}