package com.jzheadley.eat.ui.menucreation.presenter;


import com.jzheadley.eat.data.models.Menu;
import com.jzheadley.eat.ui.base.presenter.BasePresenter;

public interface MenuCreationPresenter extends BasePresenter {
    void loadMenuCategories(Menu menu);
}
