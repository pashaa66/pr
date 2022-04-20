package com.no.myapplication22222222;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class EventActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

       EventsAdapter adapter = new EventsAdapter(this, makeEvents());
        ListView lv =findViewById(R.id.listView);
        lv.setAdapter(adapter);
    }


    Events[] makeEvents() {
        Events[] arr = new Events[4];


        String[] eventsArr = {"Концерт", "Кино","Футбольный матч","Хоккейный матч"};
        String[] locationsArr = {"Театр", "Кинотеатр","Стадион","Стадион"};
        int[] startArr = {20, 13, 19, 22};


        for (int i = 0; i < arr.length; i++) {
            Events events = new Events();
            events.event = eventsArr[i];
            events.location = locationsArr[i];
            events.start = startArr[i];
            arr[i] = events;
        }
        return arr;
    }
}
