package com.jzheadley.eat.presenters;

import android.util.Log;
import android.widget.Toast;

import com.jzheadley.eat.models.services.RestaurantService;
import com.jzheadley.eat.views.RestaurantCreationActivity;
import com.sakebook.android.uploadhelper.UploadTaskCallback;

public class RestaurantCreationPresenter implements UploadTaskCallback {
    private static final String TAG = "RestaurantCreationPrese";
    private RestaurantCreationActivity restaurantCreationActivity;
    private RestaurantService restaurantService;

    public RestaurantCreationPresenter(RestaurantCreationActivity restaurantCreationActivity, RestaurantService restaurantService) {
        this.restaurantCreationActivity = restaurantCreationActivity;
        this.restaurantService = restaurantService;
    }


    @Override
    public void success(String url) {
        Log.d(TAG, "success: Things worked! Heres the url to the image you just uploaded " + url);
        Toast.makeText(restaurantCreationActivity.getApplicationContext(), url, Toast.LENGTH_LONG).show();
    }

    @Override
    public void fail(String message) {
        Log.d(TAG, "fail: Things didn't work out between us...");
        Toast.makeText(restaurantCreationActivity.getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void cancel(String message) {
        Log.d(TAG, "cancel: The hell are you doing here? I didn't cancel you...");
        Toast.makeText(restaurantCreationActivity.getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
