package com.jzheadley.eat.ui.menu.view;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jzheadley.eat.R;
import com.jzheadley.eat.data.models.services.RestaurantService;
import com.jzheadley.eat.ui.base.view.BaseActivity;
import com.jzheadley.eat.ui.menu.presenter.RestaurantMenuPresenter;
import com.jzheadley.eat.ui.menu.presenter.RestaurantMenuPresenterImpl;

public class RestaurantMenuActivity extends BaseActivity implements RestaurantMenuView {

    private RestaurantMenuPresenter mRestaurantMenuPresenter;
    private RestaurantService mRestaurantService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mRestaurantService = new RestaurantService();
        mRestaurantMenuPresenter = new RestaurantMenuPresenterImpl(this, mRestaurantService);
    }
}
