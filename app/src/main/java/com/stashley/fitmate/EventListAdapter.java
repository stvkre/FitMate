package com.stashley.fitmate;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by stephen on 7/12/17.
 */

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder>{

//    private List<LauncherActivity.ListItem> eventListItems;
    private List<EventListItem> eventListItems;
    private Context context;

    public EventListAdapter(List<EventListItem> eventListItems, Context context) {
        this.eventListItems = eventListItems;
        this.context = context;
    }

    // calling viewholder function

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        EventListItem listItem = eventListItems.get(position);

        holder.textViewHead.setText(listItem.getTitle());
        holder.textViewDescription.setText(listItem.getDescription());

    }

    @Override
    public int getItemCount() {
        return eventListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewHead;
        public TextView textViewDescription;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewHead = (TextView) itemView.findViewById(R.id.textViewHead);
            textViewDescription = (TextView) itemView.findViewById(R.id.textViewDescription);
        }
    }
}
