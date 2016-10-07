package com.jzheadley.eat.services;

import com.jzheadley.eat.models.MenuItem;
import com.jzheadley.eat.models.ResponseEntity;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


public interface MenuItemService {
    String SERVICE_ENDPOINT = "http://http://192.241.167.209:9000/menuItems";

    @GET
    Observable<ResponseEntity> getMenuItems();

    @GET("{id}")
    Observable<MenuItem> getMenuItemById(@Path("id") int menuItemId);

    @POST
    Observable<Void> createMenuItem(@Body MenuItem menuItem);

    @GET
    Observable<ResponseEntity> getMenuItems(@Query("size") int numberOfItems);
}
