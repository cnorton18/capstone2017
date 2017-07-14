package com.groupc.officelocator;

import android.graphics.Typeface;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

public class directions extends AppCompatActivity {

    ImageView chosenRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions);
        Intent goToRoom = getIntent();

        //Setting chosen room name
        String room = goToRoom.getStringExtra("roomName");
        TextView floorplanname = (TextView) findViewById(R.id.roomName);
        Typeface myCustomfont = Typeface.createFromAsset(getAssets(), "fonts/newsgothiccondensedbold.ttf");
        floorplanname.setTypeface(myCustomfont);
        floorplanname.setText(room);

        //Setting chosen room highlighted image
        room = room.replaceAll("\\s+","");
        room = room.toLowerCase();
        int res = getResources().getIdentifier(room, "drawable", this.getPackageName());
        chosenRoom = (ImageView) findViewById(R.id.chosenRoom);
        chosenRoom.setImageResource(res);
    }
    private void exitit() {
        ActivityCompat.finishAffinity(this);
    }

}