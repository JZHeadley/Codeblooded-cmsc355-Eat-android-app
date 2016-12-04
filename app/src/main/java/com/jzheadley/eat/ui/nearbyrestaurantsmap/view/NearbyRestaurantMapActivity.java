package com.jzheadley.eat.ui.nearbyrestaurantsmap.view;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.jzheadley.eat.R;
import com.jzheadley.eat.data.models.Restaurant;
import com.jzheadley.eat.data.services.LocationService;
import com.jzheadley.eat.data.services.RestaurantService;
import com.jzheadley.eat.ui.nearbyrestaurantsmap.presenter.NearbyRestaurantMapPresenter;
import com.jzheadley.eat.ui.nearbyrestaurantsmap.presenter.NearbyRestaurantMapPresenterImpl;

import java.io.IOException;
import java.util.List;

public class NearbyRestaurantMapActivity extends FragmentActivity implements OnMapReadyCallback {
    private static final String TAG = "NearbyRestaurantMapActi";
    private NearbyRestaurantMapPresenter nearbyRestaurantMapPresenter;
    private GoogleMap map;
    private LocationService locationService;
    private Geocoder geocoder;
    private RestaurantService restaurantService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restaurantService = new RestaurantService();
        nearbyRestaurantMapPresenter = new NearbyRestaurantMapPresenterImpl(this, restaurantService);
        setContentView(R.layout.activity_nearby_restaurant_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationService = new LocationService(this);
        geocoder = new Geocoder(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        nearbyRestaurantMapPresenter.loadRestaurants();
    }

    public void addRestaurantsToMap(List<Restaurant> restaurants) {
        Location restaurantLocation = null;
        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(locationService.getLocation().getLatitude(), locationService.getLocation().getLongitude())));
        Log.d(TAG, "addRestaurantsToMap: " + restaurants.toString());
        for (Restaurant restaurant : restaurants) {
            List<Address> possibleLocations = null;
            if (locationService.getLocation() != null) {
                try {
                    possibleLocations = geocoder.getFromLocationName(restaurant.getName() + " " + restaurant.getCity() + " " + restaurant.getCountry(), 10);
                    if (possibleLocations.size() > 0) {
                        restaurantLocation.setLatitude(possibleLocations.get(0).getLatitude());
                        restaurantLocation.setLongitude(possibleLocations.get(0).getLongitude());

                        map.addMarker(new MarkerOptions().position(new LatLng(restaurantLocation.getLatitude(), restaurantLocation.getLongitude())).title(restaurant.getName()));
                    }
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}