package com.jzheadley.eat.ui.userprofile.presenter;

import com.jzheadley.eat.ui.base.presenter.BasePresenterImpl;
import com.jzheadley.eat.ui.userprofile.view.UserProfileActivity;

public class UserProfilePresenter extends BasePresenterImpl {
    private UserProfileActivity userProfileActivity;

    public UserProfilePresenter(UserProfileActivity userProfileActivity) {
        super(userProfileActivity);
        this.userProfileActivity = userProfileActivity;
    }
}
