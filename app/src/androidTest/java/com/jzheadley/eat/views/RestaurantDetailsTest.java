package com.jzheadley.eat.views;


import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
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
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


@RunWith(AndroidJUnit4.class)
public class RestaurantDetailsTest {


    @Rule
    public ActivityTestRule<RestaurantDetailsActivity> mActivityRule =
            new ActivityTestRule<RestaurantDetailsActivity>(RestaurantDetailsActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    Context targetContext = InstrumentationRegistry.getInstrumentation()
                            .getTargetContext();
                    Intent result = new Intent(targetContext, RestaurantDetailsActivity.class);
                    result.putExtra("restaurantId", 0);
                    return result;
                }
            };



    @Test
    public void restaurantDetailsConnectionTest() {
        onView(allOf(withId(R.id.menu_restaurant_detail), withText("Menu"), isDisplayed())).perform(click());
        onView(withId(R.id.restaurant_menu_title)).check(matches(isDisplayed()));
    }

    @Test
    public void restaurantDetailsInformationTest() {
        onView(withId(R.id.restaurant_name_detail)).check(matches(withText("La Toque")));
    }

    @Test
    public void nearbyRestaurantActivityTest() {
        onView(withId(R.id.menu_restaurant_detail)).check(matches(isDisplayed()));
    }

}
