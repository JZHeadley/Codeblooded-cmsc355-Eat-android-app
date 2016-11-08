package com.jzheadley.eat.views;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.jzheadley.eat.ui.userprofile.view.UserProfileActivity;

import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * Given [I am a User]
 * When [I click "sign in"]
 * Then [I should see and option to Register as a member]
 */
@RunWith(AndroidJUnit4.class)

public class UserSignUpTest {
    @Rule
    public ActivityTestRule<UserProfileActivity> mActivityTestRule = new ActivityTestRule<>(UserProfileActivity.class, false, true);

}
