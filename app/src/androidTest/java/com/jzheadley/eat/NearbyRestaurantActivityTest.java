package com.jzheadley.eat;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.jzheadley.eat.views.NearbyRestaurantActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class NearbyRestaurantActivityTest {
    @Rule
    public ActivityTestRule<NearbyRestaurantActivity> activityTestRule =
            new ActivityTestRule<>(NearbyRestaurantActivity.class);

    @Test
    public void ensureNavigationMenuAppears() {
        onView(withId(R.id.slide_in_menu_icon)).perform(click());
        onView(withText("Home")).check(matches(isDisplayed()));
    }

}
