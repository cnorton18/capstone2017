package com.groupc.officelocator;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

public class campus extends AppCompatActivity {
        RelativeLayout relativeLayout;

    public Button albertoSalazar, boJackson, danFouts, jerryRice, joanBenoitSamuelson,
            johnMcEnroe, kenGriffeyJr, miaHamm, michaelJordan, mikeSchmidt, nikeChildDevelopmentCenter,
            nikeSportsCenter, nolanRyan, peteSampras, stevePrefontaineHall, tigerWoods, globe;
    Button[] arrayButtons = {albertoSalazar, boJackson, danFouts, jerryRice, joanBenoitSamuelson,
            johnMcEnroe, kenGriffeyJr, miaHamm, michaelJordan, mikeSchmidt, nikeChildDevelopmentCenter,
            nikeSportsCenter, nolanRyan, peteSampras, stevePrefontaineHall, tigerWoods};
    int[] buttonNames = {R.id.albertosalazar, R.id.bojackson, R.id.danfouts, R.id.jerryrice,
            R.id.joanbenoitsamuelson, R.id.johnmcenroe, R.id.kengriffeyjr, R.id.miahamm,
            R.id.michaeljordan, R.id.mikeschmidt, R.id.nikechilddevelopmentcenter,
            R.id.nikesportscenter, R.id.nolanryan, R.id.petesampras, R.id.steveprefontainehall, R.id.tigerwoods};
    public mapdata data;
    public ImageButton satelliteview;
    private static int globesetting = 0;
    ImageView mapimage;
    Bundle dataContainer;

    //Change color feature variables
    Button changeColors;
    private static String colorValue;
    Dialog colorDialog;
    TextView colorCancel, colorSubmit, colorPrompt;
    CheckBox colorOrange, colorGreen;
    SharedPreferences colorPreferences; //1 = Green, 2 = Orange
    SharedPreferences.Editor editor;

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

            colorPreferences = getSharedPreferences("ColorPreferences", Context.MODE_PRIVATE);
            colorValue = colorPreferences.getString("color", "default");

            //Satellite View
            mapimage = (ImageView)findViewById(R.id.campusmap);
            satelliteview = (ImageButton)findViewById(R.id.satelliteViewButton);

