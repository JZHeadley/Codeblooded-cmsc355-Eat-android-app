package com.jzheadley.eat.services.api;

import com.jzheadley.eat.models.Menu;
import com.jzheadley.eat.models.ResponseEntity;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface MenuApi {

    @GET
    Observable<ResponseEntity> getMenus();

    @GET("{id}")
    Observable<Menu> getMenuById(@Path("id") int menuId);

    @POST
    Observable<Void> createMenu(@Body Menu menu);

    @GET
    Observable<ResponseEntity> getMenus(@Query("size") int numberOfItems);
}
