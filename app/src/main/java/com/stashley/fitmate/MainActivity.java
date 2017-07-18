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



import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    private List<EventListItem> eventListItems;

    private RecyclerView mEvent_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


