package com.codingtest.deeplinktest.codingtest.apiService.repository;

import com.codingtest.deeplinktest.codingtest.apiService.ResponseSubscriber;
import com.codingtest.deeplinktest.codingtest.apiService.model.EventAPIResponse;
import com.google.gson.Gson;

/**
 * This class is for testing use only
 */
public class EventsStubRepository implements EventsRepository {
    @Override
    public void getEvents(ResponseSubscriber subscriber) {
        Gson gson = new Gson();
        EventAPIResponse response = gson.fromJson(json, EventAPIResponse.class);
        subscriber.onSuccess(response);
    }

    //I know this is ugly, quick and nice to get things done,
    //There are ways to add json as resource file and load it directly for testing.
    //Just request a bit of setup.
    private String json = "{  \n" +
            "   \"events\":[  \n" +
            "      {  \n" +
            "         \"name\":\"Othello\",\n" +
            "         \"date\":\"2017-11-07T20:00:00Z\",\n" +
            "         \"available_seats\":123,\n" +
            "         \"price\":75.0,\n" +
            "         \"venue\":\"Awesome Theatre\",\n" +
            "         \"labels\":[  \n" +
            "            \"play\",\n" +
            "            \"shakespeare\"\n" +
            "         ]\n" +
            "      },\n" +
            "      {  \n" +
            "         \"name\":\"The Wizard of Oz\",\n" +
            "         \"date\":\"2017-12-20T21:00:00Z\",\n" +
            "         \"available_seats\":15,\n" +
            "         \"price\":150.0,\n" +
            "         \"venue\":\"Awesome Theatre\",\n" +
            "         \"labels\":[  \n" +
            "            \"musical\"\n" +
            "         ]\n" +
            "      },\n" +
            "      {  \n" +
            "         \"name\":\"Guns N' Roses\",\n" +
            "         \"date\":\"2017-11-07T21:00:00Z\",\n" +
            "         \"available_seats\":345,\n" +
            "         \"price\":45.0,\n" +
            "         \"venue\":\"A Stadium\",\n" +
            "         \"labels\":[  \n" +
            "            \"concert\"\n" +
            "         ]\n" +
            "      },\n" +
            "      {  \n" +
            "         \"name\":\"The Nutcracker\",\n" +
            "         \"date\":\"2017-11-10T19:00:00Z\",\n" +
            "         \"available_seats\":64,\n" +
            "         \"price\":75.0,\n" +
            "         \"venue\":\"Awesome Theatre\",\n" +
            "         \"labels\":[  \n" +
            "            \"ballet\"\n" +
            "         ]\n" +
            "      },\n" +
            "      {  \n" +
            "         \"name\":\"Queensland Orchestra\",\n" +
            "         \"date\":\"2017-12-02T19:00:00Z\",\n" +
            "         \"available_seats\":78,\n" +
            "         \"price\":50.0,\n" +
            "         \"venue\":\"Concert Hall\",\n" +
            "         \"labels\":[  \n" +
            "            \"concert\",\n" +
            "            \"classical\"\n" +
            "         ]\n" +
            "      },\n" +
            "      {  \n" +
            "         \"name\":\"Rythms of Ireland\",\n" +
            "         \"date\":\"2017-12-11T20:00:00Z\",\n" +
            "         \"available_seats\":0,\n" +
            "         \"price\":40.0,\n" +
            "         \"venue\":\"Awesome Theatre\",\n" +
            "         \"labels\":[  \n" +
            "            \"dance\"\n" +
            "         ]\n" +
            "      },\n" +
            "      {  \n" +
            "         \"name\":\"Hamlet\",\n" +
            "         \"date\":\"2017-11-08T20:00:00Z\",\n" +
            "         \"available_seats\":21,\n" +
            "         \"price\":150.0,\n" +
            "         \"venue\":\"Awesome Theatre\",\n" +
            "         \"labels\":[  \n" +
            "            \"play\",\n" +
            "            \"shakespeare\"\n" +
            "         ]\n" +
            "      }\n" +
            "   ]\n" +
            "}";
}
