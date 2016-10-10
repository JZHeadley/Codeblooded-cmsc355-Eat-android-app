package com.jzheadley.eat.presenters;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.jzheadley.eat.R;
import com.jzheadley.eat.models.ResponseEntity;
import com.jzheadley.eat.models.services.RestaurantService;
import com.jzheadley.eat.views.BaseActivity;
import com.jzheadley.eat.views.NearbyRestaurantActivity;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NearbyRestaurantsPresenter {
    private static final String TAG = "NearbyRestaurantsPresen";
    private NearbyRestaurantActivity nearbyRestaurantActivity;
    private RestaurantService restaurantService;

    public NearbyRestaurantsPresenter(NearbyRestaurantActivity nearbyRestaurantActivity, RestaurantService restaurantService) {
        this.nearbyRestaurantActivity = nearbyRestaurantActivity;
        this.restaurantService = restaurantService;
    }

    public Drawer createDrawer(Toolbar toolbar, BaseActivity activity) {
        String[] drawerItems = activity.getResources().getStringArray(R.array.navigation_drawer_options);
        return new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbar)
                .withDisplayBelowStatusBar(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(drawerItems[0])
                                .withIcon(R.drawable.ic_home),
                        new SecondaryDrawerItem().withName(drawerItems[1])
                                .withIcon(R.drawable.ic_account),
                        new SecondaryDrawerItem().withName(drawerItems[2])
                                .withIcon(R.drawable.ic_place),
                        new SecondaryDrawerItem().withName(drawerItems[3])
                                .withIcon(R.drawable.ic_settings),
                        new SecondaryDrawerItem().withName(drawerItems[4])
                                .withIcon(R.drawable.ic_help),
                        new SecondaryDrawerItem().withName(drawerItems[5])
                                .withIcon(R.drawable.ic_restaurant)
                )
                .withDrawerGravity(Gravity.END)
                .build();
    }

    public void loadRestaurants() {
        restaurantService.getRestaurantApi()
                .getRestaurants()
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
                        Toast.makeText(nearbyRestaurantActivity, "Eat appears to be down.  Please try again later!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onError: " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(ResponseEntity responseEntity) {
                        Log.d(TAG, "onNext: " + responseEntity);
                        nearbyRestaurantActivity.displayRestaurants(responseEntity.getEmbedded().getRestaurants());
                    }
                });
    }
}
