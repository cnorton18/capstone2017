package com.groupc.officelocator;

import android.graphics.Typeface;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class campus extends AppCompatActivity {
        RelativeLayout relativeLayout;

        //Buttons for just 2 buildings
        public Button miaHamm, tigerWoods, search;
        Button[] arrayButtons = {miaHamm, tigerWoods};//, danFouts, tigerWoods, nolanRyan1};
        int[] buttonNames = {R.id.mh1, R.id.tw1};// R.id.df1, R.id.tw1, R.id.nr1};
        public mapdata data;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            android.support.v7.app.ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.hide();
            }
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_campus);
            ZoomLayout myZoomView = new ZoomLayout(campus.this);

            relativeLayout = (RelativeLayout) findViewById(R.id.zoom);
            relativeLayout.addView(myZoomView);

            TextView text = (TextView) findViewById(R.id.campus);
            Typeface myCustomfont = Typeface.createFromAsset(getAssets(), "fonts/newsgothiccondensedbold.ttf");
            text.setTypeface(myCustomfont);

            Intent priorInt = getIntent();
            final Bundle dataContainer = priorInt.getExtras();
            data = new mapdata();
            data = dataContainer.getParcelable("parse");

            if(data == null)
                android.os.Process.killProcess(android.os.Process.myPid());

            /*
            for (int i = 0; i < 2; i++) {//data.buildings.size(); i++) {
                final int j = i;
                arrayButtons[i] = (Button) findViewById(buttonNames[i]);
                arrayButtons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent goToFloorPlan = new Intent(campus.this, floorplan.class);
                        goToFloorPlan.putExtra("fpname", data.buildings.get(j).buildingName);
                        goToFloorPlan.putExtra("imageName",data.buildings.get(j).buildingName.replaceAll("\\s","").toLowerCase());
                        goToFloorPlan.putExtra("spinnerNumber", j);
                        goToFloorPlan.putExtra("numberOfFloors", data.buildings.get(j).numberofFloors);
                        goToFloorPlan.putExtra("floorNumber", "1");
                        goToFloorPlan.putExtras(dataContainer);
                        startActivity(goToFloorPlan);
                    }
                });
            }
            */

            search = (Button) findViewById(R.id.searchbutton);
            search.setOnClickListener(new View.OnClickListener() {

                                          public void onClick(View v) {
                                              Intent theintent = new Intent(campus.this, masterSearchWithHeaders.class);
                                              theintent.putExtras(dataContainer);
                                              startActivity(theintent);
                                          }

                                      }

            );
        }
}
