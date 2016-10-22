package com.jzheadley.eat.ui.nearbyrestaurants.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jzheadley.eat.R;
import com.jzheadley.eat.data.models.Restaurant;
import com.jzheadley.eat.data.models.services.RestaurantService;
import com.jzheadley.eat.ui.adapters.RestaurantsListAdapter;
import com.jzheadley.eat.ui.base.view.BaseActivity;
import com.jzheadley.eat.ui.nearbyrestaurants.presenter.NearbyRestaurantsPresenter;

import java.util.List;


public class NearbyRestaurantActivity extends BaseActivity {
    private static final String TAG = "NearbyRestaurantActivit";

    private RestaurantsListAdapter mRestaurantsListAdapter;
    private NearbyRestaurantsPresenter mNearbyRestaurantsPresenter;
    private RestaurantService mRestaurantService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_restaurants);
        mRestaurantService = new RestaurantService();
        mNearbyRestaurantsPresenter = new NearbyRestaurantsPresenter(this, mRestaurantService);

        if (mNearbyRestaurantsPresenter.isNetworkAvailable()) {
            mNearbyRestaurantsPresenter.loadRestaurants();
        } else {
            Snackbar.make(findViewById(R.id.toolbar), "You are disconnected from the network. "
                    + "Please resolve your connection issues and try again.", Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mNearbyRestaurantsPresenter.loadRestaurants();
    }

    public void displayRestaurants(List<Restaurant> restaurants) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.restaurant_card_list);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        mRestaurantsListAdapter = new RestaurantsListAdapter(restaurants);
        recyclerView.setAdapter(mRestaurantsListAdapter);
    }


}