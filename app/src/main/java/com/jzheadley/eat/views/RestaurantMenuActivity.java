package com.jzheadley.eat.views;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jzheadley.eat.R;
import com.jzheadley.eat.models.services.RestaurantService;
import com.jzheadley.eat.presenters.RestaurantMenuPresenter;

public class RestaurantMenuActivity extends BaseActivity {

    private RestaurantMenuPresenter restaurantMenuPresenter;
    private RestaurantService restaurantService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        restaurantService = new RestaurantService();
        restaurantMenuPresenter = new RestaurantMenuPresenter(this, restaurantService);
    }
}
