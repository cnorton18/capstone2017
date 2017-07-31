package com.groupc.officelocator;

<<<<<<< HEAD
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
=======
import android.graphics.Typeface;
>>>>>>> master
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
<<<<<<< HEAD
import android.widget.TextView;
//import android.widget.CheckBox;

public class campus extends AppCompatActivity {

    //Buttons for just 2 buildings
    public int numberBuildings = 2; //16 total;
    public Button miaHamm;
    public Button tigerWoods;
    public Button search;

    Button[] arrayButtons = {miaHamm, tigerWoods};//, danFouts, tigerWoods, nolanRyan1};
    int[] buttonNames = {R.id.mh1, R.id.tw1};// R.id.df1, R.id.tw1, R.id.nr1};
    String[] arrayFloorNames = {"Mia Hamm", "Tiger Woods"};//, "Dan Fouts", "Tiger Woods", "Nolan Ryan"};
    //Need to add more generic floor plans
    String[] arrayFloorPlanImages = {"miahamm", "tigerwoods"};//, "danfouts", "miahamm", "danfouts"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus);

        TextView text = (TextView)findViewById(R.id.campus);
        Typeface myCustomfont = Typeface.createFromAsset(getAssets(), "fonts/newsgothiccondensedbold.ttf");
        text.setTypeface(myCustomfont);

        text = (TextView)findViewById(R.id.textView4);
        text.setTypeface(myCustomfont);

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
        search = (Button)findViewById(R.id.searchbutton);
        search.setOnClickListener(new View.OnClickListener(){

                                      public void onClick(View v){
                                          Intent theintent = new Intent(campus.this, buildingsearch.class);
                                          startActivity(theintent);
                                      }

                                  }

        );

    }
=======
import android.widget.RelativeLayout;
import android.widget.TextView;

public class campus extends mapstorage {
        RelativeLayout relativeLayout;

        //Buttons for just 2 buildings
        public Button miaHamm, tigerWoods, search;

        Button[] arrayButtons = {miaHamm, tigerWoods};//, danFouts, tigerWoods, nolanRyan1};
        int[] buttonNames = {R.id.mh1, R.id.tw1};// R.id.df1, R.id.tw1, R.id.nr1};

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

            for (int i = 0; i < numberOfBuildings; i++) {
                final int j = i;
                arrayButtons[i] = (Button) findViewById(buttonNames[i]);
                arrayButtons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent goToFloorPlan = new Intent(campus.this, floorplan.class);
                        goToFloorPlan.putExtra("fpname", buildingNames[j]);
                        goToFloorPlan.putExtra("imageName", buildingNames[j].replaceAll("\\s", "").toLowerCase());
                        goToFloorPlan.putExtra("spinnerNumber", j);
                        goToFloorPlan.putExtra("numberOfFloors", numberOfFloors[j]);
                        goToFloorPlan.putExtra("floorNumber", "1");
                        startActivity(goToFloorPlan);
                    }
                });
            }
            search = (Button) findViewById(R.id.searchbutton);
            search.setOnClickListener(new View.OnClickListener() {

                                          public void onClick(View v) {
                                              Intent theintent = new Intent(campus.this, masterSearchWithHeaders.class);
                                              startActivity(theintent);
                                          }

                                      }

            );

        }


    @Override
    public void onRestart(){
        super.onRestart();
        startActivity(new Intent(this, campus.class));
    }



>>>>>>> master
}
