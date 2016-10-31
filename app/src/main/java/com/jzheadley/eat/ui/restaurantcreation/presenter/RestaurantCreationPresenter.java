package com.jzheadley.eat.ui.restaurantcreation.presenter;

import android.util.Log;
import android.widget.Toast;

import com.jzheadley.eat.data.models.Restaurant;
import com.jzheadley.eat.data.services.RestaurantService;
import com.jzheadley.eat.ui.restaurantcreation.view.RestaurantCreationActivity;
import com.jzheadley.eat.utils.Constants;
import com.sakebook.android.uploadhelper.UploadTaskCallback;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RestaurantCreationPresenter implements UploadTaskCallback {
    private static final String TAG = "RestaurantCreationPrese";
    private RestaurantCreationActivity restaurantCreationActivity;
    private RestaurantService restaurantService;

    public RestaurantCreationPresenter(RestaurantCreationActivity restaurantCreationActivity,
                                       RestaurantService restaurantService) {
        this.restaurantCreationActivity = restaurantCreationActivity;
        this.restaurantService = restaurantService;
    }

    public void postRestaurant(Restaurant restaurant) {
        restaurantService.getRestaurantApi()
                .createRestaurant(restaurant)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Void>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable error) {


                    }

                    @Override
                    public void onNext(Void avoid) {

                    }
                });
    }

    @Override
    public void success(String url) {
        Log.d(TAG, "success: Things worked! Heres the url to the image you just uploaded " + url);
        restaurantCreationActivity.setRestaurantUrl(url);
        if (Constants.DEBUG) {
            Toast.makeText(restaurantCreationActivity.getApplicationContext(), url,
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void fail(String message) {
        Log.d(TAG, "fail: Things didn't work out between us...");
        Toast.makeText(restaurantCreationActivity.getApplicationContext(), message,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void cancel(String message) {
        Toast.makeText(restaurantCreationActivity.getApplicationContext(), message,
                Toast.LENGTH_LONG).show();
    }
}
