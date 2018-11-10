package com.codingtest.deeplinktest.codingtest.apiService;

import com.codingtest.deeplinktest.codingtest.apiService.model.EventAPIResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EventService {
    @GET("/bridj-coding-challenge/events.json")
    Call<EventAPIResponse> getEvents();
}
