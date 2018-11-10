package com.codingtest.deeplinktest.codingtest.fragments.homeFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codingtest.deeplinktest.codingtest.R;
import com.codingtest.deeplinktest.codingtest.apiService.model.Event;
import com.codingtest.deeplinktest.codingtest.apiService.repository.EventsAPIRepository;

import java.util.List;

public class HomeFragment extends Fragment implements HomeFragmentMVP.RequiredViewOps {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mEventRecyclerView;
    private EventListAdapter mEventAdapter;

    private HomeFragmentMVP.PresenterOps presenter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.presenter = new HomePresenter(this, new EventsAPIRepository());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.mEventRecyclerView = view.findViewById(R.id.event_list);

        initRecyclerView();

        initSwipeRefresh(view);

        initFloatingActionButton(view);

        this.presenter.init();
    }

    private void initFloatingActionButton(View view) {
        FloatingActionButton floatingActionButton = view.findViewById(R.id.floating_action_button);

        if(null != floatingActionButton){
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenter.floatingActionButtonOnClick();
                }
            });
        }
    }

    private void initSwipeRefresh(@NonNull View view) {
        //SwipeDown to Refresh
        this.mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        this.mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onSwipeRefresh();
            }
        });
    }

    private void initRecyclerView() {
        if (null == mEventRecyclerView) {
            return;
        }
        //This will improve performance.
        this.mEventRecyclerView.setHasFixedSize(true);

        //Layout Manager
        this.mEventRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        //Adapter
        this.mEventAdapter = new EventListAdapter();
        this.mEventRecyclerView.setAdapter(mEventAdapter);
    }

    @Override
    public void handleError(String errorMessage) {
        //Ideally you want wrap this around, just doing this as a demo,
        //also normally you don't want to display the raw error message to customer.
        //Please see ResponseSubscriber for detail.
        if (isAdded() && null != getActivity()) {
            View view = getActivity().findViewById(R.id.coordinatorLayout);

            if (null != view) {
                Snackbar snackbar = Snackbar.make(view, errorMessage, Snackbar.LENGTH_SHORT);
                snackbar.getView().setBackgroundColor(getResources().getColor(R.color.roseRed));
                snackbar.show();
            }
        }
    }

    @Override
    public void showEvents(List<Event> events) {
        if (null != this.mEventAdapter) {
            this.mEventAdapter.updateData(events);
        }
    }

    @Override
    public void showLoading() {
        //Just borrowed SwipeRefreshLayout for quick loading animation.
        //Could also use a ProgressDialog
        if (null != mSwipeRefreshLayout) {
            this.mSwipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void dismissLoading() {
        if (null != mSwipeRefreshLayout) {
            this.mSwipeRefreshLayout.setRefreshing(false);
        }
    }
}
