package com.jzheadley.eat.ui.nearbyrestaurantsmap.presenter;

import com.jzheadley.eat.ui.nearbyrestaurantsmap.view.NearbyRestaurantMapActivity;

public class NearbyRestaurantMapPresenterImpl implements NearbyRestaurantMapPresenter {

    private NearbyRestaurantMapActivity nearbyRestaurantMapActivity;

    public NearbyRestaurantMapPresenterImpl(NearbyRestaurantMapActivity nearbyRestaurantMapActivity) {
        this.nearbyRestaurantMapActivity = nearbyRestaurantMapActivity;
    }
}
