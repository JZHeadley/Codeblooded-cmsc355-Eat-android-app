package com.jzheadley.eat.models.services.api;

import com.jzheadley.eat.models.ResponseEntity;
import com.jzheadley.eat.models.Restaurant;
import com.jzheadley.eat.models.User;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface RestaurantApi {

    @GET("restaurants/")
    Observable<ResponseEntity> getRestaurants();

    @GET("restaurants/{id}")
    Observable<Restaurant> getRestaurantById(@Path("id") int restaurantId);

    @POST("restaurants/")
    Observable<Void> createRestaurant(@Body Restaurant restaurant);

    @GET("restaurants/")
    Observable<ResponseEntity> getRestaurants(@Query("size") int numberOfItems);

    @GET("restaurants/")
    Observable<ResponseEntity> getRestaurantsByRestaurantOwner(@Query("restaurantOwner") User restaurantOwner);
}
