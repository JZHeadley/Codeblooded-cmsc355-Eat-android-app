package com.jzheadley.eat.data.models.services.api;

import com.jzheadley.eat.data.models.ResponseEntity;
import com.jzheadley.eat.data.models.User;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface UserApi {

    @GET("users/")
    Observable<ResponseEntity> getUsers();

    @GET("users/{id}")
    Observable<User> getUserById(@Path("id") int userId);

    @POST("users/")
    Observable<Void> createUser(@Body User user);

    @GET("users/")
    Observable<ResponseEntity> getUsers(@Query("size") int numberOfItems);

    @GET("users/{id}/restaurants")
    Observable<ResponseEntity> getRestaurantsOfUser(@Path("id") int userId);
}