            //Set to satellite view when going back to the campusorange map from another page
            if(globesetting == 1){
                //Set to satellite view
                mapimage.setImageResource(R.drawable.satellite);
                satelliteview.setImageResource(R.drawable.globe2);
                if(colorValue.equals("1") || colorValue.equals("default"))
                    satelliteview.setColorFilter(getResources().getColor(R.color.colorPrimary));
                else
                    satelliteview.setColorFilter(getResources().getColor(R.color.NikeOrange));
                globesetting = 1;

                //Satellite view -> Normal
            }else{
                if(colorValue.equals("1") || colorValue.equals("default"))
                    mapimage.setImageResource(R.drawable.campus);
                else
                    mapimage.setImageResource(R.drawable.campusorange);
                satelliteview.setImageResource(R.drawable.globe);
                satelliteview.setColorFilter(getResources().getColor(R.color.white));
                globesetting = 0;

            }
            satelliteview.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    colorValue = colorPreferences.getString("color", "default");
                    //Normal View -> satellite view
                    if(globesetting == 0){
                        mapimage.setImageResource(R.drawable.satellite);
                        satelliteview.setImageResource(R.drawable.globe2);
                        if(colorValue.equals("1") || colorValue.equals("default"))
                            satelliteview.setColorFilter(getResources().getColor(R.color.colorPrimary));
                        else
                            satelliteview.setColorFilter(getResources().getColor(R.color.NikeOrange));
                        globesetting = 1;
                    }
                    //Satellite View -> Normal
                    else{
                        if(colorValue.equals("1") || colorValue.equals("default"))
                            mapimage.setImageResource(R.drawable.campus);
                        else
                            mapimage.setImageResource(R.drawable.campusorange);
                        satelliteview.setImageResource(R.drawable.globe);
                        satelliteview.setColorFilter(getResources().getColor(R.color.white));
                        globesetting = 0;
                    }
                }
            });

            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
            navigation.getMenu().getItem(0).setChecked(true);


            //Changing colors
            RelativeLayout universalLayout = (RelativeLayout)findViewById(R.id.universal_layout);
            View gradientBlock = (View) universalLayout.findViewById(R.id.gradientBlock);
            editor = colorPreferences.edit();
            changeColors = (Button) findViewById(R.id.colors);
            createColorDialog();
            changeColors.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    colorDialog.show();
                    if (colorValue.equals("1") || colorValue.equals("default")) {
                        colorGreen.setChecked(true);
                        colorOrange.setChecked(false);
                    }
                    else {
                        colorOrange.setChecked(true);
                        colorGreen.setChecked(false);
                    }
                    editor.clear();
                }
            });

            colorOrange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    colorOrange.setChecked(true);
                    colorGreen.setChecked(false);
                    editor.putString("color", "2");
                }
            });

            colorGreen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    colorGreen.setChecked(true);
                    colorOrange.setChecked(false);
                    editor.putString("color", "1");
                }
            });

            colorSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    editor.commit();
                    colorDialog.dismiss();
                    finish();
                    overridePendingTransition( 0, 0);
                    startActivity(getIntent());
                    overridePendingTransition( 0, 0);
                }
            });

            colorCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    if (colorValue.equals("1") || colorValue.equals("default")){
                        colorGreen.setChecked(true);
                        colorOrange.setChecked(false);
                    }
                    else {
                        colorOrange.setChecked(true);
                        colorGreen.setChecked(false);
                    }
                    colorDialog.dismiss();
                }
            });


            //Setting colors for bottom navigation bar
            //Different icon states
            int[][] iconStates = new int[][]{
                    new int[]{android.R.attr.state_checked},//checked state
                    new int[]{-android.R.attr.state_checked},//unchecked state
                    new int[]{} //default color
            };

            //Different icon colors - Green dark theme
            int[] greenColors = new int[]{
                    ResourcesCompat.getColor(getResources(),R.color.colorPrimary, null),
                    ResourcesCompat.getColor(getResources(),R.color.iconColor, null),
                    ResourcesCompat.getColor(getResources(),R.color.iconColor, null),
            };

            //Different icon colors - Orange dark theme
            int[] orangeColors = new int[]{
                    ResourcesCompat.getColor(getResources(),R.color.NikeOrange, null),
                    ResourcesCompat.getColor(getResources(),R.color.iconColor, null),
                    ResourcesCompat.getColor(getResources(),R.color.iconColor, null),
            };


                switch (colorValue) {
                    //Green Dark
                    case "default":
                    case "1":
                        mapimage.setImageResource(R.drawable.campus);
                        gradientBlock.setBackgroundResource(R.drawable.greengradient);
                        ((TextView)colorDialog.findViewById(R.id.submit)).setBackgroundResource(R.drawable.greengradient);
                        ColorStateList navigationColorStateList = new ColorStateList(iconStates, greenColors);
                        navigation.setItemTextColor(navigationColorStateList);
                        navigation.setItemIconTintList(navigationColorStateList);
                        break;

                    //Orange Dark
                    case "2":
                        mapimage.setImageResource(R.drawable.campusorange);
                        gradientBlock.setBackgroundResource(R.drawable.orangegradient);
                        ((TextView)colorDialog.findViewById(R.id.submit)).setBackgroundResource(R.drawable.orangegradient);
                        ColorStateList navigationColorStateList2 = new ColorStateList(iconStates, orangeColors);
                        navigation.setItemTextColor(navigationColorStateList2);
                        navigation.setItemIconTintList(navigationColorStateList2);
                        break;
                }

        }

    //Sets up color dialog
    private void createColorDialog(){
        colorDialog = new Dialog(campus.this);
        colorDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        colorDialog.setContentView(R.layout.colordialog);
        colorCancel = (TextView) colorDialog.findViewById(R.id.cancel);
        colorPrompt = (TextView) colorDialog.findViewById(R.id.prompt);
        Typeface myCustomfont = Typeface.createFromAsset(getAssets(), "fonts/newsgothiccondensedbold.ttf");
        colorPrompt.setTypeface(myCustomfont);
        colorSubmit = (TextView) colorDialog.findViewById(R.id.submit);
        colorOrange = (CheckBox) colorDialog.findViewById(R.id.checkorange);
        colorGreen = (CheckBox) colorDialog.findViewById(R.id.checkneongreen);
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

    @Override
    public void onResume(){
        super.onResume();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.getMenu().getItem(0).setChecked(true);
    }

}

