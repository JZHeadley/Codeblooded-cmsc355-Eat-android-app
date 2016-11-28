package com.jzheadley.eat.ui.menucreation.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jzheadley.eat.R;
import com.jzheadley.eat.data.models.Category;
import com.jzheadley.eat.data.models.Menu;
import com.jzheadley.eat.data.services.MenuService;
import com.jzheadley.eat.ui.adapters.CategoriesListAdapter;
import com.jzheadley.eat.ui.base.view.BaseActivity;
import com.jzheadley.eat.ui.menucreation.presenter.MenuCreationPresenter;
import com.jzheadley.eat.ui.menucreation.presenter.MenuCreationPresenterImpl;

import java.util.List;


public class MenuCreationActivity extends BaseActivity implements MenuCreationView {
    private static final String TAG = "MenuCreationActivity";
    private RecyclerView categoriesRecyclerView;
    private MenuService menuService;
    private CategoriesListAdapter categoriesListAdapter;
    private MenuCreationPresenter menuCreationPresenter;
    private Menu menu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_creation);
        menuService = new MenuService();
        this.menuCreationPresenter = new MenuCreationPresenterImpl(this, menuService);
        // Setting up Categories List RecyclerView
        categoriesRecyclerView = (RecyclerView) findViewById(R.id.menu_creation_recyclerview);
        categoriesRecyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoriesRecyclerView.setLayoutManager(linearLayoutManager);
    }

    public void displayCategories(List<Category> categories) {
        categoriesListAdapter = new CategoriesListAdapter(categories);
        categoriesRecyclerView.setAdapter(categoriesListAdapter);
    }

}