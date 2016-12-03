package com.jzheadley.eat.ui.categorymenuitems.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.jzheadley.eat.R;
import com.jzheadley.eat.data.models.Category;
import com.jzheadley.eat.data.models.MenuItem;
import com.jzheadley.eat.data.services.CategoryService;
import com.jzheadley.eat.ui.adapters.MenuItemsListAdapter;
import com.jzheadley.eat.ui.base.view.BaseActivity;
import com.jzheadley.eat.ui.categorymenuitems.presenter.CategoryMenuItemsPresenter;
import com.jzheadley.eat.ui.categorymenuitems.presenter.CategoryMenuItemsPresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class CategoryMenuItemsActivity extends BaseActivity implements CategoryMenuItemsView {
    private CategoryMenuItemsPresenter categoryMenuItemsPresenter;
    private Category menuCategory;
    private CategoryService categoriesService;
    private RecyclerView menuItemsRecyclerView;
    private MenuItemsListAdapter menuItemsListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_menu_items);
        categoriesService = new CategoryService();
        categoryMenuItemsPresenter = new CategoryMenuItemsPresenterImpl(this, categoriesService);
        menuCategory = getIntent().getExtras().getParcelable("menuCategory");

        ((TextView) findViewById(R.id.category_name)).setText(menuCategory.getCategoryName());

        menuItemsRecyclerView = (RecyclerView) findViewById(R.id.menuItems_recyclerview);
        menuItemsRecyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        menuItemsRecyclerView.setLayoutManager(linearLayoutManager);
        categoryMenuItemsPresenter.loadMenuItems(menuCategory);
    }


    @Override
    public void displayMenuItems(List<MenuItem> menuItems) {
        List<MenuItem> uniqueMenuItems = new ArrayList<>();
        for (MenuItem menuItem : menuItems) {
            if (!uniqueMenuItems.contains(menuItem)) {
                uniqueMenuItems.add(menuItem);
            }
        }
        menuItemsListAdapter = new MenuItemsListAdapter(uniqueMenuItems);
        menuItemsRecyclerView.setAdapter(menuItemsListAdapter);
    }
}
