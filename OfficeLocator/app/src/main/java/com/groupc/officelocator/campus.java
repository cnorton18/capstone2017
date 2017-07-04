package com.groupc.officelocator;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//import android.widget.CheckBox;

public class campus extends AppCompatActivity {

    //Buttons for buildings
    public int numberBuildings = 5; //16 total;
    public Button miaHamm;
    public Button mikeSchmidt;
    public Button danFouts;
    public Button tigerWoods;
    public Button nolanRyan1;

    Button[] arrayButtons = {miaHamm, mikeSchmidt, danFouts, tigerWoods, nolanRyan1};
    int[] buttonNames = {R.id.mh1, R.id.ms1, R.id.df1, R.id.tw1, R.id.nr1};
    String[] arrayFloorNames = {"Mia Hamm", "Mike Schmidt", "Dan Fouts", "Tiger Woods", "Nolan Ryan"};
    //Need to add more generic floor plans
    String[] arrayFloorPlanImages = {"miahammfloorplan", "mikeschmidtfloorplan", "danfoutsfloorplan", "miahammfloorplan", "danfoutsfloorplan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus);

        for (int i = 0; i < numberBuildings; i++) {
            final int j = i;
            arrayButtons[i] = (Button)findViewById(buttonNames[i]);
            arrayButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goToFloorPlan = new Intent(campus.this, floorplan.class);
                    goToFloorPlan.putExtra("fpname", arrayFloorNames[j]);
                    goToFloorPlan.putExtra("imageName", arrayFloorPlanImages[j]);
                    goToFloorPlan.putExtra("spinnerNumber", j);
                    startActivity(goToFloorPlan);
                }
            });
        }
    }
}
