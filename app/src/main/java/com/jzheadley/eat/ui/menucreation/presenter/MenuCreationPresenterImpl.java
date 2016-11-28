package com.jzheadley.eat.ui.menucreation.presenter;

import com.jzheadley.eat.data.models.Menu;
import com.jzheadley.eat.data.services.MenuService;
import com.jzheadley.eat.ui.base.presenter.BasePresenterImpl;
import com.jzheadley.eat.ui.base.view.BaseActivity;

public class MenuCreationPresenterImpl extends BasePresenterImpl implements MenuCreationPresenter {
    private MenuService menuService;

    public MenuCreationPresenterImpl(BaseActivity baseActivity, MenuService menuService) {
        super(baseActivity);
        this.menuService = menuService;
    }

    @Override
    public void loadMenuCategories(Menu menu) {

    }
}
