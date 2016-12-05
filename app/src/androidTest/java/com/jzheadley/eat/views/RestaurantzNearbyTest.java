package com.jzheadley.eat.views;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.jzheadley.eat.views.LocationServicesTest.withRecyclerView;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.jzheadley.eat.R;
import com.jzheadley.eat.ui.nearbyrestaurants.view.NearbyRestaurantActivity;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class RestaurantzNearbyTest {

    private static final long SLEEP_TIME = 5000;
    @Rule
    public ActivityTestRule<NearbyRestaurantActivity> mActivityTestRule = new ActivityTestRule<>(NearbyRestaurantActivity.class);

    @BeforeClass
    public static void beforeClass() {
        // Espresso.registerIdlingResource(new WebViewIdlingResource(getActivity().findViewById(R.id.webview)));

    }

    // Given [i am a customer]  when [i click on a restaurant in the list of nearby restaurants] then [I should see the details of that restaurant]
    @Test
    //test will fail if clicking on a restaurant does not show deatails of the restaurant
    public void ensureRestaurantDetailsAppear() {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.restaurant_card_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.restaurant_name_detail)).check(matches(isDisplayed()));
    }

    // Given [ that I am a customer]when [I open the app] then [I should see restaurants]
    @Test
    //test will fails if a list of Restaurants does not appear
    public void
    ensureNearbyRestaurantsAppear() {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.restaurant_card_list)).check(matches(isDisplayed()));
    }

    @Test
    //test fails if scrolling isn't possible
    // Given [I am on the nearby restaurants screen] when [I am Looking for restaurants] then [i should be able to scroll on the list to view others]
    public void isItScrollable() {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.restaurant_card_list)).perform(RecyclerViewActions.scrollToPosition(0));
        // onView(withId(R.id.restaurant_card_list)).perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
    }

    /*
        Given [ that I am a customer]
        when [i am looking at restaurants]
        then [I should see how far the restaurants are from me]
     */

    @Test
    public void ensureDistanceIsDisplayed() {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withRecyclerView(R.id.restaurant_card_list).atPosition(0)).check(matches(hasDescendant(withId(R.id.restaurant_distance))));
    }


}
