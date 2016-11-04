package com.jzheadley.eat.views.iteration2;

import com.google.firebase.auth.FirebaseAuth;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.jzheadley.eat.data.models.User;
import com.jzheadley.eat.data.services.UserService;
import com.jzheadley.eat.ui.nearbyrestaurants.view.NearbyRestaurantActivity;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@RunWith(AndroidJUnit4.class)

public class AuthenticationTest {
    private static final String TAG = "AuthenticationTest";
    @Rule
    public ActivityTestRule<NearbyRestaurantActivity> mActivityTestRule = new ActivityTestRule<>(NearbyRestaurantActivity.class);


    @Test
    public void signUpTest() {

    }

    /*
        Given [I am a User]
        when [I try to login and mistype my password ]
        then [I am sent back to the login activity and informed that my credentials were wrong]
     */
    @Test
    public void loginTest() {

    }

    /*
        Given [I am trying to sign in]
        when [I forget my password]
        then [I can send an reactivation link to my associated email address]
     */
    @Test
    public void forgotPasswordTest() {

    }


    @Test
    public void logoutTest() {

    }

    @After
    public void removeUser() {
        final UserService userService = new UserService();
        userService.getUserApi()
            .getUserByFirebaseId(FirebaseAuth.getInstance().getCurrentUser().getUid())
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
                    userService.getUserApi().deleteUser(user)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Void>() {
                            @Override
                            public void onCompleted() {
                                Log.d(TAG, "onCompleted: User has been deleted");
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(Void aVoid) {
                                FirebaseAuth.getInstance().getCurrentUser().delete();
                            }
                        });
                }
            });
    }
}
