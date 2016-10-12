package com.jzheadley.eat.views;

import android.support.test.rule.ActivityTestRule;

import com.jzheadley.eat.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


public class RestaurantsOwnedByOwnersActivityTest {
    @Rule
    public ActivityTestRule<RestaurantsOwnedByOwnerActivity> mActivityTestRule = new ActivityTestRule<>(RestaurantsOwnedByOwnerActivity.class, false, true);

    @Test
    public void restaurantOwnersRecyclerView() {
        onView(withId(R.id.owned_restaurant_card_list)).check(matches(isDisplayed()));
        onView(withId(R.id.owned_restaurant_card_list)).perform(actionOnItemAtPosition(0, click()));
    }

}
