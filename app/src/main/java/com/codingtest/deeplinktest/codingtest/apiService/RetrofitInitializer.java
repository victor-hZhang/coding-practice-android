package com.codingtest.deeplinktest.codingtest.apiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInitializer {
    private static String baseUrl = "https://s3-ap-southeast-2.amazonaws.com";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static EventService getEventsService() {
        return retrofit.create(EventService.class);
    }
}
