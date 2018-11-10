package com.codingtest.deeplinktest.codingtest.apiService.repository;

import com.codingtest.deeplinktest.codingtest.apiService.ResponseSubscriber;

public interface EventsRepository {
    void getEvents(ResponseSubscriber subscriber);
}
