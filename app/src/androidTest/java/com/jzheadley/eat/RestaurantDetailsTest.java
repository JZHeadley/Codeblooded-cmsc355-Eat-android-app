package com.jzheadley.eat;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.jzheadley.eat.R.id.description_restaurant_detail;

import org.junit.Rule;
import org.junit.Test;
import com.jzheadley.eat.views.RestaurantDetailsActivity;
import android.support.test.rule.ActivityTestRule;


public class RestaurantDetailsTest  {
        @Rule
        public ActivityTestRule<RestaurantDetailsActivity> RDActivityRule = new ActivityTestRule(RestaurantDetailsActivity.class);

        @Test
        public void listGoesOverTheFold() {
                onView(withId(R.id.description_restaurant_detail)).check(matches(isDisplayed()));
        }

}
