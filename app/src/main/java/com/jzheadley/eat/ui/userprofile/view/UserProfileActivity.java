package com.jzheadley.eat.ui.userprofile.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jzheadley.eat.ui.base.view.BaseActivity;
import com.jzheadley.eat.ui.userprofile.presenter.UserProfilePresenter;


public class UserProfileActivity extends BaseActivity {
    UserProfilePresenter userProfilePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userProfilePresenter = new UserProfilePresenter(this);
    }
}
