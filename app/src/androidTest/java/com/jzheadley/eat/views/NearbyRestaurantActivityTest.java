package com.jzheadley.eat.views;


import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.jzheadley.eat.R;
import com.jzheadley.eat.ui.nearbyrestaurants.view.NearbyRestaurantActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static android.support.test.espresso.matcher.RootMatchers.withDecorView;

import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class NearbyRestaurantActivityTest {

    @Rule
    public ActivityTestRule<NearbyRestaurantActivity> mActivityTestRule = new ActivityTestRule<>(NearbyRestaurantActivity.class);

    // Given [i am a customer]  when [i click on a restaurant in the list of nearby restaurants] then [I should see the details of that restaurant]
    @Test
    //test will fail if clicking on a restaurant does not show deatails of the restaurant
    public void ensureRestaurantDetailsAppear() {
        onView(withId(R.id.restaurant_card_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.restaurant_name_detail)).check(matches(isDisplayed()));
    }

    // Given [ that I am a customer]when [I open the app] then [I should see restaurants]
    @Test
    //test will fails if a list of Restaurants does not appear
    public void ensureNearbyRestaurantsAppear() {
        onView(withId(R.id.restaurant_card_list)).check(matches(isDisplayed()));
    }

    @Test
    //test fails if scrolling isn't possible
    // Given [I am on the nearby restaurants screen] when [I am Looking for restaurants] then [i should be able to scroll on the list to view others]
    public void isItScrollable() {
        onView(withId(R.id.restaurant_card_list)).perform(RecyclerViewActions.scrollToPosition(3));
        onView(withId(R.id.restaurant_card_list)).perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
    }


}
