package com.jzheadley.eat.ui.ownedrestaurants.view;

import com.google.firebase.auth.FirebaseAuth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.jzheadley.eat.R;
import com.jzheadley.eat.data.models.Restaurant;
import com.jzheadley.eat.data.models.User;
import com.jzheadley.eat.data.services.RestaurantService;
import com.jzheadley.eat.data.services.UserService;
import com.jzheadley.eat.ui.adapters.RestaurantsListAdapter;
import com.jzheadley.eat.ui.base.view.BaseActivity;
import com.jzheadley.eat.ui.ownedrestaurants.presenter.RestaurantsOwnedByOwnerPresenter;
import com.jzheadley.eat.ui.restaurantcreation.view.RestaurantCreationActivity;

import java.util.List;


public class RestaurantsOwnedByOwnerActivity extends BaseActivity {
    private static final String TAG = "RestaurantsOwnedByOwner";
    private RestaurantsListAdapter restaurantsListAdapter;
    private RestaurantsOwnedByOwnerPresenter restaurantsOwnedByOwnerPresenter;
    private UserService userService;
    private User currentUser;
    private RestaurantService restaurantService;

    // TODO: 11/2/2016 Make this refresh after creation of a new restaurant
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants_owned_by_owner);
        userService = new UserService();
        restaurantService = new RestaurantService();
        restaurantsOwnedByOwnerPresenter = new RestaurantsOwnedByOwnerPresenter(this,
                restaurantService, userService);

        restaurantsOwnedByOwnerPresenter.showProgress();

        restaurantsOwnedByOwnerPresenter.getOwnedRestaurants(
                FirebaseAuth.getInstance().getCurrentUser().getUid());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.submit_new_restaurant);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addRestaurantIntent = new Intent(view.getContext(),
                        RestaurantCreationActivity.class);
                view.getContext().startActivity(addRestaurantIntent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        restaurantsOwnedByOwnerPresenter.getOwnedRestaurants(
                FirebaseAuth.getInstance().getCurrentUser().getUid());
    }

    public void displayRestaurantsOfOwner(List<Restaurant> restaurants) {
        Log.d(TAG, "onNext: " + restaurants);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.owned_restaurant_card_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        restaurantsListAdapter = new RestaurantsListAdapter(restaurants);
        recyclerView.setAdapter(restaurantsListAdapter);
    }

    public void onAddRestaurantClick(View view) {
        Intent addRestaurantIntent = new Intent(view.getContext(),
                RestaurantCreationActivity.class);
        view.getContext().startActivity(addRestaurantIntent);
    }
}
