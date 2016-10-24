package com.jzheadley.eat.ui.ownedrestaurants.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.jzheadley.eat.R;
import com.jzheadley.eat.data.models.Restaurant;
import com.jzheadley.eat.data.models.User;
import com.jzheadley.eat.data.models.services.EatUserService;
import com.jzheadley.eat.data.models.services.RestaurantService;
import com.jzheadley.eat.ui.adapters.RestaurantsListAdapter;
import com.jzheadley.eat.ui.base.view.BaseActivity;
import com.jzheadley.eat.ui.ownedrestaurants.presenter.RestaurantsOwnedByOwnerPresenter;
import com.jzheadley.eat.ui.restaurantcreation.view.RestaurantCreationActivity;

import java.util.List;


public class RestaurantsOwnedByOwnerActivity extends BaseActivity {
    private static final String TAG = "RestaurantsOwnedByOwner";
    private RestaurantsListAdapter restaurantsListAdapter;
    private RestaurantsOwnedByOwnerPresenter restaurantsOwnedByOwnerPresenter;
    private RestaurantService restaurantService;
    private EatUserService userService;
    private User user;

    public void logUser(User currentUser) {
        // TODO: 10/10/2016 Fix this to actually call the currentUsers profile up from storage
        this.user = currentUser;
        int userId = Integer.parseInt(user.getLinks().getRestaurants().getHref()
                .replace("http://192.99.0.20:9000/users/", "").replace("/restaurants", "")); // TODO: 10/10/2016 This is horrific.... wtf, fix this
        Log.d(TAG, "logUser: " + userId);
        restaurantsOwnedByOwnerPresenter.loadRestaurantsOfUser(userId);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants_owned_by_owner);
        restaurantService = new RestaurantService();
        userService = new EatUserService();
        restaurantsOwnedByOwnerPresenter = new RestaurantsOwnedByOwnerPresenter(this, restaurantService, userService);
        restaurantsOwnedByOwnerPresenter.loadUser(0);
        Log.d(TAG, "onCreate: " + user);
        restaurantsOwnedByOwnerPresenter.loadRestaurants(user);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.submit_new_restaurant);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addRestaurantIntent = new Intent(view.getContext(), RestaurantCreationActivity.class);
                view.getContext().startActivity(addRestaurantIntent);
            }
        });

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

    public void onAddRestaurantClick(View view) {
        Intent addRestaurantIntent = new Intent(view.getContext(), RestaurantCreationActivity.class);
        view.getContext().startActivity(addRestaurantIntent);
    }
}
