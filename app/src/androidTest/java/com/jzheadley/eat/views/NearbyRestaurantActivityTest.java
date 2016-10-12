package com.jzheadley.eat.views;


import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.jzheadley.eat.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class NearbyRestaurantActivityTest {

    @Rule
    public ActivityTestRule<NearbyRestaurantActivity> mActivityTestRule = new ActivityTestRule<>(NearbyRestaurantActivity.class);


    @Test
    public void ensureRestaurantDetailsAppear() {
        onView(withId(R.id.restaurant_card_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.restaurant_name_detail)).check(matches(isDisplayed()));
    }

    @Test
    public void ensureNearbyRestaurantsAppear() {
        onView(withId(R.id.restaurant_card_list)).check(matches(isDisplayed()));
    }


}
