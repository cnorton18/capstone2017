package com.groupc.officelocator;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatCallback;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class masterSearchWithHeaders extends mapstorage {
    private ListView allSearchResults;
    private EditText searchBar;
    private String choice, fpname, floorNumber;
    private int floorCode;
    private int [] numberOfFloors = {2,2}; //Mia Hamm, Tiger Wood # of floors

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) {
            actionBar.hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mastersearch);
        floorplan.fromSearch = 1;

        searchBar = (EditText) findViewById(R.id.searchBar);
        allSearchResults = (ListView) findViewById(R.id.searchList);
        allSearchResults.setEmptyView(findViewById(R.id.empty));

        ArrayList<SearchItem> campusList = new ArrayList<masterSearchWithHeaders.SearchItem>();
        String [] buildings = getResources().getStringArray(R.array.Buildings);
        String [] searchResultValues; //grabbed from Hash map
        for (int i = 0; i < buildings.length; ++i){
            campusList.add(new BuildingName(buildings[i])); // Add building header ("Mia Hamm", "Tiger Woods")
            searchResultValues = campusMap.get(buildings[i]); //Add search results for each building ("Mia Hamm 1, Mia Hamm 1 Flyknit"...)
            for (int j = 0; j < searchResultValues.length; ++j){
                campusList.add(new RoomName(searchResultValues[j]));
            }
        }

        // set adapter
        final CampusAdapter adapter = new CampusAdapter(this, campusList);
        allSearchResults.setAdapter(adapter);
        allSearchResults.setTextFilterEnabled(true);

        //What to do when the user clicks on a search result
        allSearchResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                //Gets object at the position
                SearchItem object = (SearchItem) allSearchResults.getItemAtPosition(position);
                choice = object.getName(); //Gets the name of the object at the position
                //Toast.makeText(v.getContext(), choice, Toast.LENGTH_LONG).show(); - For debugging
                /*if (choice.contains("Mia Hamm")) {
                    fpname = "Mia Hamm";
                    floorCode = 0; //Set to match the spinner values in the campus and floorplan class
                }
                else if(choice.contains("Tiger Woods")){
                    fpname = "Tiger Woods";
                    floorCode = 1;
                }*/
                String floors[] = campusMap.keySet().toArray(new String[0]);
                for(int i = 0; i < floors.length; ++i){
                    if(choice.contains(floors[i])){
                        fpname = floors[i];
                        floorCode = i;
                        break;
                    }
                }
                Intent goToFloorPlan = new Intent(masterSearchWithHeaders.this, floorplan.class);
                //If the user clicks a section header ("Mia Hamm"/"Tiger Woods")
                if(object.isSection()){
                    goToFloorPlan.putExtra("imageName",fpname.toLowerCase().replaceAll("\\s",""));
                    goToFloorPlan.putExtra("floorNumber", "0");
                    goToFloorPlan.putExtra("roomName", "");
                }
                //If the user clicks a non section header ("Mia Hamm 1" or "Mia Hamm Flyknit")
                else{
                    floorNumber = choice.replaceAll("\\D+",""); //Grab the floor # from the search result
                    floorplan.buildingselected = floorCode + 1; //Used in floorplan class
                    //If there's no room in the result
                    if(choice.equals(fpname + " " + floorNumber)) {
                        goToFloorPlan.putExtra("imageName", choice.toLowerCase().replaceAll("\\s", ""));
                    }
                    //If there's a room in the result
                    else{
                        floorplan.setRoomfromSearch = 1;
                        goToFloorPlan.putExtra("roomName", choice.replaceAll(fpname + " " + floorNumber + " ",""));
                        goToFloorPlan.putExtra("imageName", choice.replaceAll(fpname + " " + floorNumber + " ","").toLowerCase().replaceAll("\\s",""));
                    }
                    goToFloorPlan.putExtra("floorNumber", floorNumber);
                }
                goToFloorPlan.putExtra("fpname", fpname);
                goToFloorPlan.putExtra("spinnerNumber", floorCode);
                goToFloorPlan.putExtra("numberOfFloors", numberOfFloors[floorCode]);
                startActivity(goToFloorPlan);
            }

        });

        //What to do when the user enters input into the search bar
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //If the user enters in text, filter the result
                if(adapter != null)
                    adapter.getFilter().filter(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    //WHERE WE DEFINE THE SEARCH ITEMS THAT WILL POPULATE THE SEARCH RESULT LISTVIEW
    //Search result items can be either a section header or a regular search result
    public interface SearchItem {
        public boolean isSection();
        public String getName();
    }

    //Listview Section Headers - Buildings
    public class BuildingName implements SearchItem {
        private final String name;
        public BuildingName(String name) {this.name = name;}
        public String getName() {return name;}
        @Override
        public boolean isSection() {return true;}
    }

    //Listview Nonsection-Header results - Rooms
    public class RoomName implements SearchItem {
        public final String name;
        public RoomName(String name) {this.name = name;}
        public String getName() {return name;}
        @Override
        public boolean isSection() {return false;}
    }

    //Custom "CampusAdapter"
    public class CampusAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<SearchItem> searchItem;
        private ArrayList<SearchItem> originalSearchItem;
        public CampusAdapter(Context context, ArrayList<SearchItem> searchItem) {
            this.context = context;
            this.searchItem = searchItem;
        }

        //Supporting methods
        @Override
        public int getCount() {return searchItem.size();}
        @Override
        public Object getItem(int position) {return searchItem.get(position);}
        @Override
        public long getItemId(int position) {return position;}

        @Override
        public View getView(final int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (searchItem.get(position).isSection()) {
                //If the item in the listview search result is a section header...
                convertView = inflater.inflate(R.layout.list_header, parent, false);
                TextView headerName = (TextView) convertView.findViewById(R.id.searchHeader);
                headerName.setText(((BuildingName) searchItem.get(position)).getName());
            }
            else
            {
                //If the item in the listview search result is not a section header...
                convertView = inflater.inflate(R.layout.list_items, parent, false);
                final TextView searchResult = (TextView) convertView.findViewById(R.id.searchItems);
                searchResult.setText(((RoomName) searchItem.get(position)).getName());
            }
            return convertView;
        }


        //Filtering the search results
        public Filter getFilter()
        {
            Filter filter = new Filter() {
                @SuppressWarnings("unchecked")
                @Override
                protected void publishResults(CharSequence userEnteredString, FilterResults results) {
                    searchItem = (ArrayList<SearchItem>) results.values;
                    notifyDataSetChanged();
                }

                @SuppressWarnings("null")
                @Override
                protected FilterResults performFiltering(CharSequence userEnteredString) {
                    FilterResults results = new FilterResults();
                    ArrayList<SearchItem> filteredArrayList = new ArrayList<SearchItem>();

                    if(originalSearchItem == null || originalSearchItem.size() == 0)
                        originalSearchItem = new ArrayList<SearchItem>(searchItem);

                    //if userEnteredString is null then we return the original value, else return the filtered value
                    if(userEnteredString == null && userEnteredString.length() == 0){
                        results.count = originalSearchItem.size();
                        results.values = originalSearchItem;
                    }
                    else {
                        userEnteredString = userEnteredString.toString().toLowerCase(Locale.ENGLISH);
                        for (int i = 0; i < originalSearchItem.size(); i++)
                        {
                            String name = originalSearchItem.get(i).getName().toLowerCase(Locale.ENGLISH);
                            if(name.contains(userEnteredString.toString()))
                                filteredArrayList.add(originalSearchItem.get(i));
                        }
                        results.count = filteredArrayList.size();
                        results.values = filteredArrayList;
                    }
                    return results;
                }
            };
            return filter;
        }
    }
}
