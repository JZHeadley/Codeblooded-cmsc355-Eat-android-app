package com.jzheadley.eat.ui.nearbyrestaurants.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.jzheadley.eat.R;
import com.jzheadley.eat.data.models.Restaurant;
import com.jzheadley.eat.data.services.LocationService;
import com.jzheadley.eat.data.services.RestaurantService;
import com.jzheadley.eat.ui.adapters.RestaurantsListAdapter;
import com.jzheadley.eat.ui.base.view.BaseActivity;
import com.jzheadley.eat.ui.nearbyrestaurants.presenter.NearbyRestaurantsPresenter;

import java.util.List;


public class NearbyRestaurantActivity extends BaseActivity {
    private static final String TAG = "NearbyRestaurantActivit";

    private RestaurantsListAdapter restaurantsListAdapter;
    private NearbyRestaurantsPresenter nearbyRestaurantsPresenter;
    private RestaurantService restaurantService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_restaurants);
        restaurantService = new RestaurantService();
        nearbyRestaurantsPresenter = new NearbyRestaurantsPresenter(this, restaurantService);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                1);

        nearbyRestaurantsPresenter.showProgress();
        Log.d(TAG, "onCreate: lajhfljkashdflkjahdsf" + (new LocationService(getBaseContext())).getLocation());
        if (nearbyRestaurantsPresenter.isNetworkAvailable()) {
            nearbyRestaurantsPresenter.loadRestaurants();
        } else {
            Snackbar.make(findViewById(R.id.toolbar), "You are disconnected from the network. "
                            + "Please resolve your connection issues and try again.",
                    Snackbar.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        nearbyRestaurantsPresenter.loadRestaurants();
    }

    public void displayRestaurants(List<Restaurant> restaurants) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.restaurant_card_list);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        restaurantsListAdapter = new RestaurantsListAdapter(restaurants, getBaseContext());
        recyclerView.setAdapter(restaurantsListAdapter);

        nearbyRestaurantsPresenter.hideProgress();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Permission denied to access location", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
