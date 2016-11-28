package com.jzheadley.eat.ui.menucategories.presenter;

import com.jzheadley.eat.data.models.Menu;
import com.jzheadley.eat.ui.base.presenter.BasePresenter;

public interface MenuCategoriesPresenter extends BasePresenter {
    void loadCategories(Menu restaurantMenu);
}
