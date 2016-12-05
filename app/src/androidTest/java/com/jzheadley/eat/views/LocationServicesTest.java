package com.jzheadley.eat.views;


import android.support.test.rule.ActivityTestRule;

import com.jzheadley.eat.R;
import com.jzheadley.eat.ui.nearbyrestaurants.view.NearbyRestaurantActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class LocationServicesTest {
    @Rule
    public ActivityTestRule<NearbyRestaurantActivity> mActivityTestRule = new ActivityTestRule<>(NearbyRestaurantActivity.class);

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    /*
      Given [that I need a nearby map]
      when [I open the side menu]
      then [I should be able to select a map view]
    */
    @Test
    public void ensureMapOptionAppears() {
        onView(withId(R.id.slide_in_menu_icon)).perform(click());
        onView(withText("Map")).check(matches(isDisplayed()));
    }

    /*
        Given [ that I am a customer]
        when [i am looking at restaurants]
        then [I should see how far the restaurants are from me]
     */

    /*
        Given [that I want to see a map of the restaurants]
        when [I choose Map on the menu]
        then [I should be brought to a map]
     */
    @Test
    public void ensureMapIsShown() {
        onView(withId(R.id.slide_in_menu_icon)).perform(click());
        onView(withText("Map")).perform(click());
        onView(withId(R.id.map)).check(matches(isDisplayed()));
    }

    @Test
    public void ensureDistanceIsDisplayed() {
        onView(withRecyclerView(R.id.restaurant_card_list).atPosition(0)).check(matches(hasDescendant(withId(R.id.restaurant_distance))));
    }

}
