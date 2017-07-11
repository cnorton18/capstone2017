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

/**
 * Created by Nhi Vu on 7/10/2017.
 */

public class search extends AppCompatActivity {
    private ListView searchList;
    private EditText searchBar;
    private ArrayAdapter<String> adapter;
    private String choice;

    String locations[] = {"Mia Hamm", "Air Max", "Flyknit", "Tiger Woods", "Air Jordan", "Roshe"};
    String rooms[] = {"Air Max", "Flyknit", "Air Jordan", "Roshe"};
    String buildings[] = {"Mia Hamm", "Tiger Woods"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchList = (ListView)findViewById(R.id.searchList);
        searchBar = (EditText)findViewById(R.id.searchBar);
        adapter = new ArrayAdapter<String>(this,R.layout.list_items,R.id.textView,locations);
        searchList.setAdapter(adapter);
        searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                choice = searchList.getItemAtPosition(position).toString();
                for (int i = 0; i < rooms.length; ++i) {
                    if (choice.equals(rooms[i])) {
                        Intent goToRoom = new Intent(search.this, directions.class);
                        goToRoom.putExtra("roomName", choice);
                        startActivity(goToRoom);
                    }
                }
                for (int i = 0; i < buildings.length; ++i) {
                    if (choice.equals(buildings[i])) {
                        Intent goToRoom = new Intent(search.this, floorplan.class);
                        goToRoom.putExtra("fpname", choice);
                        choice = choice.replaceAll("\\s+", "");
                        choice = choice.toLowerCase();
                        goToRoom.putExtra("imageName", choice);
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
                search.this.adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

    }
}
