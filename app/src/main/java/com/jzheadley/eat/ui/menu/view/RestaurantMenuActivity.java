package com.jzheadley.eat.ui.menu.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.jzheadley.eat.R;
import com.jzheadley.eat.data.models.Menu;
import com.jzheadley.eat.data.models.Restaurant;
import com.jzheadley.eat.data.services.MenuService;
import com.jzheadley.eat.data.services.RestaurantService;
import com.jzheadley.eat.ui.adapters.MenusListAdapter;
import com.jzheadley.eat.ui.base.view.BaseActivity;
import com.jzheadley.eat.ui.menu.presenter.RestaurantMenuPresenter;
import com.jzheadley.eat.ui.menu.presenter.RestaurantMenuPresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class RestaurantMenuActivity extends BaseActivity implements RestaurantMenuView {
    private static final String TAG = "RestaurantMenuActivity";
    private RestaurantMenuPresenter restaurantMenuPresenter;
    private MenuService menuService;
    private MenusListAdapter menusListAdapter;
    private RecyclerView menusRecyclerView;
    private Restaurant restaurant;
    private RestaurantService restaurantService;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus);
        menuService = new MenuService();
        restaurantService = new RestaurantService();
        restaurantMenuPresenter = new RestaurantMenuPresenterImpl(this, menuService, restaurantService);
        restaurant = getIntent().getExtras().getParcelable("restaurant");
        ((TextView) findViewById(R.id.restaurant_name_title)).setText(restaurant.getName());

        menusRecyclerView = (RecyclerView) findViewById(R.id.menu_recyclerview);
        menusRecyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        menusRecyclerView.setLayoutManager(linearLayoutManager);
        restaurantMenuPresenter.loadRestaurantMenus(restaurant);
    }

    @Override
    public void displayMenus(List<Menu> menus) {
        List<Menu> uniqueMenus = new ArrayList<>();
        for (Menu menu : menus) {
            if (!uniqueMenus.contains(menu)) {
                uniqueMenus.add(menu);
            }
        }
        menusListAdapter = new MenusListAdapter(uniqueMenus, this);
        menusRecyclerView.setAdapter(menusListAdapter);
    }
}
