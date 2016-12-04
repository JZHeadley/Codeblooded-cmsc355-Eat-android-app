package com.jzheadley.eat;

import com.jzheadley.eat.data.models.Links;
import com.jzheadley.eat.data.models.ResponseEntity;
import com.jzheadley.eat.data.models.Restaurant;
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
public class RestaurantDetailsPresenterTest {


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
    public void loadRestaurantDetails() throws Exception {
        presenter.postRestaurant(restaurant);
        (new RestaurantService()).getRestaurantApi()
                .getRestaurants()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable error) {

                    }

                    @Override
                    public void onNext(ResponseEntity responseEntity) {
                        for (Restaurant otherRestaurant : responseEntity.getEmbedded().getRestaurants()) {
                            if (otherRestaurant.getName().equals(restaurant.getName())) {
                                Assert.assertTrue(restaurant.getDescription().equalsIgnoreCase("unit description"));
                            }
                        }
                    }
                });
    }

}