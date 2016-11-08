package com.jzheadley.eat.views;

import android.support.test.rule.ActivityTestRule;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.jzheadley.eat.R;
import com.jzheadley.eat.data.models.User;
import com.jzheadley.eat.data.services.UserService;
import com.jzheadley.eat.ui.userprofile.view.UserProfileActivity;

import org.junit.Rule;
import org.junit.Test;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;


public class UserProfileTest {
    private static final String TAG = "UserProfileTest";
    @Rule
    public ActivityTestRule<UserProfileActivity> mActivityTestRule = new ActivityTestRule<>(UserProfileActivity.class, false, true);

    /*
        Given [ I am a user ]
        when [ I submit changes to my username and password]
        then [ I can cancel and leave things the same ]
    */
    @Test
    public void cancelUpdateTest() {
        onView(withId(R.id.user_profile_et)).check(matches(isDisplayed()));
        onView(withId(R.id.user_profile_et)).perform(typeText("newUsername"));
        onView(withId(R.id.email_profile_et)).perform(typeText("newemail@gmail.com"));
        onView(withId(R.id.user_profile_update_submit_btn)).perform(click());
        onView(withText("No")).perform(click());
        onView(withId(R.id.email_profile_et)).check(matches(withHint(FirebaseAuth.getInstance().getCurrentUser().getEmail())));
        (new UserService()).getUserApi()
                .getUserByFirebaseId(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(User user) {
                        onView(withId(R.id.user_profile_et)).check(matches(withHint(user.getUsername())));
                    }
                });

    }


    /*
        Given [I am a user]
        when [I see an error in my user profile]
        then [I should be able to modify it to be accurate]
     */

    @Test
    public void updateEmailUsernameTest() {
        onView(withId(R.id.user_profile_et)).check(matches(isDisplayed()));
        onView(withId(R.id.user_profile_et)).perform(typeText("newUsername"), closeSoftKeyboard());
        onView(withId(R.id.email_profile_et)).perform(typeText("newemail@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.user_profile_update_submit_btn)).perform(click());
        onView(withText("Yes")).perform(click());
        Log.d(TAG, "updateEmailUsernameTest: Just clicked the thingy");
        onView(withId(R.id.user_profile_et)).check(matches(withHint(is("newUsername"))));
        onView(withId(R.id.email_profile_et)).check(matches(withHint(is("newemail@gmail.com"))));
    }

}
