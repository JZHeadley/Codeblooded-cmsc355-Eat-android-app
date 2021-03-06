package com.jzheadley.eat.views;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

import android.support.test.rule.ActivityTestRule;

import com.jzheadley.eat.R;
import com.jzheadley.eat.ui.ownedrestaurants.view.RestaurantsOwnedByOwnerActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.fabric.sdk.android.services.concurrency.DependsOn;

@DependsOn(AuthenticationTest.class)
public class RestaurantsOwnedByOwnersActivityTest {
    @Rule
    public ActivityTestRule<RestaurantsOwnedByOwnerActivity> mActivityTestRule = new ActivityTestRule<>(RestaurantsOwnedByOwnerActivity.class, false, true);


    @Test
    @Before
    // Given [I am a restaurant Owner on the RestaurantOwners side of the app] when [I am on the main screen of the owner side] then [I want to see a list of the restaurants I own]
    public void restaurantOwnersRecyclerView() {
        onView(withText("Restaurants you manage:")).check(matches(isDisplayed()));
//         onView(withId(R.id.owned_restaurant_card_list)).check(matches(isDisplayed()));
    }

    @Test
    // This may or may not work.  It was working at some point and I haven't changed anything but its not working.  Proof of it working can be shown by all of the Restaurants generated by this test.  Just look at them all...
    // Given [I want to register a restaurant]when [I am in the restaurant management side of the app]then [I want to be able to add my restaurant's details into the system]
    public void addRestaurantTest() {
        onView(withId(R.id.submit_new_restaurant)).perform(click());

        // Enter details for the restaurant
        onView(withId(R.id.restaurant_creation_name)).perform(scrollTo(), replaceText("EspressoTestRestaurant"), closeSoftKeyboard());

        onView(withId(R.id.restaurant_creation_address)).perform(scrollTo(), replaceText("EspressoTestAddress"), closeSoftKeyboard());

        onView(allOf(withId(R.id.restaurant_creation_city), withParent(withId(R.id.layout_line1)))).perform(scrollTo(), replaceText("EspressoCity"), closeSoftKeyboard());

        onView(withId(R.id.restaurant_creation_state)).perform(scrollTo(), replaceText("Es"), closeSoftKeyboard());

        onView(withId(R.id.restaurant_creation_zipcode)).perform(scrollTo(), replaceText("23220"), closeSoftKeyboard());


        onView(withId(R.id.restaurant_creation_description)).perform(scrollTo(), replaceText("Espresso is an ok Test Suite"), closeSoftKeyboard());

        // Check the checkboxes
        onView(allOf(withText("Chinese Cuisine"), withParent(withId(R.id.restaurant_food_type)))).perform(scrollTo(), click());

        onView(allOf(withText("Fast Food"), withParent(withId(R.id.restaurant_food_type)))).perform(scrollTo(), click());

        onView(allOf(withText("Affordable"), withParent(withId(R.id.restaurant_food_type)))).perform(scrollTo(), click());

        // Submit the restaurant
        onView(allOf(withId(R.id.submit_new_restaurant), withText("Done"))).perform(scrollTo(), click());
    }

    @Test
    // Given [I am in the restaurant registration screen]when [I click opening hours]then [I am redirected to a new screen that allows me to enter these pieces of detailed information]
    public void opensHourSelectionScreen() {
//        onView(withId(R.id.add_new_restaurant_btn)).perform(scrollTo());
        onView(withId(R.id.submit_new_restaurant)).perform(click());
//        onView(withId(R.id.restaurant_creation_hours)).perform(click());
        onView(withText("Hours?")).perform(click());
        onView(withId(R.id.opening_hours_title)).check(matches(isDisplayed()));
    }


}
