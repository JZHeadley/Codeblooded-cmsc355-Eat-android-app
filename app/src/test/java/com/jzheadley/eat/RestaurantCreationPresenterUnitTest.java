package com.jzheadley.eat;

import com.jzheadley.eat.data.models.Links;
import com.jzheadley.eat.data.models.ResponseEntity;
import com.jzheadley.eat.data.models.Restaurant;
import com.jzheadley.eat.data.models.User;
import com.jzheadley.eat.data.services.RestaurantService;
import com.jzheadley.eat.data.services.UserService;
import com.jzheadley.eat.ui.restaurantcreation.presenter.RestaurantCreationPresenter;
import com.jzheadley.eat.ui.restaurantcreation.view.RestaurantCreationActivity;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import rx.Observer;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@RunWith(JUnit4.class)
public class RestaurantCreationPresenterUnitTest {


    private static RestaurantCreationPresenter presenter;
    private static Restaurant restaurant;
    private static Links links;

    @BeforeClass
    public static void construct() {
        presenter = new RestaurantCreationPresenter(new RestaurantCreationActivity(), new RestaurantService(), new UserService());
        links = new Links();
        restaurant = new Restaurant("UnitRestaurant", "unitPicture", null, "unit description", "unitZipcode", "unit address", "unitCity", "unitCountry", links);
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

    @Test
    public void postRestaurant() throws Exception {
        presenter.postRestaurant(restaurant);
        (new RestaurantService()).getRestaurantApi()
            .getRestaurants()
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(Schedulers.newThread())
            .subscribe(new Observer<ResponseEntity>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(ResponseEntity responseEntity) {
                    Assert.assertTrue(responseEntity.getEmbedded().getRestaurants().contains(restaurant));
                }
            });
    }

    @Test
    public void getUserByFirebaseId() throws Exception {
        presenter.getUserByFirebaseId("ZjE9jkJzVGY0ZfGn3620MDUpNyY2", restaurant);
        (new RestaurantService()).getRestaurantApi()
            .getRestaurants()
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(Schedulers.newThread())
            .subscribe(new Observer<ResponseEntity>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(ResponseEntity responseEntity) {
                    for (final Restaurant otherRestaurant : responseEntity.getEmbedded().getRestaurants()) {
                        if (otherRestaurant.getName().equals(restaurant.getName())) {
                            (new UserService()).getUserApi()
                                .getUserByFirebaseId("ZjE9jkJzVGY0ZfGn3620MDUpNyY2")
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
                                        Assert.assertTrue(otherRestaurant.getOwnerId() == Integer.parseInt(user.getLinks().getSelf().getHref()
                                            .replace("http://192.99.0.20:9000/users/", "")));
                                    }
                                });
                        }
                    }
                    Assert.assertTrue(responseEntity.getEmbedded().getRestaurants().contains(restaurant));
                }
            });
    }

}