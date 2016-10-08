package com.jzheadley.eat.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jzheadley.eat.R;
import com.jzheadley.eat.models.Restaurant;
import com.jzheadley.eat.models.services.RestaurantService;
import com.jzheadley.eat.presenters.RestaurantDetailsPresenter;


public class RestaurantDetailsActivity extends AppCompatActivity {
    private static final String TAG = "RestaurantDetailsActivi";
    private RestaurantDetailsPresenter restaurantDetailsPresenter;
    private RestaurantService restaurantService;
    private int restaurantId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_restaurant_details);
        restaurantService = new RestaurantService();
        restaurantDetailsPresenter = new RestaurantDetailsPresenter(this, restaurantService);
        restaurantId = getIntent().getExtras().getInt("restaurantId");
        Log.d(TAG, "onCreate: " + restaurantId);
        restaurantDetailsPresenter.loadRestaurantDetails(restaurantId + 1);
    }

    public void displayRestaurant(Restaurant restaurant) {
        Log.d(TAG, "displayRestaurant: " + restaurant);
    }
}
