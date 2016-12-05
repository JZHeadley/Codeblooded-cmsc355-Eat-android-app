package com.jzheadley.eat.views;


import android.support.test.rule.ActivityTestRule;

import com.jzheadley.eat.R;
import com.jzheadley.eat.ui.nearbyrestaurants.view.NearbyRestaurantActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class AboutTeamTest {
    @Rule
    public ActivityTestRule<NearbyRestaurantActivity> mActivityTestRule = new ActivityTestRule<>(NearbyRestaurantActivity.class);

    /*
      Given [I am a new user]
      when [I open the app]
      then [I want to see an About Us page in the slide-in menu]
    */
    @Test
    public void ensureAboutUsAppears() {
        onView(withId(R.id.slide_in_menu_icon)).perform(click());
        onView(withText("About The Team")).check(matches(isDisplayed()));
    }

    /*
        Given [I am interested in reading about the team behind the app]
        when [I click the About Us tab in the slide-in menu]
        then [I want to see brief information about the team]
     */
    @Test
    public void ensureDescriptionIsShown() {
        onView(withId(R.id.slide_in_menu_icon)).perform(click());
        onView(withText("About The Team")).perform(click());
        onView(withId(R.id.zephyr_description)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.courtney_description)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.mit_description)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.tomas_description)).perform(scrollTo()).check(matches(isDisplayed()));
    }

    /*
        Given [I want to see the team]
        when [I am in the About Us part of the app]
        then [I should see pictures of each of them]
     */
    @Test
    public void ensureEachMemberDisplayed() {
        onView(withId(R.id.slide_in_menu_icon)).perform(click());
        onView(withText("About The Team")).perform(click());
        onView(withId(R.id.zephyr_portrait)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.courtney_portrait)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.mit_portrait)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.tomas_portrait)).perform(scrollTo()).check(matches(isDisplayed()));
    }


}
