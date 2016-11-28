package com.jzheadley.eat.ui.menucategories.presenter;

import android.util.Log;

import com.jzheadley.eat.data.models.Menu;
import com.jzheadley.eat.data.models.ResponseEntity;
import com.jzheadley.eat.data.services.CategoryService;
import com.jzheadley.eat.data.services.MenuService;
import com.jzheadley.eat.ui.base.presenter.BasePresenterImpl;
import com.jzheadley.eat.ui.menucategories.view.MenuCategoriesActivity;
import com.jzheadley.eat.ui.menucategories.view.MenuCategoriesView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MenuCategoriesPresenterImpl extends BasePresenterImpl implements MenuCategoriesPresenter {
    private static final String TAG = "MenuCategoriesPresenter";
    private CategoryService categoriesService;
    private MenuService menuService;
    private MenuCategoriesView menuCategoriesView;

    public MenuCategoriesPresenterImpl(MenuCategoriesActivity activity, CategoryService categoriesService, MenuService menuService) {
        super(activity);
        this.menuCategoriesView = activity;
        this.categoriesService = categoriesService;
        this.menuService = menuService;
    }


    @Override
    public void loadCategories(Menu restaurantMenu) {
        menuService.getMenuApi()
            .getMenuCategories(
                Integer.parseInt(restaurantMenu.getLinks().getCategories().getHref()
                    .replace("http://192.99.0.20:9000/menus/", "")
                    .replace("/categories", "")))
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<ResponseEntity>() {
                @Override
                public void onCompleted() {
                    Log.d(TAG, "onCompleted: Finished getting categories of menu");
                }

                @Override
                public void onError(Throwable exception) {

                }

                @Override
                public void onNext(ResponseEntity responseEntity) {
                    menuCategoriesView.displayCategories(responseEntity.getEmbedded().getCategories());
                }
            });
    }

}
