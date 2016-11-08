package com.jzheadley.eat.data.services.api;

import com.jzheadley.eat.data.models.ResponseEntity;
import com.jzheadley.eat.data.models.User;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

import rx.Observable;

public interface UserApi {

    @GET("users/{id}")
    Observable<User> getUserById(@Path("id") int userId);

    @POST("users/")
    Observable<Void> createUser(@Body User user);

    @GET("users/")
    Observable<ResponseEntity> getUsers();

    @GET("users/")
    Observable<ResponseEntity> getUsers(@Query("size") int numberOfItems);

    @GET("users/{id}/restaurants")
    Observable<ResponseEntity> getRestaurantsOfUser(@Path("id") int userId);

    @GET("users/search/findByFirebaseId/")
    Observable<User> getUserByFirebaseId(@Query("firebaseId") String firebaseId);

    @DELETE("users/")
    Observable<Void> deleteUser(@Body User user);

    @PUT("users/{id}/")
    Observable<Void> updateUser(@Body User user, @Path("id") int userId);
}
