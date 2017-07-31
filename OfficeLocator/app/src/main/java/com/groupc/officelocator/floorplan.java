package com.groupc.officelocator;

<<<<<<< HEAD
=======
import android.app.Dialog;
>>>>>>> master
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
<<<<<<< HEAD
=======
import android.view.Window;
>>>>>>> master
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

<<<<<<< HEAD
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


=======
import java.util.ArrayList;
import java.util.List;
>>>>>>> master

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floorplan);

<<<<<<< HEAD
        Intent goToFloorPlan = getIntent();

        //Displaying the correct building name and floorplan image
        //Setting floor plan name
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
=======
public class floorplan extends AppCompatActivity{

    public static int buildingselected = 0; //Tracks which building
    public static int setRoomfromSearch = 0; //Determines if a room was chosen in Search
    public static int floorselected = 0; //Determines if a floor number was chosen in Search or through Spinner
    public static int fromSearch = 0; //Determines if the previous page was Search before coming to the floorplan page
>>>>>>> master

    Button maplocationbut, home, search;
    ImageView floorPlanImage, buildingLocation, spinner2drop;
    private Spinner chooseFloor, chooseRoom;

<<<<<<< HEAD
        int spinnerNumber = goToFloorPlan.getIntExtra("spinnerNumber",0);
        chooseRoom = (Spinner) findViewById(R.id.roomSelector);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, buildingNames[spinnerNumber], android.R.layout.simple_spinner_item);
        chooseRoom.setAdapter(adapter);
        chooseRoom.setOnItemSelectedListener(this);
        chooseRoom.setSelection(0);
=======
    Dialog dialog;
    TextView cancel, floorplanname, roomName;
    String fpname, imageName;
>>>>>>> master

    //Provides the room choices for each building for the 2nd spinner that chooses rooms
    int[] miaHamm = {R.array.miaHamm1, R.array.miaHamm2};
    int[] tigerWoods = {R.array.tigerWoods1, R.array.tigerWoods2};

<<<<<<< HEAD
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

=======
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floorplan);
        final Intent goToFloorPlan = getIntent();

        spinner2drop = (ImageView)findViewById(R.id.imageView10); //Dropdown arrow for 2nd spinner

        //Room title on the floorplan page
        roomName = (TextView) findViewById(R.id.roomName);
        Typeface myCustomfont = Typeface.createFromAsset(getAssets(), "fonts/newsgothiccondensedbold.ttf");
        roomName.setTypeface(myCustomfont);

        //If a room was chosen through search, must cause the room title to appear since by default it doesn't until you choose the
        //room through the second spinner
        if(setRoomfromSearch == 1){
            roomName.setText(goToFloorPlan.getStringExtra("roomName"));
            roomName.setVisibility(View.VISIBLE);
        }

        //Setting floor plan title name + font style
        fpname = goToFloorPlan.getStringExtra("fpname");
        floorplanname = (TextView) findViewById(R.id.floorPlanName);
        floorplanname.setTypeface(myCustomfont);
        String floorNumber = goToFloorPlan.getStringExtra("floorNumber");
        //If the floor plan title has a floor number, we add that to the title
        if(Integer.parseInt(floorNumber) == 0) //For those without a floor number ("Mia Hamm"/"Tiger Woods"
        //the default is the first floor
            floorNumber = "1";
        floorplanname.setText(fpname + " Floor " + floorNumber);

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

        //Creating the two spinner drop down menus that choose the floor and rooms
        //Choosing a floor in the first spinner causes the second spinner to be visible
        //The choice of the floor also determines the choices of rooms for the second spinner
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

            //Choose the correct choices for the second room spinner
            if(floor > 0) {
                //Mia Hamm
                if (buildingselected == 1) {
                    ArrayAdapter adapter = ArrayAdapter.createFromResource(floorplan.this, miaHamm[floor - 1], R.layout.spinner_layout);
                    chooseRoom.setAdapter(adapter);
                }
                //Tiger Woods
                else if (buildingselected == 2) {
                    ArrayAdapter adapter = ArrayAdapter.createFromResource(floorplan.this, tigerWoods[floor - 1], R.layout.spinner_layout);
                    chooseRoom.setAdapter(adapter);
                }
            }
            chooseRoom.setSelected(false);
            chooseRoom.setSelection(0,true);
            select();

            //If the room was also chosen through the search result chosen, we choose that value to
            //appear in the second spinner for the rooms
            if(setRoomfromSearch==1){
                chooseRoom.setSelected(true);
                int selection = 0;
                String chosenRoomFromSearch = goToFloorPlan.getStringExtra("roomName");
                if(chosenRoomFromSearch != null) {
                    if (chosenRoomFromSearch.matches("Flyknit|LunarCharge|Air Jordan|Pegasus"))
                        selection = 1;
                    else if (chosenRoomFromSearch.matches("Air Max|Kobe Mamba|Roshe|VaporMax"))
                        selection = 2;
                }
                chooseRoom.setSelection(selection,true);
            }
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
                startActivity(theintent);
            }}
        );

        //Search button
        search = (Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent theintent = new Intent(floorplan.this, masterSearchWithHeaders.class);
                startActivity(theintent);
            }}
        );
>>>>>>> master
    }

    //Sets up pop up dialog
    private void createDialog()
    {
        dialog = new Dialog(floorplan.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.imagedialog);
        buildingLocation = (ImageView)dialog.findViewById(R.id.buildingLocation);
        //Mia Hamm
        if(buildingselected == 1)
            buildingLocation.setImageResource(R.drawable.miahammhighlighted);
        //Tiger Woods
        else if (buildingselected == 2)
            buildingLocation.setImageResource(R.drawable.tigerwoodshighlighted);
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
                selection = selection.toLowerCase().replaceAll("\\s","");
                int resource = getResources().getIdentifier(selection, "drawable", floorplan.this.getPackageName());
                floorPlanImage.setImageResource(resource);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }



    @Override
    public void onNothingSelected(AdapterView<?> adapterView){
    }

    public void onResume(){
        super.onResume();
        chooseRoom.setSelection(0);//Resets spinner choice when going back to this page (e.g. back button press)
    }

<<<<<<< HEAD



=======
>>>>>>> master
}
