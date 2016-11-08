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
import static android.support.test.espresso.matcher.ViewMatchers.withText;


public class UserProfileTest {
    @Rule
    public ActivityTestRule<UserProfileActivity> mActivityTestRule = new ActivityTestRule<>(UserProfileActivity.class, false, true);


    /*
    Given [ I am a user ]
    when [ I submit changes to my username and password]
    then [ I can cancel and leave things the same ]
    */
    @Test
    public void cancelUpdateTest() {
        onView(withId(R.id.user_profile_et)).check(matches(isDisplayed()));
        onView(withId(R.id.user_profile_et)).perform(typeText("newUsername"));
        onView(withId(R.id.email_profile_et)).perform(typeText("newemail@gmail.com"));
        onView(withId(R.id.user_profile_update_submit_btn)).perform(click());
        onView(withText("No")).perform(click());
        onView(withId(R.id.user_profile_et)).check(matches(withHint("EspressoTestUser")));
        onView(withId(R.id.email_profile_et)).check(matches(withHint("espresotestuser@gmail.com")));
    }

    /*
    Given [I am a user]
    when [I see an error in my user profile]
    then [I should be able to modify it to be accurate]
     */
    @Test
    public void updateEmailUsernameTest() {
        onView(withId(R.id.user_profile_et)).check(matches(isDisplayed()));
        onView(withId(R.id.user_profile_et)).perform(typeText("newUsername"));
        onView(withId(R.id.email_profile_et)).perform(typeText("newemail@gmail.com"));
        onView(withId(R.id.user_profile_update_submit_btn)).perform(click());
        onView(withText("Yes")).perform(click());
        onView(withId(R.id.user_profile_et)).check(matches(withHint("newUsername")));
        onView(withId(R.id.email_profile_et)).check(matches(withHint("newemail@gmail.com")));
    }

}
