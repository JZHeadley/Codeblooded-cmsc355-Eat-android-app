package com.jzheadley.eat.ui.nearbyrestaurants.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.jzheadley.eat.data.models.ResponseEntity;
import com.jzheadley.eat.data.models.Restaurant;
import com.jzheadley.eat.data.services.RestaurantService;
import com.jzheadley.eat.ui.base.presenter.BasePresenterImpl;
import com.jzheadley.eat.ui.nearbyrestaurants.view.NearbyRestaurantActivity;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import java.util.List;

public class NearbyRestaurantsPresenter extends BasePresenterImpl {
    private static final String TAG = "NearbyRestaurantsPrsntr";

    private NearbyRestaurantActivity nearbyRestaurantActivity;
    private RestaurantService restaurantService;

    public NearbyRestaurantsPresenter(NearbyRestaurantActivity nearbyRestaurantActivity,
                                      RestaurantService restaurantService) {
        super(nearbyRestaurantActivity);
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
                        List<Restaurant> restaurants =
                                responseEntity.getEmbedded().getRestaurants();
                        nearbyRestaurantActivity.displayRestaurants(restaurants);
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
