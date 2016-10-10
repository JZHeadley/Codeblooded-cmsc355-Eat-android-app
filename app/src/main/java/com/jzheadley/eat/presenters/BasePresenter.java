package com.jzheadley.eat.presenters;

import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

import com.jzheadley.eat.R;
import com.jzheadley.eat.views.BaseActivity;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;


public class BasePresenter {

    private BaseActivity baseActivity;

    public BasePresenter(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }


    public Drawer createDrawer(Toolbar toolbar, BaseActivity activity) {
        String[] drawerItems = activity.getResources().getStringArray(R.array.navigation_drawer_options);
        return new DrawerBuilder()
                .withActivity(activity)
//                .withToolbar(toolbar)
                .withDisplayBelowStatusBar(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(drawerItems[0])
                                .withIcon(R.drawable.ic_home),
                        new SecondaryDrawerItem().withName(drawerItems[1])
                                .withIcon(R.drawable.ic_account),
                        new SecondaryDrawerItem().withName(drawerItems[2])
                                .withIcon(R.drawable.ic_place),
                        new SecondaryDrawerItem().withName(drawerItems[3])
                                .withIcon(R.drawable.ic_settings),
                        new SecondaryDrawerItem().withName(drawerItems[4])
                                .withIcon(R.drawable.ic_help),
                        new SecondaryDrawerItem().withName(drawerItems[5])
                                .withIcon(R.drawable.ic_restaurant)
                                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                                    @Override
                                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                                        return false;
                                    }
                                })
                )
                .withDrawerGravity(Gravity.END)
                .build();
    }
}
