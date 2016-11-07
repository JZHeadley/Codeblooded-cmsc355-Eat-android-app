package com.jzheadley.eat.ui.base.presenter;

import android.support.v7.widget.Toolbar;

import com.jzheadley.eat.ui.base.view.BaseActivity;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.List;

public interface BasePresenter {
    void showProgress();

    Drawer createDrawer(final Toolbar toolbar, final BaseActivity activity);

    List<IDrawerItem> getDrawerItems(final Toolbar toolbar, BaseActivity activity);
    void hideProgress();
}
