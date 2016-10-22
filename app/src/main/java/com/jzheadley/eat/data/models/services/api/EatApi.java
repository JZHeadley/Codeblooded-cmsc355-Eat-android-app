package com.jzheadley.eat.data.models.services.api;

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

    @GET("categories/{id}")
    Observable<Category> getCategoryById(@Path("id") int categoryId);

    @POST("categories/")
    Observable<Void> createCategory(@Body Category category);

    @GET("categories/")
    Observable<ResponseEntity> getCategories(@Query("size") int numberOfItems);

    @GET("menus")
    Observable<ResponseEntity> getMenus();

    @GET("menus/{id}")
    Observable<Menu> getMenuById(@Path("id") int menuId);

    @POST("menus/")
    Observable<Void> createMenu(@Body Menu menu);

    @GET("menus/")
    Observable<ResponseEntity> getMenus(@Query("size") int numberOfItems);

    @GET("menuItems/")
    Observable<ResponseEntity> getMenuItems();

    @GET("menuItems/{id}")
    Observable<MenuItem> getMenuItemById(@Path("id") int menuItemId);

    @POST("menuItems/")
    Observable<Void> createMenuItem(@Body MenuItem menuItem);

    @GET("menuItems/")
    Observable<ResponseEntity> getMenuItems(@Query("size") int numberOfItems);

    @GET("restaurants/")
    Observable<ResponseEntity> getRestaurants();

    @GET("restaurants/{id}")
    Observable<Restaurant> getRestaurantById(@Path("id") int restaurantId);

    @POST("restaurants/")
    Observable<Void> createRestaurant(@Body Restaurant restaurant);

    @GET("restaurants/")
    Observable<ResponseEntity> getRestaurants(@Query("size") int numberOfItems);

    @GET("restaurants/")
    Observable<ResponseEntity> getRestaurantsByRestaurantOwner(@Query("restaurantOwner") User restaurantOwner);

}
