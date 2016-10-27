package com.jzheadley.eat.data.models.services.api;

import com.jzheadley.eat.data.models.Category;
import com.jzheadley.eat.data.models.ResponseEntity;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface CategoryApi {

    @GET("categories/")
    Observable<ResponseEntity> getCategories();

    @GET("categories/")
    Observable<ResponseEntity> getCategories(@Query("size") int numberOfItems);

    @GET("categories/{id}")
    Observable<Category> getCategoryById(@Path("id") int categoryId);

    @POST("categories/")
    Observable<Void> createCategory(@Body Category category);

}
