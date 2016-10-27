package com.jzheadley.eat.ui.ownedrestaurants.presenter;


import android.util.Log;
import android.widget.Toast;

import com.jzheadley.eat.data.models.ResponseEntity;
import com.jzheadley.eat.data.models.User;
import com.jzheadley.eat.data.models.services.EatUserService;
import com.jzheadley.eat.data.models.services.RestaurantService;
import com.jzheadley.eat.ui.ownedrestaurants.view.RestaurantsOwnedByOwnerActivity;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RestaurantsOwnedByOwnerPresenter {
    private static final String TAG = "RestaurantsOwnedByOwner";
    private EatUserService userService;
    private RestaurantsOwnedByOwnerActivity restaurantsOwnedByOwnerActivity;
    private RestaurantService restaurantService;

    public RestaurantsOwnedByOwnerPresenter(RestaurantsOwnedByOwnerActivity
                                                    restaurantsOwnedByOwnerActivity,
                                            RestaurantService restaurantService,
                                            EatUserService userService) {
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
                    public void onError(Throwable error) {
                        Log.e(TAG, "onError: loadRestaurants has Failed");
                        Toast.makeText(restaurantsOwnedByOwnerActivity, "Eat appears to be down.  "
                                + "Please try again later!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onError: " + error.getLocalizedMessage());
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
                    public void onError(Throwable error) {

                    }

                    @Override
                    public void onNext(ResponseEntity responseEntity) {
                        restaurantsOwnedByOwnerActivity.logUser(responseEntity.getEmbedded()
                                .getUsers().get(userId));

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
                    public void onError(Throwable error) {

                    }

                    @Override
                    public void onNext(ResponseEntity responseEntity) {
                        restaurantsOwnedByOwnerActivity.displayRestaurantsOfOwner(
                                responseEntity.getEmbedded().getRestaurants());
                    }
                });
    }
}
