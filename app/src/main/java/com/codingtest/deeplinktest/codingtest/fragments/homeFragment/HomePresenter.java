package com.codingtest.deeplinktest.codingtest.fragments.homeFragment;

import com.codingtest.deeplinktest.codingtest.apiService.ResponseSubscriber;
import com.codingtest.deeplinktest.codingtest.apiService.model.Event;
import com.codingtest.deeplinktest.codingtest.apiService.model.EventAPIResponse;
import com.codingtest.deeplinktest.codingtest.apiService.repository.EventsRepository;
import com.codingtest.deeplinktest.codingtest.fragments.homeFragment.HomeFragmentMVP.PresenterOps;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class HomePresenter implements PresenterOps {
    private HomeFragmentMVP.RequiredViewOps fragment;
    private EventsRepository eventsRepository;

    private List<Event> events;

    HomePresenter(HomeFragmentMVP.RequiredViewOps fragment, EventsRepository eventsRepository) {
        this.fragment = fragment;
        this.eventsRepository = eventsRepository;
    }

    @Override
    public void onSwipeRefresh() {
        init();
    }

    @Override
    public void init() {
        loadEvents();
    }

    @Override
    public void floatingActionButtonOnClick() {
        filterByPlayLabel();
    }

    //I didn't use an Enum for those Labels they sounds like they will change or update all the time.
    //Just used a list of string to represent them.
    private void filterByPlayLabel() {
        Iterator<Event> iterator = events.iterator();
        while(iterator.hasNext()) {
            Event event = iterator.next();
            if(null != event && !event.labels.contains("play")) iterator.remove();
        }

        fragment.showEvents(events);
    }

    private void loadEvents() {
        fragment.showLoading();

        if (null == eventsRepository) {
            return;
        }
        eventsRepository.getEvents(new ResponseSubscriber<EventAPIResponse>() {
            @Override
            public void onError(String errorMessage) {
                handleLoadingError(errorMessage);
            }

            @Override
            public void onSuccess(EventAPIResponse eventAPIResponse) {
                handleLoadingSuccess(eventAPIResponse);
            }
        });
    }

    private void handleLoadingSuccess(EventAPIResponse eventAPIResponse) {
        if (null != eventAPIResponse && null != eventAPIResponse.events) {
            this.events = eventAPIResponse.events;
            sortByDate();
            filterByAvailableSeat();
            fragment.showEvents(events);
        }

        fragment.dismissLoading();
    }

    private void sortByDate() {
        Collections.sort(events);
    }

    private void filterByAvailableSeat() {
        Iterator<Event> iterator = events.iterator();
        while(iterator.hasNext()) {
            Event event = iterator.next();
            if(null != event && event.availableSeats == 0) iterator.remove();
        }
    }

    private void handleLoadingError(String errorMessage) {
        fragment.dismissLoading();
        fragment.handleError(errorMessage);
    }
}
