package com.jzheadley.eat.models.services.api;

import com.jzheadley.eat.models.MenuItem;
import com.jzheadley.eat.models.ResponseEntity;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface MenuItemApi {

    @GET("menuItems/")
    Observable<ResponseEntity> getMenuItems();

    @GET("menuItems/{id}")
    Observable<MenuItem> getMenuItemById(@Path("id") int menuItemId);

    @POST("menuItems/")
    Observable<Void> createMenuItem(@Body MenuItem menuItem);

    @GET("menuItems/")
    Observable<ResponseEntity> getMenuItems(@Query("size") int numberOfItems);
}
