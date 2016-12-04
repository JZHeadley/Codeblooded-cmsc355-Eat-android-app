package com.jzheadley.eat.ui.nearbyrestaurantsmap.presenter;

import com.jzheadley.eat.data.models.Restaurant;

import java.util.List;

public interface NearbyRestaurantMapPresenter {
    List<Restaurant> loadRestaurants();
}
