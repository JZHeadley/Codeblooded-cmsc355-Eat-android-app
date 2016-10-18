package com.jzheadley.eat.ui.nearbyrestaurants.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.jzheadley.eat.models.ResponseEntity;
import com.jzheadley.eat.models.services.RestaurantService;
import com.jzheadley.eat.ui.nearbyrestaurants.view.NearbyRestaurantActivity;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NearbyRestaurantsPresenter {
    private static final String TAG = "NearbyRestaurantsPrsntr";
    private NearbyRestaurantActivity mNearbyRestaurantActivity;
    private RestaurantService mRestaurantService;

    public NearbyRestaurantsPresenter(NearbyRestaurantActivity nearbyRestaurantActivity, RestaurantService restaurantService) {
        this.mNearbyRestaurantActivity = nearbyRestaurantActivity;
        this.mRestaurantService = restaurantService;
    }


    public void loadRestaurants() {
        mRestaurantService.getRestaurantApi()
                .getRestaurants()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseEntity>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: Restaurant Loading completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: loadRestaurants has Failed");
                        Toast.makeText(mNearbyRestaurantActivity, "Eat appears to be down.  Please try again later!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onError: " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(ResponseEntity responseEntity) {
                        Log.d(TAG, "onNext: " + responseEntity);
                        mNearbyRestaurantActivity.displayRestaurants(responseEntity.getEmbedded().getRestaurants());
                    }
                });
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) mNearbyRestaurantActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
