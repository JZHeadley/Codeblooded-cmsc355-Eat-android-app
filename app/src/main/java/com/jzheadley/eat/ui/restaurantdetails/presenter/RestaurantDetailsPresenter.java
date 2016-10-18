package com.jzheadley.eat.ui.restaurantdetails.presenter;

import com.jzheadley.eat.models.Restaurant;
import com.jzheadley.eat.models.services.RestaurantService;
import com.jzheadley.eat.ui.restaurantdetails.view.RestaurantDetailsActivity;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class RestaurantDetailsPresenter {
    private static final String TAG = "RestaurantDetailsPresen";
    private RestaurantDetailsActivity mRestaurantDetailsActivity;
    private RestaurantService mRestaurantService;

    public RestaurantDetailsPresenter(RestaurantDetailsActivity restaurantDetailsActivity, RestaurantService restaurantService) {
        this.mRestaurantDetailsActivity = restaurantDetailsActivity;
        this.mRestaurantService = restaurantService;
    }

    public void loadRestaurantDetails(int restaurantId) {
        mRestaurantService.getRestaurantApi()
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
                        mRestaurantDetailsActivity.displayRestaurant(restaurant);
                    }
                });
    }
}
