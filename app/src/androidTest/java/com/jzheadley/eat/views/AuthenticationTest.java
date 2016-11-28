package com.jzheadley.eat.views;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNot.not;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.widget.EditText;

import com.jzheadley.eat.R;
import com.jzheadley.eat.ui.login.view.LoginActivity;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AuthenticationTest {

    private static final String TAG = "AuthenticationTest";
    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);


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

    /*
           Given [I am a User]
           When [I have created an account]
           Then [I should be able to login in with that new account]
     */
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
    public void loginWrongCredsTest() {
        onView(withId(R.id.email)).perform(replaceText("EspressoTestUser@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(replaceText("kjb"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());
        Log.d(TAG, "loginWrongCredsTest: " + ((EditText) mActivityTestRule.getActivity().findViewById(R.id.password)).getError());
        onView(withId(R.id.password)).check(matches(not(hasErrorText(Matchers.isEmptyOrNullString()))));
    }

    /*
        Given [I am a User]
        when [I try to login ]
        then [I am to be granted access to all of my preferences and favorite restaurants.]
     */
    @Test
    public void loginTest() {
        onView(withId(R.id.email)).perform(replaceText("EspresoTestUser@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(replaceText("Welcome123!"), closeSoftKeyboard());
        onView(allOf(withId(R.id.btn_login), isDisplayed())).perform(click());

    }

    /*
        Given [I am a User]
        When [I click "sign in"]
        Then [I should see and option to Register as a member]
     */
    @Test
    public void onSignInClickSeeRegister() {
        onView(withId(R.id.btn_signup)).check(matches(isDisplayed()));
    }
}
