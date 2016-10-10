package com.jzheadley.eat.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jzheadley.eat.R;
import com.jzheadley.eat.models.Restaurant;
import com.jzheadley.eat.models.services.RestaurantService;
import com.jzheadley.eat.presenters.NearbyRestaurantsPresenter;
import com.jzheadley.eat.views.adapters.NearbyRestaurantsAdapter;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.List;

import butterknife.ButterKnife;


public class NearbyRestaurantActivity extends BaseActivity {
    private static final String TAG = "NearbyRestaurantActivit";

    private NearbyRestaurantsAdapter restaurantsListAdapter;
    private NearbyRestaurantsPresenter nearbyRestaurantsPresenter;
    private RestaurantService restaurantService;
    private Drawer drawer;
    private List<IDrawerItem> drawerItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nearby_restaurants);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        restaurantService = new RestaurantService();
        nearbyRestaurantsPresenter = new NearbyRestaurantsPresenter(this, restaurantService);
        drawer = nearbyRestaurantsPresenter.createDrawer(toolbar, this);
        nearbyRestaurantsPresenter.loadRestaurants();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        ButterKnife.bind(this);
        restaurantService = new RestaurantService();
        nearbyRestaurantsPresenter = new NearbyRestaurantsPresenter(this, restaurantService);
        nearbyRestaurantsPresenter.loadRestaurants();
    }

    public void displayRestaurants(List<Restaurant> restaurants) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.restaurant_card_list);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        restaurantsListAdapter = new NearbyRestaurantsAdapter(restaurants);
        recyclerView.setAdapter(restaurantsListAdapter);
    }


    public void onDrawerClick(View view) {
        if (drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        } else if (!(drawer.isDrawerOpen())) {
            drawer.openDrawer();
        }
    }
}
