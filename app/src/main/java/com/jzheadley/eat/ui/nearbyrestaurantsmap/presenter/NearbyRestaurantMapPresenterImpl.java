package com.jzheadley.eat.ui.nearbyrestaurantsmap.presenter;

import com.jzheadley.eat.data.models.ResponseEntity;
import com.jzheadley.eat.data.models.Restaurant;
import com.jzheadley.eat.data.services.RestaurantService;
import com.jzheadley.eat.ui.nearbyrestaurantsmap.view.NearbyRestaurantMapActivity;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import java.util.List;

public class NearbyRestaurantMapPresenterImpl implements NearbyRestaurantMapPresenter {

    private NearbyRestaurantMapActivity nearbyRestaurantMapActivity;
    private RestaurantService restaurantService;

    public NearbyRestaurantMapPresenterImpl(NearbyRestaurantMapActivity nearbyRestaurantMapActivity, RestaurantService restaurantService) {
        this.nearbyRestaurantMapActivity = nearbyRestaurantMapActivity;
        this.restaurantService = restaurantService;
    }

    @Override
    public List<Restaurant> loadRestaurants() {
        restaurantService.getRestaurantApi()
            .getRestaurants()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe(new Observer<ResponseEntity>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable exception) {

                }

                @Override
                public void onNext(ResponseEntity responseEntity) {
                    nearbyRestaurantMapActivity.addRestaurantsToMap(responseEntity.getEmbedded().getRestaurants());
                }
            });
        return null;
    }
}
