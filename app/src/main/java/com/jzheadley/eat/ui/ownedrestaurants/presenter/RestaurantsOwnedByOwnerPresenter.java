package com.jzheadley.eat.ui.ownedrestaurants.presenter;


import android.util.Log;

import com.jzheadley.eat.data.models.ResponseEntity;
import com.jzheadley.eat.data.models.User;
import com.jzheadley.eat.data.services.RestaurantService;
import com.jzheadley.eat.data.services.UserService;
import com.jzheadley.eat.ui.base.presenter.BasePresenterImpl;
import com.jzheadley.eat.ui.ownedrestaurants.view.RestaurantsOwnedByOwnerActivity;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RestaurantsOwnedByOwnerPresenter extends BasePresenterImpl {
    private static final String TAG = "RestaurantsOwnedByOwner";
    private final RestaurantService restaurantService;
    private UserService userService;
    private RestaurantsOwnedByOwnerActivity restaurantsOwnedByOwnerActivity;

    public RestaurantsOwnedByOwnerPresenter(RestaurantsOwnedByOwnerActivity
                                                restaurantsOwnedByOwnerActivity,
                                            RestaurantService restaurantService,
                                            UserService userService) {
        super(restaurantsOwnedByOwnerActivity);
        this.restaurantsOwnedByOwnerActivity = restaurantsOwnedByOwnerActivity;
        this.userService = userService;
        this.restaurantService = restaurantService;
    }

    private void loadRestaurants(User restaurantOwner) {
        Log.d(TAG, "loadRestaurants: " + restaurantOwner);
        int userId = Integer.parseInt(restaurantOwner.getLinks().getSelf().getHref()
            .replace("http://192.99.0.20:9000/users/", ""));
        Log.d(TAG, "loadRestaurants: " + userId);
        restaurantService.getRestaurantApi()
            .getRestaurantsByOwnerId(userId)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<ResponseEntity>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable exception) {

                }

                @Override
                public void onNext(ResponseEntity responseEntity) {

                    restaurantsOwnedByOwnerActivity.displayRestaurantsOfOwner(
                        responseEntity.getEmbedded().getRestaurants());
                }
            });
    }

    public void getOwnedRestaurants(String firebaseId) {
        Log.d(TAG, "getOwnedRestaurants: " + firebaseId);
        userService.getUserApi()
            .getUserByFirebaseId(firebaseId)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<User>() {
                @Override
                public void onCompleted() {
                    Log.i(TAG, "onCompleted: Current User Request was completed");
                }

                @Override
                public void onError(Throwable exception) {
                    Log.e(TAG, "onError: ", exception);
                }

                @Override
                public void onNext(User user) {
                    loadRestaurants(user);
                }
            });
        Log.d(TAG, "getOwnedRestaurants: End of getOwnedRestaurants");
    }

}
