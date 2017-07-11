package com.groupc.officelocator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
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

    String locations[] = {"Mia Hamm", "Tiger Woods","Air Max", "Air Jordan", "Flyknit", "Roshe"};

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
