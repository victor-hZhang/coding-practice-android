package com.codingtest.deeplinktest.codingtest.fragments.homeFragment.kotlin

import com.codingtest.deeplinktest.codingtest.apiService.ResponseSubscriber
import com.codingtest.deeplinktest.codingtest.apiService.model.Event
import com.codingtest.deeplinktest.codingtest.apiService.model.EventAPIResponse
import com.codingtest.deeplinktest.codingtest.apiService.repository.EventsRepository
import com.codingtest.deeplinktest.codingtest.fragments.homeFragment.HomeFragmentMVP

class HomePresenterKotlin constructor(private val fragment: HomeFragmentMVP.RequiredViewOps, private val eventsRepository: EventsRepository) : HomeFragmentMVP.PresenterOps{
    private var events: List<Event>? = null

    override fun onSwipeRefresh() {
        init()
    }

    override fun init() {
        loadEvents()
    }

    private fun loadEvents() {
        fragment.showLoading()

        eventsRepository.getEvents(object : ResponseSubscriber<EventAPIResponse> {
            override fun onError(errorMessage: String) {
                handleLoadingError(errorMessage)
            }

            override fun onSuccess(eventAPIResponse: EventAPIResponse) {
                handleLoadingSuccess(eventAPIResponse)
            }
        })
    }

    private fun sortByDate() {
        events?.sortedBy { it.dateTime }
    }

    private fun filterByAvailableSeat() {
        events?.filter {  it.availableSeats > 0}
    }

    private fun handleLoadingSuccess(eventAPIResponse: EventAPIResponse?) {
        if(null != eventAPIResponse) {
            sortByDate()
            filterByAvailableSeat()
            val eventsList = events?.let { it } ?: return
            fragment.showEvents(eventsList)
            fragment.dismissLoading()
        }
    }

    private fun handleLoadingError(errorMessage: String) {
        fragment.dismissLoading()
        fragment.handleError(errorMessage)
    }

    override fun floatingActionButtonOnClick() {
        filterByPlayLabel()
    }

    private fun filterByPlayLabel() {
        events?.filter {  it.labels.contains("play")}
    }
}