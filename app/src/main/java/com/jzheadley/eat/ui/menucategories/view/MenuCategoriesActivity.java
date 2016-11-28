package com.jzheadley.eat.ui.menucategories.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.jzheadley.eat.R;
import com.jzheadley.eat.data.models.Category;
import com.jzheadley.eat.data.models.Menu;
import com.jzheadley.eat.data.services.CategoryService;
import com.jzheadley.eat.data.services.MenuService;
import com.jzheadley.eat.ui.adapters.CategoriesListAdapter;
import com.jzheadley.eat.ui.base.view.BaseActivity;
import com.jzheadley.eat.ui.menucategories.presenter.MenuCategoriesPresenter;
import com.jzheadley.eat.ui.menucategories.presenter.MenuCategoriesPresenterImpl;

import java.util.List;

public class MenuCategoriesActivity extends BaseActivity implements MenuCategoriesView {
    private MenuCategoriesPresenter menuCategoriesPresenter;
    private Menu restaurantMenu;
    private CategoryService categoriesService;
    private RecyclerView categoriesRecyclerView;
    private MenuService menuService;
    private CategoriesListAdapter categoriesListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_categories);
        categoriesService = new CategoryService();
        menuService = new MenuService();
        menuCategoriesPresenter = new MenuCategoriesPresenterImpl(this, categoriesService, menuService);
        restaurantMenu = getIntent().getExtras().getParcelable("restaurantMenu");

        ((TextView) findViewById(R.id.menu_name)).setText(restaurantMenu.getMenuName());

        categoriesRecyclerView = (RecyclerView) findViewById(R.id.categories_recyclerview);
        categoriesRecyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoriesRecyclerView.setLayoutManager(linearLayoutManager);
        menuCategoriesPresenter.loadCategories(restaurantMenu);
    }


    @Override
    public void displayCategories(List<Category> categories) {
        categoriesListAdapter = new CategoriesListAdapter(categories);
        categoriesRecyclerView.setAdapter(categoriesListAdapter);
    }
}
