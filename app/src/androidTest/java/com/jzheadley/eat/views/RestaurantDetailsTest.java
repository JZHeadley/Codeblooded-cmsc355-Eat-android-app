package com.jzheadley.eat.views;


import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.jzheadley.eat.R;
import com.jzheadley.eat.ui.restaurantdetails.view.RestaurantDetailsActivity;

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
        new ActivityTestRule<RestaurantDetailsActivity>(RestaurantDetailsActivity.class, false, true) {
            @Override
            protected Intent getActivityIntent() {
                Context targetContext = InstrumentationRegistry.getInstrumentation()
                    .getTargetContext();
                Intent result = new Intent(targetContext, RestaurantDetailsActivity.class);
                result.putExtra("restaurantId", 0);
                return result;
            }
        };


    // Given [I am a customer]when [I click on a specific restaurant from my home screen]then [I want to see a picture or pictures of the restaurant interior]
    @Test
    public void checkForRestaurantPicture() {
        onView(withId(R.id.restaurant_photo_detail)).check(matches(isDisplayed()));
    }

    @Test
    public void restaurantDetailsConnectionTest() {
        onView(allOf(withId(R.id.menu_restaurant_detail), withText("Menu"), isDisplayed())).perform(click());
        onView(withId(R.id.restaurant_name_title)).check(matches(isDisplayed()));
    }

    @Test
    // Given [ that I am a customer]when [I have selected a restaurants "view details" page]then [I should see details like parking, alcohol, attire, etc.]
    // Checks to see if specific details of the restaurant passed in from the mock are shown
    public void restaurantDetailsInformationTest() {
        onView(withId(R.id.restaurant_name_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.restaurant_name_detail)).check(matches(withText("La Toque")));
//        onView(withId(R.id.restaurant_description)).check(matches(withText("Some Description of the restaurant")));
//        onView(withId(R.id.description_restaurant_detail)).check(matches(withText("1314 McKinstry St. Napa, Napa 94559")));
    }

    @Test
    // Given [ that I am a customer]when [I am looking at a restaurant's page]then [I should be able to select a "view menu" option for the restaurant]
    public void checkForViewMenuButton() {
        onView(withId(R.id.menu_restaurant_detail)).check(matches(isDisplayed()));
    }

}
