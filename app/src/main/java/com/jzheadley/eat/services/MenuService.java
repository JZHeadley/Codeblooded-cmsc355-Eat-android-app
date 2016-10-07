package com.jzheadley.eat.services;

import com.jzheadley.eat.models.Menu;
import com.jzheadley.eat.models.ResponseEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface MenuService {
    String SERVICE_ENDPOINT = "http://http://192.241.167.209:9000/menus";

    @GET
    Call<ResponseEntity> getMenus();

    @GET("{id}")
    Call<Menu> getMenuById(@Path("id") int menuId);

    @POST
    Call<Void> createMenu(@Body Menu menu);

    @GET
    Call<ResponseEntity> getMenus(@Query("size") int numberOfItems);
}
