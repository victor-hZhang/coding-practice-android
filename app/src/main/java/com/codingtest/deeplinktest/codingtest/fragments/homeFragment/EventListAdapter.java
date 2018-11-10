package com.codingtest.deeplinktest.codingtest.fragments.homeFragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codingtest.deeplinktest.codingtest.R;
import com.codingtest.deeplinktest.codingtest.apiService.model.Event;

import java.util.ArrayList;
import java.util.List;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventViewHolder>{
    protected List<Event> events = new ArrayList<>();

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EventViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_event, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        holder.initView(events.get(position));
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public void updateData(List<Event> events) {
        if(null != events) {
            this.events = events;

            this.notifyDataSetChanged();
        }
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {
        public TextView mEventNameTextView;

        public EventViewHolder(View itemView) {
            super(itemView);

            this.mEventNameTextView = itemView.findViewById(R.id.event_name_text);
        }


        public void initView(Event event) {
            setEventNameText(event.name);
        }

        private void setEventNameText(String eventName) {
            if(null != mEventNameTextView) {
                mEventNameTextView.setText(eventName);
            }
        }
    }
}
