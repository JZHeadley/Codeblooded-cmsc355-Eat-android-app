package com.jzheadley.eat.ui.base.view;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.crashlytics.android.Crashlytics;
import com.jzheadley.eat.R;
import com.jzheadley.eat.ui.base.presenter.BasePresenter;
import com.mikepenz.materialdrawer.Drawer;

import io.fabric.sdk.android.Fabric;


public class BaseActivity extends AppCompatActivity implements BaseView {
    private static final String TAG = "BaseActivity";
    // Anything that will be used by all activities should be put here.
    private Toolbar toolbar;
    private Drawer drawer;
    private BasePresenter basePresenter;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        basePresenter = new BasePresenter(this);
        Fabric.with(this, new Crashlytics());
        auth = FirebaseAuth.getInstance();
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    updateDrawer();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

    }


    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);

        }
    }

    @Override
    public void setContentView(int layoutResId) {
        LinearLayout fullView =
                (LinearLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        FrameLayout activityContainer = (FrameLayout) fullView.findViewById(R.id.activity_content);
        getLayoutInflater().inflate(layoutResId, activityContainer, true);
        super.setContentView(fullView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = basePresenter.createDrawer(toolbar, this);

        // to opt out of using the toolbar override useToolbar() and return false
        if (useToolbar()) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        } else {
            toolbar.setVisibility(View.GONE);
        }

    }

    protected boolean useToolbar() {
        return true;
    }

    public void onDrawerClick(View view) {
        if (drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        } else if (!(drawer.isDrawerOpen())) {
            drawer.openDrawer();
        }
    }

    public void updateDrawer() {

        drawer = basePresenter.createDrawer(toolbar, this);
    }

}
