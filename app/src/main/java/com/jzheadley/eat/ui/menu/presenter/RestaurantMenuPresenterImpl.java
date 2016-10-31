package com.jzheadley.eat.ui.menu.presenter;

import com.jzheadley.eat.data.services.RestaurantService;
import com.jzheadley.eat.ui.menu.view.RestaurantMenuActivity;
import com.jzheadley.eat.ui.menu.view.RestaurantMenuView;

public class RestaurantMenuPresenterImpl implements RestaurantMenuPresenter {

    private RestaurantMenuView restaurantMenuActivity;
    private RestaurantService restaurantService;

    public RestaurantMenuPresenterImpl(RestaurantMenuActivity restaurantMenuActivity,
                                       RestaurantService restaurantService) {
        this.restaurantMenuActivity = restaurantMenuActivity;
        this.restaurantService = restaurantService;
    }

}
