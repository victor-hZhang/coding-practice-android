package com.codingtest.deeplinktest.codingtest.apiService.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventAPIResponse {
    @SerializedName("events")
    public List<Event> events;
}
