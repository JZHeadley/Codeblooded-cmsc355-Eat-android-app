package com.jzheadley.eat.services;

import com.jzheadley.eat.models.ResponseEntity;
import com.jzheadley.eat.models.Restaurant;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface RestaurantService {
    String SERVICE_ENDPOINT = "http://http://192.241.167.209:9000/restaurants";

    @GET
    Observable<ResponseEntity> getRestaurants();

    @GET("{id}")
    Observable<Restaurant> getRestaurantById(@Path("id") int restaurantId);

    @POST
    Observable<Void> createRestaurant(@Body Restaurant restaurant);

    @GET
    Observable<ResponseEntity> getRestaurants(@Query("size") int numberOfItems);
}
