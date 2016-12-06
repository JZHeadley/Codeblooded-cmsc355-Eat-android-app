package com.jzheadley.eat.views;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import com.jzheadley.eat.R;
import com.jzheadley.eat.ui.nearbyrestaurants.view.NearbyRestaurantActivity;

import org.junit.Rule;
import org.junit.Test;


public class BaseActivityTest {
    @Rule
    public ActivityTestRule<NearbyRestaurantActivity> mActivityTestRule = new ActivityTestRule<>(NearbyRestaurantActivity.class);

    @Test
    public void ensureNavigationMenuAppears() {
        onView(withId(R.id.slide_in_menu_icon)).perform(click());
        onView(withText("Home")).check(matches(isDisplayed()));
    }


    /*
        Given [ I am a customer ]
        when [ I open the navigation menu ]
        then [ I want to choose my username and be brought to my profile ]
     */
    @Test
    public void displayUserProfileTest() {
        onView(withId(R.id.slide_in_menu_icon)).perform(click());
        onView(withId(R.id.material_drawer_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.user_profile_et)).check(matches(isDisplayed()));
    }


}
