package com.jzheadley.eat.services;

import com.jzheadley.eat.models.ResponseEntity;
import com.jzheadley.eat.models.Restaurant;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestaurantService {
    String SERVICE_ENDPOINT = "http://http://192.241.167.209:9000/restaurants";

    @GET
    Call<ResponseEntity> getRestaurants();

    @GET("{id}")
    Call<Restaurant> getRestaurantById(@Path("id") int restaurantId);

    @POST
    Call<Void> createRestaurant(@Body Restaurant restaurant);

    @GET
    Call<ResponseEntity> getRestaurants(@Query("size") int numberOfItems);
}
