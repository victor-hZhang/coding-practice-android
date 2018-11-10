package com.codingtest.deeplinktest.codingtest.fragments.homeFragment;

import com.codingtest.deeplinktest.codingtest.apiService.ResponseSubscriber;
import com.codingtest.deeplinktest.codingtest.apiService.model.Event;
import com.codingtest.deeplinktest.codingtest.apiService.model.EventAPIResponse;
import com.codingtest.deeplinktest.codingtest.apiService.repository.EventsRepository;
import com.codingtest.deeplinktest.codingtest.fragments.homeFragment.HomeFragmentMVP.PresenterOps;

import java.util.List;

public class HomePresenter implements PresenterOps {
    private final HomeFragmentMVP.RequiredViewOps fragment;
    private final EventsRepository eventsRepository;
    private List<Event> events;

    HomePresenter(HomeFragmentMVP.RequiredViewOps fragment, EventsRepository eventsRepository) {
        this.fragment = fragment;
        this.eventsRepository = eventsRepository;
    }

    @Override
    public void onSwipeRefresh() {

    }

    @Override
    public void init() {
        loadEvents();
    }

    @Override
    public void floatingActionButtonOnClick() {

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
            fragment.showEvents(events);
        }

        fragment.dismissLoading();
    }

    private void handleLoadingError(String errorMessage) {
        fragment.dismissLoading();
        fragment.handleError(errorMessage);
    }
}
