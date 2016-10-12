package com.jzheadley.eat.views;

import android.support.test.rule.ActivityTestRule;

import com.jzheadley.eat.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


public class RestaurantsOwnedByOwnersActivityTest {
    @Rule
    public ActivityTestRule<RestaurantsOwnedByOwnerActivity> mActivityTestRule = new ActivityTestRule<>(RestaurantsOwnedByOwnerActivity.class, false, true);

    @Test
    public void restaurantOwnersRecyclerView() {
        onView(withId(R.id.owned_restaurant_card_list)).check(matches(isDisplayed()));
        onView(withId(R.id.owned_restaurant_card_list)).perform(actionOnItemAtPosition(0, click()));
    }

    @Test
    public void addRestaurantTest() {
        onView(withId(R.id.add_new_restaurant_btn)).perform(click());

        // Enter details for the restaurant
        onView(withId(R.id.restaurant_creation_name)).perform(scrollTo(), replaceText("EspressoTestRestaurant"), closeSoftKeyboard());

        onView(withId(R.id.restaurant_creation_address)).perform(scrollTo(), replaceText("EspressoTestAddress"), closeSoftKeyboard());

        onView(allOf(withId(R.id.restaurant_creation_city), withParent(withId(R.id.layout_line1)))).perform(scrollTo(), replaceText("EspressoCity"), closeSoftKeyboard());

        onView(withId(R.id.restaurant_creation_state)).perform(scrollTo(), replaceText("Es"), closeSoftKeyboard());

        onView(withId(R.id.restaurant_creation_zipcode)).perform(scrollTo(), replaceText("23220"), closeSoftKeyboard());


        onView(withId(R.id.restaurant_creation_description)).perform(scrollTo(), replaceText("Espresso is an ok Test Suite"), closeSoftKeyboard());

        // Check the checkboxes
        onView(allOf(withText("Chinese Cuisine"), withParent(withId(R.id.restaurant_creation_type_of_food_check_group)))).perform(scrollTo(), click());

        onView(allOf(withText("Fast Food"), withParent(withId(R.id.restaurant_creation_type_of_food_check_group)))).perform(scrollTo(), click());

        onView(allOf(withText("Affordable"), withParent(withId(R.id.restaurant_creation_type_of_food_check_group)))).perform(scrollTo(), click());

        // Submit the restaurant
        onView(allOf(withId(R.id.submit_new_restaurant), withText("Done"))).perform(scrollTo(), click());
    }

}
