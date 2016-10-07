package com.jzheadley.eat.services;

import com.jzheadley.eat.models.Menu;
import com.jzheadley.eat.models.ResponseEntity;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


public interface MenuService {
    String SERVICE_ENDPOINT = "http://http://192.241.167.209:9000/menus";

    @GET
    Observable<ResponseEntity> getMenus();

    @GET("{id}")
    Observable<Menu> getMenuById(@Path("id") int menuId);

    @POST
    Observable<Void> createMenu(@Body Menu menu);

    @GET
    Observable<ResponseEntity> getMenus(@Query("size") int numberOfItems);
}
