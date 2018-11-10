package com.codingtest.deeplinktest.codingtest.apiService.repository;

import com.codingtest.deeplinktest.codingtest.apiService.ResponseSubscriber;
import com.codingtest.deeplinktest.codingtest.apiService.RetrofitInitializer;
import com.codingtest.deeplinktest.codingtest.apiService.model.EventAPIResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsAPIRepository implements EventsRepository {
    //This seems like a over kill, mainly to break off the dependency.
    //We havn't found a good way to unit testing Rx-java in complex code environment.
    @Override
    public void getEvents(final ResponseSubscriber subscriber) {
        Call<EventAPIResponse> call = RetrofitInitializer.getEventsService().getEvents();

        call.enqueue(new Callback<EventAPIResponse>() {
            @Override
            public void onResponse(Call<EventAPIResponse> call, Response<EventAPIResponse> response) {
                subscriber.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<EventAPIResponse> call, Throwable t) {
                if(null != t) {
                    subscriber.onError(t.getMessage());
                }
            }
        });
    }
}
