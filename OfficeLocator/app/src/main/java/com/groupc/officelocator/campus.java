package com.groupc.officelocator;

import android.graphics.Typeface;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

public class campus extends AppCompatActivity {
        RelativeLayout relativeLayout;

        public Button albertoSalazar, boJackson, danFouts, jerryRice, joanBenoitSamuelson,
                johnMcEnroe, kenGriffeyJr, miaHamm, michaelJordan, mikeSchmidt, nikeSportsCenter, nolanRyan,
                peteSampras, tigerWoods, globe;
        Button[] arrayButtons = {albertoSalazar, boJackson, danFouts, jerryRice, joanBenoitSamuelson,
                johnMcEnroe, kenGriffeyJr, miaHamm, michaelJordan, mikeSchmidt, nikeSportsCenter,
                nolanRyan, peteSampras, tigerWoods};
        int[] buttonNames = {R.id.albertosalazar, R.id.bojackson, R.id.danfouts, R.id.jerryrice,
                R.id.joanbenoitsamuelson, R.id.johnmcenroe, R.id.kengriffeyjr, R.id.miahamm,
                R.id.michaeljordan, R.id.mikeschmidt, R.id.nikesportscenter, R.id.nolanryan,
                R.id.petesampras, R.id.tigerwoods};
        public mapdata data;
        public ImageButton satelliteview;
        private static int globesetting = 0;
        ImageView mapimage, mapicon;
        Bundle dataContainer;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_campus);

            ZoomLayout myZoomView = new ZoomLayout(campus.this);
            relativeLayout = (RelativeLayout) findViewById(R.id.zoom);
            relativeLayout.addView(myZoomView);

            TextView text = (TextView) findViewById(R.id.campus);
            Typeface myCustomfont = Typeface.createFromAsset(getAssets(), "fonts/newsgothiccondensedbold.ttf");
            text.setTypeface(myCustomfont);

            Intent priorInt = getIntent();
            dataContainer = priorInt.getExtras();
            data = new mapdata();
            data = dataContainer.getParcelable("parse");

            if(data == null)
                android.os.Process.killProcess(android.os.Process.myPid());

            for (int i = 0; i < data.buildings.size(); i++) {
                final int j = i;
                arrayButtons[i] = (Button) findViewById(buttonNames[i]);
                arrayButtons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent goToFloorPlan = new Intent(campus.this, floorplan.class);

                        goToFloorPlan.putExtras(dataContainer);
                        floorplan.fromSearch = 1;
                        floorplan.fromCampus = 1;
                        floorplan.floorNumber = "0";
                        floorplan.fpname = data.buildings.get(j).buildingName;
                        floorplan.imageName = data.buildings.get(j).buildingName.replaceAll("\\s","").toLowerCase() + 1;
                        floorplan.spinnerNumber = j;
                        floorplan.numberOfFloors = data.buildings.get(j).numberofFloors;
                        floorplan.buildingselected = j + 1;

                        startActivity(goToFloorPlan);
                    }
                });
            }


            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
            navigation.getMenu().getItem(0).setChecked(true);






            mapimage = (ImageView) findViewById(R.id.campusmap);
            mapicon = (ImageView) findViewById(R.id.globeimage);


            //set up satellite view when going back to the campus map from another screen
            if(globesetting == 1){
                //change to satellite
                mapimage.setImageResource(R.drawable.campusaboveorange);
                mapicon.setImageResource(R.drawable.globe2);
                globesetting = 1;


                //satellite view
            }else{
                //change to normal
                mapimage.setImageResource(R.drawable.campus);
                mapicon.setImageResource(R.drawable.globe);
                globesetting = 0;

            }



            //satellite view
            globe = (Button) findViewById(R.id.globebutton);
            globe.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    //normal view
                    if(globesetting == 0){
                        //change to satellite
                        mapimage.setImageResource(R.drawable.campusaboveorange);
                        mapicon.setImageResource(R.drawable.globe2);
                        globesetting = 1;


                        //satellite view
                    }else{
                        //change to normal
                        mapimage.setImageResource(R.drawable.campus);
                        mapicon.setImageResource(R.drawable.globe);
                        globesetting = 0;

                    }


                }

            });






        }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Intent theintent = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;

                case R.id.navigation_search:
                    theintent = new Intent(campus.this, masterSearchWithHeaders.class);
                    theintent.putExtras(dataContainer);
                    break;

                case R.id.navigation_favorites:
                    theintent = new Intent(campus.this, favoritesList.class);
                    theintent.putExtras(dataContainer);
                    break;
            }
            startActivity(theintent);
            return true;
        }

    };
}
