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


public class floorplan extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Tracks which building
    public static int buildingselected = 0;

    Button maplocationbut, home, search;
    ImageView floorPlanImage, buildingLocation;
    private Spinner chooseRoom;
    int[] buildingNames = {R.array.miaHamm, R.array.tigerWoods};//, R.array.danFouts, R.array.tigerWoods, R.array.nolanRyan};
    private boolean isFirstSelection = true;

    Dialog dialog;
    TextView cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floorplan);
        Intent goToFloorPlan = getIntent();

        //Setting floor plan name + font style
        String fpname = goToFloorPlan.getStringExtra("fpname");
        TextView floorplanname = (TextView) findViewById(R.id.floorPlanName);
        Typeface myCustomfont = Typeface.createFromAsset(getAssets(), "fonts/newsgothiccondensedbold.ttf");
        floorplanname.setTypeface(myCustomfont);
        floorplanname.setText(fpname);

        //Setting floor plan image
        String imageName = goToFloorPlan.getStringExtra("imageName");
        int res = getResources().getIdentifier(imageName, "drawable", this.getPackageName());
        floorPlanImage = (ImageView) findViewById(R.id.floorPlanImage);
        floorPlanImage.setImageResource(res);

        //Creating drop down menu
        int spinnerNumber = goToFloorPlan.getIntExtra("spinnerNumber",0);
        chooseRoom = (Spinner) findViewById(R.id.roomSelector);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, buildingNames[spinnerNumber], R.layout.spinner_layout);
        chooseRoom.setAdapter(adapter);
        chooseRoom.setOnItemSelectedListener(this);
        chooseRoom.setSelection(0);

        //Mia Hamm
        if(spinnerNumber == 0){ buildingselected = 1;}
        //Tiger Woods
        else if(spinnerNumber == 1){ buildingselected = 2;}

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


/*
        //map location button
        maplocationbut = (Button) findViewById(R.id.floorplanbutton2);
        maplocationbut.setOnClickListener(new View.OnClickListener(){

                                              public void onClick(View v){

                                                  if(buildingselected == 1) {
                                                      Log.d("ok", "mia hamm");
                                                      Intent theintent = new Intent(floorplan.this, building1location.class);
                                                      startActivity(theintent);
                                                  } else if (buildingselected == 2) {
                                                      Log.d("ok", "tiger woods");
                                                      Intent theintent = new Intent(floorplan.this, building2location.class);
                                                      startActivity(theintent);
                                                  }

                                              }

                                          }
        );

*/
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

    //Sets up spinner
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
        if(isFirstSelection){
            isFirstSelection = false;
            return;
        }
        TextView myText = (TextView) view;
        //Intent goToRoom = new Intent(floorplan.this, directions.class);
        //goToRoom.putExtra("roomName", myText.getText());
        if(myText.getText().equals("Choose a room")) {
            return;
        }

        if(buildingselected == 0) {
            if (buildingselected == 1) {
                floorPlanImage.setImageResource(R.drawable.miahamm);
            } else if (buildingselected == 2) {
                floorPlanImage.setImageResource(R.drawable.tigerwoods);
            }
        }
        if(buildingselected == 1) {
            if(i == 1){ floorPlanImage.setImageResource(R.drawable.flyknit);}
            else if(i == 2){ floorPlanImage.setImageResource(R.drawable.airmax);}
        }
        else if(buildingselected == 2) {
            if(i == 1){ floorPlanImage.setImageResource(R.drawable.airjordan);}
            else if(i == 2){ floorPlanImage.setImageResource(R.drawable.roshe);}
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView){
    }

    //Resetting Spinner
    public void onResume(){
        super.onResume();
        chooseRoom.setSelection(0);//Resets spinner choice when going back to this page (e.g. back button press)
    }



}
