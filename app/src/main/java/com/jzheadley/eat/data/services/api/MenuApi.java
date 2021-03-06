package com.jzheadley.eat.data.services.api;

import com.jzheadley.eat.data.models.Menu;
import com.jzheadley.eat.data.models.ResponseEntity;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import rx.Observable;

public interface MenuApi {

    @GET("menus")
    Observable<ResponseEntity> getMenus();

    @GET("menus/")
    Observable<ResponseEntity> getMenus(@Query("size") int numberOfItems);

    @GET("menus/{id}")
    Observable<Menu> getMenuById(@Path("id") int menuId);

    @POST("menus/")
    Observable<Void> createMenu(@Body Menu menu);

    @GET("menus/{menuId}/categories")
    Observable<ResponseEntity> getMenuCategories(@Path("menuId") int menuId);
}
