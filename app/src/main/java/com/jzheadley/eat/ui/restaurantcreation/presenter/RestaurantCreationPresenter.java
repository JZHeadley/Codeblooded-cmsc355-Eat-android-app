package com.jzheadley.eat.ui.restaurantcreation.presenter;

import android.util.Log;
import android.widget.Toast;

import com.jzheadley.eat.data.models.Restaurant;
import com.jzheadley.eat.data.models.services.RestaurantService;
import com.jzheadley.eat.ui.restaurantcreation.view.RestaurantCreationActivity;
import com.sakebook.android.uploadhelper.UploadTaskCallback;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RestaurantCreationPresenter implements UploadTaskCallback {
    private static final String TAG = "RestaurantCreationPrese";
    private RestaurantCreationActivity mRestaurantCreationActivity;
    private RestaurantService mRestaurantService;

    public RestaurantCreationPresenter(RestaurantCreationActivity restaurantCreationActivity, RestaurantService restaurantService) {
        this.mRestaurantCreationActivity = restaurantCreationActivity;
        this.mRestaurantService = restaurantService;
    }

    public void postRestaurant(Restaurant restaurant) {
        mRestaurantService.getRestaurantApi()
                .createRestaurant(restaurant)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Void>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Void aVoid) {

                    }
                });
    }

    @Override
    public void success(String url) {
        Log.d(TAG, "success: Things worked! Heres the url to the image you just uploaded " + url);
        mRestaurantCreationActivity.setRestaurantUrl(url);
//        Toast.makeText(mRestaurantCreationActivity.getApplicationContext(), url, Toast.LENGTH_LONG).show();
    }

    @Override
    public void fail(String message) {
        Log.d(TAG, "fail: Things didn't work out between us...");
        Toast.makeText(mRestaurantCreationActivity.getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void cancel(String message) {
        Toast.makeText(mRestaurantCreationActivity.getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
