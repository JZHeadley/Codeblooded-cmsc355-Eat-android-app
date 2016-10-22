package com.jzheadley.eat.ui.base.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.jzheadley.eat.R;
import com.jzheadley.eat.ui.base.presenter.BasePresenter;
import com.mikepenz.materialdrawer.Drawer;


public class BaseActivity extends AppCompatActivity implements BaseView {
    private static final String TAG = "BaseActivity";
    // Anything that will be used by all activities should be put here.
    private Toolbar mToolbar;
    private Drawer mDrawer;
    private BasePresenter mBasePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBasePresenter = new BasePresenter(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    public void setContentView(int layoutResID) {
        LinearLayout fullView = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        FrameLayout activityContainer = (FrameLayout) fullView.findViewById(R.id.activity_content);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);
        super.setContentView(fullView);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawer = mBasePresenter.createDrawer(mToolbar, this);

        // to opt out of using the mToolbar override useToolbar() and return false
        if (useToolbar()) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        } else {
            mToolbar.setVisibility(View.GONE);
        }
    }

    protected boolean useToolbar() {
        return true;
    }

    public void onDrawerClick(View view) {
        if (mDrawer.isDrawerOpen()) {
            mDrawer.closeDrawer();
        } else if (!(mDrawer.isDrawerOpen())) {
            mDrawer.openDrawer();
        }
    }

}
