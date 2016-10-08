package com.jzheadley.eat.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jzheadley.eat.R;
import com.jzheadley.eat.models.Restaurant;
import com.jzheadley.eat.models.services.RestaurantService;
import com.jzheadley.eat.presenters.NearbyRestaurantsPresenter;
import com.jzheadley.eat.views.adapters.NearbyRestaurantsAdapter;

import java.util.List;

import butterknife.ButterKnife;


public class NearbyRestaurantActivity extends AppCompatActivity {
    private static final String TAG = "NearbyRestaurantActivit";

    private NearbyRestaurantsAdapter restaurantsListAdapter;
    private NearbyRestaurantsPresenter nearbyRestaurantsPresenter;
    private RestaurantService restaurantService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nearby_restaurants);
        ButterKnife.bind(this);
        restaurantService = new RestaurantService();
        nearbyRestaurantsPresenter = new NearbyRestaurantsPresenter(this, restaurantService);
        nearbyRestaurantsPresenter.loadRestaurants();
    }

    public void displayRestaurants(List<Restaurant> restaurants) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.restaurant_card_list);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        restaurantsListAdapter = new NearbyRestaurantsAdapter(restaurants);
        recyclerView.setAdapter(restaurantsListAdapter);
    }
}
