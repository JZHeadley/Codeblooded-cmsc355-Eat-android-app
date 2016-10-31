package com.jzheadley.eat.ui.nearbyrestaurants.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.jzheadley.eat.data.models.ResponseEntity;
import com.jzheadley.eat.data.services.RestaurantService;
import com.jzheadley.eat.ui.nearbyrestaurants.view.NearbyRestaurantActivity;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NearbyRestaurantsPresenter {
    private static final String TAG = "NearbyRestaurantsPrsntr";


    private NearbyRestaurantActivity nearbyRestaurantActivity;
    private RestaurantService restaurantService;
    private Retrofit retrofit;

    public NearbyRestaurantsPresenter(NearbyRestaurantActivity nearbyRestaurantActivity,
                                      RestaurantService restaurantService) {
        this.nearbyRestaurantActivity = nearbyRestaurantActivity;
        this.restaurantService = restaurantService;
        // retrofit = retrofit;
    }


    public void loadRestaurants() {
        restaurantService.getRestaurantApi()
                .getRestaurants()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseEntity>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: Restaurant Loading completed");
                    }

                    @Override
                    public void onError(Throwable exception) {
                        Log.e(TAG, "onError: loadRestaurants has Failed");
                        Toast.makeText(nearbyRestaurantActivity,
                                "Eat appears to be down.  Please try again later!",
                                Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onError: " + exception.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(ResponseEntity responseEntity) {
                        Log.d(TAG, "onNext: " + responseEntity);
                        nearbyRestaurantActivity.displayRestaurants(
                                responseEntity.getEmbedded().getRestaurants());
                    }
                });
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) nearbyRestaurantActivity
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
