package com.jzheadley.eat.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jzheadley.eat.R;
import com.jzheadley.eat.presenters.BasePresenter;
import com.mikepenz.materialdrawer.Drawer;


public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    // Anything that will be used by all activities should be put here.
    private Toolbar toolbar;
    private Drawer drawer;
    private BasePresenter basePresenter;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        basePresenter = new BasePresenter(this);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged: signed_in " + user.getUid());
                } else {
                    Log.d(TAG, "onAuthStateChanged: signed_out");
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        LinearLayout fullView = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        FrameLayout activityContainer = (FrameLayout) fullView.findViewById(R.id.activity_content);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);
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


}
