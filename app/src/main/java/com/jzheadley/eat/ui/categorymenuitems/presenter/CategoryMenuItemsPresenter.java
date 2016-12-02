package com.jzheadley.eat.ui.categorymenuitems.presenter;

import com.jzheadley.eat.data.models.Category;
import com.jzheadley.eat.ui.base.presenter.BasePresenter;

public interface CategoryMenuItemsPresenter extends BasePresenter {
    void loadMenuItems(Category menuCategory);
}
