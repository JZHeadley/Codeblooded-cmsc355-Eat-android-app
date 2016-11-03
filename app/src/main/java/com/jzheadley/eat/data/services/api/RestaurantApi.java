package com.jzheadley.eat.data.services.api;

import com.jzheadley.eat.data.models.ResponseEntity;
import com.jzheadley.eat.data.models.Restaurant;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface RestaurantApi {

    @GET("restaurants/{id}")
    Observable<Restaurant> getRestaurantById(@Path("id") int restaurantId);

    @POST("restaurants/")
    Observable<Void> createRestaurant(@Body Restaurant restaurant);

    @GET("restaurants/")
    Observable<ResponseEntity> getRestaurants();

    @GET("restaurants/")
    Observable<ResponseEntity> getRestaurants(@Query("size") int numberOfItems);

    @GET("restaurants/search/findByOwnerId")
    Observable<ResponseEntity> getRestaurantsByOwnerId(
        @Query("restaurantOwnerId") int restaurantOwnerId);
}
