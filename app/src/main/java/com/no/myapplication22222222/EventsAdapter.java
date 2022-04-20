package com.no.myapplication22222222;

import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
public class EventsAdapter extends ArrayAdapter<Events> {
    public EventsAdapter(Context context, Events[] arr) {
        super(context, R.layout.activity_events, arr);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Events events = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_events, null);
        }
        ((TextView) convertView.findViewById(R.id.textEvent)).setText(events.event);
        ((TextView) convertView.findViewById(R.id.textLocation)).setText(String.valueOf(events.location));
        ((TextView) convertView.findViewById(R.id.textStart)).setText(String.valueOf(events.start));
        return convertView;
    }
}
