package com.codingtest.deeplinktest.codingtest.fragments.homeFragment.kotlin

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codingtest.deeplinktest.codingtest.R
import com.codingtest.deeplinktest.codingtest.apiService.model.Event
import com.codingtest.deeplinktest.codingtest.apiService.repository.EventsAPIRepository
import com.codingtest.deeplinktest.codingtest.fragments.homeFragment.EventListAdapter
import com.codingtest.deeplinktest.codingtest.fragments.homeFragment.HomeFragmentMVP
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragmentKotlin : Fragment(), HomeFragmentMVP.RequiredViewOps {
    companion object {
        fun newInstance(): HomeFragmentKotlin {
            return HomeFragmentKotlin()
        }
    }

    private var presenter: HomeFragmentMVP.PresenterOps? = null
    private var eventListAdapter: EventListAdapter? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.presenter = HomePresenterKotlin(this, EventsAPIRepository())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        event_list.setHasFixedSize(true)

        event_list.layoutManager = GridLayoutManager(context, 2)

        eventListAdapter = EventListAdapter()
        event_list.adapter = eventListAdapter

        swipe_refresh_layout.setOnRefreshListener {  this.presenter?.onSwipeRefresh()}

        this.presenter?.init()
    }

    override fun handleError(errorMessage: String) {
        //Ideally you want wrap this around, just doing this as a demo,
        //also normally you don't want to display the raw error message to customer.
        //Please see ResponseSubscriber for detail.
        if (isAdded) {
            val view = activity?.findViewById<View>(R.id.coordinatorLayout)

            if (null != view) {
                val snackbar = Snackbar.make(coordinatorLayout, errorMessage, Snackbar.LENGTH_SHORT)
                snackbar.view.setBackgroundColor(ContextCompat.getColor(context!!, R.color.roseRed))
                snackbar.show()
            }
        }
    }

    override fun showEvents(events: List<Event>) {
        this.eventListAdapter?.updateData(events)
    }

    override fun showLoading() {
        //Just borrowed SwipeRefreshLayout for quick loading animation.
        //Could also use a ProgressDialog
        swipe_refresh_layout.isRefreshing = true
    }

    override fun dismissLoading() {
        swipe_refresh_layout.isRefreshing = false
    }
}