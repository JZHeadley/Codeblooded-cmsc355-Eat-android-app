package com.jzheadley.eat.views;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.jzheadley.eat.R;
import com.jzheadley.eat.data.models.Links;
import com.jzheadley.eat.data.models.Menus;
import com.jzheadley.eat.data.models.Restaurant;
import com.jzheadley.eat.data.models.Self;
import com.jzheadley.eat.ui.restaurantdetails.view.RestaurantDetailsActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


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
                Links links = new Links(new Self("http://192.99.0.20:9000/restaurants/1"));
                links.setMenus(new Menus("http://192.99.0.20:9000/restaurants/1/menus"));
                result.putExtra("restaurant",
                    new Restaurant("La Toque", "http://latoque.com/wp-content/uploads/2015/10/Night-Patio-resized-e1455670947299.jpg", 1, "Some Description of the restaurant", "94559", "1314 McKinstry St.", "Napa", "Ca",
                        links));
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

    @Test
    // Given [ that I am a customer]when [I am looking at a restaurant's page]then [I should be able select the menu and view the categories of it]
    public void checkForCategoriesOfMenu() {
        onView(allOf(withId(R.id.menu_restaurant_detail),
            withText("Menu"),
            isDisplayed())).perform(click());
        onView(allOf(withText("Toque Dinner Menu"))).check(matches(isDisplayed()));
    }

    @Test
    // Given [ that I am a customer]when [I am looking at a restaurant's page]then [I should be able to navigate through the menu and view menuItems]
    public void checkMenuIsFullyConnected() {
        onView(allOf(withId(R.id.menu_restaurant_detail),
            withText("Menu"),
            isDisplayed())).perform(click());
        onView(
            allOf(withId(R.id.btn_view_menu), withText("Toque Dinner Menu"),
                withParent(allOf(withId(R.id.menu_layout),
                    withParent(withId(R.id.menu_card)))),
                isDisplayed()))
            .perform(click());
        onView(
            allOf(withId(R.id.btn_view_category), withText("First Courses"),
                withParent(allOf(withId(R.id.category_layout),
                    withParent(withId(R.id.category_card)))),
                isDisplayed())).perform(click());
    }

    @Test
    // Given [ that I am a customer]when [I am looking at a restaurant's page]then [I should be able select from multiple menus if the restaurant has them
    public void multipleMenuSupportTest() {
        onView(allOf(withId(R.id.menu_restaurant_detail),
            withText("Menu"),
            isDisplayed())).perform(click());
        onView(allOf(withText("Toque Dinner Menu"))).check(matches(isDisplayed()));

    }

}
