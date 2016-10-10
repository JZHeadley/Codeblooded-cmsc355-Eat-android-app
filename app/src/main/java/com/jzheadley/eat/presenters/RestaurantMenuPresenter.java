package com.jzheadley.eat.presenters;

import com.jzheadley.eat.models.services.RestaurantService;
import com.jzheadley.eat.views.RestaurantMenuActivity;

public class RestaurantMenuPresenter {

    private RestaurantMenuActivity restaurantMenuActivity;
    private RestaurantService restaurantService;

    public RestaurantMenuPresenter(RestaurantMenuActivity restaurantMenuActivity, RestaurantService restaurantService) {
        this.restaurantMenuActivity = restaurantMenuActivity;
        this.restaurantService = restaurantService;
    }

}
