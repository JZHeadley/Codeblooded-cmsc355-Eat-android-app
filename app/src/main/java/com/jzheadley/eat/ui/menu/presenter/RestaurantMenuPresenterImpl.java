package com.jzheadley.eat.ui.menu.presenter;

import android.util.Log;

import com.jzheadley.eat.data.models.ResponseEntity;
import com.jzheadley.eat.data.models.Restaurant;
import com.jzheadley.eat.data.services.MenuService;
import com.jzheadley.eat.data.services.RestaurantService;
import com.jzheadley.eat.ui.menu.view.RestaurantMenuActivity;
import com.jzheadley.eat.ui.menu.view.RestaurantMenuView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RestaurantMenuPresenterImpl implements RestaurantMenuPresenter {

    private static final String TAG = "RestaurantMenuPresenter";
    private RestaurantMenuView restaurantMenuActivity;
    private MenuService menuService;
    private RestaurantService restaurantService;

    public RestaurantMenuPresenterImpl(RestaurantMenuActivity restaurantMenuActivity,
                                       MenuService menuService, RestaurantService restaurantService) {
        this.restaurantMenuActivity = restaurantMenuActivity;
        this.menuService = menuService;
        this.restaurantService = restaurantService;
    }

    @Override
    public void loadRestaurantMenus(Restaurant restaurant) {
        restaurantService.getRestaurantApi()
            .getMenusOfRestaurant(
                Integer.parseInt((restaurant.getLinks().getMenus().getHref()
                    .replace("http://192.99.0.20:9000/restaurants/", "")
                    .replace("/menus", ""))))
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<ResponseEntity>() {
                @Override
                public void onCompleted() {
                    Log.d(TAG, "onCompleted: Finished getting menus of the restaurant");
                }

                @Override
                public void onError(Throwable exception) {

                }

                @Override
                public void onNext(ResponseEntity responseEntity) {
                    restaurantMenuActivity.displayMenus(responseEntity.getEmbedded().getMenus());
                }
            });
    }
}
