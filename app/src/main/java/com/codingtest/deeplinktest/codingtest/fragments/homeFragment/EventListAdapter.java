package com.codingtest.deeplinktest.codingtest.fragments.homeFragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codingtest.deeplinktest.codingtest.R;
import com.codingtest.deeplinktest.codingtest.apiService.model.Event;
import com.codingtest.deeplinktest.codingtest.utility.CurrencyUtil;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventViewHolder>{
    private List<Event> events = new ArrayList<>();

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

    void updateData(List<Event> events) {
        if(null != events) {
            this.events = events;

            this.notifyDataSetChanged();
        }
    }

    class EventViewHolder extends RecyclerView.ViewHolder {
        TextView mEventNameTextView;
        TextView mEventPriceTextView;
        TextView mEventVenueTextView;
        ImageView mEventPosterImageView;

        EventViewHolder(View itemView) {
            super(itemView);

            this.mEventNameTextView = itemView.findViewById(R.id.event_name_text);
            this.mEventPriceTextView = itemView.findViewById(R.id.event_price_text);
            this.mEventVenueTextView = itemView.findViewById(R.id.event_venue_text);
            this.mEventPosterImageView = itemView.findViewById(R.id.event_poster_image);
        }


        void initView(Event event) {
            setEventNameText(event.name);
            setEventPriceText(event.price);
            setEventVenueText(event.venue);
            loadEventPosterImage();
        }

        private void loadEventPosterImage() {
            if(null != mEventPosterImageView) {
                Picasso.get()
                        .load(getRandomImageUrl())
                        .error(R.drawable.default_background)
                        .into(mEventPosterImageView);
            }
        }

        private void setEventNameText(String eventName) {
            if(null != mEventNameTextView) {
                mEventNameTextView.setText(eventName);
            }
        }

        private void setEventPriceText(BigDecimal price) {
            if(null != mEventPriceTextView) {
                mEventPriceTextView.setText(CurrencyUtil.formatCurrency(price, 0, 2));
            }
        }

        private void setEventVenueText(String eventVenueText) {
            if(null != mEventVenueTextView) {
                mEventVenueTextView.setText(eventVenueText);
            }
        }

        String getRandomImageUrl() {
            //This is a bit hacky :) just wanna grab a quick image
            return "https://picsum.photos/300?image=" + new Random().nextInt(500);
        }
    }
}
