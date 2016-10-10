package com.jzheadley.eat.presenters;

import com.jzheadley.eat.models.services.RestaurantService;
import com.jzheadley.eat.views.RestaurantCreationActivity;

public class RestaurantCreationPresenter {
    private RestaurantCreationActivity restaurantCreationActivity;
    private RestaurantService restaurantService;

    public RestaurantCreationPresenter(RestaurantCreationActivity restaurantCreationActivity, RestaurantService restaurantService) {
        this.restaurantCreationActivity = restaurantCreationActivity;
        this.restaurantService = restaurantService;
    }
}
