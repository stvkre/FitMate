package com.stashley.fitmate;


import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    private List<EventListItem> eventListItems;

    private RecyclerView mEvent_list;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("events");

        mEvent_list = (RecyclerView) findViewById(R.id.event_list);
        mEvent_list.setHasFixedSize(true);
        mEvent_list.setLayoutManager(new LinearLayoutManager(this));


//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
//        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
//
//        mDrawerLayout.addDrawerListener(mToggle);
//        mToggle.syncState();
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//
//        eventListItems = new ArrayList<>();
//
//        for(int i = 0; i<=10; i++) {
//            EventListItem listItem = new EventListItem("fitmate" + i+1,"description");
//            eventListItems.add(listItem);
//        }
//
//
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (mToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Event, EventViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Event, EventViewHolder>(
                Event.class,
                R.layout.event_row,
                EventViewHolder.class,
                mDatabase

        ) {
            @Override
            protected void populateViewHolder(EventViewHolder viewHolder, Event model, int position) {

                viewHolder.setTitle(model.getTitle());
                viewHolder.setDate(model.getDate());
                viewHolder.setDescription(model.getDescription());
                viewHolder.setLocation(model.getLocation());

            }
        };

        mEvent_list.setAdapter(firebaseRecyclerAdapter);
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public EventViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
        }

        // setting variable to place holders

        public void setTitle (String title) {
            TextView event_title = (TextView) mView.findViewById(R.id.event_title);
            event_title.setText(title);
        }

        public void setDescription (String description) {
            TextView event_description = (TextView) mView.findViewById(R.id.event_desc);
            event_description.setText(description);
        }

        public void setLocation (String location) {
            TextView event_location = (TextView) mView.findViewById(R.id.event_location);
            event_location.setText(location);
        }

        public void setDate (String date) {
            TextView event_date = (TextView) mView.findViewById(R.id.event_Date);
            event_date.setText(date);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_add) {
            startActivity(new Intent(MainActivity.this, CreateEvent.class));
        }
        return super.onOptionsItemSelected(item);
    }
}


