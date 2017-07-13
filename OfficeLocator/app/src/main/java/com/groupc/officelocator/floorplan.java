package com.groupc.officelocator;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class floorplan extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //track which building
    public static int buildingselected = 0;

    public Button maplocationbut;
    public Button back;
    ImageView floorPlanImage;
    private Spinner chooseRoom;
    int[] buildingNames = {R.array.miaHamm, R.array.tigerWoods};//, R.array.danFouts, R.array.tigerWoods, R.array.nolanRyan};
    private boolean isFirstSelection = true;



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
        Typeface myCustomfont = Typeface.createFromAsset(getAssets(), "fonts/futuracondensedextrabold.ttf");
        floorplanname.setTypeface(myCustomfont);
        floorplanname.setText(fpname);
        //Setting floor plan image
        String imageName = goToFloorPlan.getStringExtra("imageName");
        int res = getResources().getIdentifier(imageName, "drawable", this.getPackageName());
        floorPlanImage = (ImageView) findViewById(R.id.floorPlanImage);
        floorPlanImage.setImageResource(res);


        int spinnerNumber = goToFloorPlan.getIntExtra("spinnerNumber",0);
        chooseRoom = (Spinner) findViewById(R.id.roomSelector);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, buildingNames[spinnerNumber], android.R.layout.simple_spinner_item);
        chooseRoom.setAdapter(adapter);
        chooseRoom.setOnItemSelectedListener(this);
        chooseRoom.setSelection(0);


        //mia hamm
        if(spinnerNumber == 0){ buildingselected = 1;}

        //tiger woods
        else if(spinnerNumber == 1){ buildingselected = 2;}





        //Back button
        back = (Button) findViewById(R.id.floorplanbutton1);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent theintent = new Intent(floorplan.this, campus.class);
                startActivity(theintent);
            }}
        );



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


    }









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



        //startActivity(goToRoom);


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

    public void onResume(){
        super.onResume();
        chooseRoom.setSelection(0);//Resets spinner choice when going back to this page (e.g. back button press)
    }




}
