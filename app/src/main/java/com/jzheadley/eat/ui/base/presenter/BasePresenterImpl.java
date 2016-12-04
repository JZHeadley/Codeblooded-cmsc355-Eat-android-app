package com.jzheadley.eat.ui.base.presenter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jzheadley.eat.R;
import com.jzheadley.eat.ui.about.view.AboutActivity;
import com.jzheadley.eat.ui.base.view.BaseActivity;
import com.jzheadley.eat.ui.favoriterestaurants.view.favoriteRestaurantActivity;
import com.jzheadley.eat.ui.help.view.HelpActivity;
import com.jzheadley.eat.ui.layoutobjects.animations.ProgressBarAnimation;
import com.jzheadley.eat.ui.login.view.LoginActivity;
import com.jzheadley.eat.ui.nearbyrestaurants.view.NearbyRestaurantActivity;
import com.jzheadley.eat.ui.ownedrestaurants.view.RestaurantsOwnedByOwnerActivity;
import com.jzheadley.eat.ui.settings.view.SettingsActivity;
import com.jzheadley.eat.ui.userprofile.view.UserProfileActivity;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.Drawer.OnDrawerItemClickListener;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.List;


public class BasePresenterImpl implements BasePresenter,
    GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "BasePresenterImpl";
    private ProgressBar progressBar;
    private GoogleApiClient googleApiClient;
    private BaseActivity baseActivity;

    public BasePresenterImpl(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
        GoogleSignInOptions gso = new GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(baseActivity.getString(R.string.default_web_client_id))
            .requestEmail()
            .build();
        googleApiClient = new GoogleApiClient.Builder(baseActivity)
            // .enableAutoManage(baseActivity, this)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build();
        googleApiClient.connect();
    }

    public Drawer createDrawer(final Toolbar toolbar, final BaseActivity activity) {
        showProgress();
        String[] drawerItems = activity.getResources()
            .getStringArray(R.array.navigation_drawer_options);
        Drawer drawer = new DrawerBuilder()
            .withActivity(activity)
            .withDisplayBelowStatusBar(true)
            .addDrawerItems(
                new PrimaryDrawerItem().withName(drawerItems[0])
                    .withIcon(R.drawable.ic_home)
                    .withOnDrawerItemClickListener(new OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(
                            View view, int position, IDrawerItem drawerItem) {
                            Intent nearbyRestaurantsIntent = new Intent(toolbar
                                .getContext(), NearbyRestaurantActivity.class);
                            toolbar.getContext().startActivity(nearbyRestaurantsIntent);
                            return false;
                        }
                    }),
                // TODO: 10/10/2016 Wire Maps Click Listener up
                new SecondaryDrawerItem().withName(drawerItems[2])
                    .withIcon(R.drawable.ic_place),
                new SecondaryDrawerItem().withName(drawerItems[3])
                    .withIcon(R.drawable.ic_settings)
                    .withOnDrawerItemClickListener(new OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(View view, int position,
                                                   IDrawerItem drawerItem) {
                            Intent settingsIntent = new Intent(toolbar.getContext(),
                                SettingsActivity.class);
                            toolbar.getContext().startActivity(settingsIntent);
                            return false;
                        }
                    }),
                new SecondaryDrawerItem().withName(drawerItems[4])
                    .withIcon(R.drawable.ic_help)
                    .withOnDrawerItemClickListener(new OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(View view, int position,
                                                   IDrawerItem drawerItem) {
                            Intent helpIntent = new Intent(toolbar.getContext(),
                                HelpActivity.class);
                            toolbar.getContext().startActivity(helpIntent);
                            return false;
                        }
                    }),

                new SecondaryDrawerItem().withName(drawerItems[5])
                    .withIcon(R.drawable.ic_info)
                    .withOnDrawerItemClickListener(new OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(View view, int position,
                                                   IDrawerItem drawerItem) {
                            Intent aboutIntent = new Intent(toolbar.getContext(),
                                AboutActivity.class);
                            toolbar.getContext().startActivity(aboutIntent);
                            return false;
                        }
                    })

            )
            .withDrawerGravity(Gravity.END)
            .build();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {

            drawer.addItem(
                new SecondaryDrawerItem().withName(drawerItems[5])
                    .withIcon(R.drawable.ic_restaurant)
                    .withOnDrawerItemClickListener(new OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(View view, int position,
                                                   IDrawerItem drawerItem) {
                            Intent restaurantOwnerIntent = new Intent(
                                toolbar.getContext(),
                                RestaurantsOwnedByOwnerActivity.class);
                            toolbar.getContext().startActivity(restaurantOwnerIntent);

                            return false;
                        }

                    }));

            drawer.addItem(
                new SecondaryDrawerItem().withName(drawerItems[6])
                    .withIcon(R.drawable.ic_star)
                    .withOnDrawerItemClickListener(new OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                            Intent favoriteIntent = new Intent(toolbar.getContext(),
                                favoriteRestaurantActivity.class);
                            toolbar.getContext().startActivity(favoriteIntent);
                            return false;
                        }
                    }));


            drawer.addItem(new SecondaryDrawerItem()
                .withName("Sign Out")
                .withOnDrawerItemClickListener(new OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position,
                                               IDrawerItem drawerItem) {
                        FirebaseAuth.getInstance().signOut();
                        baseActivity.updateDrawer();
                        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
                            new ResultCallback<Status>() {
                                @Override
                                public void onResult(@NonNull Status status) {
                                    Log.d(TAG, "onResult: " + status.getStatusMessage());
                                }
                            });
                        return false;
                    }
                })
            );

            drawer.addItemAtPosition(new ProfileDrawerItem()
                    .withIcon(currentUser.getPhotoUrl())
                    .withName(currentUser.getDisplayName())
                    .withOnDrawerItemClickListener(new OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(View view, int position,
                                                   IDrawerItem drawerItem) {
                            Intent profileIntent = new Intent(
                                toolbar.getContext(),
                                UserProfileActivity.class);
                            toolbar.getContext().startActivity(profileIntent);
                            return false;
                        }
                    }),
                0);
        } else {
            drawer.addItem(new SecondaryDrawerItem()
                .withName("Sign In")
                .withOnDrawerItemClickListener(new OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position,
                                               IDrawerItem drawerItem) {
                        Intent signInIntent = new Intent(toolbar.getContext(),
                            LoginActivity.class);
                        toolbar.getContext().startActivity(signInIntent);
                        return false;
                    }
                })
            );
        }
        hideProgress();
        return drawer;
    }


    public void showProgress() {
        ProgressBarAnimation anim = new ProgressBarAnimation(progressBar, 0, 1000);
        anim.setDuration(1000);
        if (progressBar == null) {
            progressBar = new ProgressBar(baseActivity);
            progressBar.startAnimation(anim);
            progressBar.setIndeterminate(true);
        }

        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        if (progressBar != null && progressBar.getVisibility() == View.VISIBLE) {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public List<IDrawerItem> getDrawerItems(final Toolbar toolbar, final BaseActivity activity) {
        String[] drawerItemsTextFields = activity.getResources()
            .getStringArray(R.array.navigation_drawer_options);

        List<IDrawerItem> drawerItems = new ArrayList<>();

        drawerItems.add(new PrimaryDrawerItem().withName(drawerItemsTextFields[0])
            .withIcon(R.drawable.ic_home)
            .withOnDrawerItemClickListener(new OnDrawerItemClickListener() {
                @Override
                public boolean onItemClick(
                    View view, int position, IDrawerItem drawerItem) {
                    Intent nearbyRestaurantsIntent = new Intent(toolbar
                        .getContext(), NearbyRestaurantActivity.class);
                    toolbar.getContext().startActivity(nearbyRestaurantsIntent);
                    return false;
                }
            }));

        // TODO: 10/10/2016 Wire Maps Click Listener up
        drawerItems.add(new SecondaryDrawerItem().withName(drawerItemsTextFields[2])
            .withIcon(R.drawable.ic_place));

        drawerItems.add(new SecondaryDrawerItem().withName(drawerItemsTextFields[3])
            .withIcon(R.drawable.ic_settings)
            .withOnDrawerItemClickListener(new OnDrawerItemClickListener() {
                @Override
                public boolean onItemClick(View view, int position,
                                           IDrawerItem drawerItem) {
                    Intent settingsIntent = new Intent(toolbar.getContext(),
                        SettingsActivity.class);
                    toolbar.getContext().startActivity(settingsIntent);
                    return false;
                }
            }));

        drawerItems.add(new SecondaryDrawerItem().withName(drawerItemsTextFields[4])
            .withIcon(R.drawable.ic_help)
            .withOnDrawerItemClickListener(new OnDrawerItemClickListener() {
                @Override
                public boolean onItemClick(View view, int position,
                                           IDrawerItem drawerItem) {
                    Intent helpIntent = new Intent(toolbar.getContext(),
                        HelpActivity.class);
                    toolbar.getContext().startActivity(helpIntent);
                    return false;
                }
            }));

        drawerItems.add(new SecondaryDrawerItem().withName(drawerItemsTextFields[5])
            .withIcon(R.drawable.ic_info)
            .withOnDrawerItemClickListener(new OnDrawerItemClickListener() {
                @Override
                public boolean onItemClick(View view, int position,
                                           IDrawerItem drawerItem) {
                    Intent aboutIntent = new Intent(toolbar.getContext(),
                        AboutActivity.class);
                    toolbar.getContext().startActivity(aboutIntent);
                    return false;
                }
            }));

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {

            drawerItems.add(new SecondaryDrawerItem().withName(drawerItemsTextFields[6])
                .withIcon(R.drawable.ic_star)
                .withOnDrawerItemClickListener(new OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Intent favoriteIntent = new Intent(toolbar.getContext(),
                            favoriteRestaurantActivity.class);
                        toolbar.getContext().startActivity(favoriteIntent);
                        return false;
                    }
                }));

            drawerItems.add(new SecondaryDrawerItem().withName(drawerItemsTextFields[7])
                .withIcon(R.drawable.ic_restaurant)
                .withOnDrawerItemClickListener(new OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position,
                                               IDrawerItem drawerItem) {
                        Intent restaurantOwnerIntent = new Intent(
                            toolbar.getContext(),
                            RestaurantsOwnedByOwnerActivity.class);
                        toolbar.getContext().startActivity(restaurantOwnerIntent);

                        return false;
                    }
                }));
            drawerItems.add(new SecondaryDrawerItem()
                .withName("Sign Out")
                .withOnDrawerItemClickListener(new OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position,
                                               IDrawerItem drawerItem) {
                        FirebaseAuth.getInstance().signOut();
                        baseActivity.updateDrawer();
                        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
                            new ResultCallback<Status>() {
                                @Override
                                public void onResult(@NonNull Status status) {
                                    Log.d(TAG, "onResult: " + status.getStatusMessage());
                                    activity.startActivity(
                                        new Intent(activity.getApplicationContext(),
                                            NearbyRestaurantActivity.class));
                                }
                            });
                        return false;
                    }
                })
            );
            drawerItems.add(0, new ProfileDrawerItem()
                .withIcon(currentUser.getPhotoUrl())
                .withName(currentUser.getDisplayName())
                .withOnDrawerItemClickListener(new OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position,
                                               IDrawerItem drawerItem) {
                        Intent profileIntent = new Intent(
                            toolbar.getContext(),
                            UserProfileActivity.class);
                        toolbar.getContext().startActivity(profileIntent);
                        return false;
                    }
                }));

        } else {
            drawerItems.add(new SecondaryDrawerItem()
                .withName("Sign In")
                .withOnDrawerItemClickListener(new OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position,
                                               IDrawerItem drawerItem) {
                        Intent signInIntent = new Intent(toolbar.getContext(),
                            LoginActivity.class);
                        toolbar.getContext().startActivity(signInIntent);
                        return false;
                    }
                })
            );
        }

        return drawerItems;
    }

}
