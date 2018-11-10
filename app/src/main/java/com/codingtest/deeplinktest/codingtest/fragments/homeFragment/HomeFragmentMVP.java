package com.codingtest.deeplinktest.codingtest.fragments.homeFragment;

import com.codingtest.deeplinktest.codingtest.apiService.model.Event;

import java.util.List;

public interface HomeFragmentMVP {
    /**
     * Presenter -> View
     */
    interface RequiredViewOps {
        void handleError(String errorMessage);

        void showEvents(List<Event> events);

        void showLoading();

        void dismissLoading();
    }


    /**
     * View -> Presenter
     */
    interface PresenterOps {
        void onSwipeRefresh();

        void init();

        void floatingActionButtonOnClick();
    }
}
