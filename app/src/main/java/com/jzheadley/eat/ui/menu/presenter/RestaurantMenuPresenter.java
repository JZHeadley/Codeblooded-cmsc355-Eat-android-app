package com.jzheadley.eat.ui.menu.presenter;

import com.jzheadley.eat.models.services.RestaurantService;
import com.jzheadley.eat.ui.menu.view.RestaurantMenuActivity;

public class RestaurantMenuPresenter {

    private RestaurantMenuActivity mRestaurantMenuActivity;
    private RestaurantService mRestaurantService;

    public RestaurantMenuPresenter(RestaurantMenuActivity restaurantMenuActivity, RestaurantService restaurantService) {
        this.mRestaurantMenuActivity = restaurantMenuActivity;
        this.mRestaurantService = restaurantService;
    }

}
