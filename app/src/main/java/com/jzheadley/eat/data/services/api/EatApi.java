package com.jzheadley.eat.data.services.api;

import com.jzheadley.eat.data.models.Category;
import com.jzheadley.eat.data.models.Menu;
import com.jzheadley.eat.data.models.MenuItem;
import com.jzheadley.eat.data.models.ResponseEntity;
import com.jzheadley.eat.data.models.Restaurant;
import com.jzheadley.eat.data.models.User;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import rx.Observable;

public interface EatApi {
    @GET("categories/")
    Observable<ResponseEntity> getCategories();

    @GET("categories/")
    Observable<ResponseEntity> getCategories(@Query("size") int numberOfItems);

    @POST("categories/")
    Observable<Void> createCategory(@Body Category category);

    @GET("categories/{id}")
    Observable<Category> getCategoryById(@Path("id") int categoryId);

    @GET("menus/{id}")
    Observable<Menu> getMenuById(@Path("id") int menuId);

    @GET("menus")
    Observable<ResponseEntity> getMenus();

    @GET("menus/")
    Observable<ResponseEntity> getMenus(@Query("size") int numberOfItems);

    @POST("menus/")
    Observable<Void> createMenu(@Body Menu menu);

    @GET("menuItems/{id}")
    Observable<MenuItem> getMenuItemById(@Path("id") int menuItemId);

    @GET("menuItems/")
    Observable<ResponseEntity> getMenuItems();

    @GET("menuItems/")
    Observable<ResponseEntity> getMenuItems(@Query("size") int numberOfItems);

    @POST("menuItems/")
    Observable<Void> createMenuItem(@Body MenuItem menuItem);

    @GET("restaurants/{id}")
    Observable<Restaurant> getRestaurantById(@Path("id") int restaurantId);

    @GET("restaurants/")
    Observable<ResponseEntity> getRestaurants();

    @GET("restaurants/")
    Observable<ResponseEntity> getRestaurants(@Query("size") int numberOfItems);

    @GET("restaurants/")
    Observable<ResponseEntity> getRestaurantsByRestaurantOwner(
            @Query("restaurantOwner") User restaurantOwner);

    @POST("restaurants/")
    Observable<Void> createRestaurant(@Body Restaurant restaurant);

}
