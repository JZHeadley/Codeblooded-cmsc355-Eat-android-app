package com.jzheadley.eat.ui.userprofile.presenter;

import com.google.firebase.auth.FirebaseAuth;
import com.jzheadley.eat.data.models.User;
import com.jzheadley.eat.data.services.UserService;
import com.jzheadley.eat.ui.base.presenter.BasePresenterImpl;
import com.jzheadley.eat.ui.userprofile.view.UserProfileActivity;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserProfilePresenter extends BasePresenterImpl {
    private static final String TAG = "UserProfilePresenter";
    private UserService userService;
    private UserProfileActivity userProfileActivity;

    public UserProfilePresenter(UserProfileActivity userProfileActivity, UserService userService) {
        super(userProfileActivity);
        this.userProfileActivity = userProfileActivity;
        this.userService = userService;
    }

    public void getUser() {
        String firebaseId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        userService.getUserApi()
                .getUserByFirebaseId(firebaseId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(User user) {
                        userProfileActivity.displayUsername(user);
                    }
                });
    }

}
