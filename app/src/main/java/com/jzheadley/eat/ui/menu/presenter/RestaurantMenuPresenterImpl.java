package com.jzheadley.eat.ui.menu.presenter;

import com.jzheadley.eat.data.models.services.RestaurantService;
import com.jzheadley.eat.ui.menu.view.RestaurantMenuActivity;
import com.jzheadley.eat.ui.menu.view.RestaurantMenuView;

public class RestaurantMenuPresenterImpl implements RestaurantMenuPresenter {

    private RestaurantMenuView mRestaurantMenuActivity;
    private RestaurantService mRestaurantService;

    public RestaurantMenuPresenterImpl(RestaurantMenuActivity restaurantMenuActivity, RestaurantService restaurantService) {
        this.mRestaurantMenuActivity = restaurantMenuActivity;
        this.mRestaurantService = restaurantService;
    }

}
