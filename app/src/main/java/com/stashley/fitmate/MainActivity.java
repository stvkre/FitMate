package com.stashley.fitmate;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.stashley.fitmate.activities.PostListActivity;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

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
                viewHolder.setDescription(model.getDescription());
                viewHolder.setLocation(model.getLocation());
                viewHolder.setCategory(model.getCategory());
                viewHolder.setDate(model.getDate());
                viewHolder.setImage(getApplicationContext(), model.getImage());

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
            TextView event_date = (TextView) mView.findViewById(R.id.event_date);
            event_date.setText(date);
        }


        public void setCategory (String category) {
            TextView event_category = (TextView) mView.findViewById(R.id.event_category);
            event_category.setText(category);
        }

        public void setImage(Context ctx,String image) {
            ImageView event_image = (ImageView) mView.findViewById(R.id.event_image);
            Picasso.with(ctx).load(image).into(event_image);

        }





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        if (item.getItemId() == R.id.action_add) {
            startActivity(new Intent(MainActivity.this, CreateEvent.class));
        }
        int id = item.getItemId();
        if (id == R.id.action_blog) {
            Intent intent = new Intent(MainActivity.this, PostListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}


