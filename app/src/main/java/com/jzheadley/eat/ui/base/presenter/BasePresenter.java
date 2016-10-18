package com.jzheadley.eat.ui.base.presenter;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

import com.jzheadley.eat.R;
import com.jzheadley.eat.ui.base.view.BaseActivity;
import com.jzheadley.eat.ui.help.view.HelpActivity;
import com.jzheadley.eat.ui.nearbyrestaurants.view.NearbyRestaurantActivity;
import com.jzheadley.eat.ui.ownedrestaurants.view.RestaurantsOwnedByOwnerActivity;
import com.jzheadley.eat.ui.settings.view.SettingsActivity;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.Drawer.OnDrawerItemClickListener;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;


public class BasePresenter {

    private BaseActivity mBaseActivity;

    public BasePresenter(BaseActivity baseActivity) {
        this.mBaseActivity = baseActivity;
    }


    public Drawer createDrawer(final Toolbar toolbar, final BaseActivity activity) {
        String[] drawerItems = activity.getResources().getStringArray(R.array.navigation_drawer_options);
        return new DrawerBuilder()
                .withActivity(activity)
//                .withToolbar(toolbar)
                .withDisplayBelowStatusBar(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(drawerItems[0])
                                .withIcon(R.drawable.ic_home)
                                .withOnDrawerItemClickListener(new OnDrawerItemClickListener() {
                                    @Override
                                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                        Intent nearbyRestaurantsIntent = new Intent(toolbar.getContext(), NearbyRestaurantActivity.class);
                                        toolbar.getContext().startActivity(nearbyRestaurantsIntent);
                                        return false;
                                    }
                                }),
                        // TODO: 10/10/2016 Wire Profile Click Listener up
                        new SecondaryDrawerItem().withName(drawerItems[1])
                                .withIcon(R.drawable.ic_account),
                        // TODO: 10/10/2016 Wire Maps Click Listener up
                        new SecondaryDrawerItem().withName(drawerItems[2])
                                .withIcon(R.drawable.ic_place),
                        new SecondaryDrawerItem().withName(drawerItems[3])
                                .withIcon(R.drawable.ic_settings)
                                .withOnDrawerItemClickListener(new OnDrawerItemClickListener() {
                                    @Override
                                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                        Intent settingsIntent = new Intent(toolbar.getContext(), SettingsActivity.class);
                                        toolbar.getContext().startActivity(settingsIntent);
                                        return false;
                                    }
                                }),
                        new SecondaryDrawerItem().withName(drawerItems[4])
                                .withIcon(R.drawable.ic_help)
                                .withOnDrawerItemClickListener(new OnDrawerItemClickListener() {
                                    @Override
                                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                        Intent helpIntent = new Intent(toolbar.getContext(), HelpActivity.class);
                                        toolbar.getContext().startActivity(helpIntent);
                                        return false;
                                    }
                                }),
                        new SecondaryDrawerItem().withName(drawerItems[5])
                                .withIcon(R.drawable.ic_restaurant)
                                .withOnDrawerItemClickListener(new OnDrawerItemClickListener() {
                                    @Override
                                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                        Intent restaurantOwnerIntent = new Intent(toolbar.getContext(), RestaurantsOwnedByOwnerActivity.class);
                                        toolbar.getContext().startActivity(restaurantOwnerIntent);

                                        return false;
                                    }
                                })
                )
                .withDrawerGravity(Gravity.END)
                .build();
    }
}
