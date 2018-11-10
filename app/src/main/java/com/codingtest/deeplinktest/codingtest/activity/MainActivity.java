package com.codingtest.deeplinktest.codingtest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.codingtest.deeplinktest.codingtest.R;
import com.codingtest.deeplinktest.codingtest.fragments.homeFragment.HomeFragment;

public class MainActivity extends AppCompatActivity {
    //Using a single Activity app, all logic in Fragments
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showDefaultFragment();
    }

    //This can be moved to a Util, putting it here to make everything simple.
    private void showDefaultFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, HomeFragment.newInstance())
                .addToBackStack(HomeFragment.class.getSimpleName())
                .commit();
    }
}
