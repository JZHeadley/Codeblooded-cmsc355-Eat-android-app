package com.jzheadley.eat.ui.categorymenuitems.presenter;

import android.util.Log;

import com.jzheadley.eat.data.models.Category;
import com.jzheadley.eat.data.models.ResponseEntity;
import com.jzheadley.eat.data.services.CategoryService;
import com.jzheadley.eat.ui.base.presenter.BasePresenterImpl;
import com.jzheadley.eat.ui.categorymenuitems.view.CategoryMenuItemsActivity;
import com.jzheadley.eat.ui.categorymenuitems.view.CategoryMenuItemsView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CategoryMenuItemsPresenterImpl extends BasePresenterImpl implements CategoryMenuItemsPresenter {
    private static final String TAG = "CatMenuItemsPresenter";
    private CategoryService categoriesService;
    private CategoryMenuItemsView activity;

    public CategoryMenuItemsPresenterImpl(CategoryMenuItemsActivity activity, CategoryService categoriesService) {
        super(activity);
        this.activity = activity;
        this.categoriesService = categoriesService;
    }


    @Override
    public void loadMenuItems(Category restaurantCategory) {
        categoriesService.getCategoryApi()
            .getMenuItemsOfCategory(
                Integer.parseInt(restaurantCategory.getLinks().getMenuItems().getHref()
                    .replace("http://192.99.0.20:9000/categories/", "")
                    .replace("/menuItems", "")))
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<ResponseEntity>() {
                @Override
                public void onCompleted() {
                    Log.d(TAG, "onCompleted: Finished getting items of category");
                }

                @Override
                public void onError(Throwable exception) {

                }

                @Override
                public void onNext(ResponseEntity responseEntity) {
                    activity.displayMenuItems(responseEntity.getEmbedded().getMenuItems());
                }
            });
    }

}
