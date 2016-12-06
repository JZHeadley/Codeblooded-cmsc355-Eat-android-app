package com.jzheadley.eat.ui.favoriterestaurants.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jzheadley.eat.R;
import com.jzheadley.eat.ui.base.view.BaseActivity;

public class FavoriteRestaurantActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_restaurants);
    }
}
