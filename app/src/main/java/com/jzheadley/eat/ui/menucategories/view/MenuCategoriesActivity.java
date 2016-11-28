package com.jzheadley.eat.ui.menucategories.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jzheadley.eat.R;
import com.jzheadley.eat.data.models.Menu;
import com.jzheadley.eat.data.services.CategoryService;
import com.jzheadley.eat.ui.base.view.BaseActivity;
import com.jzheadley.eat.ui.menucategories.presenter.MenuCategoriesPresenter;
import com.jzheadley.eat.ui.menucategories.presenter.MenuCategoriesPresenterImpl;

public class MenuCategoriesActivity extends BaseActivity implements MenuCategoriesView {
    private MenuCategoriesPresenter menuCategoriesPresenter;
    private Menu restaurantMenu;
    private CategoryService categoriesService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_categories);
        categoriesService = new CategoryService();
        menuCategoriesPresenter = new MenuCategoriesPresenterImpl(this, categoriesService);
        restaurantMenu = getIntent().getExtras().getParcelable("restaurantMenu");

    }

}
