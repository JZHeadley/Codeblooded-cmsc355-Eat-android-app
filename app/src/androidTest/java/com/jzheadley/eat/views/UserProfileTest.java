package com.jzheadley.eat.views;

import android.support.test.rule.ActivityTestRule;

import com.jzheadley.eat.R;
import com.jzheadley.eat.ui.userprofile.view.UserProfileActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


public class UserProfileTest {
    @Rule
    public ActivityTestRule<UserProfileActivity> mActivityTestRule = new ActivityTestRule<>(UserProfileActivity.class, false, true);

    @Test
    public void restaurantDetailsConnectionTest() {
        onView(withId(R.id.user_profile_et)).check(matches(isDisplayed()));
        onView(withId(R.id.user_profile_et)).perform(typeText("newUsername"));
        onView(withId(R.id.email_profile_et)).perform(typeText("newEmail"));
        onView(withId(R.id.user_profile_update_submit_btn)).perform(click());
        onView(withId(R.id.user_profile_et)).check(matches(withHint("newUsername")));
        onView(withId(R.id.email_profile_et)).check(matches(withHint("newEmail")));
    }
}
