package com.jzheadley.eat.ui.userprofile.presenter;

import com.jzheadley.eat.data.models.Links;
import com.jzheadley.eat.data.models.ResponseEntity;
import com.jzheadley.eat.data.models.Restaurant;
import com.jzheadley.eat.data.models.Self;
import com.jzheadley.eat.data.models.User;
import com.jzheadley.eat.data.services.UserService;
import com.jzheadley.eat.ui.userprofile.view.UserProfileActivity;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import rx.Observer;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import java.util.List;

public class UserProfilePresenterTest {
    private static UserProfilePresenter userProfilePresenter;
    private static UserService userService;
    private static UserProfileActivity userProfileActivity;
    List<Restaurant> restaurants;
    private User user;

    @BeforeClass
    public static void setup() {
        // userProfilePresenter = Mockito.mock(UserProfilePresenter.class);
        userService = new UserService();
        userProfileActivity = new UserProfileActivity();
        userProfilePresenter = new UserProfilePresenter(userProfileActivity, userService);

    }

    @AfterClass
    public static void finishUp() {
        RxAndroidPlugins.getInstance().reset();
    }

    @Before
    public void setUp() throws Exception {
        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });
    }

    @After

    public void tearDown() {
        RxAndroidPlugins.getInstance().reset();
    }

    @Before
    public void createUserForTest() {

        user = new User("", "Unit Test", new Links(new Self("10000")));
        user.setFirebaseId("unitTestFirebaseId");
        (new UserService()).getUserApi().createUser(user);
    }

    // TDD
    @Test
    public void deleteUser() throws Exception {
        userProfilePresenter.deleteUser(user);
        userService.getUserApi().getUsers()
            .subscribe(new Observer<ResponseEntity>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(ResponseEntity responseEntity) {
                    Assert.assertFalse(responseEntity.getEmbedded().getUsers().contains(user));
                }


            });
    }

    // TDD
    @Test
    public void modifyDatabaseUser() throws Exception {
        userProfilePresenter.modifyDatabaseUser(user, "modifiedUnitTestUsername");
        (new UserService()).getUserApi()
            .getUserByFirebaseId("unitTestFirebaseId")
            .observeOn(Schedulers.newThread())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<User>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(User user) {
                    Assert.assertEquals(user.getUsername().equals("modifiedUnitTestUsername"), true);
                }
            });
    }
}