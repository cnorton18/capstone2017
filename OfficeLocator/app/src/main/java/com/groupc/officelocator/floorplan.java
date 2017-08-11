package com.groupc.officelocator;

import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

    public static int buildingselected = 0; //Tracks which building
    public static int setRoomfromSearch = 0; //Determines if a room was chosen in Search
    public static int floorselected = 0; //Determines if a floor number was chosen in Search or through Spinner
    public static int fromSearch = 0; //Determines if the previous page was Search before coming to the floorplan page

    Button maplocationbut, home, search;
    ImageView floorPlanImage, buildingLocation, spinner2drop;
    private Spinner chooseFloor, chooseRoom;

    Dialog dialog;
    TextView cancel, floorplanname, roomName;
    public static String fpname, imageName, floorNumber, chosenRoomFromSearch, rmName;
    public static int spinnerNumber, numberOfFloors;

    public mapdata data;
    Bundle dataContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floorplan);

        data = new mapdata();

        Intent goToFloorPlan = getIntent();
        final Bundle dataContainer = goToFloorPlan.getExtras();
        data = dataContainer.getParcelable("parse");

        spinner2drop = (ImageView)findViewById(R.id.imageView10); //Dropdown arrow for 2nd spinner

        //Room title on the floorplan page
        roomName = (TextView) findViewById(R.id.roomName);
        Typeface myCustomfont = Typeface.createFromAsset(getAssets(), "fonts/newsgothiccondensedbold.ttf");
        roomName.setTypeface(myCustomfont);

        //If a room was chosen through search, must cause the room title to appear since by default it doesn't until you choose the
        //room through the second spinner
        if(setRoomfromSearch == 1){
            //rmName = goToFloorPlan.getStringExtra("roomName");
            roomName.setText(rmName);
            roomName.setVisibility(View.VISIBLE);
        }

        //Setting floor plan title name + font style
        //fpname = goToFloorPlan.getStringExtra("fpname");
        floorplanname = (TextView) findViewById(R.id.floorPlanName);
        floorplanname.setTypeface(myCustomfont);
        //floorNumber = goToFloorPlan.getStringExtra("floorNumber");
        //If the floor plan title has a floor number, we add that to the title
        if(Integer.parseInt(floorNumber) == 0) //For those without a floor number ("Mia Hamm"/"Tiger Woods"
        //the default is the first floor
            floorNumber = "1";
        floorplanname.setText(fpname + " Floor " + floorNumber);

        //Setting floor plan image
        //imageName = goToFloorPlan.getStringExtra("imageName");
        int res = getResources().getIdentifier(imageName, "drawable", this.getPackageName());
        //floorPlanImage = (ImageView) findViewById(R.id.floorPlanImage);
        floorPlanImage = (ImageView) findViewById(R.id.floorPlanImage);
        floorPlanImage.setImageResource(res);

        //spinnerNumber = goToFloorPlan.getIntExtra("spinnerNumber",0);
        buildingselected = spinnerNumber + 1;

        //Creating the two spinner drop down menus that choose the floor and rooms
        //Choosing a floor in the first spinner causes the second spinner to be visible
        //The choice of the floor also determines the choices of rooms for the second spinner
        chooseFloor = (Spinner)findViewById(R.id.floorSelector);
        chooseRoom = (Spinner) findViewById(R.id.roomSelector);

        //First spinner - Choosing which floor
        //numberOfFloors = goToFloorPlan.getIntExtra("numberOfFloors",0);
        List<String> list = new ArrayList<String>();
        list.add("Choose a floor");
        for (int i = 1; i <= numberOfFloors; i++){
            list.add(String.valueOf(i));
        }
        ArrayAdapter<String> numberAdapter = new ArrayAdapter<String>(this, R.layout.spinner_layout, list);
        chooseFloor.setAdapter(numberAdapter);
        chooseFloor.setSelected(false);
        chooseFloor.setSelection(0,true);

        //When coming from the search menu, the floor number is already chosen and the room can be chosen through the search
        //results. If they are we must set the spinners to reflect these predetermined choices
        if(fromSearch == 1){
            //All search results have floor values set into them (if there is not one explicitly set, it gets sent
            //to the first floor
            int floor = Integer.parseInt(floorNumber);
            chooseFloor.setSelection(floor,true);
            chooseFloor.setSelected(true);

            spinner2drop.setVisibility(View.VISIBLE);
            chooseRoom.setVisibility(View.VISIBLE);

            List<String> spinnerArray = new ArrayList<String>();
            for(int i = 0; i < data.numberofBuildings; ++i) {
                for(int j = 0; j < data.buildings.get(i).floors.size(); ++j) {
                    if(buildingselected == (i + 1) && data.buildings.get(i).floors.get(j).level == floor) {
                        for(int k = 0; k < data.buildings.get(i).floors.get(j).rooms.size(); ++k) {
                            spinnerArray.add(data.buildings.get(i).floors.get(j).rooms.get(k).roomName);
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(floorplan.this,  R.layout.spinner_layout, spinnerArray);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        chooseRoom.setAdapter(adapter);
                        break;
                    }
                }
            }
            chooseRoom.setSelected(false);
            chooseRoom.setSelection(0,true);

            //If the room was also chosen through the search result chosen, we choose that value to
            //appear in the second spinner for the rooms
            if(setRoomfromSearch==1){
                chooseRoom.setSelected(true);
                int selection = 0;
                //chosenRoomFromSearch = goToFloorPlan.getStringExtra("roomName");
                if(chosenRoomFromSearch != null) {
                    for(int i = 0; i < data.numberofBuildings; ++i) {
                        for (int j = 0; j < data.buildings.get(i).floors.size(); ++j) {
                            for (int k = 0; k < data.buildings.get(i).floors.get(j).rooms.size(); ++k) {
                                if (chosenRoomFromSearch.matches(data.buildings.get(i).floors.get(j).rooms.get(k).roomName))
                                    selection = k;
                            }
                        }
                    }
                }
                chooseRoom.setSelection(selection,true);
            }
            select();
        }

        //What to do when the user clicks on a choice for the first spinner for choosing floors
        chooseFloor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //User selects the floor #
                String theChoice = ((TextView) view).getText().toString();
                roomName.setVisibility(View.INVISIBLE);
                if(theChoice.equals("Choose a floor")){
                    chooseRoom.setSelected(false);
                    chooseRoom.setSelection(0,true);
                    return;
                }

                floorplanname.setText(fpname + " Floor " + theChoice);
                String chosenImage = fpname.replaceAll("\\s", "").toLowerCase() + theChoice;

                //Set the new floor plan image to reflect the user's choice
                int res = getResources().getIdentifier(chosenImage, "drawable", floorplan.this.getPackageName());
                floorPlanImage.setImageResource(res);
                floorselected = Integer.parseInt(theChoice);
                //Must subtract 1 since the array of choices begins with 0; this is used to set the right room
                //values for the room spinner menu
                int choice = floorselected;

                //After selecting first spinner, now the second one is populated
                spinner2drop.setVisibility(View.VISIBLE);
                chooseRoom.setVisibility(View.VISIBLE);

                List<String> spinnerArray = new ArrayList<String>();
                for(int j = 0; j < data.numberofBuildings; ++j) {
                    for(int k = 0; k < data.buildings.get(j).floors.size(); ++k) {
                        if(buildingselected == (j + 1) && data.buildings.get(j).floors.get(k).level == choice) {
                            for(int m = 0; m < data.buildings.get(j).floors.get(k).rooms.size(); ++m) {
                                spinnerArray.add(data.buildings.get(j).floors.get(k).rooms.get(m).roomName);
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(floorplan.this,  R.layout.spinner_layout, spinnerArray);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            chooseRoom.setAdapter(adapter);
                            break;
                        }
                    }
                }
                chooseRoom.setSelected(false);
                chooseRoom.setSelection(0,true);
                select();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
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
                theintent.putExtras(dataContainer);
                startActivity(theintent);
            }}
        );

        //Search button
        search = (Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent theintent = new Intent(floorplan.this, masterSearchWithHeaders.class);
                theintent.putExtras(dataContainer);
                floorplan.this.finish();
                startActivity(theintent);
            }}
        );
    }

    //Come back to fix this perhaps after room class stores images
    //Sets up pop up dialog
    private void createDialog()
    {
        dialog = new Dialog(floorplan.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.imagedialog);
        buildingLocation = (ImageView)dialog.findViewById(R.id.buildingLocation);

        String dialogImage = fpname.toLowerCase().replaceAll("\\s", "") + "highlighted";
        int imgid = getResources().getIdentifier(dialogImage, "drawable", getPackageName());
        buildingLocation.setImageResource(imgid);

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

                //Set the room title on the page to the user's choice
                roomName.setVisibility(View.VISIBLE);
                roomName.setText(selection);

                //Set the image on the page to reflect the user's choice
                    selection = selection.toLowerCase().replaceAll("\\s", "");
                    int resource = getResources().getIdentifier(selection, "drawable", floorplan.this.getPackageName());
                    floorPlanImage.setImageResource(resource);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }
}
