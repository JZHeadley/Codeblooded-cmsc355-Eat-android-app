package com.jzheadley.eat.services;


import com.jzheadley.eat.models.Category;
import com.jzheadley.eat.models.ResponseEntity;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


public interface CategoryService {
    String SERVICE_ENDPOINT = "http://http://192.241.167.209:9000/categories";

    @GET
    Observable<ResponseEntity> getCategories();

    @GET("{id}")
    Observable<Category> getCategoryById(@Path("id") int categoryId);

    @POST
    Observable<Void> createCategory(@Body Category category);

    @GET
    Observable<ResponseEntity> getCategories(@Query("size") int numberOfItems);
}
