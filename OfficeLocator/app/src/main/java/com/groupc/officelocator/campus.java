package com.groupc.officelocator;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class campus extends AppCompatActivity {

    //Buttons for just 2 buildings
    public int numberBuildings = 2; //16 total;
    public Button miaHamm, tigerWoods, search;

    Button[] arrayButtons = {miaHamm, tigerWoods};//, danFouts, tigerWoods, nolanRyan1};
    int[] buttonNames = {R.id.mh1, R.id.tw1};// R.id.df1, R.id.tw1, R.id.nr1};
    String[] arrayFloorNames = {"Mia Hamm", "Tiger Woods"};//, "Dan Fouts", "Tiger Woods", "Nolan Ryan"};
    int [] numberOfFloors = {2,2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus);

        TextView text = (TextView)findViewById(R.id.campus);
        Typeface myCustomfont = Typeface.createFromAsset(getAssets(), "fonts/newsgothiccondensedbold.ttf");
        text.setTypeface(myCustomfont);

        for (int i = 0; i < numberBuildings; i++) {
            final int j = i;
            arrayButtons[i] = (Button)findViewById(buttonNames[i]);
            arrayButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goToFloorPlan = new Intent(campus.this, floorplan.class);
                    goToFloorPlan.putExtra("fpname", arrayFloorNames[j]);
                    goToFloorPlan.putExtra("imageName", arrayFloorNames[j].replaceAll("\\s","").toLowerCase());
                    goToFloorPlan.putExtra("spinnerNumber", j);
                    goToFloorPlan.putExtra("numberOfFloors", numberOfFloors[j]);
                    goToFloorPlan.putExtra("floorNumber", "1");
                    startActivity(goToFloorPlan);
                }
            });
        }
        search = (Button)findViewById(R.id.searchbutton);
        search.setOnClickListener(new View.OnClickListener(){

                                      public void onClick(View v){
                                          Intent theintent = new Intent(campus.this, masterSearchWithHeaders.class);
                                          startActivity(theintent);
                                      }

                                  }

        );

    }
}
