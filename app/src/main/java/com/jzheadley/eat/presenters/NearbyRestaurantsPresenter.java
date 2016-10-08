package com.jzheadley.eat.presenters;

import android.util.Log;

import com.jzheadley.eat.models.ResponseEntity;
import com.jzheadley.eat.services.RestaurantService;
import com.jzheadley.eat.views.NearbyRestaurantActivity;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NearbyRestaurantsPresenter {
    private static final String TAG = "NearbyRestaurantsPresen";
    private NearbyRestaurantActivity nearbyRestaurantActivity;
    private RestaurantService restaurantService;

    public NearbyRestaurantsPresenter(NearbyRestaurantActivity nearbyRestaurantActivity, RestaurantService restaurantService) {
        this.nearbyRestaurantActivity = nearbyRestaurantActivity;
        this.restaurantService = restaurantService;
    }

    public void loadRestaurants() {
        restaurantService.getRestaurantApi()
                .getRestaurants()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                        Log.d(TAG, "onError: " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(ResponseEntity responseEntity) {
                        Log.d(TAG, "onNext: " + responseEntity);
                        nearbyRestaurantActivity.displayRestaurants(responseEntity.getEmbedded().getRestaurants());
                    }
                });
    }
}
