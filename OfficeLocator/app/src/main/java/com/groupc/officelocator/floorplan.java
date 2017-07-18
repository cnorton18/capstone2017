package com.groupc.officelocator;

import android.app.Dialog;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class floorplan extends AppCompatActivity{

    //Tracks which building
    public static int buildingselected = 0;
    public static int floorselected = 0;

    Button maplocationbut, home, search;
    ImageView floorPlanImage, buildingLocation, spinner2drop;
    private Spinner chooseFloor, chooseRoom;

    Dialog dialog;
    TextView cancel, floorplanname, roomName;
    String fpname, imageName;

    int[] miaHamm = {R.array.miaHamm1, R.array.miaHamm2};
    int[] tigerWoods = {R.array.tigerWoods1, R.array.tigerWoods2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floorplan);
        Intent goToFloorPlan = getIntent();
        spinner2drop = (ImageView)findViewById(R.id.imageView10);
        roomName = (TextView) findViewById(R.id.roomName);
        Typeface myCustomfont = Typeface.createFromAsset(getAssets(), "fonts/newsgothiccondensedbold.ttf");
        roomName.setTypeface(myCustomfont);

        //Setting floor plan name + font style
        fpname = goToFloorPlan.getStringExtra("fpname");
        floorplanname = (TextView) findViewById(R.id.floorPlanName);
        floorplanname.setTypeface(myCustomfont);
        floorplanname.setText(fpname + " Floor 1");

        //Setting floor plan image
        imageName = goToFloorPlan.getStringExtra("imageName");
        int res = getResources().getIdentifier(imageName, "drawable", this.getPackageName());
        floorPlanImage = (ImageView) findViewById(R.id.floorPlanImage);
        floorPlanImage.setImageResource(res);

        int spinnerNumber = goToFloorPlan.getIntExtra("spinnerNumber",0);
        //Mia Hamm
        if(spinnerNumber == 0){ buildingselected = 1;}
        //Tiger Woods
        else if(spinnerNumber == 1){ buildingselected = 2;}

        //Creating drop down menus
        chooseFloor = (Spinner)findViewById(R.id.floorSelector);
        chooseRoom = (Spinner) findViewById(R.id.roomSelector);

        //First spinner - Choosing which floor
        int numberOfFloors = goToFloorPlan.getIntExtra("numberOfFloors",0);
        List<String> list = new ArrayList<String>();
        list.add("Choose a floor");
        for (int i = 1; i <= numberOfFloors; i++){
            list.add(String.valueOf(i));
        }
        ArrayAdapter<String> numberAdapter = new ArrayAdapter<String>(this, R.layout.spinner_layout, list);
        chooseFloor.setAdapter(numberAdapter);
        chooseFloor.setSelected(false);
        chooseFloor.setSelection(0,true);
        chooseFloor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String theChoice = ((TextView) view).getText().toString();
                roomName.setVisibility(View.INVISIBLE);
                if(theChoice.equals("Choose a floor")){
                    chooseRoom.setSelected(false);
                    chooseRoom.setSelection(0,true);
                    return;
                }
                floorplanname.setText(fpname + " Floor " + theChoice);
                String chosenImage = imageName + theChoice;
                int res = getResources().getIdentifier(chosenImage, "drawable", floorplan.this.getPackageName());
                floorPlanImage.setImageResource(res);
                floorselected = Integer.parseInt(theChoice);
                int choice = floorselected - 1;

                //After selecting first spinner, now the second one is populated
                spinner2drop.setVisibility(View.VISIBLE);
                chooseRoom.setVisibility(View.VISIBLE);

                //Mia Hamm
                if(buildingselected == 1){
                    ArrayAdapter adapter = ArrayAdapter.createFromResource(floorplan.this, miaHamm[choice], R.layout.spinner_layout);
                    chooseRoom.setAdapter(adapter);
                }
                //Tiger Woods
                else if (buildingselected == 2){
                    ArrayAdapter adapter = ArrayAdapter.createFromResource(floorplan.this, tigerWoods[choice], R.layout.spinner_layout);
                    chooseRoom.setAdapter(adapter);
                }
                chooseRoom.setSelected(false);
                chooseRoom.setSelection(0,true);
                select();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Pop up dialog for building location
        createDialog();
        maplocationbut = (Button) findViewById(R.id.floorplanbutton2);
        maplocationbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        //If cancel button in dialog popup is clicked
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        //Home button
        home = (Button) findViewById(R.id.floorplanbutton1);
        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent theintent = new Intent(floorplan.this, campus.class);
                startActivity(theintent);
            }}
        );

        //Search button
        search = (Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent theintent = new Intent(floorplan.this, buildingsearch.class);
                startActivity(theintent);
            }}
        );

    }

    //Sets up pop up dialog
    private void createDialog()
    {
        dialog = new Dialog(floorplan.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.imagedialog);
        buildingLocation = (ImageView)dialog.findViewById(R.id.buildingLocation);
        if(buildingselected == 1)
            buildingLocation.setImageResource(R.drawable.building1highlight);
        else if (buildingselected == 2)
            buildingLocation.setImageResource(R.drawable.building2highlight);
        cancel = (TextView) dialog.findViewById(R.id.cancelTxt);
    }


    private void select(){
        chooseRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selection = ((TextView) view).getText().toString();
                if(selection.equals("Choose a room")){
                    return;
                }
                roomName.setVisibility(View.VISIBLE);
                roomName.setText(selection);

                selection = selection.toLowerCase().replaceAll("\\s","");
                int resource = getResources().getIdentifier(selection, "drawable", floorplan.this.getPackageName());
                floorPlanImage.setImageResource(resource);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    //Resetting Spinner
    public void onResume(){
        super.onResume();
        if(fpname.equals("Mia Hamm"))
            buildingselected = 1;
        else if (fpname.equals("Tiger Woods"))
            buildingselected = 2;

    }



}
