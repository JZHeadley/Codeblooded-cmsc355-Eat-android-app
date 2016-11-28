package com.jzheadley.eat.ui.menu.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jzheadley.eat.R;
import com.jzheadley.eat.data.models.Category;
import com.jzheadley.eat.data.models.Restaurant;
import com.jzheadley.eat.data.services.MenuService;
import com.jzheadley.eat.ui.adapters.CategoriesListAdapter;
import com.jzheadley.eat.ui.base.view.BaseActivity;
import com.jzheadley.eat.ui.menu.presenter.RestaurantMenuPresenter;
import com.jzheadley.eat.ui.menu.presenter.RestaurantMenuPresenterImpl;

import java.util.List;

public class RestaurantMenuActivity extends BaseActivity implements RestaurantMenuView {
    private RestaurantMenuPresenter restaurantMenuPresenter;
    private MenuService menuService;
    private CategoriesListAdapter categoriesListAdapter;
    private RecyclerView categoriesRecyclerView;
    private Restaurant restaurant;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        menuService = new MenuService();
        restaurantMenuPresenter = new RestaurantMenuPresenterImpl(this, menuService);
        restaurant = getIntent().getExtras().getParcelable("restaurant");

        categoriesRecyclerView = (RecyclerView) findViewById(R.id.menu_recyclerview);
        categoriesRecyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoriesRecyclerView.setLayoutManager(linearLayoutManager);

        // restaurantMenuPresenter.loadMenuCategories(restaurant);
    }

    public void displayCategories(List<Category> categories) {
        categoriesListAdapter = new CategoriesListAdapter(categories);
        categoriesRecyclerView.setAdapter(categoriesListAdapter);
    }
}
