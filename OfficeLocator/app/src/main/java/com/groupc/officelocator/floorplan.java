package com.groupc.officelocator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
//import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.View.OnClickListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class floorplan extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public Button floorplanb1;
    public Button floorplanb2;
    ImageView floorPlanImage;
    private Spinner chooseRoom;
    int[] buildingNames = {R.array.miaHamm, R.array.mikeSchmidt, R.array.danFouts, R.array.tigerWoods, R.array.nolanRyan};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floorplan);
        Intent goToFloorPlan = getIntent();

        //Displaying the correct building name and floorplan image
        //Setting floor plan name
        String fpname = goToFloorPlan.getStringExtra("fpname");
        TextView floorplanname = (TextView) findViewById(R.id.floorPlanName);
        floorplanname.setText(fpname);
        //Setting floor plan image
        String imageName = goToFloorPlan.getStringExtra("imageName");
        int res = getResources().getIdentifier(imageName, "drawable", this.getPackageName());
        floorPlanImage = (ImageView) findViewById(R.id.floorPlanImage);
        floorPlanImage.setImageResource(res);

        //Back and Next buildings
        floorplanb1 = (Button) findViewById(R.id.floorplanbutton1);
        floorplanb1.setOnClickListener(new View.OnClickListener() {
                                           public void onClick(View v) {
                                               Intent theintent = new Intent(floorplan.this, campus.class);
                                               startActivity(theintent);
                                           }
                                       }
        );
        floorplanb2 = (Button) findViewById(R.id.floorplanbutton2);
        floorplanb2.setOnClickListener(new View.OnClickListener() {

                                           public void onClick(View v) {
                                               Intent theintent = new Intent(floorplan.this, directions.class);
                                               startActivity(theintent);
                                           }

                                       }
        );

        int spinnerNumber = goToFloorPlan.getIntExtra("spinnerNumber",0);

        chooseRoom = (Spinner) findViewById(R.id.roomSelector);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, buildingNames[spinnerNumber], android.R.layout.simple_spinner_item);
        chooseRoom.setAdapter(adapter);
        chooseRoom.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
        TextView myText = (TextView) view;
        //Right now the drop down button causes a "Toast" to appear (onscreen text), need to change
        //this to choosing which page to open instead...
        Toast.makeText(this,"You chose "+myText.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView){

    }





}
