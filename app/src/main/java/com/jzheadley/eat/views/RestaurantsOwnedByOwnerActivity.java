package com.jzheadley.eat.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.jzheadley.eat.R;
import com.jzheadley.eat.models.Restaurant;
import com.jzheadley.eat.models.User;
import com.jzheadley.eat.models.services.RestaurantService;
import com.jzheadley.eat.models.services.UserService;
import com.jzheadley.eat.presenters.RestaurantsOwnedByOwnerPresenter;
import com.jzheadley.eat.views.adapters.RestaurantsListAdapter;

import java.util.List;


public class RestaurantsOwnedByOwnerActivity extends BaseActivity {
    private static final String TAG = "RestaurantsOwnedByOwner";
    private RestaurantsListAdapter restaurantsListAdapter;
    private RestaurantsOwnedByOwnerPresenter restaurantsOwnedByOwnerPresenter;
    private RestaurantService restaurantService;
    private UserService userService;
    private User user;

    public void logUser(User user) {
        this.user = user;
        int userId = Integer.parseInt(user.getLinks().getRestaurants().getHref().replace("http://192.99.0.20:9000/users/", "").replace("/restaurants", ""));// TODO: 10/10/2016 This is horrific.... wtf, fix this
        Log.d(TAG, "logUser: " + userId);
        restaurantsOwnedByOwnerPresenter.loadRestaurantsOfUser(userId);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants_owned_by_owner);
        restaurantService = new RestaurantService();
        userService = new UserService();
        restaurantsOwnedByOwnerPresenter = new RestaurantsOwnedByOwnerPresenter(this, restaurantService, userService);
        restaurantsOwnedByOwnerPresenter.loadUser(0);
        Log.d(TAG, "onCreate: " + user);
        restaurantsOwnedByOwnerPresenter.loadRestaurants(user);
    }

    public void displayRestaurantsOfOwner(List<Restaurant> restaurants) {
        Log.d(TAG, "onNext: " + restaurants);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.owned_restaurant_card_list);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        restaurantsListAdapter = new RestaurantsListAdapter(restaurants);
        recyclerView.setAdapter(restaurantsListAdapter);

    }
}
