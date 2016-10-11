package com.jzheadley.eat.views;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.jzheadley.eat.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


@RunWith(AndroidJUnit4.class)
public class RestaurantDetailsTest {

    @Rule
    public ActivityTestRule<RestaurantDetailsActivity> mActivityTestRule = new ActivityTestRule<>(RestaurantDetailsActivity.class, true, true);

    @Test
    public void restaurantDetailsConnectionTest() {
        onView(withId(R.id.restaurant_card_list)).perform(actionOnItemAtPosition(0, click()));
        onView(allOf(withId(R.id.menu_restaurant_detail), withText("Menu"), isDisplayed())).perform(click());
        onView(withId(R.id.restaurant_menu_title)).check(matches(isDisplayed()));
    }

    @Test
    public void restaurantDetailsInformationTest() {
        onView(withId(R.id.restaurant_card_list)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.restaurant_name_detail)).check(matches(withText("La Toque")));
    }

    @Test
    public void nearbyRestaurantActivityTest() {
        onView(withId(R.id.restaurant_card_list)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.menu_restaurant_detail)).check(matches(isDisplayed()));
    }

}
