package com.jzheadley.eat.ui.menu.presenter;

import com.jzheadley.eat.data.models.Restaurant;
import com.jzheadley.eat.data.services.MenuService;
import com.jzheadley.eat.ui.menu.view.RestaurantMenuActivity;
import com.jzheadley.eat.ui.menu.view.RestaurantMenuView;

public class RestaurantMenuPresenterImpl implements RestaurantMenuPresenter {

    private RestaurantMenuView restaurantMenuActivity;
    private MenuService menuService;

    public RestaurantMenuPresenterImpl(RestaurantMenuActivity restaurantMenuActivity,
                                       MenuService menuService) {
        this.restaurantMenuActivity = restaurantMenuActivity;
        this.menuService = menuService;
    }

    @Override
    public void loadMenuCategories(Restaurant restaurant) {

    }
}
