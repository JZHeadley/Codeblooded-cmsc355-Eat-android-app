package com.jzheadley.eat.presenters;


import android.util.Log;
import android.widget.Toast;

import com.jzheadley.eat.models.ResponseEntity;
import com.jzheadley.eat.models.User;
import com.jzheadley.eat.models.services.RestaurantService;
import com.jzheadley.eat.models.services.UserService;
import com.jzheadley.eat.views.RestaurantsOwnedByOwnerActivity;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RestaurantsOwnedByOwnerPresenter {
    private static final String TAG = "RestaurantsOwnedByOwner";
    private UserService userService;
    private RestaurantsOwnedByOwnerActivity restaurantsOwnedByOwnerActivity;
    private RestaurantService restaurantService;

    public RestaurantsOwnedByOwnerPresenter(RestaurantsOwnedByOwnerActivity restaurantsOwnedByOwnerActivity, RestaurantService restaurantService, UserService userService) {
        this.restaurantsOwnedByOwnerActivity = restaurantsOwnedByOwnerActivity;
        this.restaurantService = restaurantService;
        this.userService = userService;
    }

    public void loadRestaurants(User restaurantOwner) {
        restaurantService.getRestaurantApi()
                .getRestaurantsByRestaurantOwner(restaurantOwner)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseEntity>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: Restuarant Loading completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: loadRestaurants has Failed");
                        Toast.makeText(restaurantsOwnedByOwnerActivity, "Eat appears to be down.  Please try again later!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onError: " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(ResponseEntity responseEntity) {
                        Log.d(TAG, "onNext: " + responseEntity);
                    }
                });
    }

    public void loadUser(final int userId) {
        final User[] user = new User[1];
        userService.getUserApi()
                .getUsers()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseEntity responseEntity) {
                        restaurantsOwnedByOwnerActivity.logUser(responseEntity.getEmbedded().getUsers().get(userId));

                    }
                });
    }

    public void loadRestaurantsOfUser(int userId) {
        userService.getUserApi()
                .getRestaurantsOfUser(userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseEntity responseEntity) {
                        restaurantsOwnedByOwnerActivity.displayRestaurantsOfOwner(responseEntity.getEmbedded().getRestaurants());
                    }
                });
    }
}
