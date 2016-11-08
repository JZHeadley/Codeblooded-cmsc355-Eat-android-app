package com.jzheadley.eat.views;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import com.google.firebase.auth.FirebaseAuth;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.jzheadley.eat.R;
import com.jzheadley.eat.data.models.User;
import com.jzheadley.eat.data.services.UserService;
import com.jzheadley.eat.ui.login.view.LoginActivity;

import org.junit.AfterClass;
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
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @AfterClass
    public static void removeUser() {
        final UserService userService = new UserService();
        userService.getUserApi()
                .getUserByFirebaseId(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: Well we at least got the current user");
                    }

                    @Override
                    public void onError(Throwable exception) {
                        Log.e(TAG, "onError: Something wen't wrong with getting the user", exception);
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
                                    public void onError(Throwable error) {
                                        Log.e(TAG, "onError: User could not be deleted", error);
                                    }

                                    @Override
                                    public void onNext(Void aVoid) {
                                        Log.d(TAG, "onNext: Firebase user being deleted");
                                        FirebaseAuth.getInstance().getCurrentUser().delete();
                                    }
                                });
                    }
                });
    }


    /*
        Given [I am a User]
        When [I click "Register"]
        Then [I should be brought to a screen asking for a username, email, and password]
     */
    @Test
    public void onRegistrationTest() {
        onView(withId(R.id.btn_signup)).perform(click());

        onView(withId(R.id.username_signup)).check(matches(isDisplayed()));
        onView(withId(R.id.email)).check(matches(isDisplayed()));
        onView(withId(R.id.password)).check(matches(isDisplayed()));
    }

    @Test
    public void aSignUpTest() {
        onView(withId(R.id.btn_signup)).perform(click());
        onView(withId(R.id.username_signup)).perform(replaceText("EspressoTestUser"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(replaceText("EspresoTestUser@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(replaceText("Welcome123!"), closeSoftKeyboard());

        onView(allOf(withId(R.id.sign_up_button), withText("Register"))).perform(click());
    }


    /*
        Given [I am trying to sign in]
        when [I forget my password]
        then [I can send an reactivation link to my associated email address]
     */
    @Test
    public void forgotPasswordTest() {
        onView(withId(R.id.btn_forgot_password)).perform(click());
        onView(withId(R.id.reset_email)).perform(replaceText("EspresoTestUser@gmail.com"));
        onView(withId(R.id.btn_forgot_password)).perform(click());

    }

    /*
       Given [I am a User]
       when [I try to login and mistype my password ]
       then [I am sent back to the login activity and informed that my credentials were wrong]
    */
    @Test
    public void loginTest() {
        onView(withId(R.id.email)).perform(replaceText("EspresoTestUser@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(replaceText("Welcome123!"), closeSoftKeyboard());
        onView(allOf(withId(R.id.btn_login), isDisplayed())).perform(click());
    }

    public void accessTest() {

    }

}
