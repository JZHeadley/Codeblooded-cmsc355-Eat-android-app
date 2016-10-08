package com.jzheadley.eat.models.services.api;

import com.jzheadley.eat.models.Menu;
import com.jzheadley.eat.models.ResponseEntity;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface MenuApi {

    @GET("menus")
    Observable<ResponseEntity> getMenus();

    @GET("menus/{id}")
    Observable<Menu> getMenuById(@Path("id") int menuId);

    @POST("menus/")
    Observable<Void> createMenu(@Body Menu menu);

    @GET("menus/")
    Observable<ResponseEntity> getMenus(@Query("size") int numberOfItems);
}
