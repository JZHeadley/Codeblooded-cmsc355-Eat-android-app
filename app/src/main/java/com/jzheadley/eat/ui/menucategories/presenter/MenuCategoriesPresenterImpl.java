package com.jzheadley.eat.ui.menucategories.presenter;

import com.jzheadley.eat.data.services.CategoryService;
import com.jzheadley.eat.ui.base.presenter.BasePresenterImpl;
import com.jzheadley.eat.ui.base.view.BaseActivity;

public class MenuCategoriesPresenterImpl extends BasePresenterImpl implements MenuCategoriesPresenter {
    private CategoryService categoriesService;

    public MenuCategoriesPresenterImpl(BaseActivity activity, CategoryService categoriesService) {
        super(activity);
        this.categoriesService = categoriesService;
    }


}
