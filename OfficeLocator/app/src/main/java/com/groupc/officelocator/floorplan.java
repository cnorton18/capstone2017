package com.groupc.officelocator;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ListView;
import android.widget.EditText;


public class floorplan extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public Button back;
    ImageView floorPlanImage;
    private Spinner chooseRoom;
    private ListView lvSearch;
    private EditText etItems;

    //int[] buildingNames = {R.array.miaHamm, R.array.mikeSchmidt};//, R.array.danFouts, R.array.tigerWoods, R.array.nolanRyan};
    int[] buildingNames = {R.array.miaHamm, R.array.tigerWoods};//, R.array.danFouts, R.array.tigerWoods, R.array.nolanRyan};
    private boolean isFirstSelection = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // chooseRoom.setSelection(0);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floorplan);
        Intent goToFloorPlan = getIntent();

        //Displaying the correct building name and floorplan image
        //Setting floor plan name
        String fpname = goToFloorPlan.getStringExtra("fpname");
        TextView floorplanname = (TextView) findViewById(R.id.floorPlanName);
        Typeface myCustomfont = Typeface.createFromAsset(getAssets(), "fonts/futuracondensedextrabold.ttf");
        floorplanname.setTypeface(myCustomfont);
        floorplanname.setText(fpname);
        //Setting floor plan image
        String imageName = goToFloorPlan.getStringExtra("imageName");
        int res = getResources().getIdentifier(imageName, "drawable", this.getPackageName());
        floorPlanImage = (ImageView) findViewById(R.id.floorPlanImage);
        floorPlanImage.setImageResource(res);

        //Back button

        back = (Button) findViewById(R.id.floorplanbutton1);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }}
        );

        int spinnerNumber = goToFloorPlan.getIntExtra("spinnerNumber", 0);
        chooseRoom = (Spinner) findViewById(R.id.roomSelector);
        final ArrayAdapter adapter = ArrayAdapter.createFromResource(this, buildingNames[spinnerNumber], android.R.layout.simple_spinner_item);
        // chooseRoom.clear();
        chooseRoom.setAdapter(adapter);
        //chooseRoom.setSelection(0);
        chooseRoom.setOnItemSelectedListener(this);
        chooseRoom.setSelection(0);
        etItems.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });

        chooseRoom.setOnItemSelectedListener(this);
        chooseRoom.setSelection(0);
    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
        if(isFirstSelection){
            isFirstSelection = false;
            return;
        }
        TextView myText = (TextView) view;
        Intent goToRoom = new Intent(floorplan.this, directions.class);
        goToRoom.putExtra("roomName", myText.getText());
        if(myText.getText().equals("Choose a room")) {
            return;
        }
        startActivity(goToRoom);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView){
    }


    @Override
    public void onBackPressed(){
        chooseRoom.setSelection(0);
    }
    @Override
    public void onResume(){
        super.onResume();
        chooseRoom.setSelection(0);//Resets spinner choice when going back to this page (e.g. back button press)
    }


}
