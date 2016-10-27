package com.jzheadley.eat.data.models.services.api;

import com.jzheadley.eat.data.models.MenuItem;
import com.jzheadley.eat.data.models.ResponseEntity;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface MenuItemApi {

    @GET("menuItems/")
    Observable<ResponseEntity> getMenuItems();

    @GET("menuItems/")
    Observable<ResponseEntity> getMenuItems(@Query("size") int numberOfItems);

    @GET("menuItems/{id}")
    Observable<MenuItem> getMenuItemById(@Path("id") int menuItemId);

    @POST("menuItems/")
    Observable<Void> createMenuItem(@Body MenuItem menuItem);
}
