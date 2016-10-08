package com.jzheadley.eat.presenters;

import com.jzheadley.eat.models.Restaurant;
import com.jzheadley.eat.models.services.RestaurantService;
import com.jzheadley.eat.views.RestaurantDetailsActivity;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class RestaurantDetailsPresenter {
    private static final String TAG = "RestaurantDetailsPresen";
    private RestaurantDetailsActivity restaurantDetailsActivity;
    private RestaurantService restaurantService;

    public RestaurantDetailsPresenter(RestaurantDetailsActivity restaurantDetailsActivity, RestaurantService restaurantService) {
        this.restaurantDetailsActivity = restaurantDetailsActivity;
        this.restaurantService = restaurantService;
    }

    public void loadRestaurantDetails(int restaurantId) {
        restaurantService.getRestaurantApi()
                .getRestaurantById(restaurantId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Restaurant>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Restaurant restaurant) {
                        restaurantDetailsActivity.displayRestaurant(restaurant);
                    }
                });
    }
}
